package io.github.meta.ease.bpm;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * <p>文件名称:  ScheduleJobAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/5</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Configuration(proxyBeanMethods = false)
public class ScheduleJobAutoConfiguration implements InitializingBean {

    @Bean({"jobTaskScheduler"})
    public ThreadPoolTaskScheduler jobTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("job-scheduler-");
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        taskScheduler.setDaemon(true);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return taskScheduler;
    }

    @Bean
    public ThreadPoolTaskExecutor dataConvertTheadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        return executor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
