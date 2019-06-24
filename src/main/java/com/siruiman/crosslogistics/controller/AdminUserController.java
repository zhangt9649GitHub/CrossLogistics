package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.AdminUser;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.dto.AdminUserDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Api(value="AdminUser",description = "管理员列表",tags={"管理员管理-管理员列表"})
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @ApiOperation(value = "获取管理员列表",notes = "getAdminUserList",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="groupId", value="权限组id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="search", value="搜索",paramType="query",dataType="String")
    })
    @RequestMapping(value = "/getAdminUserList",method = RequestMethod.GET)
    public LayuiCommonResponse getAdminUserList(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                @Validated @RequestParam(defaultValue = "0") int groupId, @Validated @RequestParam(defaultValue = "") String search) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();

        try{
            PageHelper.startPage(page,limit);
            List<AdminUser> selectAdminUserAll = adminUserService.selectAdminUserAll(groupId, search);
            Integer count = adminUserService.count(groupId, search);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectAdminUserAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "新增管理员" , notes = "addAdminUser",tags={"@郭阳"})
    @RequestMapping(value = "/addAdminUser",method = RequestMethod.POST)
    public CommonResponse addAdminUser(@Validated @RequestBody AdminUserDto adminUserDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer addAdminUser = adminUserService.addAdminUser(adminUserDto);
            if(addAdminUser < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
                return commonResponse;
            }
            if(addAdminUser == 2){
                commonResponse.setCode(GyCode.FAIL_USER_NAME.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_NAME.getInfo());
                return commonResponse;
            }
//            添加效验三方账号是否存在 就反馈三方公司只能添加一个
            if(addAdminUser==3){
                commonResponse.setCode(ZwCode.WARNING_TRIPARTITE_USER.getCode());
                commonResponse.setMessage(ZwCode.WARNING_TRIPARTITE_USER.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setData(addAdminUser);
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_ADD.getCode());
            commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "权限组下拉框（新增和筛选）" , notes = "selectGroupAll",tags={"@郭阳"})
    @RequestMapping(value = "/selectGroupAll",method = RequestMethod.POST)
    public CommonResponse selectGroupAll() {

        CommonResponse commonResponse = new CommonResponse();
        try{
            List<Group> selectGroupAll = adminUserService.selectGroupAll();
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectGroupAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑管理员-显示信息" , notes = "selectEditAdminUser",tags={"@郭阳"})
    @ApiImplicitParam(name="adminUid", value="管理员id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectEditAdminUser",method = RequestMethod.POST)
    public CommonResponse selectEditAdminUser(int adminUid) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserDto selectEditAdminUser = adminUserService.selectEditAdminUser(adminUid);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectEditAdminUser);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑管理员" , notes = "editAdminUser",tags={"@郭阳"})
    @RequestMapping(value = "/editAdminUser",method = RequestMethod.POST)
    public CommonResponse editAdminUser(@RequestBody AdminUserDto adminUserDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editAdminUser = adminUserService.editAdminUser(adminUserDto);
            if(editAdminUser == null){
                commonResponse.setCode(GyCode.ERROR_PASSWORD.getCode());
                commonResponse.setMessage(GyCode.ERROR_PASSWORD.getInfo());
                return commonResponse;
            }
            if(editAdminUser < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setData(editAdminUser);
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "删除管理员" , notes = "deleteAdminUser",tags={"@郭阳"})
    @ApiImplicitParam(name="adminUid", value="管理员id",paramType="query",dataType="int")
    @RequestMapping(value = "/deleteAdminUser",method = RequestMethod.POST)
    public CommonResponse deleteAdminUser(int adminUid) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteAdminUser = adminUserService.deleteAdminUser(adminUid);
            if(deleteAdminUser < 1){
                commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
                commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_DELETE.getCode());
            commonResponse.setData(deleteAdminUser);
            commonResponse.setMessage(GyCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
            commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "启用禁用管理员" , notes = "editAdminUserStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminUid", value="管理员id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="status", value="状态1或0",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editAdminUserStatus",method = RequestMethod.POST)
    public CommonResponse editAdminUserStatus(int adminUid, int status) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            Integer editAdminUserStatus = adminUserService.editAdminUserStatus(adminUid, status);
            if(editAdminUserStatus < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setData(editAdminUserStatus);
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }


}
