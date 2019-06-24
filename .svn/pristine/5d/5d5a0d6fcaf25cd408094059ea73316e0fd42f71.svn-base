package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.StaffDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.BizdictionaryService;
import com.siruiman.crosslogistics.service.StaffGroupAccessService;
import com.siruiman.crosslogistics.service.StaffService;
import com.siruiman.crosslogistics.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Api(value="Staff",description = "员工列表",tags={"员工管理-员工列表"})
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private BizdictionaryService bizdictionaryService;
    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation(value = "获取员工列表",notes = "selectStaffAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectStaffAll",method = RequestMethod.GET)
    public LayuiCommonResponse selectStaffAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                              StaffDto staffDto) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<Staff> selectStaffAll = staffService.selectStaffAll(staffDto);
            Integer count = staffService.count(staffDto);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectStaffAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "新增员工",notes = "addStaff",tags={"@郭阳"})
    @RequestMapping(value = "/addStaff",method = RequestMethod.POST)
    public CommonResponse addStaff(@RequestBody Staff staff) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer addStaff = staffService.addStaff(staff);
            if(addStaff == 2){
                commonResponse.setCode(GyCode.FAIL_USER_NAME.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_NAME.getInfo());
                return commonResponse;
            }
            if(addStaff < 1){
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

    @ApiOperation(value = "员工详情",notes = "staffDetail",tags={"@郭阳"})
    @ApiImplicitParam(name="staffId", value="员工id",paramType="query",dataType="int")
    @RequestMapping(value = "/staffDetail",method = RequestMethod.GET)
    public CommonResponse staffDetail(int staffId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Staff selectStaffDetail = staffService.selectStaffDetail(staffId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectStaffDetail);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "员工权限列表",notes = "staffAccessAll",tags={"@郭阳"})
    @RequestMapping(value = "/staffAccessAll",method = RequestMethod.GET)
    public CommonResponse staffAccessAll() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<StaffAccess> selectStaffAccessAll = staffService.selectStaffAccessAll();
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectStaffAccessAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询编辑员工信息",notes = "selectEeditStaff",tags={"@郭阳"})
    @ApiImplicitParam(name="staffId", value="员工id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectEeditStaff",method = RequestMethod.GET)
    public CommonResponse selectEeditStaff(int staffId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Staff selectEeditStaff = staffService.selectEeditStaff(staffId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectEeditStaff);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "编辑员工",notes = "editStaff",tags={"@郭阳"})
    @RequestMapping(value = "/editStaff",method = RequestMethod.POST)
    public CommonResponse editStaff(@RequestBody Staff staff) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editStaff = staffService.editStaff(staff);
            if(editStaff == null){
                commonResponse.setCode(GyCode.ERROR_PASSWORD.getCode());
                commonResponse.setMessage(GyCode.ERROR_PASSWORD.getInfo());
                return commonResponse;
            }
            if(editStaff < 1){
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


    @ApiOperation(value = "删除员工",notes = "deleteStaff",tags={"@郭阳"})
    @ApiImplicitParam(name="staffId", value="员工id",paramType="query",dataType="int")
    @RequestMapping(value = "/deleteStaff",method = RequestMethod.POST)
    public CommonResponse deleteStaff(int staffId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer deleteStaff = staffService.deleteStaff(staffId);
            if(deleteStaff < 1){
                commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
                commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_DELETE.getCode());
            commonResponse.setData(deleteStaff);
            commonResponse.setMessage(GyCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_DELETE.getCode());
            commonResponse.setMessage(GyCode.FAIL_DELETE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "启用禁用",notes = "editStaffStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="staffId", value="员工id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="status", value="启用1禁用0",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editStaffStatus",method = RequestMethod.POST)
    public CommonResponse editStaffStatus(int staffId, int status) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editStaffStatus = staffService.editStaffStatus(staffId, status);
            if(editStaffStatus < 1){
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

    @ApiOperation(value = "验证新增员工用户名重复",notes = "checkUserName",tags={"@郭阳"})
    @ApiImplicitParam(name="userName", value="员工用户名",paramType="query",dataType="String")
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    public CommonResponse checkUserName(String userName) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer checkUserName = staffService.checkUserName(userName);
            if(checkUserName > 0){
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
    }

    @ApiOperation(value = "获取查看员工信息操作记录",notes = "logisticInfoByStaff",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="staffId", value="员工id",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/logisticInfoByStaff",method = RequestMethod.GET)
    public LayuiCommonResponse selectLogisticInfoByStaff(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                                         int staffId) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<LogInfo> selectLogisticInfoByStaff = staffService.selectLogisticInfoByStaff(staffId);

            Integer countLogisticInfoByStaff = staffService.countLogisticInfoByStaff(staffId);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectLogisticInfoByStaff);
            layuiCommonResponse.setCount(countLogisticInfoByStaff);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取员工权限组列表",notes = "staffGroupAll",tags={"@郭阳"})
    @RequestMapping(value = "/staffGroupAll",method = RequestMethod.GET)
    public CommonResponse staffGroupAll() {

        CommonResponse commonResponse = new CommonResponse();
        try{
            List<StaffGroupAccess> selectStaffGroupAll = staffService.selectStaffGroupAll();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectStaffGroupAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "获取员工归属地",notes = "获取员工归属地",tags={"@郝腾"})
    @RequestMapping(value = "/getStaffAttribution",method = RequestMethod.GET)
    public LayuiCommonResponse getStaffAttribution() {
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            List<Bizdictionary> bizdictionaryList =bizdictionaryService.selectBizdictionaryByParentId(261);
            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(bizdictionaryList);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }


    @ApiOperation(value = "下拉选仓库名", notes = "下拉选仓库名", nickname = "getWarehouseList", tags = {"@占伟"})
    @RequestMapping(value = "/getWarehouseList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWarehouseList() {
        CommonResponse response = new CommonResponse();
        try {
            List<Warehouse> list = warehouseService.selectAllNameAndId();
            response.setMessage(ZwCode.SUCCESS_GET.getInfo());
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setData(list);
        } catch (Exception e) {
            response.setMessage(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }
}
