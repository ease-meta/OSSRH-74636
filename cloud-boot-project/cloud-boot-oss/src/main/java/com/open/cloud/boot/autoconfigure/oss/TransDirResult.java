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
package com.open.cloud.boot.autoconfigure.oss;

/**
 * The type Trans dir result.
 *
 * @author Leijian
 * @date 2021 /1/19 9:43
 * @since
 */
public class TransDirResult {

    private final String srcFileName;

    private final String destFileName;

    TransDirResult(String srcFileName, String destFileName) {
        this.srcFileName = srcFileName;
        this.destFileName = destFileName;
    }

    public static TransDirResultBuilder builder() {
        return new TransDirResultBuilder();
    }

    public static class TransDirResultBuilder {
        private String srcFileName;
        private String destFileName;

        public TransDirResultBuilder srcFileName(String srcFileName) {
            this.srcFileName = srcFileName;
            return this;
        }

        public TransDirResultBuilder destFileName(String destFileName) {
            this.destFileName = destFileName;
            return this;
        }

        public TransDirResult build() {
            return new TransDirResult(this.srcFileName, this.destFileName);
        }

        @Override
        public String toString() {
            return "TransDirResult.TransDirResultBuilder(srcFileName=" + this.srcFileName
                    + ", destFileName=" + this.destFileName + ")";
        }
    }

}
