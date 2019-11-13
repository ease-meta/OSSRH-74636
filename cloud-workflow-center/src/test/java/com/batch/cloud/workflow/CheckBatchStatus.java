package com.batch.cloud.workflow;

import com.open.cloud.workflow.framework.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


public class CheckBatchStatus implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(CheckBatchStatus.class);

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        boolean runing = true;
        int count = 0;
        String status = null;
        while (runing && count < 30) {
            long counts = sysUserMapper.selectAll().size();
            logger.info("{}", counts);
            //status = myJobDataDao.getBatchJobStatus(count);  // 数据库更新了 这里没有获取到最新的值
            count++;
            logger.info("job status:" + status);
            if ("COMPLETED".equals(status)) {
                runing = false;
            } else if ("EXECUTING".equals(status)) {
                try {
                    logger.info("job executing... wait 60s");
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    runing = false;
                }
            } else if ("FAILED".equals(status)) {
                throw new RuntimeException("BatchStatus is FAILED");
            } else {
                logger.error("job status:" + status);
            }
        }
        return RepeatStatus.FINISHED;
    }
}