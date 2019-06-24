package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.Staff;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.PDALoginService;
import com.siruiman.crosslogistics.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value="PDALogin",description = "登录",tags={"PDA登录-登录"})
@RestController
@RequestMapping("/pdaLogin")
public class PDALoginController {
    @Autowired
    private PDALoginService pdaLoginService;

    @ApiOperation(value = "PDA登录",notes = "pdaLogin",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName", value="用户名",paramType="query",dataType="String"),
            @ApiImplicitParam(name="password", value="密码",paramType="query",dataType="String")
    })
    @RequestMapping(value = "/pdaLogin",method = RequestMethod.POST)
    public CommonResponse appLoginByPsw(String userName, String password) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Staff selectStaff = pdaLoginService.selectStaff(userName);
            if(selectStaff == null){
                commonResponse.setCode(GyCode.ERROR_USER_NAME.getCode());
                commonResponse.setMessage(GyCode.ERROR_USER_NAME.getInfo());
                return commonResponse;
            }
            String psd = MD5Util.encrypt(password);
            if(!selectStaff.getPassword().equals(psd)){
                commonResponse.setCode(GyCode.PASSWORD_ERROR.getCode());
                commonResponse.setMessage(GyCode.PASSWORD_ERROR.getInfo());
                return commonResponse;
            }
            int status = selectStaff.getStatus();
            if(status == 0){
                commonResponse.setCode(GyCode.USER_STATUS_ERROR.getCode());
                commonResponse.setMessage(GyCode.USER_STATUS_ERROR.getInfo());
                return commonResponse;
            }
            if(status == -1){
                commonResponse.setCode(GyCode.ERROR_USER_NAME.getCode());
                commonResponse.setMessage(GyCode.ERROR_USER_NAME.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_LOGIN.getCode());
            commonResponse.setData(selectStaff);
            commonResponse.setMessage(GyCode.SUCCESS_LOGIN.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

}
