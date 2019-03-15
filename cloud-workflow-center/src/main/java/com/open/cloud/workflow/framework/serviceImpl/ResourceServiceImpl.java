/**
 * <p>Title: ResourceServiceImpl.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月26日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.serviceImpl;

import com.open.cloud.workflow.framework.entity.Resource;
import com.open.cloud.workflow.framework.mapper.ResourceMapper;
import com.open.cloud.workflow.framework.repository.ResourceRepository;
import com.open.cloud.workflow.framework.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author leijian
 * @date 2019年1月26日
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> selectAll(String method) {
        //return baseMapper.selectAll();
        return resourceRepository.findAll();
    }

    public List<Resource> getResourceByMethod(String method) {
        //return baseMapper.selectResourceByMethod(method);
        List<Resource> resourceList =  resourceRepository.findResourceByMethodEquals(method);
        System.out.println(resourceList.get(0));
        return resourceList;
    }

    public String getResourcePermTag(String method, String mapping) {
        return method + ":" + mapping;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveOrUpdateBatch(List list) {
        //baseMapper.deleteAll();
        //baseMapper.insertBatch(list);
        resourceRepository.deleteAll();
        resourceRepository.saveAll(list);
    }

}
