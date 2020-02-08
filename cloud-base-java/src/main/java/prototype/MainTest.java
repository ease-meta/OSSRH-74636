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

import java.util.Iterator;

import model.base.Door;
import model.base.Maze;
import model.base.Room;
import model.base.Wall;

/**
 * @fun 原型模式测试<br/>
 * 		注意事项：1.使用原型模式复制对象不会调用类的构造方法。<br/>
 * 			   2.深拷贝和浅拷贝。<br/>
 * @author shadow E-mail:zyydqpi@163.com
 * @Date 2016年8月26日下午7:13:54
 * @version 1.0
 * @since 
 **/
public class MainTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Maze maze = new Maze();
        Room room = new Room(1);
        Wall wall = new Wall();
        Door door = new Door(room, room);
        MazeGame game = new MazePrototypeFactory(maze, wall, room, door);
        //创建原型对象
        Maze maze2 = game.CreateMaze();

        System.err.println(maze.getRoomCount());
        System.err.println(maze2.getRoomCount());

        Iterator<Room> iterator = maze2.iterator();

        while (iterator.hasNext()) {
            System.err.println("RoomNumber : " + iterator.next().getRoomNumber());
        }
    }
}
/**
 * 推荐博客：http://blog.csdn.net/jason0539/article/details/23158081
 */
