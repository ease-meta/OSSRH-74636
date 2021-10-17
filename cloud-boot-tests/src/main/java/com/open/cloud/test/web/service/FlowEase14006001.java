package com.open.cloud.test.web.service;

import cn.hutool.db.Page;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.flow.base.AbstractProcess;
import com.open.cloud.core.flow.base.SpringApplicationContext;
import com.open.cloud.test.web.controler.Hello;
import com.open.cloud.test.web.module.Ease14006001In;
import com.open.cloud.test.web.module.Ease14006001Out;
import com.open.cloud.test.web.mybatis.demo.entity.SysUser;
import com.open.cloud.test.web.mybatis.demo.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 18:13
 */
@Service
public class FlowEase14006001 extends AbstractProcess<Ease14006001In, Ease14006001Out> {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    @Override
    public Ease14006001Out process(Ease14006001In request) {
        Hello hello = SpringApplicationContext.getContext().getBean(Hello.class);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(request);


        PageInfo<SysUser> p = PageHelper.startPage(1, 3).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", "9999");
                hashMap.put("page", new Page());
                sysUserMapper.deleteByPage(hashMap);
            }
        });
        Ease14006001Out ease14006001Out = new Ease14006001Out();
        ease14006001Out.setName(request.getName());
        return ease14006001Out;
    }

    @Override
    public Class<? extends BaseRequest> getRequestModel() {
        return Ease14006001In.class;
    }
}
