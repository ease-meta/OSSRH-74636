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
package observer;

/**
 * @author shadow
 * @Date 2016年8月12日下午7:42:11
 * @Fun 抽象观察者 Observer
 * 观察 更新
 **/
public interface IWatcher {
	/**
	 * 通知接口：
	 * 1.简单通知。
	 * 2.观察者需要目标的变化的数据，那么可以将目标用作参数，见java的Observer和Observable
	 */

	void update();
}
