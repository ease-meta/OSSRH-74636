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
package com.open.cloud.core.moc;

import com.open.cloud.core.moc.logger.DebugLog;
import com.open.cloud.core.moc.logger.ErrorLog;
import com.open.cloud.core.moc.logger.InfoLog;
import com.open.cloud.core.moc.logger.MethodLog;
import com.open.cloud.core.moc.logger.ParmLog;
import com.open.cloud.core.moc.logger.PlatformLog;
import com.open.cloud.core.moc.logger.ProfileLog;
import com.open.cloud.core.moc.logger.SqlLog;
import com.open.cloud.core.moc.logger.TraceLog;
import com.open.cloud.core.moc.logger.WarnLog;

/**
 * The six logging levels used by Log are (in order):
 * 1. TRACE (the least serious)
 * 2. DEBUG
 * 3. INFO
 * 4. WARN
 * 5. ERROR
 * 6. FATAL (the most serious)
 *  @author Leijian
 *  @date 2020/11/15
 *
 */
public abstract class BizLog implements TraceLog, DebugLog, InfoLog, WarnLog, ErrorLog, ParmLog,
		MethodLog, SqlLog, ProfileLog, PlatformLog {

	/**
	 * 判断可变参数是否以 Throwable 结尾
	 */
	protected boolean endsWithThrowable(Object... args) {
		return args != null && args.length != 0 && args[args.length - 1] instanceof Throwable;
	}

	/**
	 * parse(...) 方法必须与 if (endsWithThrowable(...)) 配合使用，
	 * 确保可变参数 Object... args 中的最后一个元素为 Throwable 类型
	 */
	protected LogInfo parse(String format, Object... args) {
		LogInfo li = new LogInfo();

		// 最后一个参数已确定为 Throwable
		li.throwable = (Throwable) args[args.length - 1];

		// 其它参数与 format 一起格式化成 message
		if (args.length > 1) {
			Object[] temp = new Object[args.length - 1];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = args[i];
			}

			li.message = String.format(format, temp);
		} else {
			li.message = format;
		}

		return li;
	}

    /*@Override
    public void debug(String format, Object... args) {
    	if (isDebugEnabled()) {
    		if (endsWithThrowable(args)) {
    			LogInfo li = parse(format, args);
    			debug(li.message, li.throwable);
    		} else {
    			debug(String.format(format, args));
    		}
    	}
    }*/

}
