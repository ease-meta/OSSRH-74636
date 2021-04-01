package com.learn.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 自定义Logger日志，不同类输出不同的日志文件.
 * 使用时候不要在服务主函数使用，由于以下配置需要先加载application。导致主函数无法使用。如需使用建议使用
 * org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger();
 *
 * @author: ys.
 * @date:
 */
public final class LoggerBuilder {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggerBuilder.class);

    private LoggerBuilder() {
    }

    private static final Map<Class, Logger> CONTAINER = new HashMap<>();


    /**
     * Description: 根据类定义对应的Logger.
     *
     * @param className : class
     * @author : ys.
     * @return:
     */
    public static Logger getLogger(Class className) {
        Logger logger = CONTAINER.get(className);
        if (logger != null) {
            return logger;
        }
        synchronized (LoggerBuilder.class) {
            logger = CONTAINER.get(className);
            if (logger != null) {
                return logger;
            }
            try {
                logger = build(className);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Please check the log configuration error! you must configure spring.application.name"
                        + " and spring.profiles.active in the application." + e.getMessage(), e);
                return null;
            }
            CONTAINER.put(className, logger);
        }
        return logger;
    }

    /**
     * Description: Logger生成对应的规则. 对应的引用数据配置在对应xml里面
     *
     * @param className : class
     * @author : ys.
     * @date:
     * @return:
     */
    private static Logger build(Class className) throws IllegalArgumentException {
        String name = className.getName().substring(className.getName().lastIndexOf(".")
                + 1, className.getName().length());
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(className.getName());
        //false不向上打印,仅打印一次。默认test:测试环境，dev:开发环境打印。prod:生产环境不打印
        Boolean additive = Boolean.valueOf(OptionHelper.substVars("${LOG_ADDITIVE}", context));
        logger.setAdditive(additive);
        //设置回滚策略
        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
        //设置文件保存目录
        RollingFileAppender appender = new RollingFileAppender();
        appender.setContext(context);
        appender.setName(name);
        appender.setFile(OptionHelper.substVars("${LOG_HOME}//" + "${LOG_NAME}" + "//" + name + ".log", context));
        appender.setAppend(true);
        appender.setPrudent(false);
        String fp = OptionHelper.substVars("${LOG_HOME}//" + "${LOG_NAME}" + "//" + name
                + ".%d{yyyy-MM-dd}.%i.log", context);
        policy.setMaxFileSize(FileSize.valueOf(OptionHelper.substVars("${LOG_MaxFileSize}", context)));
        policy.setFileNamePattern(fp);
        policy.setMaxHistory(Integer.valueOf(OptionHelper.substVars("${LOG_MaxHistory}", context)));
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();
        //设置输出格式
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(OptionHelper.substVars("${LOG_MSG}", context));
        encoder.start();
        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();
        logger.addAppender(appender);
        //设置级别 可以定义不同类的输出级别 默认则是xml 根目录配置级别
        /*
        Level level =Level.toLevel(Level.INFO.levelInt);
        logger.setLevel(level);
         */
        return logger;
    }
}
