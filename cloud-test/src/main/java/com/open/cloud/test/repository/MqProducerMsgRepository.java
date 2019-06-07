package com.open.cloud.test.repository;

import com.open.cloud.test.entity.MqProducerMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName MqProducerMsgRepository
 * @Author leijian
 * @Date 2019/6/7 12:39
 * @Description TODO
 * @Version 1.0
 **/
@Repository
public interface MqProducerMsgRepository extends JpaRepository<MqProducerMsgEntity,String> {
}
