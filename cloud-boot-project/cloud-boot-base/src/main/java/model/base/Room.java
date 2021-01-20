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
package model.base;

/**
 * @author shadow
 */
public class Room extends MapSite {

	private MapSite[] sides;
	private int roomNumber;

	public Room(int roomNo) {
		// TODO Auto-generated constructor stub
		this.sides = new MapSite[4];
		this.roomNumber = roomNo;
	}

	public void Initialize(int roomNo) {
		this.roomNumber = roomNo;
	}

	public void Enter() {
		System.err.println("Room");
	}

	public MapSite GetSide(Direction direction) {
		if (sides == null) {
			return null;
		}
		return sides[direction.getValue()];
	}

	public void SetSide(Direction direction, MapSite mapSite) {
		this.sides[direction.getValue()] = mapSite;
	}

	public int getRoomNumber() {
		return this.roomNumber;
	}

	@Override
	public Room clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Room cloneRoom = (Room) super.clone();
		clone(cloneRoom);
		return cloneRoom;
	}

	private void clone(Room room) {
		room.sides = new MapSite[4];
		System.arraycopy(room.sides, 0, this.sides, 0, room.sides.length);
	}
}
