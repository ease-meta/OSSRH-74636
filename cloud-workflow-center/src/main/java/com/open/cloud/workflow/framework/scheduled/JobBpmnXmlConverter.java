/**
 * <p>Title: JobBpmnXMLConverter.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月11日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.scheduled;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;

/**
 * @author leijian
 * @date 2019年1月11日
 */
@Slf4j
public class JobBpmnXmlConverter implements Job {

    
    @Override
    @Async
    public void execute(JobExecutionContext context) {
        log.info("JobBpmnXMLConverter：启动任务=======================");
        //TODO
        log.info("JobBpmnXMLConverter：下次执行时间=====" + DateUtil.format(context.getNextFireTime(), "yyyy-MM-dd hh:mm:ss") + "==============");

    }

}
