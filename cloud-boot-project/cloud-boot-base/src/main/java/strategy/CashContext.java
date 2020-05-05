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
 * @Date 2016年8月12日下午9:02:11
 * @Fun 根据传递的策略类，执行相应的行为。
 **/
public class CashContext {
    private ICashSuper casher;

    public CashContext() {
        // TODO Auto-generated constructor stub
    }

    public CashContext(ICashSuper casher) {
        this.casher = casher;
    }

    public void setCasher(ICashSuper casher) {
        this.casher = casher;
    }

    //根据具体的策略对象，调用它的算法行为
    public double acceptCash(double money) {
        return this.casher.acceptCash(money);
    }
}
