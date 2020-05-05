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

/**
 * 指挥者：指导 如何 建造
 *
 * @author shadow E-mail:zyydqpi@163.com
 * @version 1.0
 * @Date 2016年8月20日下午7:29:25
 * @since
 **/
public class Director {
    private MazeBuilder builder;

    public Director(MazeBuilder builder) {
        // TODO Auto-generated constructor stub
        this.builder = builder;
    }

    /**
     * 建造方法：定义了建造的流程
     */
    public void construct() {
        builder.Buildmaze();
        builder.BuildRoom(1);
        builder.BuildRoom(2);
        builder.BuildDoor(1, 2);
    }
}
