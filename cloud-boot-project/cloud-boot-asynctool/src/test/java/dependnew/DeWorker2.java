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
package dependnew;

import io.github.meta.ease.async.callback.ICallback;
import io.github.meta.ease.async.callback.IWorker;
import io.github.meta.ease.async.worker.WorkResult;
import io.github.meta.ease.async.wrapper.WorkerWrapper;

import java.util.Map;

/**
 * @author wuweifeng wrote on 2019-11-20.
 */
public class DeWorker2 implements IWorker<User, String>, ICallback<User, String> {

    @Override
    public String action(User object, Map<String, WorkerWrapper> allWrappers) {
        System.out.println("-----------------");
        System.out.println("par1的执行结果是： " + allWrappers.get("second").getWorkResult());
        System.out.println("取par1的结果作为自己的入参，并将par1的结果加上一些东西");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user1 = (User) allWrappers.get("second").getWorkResult().getResult();
        return user1.getName() + " worker2 add";
    }

    @Override
    public String defaultValue() {
        return "default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, User param, WorkResult<String> workResult) {
        System.out.println("worker2 的结果是：" + workResult.getResult());
    }

}
