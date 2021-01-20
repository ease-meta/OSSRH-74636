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
package com.open.cloud.sofa.provider.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件登记表(UPRGHT)
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-04-19 20:14:13
 */
@Data
@TableName("batch_online_check")
public class BatchOnlineCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * JOB_ID
	 */
	@TableId
	private String jobId;
	/**
	 * JOB_RUN_ID
	 */
	private String jobRunId;
	/**
	 * STEP_RUN_ID
	 */
	private String stepRunId;
	/**
	 * 渠道流水号
	 */
	private String channelSeqNo;
	/**
	 * 批次号
	 */
	private String batchNo;
	/**
	 * sonic执行序号
	 */
	private String operationId;
	/**
	 * 业务类型
	 */
	private String stepType;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件类型
	 */
	private String processType;
	/**
	 * 交易日期
	 */
	private Date tranDate;
	/**
	 * 交易日期
	 */
	private Date runDate;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件名称
	 */
	private String filePath;
	/**
	 * 文件MD5校验值
	 */
	private String fileMd5;
	/**
	 * 流程开始时间
	 */
	private Date startTime;
	/**
	 * 流程结束时间
	 */
	private Date endTime;
	/**
	 * 文件总笔数
	 */
	private Long totalNumber;
	/**
	 * 成功笔数
	 */
	private Long successNumber;
	/**
	 * 失败笔数
	 */
	private Long failureNumber;
	/**
	 * 业务处理状态 S:成功 F:失败 P 处理中
	 */
	private String tranStatus;
	/**
	 * 交易状态码
	 */
	private String errorCode;
	/**
	 * 交易状态描述信息
	 */
	private String errorDesc;
	/**
	 * IP
	 */
	private String hostIp;
	/**
	 *
	 */
	private String sysHead;
	/**
	 *
	 */
	private String appHead;
	/**
	 *
	 */
	private String attrJson;

}
