package com.open.cloud.boot.autoconfigure.oss;

/**
 * The type Trans dir result.
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

		public TransDirResultBuilder srcFileName(String srcFileName) {
			this.srcFileName = srcFileName;
			return this;
		}

		private String destFileName;

		public TransDirResultBuilder destFileName(String destFileName) {
			this.destFileName = destFileName;
			return this;
		}

		public TransDirResult build() {
			return new TransDirResult(this.srcFileName, this.destFileName);
		}

		@Override
		public String toString() {
			return "TransDirResult.TransDirResultBuilder(srcFileName=" + this.srcFileName + ", destFileName=" + this.destFileName + ")";
		}
	}

}

