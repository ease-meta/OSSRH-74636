package com.open.cloud.workflow.framework.controller;


import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.ActHiActinst;
import com.open.cloud.workflow.framework.serviceImpl.ActHiActinstServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 历史行为表信息 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Controller
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActHiActinstController extends BaseController<ActHiActinst> {

    @Autowired
    ActHiActinstServiceImpl actHiActinstService;

    @Resources(auth = AuthTypeEnum.OPEN, description = "流程实例历史执行信息")
    @ResponseBody
    @GetMapping(value = {"/runtime/acthiinst/{procInstId}"})
    public Responses< List<ActHiActinst>> getActHiActinst(@PathVariable String procInstId, HttpServletRequest request) {
        ActHiActinst actHiActinst = new ActHiActinst();
        actHiActinst.setProcInstId(procInstId);
        List<ActHiActinst> actHiActinstList = actHiActinstService.selectSelective(actHiActinst);
        return responses(HttpStatus.OK,actHiActinstList);
    }

    @Resources(auth = AuthTypeEnum.OPEN, description = "流程实例历史执行信息")
    @ResponseBody
    @PostMapping(value = {"/hello/{name}"})
    public Responses<Void> hello(@PathVariable String name, HttpServletRequest request) {
      System.out.println(name);
      return responses(HttpStatus.OK);
    }


}

