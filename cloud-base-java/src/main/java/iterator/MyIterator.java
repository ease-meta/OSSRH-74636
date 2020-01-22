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

/**
 * @author shadow
 * @Date 2016年8月7日下午9:20:52
 * @Fun  迭代器实现类
 **/
public class MyIterator<T> implements IIterator<T> {

    private ICollection<T> collection;
    private int            index = 0;

    public MyIterator(ICollection<T> collection) {
        // TODO Auto-generated constructor stub
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return index < this.collection.size();
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return index > 0;
    }

    @Override
    public T next() {
        // TODO Auto-generated method stub	
        return this.collection.get(index++);
    }

    @Override
    public T previous() {
        // TODO Auto-generated method stub
        return this.collection.get(index--);
    }

}
