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
package com.open.cloud.boot.autoconfigure.oss.sftp;

import com.open.cloud.boot.autoconfigure.oss.DownloadFileRequest;
import com.open.cloud.boot.autoconfigure.oss.FileBase;
import com.open.cloud.boot.autoconfigure.oss.TransResult;
import com.open.cloud.boot.autoconfigure.oss.UploadFileRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Leijian
 * @date 2021/1/19 20:47
 * @since
 */
public abstract class AbstractFtp extends FileBase {

	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	protected FtpConfig ftpConfig;

	/**
	 * 构造
	 *
	 * @param config FTP配置
	 * @since 5.3.3
	 */
	protected AbstractFtp(FtpConfig config) {
		this.ftpConfig = config;
	}

	/**
	 * 如果连接超时的话，重新进行连接
	 *
	 * @return this
	 * @since 4.5.2
	 */
	public abstract AbstractFtp reconnectIfTimeout();

	/**
	 * 打开上级目录
	 *
	 * @return 是否打开目录
	 * @since 4.0.5
	 */
	boolean toParent() {
		return cd("..");
	}

	/**
	 * 打开指定目录
	 *
	 * @param directory directory
	 * @return 是否打开目录
	 */
	abstract boolean cd(String directory);

	/**
	 * 文件或目录是否存在
	 *
	 * @param path 目录
	 * @return 是否存在
	 */
	@Override
	public boolean exist(final String path) {
		final String fileName = FilenameUtils.getName(path);
		final String dir = FilenameUtils.getFullPath(path);
		final List<String> names = ls(dir);
		return containsIgnoreCase(names, fileName);
	}

	/**
	 * 遍历某个目录下所有文件和目录，不会递归遍历
	 *
	 * @param path 需要遍历的目录
	 * @return 文件和目录列表
	 */
	public abstract List<String> ls(String path);

	/**
	 * 是否包含指定字符串，忽略大小写
	 *
	 * @param names      文件或目录名列表
	 * @param nameToFind 要查找的文件或目录名
	 * @return 是否包含
	 */
	private static boolean containsIgnoreCase(List<String> names, String nameToFind) {
		if (CollectionUtils.isEmpty(names)) {
			return false;
		}
		if (StringUtils.isEmpty(nameToFind)) {
			return false;
		}
		for (String name : names) {
			if (nameToFind.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 删除指定目录下的指定文件
	 *
	 * @param path 目录路径
	 * @return 是否存在
	 */
	public abstract boolean delFile(String path);

	/**
	 * 删除文件夹及其文件夹下的所有文件
	 *
	 * @param dirPath 文件夹路径
	 * @return boolean 是否删除成功
	 */
	public abstract boolean delDir(String dirPath);

	/**
	 * 创建指定文件夹及其父目录，从根目录开始创建，创建完成后回到默认的工作目录
	 *
	 * @param dir 文件夹路径，绝对路径
	 */
	protected void mkDirs(String dir) {
		final String[] dirs = StringUtils.trim(dir).split("[\\\\/]+");

		final String now = pwd();
		if (dirs.length > 0 && StringUtils.isEmpty(dirs[0])) {
			//首位为空，表示以/开头
			cd("\\/");
		}
		for (String s : dirs) {
			if (StringUtils.isNotEmpty(s)) {
				if (!cd(s)) {
					//目录不存在时创建
					mkdir(s);
					cd(s);
				}
			}
		}
		// 切换回工作目录
		cd(now);
	}

	/**
	 * 远程当前目录（工作目录）
	 *
	 * @return 远程当前目录
	 */
	abstract String pwd();

	/**
	 * 在当前远程目录（工作目录）下创建新的目录
	 *
	 * @param dir 目录名
	 * @return 是否创建成功
	 */
	abstract boolean mkdir(String dir);

	/**
	 * 将本地文件上传到目标服务器，目标文件名为destPath，若destPath为目录，则目标文件名将与file文件名相同。
	 * 覆盖模式
	 *
	 * @param destPath 服务端路径，可以为{@code null} 或者相对路径或绝对路径
	 * @param file     需要上传的文件
	 * @return 是否成功
	 */
	protected abstract boolean upload(String destPath, File file);

	@Override
	public abstract TransResult upload(final UploadFileRequest uploadFileRequest);

	@Override
	public abstract TransResult download(final DownloadFileRequest downloadFileRequest);

	@Override
	public abstract TransResult recursiveDownloadFolder(DownloadFileRequest downloadFileRequest);

	// ---------------------------------------------------------------------------------------------------------------------------------------- Private method start

	/**
	 * 下载文件
	 *
	 * @param path    文件路径
	 * @param outFile 输出文件或目录
	 */
	protected abstract void download(String path, File outFile);
	// ---------------------------------------------------------------------------------------------------------------------------------------- Private method end
}
