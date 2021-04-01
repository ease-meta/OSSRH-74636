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

import model.base.Door;
import model.base.Maze;
import model.base.Room;
import model.base.Wall;
import model.enchanted.DoorNeedingSpell;
import model.enchanted.EnchantedRoom;
import model.enchanted.Spell;

/**
 * @author shadow
 * @Date 2016年7月29日21:05:07
 * @Fun Enchanted Maze Game created by Factory Method.
 */
public class EnchantedMazeGame extends MazeGame {

    public static void main(String[] args) {
        MazeGame game = new EnchantedMazeGame();
        Maze aMaze = game.CreateMaze();
        Room room = aMaze.getRoom(1);
        System.err.println("Rooms : " + aMaze.getRoomCount());
        room.Enter();
    }

    @Override
    public Maze MakeMaze() {
        // TODO Auto-generated method stub
        return new Maze();
    }

    @Override
    public Room MakeRoom(int number) {
        // TODO Auto-generated method stub
        return new EnchantedRoom(number, new Spell());
    }

    @Override
    public Door MakeDoor(Room r1, Room r2) {
        // TODO Auto-generated method stub
        return new DoorNeedingSpell(r1, r2);
    }

    @Override
    public Wall MakeWall() {
        // TODO Auto-generated method stub
        return new Wall();
    }

}
