package com.siruiman.crosslogistics.controller;


import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.Distance;
import com.siruiman.crosslogistics.util.MapUtils;
import com.siruiman.crosslogistics.util.MyPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/area")
@Api(value="AREA-API",description = "地区-API",tags={"中国和新加坡地区管理和小车集结点管理"})
public class AreaController {
    @Autowired
    private ChinaAreaService chinaAreaService;
    @Autowired
    private SingaporeAreaService singaporeAreaService;
    @Autowired
    private RallyPointService rallyPointService;
    @Autowired
    private SingaporePointService singaporePointService;
    @Autowired
    private SingaporeAreaBuildingService singaporeAreaBuildingService;

    @Autowired
    private AppUserPreferencesAreaService appUserPreferencesAreaService;

    private static final Logger logger = LoggerFactory
            .getLogger(AreaController.class);



    @ApiOperation(value = "获取中国省直辖市自治区特别行政区列表",nickname = "getallchinaarea",notes = "获取中国省列表",tags = {"@占伟"})
    @RequestMapping(value = "getallchinaarea",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getallchinaarea"})
    public LayuiCommonResponse getAllChinaProvinceArea (){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<ChinaArea> list = chinaAreaService.selectProvince();
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









    @ApiOperation(value = "获取中国市,区,县列表",nickname = "getallchinaarea",notes = "获取中省市子列表",tags = {"@占伟"})
    @RequestMapping(value = "getallchinachild",method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getAllChinaAreaChild(int chinaAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<ChinaArea> list = chinaAreaService.selectChildById(chinaAreaId);
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







    @ApiImplicitParams({
            @ApiImplicitParam(name="chinaAreaName", value="地区名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="chinaAreaParentId", value="上级id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="chinaAreaType", value="地区类型省市区",paramType="query",dataType="int")

    })
    @ApiOperation(value = "中国行政区添加",nickname = "addchinaarea",notes = "添加省直辖市自治区县等",tags = {"@占伟"})
    @RequestMapping(value = "addchinaarea",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"addchinaarea"})
    public LayuiCommonResponse addChinaArea(@RequestBody ChinaArea chinaArea){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            chinaAreaService.insert(chinaArea);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }
        return response;
    }



    @ApiOperation(value = "中国行政区列表",nickname = "getallareaname",notes = "获得中国所有行政区",tags = {"@占伟"})
    @RequestMapping(value = "getallareaname",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getallareaname"})
    public LayuiCommonResponse getAllAreaName(){

        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<ChinaArea> list = chinaAreaService.selectAll();
            response.setData(list);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }


    @ApiImplicitParam(name="chinaAreaId", value="行政区id",paramType="query",dataType="int")
    @ApiOperation(value = "根据id获取行政区",nickname = "getChinaAreaByChinaAreaId",notes = "根据id获取行政区编辑用",tags = {"@占伟"})
    @RequestMapping(value = "getChinaAreaByChinaAreaId",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"editchinaarea"})
    public LayuiCommonResponse getChinaAreaByChinaAreaId(@RequestParam(defaultValue = "-1") int chinaAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            ChinaArea area  =   chinaAreaService.selectChinaAreaById(chinaAreaId);
            response.setData(area);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }






    @ApiOperation(value = "中国行政区删除",nickname = "delchinaarea",notes = "删除省直辖市自治区县等",tags = {"@占伟"})
    @RequestMapping(value = "delchinaarea",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"delchinaarea"})
    public LayuiCommonResponse delChinaArea(@RequestBody ChinaArea chinaArea){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            chinaAreaService.delById(chinaArea);
            response.setCode(ZwCode.SUCCESS_DELETE.getCode());
            response.setMsg(ZwCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_DELETE.getCode());
            response.setMsg(ZwCode.FAIL_DELETE.getInfo());
        }
        return response;
    }



    @ApiImplicitParams({
            @ApiImplicitParam(name="chinaAreaId", value="地区Id",paramType="query",dataType="String"),
            @ApiImplicitParam(name="chinaAreaName", value="地区名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="chinaAreaParentId", value="上级id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="chinaAreaType", value="地区类型省市区",paramType="query",dataType="int")

    })
    @RequiresPermissions(value = {"editchinaarea"})
    @ApiOperation(value = "中国行政区修改",nickname = "editchinaarea",notes = "修改省直辖市自治区县等",tags = {"@占伟"})
    @RequestMapping(value = "editchinaarea",method = RequestMethod.POST)
    @ResponseBody
    public LayuiCommonResponse editChinaArea(@RequestBody ChinaArea chinaArea){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            chinaAreaService.update(chinaArea);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }




//  新加坡地址参数管理
//
//

