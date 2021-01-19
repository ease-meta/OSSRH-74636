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

	public TransResult(int totalFileNumber, int succNumber, int failNumber, int errNo, String errStr, String tlqcause, TransDirResult[] transDirResults) {
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

		public TransResultBuilder totalFileNumber(int totalFileNumber) {
			this.totalFileNumber = totalFileNumber;
			return this;
		}

		private int succNumber;

		public TransResultBuilder succNumber(int succNumber) {
			this.succNumber = succNumber;
			return this;
		}

		private int failNumber;

		public TransResultBuilder failNumber(int failNumber) {
			this.failNumber = failNumber;
			return this;
		}

		private int errNo;

		public TransResultBuilder errNo(int errNo) {
			this.errNo = errNo;
			return this;
		}

		private String errStr;

		public TransResultBuilder errStr(String errStr) {
			this.errStr = errStr;
			return this;
		}

		private String tlqcause;

		public TransResultBuilder tlqcause(String tlqcause) {
			this.tlqcause = tlqcause;
			return this;
		}

		private TransDirResult[] transDirResults;

		public TransResultBuilder tlqcause(TransDirResult[] transDirResults) {
			this.transDirResults = transDirResults;
			return this;
		}

		public TransResult build() {
			return new TransResult(this.totalFileNumber, this.succNumber, this.failNumber, this.errNo, this.errStr, this.tlqcause, this.transDirResults);
		}


	}
}
