package com.siruiman.crosslogistics.controller;


import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.Warehouse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.WarehousePositionService;
import com.siruiman.crosslogistics.service.WarehouseService;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.binding.BindingException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value="Warehouse-API",description = "仓库管理-API",tags={"仓库管理"})
@RequestMapping("warehouse")
@RestController
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehousePositionService warehousePositionService;




    @ApiImplicitParams({
            @ApiImplicitParam(name="warehouseNumber", value="仓库编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseName", value="仓库名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseType", value="仓库类型",paramType="query",dataType="int"),
            @ApiImplicitParam(name="warehousePhone", value="仓库联系电话",paramType="query",dataType="String")
    })


    @ApiOperation(value = "获取仓库列表",nickname = "getwarehouselist",notes = "获取仓库列表",tags = {"@占伟"})
    @RequestMapping(value = "getwarehouselist",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getwarehouselist"})
    public LayuiCommonResponse getWarehouseList(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10")int limit ,
                                                String warehouseName,
                                                String warehousePhone,
                                               @RequestParam(defaultValue = "0") int warehouseType ,
                                                String warehouseNumber){
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName(warehouseName);
        warehouse.setWarehouseNumber(warehouseNumber);
        warehouse.setWarehouseType(warehouseType);
        warehouse.setWarehousePhone(warehousePhone);
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Warehouse> list = warehouseService.getAll(warehouse);
            int count = warehouseService.getCountWarehouse(warehouse);
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
            @ApiImplicitParam(name="warehouseId", value="仓库id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="warehouseLatitudeLongitude", value="仓库经纬度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseName", value="仓库名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseCapacity", value="仓库容量",paramType="query",dataType="double"),
            @ApiImplicitParam(name="warehouseAddress", value="仓库地址",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseCurrentCapacity", value="仓库当前容量",paramType="query",dataType="double"),
            @ApiImplicitParam(name="warehouseState", value="仓库状态",paramType="query",dataType="int"),
            @ApiImplicitParam(name="warehousePhone", value="仓库联系电话",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseHead", value="仓库负责人",paramType="query",dataType="int")
    })
    @ApiOperation(value = "添加仓库" , nickname = "addwarehouse",notes = "添加仓库",tags={"@占伟"})
    @RequestMapping( value = "addwarehouse",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"addwarehouse"})
    public LayuiCommonResponse addWarehouse(@RequestBody Warehouse warehouse){
        warehouse.setWarehouseNumber(RandomCodeUtil.getSixRandomCode());
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            warehouseService.insert(warehouse);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }

        return response;
    }


    @ApiImplicitParam(name="warehouseName", value="仓库名字",paramType="query",dataType="String")
    @ApiOperation(value = "检查仓库名字是否可用",nickname = "checkWarehouseName",notes = "检查仓库名字是否存在",tags = {"@占伟"})
    @RequestMapping(value = "checkWarehouseName",method = RequestMethod.POST)
    @RequiresPermissions(value = {"addwarehouse"})
    public LayuiCommonResponse checkWarehouseName(@RequestParam(defaultValue = "0") int warehouseId,String warehouseName){
        LayuiCommonResponse response = new LayuiCommonResponse();
        boolean result =  warehouseService.checkWarehouseName(warehouseId,warehouseName);
//        如果存在就不能使用
        if(!result){
            response.setMsg(ZwCode.UNABLE_CHECKNAME.getInfo());
            response.setCode(ZwCode.UNABLE_CHECKNAME.getCode());
        }else {
            response.setMsg(ZwCode.CAN_USERNAME.getInfo());
            response.setCode(ZwCode.CAN_USERNAME.getCode());
        }
        return response;
    }






    @ApiImplicitParams({
            @ApiImplicitParam(name="warehouseId", value="仓库id",paramType="query",dataType="int")
    })
    @ApiOperation(value = "根据仓库id查询查询仓库" ,nickname = "getwarehouse", notes = "根据仓库id查询仓库",tags={"@占伟"})
    @RequestMapping(value = "getwarehouse" ,method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updatewarehouse"})
    public LayuiCommonResponse getWarehouse(@RequestParam(defaultValue = "0") int warehouseId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Warehouse warehouse = warehouseService.selectById(warehouseId);
            response.setData(warehouse);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }

        return response;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name="warehouseId", value="仓库id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="warehouseLat", value="仓库纬度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseLng", value="仓库经度",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseName", value="仓库名字",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseCapacity", value="仓库容量",paramType="query",dataType="double"),
            @ApiImplicitParam(name="warehouseAddress", value="仓库地址",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseCurrentCapacity", value="仓库当前容量",paramType="query",dataType="double"),
            @ApiImplicitParam(name="warehouseState", value="仓库状态",paramType="query",dataType="int"),
            @ApiImplicitParam(name="warehousePhone", value="仓库联系电话",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseHead", value="仓库负责人",paramType="query",dataType="int")
    })



    @ApiOperation(value = "修改仓库",nickname = "updatewarehouse",notes = "修改传递仓库对象",tags = {"@占伟"})
    @RequestMapping(value = "updatewarehouse",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updatewarehouse"})
    public LayuiCommonResponse updateWarehouse(@RequestBody Warehouse warehouse){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try{
            warehouseService.updateWarehouse(warehouse);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }




    @ApiOperation(value = "删除仓库",nickname = "deletewarehousebyid",notes = "获取仓库列表",tags = {"@占伟"})
    @RequestMapping(value = "deletewarehousebyid",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deletewarehousebyid"})
    public LayuiCommonResponse deleteWarehouseByid(int warehouseId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            warehouseService.deleteWarehouseByid(warehouseId);
            warehousePositionService.deleteByWarehouseId(warehouseId);
            response.setCode(ZwCode.SUCCESS_DELETE.getCode());
            response.setMsg(ZwCode.SUCCESS_DELETE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_DELETE.getCode());
            response.setMsg(ZwCode.FAIL_DELETE.getInfo());
        }
        return response;
    }
}
