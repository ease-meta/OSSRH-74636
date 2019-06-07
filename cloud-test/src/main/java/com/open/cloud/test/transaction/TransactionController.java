package com.open.cloud.test.transaction;

import com.open.cloud.test.repository.MqConsumerMsgRepository;
import com.open.cloud.test.repository.MqProducerMsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/7 12:47
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TransactionController {

    @Autowired
    MqConsumerMsgRepository mqConsumerMsgRepository;

    @Autowired
    MqProducerMsgRepository mqProducerMsgRepository;

    @Resource
    private PlatformTransactionManager transactionManager;

    private final TransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    @RequestMapping(value = {"/transaction"}, method = {RequestMethod.GET})
    public boolean createProcessInstance(HttpServletRequest httpRequest, HttpServletResponse response) {

        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            mqConsumerMsgRepository.deleteAll();
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return true;
    }
}
