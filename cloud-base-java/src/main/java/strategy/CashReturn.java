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
 * @Date 2016年8月12日下午8:58:25
 * @Fun  返现策略
 **/
public class CashReturn implements ICashSuper {

    //返现底限金额
    private double moneyCondition;
    //返现金额
    private double returnMoney;

    public CashReturn(double moneyCondition, double returnMoney) {
        // TODO Auto-generated constructor stub
        this.moneyCondition = moneyCondition;
        this.returnMoney = returnMoney;
    }

    //多重返利
    @Override
    public double acceptCash(double money) {
        // TODO Auto-generated method stub
        if (money >= moneyCondition) {
            return money - Math.floor(money / moneyCondition) * returnMoney;
        }
        return 0;
    }

}
