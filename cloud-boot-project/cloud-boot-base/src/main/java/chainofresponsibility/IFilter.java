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
package chainofresponsibility;

/**
 * @author shadow
 * @Date 2016年8月7日下午7:58:16
 * @Fun 抽象责任
 **/
public abstract class IFilter {
	private IFilter successor;

	//获取下一级处理单元
	public IFilter getSuccessor() {
		return successor;
	}

	//设置下一级处理单元
	public void setSuccessor(IFilter successor) {
		this.successor = successor;
	}

	/**
	 * 处理事件
	 */
	public abstract void handleFilter();

	public abstract void handleFilter2();
}
