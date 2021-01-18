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
package com.open.cloud.core.log.logger;

/**
 * @author Leijian
 * @date 2020/11/15
 */
public interface TraceLog {

	public boolean isTraceEnabled();

	/**
	 * Log a message at the TRACE level.
	 *
	 * @param msg the message string to be logged
	 * @since 1.4
	 */
	public void trace(String msg);

	/**
	 * Log a message at the TRACE level according to the specified format
	 * and argument.
	 * <p/>
	 * <p>This form avoids superfluous object creation when the logger
	 * is disabled for the TRACE level. </p>
	 *
	 * @param format the format string
	 * @param arg    the argument
	 * @since 1.4
	 */
	public void trace(String format, Object arg);

	/**
	 * Log a message at the TRACE level according to the specified format
	 * and arguments.
	 * <p/>
	 * <p>This form avoids superfluous object creation when the logger
	 * is disabled for the TRACE level. </p>
	 *
	 * @param format the format string
	 * @param arg1   the first argument
	 * @param arg2   the second argument
	 * @since 1.4
	 */
	public void trace(String format, Object arg1, Object arg2);

	/**
	 * Log a message at the TRACE level according to the specified format
	 * and arguments.
	 * <p/>
	 * <p>This form avoids superfluous string concatenation when the logger
	 * is disabled for the TRACE level. However, this variant incurs the hidden
	 * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
	 * even if this logger is disabled for TRACE. The variants taking {@link #trace(String, Object) one} and
	 * {@link #trace(String, Object, Object) two} arguments exist solely in order to avoid this hidden cost.</p>
	 *
	 * @param format    the format string
	 * @param arguments a list of 3 or more arguments
	 * @since 1.4
	 */
	public void trace(String format, Object... arguments);

	/**
	 * Log an exception (throwable) at the TRACE level with an
	 * accompanying message.
	 *
	 * @param msg the message accompanying the exception
	 * @param t   the exception (throwable) to log
	 * @since 1.4
	 */
	public void trace(String msg, Throwable t);
}
