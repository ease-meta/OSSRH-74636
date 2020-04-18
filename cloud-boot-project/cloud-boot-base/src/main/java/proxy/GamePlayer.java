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
package proxy;

/**
 * @author shadow
 * @Date 2016年8月6日下午9:07:59
 * @Fun 具体玩家
 **/
public class GamePlayer implements IGamePlayer {

	private String name = "";

	public GamePlayer(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void login(String user, String password) {
		// TODO Auto-generated method stub
		System.out.println("登录名为" + user + "的角色 " + this.name + " 登录成功！");
	}

	@Override
	public void killBoss() {
		// TODO Auto-generated method stub
		System.out.println(this.name + " 在打怪！");
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		System.out.println(this.name + " 又升了一级！");
	}

}
