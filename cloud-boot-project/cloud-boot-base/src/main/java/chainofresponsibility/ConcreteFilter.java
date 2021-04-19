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
package chainofresponsibility;

/**
 * @author shadow
 * @Date 2016年8月7日下午8:00:34
 * @Fun 具体责任
 **/
public class ConcreteFilter extends IFilter {

    private String name;

    public ConcreteFilter(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    @Override
    public void handleFilter() {
        // TODO Auto-generated method stub
        /**
         * 自己先处理，如有后继者，再由它处理一次。
         */
        System.out.println(name + " 处理了请求");

        if (getSuccessor() != null) {
            getSuccessor().handleFilter();
        }
    }

    @Override
    public void handleFilter2() {
        // TODO Auto-generated method stub
        /**
         * 有后继者就后继者处理，否则自己处理
         */

        if (getSuccessor() != null) {
            getSuccessor().handleFilter2();
        } else {
            System.out.println(name + " 处理了请求");
        }
    }

}
