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
package observer;

/**
 * @author shadow
 * @Date 2016年8月12日下午7:44:53
 * @Fun 抽象目标 Subject<br/>
 * 提供注册和删除观察者对象的接口，以及通知观察者进行观察的接口及目标 自身被观察的业务的接口。<br/>
 **/
public interface IWathedSubject {

    void add(IWatcher watcher);

    void remove(IWatcher watcher);

    void notifyWatchers();

    //被观察业务变化的接口
    void update();
}
