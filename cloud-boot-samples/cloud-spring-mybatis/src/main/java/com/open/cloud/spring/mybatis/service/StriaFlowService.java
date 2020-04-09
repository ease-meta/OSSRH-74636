package com.open.cloud.spring.mybatis.service;

import com.open.cloud.spring.mybatis.entity.StriaFlow;

import java.util.List;

/**
 * Stria流程定义(StriaFlow)表服务接口
 *
 * @author makejava
 * @since 2020-04-04 03:38:58
 */
public interface StriaFlowService {

	List<StriaFlow> queryAll();
}