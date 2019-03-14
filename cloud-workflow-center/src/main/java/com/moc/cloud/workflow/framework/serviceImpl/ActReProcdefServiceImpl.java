package com.moc.cloud.workflow.framework.serviceImpl;


import com.moc.cloud.workflow.framework.entity.ActGeBytearray;
import com.moc.cloud.workflow.framework.entity.ActReProcdef;
import com.moc.cloud.workflow.framework.mapper.ActGeBytearrayMapper;
import com.moc.cloud.workflow.framework.mapper.ActReProcdefMapper;
import com.moc.cloud.workflow.framework.service.ActReProcdefService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 流程定义 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-11
 */
@Service
public class ActReProcdefServiceImpl implements ActReProcdefService {

    @Resource
    ActReProcdefMapper actReProcdefMapper;

    @Resource
    ActGeBytearrayMapper actGeBytearrayMapper;

    /**
     * @param actReProcdef
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int save(ActReProcdef actReProcdef) {
        return actReProcdefMapper.insertSelective(actReProcdef);

    }

    public ActReProcdef selectOne(ActReProcdef actReProcdef) {
        return actReProcdefMapper.selectSelective(actReProcdef).stream().sorted(Comparator.comparing(ActReProcdef::getVersion).reversed()).findFirst().get();

    }

    /**
     * @param actReProcdef
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateByKey(ActReProcdef actReProcdef) {
        return actReProcdefMapper.updateByKey(actReProcdef);

    }

    /**
     * @param actReProcdef
     * @param actGeBytearray
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(ActReProcdef actReProcdef, ActGeBytearray actGeBytearray) {
        actReProcdefMapper.insertSelective(actReProcdef);
        actGeBytearrayMapper.insertSelective(actGeBytearray);

    }

    /**
     * @param key
     * @return
     */
    public List<ActReProcdef> list(String key) {
        return actReProcdefMapper.selectByKey(key);

    }

    /**
     * @param id
     * @return
     */
    public ActReProcdef listByID(String id) {
        return actReProcdefMapper.selectById(id);

    }

    /**
     * @return
     */
    public List<ActReProcdef> listAll() {
        return actReProcdefMapper.selectAll();

    }

    /**
     * @param actReProcdef
     * @return
     */
    public List<ActReProcdef> listAll(ActReProcdef actReProcdef) {
        return actReProcdefMapper.selectSelective(actReProcdef);
    }

}
