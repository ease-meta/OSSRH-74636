package com.open.cloud.test.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/7 17:49
 **/
public class DaoSupportImpl extends SqlSessionDaoSupport {

    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

}
