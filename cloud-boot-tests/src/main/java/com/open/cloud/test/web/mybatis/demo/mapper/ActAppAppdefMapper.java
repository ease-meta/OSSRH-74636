package com.open.cloud.test.web.mybatis.demo.mapper;


import com.open.cloud.mybatis.BaseMapper;
import com.open.cloud.test.web.mybatis.demo.entity.ActAppAppdef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2021-10-11
 */
@Mapper
public interface ActAppAppdefMapper extends BaseMapper<ActAppAppdef> {

    @Select("SELECT * FROM act_app_appdef")
    public List<ActAppAppdef> selectByPage();
}
