package com.learn.service;

import com.learn.util.BaseResponse;
import com.learn.util.KieSessionType;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.conf.MultithreadEvaluationOption;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description: .
 *
 * @author : ys.
 * @date :
 */
@Service
public class KieUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KieUpdateService.class);

    @Autowired
    KieContainerService kieContainerService;


    /**
     * Description: 输入文件转为字符串.
     *
     * @param inputStream :
     * @author : ys.
     * @return: java.lang.String
     **/
    public String readDrlByStream(InputStream inputStream) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            return bos.toString();
        } catch (IOException e) {
            LOGGER.error("Read file error");
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                LOGGER.error("Close stream error");
            }
        }
        return null;
    }


    /**
     * Description: 使用转为字符串的drl文件,更新KieSession.
     *
     * @param session    : 指定需要更新的KieSession类型
     * @param drlStrList : 字符串格式的DRL文件
     * @author : ys.
     * @return:
     **/
    public BaseResponse<String> updateByFile(String session, List<String> drlStrList) {
        BaseResponse<String> response = new BaseResponse<>();
        if (updateKieSession(session, drlStrList)) {
            response.setMessage("Success to update " + session + " kieSession");
        } else {
            response.setMessage("Failed to update " + session + " kieSession");
        }
        return response;
    }


    /**
     * Description: 更新KieSession.
     *
     * @param session    :
     * @param drlStrList :
     * @author : ys.
     * @return: boolean
     **/
    public boolean updateKieSession(String session, List<String> drlStrList) {
        //1.生成新的KieSession
        KieHelper helper = new KieHelper();
        //添加传入规则
        for (String str : drlStrList) {
            helper.addContent(str, ResourceType.DRL);
        }
        Results results = helper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                LOGGER.error("规则语法异常: {}", message.getText());
            }
            throw new IllegalStateException("规则文件语法错误!");
        }
        //生成StatelessKieSession对象,方法一
        //KieBaseConfiguration configuration = helper.ks.newKieBaseConfiguration();
        //configuration.setOption(EventProcessingOption.STREAM);
        //StatelessKieSession kieSession = helper.build(configuration).newStatelessKieSession();
        //方法二
        StatelessKieSession kieSession = helper.build(MultithreadEvaluationOption.YES).newStatelessKieSession();

        //2.校验Kiesession
        KieSessionType kieSessionType = KieSessionType.getVaule(session);
        if (kieSessionType.equals(KieSessionType.OTHER)) {
            LOGGER.error("Session名称错误: " + session);
            return false;
        }

        //3.更新Kiesession
        kieContainerService.updateKieSession(kieSession, kieSessionType);

        return true;
    }

}
