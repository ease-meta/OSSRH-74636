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
package prototype;

import model.base.Door;
import model.base.Maze;
import model.base.Room;
import model.base.Wall;

/**
 * @author shadow
 * @Date 2016年7月30日下午6:52:27
 * @Fun 原型设计模式    创建型设计模式<br/>
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。<br/>
 * Prototype原型模式是一种创建型设计模式，Prototype模式允许一个对象再创建另外一个可定制的对象，根本无需知道任何如何创建的细节<br/>
 * 工作原理：通过将一个原型对象传给那个要发动创建的对象，这个要发动创建的对象通过请求原型对象拷贝它们自己来实施创建。<br/>
 * 在java中体现为clone()对象。<br/>
 **/
public class MazePrototypeFactory extends MazeGame {

	private Maze prototypeMaze;
	private Room prototypeRoom;
	private Wall prototypewall;
	private Door prototypeDoor;

	public MazePrototypeFactory(Maze maze, Wall wall, Room room, Door door) {
		this.prototypeMaze = maze;
		this.prototypewall = wall;
		this.prototypeRoom = room;
		this.prototypeDoor = door;
	}

	@Override
	public Maze MakeMaze() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return this.prototypeMaze.clone();
	}

	@Override
	public Room MakeRoom(int number) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Room room = this.prototypeRoom.clone();
		room.Initialize(number);
		return room;
	}

	@Override
	public Door MakeDoor(Room room1, Room room2) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Door door = this.prototypeDoor.clone();
		door.Initialize(room1, room2);
		return null;
	}

	@Override
	public Wall MakeWall() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return this.prototypewall.clone();
	}

	@Override
	public int GetRoomCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
