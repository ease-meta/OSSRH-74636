package com.moc.cloud.workflow.framework.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moc.cloud.workflow.framework.entity.ActAssignee;
import com.moc.cloud.workflow.framework.mapper.ActAssigneeMapper;
import com.moc.cloud.workflow.framework.service.ActAssigneeService;

/**
 * <p>
 * 流程定义审批人 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
public class ActAssigneeServiceImpl extends ServiceImpl<ActAssigneeMapper, ActAssignee> implements ActAssigneeService {
	@Resource
	ActAssigneeMapper actAssigneeMapper;
	
    public int deleteBatch(final List<ActAssignee> actAssigneeLinkedList) {
        return actAssigneeMapper.deleteBatch(actAssigneeLinkedList);
    }

    public ActAssignee getByKeyAndSid(String key, String sid) {
        return actAssigneeMapper.getByKeyAndSid(key, sid);
    }
}
