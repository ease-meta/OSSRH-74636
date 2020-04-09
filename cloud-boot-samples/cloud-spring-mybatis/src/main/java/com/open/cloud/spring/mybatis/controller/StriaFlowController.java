package com.open.cloud.spring.mybatis.controller;

import com.open.cloud.spring.mybatis.entity.StriaFlow;
import com.open.cloud.spring.mybatis.service.StriaFlowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Stria流程定义(StriaFlow)表控制层
 *
 * @author makejava
 * @since 2020-04-04 03:39:00
 */
@RestController
@RequestMapping("/striaFlow/")
public class StriaFlowController {
	/**
	 * 服务对象
	 */
	@Resource
	private StriaFlowService striaFlowService;

	/**
	 * 通过主键查询单条数据
	 *
	 * @return 单条数据
	 */
	@PostMapping("/selectOne")
	public List<StriaFlow> selectOne() {
		return this.striaFlowService.queryAll();
	}

}