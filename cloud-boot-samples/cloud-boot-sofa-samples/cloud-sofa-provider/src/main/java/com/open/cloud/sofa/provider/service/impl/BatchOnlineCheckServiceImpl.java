package com.open.cloud.sofa.provider.service.impl;

import com.open.cloud.sofa.provider.dao.BatchOnlineCheckDao;
import com.open.cloud.sofa.provider.entity.BatchOnlineCheckEntity;
import com.open.cloud.sofa.provider.service.BatchOnlineCheckService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("batchOnlineCheckService")
public class BatchOnlineCheckServiceImpl extends ServiceImpl<BatchOnlineCheckDao, BatchOnlineCheckEntity> implements BatchOnlineCheckService {

	@Cacheable(value="business#6*10")
	public BatchOnlineCheckEntity getByJobId(String id) {
		return list().get(0);
	}
}