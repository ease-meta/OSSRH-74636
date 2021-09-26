package com.open.cloud.logging.logger;

/**
 * @author Leijian
 * @date 2020/11/16
 */
public interface ProfileLog {

    /**
     * Is the logger instance enabled for the PROFILE level?
     *
     * @return True if this Logger is enabled for the PROFILE level,
     * false otherwise.
     */
    public boolean isProfileEnabled();

    /**
     * Log a message at the PROFILE level.
     *
     * @param msg the message string to be logged
     */
    public void profile(String msg);

    /**
     * Log a message at the PROFILE level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the PROFILE level. </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void profile(String format, Object arg);

    /**
     * Log a message at the PROFILE level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the PROFILE level. </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void profile(String format, Object arg1, Object arg2);

    /**
     * Log a message at the PROFILE level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the PROFILE level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for PROFILE. The variants taking
     * {@link #profile(String, Object) one} and {@link #profile(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    public void profile(String format, Object... arguments);

    /**
     * Log an exception (throwable) at the PROFILE level with an
     * accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void profile(String msg, Throwable t);
}
