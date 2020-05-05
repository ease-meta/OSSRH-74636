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
 * @Date 2016年7月29日19:49:01
 * @fun Builder
 */
public abstract class MazeBuilder {
    public abstract void Buildmaze();

    public abstract void BuildRoom(int number);

    public abstract void BuildDoor(int roomFrom, int roomTo);

    public abstract Maze GetMaze();

    public abstract MazeFactory getMazeFactory();

    protected MazeBuilder(MazeFactory factory) {
        this.factory = factory;
    }

    protected MazeFactory factory;
    protected Maze        currentMaze;
}
