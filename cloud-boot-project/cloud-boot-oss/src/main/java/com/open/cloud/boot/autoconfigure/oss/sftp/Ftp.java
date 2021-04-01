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
import com.open.cloud.boot.autoconfigure.oss.Filter;
import com.open.cloud.boot.autoconfigure.oss.TransResult;
import com.open.cloud.boot.autoconfigure.oss.UploadFileRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FTP客户端封装<br>
 * 此客户端基于Apache-Commons-Net
 */
public class Ftp extends AbstractFtp {

    /**
     * 默认端口
     */
    private static final int DEFAULT_PORT = 21;

    private FTPClient client;

    private FtpMode mode;
    /**
     * 执行完操作是否返回当前目录
     */
    private boolean backToPwd;

    /**
     * 构造，匿名登录
     *
     * @param host 域名或IP
     */
    public Ftp(String host) {
        this(host, DEFAULT_PORT);
    }

    /**
     * 构造，匿名登录
     *
     * @param host 域名或IP
     * @param port 端口
     */
    public Ftp(String host, int port) {
        this(host, port, "anonymous", "");
    }

    /**
     * 构造
     *
     * @param host     域名或IP
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     */
    public Ftp(String host, int port, String user, String password) {
        this(host, port, user, password, StandardCharsets.UTF_8);
    }

    /**
     * 构造
     *
     * @param host     域名或IP
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     * @param charset  编码
     */
    public Ftp(String host, int port, String user, String password, Charset charset) {
        this(host, port, user, password, charset, null, null);
    }

    /**
     * 构造
     *
     * @param host               域名或IP
     * @param port               端口
     * @param user               用户名
     * @param password           密码
     * @param charset            编码
     * @param serverLanguageCode 服务器语言 例如：zh
     * @param systemKey          服务器标识 例如：org.apache.commons.net.ftp.FTPClientConfig.SYST_NT
     */
    public Ftp(String host, int port, String user, String password, Charset charset,
               String serverLanguageCode, String systemKey) {
        this(host, port, user, password, charset, serverLanguageCode, systemKey, null);
    }

    /**
     * 构造
     *
     * @param host     域名或IP
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     * @param charset  编码
     * @param mode     模式
     */
    public Ftp(String host, int port, String user, String password, Charset charset,
               String serverLanguageCode, String systemKey, FtpMode mode) {
        this(new FtpConfig(host, port, user, password, charset, serverLanguageCode, systemKey),
                mode);
    }

    /**
     * 构造
     *
     * @param config FTP配置
     * @param mode   模式
     */
    public Ftp(FtpConfig config, FtpMode mode) {
        super(config);
        this.mode = mode;
        this.init();
    }

    /**
     * 初始化连接
     *
     * @return this
     */
    private Ftp init() {
        return this.init(this.ftpConfig, this.mode);
    }

