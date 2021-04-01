package com.learn.controller;

import com.learn.model.Student;
import com.learn.service.DroolsExecuteService;
import com.learn.service.KieUpdateService;
import com.learn.util.BaseResponse;
import com.learn.util.KieSessionType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: .
 *
 * @author : ys.
 * @date :
 */
@RestController
@RequestMapping("/drools")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private KieUpdateService kieUpdateService;

    @Autowired
    private DroolsExecuteService executeService;


    @ApiOperation(value = "执行规则引擎")
    @GetMapping(value = "/executeDrools")
    @ResponseBody
    public BaseResponse<String> executeDrools() {

        BaseResponse<String> result = new BaseResponse<>();

        List<Object> paramlist = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        student.setName("小明");
        paramlist.add(student);

        executeService.configDataContainGlobal(paramlist, KieSessionType.FIRST);
        executeService.configData(paramlist, KieSessionType.SECOND);

        result.setMessage(student.toString());
        return result;

    }

    @ApiOperation("动态更新规则")
    @RequestMapping(value = "/updateKieSession", method = RequestMethod.POST,
            headers = "content-type=multipart/form-data")
    public BaseResponse<String> updateKieSession(@RequestParam(value = "type") @ApiParam("规则文件类型") String type,
                                                 @RequestBody MultipartFile[] requestList) throws Exception {

        BaseResponse<String> result = new BaseResponse<>();
        InputStream stream = null;
        try {
            List<String> stringList = new ArrayList<>();
            for (MultipartFile request : requestList) {
                stream = request.getInputStream();
                stringList.add(kieUpdateService.readDrlByStream(stream));
            }
            result = kieUpdateService.updateByFile(type, stringList);
        } catch (IOException e) {
            LOGGER.error("Read file failed!");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ec) {
                    LOGGER.error("stream close error");
                }
            }
        }
        return result;
    }
}
