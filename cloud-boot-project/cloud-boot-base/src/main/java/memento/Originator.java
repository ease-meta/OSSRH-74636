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
package memento;

/**
 * @author shadow
 * @Date 2016年8月8日下午8:01:48
 * @Fun
 **/
public class Originator {

    private DataState state;

    public Originator() {
        // TODO Auto-generated constructor stub
    }

    public Originator(DataState state) {
        this.state = state;
    }

    public Memento getMemento() {
        return new Memento(this);
    }

    public void setMemento(Memento m) {
        /**
         * getMemento()创建的对象，保存在某个容器里，当需要恢复时，将其传入当前方法，再使用getState()得出
         */
        this.state = m.getState();
    }

    public DataState getState() {
        return this.state;
    }

    public void setState(DataState state) {
        this.state = state;
    }

}
