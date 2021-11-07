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
package io.github.meta.ease.flow.engine.base;

import io.github.meta.ease.core.exception.ResultCode;

/**
 * 执行结果
 */
public class WorkResult<R> {
    /**
     * 执行的结果
     */
    private R result;
    /**
     * 结果状态
     */
    private ResultCode resultCode;
    private Exception ex;

    public WorkResult(R result, ResultCode resultCode) {
        this(result, resultCode, null);
    }

    public WorkResult(R result, ResultCode resultCode, Exception ex) {
        this.result = result;
        this.resultCode = resultCode;
        this.ex = ex;
    }

    public static <R> WorkResult<R> defaultResult() {
        return new WorkResult<>(null, ResultCode.SUCCESS);
    }

    @Override
    public String toString() {
        return "WorkResult{" + "result=" + result + ", resultState=" + resultCode + ", ex=" + ex
                + '}';
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
