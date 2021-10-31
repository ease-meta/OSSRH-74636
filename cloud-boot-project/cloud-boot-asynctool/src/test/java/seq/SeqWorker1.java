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
package seq;

import io.github.meta.ease.async.callback.ICallback;
import io.github.meta.ease.async.callback.IWorker;
import io.github.meta.ease.async.executor.timer.SystemClock;
import io.github.meta.ease.async.worker.WorkResult;
import io.github.meta.ease.async.wrapper.WorkerWrapper;

import java.util.Map;

/**
 * @author wuweifeng wrote on 2019-11-20.
 */
public class SeqWorker1 implements IWorker<String, String>, ICallback<String, String> {

    @Override
    public String action(String object, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + object + " from 0";
    }

    @Override
    public String defaultValue() {
        return "worker1--default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker1 success--" + SystemClock.now() + "----"
                    + workResult.getResult() + "-threadName:"
                    + Thread.currentThread().getName());
        } else {
            System.err.println("callback worker1 failure--" + SystemClock.now() + "----"
                    + workResult.getResult() + "-threadName:"
                    + Thread.currentThread().getName());
        }
    }

}
