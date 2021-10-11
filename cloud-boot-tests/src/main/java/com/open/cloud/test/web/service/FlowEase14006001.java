package com.open.cloud.test.web.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.flow.base.AbstractProcess;
import com.open.cloud.test.web.module.Ease14006001In;
import com.open.cloud.test.web.module.Ease14006001Out;
import com.open.cloud.test.web.mybatis.demo.entity.SysUser;
import com.open.cloud.test.web.mybatis.demo.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        PageInfo<SysUser> p = PageHelper.startPage(1, 3).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                sysUserMapper.selectByPage();
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
