package com.open.cloud.workflow.framework.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.cloud.workflow.framework.serviceImpl.ActTokenServiceImpl;

/**
 * @author leijian
 * @date 2019年1月26日
 */
@Service
@Slf4j
public class JWTRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Autowired
    ActTokenServiceImpl actTokenService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //Integer uid = JWTUtils.getUid(principals.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // TODO
        // Set<String> roleIds =
        // userService.getRoleIds(uid).stream().map(TypeUtils::castToString).collect(Collectors.toSet());
        // simpleAuthorizationInfo.addRoles(roleIds);
        // simpleAuthorizationInfo.addStringPermissions(resourceService.getUserPerms(uid));
        return simpleAuthorizationInfo;
    }

    /**
     * @throws org.apache.shiro.authc.AuthenticationException if there is any problem during the authentication process - see the
     *                                 interface's JavaDoc for a more detailed explanation.
     * @see org.apache.shiro.authc.AbstractAuthenticator
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getPrincipal();
        log.info("token 信息判断是否过期[{}],用户ID[{}],过期时间{}", JWTUtils.isExpired(token), JWTUtils.getUid(token), JWTUtils.getExpiration(token));
        String uuid = JWTUtils.getUid(token);
        if (JWTUtils.isExpired(token)) {
            log.info("token过期，重新申请");
            //throw new AuthenticationException("token过期，重新申请");
        }
        if (actTokenService.existsById(uuid)) {
            log.info("系统id不存在");
            throw new AuthenticationException("系统id不存在");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
