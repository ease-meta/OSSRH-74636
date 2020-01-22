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
package visitor;

/**
 * @author shadow
 * @Date 2016年8月13日下午8:15:25
 * @Fun  名称访问
 **/
public class StringVisitor implements Visitor {

    String s;

    public String toString() {
        return s;
    }

    @Override
    public void visit(Gladiolus g) {
        // TODO Auto-generated method stub
        s = "That's Gladiolus";
    }

    @Override
    public void visit(Chrysanthemum c) {
        // TODO Auto-generated method stub
        s = "That's Chrysanthemum";
    }

}
