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

import java.util.Date;

/**
 * @author shadow
 * @Date 2016年8月6日下午9:14:10
 * @Fun 客户端 对被代理对象不可见<br/>
 * 代理对象，增强了被代理对象的功能<br/>
 **/
public class GamePlayerProxy2 implements IGamePlayer {

    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy2(String userName) {
        // TODO Auto-generated constructor stub
        this.gamePlayer = new GamePlayer(userName);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void login(String user, String password) {
        // TODO Auto-generated method stub
        System.out.println("登录时间是：" + new Date().toLocaleString());
        this.gamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        // TODO Auto-generated method stub
        this.gamePlayer.killBoss();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void upgrade() {
        // TODO Auto-generated method stub
        this.gamePlayer.upgrade();
        System.out.println("升级时间是：" + new Date().toLocaleString());
    }

}
