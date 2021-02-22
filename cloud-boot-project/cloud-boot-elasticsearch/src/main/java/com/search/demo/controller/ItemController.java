package com.search.demo.controller;

import com.search.demo.bean.Item;
import com.search.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User控制层
 *
 * @author sk
 * @date 2020/3/30 10:48
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 创建索引
     * @return
     */
    @GetMapping("/createIndex")
    public Object createIndex() {
        return itemService.createIndex();
    }

    /**
     * 创建映射
     *
     * @return
     */
    @GetMapping("/createMapping")
    public Object createMapping() {
        return itemService.createMapping();
    }

    /**
     * 批量导入数据
     *
     * @return
     */
    @GetMapping("insertByBulk")
    public Object insertByBulk() {
        return itemService.insertByBulk();
    }

    /**
     * 插入一条数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/insertOne")
    public Object insertOne(Integer id) {
        Item user = itemService.getById(id);
        return itemService.addItem(user, id + "");
    }

    /**
     * 删除所有
     *
     * @return
     */
    @GetMapping("deleteAll")
    public Object deleteAll() {
        return itemService.deleteAll();
    }

    /**
     * 删除所有
     *
     * @return
     */
    @GetMapping("deleteById")
    public Object deleteById(Long id) {
        return itemService.deleteOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("selectAll")
    public Object selectAll() {
        return itemService.selectAll();
    }

    /**
     * 根据关键字的字段进行查询
     *
     * @param key     字段名
     * @param value   查询关键字
     * @param current 当前页
     * @param size    每页显示条数
     * @return
     */
    @RequestMapping("/selectByMap")
    public Object selectByMap(String key, String value, Integer current, Integer size) {
        return itemService.selectByKey(key, value, current, size);
    }

    /**
     * 搜索建议
     *
     * @param value
     * @return
     */
    @GetMapping("/selectSuggest")
    public Object selectSuggest(String value) {
        return itemService.selectSuggest(value);
    }

}
