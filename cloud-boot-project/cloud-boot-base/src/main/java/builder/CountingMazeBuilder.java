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
package builder;

import absfactory.MazeFactory;
import model.base.Maze;

/**
 * @author shadow
 * @Date 2016年7月29日21:07:25
 * @Fun A counting Maze Builder is created by Builder.
 */
public class CountingMazeBuilder extends MazeBuilder {

	public CountingMazeBuilder(MazeFactory factory) {
		// TODO Auto-generated constructor stub
		super(factory);
		rooms = 0;
		doors = 0;
	}

	@Override
	public void Buildmaze() {
		// TODO Auto-generated method stub
		this.currentMaze = factory.MakeMaze();
	}

	@Override
	public void BuildRoom(int number) {
		// TODO Auto-generated method stub
		this.rooms++;
	}

	@Override
	public void BuildDoor(int roomFrom, int roomTo) {
		// TODO Auto-generated method stub
		this.doors++;
	}

	public void GetCounts(Integer[] rooms) {
		System.err.println("Rooms : " + this.rooms + "\n Doors : " + this.doors);
		rooms[0] = new Integer(this.rooms);
		rooms[1] = new Integer(this.doors);
	}

	@Override
	public Maze GetMaze() {
		// TODO Auto-generated method stub
		return this.currentMaze;
	}

	private int doors;
	private int rooms;

	@Override
	public MazeFactory getMazeFactory() {
		// TODO Auto-generated method stub
		return factory;
	}
}
