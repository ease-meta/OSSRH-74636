package com.open.cloud.workflow.framework.repository;

import com.open.cloud.workflow.framework.entity.ActToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname: ActTokenRepository
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/13
 * @Version: 1.0
 */
@Repository
public interface ActTokenRepository  extends JpaRepository<ActToken,String> {
}
