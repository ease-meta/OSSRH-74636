package com.open.cloud.common.utils;

import java.util.List;

/**
 * @author Leijian
 */
public interface BaseMapper<T> {
	/**
	 * 根据主键删除
	 *
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 删除
	 *
	 * @param entity
	 * @return
	 */
	int deleteSelective(T entity);

	/**
	 * 插入实体类
	 *
	 * @param entity
	 * @return
	 */
	int insert(T entity);

	/**
	 * 批量插入实体类
	 *
	 * @param entity
	 * @return
	 */
	int insertBatch(List<T> entity);

	/**
	 * 选择性插入实体类
	 *
	 * @param entity
	 * @return
	 */
	int insertSelective(T entity);

	/**
	 * 根据主键查询
	 *
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(String id);

	/**
	 * 查询所有信息
	 *
	 * @return
	 */
	List<T> selectAll();

	/**
	 * @param entity
	 * @return
	 */
	List<T> selectSelective(T entity);

	/**
	 * 选择性更新
	 *
	 * @param entity
	 * @return
	 */
	int updateByPrimaryKeySelective(T entity);

	/**
	 * 根据主键更新
	 *
	 * @param entity
	 * @return
	 */
	int updateByPrimaryKey(T entity);

}
