package com.open.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/4 21:29
 **/
@Slf4j
public class JobCompletionListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("BATCH JOB COMPLETED SUCCESSFULLY");
        }
    }
}
