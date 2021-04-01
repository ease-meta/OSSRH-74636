package com.learn.service;

import com.learn.util.KieSessionType;
import org.kie.api.command.Command;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 执行规则引擎.
 *
 * @author : ys.
 * @date :
 **/
@Service
public class DroolsExecuteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsExecuteService.class);

    @Autowired
    KieContainerService kieService;

    public void configData(List<Object> paramlist, KieSessionType type) {

        StatelessKieSession stlkieSession = kieService.getKieSession(type);
        //添加参数
        List<Command> commands = new ArrayList<>();
        for (Object param : paramlist) {
            commands.add(CommandFactory.newInsert(param));
        }
        stlkieSession.execute(CommandFactory.newBatchExecution(commands));
    }


    public void configDataContainGlobal(List<Object> paramlist, KieSessionType type) {

        StatelessKieSession stlkieSession = kieService.getKieSession(type);
        //全局变量
        String str = "匹配成功";
        stlkieSession.setGlobal("str", str);
        //添加参数
        List<Command> commands = new ArrayList<>();
        for (Object param : paramlist) {
            commands.add(CommandFactory.newInsert(param));
        }
        stlkieSession.execute(CommandFactory.newBatchExecution(commands));
    }
}
