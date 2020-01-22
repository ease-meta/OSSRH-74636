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
package strategy;

/**
 * @author shadow
 * @Date 2016年8月12日下午8:48:34
 * @Fun  策略模式：针对同一命令(或行为)，不同的策略做不同的动作。
 * 
 * 		商品促销
 * 		本接口为：收取现金的类
 **/
public interface ICashSuper {
    double acceptCash(double money);
}
