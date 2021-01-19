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
	 *
	 * @param path    文件路径
	 * @param outFile 输出文件或目录
	 */
	public abstract TransResult download(final DownloadFileRequest downloadFileRequest);

	/**
	 * 递归下载FTP服务器上文件到本地(文件目录和服务器同步), 服务器上有新文件会覆盖本地文件
	 *
	 * @param sourcePath ftp服务器目录
	 * @param destDir    本地目录
	 */
	public abstract TransResult recursiveDownloadFolder(final DownloadFileRequest downloadFileRequest);
}