    @ApiOperation(value = "获取新加坡区域列表",nickname = "getSingaporeAreaList",notes = "获取区域列表",tags = {"@占伟"})
    @RequestMapping(value = "getSingaporeAreaList",method = RequestMethod.GET)
    @RequiresPermissions(value = {"getSingaporeAreaList"})
    public LayuiCommonResponse getSingaporeAreaList(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10")int limit,
                                                    String singaporeAreaName){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page,limit);
            List<SingaporeArea> list = singaporeAreaService.selectAll(singaporeAreaName);
            for (SingaporeArea sgArea:list) {
            List<SingaporePoint> points  =  singaporePointService.selectBySGAreaId(sgArea.getSingaporeAreaId());
                    sgArea.setSingaporePoints(points);
            }
            int count = singaporeAreaService.getCount(singaporeAreaName);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            response.setCount(count);
            response.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }



    @ApiImplicitParams({
            @ApiImplicitParam(name="singaporeAreaName", value="新加坡区域名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="singaporeAreaAtitudeLongitude", value="新加坡区域经纬度",paramType="query",dataType="String"),

    })
    @RequiresPermissions(value = {"addsingaporearea"})
    @ApiOperation(value = "新加坡区域添加",nickname = "addsingaporearea",notes = "添加区域",tags = {"@占伟"})
    @RequestMapping(value = "addsingaporearea",method = RequestMethod.POST)
    public LayuiCommonResponse addSingaporeArea(@RequestBody SingaporeArea singaporeArea){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
//            添加新加坡区域
            singaporeAreaService.insertSingaporeArea(singaporeArea);
            List<SingaporePoint> singaporePoints = singaporeArea.getSingaporePoints();
//            查出新加坡区域的id
                int singaporeId = singaporeAreaService.selectSGIdBySingaporeName(singaporeArea.getSingaporeAreaName());
//            每一个点加上加上添加时间
            for (SingaporePoint singaporePoint:singaporePoints) {
                singaporePoint.setCreateTime(new Date());
                singaporePoint.setSingaporeAreaId(singaporeId);
            }
            singaporePointService.insertSGPoints(singaporePoints);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());

        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }
        return response;
    }



    @ApiImplicitParams({
            @ApiImplicitParam(name="singaporeAreaId", value="新加坡区域Id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="singaporeAreaName", value="新加坡区域名字",paramType="query",dataType="String"),

    })
    @ApiOperation(value = "检查新加坡区域名字是否存在",nickname = "checkSingaporeAreaName",notes = "检查新加坡区域名字是否存在",tags = {"@占伟"})
    @RequestMapping(value = "checkSingaporeAreaName",method = RequestMethod.POST)
    @RequiresPermissions(value = {"addsingaporearea"})
    public LayuiCommonResponse checkSingaporeAreaName(@RequestParam(defaultValue = "0") int singaporeAreaId,String singaporeAreaName){
        LayuiCommonResponse response = new LayuiCommonResponse();

//          根据名字查新加坡id
            boolean  results= singaporeAreaService.checkSingaporeAreaName(singaporeAreaId,singaporeAreaName);
//            如果id大于0说明存在不可用
//            false
            if(results){
                response.setMsg(ZwCode.CAN_USERNAME.getInfo());
                response.setCode(ZwCode.CAN_USERNAME.getCode());
            }else {
                response.setMsg(ZwCode.UNABLE_CHECKNAME.getInfo());
                response.setCode(ZwCode.UNABLE_CHECKNAME.getCode());
            }
        return response;
    }


    @ApiImplicitParam(name="singaporeAreaId", value="区域id",paramType="query",dataType="int")
    @ApiOperation(value = "根据id获取新加坡",nickname = "getSingaporeAreaById",notes = "根据id获取行政区编辑用",tags = {"@占伟"})
    @RequestMapping(value = "getSingaporeAreaById",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"editsingaporearea"})
    public LayuiCommonResponse getSingaporeAreaById(@RequestParam(defaultValue = "-1") int singaporeAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            SingaporeArea singaporeArea = singaporeAreaService.selectById(singaporeAreaId);
            List<SingaporePoint> points  =  singaporePointService.selectBySGAreaId(singaporeAreaId);
            singaporeArea.setSingaporePoints(points);
            response.setData(singaporeArea);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="singaporeAreaId", value="新加坡区域Id不用显示给用户",paramType="query",dataType="int"),
            @ApiImplicitParam(name="singaporeAreaName", value="新加坡区域名字",paramType="query",dataType="String"),
    })
    @RequiresPermissions(value = {"editsingaporearea"})
    @ApiOperation(value = "新加坡区域修改",nickname = "editsingaporearea",notes = "修改区域",tags = {"@占伟"})
    @RequestMapping(value = "editsingaporearea",method = RequestMethod.POST)
    public LayuiCommonResponse editSingaporeArea(@RequestBody SingaporeArea singaporeArea){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<SingaporePoint> points = singaporeArea.getSingaporePoints();
            List<SingaporePoint> points1 = singaporePointService.selectBySGAreaId(singaporeArea.getSingaporeAreaId());
//            如果对比客户端传来的与数据库存储的改变就删除之前的在添加客户端传来的
            if (!points.equals(points1)){
                singaporePointService.deleteBySGAreaId(singaporeArea.getSingaporeAreaId());
                for (SingaporePoint singaporePoint:points){
                    singaporePoint.setCreateTime(new Date());
                    singaporePoint.setSingaporeAreaId(singaporeArea.getSingaporeAreaId());
                }
                singaporePointService.insertSGPoints(points);
            }
            singaporeAreaService.editSingaporeArea(singaporeArea);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }


    @ApiImplicitParam(name="singaporeAreaId", value="新加坡区域Id",paramType="query",dataType="int")
    @ApiOperation(value = "新加坡区域删除",nickname = "delsingaporearea",notes = "删除区域",tags = {"@占伟"})
    @RequestMapping(value = "delsingaporearea",method = RequestMethod.GET)
    @RequiresPermissions(value = {"delsingaporearea"})
    public LayuiCommonResponse deltSingaporeArea(@RequestParam(defaultValue = "1") int singaporeAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            singaporeAreaService.deleteSingaporeArea(singaporeAreaId);
//            区域定点也删除
            singaporePointService.deleteBySGAreaId(singaporeAreaId);
//            用户偏好区域也删除 2019 -04 -22 新增删除区域同时删除 偏好区域 张占伟
            appUserPreferencesAreaService.deleteByAreaId(singaporeAreaId);
            response.setCode(ZwCode.SUCCESS_DELETE.getCode());
            response.setMsg(ZwCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_DELETE.getCode());
            response.setMsg(ZwCode.FAIL_DELETE.getInfo());
        }
        return response;
    }





