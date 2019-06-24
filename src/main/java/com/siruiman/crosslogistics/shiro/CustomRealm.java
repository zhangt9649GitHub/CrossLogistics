package com.siruiman.crosslogistics.shiro;

import com.siruiman.crosslogistics.model.AdminPermission;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.service.AdminPermissionService;
import com.siruiman.crosslogistics.service.AdminUserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/16 11:23
 */
@Component
public class CustomRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory
            .getLogger(CustomRealm.class);
//    private AdminRolse

    @Autowired
    private AdminUserLoginService adminUserLoginService;

    @Autowired
    private AdminPermissionService adminPermissionService;
    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        AdminUserLoginDto userLoginDto =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(userLoginDto!=null){
            List<AdminPermission> permissions = userLoginDto.getPermissions();
            for (AdminPermission permission : permissions) {
                simpleAuthorInfo.addStringPermission(permission.getPermissions());
            }
            return simpleAuthorInfo;
        }
        AdminUserLoginDto user = (AdminUserLoginDto)principalCollection.getPrimaryPrincipal();
        logger.info(user.getUserName()+"正在授权");
        List<AdminPermission> list = adminPermissionService.selectByUId(Integer.parseInt(user.getAdminUId()));
        for (AdminPermission a:list) {
            simpleAuthorInfo.addStringPermission(a.getPermissions());
        }
        logger.info(user.getUserName()+"授权成功");
        return simpleAuthorInfo;
    }

    @Override
    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("正在进行登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        AdminUserLoginDto user = adminUserLoginService.selectByName(token.getUsername());

        if (user==null){
          return null;
        }else{
            //      如果查出来的不为空就比对密码

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
            super.clearCachedAuthenticationInfo(info.getPrincipals());
            if (user.getStatus()!=0){
                List<AdminPermission> list = adminPermissionService.selectByUId(Integer.parseInt(user.getAdminUId()));
                user.setPermissions(list);
            }
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            return info;
        }


    }
}
