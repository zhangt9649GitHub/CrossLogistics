package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.AdminUser;
import com.siruiman.crosslogistics.model.AppUserCertification;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.dto.AdminUserDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AdminUserService;
import com.siruiman.crosslogistics.service.AppUserCertificationService;
import com.siruiman.crosslogistics.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "AppUserCertification", description = "用户类型审批", tags = {"用户管理-用户类型审批"})
@RestController
@RequestMapping("/admin/user")
public class AppUserCertificationController {
    @Autowired
    private AppUserCertificationService appUserCertificationService;

    @ApiOperation(value = "获取审核列表", notes = "获取审核列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userTrueName", value = "真实姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userType", value = "审核类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "number", value = "用户编号", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getAppUserCertificationList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getAppUserCertificationList"})
    public LayuiCommonResponse getAppUserCertificationList(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                           String userTrueName, String userType, String number) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<AppUserCertification> appUserCertificationList = appUserCertificationService.selectAppUserCertificationList(userTrueName, userType, number);
            int count = appUserCertificationService.selectCountAppUserCertificationList(userTrueName, userType, number);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(appUserCertificationList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "查看审核用户信息", notes = "获取审核列表", tags = {"@郝腾"})
    @ApiImplicitParam(name = "certificationId", value = "app用户审核id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getAppUserCertificationDetails", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getAppUserCertificationDetails"})
    public LayuiCommonResponse getAppUserCertificationDetails(@Validated int certificationId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            AppUserCertification appUserCertification = appUserCertificationService.selectAppUserCertificationDetails(certificationId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(appUserCertification);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "审核通过或驳回", notes = "审核通过或驳回", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "certificationId", value = "app用户审核id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userCertificationStatus", value = "认证状态", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "dismissExplain", value = "驳回说明", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/updateUserCertificationStatus", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateUserCertificationStatus"})
    public LayuiCommonResponse updateUserCertificationStatus(@Validated int certificationId, @Validated String userCertificationStatus, @Validated String dismissExplain) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            appUserCertificationService.updateUserCertificationStatus(certificationId, userCertificationStatus, dismissExplain);
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMsg(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMsg(HtCode.FAIL_UPDATE.getInfo());
            return response;
        }
        return response;
    }
}
