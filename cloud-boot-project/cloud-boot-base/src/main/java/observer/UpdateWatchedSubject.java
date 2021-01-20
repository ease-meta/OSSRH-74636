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

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadow
 * @Date 2016年8月12日下午7:50:13
 * @Fun 具体被观察者目标
 **/
public class UpdateWatchedSubject implements IWathedSubject {

	private List<IWatcher> list;

	public UpdateWatchedSubject() {
		// TODO Auto-generated constructor stub
		this.list = new ArrayList<>();
	}

	@Override
	public void add(IWatcher watcher) {
		// TODO Auto-generated method stub
		this.list.add(watcher);
	}

	@Override
	public void remove(IWatcher watcher) {
		// TODO Auto-generated method stub
		this.list.add(watcher);
	}

	@Override
	public void notifyWatchers() {
		// TODO Auto-generated method stub
		for (IWatcher watcher : list) {
			watcher.update();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("目标更新中....");
		notifyWatchers();
	}

}
