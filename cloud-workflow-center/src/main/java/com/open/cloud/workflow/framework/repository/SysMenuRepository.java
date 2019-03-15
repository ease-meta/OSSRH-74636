package com.open.cloud.workflow.framework.repository;

import com.open.cloud.workflow.framework.entity.SysConfig;
import com.open.cloud.workflow.framework.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Classname: SysMenuRepository
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/15
 * @Version: 1.0
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, Integer> {
}
