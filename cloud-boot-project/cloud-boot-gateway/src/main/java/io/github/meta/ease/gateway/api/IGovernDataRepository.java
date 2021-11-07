package io.github.meta.ease.gateway.api;

import java.util.Set;

/**
 * 数据获取接口
 *
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 19:03
 */
public interface IGovernDataRepository<T> {

    void create(T e);

    void update(T e);

    void delete(String... conditions);

    /**
     * 模糊删除
     *
     * @param conditions
     */
    void fuzzyDelete(String... conditions);

    /**
     * 模糊查询
     *
     * @param conditions
     */
    Set<T> fuzzyQuery(String... conditions);

    /**
     * 返回指定对象
     *
     * @param conditions
     */
    T get(String... conditions);

}
