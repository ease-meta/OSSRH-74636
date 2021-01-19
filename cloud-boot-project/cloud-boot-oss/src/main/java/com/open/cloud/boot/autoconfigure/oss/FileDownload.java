package com.open.cloud.boot.autoconfigure.oss;

/**
 * The interface File download.
 * @author Leijian
 * @date 2021 /1/18 21:00
 * @since
 */
public interface FileDownload {

	/**
	 * 下载文件
	 */
	public abstract TransResult download(final DownloadFileRequest downloadFileRequest);
}
