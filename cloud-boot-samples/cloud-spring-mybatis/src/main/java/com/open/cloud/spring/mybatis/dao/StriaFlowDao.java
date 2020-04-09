package com.open.cloud.spring.mybatis.dao;

import com.open.cloud.spring.mybatis.entity.StriaFlow;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Stria流程定义(StriaFlow)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-04 03:38:57
 */
@Repository
public interface StriaFlowDao {

    List<StriaFlow> queryAll();

}