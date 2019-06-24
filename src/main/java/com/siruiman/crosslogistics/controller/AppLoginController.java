package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.AppUserWallet;
import com.siruiman.crosslogistics.model.dto.AppUserLoginDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.AppLoginService;
import com.siruiman.crosslogistics.service.AppUserWalletService;
import com.siruiman.crosslogistics.util.MD5Util;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import com.siruiman.crosslogistics.util.SmsCodeUtil;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;


@Api(value="AppLogin",description = "登录注册",tags={"APP用户端/货车端-登录注册"})
@RestController
@RequestMapping("/appLogin")
public class AppLoginController {
    @Autowired
    private AppLoginService appLoginService;
    @Autowired
    private AppUserWalletService appUserWalletService;


    @ApiOperation(value = "密码登录(普通用户、小车用户、货车用户)",notes = "appLoginByPsw",tags={"@郭阳"})
    @RequestMapping(value = "/appLoginByPsw",method = RequestMethod.POST)
    public CommonResponse appLoginByPsw(AppUserLoginDto appUserLoginDto) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            AppUser selectAppUser = appLoginService.selectAppUser(appUserLoginDto);
            if(selectAppUser == null){
                commonResponse.setCode(GyCode.PHONE_ERROR.getCode());
                commonResponse.setMessage(GyCode.PHONE_ERROR.getInfo());
                return commonResponse;
            }
            String password = MD5Util.encrypt(appUserLoginDto.getPassword());
            if(!selectAppUser.getPassword().equals(password)){
                commonResponse.setCode(GyCode.PASSWORD_ERROR.getCode());
                commonResponse.setMessage(GyCode.PASSWORD_ERROR.getInfo());
                return commonResponse;
            }
            if(selectAppUser.getStatus().equals("禁用")){
                commonResponse.setCode(GyCode.USER_STATUS_ERROR.getCode());
                commonResponse.setMessage(GyCode.USER_STATUS_ERROR.getInfo());
                return commonResponse;
            }
            if(selectAppUser.getStatus().equals("删除")){
                commonResponse.setCode(GyCode.USER_NOT_EXIST.getCode());
                commonResponse.setMessage(GyCode.USER_NOT_EXIST.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_LOGIN.getCode());
            HashMap<String, Object> map = new HashMap<>();
            map.put("user",selectAppUser);
            map.put("STC",StaticConfigUtil.STC);
            commonResponse.setData(map);
            commonResponse.setMessage(GyCode.SUCCESS_LOGIN.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "发送验证码",notes = "sendCode",tags={"@郭阳"})
    @ApiImplicitParams({
        @ApiImplicitParam(name="phone", value="手机号",paramType="query",dataType="String"),
        @ApiImplicitParam(name="country", value="国家手机编号",paramType="query",dataType="String")
    })
    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    public CommonResponse sendCode(String phone, String country) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            String code = SmsCodeUtil.getCode();
            int result = 1;
            if(country.equals("+86")){
                result = SmsCodeUtil.chinaSend(country+phone, code);
            }
            if(country.equals("+65")){
                result = SmsCodeUtil.singleSend(country+phone, code);
            }
            if(result != 0){
                commonResponse.setCode(GyCode.ERROR_SEND_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_SEND_CODE.getInfo());
                return commonResponse;
            }

            String selectCode = appLoginService.selectCode(phone);
            if(selectCode == null){
                Integer insertCode = appLoginService.insertCode(phone, code);
                commonResponse.setCode(GyCode.SUCCESS_SEND_CODE.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_SEND_CODE.getInfo());
                return commonResponse;
            }else{
                Integer editCode = appLoginService.editCode(phone, code);

                commonResponse.setCode(GyCode.SUCCESS_SEND_CODE.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_SEND_CODE.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.ERROR_SEND_CODE.getCode());
            commonResponse.setMessage(GyCode.ERROR_SEND_CODE.getInfo());
        }

        return commonResponse;
    }


