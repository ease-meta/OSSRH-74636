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

import java.io.Serializable;

/**
 * @author shadow
 * @Date 2016年8月8日下午8:00:53
 * @Fun 一个保存另一个对象内部状态拷贝的对象，这样以后就可以将该对象恢复到原先保存的状态。
 **/
public class Memento implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8121679932355414767L;
    //
    //	private int number;
    //	private File file = null;

    private DataState         state;

    public Memento(Originator o) {
        this.state = o.getState();
    }

    public DataState getState() {
        return this.state;
    }

    public void setState(DataState state) {
        this.state = state;
    }
}
