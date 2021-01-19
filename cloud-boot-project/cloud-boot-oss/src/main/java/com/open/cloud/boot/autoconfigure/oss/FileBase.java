package com.open.cloud.boot.autoconfigure.oss;

import java.io.Closeable;

/**
 * The type File base.
 * @author Leijian
 * @date 2021 /1/18 20:57
 * @since
 */
public abstract class FileBase implements FileDownload, FileUpload, Closeable {

	public abstract boolean exist(final String path);
}
