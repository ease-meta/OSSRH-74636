package com.open.cloud.mybatis.test;

import com.open.cloud.logging.BizLogger;
import com.open.cloud.logging.BizLoggerFactory;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/25 21:35
 */
@RestController
public class ActAppAppdefController {

    private static final BizLogger logger = BizLoggerFactory.getLogger(ActAppAppdefController.class);
    /*@Resource
    ActAppAppdefMapper actAppAppdefMapper;

    @PostConstruct
    public ActAppAppdef actAppAppdef() {
        ActAppAppdef actAppAppdef = new ActAppAppdef();
        actAppAppdefMapper.deleteById("actAppAppdef.getId()");
        return actAppAppdef;
    }*/
}
