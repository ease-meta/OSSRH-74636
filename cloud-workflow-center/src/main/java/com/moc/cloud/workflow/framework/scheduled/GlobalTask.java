package com.moc.cloud.workflow.framework.scheduled;

import com.moc.cloud.workflow.common.utils.ToolsStrBusi;
import com.moc.cloud.workflow.framework.annotation.Log;
import com.moc.cloud.workflow.framework.entity.SysJob;
import com.moc.cloud.workflow.framework.serviceImpl.SysJobServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Leijian
 */
@Component
@Slf4j
public class GlobalTask {
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    SysJobServiceImpl sysJobServiceImpl;

    private AtomicLong atomicLong = new AtomicLong();

    /**
     * 第一次被调用前的延时，单位毫秒
     */
    @Scheduled(initialDelay = 1000 * 30, fixedDelay = 1000 * 60)
    public void initialDelay() {
        long num = atomicLong.incrementAndGet();
        log.info("第{}次执行任务GlobalTask执行时间{}", num, LocalDateTime.now());
        List<SysJob> sysJob = sysJobServiceImpl.list();
        sysJob.stream().filter(Objects::nonNull).filter(job -> !job.getStatus()).filter(job -> !checkJob(job)).distinct().forEach(job -> startJob(job));
        log.info("第{}次执行任务GlobalTask执行完成", num);
    }

    private boolean checkJob(SysJob job) {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(job.getId()), Scheduler.DEFAULT_GROUP);
        try {
            if (scheduler.checkExists(triggerKey)) {
                log.info("任务ID{},任务名{}已经存在", job.getId(), job.getJobName());
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 开启
     */
    @Log(desc = "开启定时任务")
    public boolean startJob(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            log.info("启动任务:{}", job.getJobName());
            Class clazz = Class.forName(job.getClazzPath());
            JobDetail jobDetail = JobBuilder.newJob(clazz).build();
            // 触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(job.getId()), Scheduler.DEFAULT_GROUP);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
                job.setStatus(true);
                sysJobServiceImpl.updateById(job);
                log.info("---任务[" + triggerKey.getName() + "]启动成功-------");
                return true;
            } else {
                log.info("---任务[" + triggerKey.getName() + "]已经运行，请勿再次启动-------");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 更新
     */
    @Log(desc = "更新定时任务")
    public boolean updateJob(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        String createTime = ToolsStrBusi.getSysDate("yyyy-MM-dd HH:mm:ss");

        TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(job.getId()), Scheduler.DEFAULT_GROUP);
        try {
            if (scheduler.checkExists(triggerKey)) {
                return false;
            }
            JobKey jobKey = JobKey.jobKey(String.valueOf(job.getId()), Scheduler.DEFAULT_GROUP);
            CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(job.getCron()).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(trigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
            log.info("---任务[" + triggerKey.getName() + "]更新成功-------");
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @Log(desc = "删除定时任务")
    public boolean remove(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(job.getId()), Scheduler.DEFAULT_GROUP);
        try {
            if (checkJob(job)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(JobKey.jobKey(String.valueOf(job.getId()), Scheduler.DEFAULT_GROUP));
                log.info("---任务[" + triggerKey.getName() + "]删除成功-------");
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

}
