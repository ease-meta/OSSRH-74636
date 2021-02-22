package com.search.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.search.demo.bean.Item;

import java.io.IOException;

/**
 * ItemService
 *
 * @author sk
 * @date 2020/3/30 11:26
 */
public interface ItemService extends IService<Item> {

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteOne(Long id);

    /**
     * 单条插入
     * @param item
     * @param id
     * @return
     */
    Boolean addItem(Item item, String id);

    /**
     * 删除所有
     * @return
     */
    Object deleteAll();

    /**
     * 关键字查询
     * @param key
     * @param value
     * @param current
     * @param size
     * @return
     */
    Object selectByKey(String key, String value, Integer current, Integer size);

    /**
     * 查询所有
     * @return
     */
    Page<Item> selectAll();

    /**
     * 批量导入
     * @return
     */
    Object insertByBulk();

    /**
     * 搜索建议
     * @param value
     * @return
     */
    Object selectSuggest(String value);

    /**
     * 创建映射
     * @return
     */
    Object createMapping();

    /**
     * 创建索引
     * @return
     */
    Object createIndex();
}
