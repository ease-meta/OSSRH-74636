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
/**
 * @author shadow
 * @Date 2016年8月1日下午8:13:10
 * @Fun 接口适配器：接口中有抽象方法，我们只想实现其中的几个，不想全部实现，所以提供
 * 		一个默认空实现，然后继承它，重写实现我们想实现的方法
 **/
package adapter;

public interface IDraw {
    void drawCircle();

    void drawRectangle();
}
