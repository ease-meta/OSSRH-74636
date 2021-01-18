package com.open.cloud.core.log.logger;

/**
 * @author Leijian
 * @date 2020/11/16
 */
public interface SqlLog {
	/**
	 * Is the logger instance enabled for the SQL level?
	 *
	 * @return True if this Logger is enabled for the SQL level,
	 *         false otherwise.
	 */
	public boolean isSqlEnabled();

	/**
	 * Log a message at the SQL level.
	 *
	 * @param msg the message string to be logged
	 */
	public void sql(String msg);

	/**
	 * Log a message at the SQL level according to the specified format
	 * and argument.
	 * <p/>
	 * <p>This form avoids superfluous object creation when the logger
	 * is disabled for the SQL level. </p>
	 *
	 * @param format the format string
	 * @param arg    the argument
	 */
	public void sql(String format, Object arg);

	/**
	 * Log a message at the SQL level according to the specified format
	 * and arguments.
	 * <p/>
	 * <p>This form avoids superfluous object creation when the logger
	 * is disabled for the SQL level. </p>
	 *
	 * @param format the format string
	 * @param arg1   the first argument
	 * @param arg2   the second argument
	 */
	public void sql(String format, Object arg1, Object arg2);

	/**
	 * Log a message at the SQL level according to the specified format
	 * and arguments.
	 * <p/>
	 * <p>This form avoids superfluous string concatenation when the logger
	 * is disabled for the SQL level. However, this variant incurs the hidden
	 * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
	 * even if this logger is disabled for SQL. The variants taking
	 * {@link #sql(String, Object) one} and {@link #sql(String, Object, Object) two}
	 * arguments exist solely in order to avoid this hidden cost.</p>
	 *
	 * @param format    the format string
	 * @param arguments a list of 3 or more arguments
	 */
	public void sql(String format, Object... arguments);

	/**
	 * Log an exception (throwable) at the SQL level with an
	 * accompanying message.
	 *
	 * @param msg the message accompanying the exception
	 * @param t   the exception (throwable) to log
	 */
	public void sql(String msg, Throwable t);
}
