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
package com.open.cloud.sofa.provider;


import com.open.cloud.core.log.Log;

/**
 * <p></p>
 * <p>
 *
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ZookeeperBoltServerMain {

	private final static Log galxyLog = Log.getLog(ZookeeperBoltServerMain.class);

	//private final static Log log = GlobalLogFactory.get().getLog(ZookeeperBoltServerMain.class);
	//private final static Logger galxyLog = LoggerFactory.getLogger(ZookeeperBoltServerMain.class);
	public static void main(String[] args) {

		chkCycle("1", "2", "3");
	}

	public static boolean chkCycle(String sJiaoyirq, String eRqjsfshi, String eSjinzlei) {


		galxyLog.info(">>>>>>>>>>>>Begin>>>>>>>>>>>>");
		galxyLog.info("sJiaoyirq[%s],eRqjsfshi[%s]，eSjinzlei[%s]", sJiaoyirq, eRqjsfshi, eSjinzlei);
		String sCalJtriq = "";// 计算计提日期值
		if (true) {
			galxyLog.info("boolean [true]");
			galxyLog.info("<<<<<<<<<<<<End<<<<<<<<<<<<");
			return true;
		}
		galxyLog.info("boolean [false]");
		galxyLog.info("<<<<<<<<<<<<End<<<<<<<<<<<<");

		return false;
	}

}
