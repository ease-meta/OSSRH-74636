package io.activiti.spring.boot;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

/**
 * <p>文件名称:  ProcessEngineAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/5</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Configuration
@AutoConfigureAfter(name = {"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
        "org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration"})
public class ProcessEngineAutoConfiguration {

    @Bean
    public SpringAsyncExecutor springAsyncExecutor(TaskExecutor applicationTaskExecutor) {
        return new SpringAsyncExecutor(applicationTaskExecutor);
    }
}
