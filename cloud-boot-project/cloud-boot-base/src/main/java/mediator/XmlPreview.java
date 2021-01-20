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
package mediator;

/**
 * @author shadow
 * @Date 2016年8月8日下午7:24:48
 * @Fun 本类描述Android布局里的预览界面
 * 在预览界面中拖曳组件，代码会变化
 **/
public class XmlPreview implements IWork {

	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println("预览里的组件变化了");
	}

}
