package com.open.cloud.dubbo.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.open.cloud.dubbo.provider.entity.BatchOnlineCheckEntity;


/**
 * 文件登记表(UPRGHT)
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-04-19 20:14:13
 */
public interface BatchOnlineCheckService extends IService<BatchOnlineCheckEntity> {
	BatchOnlineCheckEntity getByJobId(String id);
}

