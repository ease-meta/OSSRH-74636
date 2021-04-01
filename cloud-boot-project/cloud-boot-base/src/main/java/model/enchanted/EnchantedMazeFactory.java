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
package model.enchanted;

import absfactory.MazeFactory;
import model.base.Door;
import model.base.Room;

public class EnchantedMazeFactory extends MazeFactory {

    public EnchantedMazeFactory() {
        // TODO Auto-generated constructor stub
    }

    public Room MakeRoom(int number) {
        return new EnchantedRoom(number, CastSpell());
    }

    public Door MakeDoor(Room room1, Room room2) {
        return new DoorNeedingSpell(room1, room2);
    }

    protected Spell CastSpell() {
        return new Spell();
    }
}
