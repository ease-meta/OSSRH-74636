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
package templatemethod;

/**
 * @author shadow
 * @Date 2016年8月12日下午9:29:03
 * @Fun  业务流程模板，提供基本框架
 **/
public abstract class BaseTemplate {
    public abstract void part1();

    public abstract void part2();

    public abstract void part3();

    //为了严格实验结果，使用final不可被覆写
    public final void useTemplateMethod() {
        part1();
        part2();
        part3();
    }
}