//    集结点管理
    @ApiOperation(value = "小车集结点列表",nickname = "getrallypointlist",notes = "所有小车集结点",tags = {"@占伟"})
    @RequestMapping(value = "getrallypointlist",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getrallypointlist"})
    public LayuiCommonResponse getRallyPointList(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10")int limit,@RequestParam(defaultValue = "0")int singaporeAreaId ,String rallyPointAddress ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        RallyPoint point = new RallyPoint();
        point.setRallyPointAddress(rallyPointAddress);
        point.setSingaporeAreaId(singaporeAreaId);
        try {
            PageHelper.startPage(page, limit);
            List<RallyPoint> list= rallyPointService.selectRallyPointList(point);
            int count = rallyPointService.selectCountRallyPoint(point);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            response.setData(list);
            response.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }





    @ApiImplicitParams({
            @ApiImplicitParam(name="rallyPointAddress", value="集结点地址",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointLat", value="集结点纬度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointLng", value="集结点经度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointName", value="集结点名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointNumber", value="集结点编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="singaporeAreaId", value="集结点所属新加坡区域id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="rallyPointAccount", value="集结点描述",paramType="query",dataType="int"),
    })
    @RequiresPermissions(value = {"addrallypoint"})
    @ApiOperation(value = "小车集结点添加",nickname = "addrallypoint",notes = "添加小车集结点",tags = {"@占伟"})
    @RequestMapping(value = "addrallypoint",method = RequestMethod.POST)
    @ResponseBody
    public LayuiCommonResponse addRallyPoint(@RequestBody RallyPoint rallyPoint){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
             rallyPointService.insertRallyPoint(rallyPoint);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }
        return response;
    }



    @ApiImplicitParams({
            @ApiImplicitParam(name="rallyPointName", value="集结点名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointId", value="集结点名字",paramType="query",dataType="int"),
    })
    @ApiOperation(value = "小车集结点名字查重",nickname = "checkRallyPointName",notes = "小车集结点名字查重",tags = {"@占伟"})
    @RequestMapping(value = "checkRallyPointName",method = RequestMethod.POST)
    @RequiresPermissions(value = {"addrallypoint"})
    public LayuiCommonResponse checkRallyPointName(@RequestParam(defaultValue = "0") int rallyPointId ,String rallyPointName){

        LayuiCommonResponse response = new LayuiCommonResponse();
        RallyPoint rallyPoint = new RallyPoint();
        rallyPoint.setRallyPointId(rallyPointId);
        rallyPoint.setRallyPointName(rallyPointName);
        boolean results = rallyPointService.checkName(rallyPoint);
        if (results==true){
            response.setMsg(ZwCode.CAN_USERNAME.getInfo());
            response.setCode(ZwCode.CAN_USERNAME.getCode());
        }else {
            response.setCode(ZwCode.UNABLE_CHECKNAME.getCode());
            response.setMsg(ZwCode.UNABLE_CHECKNAME.getInfo());
        }
        return response;

    }








    @ApiOperation(value = "获取新加坡区域列表分页",nickname = "getSingaporeAreaListNoPaging",notes = "用来做下拉选使用修改集结点",tags = {"@占伟"})
    @RequestMapping(value = "getSingaporeAreaListNoPaging",method = RequestMethod.POST)
    public LayuiCommonResponse getSingaporeAreaListNoPaging(){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<SingaporeArea> list = singaporeAreaService.getSingaporeAreaIdAndName();
            for (SingaporeArea singaporeArea:list) {
                List<SingaporePoint> points = singaporePointService.selectBySGAreaId(singaporeArea.getSingaporeAreaId());
                if(points==null||points.size()==0){
                    continue;
                }
                singaporeArea.setSingaporePoints(points);
            }
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            response.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }






    @ApiImplicitParams({
            @ApiImplicitParam(name="rallyPointId", value="集结点id不用显示",paramType="query",dataType="int"),
            @ApiImplicitParam(name="rallyPointAddress", value="集结点地址",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointLat", value="集结点纬度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointLng", value="集结点经度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointState", value="集结点状态",paramType="query",dataType="String"),
            @ApiImplicitParam(name="rallyPointAccount", value="集结点描述",paramType="query",dataType="String"),
            @ApiImplicitParam(name="singaporeAreaId", value="集结点所属新加坡区域id",paramType="query",dataType="int")

    })
    @ApiOperation(value = "小车集结点修改",nickname = "updaterallypoint",notes = "修改小车集结点",tags = {"@占伟"})
    @RequestMapping(value = "updaterallypoint",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updaterallypoint"})
    public LayuiCommonResponse updateRallyPoint(@RequestBody RallyPoint rallyPoint){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            rallyPointService.updateRallyPoint(rallyPoint);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }




    @ApiImplicitParam(name="rallyPointId", value="集结点id",paramType="query",dataType="int")
    @ApiOperation(value = "小车集结点删除",nickname = "delrallypoint",notes = "删除小车集结点",tags = {"@占伟"})
    @RequestMapping(value = "delrallypoint",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"delrallypoint"})
    public LayuiCommonResponse delRallyPoint(int rallyPointId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            rallyPointService.delRallyPoint(rallyPointId);
            response.setCode(ZwCode.SUCCESS_DELETE.getCode());
            response.setMsg(ZwCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_DELETE.getCode());
            response.setMsg(ZwCode.FAIL_DELETE.getInfo());
        }
        return response;
    }

    @ApiImplicitParam(name="rallyPointId", value="集结点id",paramType="query",dataType="int")
    @ApiOperation(value = "获取集结点根据id",nickname = "getRallyPoint",notes = "删除小车集结点",tags = {"@占伟"})
    @RequestMapping(value = "getRallyPoint",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updaterallypoint"})
    public LayuiCommonResponse getRallyPoint(int rallyPointId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            RallyPoint rallyPoint =   rallyPointService.selectById(rallyPointId);
            response.setData(rallyPoint);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;

    }







    @ApiOperation(value = "小车集结点列表不分页",nickname = "getRallyPointListNoPaging",notes = "做下拉选",tags = {"@占伟"})
    @RequestMapping(value = "getRallyPointListNoPaging",method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getRallyPointListNoPaging(){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<RallyPoint> list= rallyPointService.selectRallyPointListOfIdAndAdress();
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
            response.setData(list);
        }catch (Exception e){
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "执行自动分区",nickname = "subArea",notes = "划分一个区域",tags = {"@占伟"})
    @RequestMapping(value = "subArea",method = RequestMethod.POST)

    @RequiresPermissions(value = {"subArea"})
    public LayuiCommonResponse subArea(@Validated @RequestParam Integer singaporeAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        logger.info("开始划区");
        try {
            String name = null;
//          查出所有的区域

            List<MyPoint> myPoints =new ArrayList<>();
            MyPoint myPoint = new MyPoint();
//          查出指定的区域
            List<SingaporePoint> points = singaporePointService.selectBySGAreaId(singaporeAreaId);
                for (SingaporePoint sp:points) {
                myPoint.setX(Double.parseDouble(sp.getLat()));
                myPoint.setY(Double.parseDouble(sp.getLng()));
                myPoints.add(myPoint);
            }
            String saZipCode ="";
            List<SingaporeAreaBuilding> buildings = singaporeAreaBuildingService.selectSGBuildingList(saZipCode);
            for (SingaporeAreaBuilding building:buildings){
                myPoint.setX(Double.parseDouble(building.getSaBuildingLat()));
                myPoint.setY(Double.parseDouble(building.getSaBuildingLng()));
//                判断大楼是否在区域内
                boolean results = MapUtils.isPointInPolygon(myPoint, myPoints);
                if (results){
                    logger.info(building.getSaBuildingAddress()+"属于区域");
                    building.setSaId(singaporeAreaId);
                    singaporeAreaBuildingService.updateBuidingArea(building);
                }
            }
            response.setCode(0);
            response.setMsg("划分区域成功");
            logger.info("新加坡大楼划分区域成功");
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("划分区域失败");
            logger.info("新加坡大楼划分区域失败");
        }
        return response;
    }


    /**
     * 1.查出区域所有的集结点
     * 2.查出区域内的大楼
     * 3.循环找出一个大楼所在区域距离最近的集结点
     * 4.修改区域最近的集结点
     * @param singaporeAreaId
     * @return
     */

    @ApiOperation(value = "执行自动分配集结点",nickname = "subRallyPoint",notes = "划分一个区域下的所有大楼和他所属的所有集结点",tags = {"@占伟"})
    @RequestMapping(value = "subRallyPoint",method = RequestMethod.POST)
    @RequiresPermissions(value = {"subRallyPoint"})
    public LayuiCommonResponse subRallyPoint(@Validated @RequestParam Integer singaporeAreaId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        logger.info("开始划区");
        try {
            List<RallyPoint> rallyPoints = rallyPointService.selectBySGId(singaporeAreaId);
            List<SingaporeAreaBuilding> buildingList  = singaporeAreaBuildingService.selectBySaId(singaporeAreaId);
            HashMap<String, Distance> map = new HashMap<>(16);
            double distance;
            Distance dis =null;
            for (SingaporeAreaBuilding sa:buildingList){
                for (RallyPoint point:rallyPoints){
                    distance = MapUtils.getDistance(Double.valueOf(sa.getSaBuildingLat()), Double.valueOf(sa.getSaBuildingLng()),
                            Double.valueOf(point.getRallyPointLat()), Double.valueOf(point.getRallyPointLng()));
                    dis = map.get(sa.getSaBuildingId() + "");
                    if(dis==null){
                         dis = new Distance();
                         dis.setDistance(distance);
                         dis.setRallyPointId(point.getRallyPointId());
                         dis.setSaBuildingId(sa.getSaBuildingId());
                         map.put(sa.getSaBuildingId()+"",dis);
                    } else{
//                        如果该区域集结点距离比之前的小就更换map记录的
                        if(distance<dis.getDistance()) {
                            dis.setDistance(distance);
                            dis.setRallyPointId(point.getRallyPointId());
                            dis.setSaBuildingId(sa.getSaBuildingId());
                            map.put(sa.getSaBuildingId() + "", dis);
                        }
                    }
                }
                sa.setRallyPointId(map.get(sa.getSaBuildingId()+"").getRallyPointId());
                singaporeAreaBuildingService.updateBuidingRallyPoint(sa);

            }


            response.setCode(0);
            response.setMsg("划分集结点成功");
            logger.info("新加坡大楼划分集结点成功");
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("划分集结点失败");
            logger.info("新加坡大楼划分集结点失败");
        }
        return response;
    }







}
