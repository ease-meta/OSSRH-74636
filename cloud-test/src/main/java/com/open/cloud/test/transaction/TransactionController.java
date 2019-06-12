package com.open.cloud.test.transaction;

import com.open.cloud.common.sequences.UidGeneratorFactory;
import com.open.cloud.test.repository.MqConsumerMsgRepository;
import com.open.cloud.test.repository.MqProducerMsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/7 12:47
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TransactionController {

    private final static String NAME_SPACE = "com.open.cloud.prod";
    @Autowired
    MqConsumerMsgRepository mqConsumerMsgRepository;

    @Autowired
    MqProducerMsgRepository mqProducerMsgRepository;

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Resource
    private PlatformTransactionManager transactionManager;

    private final TransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    @RequestMapping(value = {"/transaction/jpa"}, method = {RequestMethod.GET})
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


    @RequestMapping(value = {"/transaction/mybatis"}, method = {RequestMethod.GET})
    public boolean mybatis(HttpServletRequest httpRequest, HttpServletResponse response) {
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            HashMap accountsMap = new HashMap();
            accountsMap.put("ACCOUNT_NO", String.valueOf(UidGeneratorFactory.getInstance(0).getKey()));
            accountsMap.put("AMOUNT", 2D);
            accountsMap.put("FREEZED_AMOUNT", 1D);
            sqlSessionFactory.openSession().insert("InsAccount", accountsMap);

            HashMap accountPoints = new HashMap();
            //accountPoints.put("TX_ID", String.valueOf(UidGeneratorFactory.getInstance(0).getKey()));
            accountPoints.put("ACCOUNT_NO", accountsMap.get("ACCOUNT_NO"));
            accountPoints.put("POINT", 1D);
            accountPoints.put("STATUS", 1);
            sqlSessionFactory.openSession().insert("InsAccountPoint", accountPoints);


            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return true;
    }


    @RequestMapping(value = {"/transaction/tcc"}, method = {RequestMethod.GET})
    @Transactional(propagation = REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean tcc(HttpServletRequest httpRequest, HttpServletResponse response) {
        try {
            HashMap accountsMap = new HashMap();
            accountsMap.put("ACCOUNT_NO", String.valueOf(UidGeneratorFactory.getInstance(0).getKey()));
            accountsMap.put("AMOUNT", 2D);
            accountsMap.put("FREEZED_AMOUNT", 1D);
            sqlSessionFactory.openSession().insert("InsAccount", accountsMap);

            HashMap accountPoints = new HashMap();
            //accountPoints.put("TX_ID", String.valueOf(UidGeneratorFactory.getInstance(0).getKey()));
            accountPoints.put("ACCOUNT_NO", accountsMap.get("ACCOUNT_NO"));
            accountPoints.put("POINT", 1D);
            accountPoints.put("STATUS", 1);
            sqlSessionFactory.openSession().insert("InsAccountPoint", accountPoints);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return true;
    }
}
