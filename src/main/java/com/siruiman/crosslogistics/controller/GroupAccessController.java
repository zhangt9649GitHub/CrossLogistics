package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.Access;
import com.siruiman.crosslogistics.model.Group;
import com.siruiman.crosslogistics.model.GroupAccess;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.GroupAccessDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.GroupAccessService;
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

@Api(value="groupAccess",description = "权限管理",tags={"管理员管理-权限管理"})
@RestController
@RequestMapping("/admin/groupAccess")
public class GroupAccessController {
    @Autowired
    private GroupAccessService groupAccessService;

    @ApiOperation(value = "权限管理列表", nickname = "groupAccessList", notes = "", tags={ "@郭阳" })
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="groupId", value="权限组id",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/getGroupAccessList",method = RequestMethod.GET)
    public LayuiCommonResponse list(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                    @Validated @RequestParam(defaultValue = "0") int groupId) {
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<Group> groupAll = groupAccessService.groupAll(groupId);
            Integer count = groupAccessService.count(groupId);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(groupAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取详情", nickname = "accessTree", notes = "", tags={ "@郭阳" })
    @ApiImplicitParam(name="groupId", value="权限组id",paramType="query",dataType="int",required=true)
    @RequestMapping(value = "/getAccessTree",method = RequestMethod.GET)
    public CommonResponse details(@Validated int groupId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            GroupAccess accessAll = groupAccessService.accessAll(groupId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(accessAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "获取新增所有权限", nickname = "accessTreeAll", notes = "", tags={ "@郭阳" })
    @RequestMapping(value = "/accessTreeAll",method = RequestMethod.GET)
    public CommonResponse accessTreeAll() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<Access> accessAll = groupAccessService.accessAll();
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(accessAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "添加权限组", nickname = "addGroupAccess", notes = "", tags={ "@郭阳" })
    @RequestMapping(value = "/addGroupAccess",method = RequestMethod.POST)
    public CommonResponse addGroupAccess(@RequestBody GroupAccessDto groupAccessDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            groupAccessDto.setAdminUid(Integer.parseInt(user.getAdminUId()));

            Integer addGroupAccess = groupAccessService.addGroupAccess(groupAccessDto);
            if(addGroupAccess < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_ADD.getCode());
            commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "删除权限组", nickname = "deleteGroupAccess", notes = "", tags={ "@郭阳" })
    @ApiImplicitParam(name="groupId", value="权限组id",paramType="query",dataType="int",required=true)
    @RequestMapping(value = "/deleteGroupAccess",method = RequestMethod.POST)
    public CommonResponse deleteGroupAccess(@Validated int groupId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteGroupAndAccess = groupAccessService.deleteGroupAndAccess(groupId);
            if(deleteGroupAndAccess == 2){
                commonResponse.setCode(GyCode.FAIL_DELETE_ACCESS_GROUP_USER.getCode());
                commonResponse.setMessage(GyCode.FAIL_DELETE_ACCESS_GROUP_USER.getInfo());
                return commonResponse;
            }
            if(deleteGroupAndAccess < 1){
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

    @ApiOperation(value = "编辑权限组-编辑信息", nickname = "selectEditGroupTree", notes = "", tags={ "@郭阳" })
    @ApiImplicitParam(name="groupId", value="权限组id",paramType="query",dataType="int",required=true)
    @RequestMapping(value = "/selectEditGroupTree",method = RequestMethod.POST)
    public CommonResponse selectEditGroupTree(@Validated int groupId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            GroupAccess accessAllByEdit = groupAccessService.accessAllByEdit(groupId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(accessAllByEdit);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑权限组", nickname = "editGroupAccess", notes = "", tags={ "@郭阳" })
    @RequestMapping(value = "/editGroupAccess",method = RequestMethod.POST)
    public CommonResponse editGroupAccess(@Validated @RequestBody GroupAccessDto groupAccessDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AdminUserLoginDto user =(AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            groupAccessDto.setAdminUid(Integer.parseInt(user.getAdminUId()));

            Integer editGroupAccess = groupAccessService.editGroupAccess(groupAccessDto);
            if(editGroupAccess < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }
}
