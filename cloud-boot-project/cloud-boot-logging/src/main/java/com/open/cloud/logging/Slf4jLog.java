/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.logging;

import org.slf4j.Logger;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * The type Slf 4 j log.
 *
 * @author Leijian
 * @date 2020 /11/15
 */
public class Slf4jLog extends BizLogger {

    private static final Object[] NULL_ARGS = new Object[0];
    private static final String callerFQCN = Slf4jLog.class.getName();
    private final Logger log;
    private final boolean isLocationAwareLogger;

    /**
     * Instantiates a new Slf 4 j log.
     *
     * @param log the log
     */
    Slf4jLog(Logger log) {
        this.log = log;
        this.isLocationAwareLogger = (log instanceof LocationAwareLogger);
    }

    /**
     * BEGIN*************************DEBUG
     *****************************************/
    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void debug(String message) {
        if (isDebugEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        message, NULL_ARGS, null);
            } else {
                log.debug(message);
            }
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (isDebugEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.debug(format, arg);
            }
        }

    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (isDebugEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.debug(format, arg1, arg2);
            }
        }

    }

    @Override
    public void debug(String message, Throwable t) {
        if (isDebugEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        message, NULL_ARGS, t);
            } else {
                log.debug(message, t);
            }
        }
    }

    @Override
    public void debug(String format, Object... args) {
        if (isDebugEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.debug(format, args);
            }
        }
    }

    /**End*************************DEBUG*****************************************/

    /**
     * BEGIN*************************INFO
     *****************************************/
    @Override
    public boolean isInfoEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void info(String msg) {
        if (isInfoEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        msg, NULL_ARGS, null);
            } else {
                log.info(msg);
            }
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (isInfoEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg);
            }
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg1, arg2);
            }
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arguments);
            }
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        msg, NULL_ARGS, t);
            } else {
                log.info(msg, t);
            }
        }
    }

    /**End*************************INFO*****************************************/

    /**
     * BEGIN*************************ERROR
     *****************************************/
    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        if (isErrorEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.ERROR_INT,
                        msg, NULL_ARGS, null);
            } else {
                log.error(msg);
            }
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (isErrorEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.ERROR_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.error(format, arg);
            }
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (isErrorEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.ERROR_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.error(format, arg1, arg2);
            }
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.ERROR_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.error(format, arguments);
            }
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.ERROR_INT,
                        msg, NULL_ARGS, t);
            } else {
                log.error(msg, t);
            }
        }
    }

    /**End*************************ERROR*****************************************/

    /**
     * BEGIN*************************Method
     *****************************************/
    @Override
    public boolean isMethodEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void method(String message) {
        if (isMethodEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        message, NULL_ARGS, null);
            } else {
                log.debug(message);
            }
        }
    }

    @Override
    public void method(String format, Object arg) {
        if (isMethodEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.debug(format, arg);
            }
        }

    }

    @Override
    public void method(String format, Object arg1, Object arg2) {
        if (isMethodEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.debug(format, arg1, arg2);
            }
        }

    }

    @Override
    public void method(String message, Throwable t) {
        if (isMethodEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        message, NULL_ARGS, t);
            } else {
                log.debug(message, t);
            }
        }
    }

    @Override
    public void method(String format, Object... args) {
        if (isMethodEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.DEBUG_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.debug(format, args);
            }
        }
    }

    /**End*************************Method*****************************************/

    /**
     * BEGIN*************************Parm
     *****************************************/
    @Override
    public boolean isParmEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public void parm(String message) {
        if (isParmEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, null);
            } else {
                log.info(message);
            }
        }
    }

    @Override
    public void parm(String format, Object arg) {
        if (isParmEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg);
            }
        }

    }

    @Override
    public void parm(String format, Object arg1, Object arg2) {
        if (isParmEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg1, arg2);
            }
        }

    }

    @Override
    public void parm(String message, Throwable t) {
        if (isParmEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, t);
            } else {
                log.info(message, t);
            }
        }
    }

    @Override
    public void parm(String format, Object... args) {
        if (isParmEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, args);
            }
        }
    }

    /**End*************************Parm*****************************************/

    /**
     * BEGIN*************************Trace
     *****************************************/
    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public void trace(String message) {
        if (isTraceEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.TRACE_INT,
                        message, NULL_ARGS, null);
            } else {
                log.trace(message);
            }
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.TRACE_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.trace(format, arg);
            }
        }

    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.TRACE_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.trace(format, arg1, arg2);
            }
        }

    }

    @Override
    public void trace(String message, Throwable t) {
        if (isTraceEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.TRACE_INT,
                        message, NULL_ARGS, t);
            } else {
                log.trace(message, t);
            }
        }
    }

    @Override
    public void trace(String format, Object... args) {
        if (isTraceEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.TRACE_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.trace(format, args);
            }
        }
    }

    /**End*************************Trace*****************************************/

    /**
     * BEGIN*************************Warn
     *****************************************/
    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public void warn(String message) {
        if (isWarnEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.WARN_INT,
                        message, NULL_ARGS, null);
            } else {
                log.warn(message);
            }
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (isWarnEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.WARN_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.warn(format, arg);
            }
        }

    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.WARN_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.warn(format, arg1, arg2);
            }
        }

    }

    @Override
    public void warn(String message, Throwable t) {
        if (isWarnEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.WARN_INT,
                        message, NULL_ARGS, t);
            } else {
                log.warn(message, t);
            }
        }
    }

    @Override
    public void warn(String format, Object... args) {
        if (isWarnEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.WARN_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.warn(format, args);
            }
        }
    }
    /**End*************************Warn*****************************************/

    /**
     * BEGIN*************************Platform
     *****************************************/
    @Override
    public boolean isPlatformEnabled() {
        return isInfoEnabled();
    }

    @Override
    public void platform(String message) {
        if (isPlatformEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, null);
            } else {
                log.info(message);
            }
        }
    }

    @Override
    public void platform(String format, Object arg) {
        if (isPlatformEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg);
            }
        }

    }

    @Override
    public void platform(String format, Object arg1, Object arg2) {
        if (isPlatformEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg1, arg2);
            }
        }

    }

    @Override
    public void platform(String message, Throwable t) {
        if (isPlatformEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, t);
            } else {
                log.info(message, t);
            }
        }
    }

    @Override
    public void platform(String format, Object... args) {
        if (isPlatformEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, args);
            }
        }
    }
    /**End*************************Platform*****************************************/

    /**
     * BEGIN*************************Profile
     *****************************************/
    @Override
    public boolean isProfileEnabled() {
        return isInfoEnabled();
    }

    @Override
    public void profile(String message) {
        if (isProfileEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, null);
            } else {
                log.info(message);
            }
        }
    }

    @Override
    public void profile(String format, Object arg) {
        if (isProfileEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg);
            }
        }

    }

    @Override
    public void profile(String format, Object arg1, Object arg2) {
        if (isProfileEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg1, arg2);
            }
        }

    }

    @Override
    public void profile(String message, Throwable t) {
        if (isProfileEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, t);
            } else {
                log.info(message, t);
            }
        }
    }

    @Override
    public void profile(String format, Object... args) {
        if (isProfileEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, args);
            }
        }
    }
    /**End*************************Profile*****************************************/

    /**
     * BEGIN*************************Sql
     *****************************************/
    @Override
    public boolean isSqlEnabled() {
        return isInfoEnabled();
    }

    @Override
    public void sql(String message) {
        if (isSqlEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, null);
            } else {
                log.info(message);
            }
        }
    }

    @Override
    public void sql(String format, Object arg) {
        if (isSqlEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg);
            }
        }

    }

    @Override
    public void sql(String format, Object arg1, Object arg2) {
        if (isSqlEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, arg1, arg2);
            }
        }

    }

    @Override
    public void sql(String message, Throwable t) {
        if (isSqlEnabled()) {
            if (isLocationAwareLogger) {
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        message, NULL_ARGS, t);
            } else {
                log.info(message, t);
            }
        }
    }

    @Override
    public void sql(String format, Object... args) {
        if (isSqlEnabled()) {
            if (isLocationAwareLogger) {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
                ((LocationAwareLogger) log).log(null, callerFQCN, LocationAwareLogger.INFO_INT,
                        ft.getMessage(), NULL_ARGS, ft.getThrowable());
            } else {
                log.info(format, args);
            }
        }
    }
}
