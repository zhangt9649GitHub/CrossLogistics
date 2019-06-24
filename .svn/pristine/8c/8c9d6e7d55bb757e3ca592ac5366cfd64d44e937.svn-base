package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AddAppUserDto;
import com.siruiman.crosslogistics.model.dto.AppUserDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AppUserService;
import com.siruiman.crosslogistics.service.AppUserWalletService;
import com.siruiman.crosslogistics.service.AppUserWalletStreamService;
import com.siruiman.crosslogistics.service.WithdrawApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api(value="AppUser",description = "用户列表",tags={"用户管理-用户列表"})
@RestController
@RequestMapping("/appUser")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private WithdrawApplicationService withdrawApplicationService;
    @Autowired
    private AppUserWalletStreamService appUserWalletStreamService;
    @Autowired
    private AppUserWalletService appUserWalletService;

    @ApiOperation(value = "获取app用户列表",notes = "selectAppUserAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectAppUserAll",method = RequestMethod.GET)
    public LayuiCommonResponse selectAppUserAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                AppUserDto appUserDto) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<AppUser> selectAppUserAll = appUserService.selectAppUserAll(appUserDto);
            Integer count = appUserService.count(appUserDto);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectAppUserAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取app用户详情",notes = "appUserDetail",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/appUserDetail",method = RequestMethod.GET)
    public CommonResponse appUserDetail(int appUserId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            AppUser selectAppUserDetail = appUserService.selectAppUserDetail(appUserId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUserDetail);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "添加APP用户",notes = "addAppUser",tags={"@郭阳"})
    @RequestMapping(value = "/addAppUser",method = RequestMethod.POST)
    public CommonResponse addAppUser(@RequestBody AddAppUserDto addAppUserDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            String userName = addAppUserDto.getUserName().trim();
            Integer count = appUserService.selectCountAppUserName(userName);
            if(count>0){
                commonResponse.setCode(HtCode.FAIL_REPEAT_USERNAME.getCode());
                commonResponse.setMessage(HtCode.FAIL_REPEAT_USERNAME.getInfo());
                return commonResponse;
            }
            Integer addAppUser = appUserService.addAppUser(addAppUserDto);
            if(addAppUser < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
                return commonResponse;
            }
            AppUserWallet appUserWallet = new AppUserWallet();
            appUserWallet.setAppUserId(addAppUserDto.getAppUserId());
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

    @ApiOperation(value = "删除app用户",notes = "deleteAppUser",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/deleteAppUser",method = RequestMethod.GET)
    public CommonResponse deleteAppUser(int appUserId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteAppUser = appUserService.deleteAppUser(appUserId);
            if(deleteAppUser < 1){
                commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
                commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_DELETE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
            commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "启用禁用APP用户",notes = "editAppUserStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="status", value="用户状态",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editAppUserStatus",method = RequestMethod.POST)
    public CommonResponse editAppUserStatus(int appUserId, String status) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editAppUserStatus = appUserService.editAppUserStatus(appUserId, status);
            if(editAppUserStatus < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "获取编辑app用户信息",notes = "selectEditAppUserDetail",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectEditAppUserDetail",method = RequestMethod.GET)
    public CommonResponse selectEditAppUserDetail(int appUserId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            AddAppUserDto selectEditAppUserDetail = appUserService.selectEditAppUserDetail(appUserId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectEditAppUserDetail);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑APP用户",notes = "editAppUser",tags={"@郭阳"})
    @RequestMapping(value = "/editAppUser",method = RequestMethod.POST)
    public CommonResponse editAppUser(@RequestBody AddAppUserDto addAppUserDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            String userName = addAppUserDto.getUserName().trim();
            Integer count = appUserService.selectCountAppUserName(userName);
            if(count>0){
                commonResponse.setCode(HtCode.FAIL_REPEAT_USERNAME.getCode());
                commonResponse.setMessage(HtCode.FAIL_REPEAT_USERNAME.getInfo());
                return commonResponse;
            }
            Integer editAppUser = appUserService.editAppUser(addAppUserDto);
           /* if(editAppUser == null){
                commonResponse.setCode(GyCode.ERROR_PASSWORD.getCode());
                commonResponse.setData(editAppUser);
                commonResponse.setMessage(GyCode.ERROR_PASSWORD.getInfo());
                return commonResponse;
            }*/
            if(editAppUser < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setData(editAppUser);
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setData(editAppUser);
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询用户钱包数据和完成订单数",notes = "appUserWalletAndOrderNum",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="userType", value="小车/货车",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/appUserWalletAndOrderNum",method = RequestMethod.POST)
    public CommonResponse selectAppUserWalletAndOrderNum(int appUserId, String userType) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AppUserWalletAndOrderNum selectAppUserWalletAndOrderNum = appUserService.selectAppUserWalletAndOrderNum(appUserId, userType);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUserWalletAndOrderNum);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询用户订单记录",notes = "orderRecordByCarUser",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="userType", value="小车/货车",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/orderRecordByCarUser",method = RequestMethod.GET)
    public LayuiCommonResponse selectOrderRecordByCarUser(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                     int appUserId, String userType) {
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<OrderRecord> selectOrderRecordByUser = appUserService.selectOrderRecordByUser(appUserId, userType);

            if (userType.equals("小车")){
                Integer countOrderRecordByCarUser = appUserService.countOrderRecordByCarUser(appUserId);
                layuiCommonResponse.setCount(countOrderRecordByCarUser);
            }
            if(userType.equals("货车")){
                Integer countOrderRecordByTruckUser = appUserService.countOrderRecordByTruckUser(appUserId);
                layuiCommonResponse.setCount(countOrderRecordByTruckUser);
            }

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectOrderRecordByUser);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取余额提现记录",notes = "获取余额提现记录",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="userType", value="提现角色小车、货车",paramType="query",required = true,dataType="String"),
            @ApiImplicitParam(name="userId", value="app用户id",paramType="query",required = true,dataType="int"),
    })
    @RequestMapping(value = "/getWithdrawApplicationList",method= RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getWithdrawApplicationList(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                          @Validated String userType,@Validated int userId){
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page,limit);
            List<WithdrawApplication> withdrawApplicationList = withdrawApplicationService.selectWithdrawApplicationByUserId(userType,userId);
            int count = withdrawApplicationService.selectCountWithdrawApplicationByUserId(userType,userId);
            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
            layuiCommonResponse.setData(withdrawApplicationList);
            layuiCommonResponse.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }
        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取积分余额记录",notes = "获取积分余额记录",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="userType", value="用户类型",paramType="query",required = true,dataType="String"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",required = true,dataType="int"),
    })
    @RequestMapping(value = "/getWalletIntegral",method= RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getWalletIntegral(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit, @Validated String userType,@Validated int appUserId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page,limit);
            List<AppUserWalletStream> appUserWalletStreamList = appUserWalletStreamService.selectWalletIntegralStream(userType,appUserId);
            int count = appUserWalletStreamService.selectCountWalletIntegralStream(userType,appUserId);
            response.setCode(GyCode.SUCCESS_GET.getCode());
            response.setMsg(GyCode.SUCCESS_GET.getInfo());
            response.setData(appUserWalletStreamList);
            response.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(GyCode.FAIL_GET.getCode());
            response.setMsg(GyCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取我的钱包交易记录",notes = "获取我的钱包交易记录",tags={"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="userType", value="用户类型",paramType="query",required = true,dataType="String"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",required = true,dataType="int"),
    })
    @RequestMapping(value = "/getWalletStream",method= RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getWalletStream(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit, @Validated String userType, @Validated int appUserId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page,limit);
            List<AppUserWalletStream> appUserWalletStreamList = appUserWalletStreamService.selectWalletStream(userType,appUserId);
            int count =appUserWalletStreamService.selectCountWalletStream(userType,appUserId);
            response.setCode(GyCode.SUCCESS_GET.getCode());
            response.setMsg(GyCode.SUCCESS_GET.getInfo());
            response.setData(appUserWalletStreamList);
            response.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(GyCode.FAIL_GET.getCode());
            response.setMsg(GyCode.FAIL_GET.getInfo());
        }
        return response;
    }

    /*@ApiOperation(value = "验证用户真实姓名",notes = "checkActualName",tags={"@郭阳"})
    @ApiImplicitParam(name="actualName", value="app用户真实姓名",paramType="query",dataType="String")
    @RequestMapping(value = "/checkActualName",method = RequestMethod.POST)
    public CommonResponse checkActualName(String actualName) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer checkActualName = appUserService.checkActualName(actualName);
            if(checkActualName > 0){
                commonResponse.setCode(GyCode.FAIL_USER_NAME.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_NAME.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_USER_NAME.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_USER_NAME.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_USER_NAME.getCode());
            commonResponse.setMessage(GyCode.FAIL_USER_NAME.getInfo());
        }

        return commonResponse;
    }*/
}
