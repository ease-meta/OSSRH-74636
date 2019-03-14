package com.moc.cloud.workflow.framework.repository;

import com.moc.cloud.workflow.framework.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname: ResourceRepository
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/13
 * @Version: 1.0
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource,String> {
    List<Resource> findResourceByMethodEquals(String method);
}
