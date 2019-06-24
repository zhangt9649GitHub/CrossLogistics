package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.RallyPointService;
import com.siruiman.crosslogistics.service.SingaporeAreaBuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/2 11:47
 */
@RestController
@RequestMapping("/sgBuilding")
@Api(value="sgBuilding",description = "新家坡大楼管理-API",tags={"新加坡大楼管理"})
public class SGBuildingController {

    @Autowired
    private SingaporeAreaBuildingService singaporeAreaBuildingService;

    @Autowired
    private RallyPointService rallyPointService;

    @ApiOperation(value = "获取新加坡大楼列表",nickname = "getSGBuildingList",notes = "获取新加坡大楼列表",tags = {"@占伟"})
    @RequestMapping(value = "getSGBuildingList",method = RequestMethod.GET)
    @RequiresPermissions(value = {"getSGBuildingList"})
    public LayuiCommonResponse getSGBuildingList(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10")int limit,@RequestParam(defaultValue = "0")int state,String saZipCode){
        LayuiCommonResponse response = new LayuiCommonResponse();
//        查询所有
        if(state==0){
            try {
                PageHelper.startPage(page,limit);
                List<SingaporeAreaBuilding> list = singaporeAreaBuildingService.selectSGBuildingList(saZipCode);
                int count = singaporeAreaBuildingService.getCountSGBuilding(saZipCode);
                response.setCount(count);
                response.setCode(0);
                response.setData(list);
                response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            }catch (Exception e){
                e.printStackTrace();
                response.setMsg(ZwCode.FAIL_GET.getInfo());
                response.setCode(-1);
            }
            return response;
        }
//        获取已绑定区域的
        if (state==1){
            try {
                PageHelper.startPage(page,limit);
                List<SingaporeAreaBuilding> list = singaporeAreaBuildingService.selectInAreaBuilding(saZipCode);
                int count = singaporeAreaBuildingService.getCountInAreaBuilding(saZipCode);
                response.setData(list);
                response.setCount(count);
                response.setCode(0);
                response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            }catch (Exception e){
                e.printStackTrace();
                response.setMsg(ZwCode.FAIL_GET.getInfo());
                response.setCode(-1);
            }
            return response;
        }
//        获取未绑定区域的
        else{
            try {
                PageHelper.startPage(page,limit);
                List<SingaporeAreaBuilding> list = singaporeAreaBuildingService.selectNoAreaBuilding(saZipCode);
                int count = singaporeAreaBuildingService.getCountNoAreaBuilding(saZipCode);
                response.setData(list);
                response.setCount(count);
                response.setCode(0);
                response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            }catch (Exception e){
                e.printStackTrace();
                response.setMsg(ZwCode.FAIL_GET.getInfo());
                response.setCode(-1);
            }
            return response;
        }
    }

