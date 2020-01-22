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
package iterator;

import java.util.Arrays;

/**
 * @author shadow
 * @Date 2016年8月7日下午9:15:55
 * @Fun  实现的存储数据集合类
 **/
public class MyCollection<T> implements ICollection<T> {

    private T[] arys;
    private int index    = -1;
    private int capacity = 6;

    @SuppressWarnings("unchecked")
    public MyCollection() {
        // TODO Auto-generated constructor stub
        this.arys = (T[]) new Object[capacity];
    }

    @Override
    public IIterator<T> iterator() {
        // TODO Auto-generated method stub
        return new MyIterator<>(this);
    }

    @Override
    public void add(T t) {
        // TODO Auto-generated method stub
        index++;
        if (index == capacity) {
            capacity += 6;
            this.arys = Arrays.copyOf(arys, capacity);
        }
        this.arys[index] = t;
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        if (index < size()) {
            return this.arys[index];
        }

        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return index + 1;
    }

}
