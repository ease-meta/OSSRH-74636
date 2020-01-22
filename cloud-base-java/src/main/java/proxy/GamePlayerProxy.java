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
 * @Date 2016年8月6日下午9:11:26
 * @Fun  玩家代理<br/>
 * 		  普通的静态代理：客户端不知道被代理对象，由代理对象完成其功能的调用<br/>
 **/
public class GamePlayerProxy implements IGamePlayer {

    //被代理对象
    private IGamePlayer gamePlayer = null;

    //通过构造函数传递要对谁进行代理
    public GamePlayerProxy(String userName) {
        // TODO Auto-generated constructor stub
        this.gamePlayer = new GamePlayer(userName);
    }

    @Override
    public void login(String user, String password) {
        // TODO Auto-generated method stub
        this.gamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        // TODO Auto-generated method stub
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        // TODO Auto-generated method stub
        this.gamePlayer.upgrade();
    }

}
