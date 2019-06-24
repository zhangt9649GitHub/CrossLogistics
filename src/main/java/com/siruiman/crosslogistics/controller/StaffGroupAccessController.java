package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.StaffGroupAccess;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.StaffGroupDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.StaffGroupAccessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value="StaffGroupAccess",description = "员工权限列表",tags={"员工管理-员工权限列表"})
@RestController
@RequestMapping("/staffGroupAccess")
public class StaffGroupAccessController {
    @Autowired
    private StaffGroupAccessService staffGroupAccessService;

    @ApiOperation(value = "获取员工权限组列表",notes = "staffGroupAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="staffGroupId", value="权限组id",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/staffGroupAll",method = RequestMethod.GET)
    public LayuiCommonResponse staffGroupAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                             @Validated @RequestParam(defaultValue = "0") int staffGroupId) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<StaffGroupAccess> selectStaffGroupAll = staffGroupAccessService.selectStaffGroupAll(staffGroupId);
            Integer count = staffGroupAccessService.count(staffGroupId);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectStaffGroupAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }


    @ApiOperation(value = "添加员工权限组",notes = "addStaffGroup",tags={"@郭阳"})
    @RequestMapping(value = "/addStaffGroup",method = RequestMethod.POST)
    public CommonResponse addStaffGroup(@RequestBody StaffGroupDto staffGroupDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            staffGroupDto.setAdminUid(Integer.parseInt(user.getAdminUId()));

            Integer addStaffGroup = staffGroupAccessService.addStaffGroup(staffGroupDto);
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setData(addStaffGroup);
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询编辑员工权限组信息",notes = "selectEditStaffGroup",tags={"@郭阳"})
    @ApiImplicitParam(name="staffGroupId", value="权限组id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectEditStaffGroup",method = RequestMethod.POST)
    public CommonResponse selectEditStaffGroup(int staffGroupId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            StaffGroupDto selectEditStaffGroup = staffGroupAccessService.selectEditStaffGroup(staffGroupId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectEditStaffGroup);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑员工权限组信息",notes = "editStaffGroup",tags={"@郭阳"})
    @RequestMapping(value = "/editStaffGroup",method = RequestMethod.POST)
    public CommonResponse selectEditStaffGroup(@RequestBody StaffGroupDto staffGroupDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editStaffGroup = staffGroupAccessService.editStaffGroup(staffGroupDto);
            if(editStaffGroup < 1){
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

    @ApiOperation(value = "删除员工权限组",notes = "deleteStaffGroup",tags={"@郭阳"})
    @ApiImplicitParam(name="staffGroupId", value="权限组id",paramType="query",dataType="int")
    @RequestMapping(value = "/deleteStaffGroup",method = RequestMethod.POST)
    public CommonResponse deleteStaffGroup(int staffGroupId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteStaffGroup = staffGroupAccessService.deleteStaffGroup(staffGroupId);
            if(deleteStaffGroup == 2){
                commonResponse.setCode(GyCode.FAIL_DELETE_ACCESS_GROUP_USER.getCode());
                commonResponse.setMessage(GyCode.FAIL_DELETE_ACCESS_GROUP_USER.getInfo());
                return commonResponse;
            }
            if(deleteStaffGroup < 1){
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
}
