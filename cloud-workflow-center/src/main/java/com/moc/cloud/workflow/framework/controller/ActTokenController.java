package com.moc.cloud.workflow.framework.controller;

import com.moc.cloud.workflow.common.enums.AuthTypeEnum;
import com.moc.cloud.workflow.common.responses.Responses;
import com.moc.cloud.workflow.framework.annotation.Resources;
import com.moc.cloud.workflow.framework.config.BaseController;
import com.moc.cloud.workflow.framework.entity.ActToken;
import com.moc.cloud.workflow.framework.serviceImpl.ActTokenServiceImpl;
import com.moc.cloud.workflow.framework.shiro.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Leijian
 * @date 2019年1月28日
 */
@Controller
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActTokenController extends BaseController<Object> {

    @Resource
    ActTokenServiceImpl actTokenService;

    @ResponseBody
    @Resources(auth = AuthTypeEnum.OPEN, description = "获取token")
    @PostMapping(value = "${api.version}/token/{uid}")
    public Responses<Object[]> getToken(@PathVariable @Validated @NotNull String uid) {
        ActToken tokenDTO = new ActToken();
        String token = JWTUtils.generate(uid);
        log.info("前端传入的系统id[{}],生成的对应的token[{}]", uid, token);
        tokenDTO.setToken(token);
        tokenDTO.setUuid(uid);
        tokenDTO.setTime(LocalDateTime.now());
        actTokenService.save(tokenDTO);
        Object[] strArray={token,JWTUtils.getExpiration(token)};
        return responses(HttpStatus.OK,strArray);
    }

    @RequestMapping({"/", "/index"})
    @ApiIgnore
    public String index() {
        return "redirect:swagger-ui.html";
    }

    @ResponseBody
    @ApiIgnore
    @PostMapping(value = "/account/token")
    public Responses<ActToken> getToken(HttpServletRequest httpRequest, HttpServletResponse response) {
        ActToken tokenDTO = new ActToken();
        tokenDTO.setToken(UUID.randomUUID().toString());
        tokenDTO.setUuid("123");
        return responses(HttpStatus.OK, tokenDTO);
    }
}
