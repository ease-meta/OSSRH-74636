package com.open.cloud.auth.repository;

import com.open.cloud.auth.entity.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * @author Leijian
 * @date   2020/2/8
 */
public interface UserRepository extends JpaRepository<UserPo, Long>, JpaSpecificationExecutor<UserPo> {

	@Query(value = "select * from user where user_name = :name", nativeQuery = true)
	public UserPo findUserByName(@Param("name") String userName);

}