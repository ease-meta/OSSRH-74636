package com.open.cloud.mybatis;


import com.open.cloud.core.po.BasePO;

import java.util.List;

/**
 * Mbatis持久化业务领域
 *
 * @author leijian
 * @date 2021/9/27 9:19
 */
public abstract class BaseDaoAction implements BaseAction {


    //todo 注入mybatis的BaseDaoAction实现类map即可。
    //使用mapper的好处是可以快速找到不同的业务领域
    @Override
    public <T extends BasePO> int insert(T entity) {
        return 0;
    }

    @Override
    public <T extends BasePO> int delete(T entity) {
        return 0;
    }

    @Override
    public <T extends BasePO> int update(T entity, T whereEntity) {
        return 0;
    }

    @Override
    public <T extends BasePO> Long selectCount(T entity) {
        return null;
    }

    @Override
    public <T extends BasePO> List<T> selectList(T record, Object... pkValue) {
        return null;
    }

    @Override
    public <T extends BasePO> T selectByPrimaryKey(T record, Object... pkValue) {
        return null;
    }

    @Override
    public <T extends BasePO> int deleteByPrimaryKey(T record) {
        return 0;
    }

    @Override
    public <T extends BasePO> int updateByPrimaryKey(T record) {
        return 0;
    }
}
















