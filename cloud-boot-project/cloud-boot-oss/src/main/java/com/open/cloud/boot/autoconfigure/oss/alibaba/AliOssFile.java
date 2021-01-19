package com.open.cloud.boot.autoconfigure.oss.alibaba;

import com.aliyun.oss.OSS;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.open.cloud.boot.autoconfigure.oss.DownloadFileRequest;
import com.open.cloud.boot.autoconfigure.oss.FileBase;
import com.open.cloud.boot.autoconfigure.oss.TransResult;
import com.open.cloud.boot.autoconfigure.oss.UploadFileRequest;
import com.open.cloud.core.log.MiddleLogger;
import com.open.cloud.core.log.MiddleLoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * The type Ali oss file.
 * @author Leijian
 * @date 2021 /1/18 21:43
 * @since
 */
public class AliOssFile extends FileBase {
	private final static MiddleLogger logger = MiddleLoggerFactory.getLogger(AliOssFile.class);
	private final OSS ossClient;

	/**
	 * Instantiates a new Ali oss file.
	 *
	 * @param ossClient the oss client
	 */
	public AliOssFile(OSS ossClient) {
		this.ossClient = ossClient;
	}

	@Override
	public void close() throws IOException {
		ossClient.shutdown();
	}

	@Override
	public TransResult download(DownloadFileRequest downloadFileRequest) {
		Assert.notNull(downloadFileRequest.getBucketName(), "[bucketName] argument is required; it must not be null");
		Assert.notNull(downloadFileRequest.getObjectName(), "[bucketName] argument is required; it must not be null");
		GetObjectRequest getObjectRequest = new GetObjectRequest(downloadFileRequest.getBucketName(), downloadFileRequest.getObjectName()).withProgressListener(new GetObjectProgressListener());
		//下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
		ObjectMetadata metadata = ossClient.getObject(getObjectRequest, downloadFileRequest.getLocalFile());
		TransResult.TransResultBuilder transResultBuilder = new TransResult.TransResultBuilder();
		return transResultBuilder.totalFileNumber(1).succNumber(1).build();
	}

	@Override
	public TransResult recursiveDownloadFolder(DownloadFileRequest downloadFileRequest) {
		//TODO
		return null;
	}

	@Override
	public TransResult upload(UploadFileRequest uploadFileRequest) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(uploadFileRequest.getBucketName(), uploadFileRequest.getObjectName(), uploadFileRequest.getLocalFile()).withProgressListener(new PutObjectProgressListener());
		ossClient.putObject(putObjectRequest);
		TransResult.TransResultBuilder transResultBuilder = new TransResult.TransResultBuilder();
		return transResultBuilder.totalFileNumber(1).succNumber(1).build();
	}

	@Override
	public boolean exist(String path) {
		//TODO
		return false;
	}

	/**
	 * The type Get object progress listener.
	 */
	static class GetObjectProgressListener implements ProgressListener {

		private long bytesRead = 0;
		private long totalBytes = -1;
		private boolean succeed = false;

		@Override
		public void progressChanged(ProgressEvent progressEvent) {
			long bytes = progressEvent.getBytes();
			ProgressEventType eventType = progressEvent.getEventType();
			switch (eventType) {
				case TRANSFER_STARTED_EVENT:
					logger.info("Start to download......");
					break;

				case RESPONSE_CONTENT_LENGTH_EVENT:
					this.totalBytes = bytes;
					logger.info(this.totalBytes + " bytes in total will be downloaded to a local file");
					break;

				case RESPONSE_BYTE_TRANSFER_EVENT:
					this.bytesRead += bytes;
					if (this.totalBytes != -1) {
						int percent = (int) (this.bytesRead * 100.0 / this.totalBytes);
						logger.info(bytes + " bytes have been read at this time, download progress: " +
								percent + "%(" + this.bytesRead + "/" + this.totalBytes + ")");
					} else {
						logger.info(bytes + " bytes have been read at this time, download ratio: unknown" +
								"(" + this.bytesRead + "/...)");
					}
					break;

				case TRANSFER_COMPLETED_EVENT:
					this.succeed = true;
					logger.info("Succeed to download, " + this.bytesRead + " bytes have been transferred in total");
					break;

				case TRANSFER_FAILED_EVENT:
					logger.info("Failed to download, " + this.bytesRead + " bytes have been transferred");
					break;

				default:
					break;
			}
		}

		/**
		 * Is succeed boolean.
		 *
		 * @return the boolean
		 */
		public boolean isSucceed() {
			return succeed;
		}
	}


	/**
	 * The type Put object progress listener.
	 */
	static class PutObjectProgressListener implements ProgressListener {
		private volatile long bytesWritten = 0;
		private volatile long totalBytes = -1;
		private volatile boolean succeed = false;

		@Override
		public void progressChanged(ProgressEvent progressEvent) {
			long bytes = progressEvent.getBytes();
			ProgressEventType eventType = progressEvent.getEventType();
			switch (eventType) {
				case TRANSFER_STARTED_EVENT:
					logger.info("Start to upload......");
					break;
				case REQUEST_CONTENT_LENGTH_EVENT:
					this.totalBytes = bytes;
					logger.info(this.totalBytes + " bytes in total will  uploaded to OSS");
					break;
				case REQUEST_BYTE_TRANSFER_EVENT:
					this.bytesWritten += bytes;
					if (this.totalBytes != -1) {
						int percent = (int) (this.bytesWritten * 100.0 / this.
								totalBytes);
						logger.info(bytes + " bytes have been written at this time, upload progress:" + percent + " % (" + this.bytesWritten + "/" + this.totalBytes + ")");
					} else {
						logger.info(bytes + " bytes have been written at this time, upload ratio: unknown " + " (" + this.bytesWritten + "/...)");
					}
					break;
				case TRANSFER_COMPLETED_EVENT:
					this.succeed = true;
					logger.info("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
					break;
				case TRANSFER_FAILED_EVENT:
					logger.info("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
					break;
				default:
					break;
			}
		}

		/**
		 * Is succeed boolean.
		 *
		 * @return the boolean
		 */
		public boolean isSucceed() {
			return succeed;
		}
	}
}
