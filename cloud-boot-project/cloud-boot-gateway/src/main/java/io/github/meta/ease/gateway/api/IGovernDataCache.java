package io.github.meta.ease.gateway.api;

/**
 * 数据清理接口
 *
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 19:01
 */
public interface IGovernDataCache {
    /**
     * 清空所有数据
     */
    void clear();

    /**
     * 从缓存加载数据
     */
    void load();

    /**
     * 获取缓存类型
     *
     * @return
     */
    String getType();


}
