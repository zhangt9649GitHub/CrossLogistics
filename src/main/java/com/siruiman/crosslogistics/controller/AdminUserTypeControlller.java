package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.AdminUserType;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AdminUserTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/3/21 14:05
 *            后台添加三方区别 用户类型
 */
@RequestMapping("adminUserType")
@RestController
@Api(value = "AdminUserType-API",description = "用户类型-api",tags = {"后台用户类型"})
public class AdminUserTypeControlller {

    @Autowired
    private AdminUserTypeService adminUserTypeService;

    @ApiOperation(value = "获取所有用户类型",notes = "用户类型获取做下选",nickname = "getAll",tags={"@张占伟"})

    @RequestMapping(value = "getAllUserType",method = RequestMethod.GET)
    public LayuiCommonResponse getAllUserType(){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<AdminUserType> list = adminUserTypeService.getAll();
            response.setData(list);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取编辑信息",notes = "获取编辑信息",nickname = "getByUserTypeId",tags={"@张占伟"})
    @RequestMapping(value = "getByUserTypeId",method = RequestMethod.GET)
    public LayuiCommonResponse getByUserTypeId(int adminUserTypeId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            AdminUserType userType  = adminUserTypeService.getById(adminUserTypeId);
            response.setData(userType);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "修改用户类型",notes = "用户类型修改",nickname = "updateUserType",tags={"@张占伟"})
    @RequestMapping(value = "updateUserType",method = RequestMethod.POST)
    public LayuiCommonResponse updateUserType(@RequestBody AdminUserType adminUserType){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            adminUserTypeService.updateUserType(adminUserType);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }



    @ApiOperation(value = "删除用户类型",notes = "用户类型删除",nickname = "deleteUserType",tags={"@张占伟"})
    @RequestMapping(value = "deleteUserType",method = RequestMethod.POST)
    public LayuiCommonResponse deleteUserType(int adminUserTypeId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        if(adminUserTypeId==1){
            response.setCode(ZwCode.WARNING_ADMIN_USER_TYPE.getCode());
            response.setMsg(ZwCode.WARNING_ADMIN_USER_TYPE.getInfo());
            return response;
        }
        boolean results = adminUserTypeService.checkUseState(adminUserTypeId);
        if(!results){
            response.setCode(ZwCode.WARNING_USER_TYPE.getCode());
            response.setMsg(ZwCode.WARNING_USER_TYPE.getInfo());
            return response;
        }

        try {
            adminUserTypeService.deleteUserType(adminUserTypeId);
            response.setCode(ZwCode.SUCCESS_DELETE.getCode());
            response.setMsg(ZwCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_DELETE.getCode());
            response.setMsg(ZwCode.FAIL_DELETE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "新增用户类型",notes = "用户类型新增",nickname = "addUserType",tags={"@张占伟"})
    @RequestMapping(value = "addUserType",method = RequestMethod.POST)
    public LayuiCommonResponse addUserType(@RequestBody AdminUserType adminUserType){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            adminUserTypeService.addUserType(adminUserType);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "用户类型",notes = "用户类型获取分页",nickname = "getList",tags={"@张占伟"})
    @RequestMapping(value = "getUserTypeList",method = RequestMethod.GET)
    public LayuiCommonResponse getUserTypeList(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page,limit);
            List<AdminUserType> list = adminUserTypeService.getAll();
            int count =adminUserTypeService.getCount();
            response.setCount(count);
            response.setData(list);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "检查用户类型名字是否重复",notes = "效验重复",nickname = "checkUserTypeName",tags={"@张占伟"})
    @RequestMapping(value = "checkUserTypeName",method = RequestMethod.POST)
    public LayuiCommonResponse checkUserTypeName(@Validated@RequestParam(defaultValue = "0") int adminUserTypeId,String name){
        LayuiCommonResponse response = new LayuiCommonResponse();
            boolean results = adminUserTypeService.checkUserTypeName(adminUserTypeId,name);
            if(!results){
                response.setCode(ZwCode.UNABLE_USE_USER_TYPE.getCode());
                response.setMsg(ZwCode.UNABLE_USE_USER_TYPE.getInfo());
                return response;
            }
            response.setCode(ZwCode.CAN_USE_USER_TYPE.getCode());
            response.setMsg(ZwCode.CAN_USE_USER_TYPE.getInfo());
        return response;
    }
}
