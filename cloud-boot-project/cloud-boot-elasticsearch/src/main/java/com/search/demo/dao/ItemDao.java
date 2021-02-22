package com.search.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.search.demo.bean.Item;
import org.apache.ibatis.annotations.Mapper;

/**
 * ItemDao
 *
 * @author sk
 * @date 2020/3/30 11:26
 */
@Mapper
public interface ItemDao extends BaseMapper<Item> {
}
