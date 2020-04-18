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
package factorymethod;

import model.base.Maze;

/**
 * @author shadow E-mail:zyydqpi@163.com
 * @version 1.0
 * @fun 工厂方法模式 创建型设计模式
 * @Date 2016年8月23日下午7:10:22
 * @since
 **/
public class MainTest {
	public static void main(String[] args) {
		MazeGame game = new BombedMazeGame();
		Maze aMaze = game.CreateMaze();
		System.out.println("" + aMaze.getRoomCount());
		aMaze.getRoom(1).Enter();

		game = new EnchantedMazeGame();
		aMaze = game.CreateMaze();
		System.out.println("" + aMaze.getRoomCount());
		aMaze.getRoom(1).Enter();

	}
}

/**
 * 推荐博客：http://blog.csdn.net/hguisu/article/details/7505909
 * <p>
 * 适用情况：
 * 1.当客户程序不需要知道要使用对象的创建过程。
 * 2.当客户程序使用的对象存在变动的可能，或者根本就不知道使用哪一个具体的对象。
 */