    @ApiOperation(value = "验证码登录",notes = "codeLogin",tags={"@郭阳"})
    @RequestMapping(value = "/codeLogin",method = RequestMethod.POST)
    public CommonResponse codeLogin(AppUserLoginDto appUserLoginDto) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            /*String code = "123456";
            if(!code.equals(appUserLoginDto.getCode())){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }*/
            String selectCode = appLoginService.selectCode(appUserLoginDto.getPhone());
            if(!selectCode.equals(appUserLoginDto.getCode())){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }

            AppUser selectAppUser = appLoginService.selectAppUser(appUserLoginDto);
            if(selectAppUser == null){
                commonResponse.setCode(GyCode.PHONE_ERROR.getCode());
                commonResponse.setMessage(GyCode.PHONE_ERROR.getInfo());
                return commonResponse;
            }
            if(selectAppUser.getStatus().equals("禁用")){
                commonResponse.setCode(GyCode.USER_STATUS_ERROR.getCode());
                commonResponse.setMessage(GyCode.USER_STATUS_ERROR.getInfo());
                return commonResponse;
            }
            if(selectAppUser.getStatus().equals("删除")){
                commonResponse.setCode(GyCode.USER_NOT_EXIST.getCode());
                commonResponse.setMessage(GyCode.USER_NOT_EXIST.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_LOGIN.getCode());
            HashMap<String, Object> map = new HashMap<>();
            map.put("user",selectAppUser);
            map.put("STC",StaticConfigUtil.STC);
            commonResponse.setData(map);
            commonResponse.setMessage(GyCode.SUCCESS_LOGIN.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "忘记密码",notes = "forgetPassword",tags={"@郭阳"})
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    public CommonResponse forgetPassword(AppUserLoginDto appUserLoginDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            /*String code = "123456";
            if(!code.equals(appUserLoginDto.getCode())){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }*/
            String selectCode = appLoginService.selectCode(appUserLoginDto.getPhone());
            if(!selectCode.equals(appUserLoginDto.getCode())){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }
            appUserLoginDto.setNewPassword(MD5Util.encrypt(appUserLoginDto.getNewPassword()));
            Integer editPsw = appLoginService.editPsw(appUserLoginDto);
            if(editPsw < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "注册账号",notes = "loginAccount",tags={"@郭阳"})
    @RequestMapping(value = "/loginAccount",method = RequestMethod.POST)
    public CommonResponse loginAccount(AppUserLoginDto appUserLoginDto) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            AppUser selectAppUser = appLoginService.selectAppUser(appUserLoginDto);
            if(selectAppUser != null){
                commonResponse.setCode(GyCode.USER_ERROR.getCode());
                commonResponse.setMessage(GyCode.USER_ERROR.getInfo());
                return commonResponse;
            }
            /*String code = "123456";
            if(!code.equals(appUserLoginDto.getCode())){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }*/
            String selectCode = appLoginService.selectCode(appUserLoginDto.getPhone());
            if(selectCode == null){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }
            if(!selectCode.equals(appUserLoginDto.getCode())){
                commonResponse.setCode(GyCode.ERROR_CODE.getCode());
                commonResponse.setMessage(GyCode.ERROR_CODE.getInfo());
                return commonResponse;
            }

            appUserLoginDto.setNumber("13" + RandomCodeUtil.getSixRandomCode());
            appUserLoginDto.setPassword(MD5Util.encrypt(appUserLoginDto.getPassword()));
            Integer addAppUser = appLoginService.addAppUser(appUserLoginDto);
            if(addAppUser < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
                return commonResponse;
            }

            AppUserWallet appUserWallet = new AppUserWallet();
            appUserWallet.setAppUserId(appUserLoginDto.getAppUserId());
            appUserWallet.setCommissionAmount(new BigDecimal(0));
            appUserWallet.setIntegral(0);
            appUserWallet.setUserType("小车");
            appUserWalletService.insertAppUserWallet(appUserWallet);
            appUserWallet.setUserType("货车");
            appUserWalletService.insertAppUserWallet(appUserWallet);
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_ADD.getCode());
            commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
        }

        return commonResponse;
    }
}
