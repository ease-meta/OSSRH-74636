package com.open.cloud.mybatis;

import com.open.cloud.core.po.BasePO;

import java.util.List;

/**
 * Mbatis持久化业务领域
 *
 * @author leijian
 * @date 2021/9/27 9:19
 */
public class MbatisDaoAction extends BaseDaoAction {


    @Override
    public int insert(BasePO entity) {
        return 0;
    }

    @Override
    public int delete(BasePO entity) {
        return 0;
    }

    @Override
    public int update(BasePO entity, BasePO whereEntity) {
        return 0;
    }

    @Override
    public Long selectCount(BasePO entity) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(BasePO record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(BasePO record) {
        return 0;
    }

    @Override
    public BasePO selectByPrimaryKey(BasePO record, Object... pkValue) {
        return null;
    }

    @Override
    public List selectList(BasePO record, Object... pkValue) {
        return null;
    }
}
