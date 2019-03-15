package com.open.cloud.workflow.framework.serviceImpl;

import com.open.cloud.workflow.common.utils.BaseMapper;
import com.open.cloud.workflow.framework.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname: ServiceImpl
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/17
 * @Version: 1.0
 */
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    @Autowired
    protected M baseMapper;

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    public  int deleteByPrimaryKey(String id){
        return  baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除
     * @param entity
     * @return
     */
    public  int deleteSelective(T entity){
        return  baseMapper.deleteSelective(entity);
    }

    /**
     * 插入实体类
     * @param entity
     * @return
     */
    public  int insert(T entity){
        return  baseMapper.insert(entity);
    }

    /**
     * 批量插入实体类
     * @param entity
     * @return
     */
    public int insertBatch(List<T> entity){
        return baseMapper.insertBatch(entity);
    }

    /**
     * 选择性插入实体类
     * @param entity
     * @return
     */
    public int insertSelective(T entity){
        return  baseMapper.insertSelective(entity);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public T selectByPrimaryKey(String id){
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有信息
     * @return
     */
    public List<T> selectAll(){
        return  baseMapper.selectAll();
    }

    /**
     *
     * @param entity
     * @return
     */
    public List<T> selectSelective(T entity){
        return  baseMapper.selectSelective(entity);
    }
    /**
     * 选择性更新
     * @param entity
     * @return
     */
    @Transactional
    public int updateByPrimaryKeySelective(T entity){
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据主键更新
     * @param entity
     * @return
     */
    public int updateByPrimaryKey(T entity){
        return baseMapper.updateByPrimaryKey(entity);
    }
}

