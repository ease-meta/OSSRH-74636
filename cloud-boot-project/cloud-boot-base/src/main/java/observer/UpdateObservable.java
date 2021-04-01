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

import java.util.Observable;
import java.util.Observer;

/**
 * @author shadow
 * @Date 2016年8月12日下午7:29:31
 * @Fun 观察目标
 **/
public class UpdateObservable extends Observable {
    private int data;

    public UpdateObservable(Observer obserer) {
        // TODO Auto-generated constructor stub
        addObserver(obserer);
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        if (data != this.data) {
            this.data = data;
            //标记 改变 只有标记后才能通知到
            setChanged();
            //通知
            notifyObservers();
        }
    }
}
