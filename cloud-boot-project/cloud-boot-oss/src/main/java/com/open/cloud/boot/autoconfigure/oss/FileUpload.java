package com.open.cloud.boot.autoconfigure.oss;

/**
 * The interface File upload.
 * @author Leijian
 * @date 2021 /1/18 20:53
 * @since
 */
public interface FileUpload {

	/**
	 * 将本地文件上传到目标服务器，目标文件名为destPath，若destPath为目录，则目标文件名将与file文件名相同。
	 * 覆盖模式
	 *
	 * @param uploadFileRequest 服务端路径，可以为{@code null} 或者相对路径或绝对路径
	 */
	public abstract TransResult upload(final UploadFileRequest uploadFileRequest);
}
