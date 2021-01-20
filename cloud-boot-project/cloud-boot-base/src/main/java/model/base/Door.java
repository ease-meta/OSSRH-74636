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
public class Door extends MapSite {

	private Room room1;
	private Room room2;
	private boolean isOpen;

	public Door(Room room1, Room room2) {
		// TODO Auto-generated constructor stub
		this.room1 = room1;
		this.room2 = room2;
		this.isOpen = true;
	}

	public void Enter() {

	}

	public Room OtherSideFrom(Room room) {
		if (room == null) {
			throw new RuntimeException("The room is null.");
		}
		if (room1.equals(room)) {
			return room2;
		} else {
			return room1;
		}
	}

	public boolean getIsOpen() {
		return this.isOpen;
	}

	protected void close() {
		this.isOpen = false;
	}

	protected void open() {
		this.isOpen = true;
	}

	public void Initialize(Room room1, Room room2) {
		this.room1 = room1;
		this.room2 = room2;
	}

	@Override
	public Door clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Door) super.clone();
	}
}