    @ApiImplicitParam(name = "saBuildingId",value = "新加坡大楼id",required = true,dataType = "int",paramType = "query")
    @ApiOperation(value = "获取新加坡大楼",nickname = "getSGBuildingList",notes = "获取新加坡大楼",tags = {"@占伟"})
    @RequestMapping(value = "getSGBuilding",method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getSGBuilding(int saBuildingId ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingService.selectSGBuildingById(saBuildingId);
            response.setCode(0);
            response.setData(singaporeAreaBuilding);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_GET.getInfo());
            response.setCode(-1);
        }
        return response;

    }


    @ApiImplicitParam(name = "saBuildingId",value = "新加坡大楼id",required = true,dataType = "int",paramType = "query")
    @ApiOperation(value = "禁用新加坡大楼",nickname = "delSGBuilding",notes = "禁用",tags = {"@占伟"})
    @RequestMapping(value = "delSGBuilding",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"delSGBuilding"})

    public LayuiCommonResponse delSGBuilding(int saBuildingId ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            singaporeAreaBuildingService.deleteById(saBuildingId);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
            response.setCode(ZwCode.FAIL_EDIT.getCode());
        }
        return response;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "saBuildingId",value = "新加坡大楼id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingName",value = "新加坡大楼名字",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saZipCode",value = "新加坡大楼邮编",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingAddress",value = "新加坡大楼地址",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingLat",value = "新加坡大楼纬度",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingLng",value = "新加坡大楼经度",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "rallyPointId",value = "集结点id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "saId",value = "区域id",required = true,dataType = "int",paramType = "query"),
    })

    @ApiOperation(value = "修改新加坡大楼",nickname = "updateSGBuilding",notes = "修改",tags = {"@占伟"})
    @RequestMapping(value = "updateSGBuilding",method = RequestMethod.POST)
    @RequiresPermissions(value = {"updateSGBuilding"})
    public LayuiCommonResponse updateSGBuilding(@RequestBody SingaporeAreaBuilding singaporeAreaBuilding ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            singaporeAreaBuildingService.updateSGBuilding(singaporeAreaBuilding);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
            response.setCode(ZwCode.FAIL_EDIT.getCode());
        }
        return response;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "saAtitudeLongitude",value = "新加坡大楼经纬度(纬度在前,经度在后)",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saZipCode",value = "新加坡大楼邮编",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingAddress",value = "新加坡大楼地址",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "rallyPointId",value = "集结点id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingName",value = "新加坡大楼名字",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saId",value = "区域id",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingLat",value = "新加坡大楼纬度",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "saBuildingLng",value = "新加坡大楼经度",required = true,dataType = "String",paramType = "query"),
    })

    @ApiOperation(value = "添加新加坡大楼",nickname = "addSGBuilding",notes = "添加",tags = {"@占伟"})
    @RequestMapping(value = "addSGBuilding",method = RequestMethod.POST)
    @RequiresPermissions(value = {"addSGBuilding"})
    public LayuiCommonResponse addSGBuilding(@RequestBody SingaporeAreaBuilding singaporeAreaBuilding ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            singaporeAreaBuildingService.insert(singaporeAreaBuilding);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
            response.setCode(ZwCode.FAIL_INSERT.getCode());
        }
        return response;
    }




    @ApiOperation(value = "获取所选区域的集结点",nickname = "getRallyPoint",notes = "添加",tags = {"@占伟"})
    @RequestMapping(value = "getRallyPoints",method = RequestMethod.POST)
    public LayuiCommonResponse getRallyPoints(Integer  saId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<RallyPoint> list = rallyPointService.selectBySGId(saId);
            response.setData(list);
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_GET.getInfo());
            response.setCode(ZwCode.FAIL_GET.getCode());
        }
        return response;
    }

    @ApiOperation(value = "效验邮编是否重复",nickname = "getRallyPoint",notes = "添加",tags = {"@占伟"})
    @RequestMapping(value = "checkZipCode",method = RequestMethod.POST)
    public LayuiCommonResponse checkZipCode(String saZipCode, @Validated @RequestParam(defaultValue = "0")Integer                             singaporeAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
            boolean results = singaporeAreaBuildingService.checkZipCode(saZipCode,singaporeAreaId);
            if(results){
                response.setCode(ZwCode.CAN_USE_CODE.getCode());
                response.setMsg(ZwCode.CAN_USE_CODE.getInfo());
            }
            else {
                response.setCode(ZwCode.UNABLE_CHECK_CODE.getCode());
                response.setMsg(ZwCode.UNABLE_CHECK_CODE.getInfo());
            }
        return response;
    }

    @ApiOperation(value = "修改大楼启用状态",nickname = "recoveryBuilding",notes = "启用",tags = {"@占伟"})
    @RequestMapping(value = "recoveryBuilding",method = RequestMethod.GET)
    @RequiresPermissions(value = {"delSGBuilding"})
    public LayuiCommonResponse recoveryBuilding(int saBuildingId ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            singaporeAreaBuildingService.recoveryBuilding(saBuildingId);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
            response.setCode(ZwCode.FAIL_EDIT.getCode());
        }
        return response;
    }
}
