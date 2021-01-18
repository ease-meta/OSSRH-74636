package com.open.cloud.boot.autoconfigure.oss.alibaba;

import com.aliyun.oss.OSSClient;
import com.open.cloud.boot.autoconfigure.oss.FileBase;

/**
 * @author Leijian
 * @date 2021/1/18 21:43
 * @since
 */
public class AliOss extends FileBase {
	private OSSClient ossClient;
}
