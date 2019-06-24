package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.AdminPermission;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AdminPermissionService;
import com.siruiman.crosslogistics.service.AdminUserLoginService;
import com.siruiman.crosslogistics.service.impl.AdminPermissionServiceImpl;
import com.siruiman.crosslogistics.util.CLientIpUtils;
import com.siruiman.crosslogistics.util.DateUtil;
import com.siruiman.crosslogistics.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.logging.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/5 17:11
 */
@RestController
@RequestMapping("adminlogin")
@Api(value = "AdminLoginController-API",description = "后台管理登录-api",tags = {"后台登录"})
public class AdminLoginController {

    @Autowired
    private AdminUserLoginService adminUserLoginService;

    @Autowired
    private AdminPermissionService  adminPermissionService;

    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "后台登录",notes = "登录",nickname = "loginAdmin",tags={"@占伟"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName", value="用户名",paramType="query",dataType="String"),
            @ApiImplicitParam(name="password", value="密码",paramType="query",dataType="String"),
            @ApiImplicitParam(name="code", value="验证码",paramType="query",dataType="String"),
    })


    public LayuiCommonResponse loginAdmin(@RequestBody AdminUserLoginDto user, HttpServletRequest request) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        if (user.getUserName()==null&&user.getUserName()==""&&user.getPassword()==null&&user.getPassword()==""){
            response.setMsg(ZwCode.ERROR_LOGIN_NAME_PASSWORD_NULL.getInfo());
            response.setCode(ZwCode.ERROR_LOGIN_NAME_PASSWORD_NULL.getCode());
            return response;
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            user.setPassword(MD5Util.encrypt(user.getPassword()));
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
//            调用登录认证方法

            subject.login(token);
            HttpSession session = request.getSession();
            AdminUserLoginDto us = (AdminUserLoginDto) session.getAttribute("user");
//            设置session过期时间
            session.setMaxInactiveInterval(30*60);
//            如果用户被禁用
            if (us.getStatus()==0){
                response.setMsg(GyCode.USER_STATUS_ERROR.getInfo());
                response.setCode(GyCode.USER_STATUS_ERROR.getCode());
                request.getSession().removeAttribute("user");
                return response;
            }
//            记录用户登录时间ip
//
//           给用户添加权限

            us.setIp(CLientIpUtils.getIp(request));
            us.setLoginTime(DateUtil.getDateTime());
            adminUserLoginService.updateLogin(us);
            response.setCode(GyCode.SUCCESS_LOGIN.getCode());
            response.setMsg(GyCode.SUCCESS_LOGIN.getInfo());
//            用户名不存在

        }catch (UnknownAccountException e){
            response.setMsg(ZwCode.ERROR_LOGIN_NAME_EXCIST.getInfo());
            response.setCode(ZwCode.ERROR_LOGIN_NAME_EXCIST.getCode());
//            密码错误
        }catch (IncorrectCredentialsException e){
            response.setMsg(ZwCode.ERROR_NAME_PASSWORD_EXCIST.getInfo());
            response.setCode(ZwCode.ERROR_NAME_PASSWORD_EXCIST.getCode());
        }

        return response;
    }

    @RequestMapping(value = "/logOutAdmin",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "后台登出",notes = "登出",nickname = "logOutAdmin",tags={"@占伟"})
    public LayuiCommonResponse logOutAdmin() {
        LayuiCommonResponse response = new LayuiCommonResponse();
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            response.setCode(0);
            response.setMsg("退出成功");
        return response;
    }




}
