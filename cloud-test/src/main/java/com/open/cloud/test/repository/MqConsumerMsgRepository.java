package com.open.cloud.test.repository;

import com.open.cloud.test.entity.MqConsumerMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName MqConsumerMsgRepository
 * @Author leijian
 * @Date 2019/6/7 12:38
 * @Description TODO
 * @Version 1.0
 **/
@Repository
public interface MqConsumerMsgRepository extends JpaRepository<MqConsumerMsgEntity,String> {
}
