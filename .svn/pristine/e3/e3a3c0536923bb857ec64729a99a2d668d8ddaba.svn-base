package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.AppUserInfo;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.AppPersonalCenterService;
import com.siruiman.crosslogistics.service.AppUserService;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value="AppPersonalCenter",description = "个人中心",tags={"APP用户端/货车端-个人中心"})
@RestController
@RequestMapping("/appPersonalCenter")
public class AppPersonalCenterController {
    @Autowired
    private AppPersonalCenterService appPersonalCenterService;
    @Autowired
    private AppUserService appUserService;

    @ApiOperation(value = "个人资料",notes = "appUserInfo",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="String"),
            @ApiImplicitParam(name="userType", value="用户类型 （货车、小车、普通用户）",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/appUserInfo",method = RequestMethod.GET)
    public CommonResponse appUserInfo(int appUserId, String userType) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AppUserInfo appUserInfo = new AppUserInfo();
            if(userType.equals("普通用户")){
                appUserInfo = appPersonalCenterService.selectAppUserInfoByPt(appUserId);
            }else {
                appUserInfo = appPersonalCenterService.selectAppUserInfo(appUserId, userType);
            }
            appUserInfo.setSTC(StaticConfigUtil.STC);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(appUserInfo);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑个人资料",notes = "editAppUserInfo",tags={"@郭阳"})
    @RequestMapping(value = "/editAppUserInfo",method = RequestMethod.POST)
    public CommonResponse editAppUserInfo(AppUserInfo appUserInfo) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            String userName = appUserInfo.getUserName().trim();
            Integer count = appUserService.selectCountAppUserName(userName);
            if(count>0){
                commonResponse.setCode(HtCode.FAIL_REPEAT_USERNAME.getCode());
                commonResponse.setMessage(HtCode.FAIL_REPEAT_USERNAME.getInfo());
                return commonResponse;
            }
            Integer editAppUserInfo = appPersonalCenterService.editAppUserInfo(appUserInfo);
            if(editAppUserInfo < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }
}
