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
import model.base.Direction;
import model.base.Door;
import model.base.Maze;
import model.base.Room;

/**
 * @author shadow
 * @Date 2016年7月29日19:48:45
 * @Fun Standard Maze Builder is created by Builber.
 */
public class StandardMazeBuilder extends MazeBuilder {

	public StandardMazeBuilder(MazeFactory factory) {
		// TODO Auto-generated constructor stub
		super(factory);
	}

	@Override
	public void Buildmaze() {
		// TODO Auto-generated method stub
		this.currentMaze = factory.MakeMaze();
	}

	@Override
	public void BuildRoom(int number) {
		// TODO Auto-generated method stub
		Room room = factory.MakeRoom(number);
		this.currentMaze.AddRoom(room);

		room.SetSide(Direction.North, factory.MakeWall());
		room.SetSide(Direction.South, factory.MakeWall());
		room.SetSide(Direction.East, factory.MakeWall());
		room.SetSide(Direction.West, factory.MakeWall());
	}

	@Override
	public void BuildDoor(int roomFrom, int roomTo) {
		// TODO Auto-generated method stub
		Room room1 = this.currentMaze.getRoom(roomFrom);
		Room room2 = this.currentMaze.getRoom(roomTo);
		Door door = factory.MakeDoor(room1, room2);

		room1.SetSide(CommonWall(room1, room2), door);
		room2.SetSide(CommonWall(room2, room1), door);

	}

	@Override
	public Maze GetMaze() {
		// TODO Auto-generated method stub
		return currentMaze;
	}

	//
	private Direction CommonWall(Room room1, Room room2) {
		Direction wallDir = null;
		int index = (room1.getRoomNumber() + room2.getRoomNumber()) % 4;
		//System.err.println("Index : " + index);
		if (TempWall) {
			TempWall = true;
			wallDir = Direction.getDirection(index);
		} else {
			index = index % 2 == 0 ? index + 1 : index - 1;
			wallDir = Direction.getDirection(index);
			TempWall = false;
		}
		return wallDir;
	}

	private boolean TempWall = false;

	@Override
	public MazeFactory getMazeFactory() {
		// TODO Auto-generated method stub
		return factory;
	}
}
