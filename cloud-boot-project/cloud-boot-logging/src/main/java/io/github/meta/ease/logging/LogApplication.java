package io.github.meta.ease.logging;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/15 11:32
 */
public class LogApplication implements ApplicationListener<ApplicationEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent = (ApplicationEnvironmentPreparedEvent) event;
            ConfigurableEnvironment configurableEnvironment = applicationEnvironmentPreparedEvent.getEnvironment();

            configurableEnvironment.getPropertySources().addLast(new PropertySource<String>("spring.application.name") {

                @Override
                public Object getProperty(String name) {
                    return configurableEnvironment.getProperty("spring.application.name");
                }
            });

            configurableEnvironment.getPropertySources().addLast(new PropertySource<String>("spring.application.name") {

                @Override
                public Object getProperty(String name) {
                    return configurableEnvironment.getProperty("spring.application.name");
                }
            });
        }
    }

    /**
     * 需要在日志初始之前执行。。。注意-15会报错；-10不会。因为-15~-9之间有其他的listener
     *
     * @return
     */
    @Override
    public int getOrder() {
        return LoggingApplicationListener.DEFAULT_ORDER - 6;
    }
}
