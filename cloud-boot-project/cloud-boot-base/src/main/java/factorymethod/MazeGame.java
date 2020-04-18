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

import model.base.Direction;
import model.base.Door;
import model.base.Maze;
import model.base.Room;
import model.base.Wall;

/**
 * @author shadow
 * @Date 2016年7月29日20:47:40
 * @fun Factory Method
 */
public abstract class MazeGame {
	public Maze CreateMaze() {
		Maze aMaze = MakeMaze();
		Room r1 = MakeRoom(1);
		Room r2 = MakeRoom(2);
		Door theDoor = MakeDoor(r1, r2);

		aMaze.AddRoom(r1);
		aMaze.AddRoom(r2);

		r1.SetSide(Direction.North, MakeWall());
		r1.SetSide(Direction.East, theDoor);
		r1.SetSide(Direction.South, MakeWall());
		r1.SetSide(Direction.West, MakeWall());

		r1.SetSide(Direction.North, MakeWall());
		r1.SetSide(Direction.East, MakeWall());
		r1.SetSide(Direction.South, MakeWall());
		r1.SetSide(Direction.West, theDoor);
		return aMaze;
	}

	public abstract Maze MakeMaze();

	public abstract Room MakeRoom(int number);

	public abstract Wall MakeWall();

	public abstract Door MakeDoor(Room r1, Room r2);

}
