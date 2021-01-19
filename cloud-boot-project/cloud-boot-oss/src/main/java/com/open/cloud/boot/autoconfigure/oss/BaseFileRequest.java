package com.open.cloud.boot.autoconfigure.oss;

import java.io.File;

/**
 * @author Leijian
 * @date 2021/1/19 20:52
 * @since
 */
public class BaseFileRequest {

	private String objectName;

	private String bucketName;

	private File localFile;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}

	public File getLocalFile() {
		return localFile;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
