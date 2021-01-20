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
 * The type Trans result.
 * @author Leijian
 * @date 2021 /1/19 9:36
 * @since
 */
public class TransResult {
	private final int totalFileNumber;
	private final int succNumber;
	private final int failNumber;
	private final int errNo;
	private final String errStr;
	private final String tlqcause;

	private final TransDirResult[] transDirResults;

	public TransResult(int totalFileNumber, int succNumber, int failNumber, int errNo,
					   String errStr, String tlqcause, TransDirResult[] transDirResults) {
		this.totalFileNumber = totalFileNumber;
		this.succNumber = succNumber;
		this.failNumber = failNumber;
		this.errNo = errNo;
		this.errStr = errStr;
		this.tlqcause = tlqcause;
		this.transDirResults = transDirResults;
	}

	public static class TransResultBuilder {
		private int totalFileNumber;
		private int succNumber;
		private int failNumber;
		private int errNo;
		private String errStr;
		private String tlqcause;
		private TransDirResult[] transDirResults;

		public TransResultBuilder totalFileNumber(int totalFileNumber) {
			this.totalFileNumber = totalFileNumber;
			return this;
		}

		public TransResultBuilder succNumber(int succNumber) {
			this.succNumber = succNumber;
			return this;
		}

		public TransResultBuilder failNumber(int failNumber) {
			this.failNumber = failNumber;
			return this;
		}

		public TransResultBuilder errNo(int errNo) {
			this.errNo = errNo;
			return this;
		}

		public TransResultBuilder errStr(String errStr) {
			this.errStr = errStr;
			return this;
		}

		public TransResultBuilder tlqcause(String tlqcause) {
			this.tlqcause = tlqcause;
			return this;
		}

		public TransResultBuilder tlqcause(TransDirResult[] transDirResults) {
			this.transDirResults = transDirResults;
			return this;
		}

		public TransResult build() {
			return new TransResult(this.totalFileNumber, this.succNumber, this.failNumber,
					this.errNo, this.errStr, this.tlqcause, this.transDirResults);
		}

	}
}