    /**
     * 初始化连接
     *
     * @param config FTP配置
     * @param mode   模式
     * @return this
     */
    private Ftp init(FtpConfig config, FtpMode mode) {
        final FTPClient client = new FTPClient();
        final Charset charset = config.getCharset();
        if (null != charset) {
            client.setControlEncoding(charset.toString());
        }
        client.setConnectTimeout((int) config.getConnectionTimeout());
        final String systemKey = config.getSystemKey();
        if (StringUtils.isNotBlank(systemKey)) {
            final FTPClientConfig conf = new FTPClientConfig(systemKey);
            final String serverLanguageCode = config.getServerLanguageCode();
            if (StringUtils.isNotBlank(serverLanguageCode)) {
                conf.setServerLanguageCode(config.getServerLanguageCode());
            }
            client.configure(conf);
        }

        try {
            // 连接ftp服务器
            client.connect(config.getHost(), config.getPort());
            client.setSoTimeout((int) config.getSoTimeout());
            // 登录ftp服务器
            client.login(config.getUser(), config.getPassword());
        } catch (IOException e) {
            throw new FtpException(e);
        }
        // 是否成功登录服务器
        final int replyCode = client.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            try {
                client.disconnect();
            } catch (IOException e) {
                // ignore
            }
            throw new FtpException("Login failed for user [{}], reply code is: [{}]");
        }
        this.client = client;
        if (mode != null) {
            setMode(mode);
        }
        return this;
    }

    /**
     * 设置FTP连接模式，可选主动和被动模式
     *
     * @param mode 模式枚举
     * @return this
     * @since 4.1.19
     */
    private Ftp setMode(FtpMode mode) {
        this.mode = mode;
        if (mode == FtpMode.Passive) {
            this.client.enterLocalPassiveMode();
        } else {
            this.client.enterLocalActiveMode();
        }
        return this;
    }

    /**
     * 初始化连接
     *
     * @param host     域名或IP
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     * @return this
     */
    private Ftp init(String host, int port, String user, String password) {
        return this.init(host, port, user, password, null);
    }

    /**
     * 初始化连接
     *
     * @param host     域名或IP
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     * @param mode     模式
     * @return this
     */
    private Ftp init(String host, int port, String user, String password, FtpMode mode) {
        return init(new FtpConfig(host, port, user, password, this.ftpConfig.getCharset(), null,
                null), mode);
    }

    /**
     * 设置执行完操作是否返回当前目录
     *
     * @param backToPwd 执行完操作是否返回当前目录
     * @return this
     * @since 4.6.0
     */
    public Ftp setBackToPwd(boolean backToPwd) {
        this.backToPwd = backToPwd;
        return this;
    }

    /**
     * 如果连接超时的话，重新进行连接 经测试，当连接超时时，client.isConnected()仍然返回ture，无法判断是否连接超时 因此，通过发送pwd命令的方式，检查连接是否超时
     *
     * @return this
     */
    @Override
    public Ftp reconnectIfTimeout() {
        String pwd = null;
        try {
            pwd = pwd();
        } catch (FtpException fex) {
            // ignore
        }

        if (pwd == null) {
            return this.init();
        }
        return this;
    }

    /**
     * 改变目录
     *
     * @param directory 目录
     * @return 是否成功
     */
    @Override
    public boolean cd(String directory) {
        if (StringUtils.isBlank(directory)) {
            return false;
        }

        try {
            return client.changeWorkingDirectory(directory);
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    /**
     * 远程当前目录
     *
     * @return 远程当前目录
     * @since 4.1.14
     */
    @Override
    public String pwd() {
        try {
            return client.printWorkingDirectory();
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    @Override
    public boolean mkdir(String dir) {
        try {
            return this.client.makeDirectory(dir);
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    @Override
    public List<String> ls(String path) {
        final FTPFile[] ftpFiles = lsFiles(path);

        final List<String> fileNames = new ArrayList<>();
        for (FTPFile ftpFile : ftpFiles) {
            fileNames.add(ftpFile.getName());
        }
        return fileNames;
    }

    /**
     * 遍历某个目录下所有文件和目录，不会递归遍历
     *
     * @param path 目录
     * @return 文件或目录列表
     */
    private FTPFile[] lsFiles(String path) {
        String pwd = null;
        if (StringUtils.isNotBlank(path)) {
            pwd = pwd();
            cd(path);
        }

        FTPFile[] ftpFiles;
        try {
            ftpFiles = this.client.listFiles();
        } catch (IOException e) {
            throw new FtpException(e);
        } finally {
            // 回到原目录
            cd(pwd);
        }

        return ftpFiles;
    }

    @Override
    public boolean delFile(String path) {
        final String pwd = pwd();
        final String fileName = FilenameUtils.getName(path);
        final String dir = FilenameUtils.getPath(path);
        cd(dir);
        boolean isSuccess;
        try {
            isSuccess = client.deleteFile(fileName);
        } catch (IOException e) {
            throw new FtpException(e);
        } finally {
            // 回到原目录
            cd(pwd);
        }
        return isSuccess;
    }

    @Override
    public boolean delDir(String dirPath) {
        FTPFile[] dirs;
        try {
            dirs = client.listFiles(dirPath);
        } catch (IOException e) {
            throw new FtpException(e);
        }
        String name;
        String childPath;
        for (FTPFile ftpFile : dirs) {
            name = ftpFile.getName();
            childPath = dirPath + name;
            if (ftpFile.isDirectory()) {
                // 上级和本级目录除外
                if (!".".equals(name) && !"..".equals(name)) {
                    delDir(childPath);
                }
            } else {
                delFile(childPath);
            }
        }

        // 删除空目录
        try {
            return this.client.removeDirectory(dirPath);
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    /**
     * 上传文件到指定目录，可选：
     *
     * <pre>
     * 1. path为null或""上传到当前路径
     * 2. path为相对路径则相对于当前路径的子路径
     * 3. path为绝对路径则上传到此路径
     * </pre>
     *
     * @param destPath 服务端路径，可以为{@code null} 或者相对路径或绝对路径
     * @param file     文件
     * @return 是否上传成功
     */
    @Override
    public boolean upload(String destPath, File file) {
        Assert.notNull(file, "file to upload is null !");
        return upload(destPath, file.getName(), file);
    }

    @Override
    public TransResult upload(UploadFileRequest uploadFileRequest) {
        Assert.notNull(uploadFileRequest, "file to upload is null !");
        String destPath = uploadFileRequest.getObjectName();
        String fileName = FilenameUtils.getName(uploadFileRequest.getObjectName());
        fileName = StringUtils.isBlank(fileName) ? FilenameUtils.getName(uploadFileRequest
                .getLocalFile().getName()) : fileName;
        upload(FilenameUtils.getPath(destPath), fileName, uploadFileRequest.getLocalFile());
        return null;
    }

    @Override
    public TransResult download(DownloadFileRequest downloadFileRequest) {
        String path = downloadFileRequest.getObjectName();
        File outFile = downloadFileRequest.getLocalFile();
        final String fileName = FilenameUtils.getName(path);
        final String dir = FilenameUtils.getPath(path);
        download(dir, fileName, outFile);
        return null;
    }

    @Override
    public TransResult recursiveDownloadFolder(DownloadFileRequest downloadFileRequest) {
        String fileName;
        String srcFile;
        File destFile;
        String sourcePath = downloadFileRequest.getObjectName();
        File destDir = downloadFileRequest.getLocalFile();
        for (FTPFile ftpFile : lsFiles(sourcePath, null)) {
            fileName = ftpFile.getName();
            srcFile = FilenameUtils.getPath(sourcePath) + fileName;
            destFile = new File(FilenameUtils.getPath(destDir.getPath()) + fileName);
            if (!ftpFile.isDirectory()) {
                // 本地不存在文件或者ftp上文件有修改则下载
                if (!destFile.exists()
                        || (ftpFile.getTimestamp().getTimeInMillis() > destFile.lastModified())) {
                    download(srcFile, destFile);
                }
            } else {
                // 服务端依旧是目录，继续递归
                if (!destFile.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    destFile.mkdirs();
                }
                DownloadFileRequest downloadFileRequest1 = new DownloadFileRequest();
                downloadFileRequest1.setObjectName(ftpFile.getName());
                downloadFileRequest1.setLocalFile(destDir);
                //recursiveDownloadFolder(srcFile, destFile);
                recursiveDownloadFolder(downloadFileRequest1);
            }
        }
        TransResult.TransResultBuilder transResultBuilder = new TransResult.TransResultBuilder();
        return transResultBuilder.totalFileNumber(1).succNumber(1).build();
    }

    @Override
    protected void download(String path, File outFile) {
        final String fileName = FilenameUtils.getName(path);
        final String dir = FilenameUtils.getPath(path);
        download(dir, fileName, outFile);
    }

    /**
     * 下载文件
     *
     * @param path     文件路径
     * @param fileName 文件名
     * @param outFile  输出文件或目录
     */
    private void download(String path, String fileName, File outFile) {
        if (outFile.isDirectory()) {
            outFile = new File(outFile, fileName);
        }
        try (OutputStream out = FileUtils.openOutputStream(outFile)) {
            if (!outFile.exists()) {
                FileUtils.touch(outFile);
            }
            download(path, fileName, out);
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    /**
     * 下载文件到输出流
     *
     * @param path     文件路径
     * @param fileName 文件名
     * @param out      输出位置
     */
    public void download(String path, String fileName, OutputStream out) {
        download(path, fileName, out, null);
    }

    /**
     * 下载文件到输出流
     *
     * @param path            文件路径
     * @param fileName        文件名
     * @param out             输出位置
     * @param fileNameCharset 文件名编码
     * @since 5.5.7
     */
    public void download(String path, String fileName, OutputStream out, Charset fileNameCharset) {
        String pwd = null;
        if (this.backToPwd) {
            pwd = pwd();
        }

        cd(path);

        if (null != fileNameCharset) {
            fileName = new String(fileName.getBytes(fileNameCharset), StandardCharsets.ISO_8859_1);
        }
        try {
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.retrieveFile(fileName, out);
        } catch (IOException e) {
            throw new FtpException(e);
        } finally {
            if (backToPwd) {
                cd(pwd);
            }
        }
    }

    /**
     * 上传文件到指定目录，可选：
     *
     * <pre>
     * 1. path为null或""上传到当前路径
     * 2. path为相对路径则相对于当前路径的子路径
     * 3. path为绝对路径则上传到此路径
     * </pre>
     *
     * @param file     文件
     * @param path     服务端路径，可以为{@code null} 或者相对路径或绝对路径
     * @param fileName 自定义在服务端保存的文件名
     * @return 是否上传成功
     */
    private boolean upload(String path, String fileName, File file) {
        try (InputStream in = FileUtils.openInputStream(file)) {
            return upload(path, fileName, in);
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    /**
     * 上传文件到指定目录，可选：
     *
     * <pre>
     * 1. path为null或""上传到当前路径
     * 2. path为相对路径则相对于当前路径的子路径
     * 3. path为绝对路径则上传到此路径
     * </pre>
     *
     * @param path       服务端路径，可以为{@code null} 或者相对路径或绝对路径
     * @param fileName   文件名
     * @param fileStream 文件流
     * @return 是否上传成功
     */
    public boolean upload(String path, String fileName, InputStream fileStream) {
        try {
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
        } catch (IOException e) {
            throw new FtpException(e);
        }

        String pwd = null;
        if (this.backToPwd) {
            pwd = pwd();
        }

        if (StringUtils.isNotBlank(path)) {
            mkDirs(path);
            boolean isOk = cd(path);
            if (!isOk) {
                return false;
            }
        }

        try {
            return client.storeFile(fileName, fileStream);
        } catch (IOException e) {
            throw new FtpException(e);
        } finally {
            if (this.backToPwd) {
                cd(pwd);
            }
        }
    }

    /**
     * 遍历某个目录下所有文件和目录，不会递归遍历<br>
     * 此方法自动过滤"."和".."两种目录
     *
     * @param path   目录
     * @param filter 过滤器，null表示不过滤，默认去掉"."和".."两种目录
     * @return 文件或目录列表
     * @since 5.3.5
     */
    private List<FTPFile> lsFiles(String path, Filter<FTPFile> filter) {
        final FTPFile[] ftpFiles = lsFiles(path);
        if (ObjectUtils.isEmpty(ftpFiles)) {
            return Collections.emptyList();
        }

        final List<FTPFile> result = new ArrayList<>(ftpFiles.length - 2 <= 0 ? ftpFiles.length
                : ftpFiles.length - 2);
        String fileName;
        for (FTPFile ftpFile : ftpFiles) {
            fileName = ftpFile.getName();
            if (!StringUtils.equals(".", fileName) && !StringUtils.equals("..", fileName)) {
                if (null == filter || filter.accept(ftpFile)) {
                    result.add(ftpFile);
                }
            }
        }
        return result;
    }

    /**
     * 获取服务端目录状态。
     *
     * @param path 路径
     * @return 状态int，服务端不同，返回不同
     * @since 5.4.3
     */
    public int stat(String path) {
        try {
            return this.client.stat(path);
        } catch (IOException e) {
            throw new FtpException(e);
        }
    }

    /**
     * 判断ftp服务器文件是否存在
     *
     * @param path 文件路径
     * @return 是否存在
     */
    public boolean existFile(String path) {
        FTPFile[] ftpFileArr;
        try {
            ftpFileArr = client.listFiles(path);
        } catch (IOException e) {
            throw new FtpException(e);
        }
        return ObjectUtils.isNotEmpty(ftpFileArr);
    }

    /**
     * 获取FTPClient客户端对象
     *
     * @return {@link FTPClient}
     */
    public FTPClient getClient() {
        return this.client;
    }

    @Override
    public void close() throws IOException {
        if (null != this.client) {
            this.client.logout();
            if (this.client.isConnected()) {
                this.client.disconnect();
            }
            this.client = null;
        }
    }
}
