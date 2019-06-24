package com.siruiman.crosslogistics.controller;


import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.Warehouse;
import com.siruiman.crosslogistics.model.WarehousePositions;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.WarehousePositionService;
import com.siruiman.crosslogistics.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "warehouseposition-API",description = "仓位管理-API",tags = {"仓位管理"})
@RequestMapping("warehouseposition")
@RestController
public class WarehousePositionController {

    @Autowired
    private WarehousePositionService warehousePositionService;
    @Autowired
    private WarehouseService warehouseService;

    @ApiImplicitParam(name="warehouseId", value="仓库id",paramType="query",dataType="int")
    @ApiOperation(value = "根据仓库id获取仓位列表",nickname = "getwarehousepositionlistbyid",notes = "仓位列表",tags = {"@占伟"})
    @RequestMapping(value = "getwarehousepositionlistbyid",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value ={"getwarehousepositionlist"} )
    public LayuiCommonResponse getWarehousePositionsListByID(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10")int limit,@RequestParam(defaultValue = "0")int warehouseId ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<WarehousePositions> list = warehousePositionService.getAll(warehouseId);
            int count = warehousePositionService.getCountByWarehouseId(warehouseId);
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

    @ApiOperation(value = "获取所有仓库id,名字不分页",nickname = "getWarehouseData",notes = "获取仓库列表做下拉选不分页",tags = {"@占伟"})
    @RequestMapping(value = "getwarehouselistnopage",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value ={"getwarehousepositionlist"} )
    public LayuiCommonResponse getWarehouseListNoPaging(){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<Warehouse> list = warehouseService.getWarehouse();
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
            @ApiImplicitParam(name="wpNumber", value="仓位编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseId", value="所在仓库id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="wpUse", value="仓位用途",paramType="query",dataType="String"),

    })
    @RequestMapping(value = "getwarehousepositionlist" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取仓位列表",nickname = "getwarehousepositionlist",notes = "仓位列表",tags = {"@占伟"})
    @ResponseBody
    @RequiresPermissions(value ={"getwarehousepositionlist"} )
    public LayuiCommonResponse getWarehousePositionList(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10")int limit,String wpNumber,
                                                        @RequestParam(defaultValue = "0")int warehouseId,String wpUse ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            WarehousePositions positions = new WarehousePositions();
            positions.setWarehouseId(warehouseId);
            positions.setWpUse(wpUse);
            positions.setWpNumber(wpNumber);
            List<WarehousePositions> list = warehousePositionService.selectAll(positions);
            int count = warehousePositionService.selectCount(positions);
            response.setData(list);
            response.setCount(count);
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
            @ApiImplicitParam(name="wpId", value="仓位id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="wpNumber", value="仓位编号",paramType="query",dataType="int"),
            @ApiImplicitParam(name="warehouseId", value="仓库id",paramType="query",dataType="String"),
            @ApiImplicitParam(name="wpState", value="仓位状态",paramType="query",dataType="String"),
            @ApiImplicitParam(name="wpHead", value="仓位负责人",paramType="query",dataType="double"),
            @ApiImplicitParam(name="wpCapacity", value="仓位容量",paramType="query",dataType="String"),
            @ApiImplicitParam(name="wpCurrentCapacity", value="仓位当前容量",paramType="query",dataType="double"),
            @ApiImplicitParam(name="wpUse", value="仓位用途",paramType="query",dataType="int")
    })



    @RequestMapping(value = "editwarehouseposition",method = RequestMethod.POST)
    @ApiOperation(value = "修改仓位",nickname = "editwarehouseposition",notes = "传递仓位修改",tags = {"@占伟"})
    @ResponseBody
    @RequiresPermissions(value ={"editwarehouseposition"} )
    public LayuiCommonResponse updateWarehousePosition(@RequestBody WarehousePositions warehousePositions){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            warehousePositionService.update(warehousePositions);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }



    @ApiImplicitParam(name="wpId", value="仓位id",paramType="query",dataType="int")
    @ResponseBody
    @ApiOperation(value = "获取仓位根据id",nickname = "getwarehouseposition",notes = "根据仓位id获取仓位",tags = {"@占伟"})
    @RequestMapping(value = "getwarehouseposition",method = RequestMethod.POST)
    @RequiresPermissions(value ={"editwarehouseposition"} )
    public LayuiCommonResponse getWarehousePositions(@RequestParam(defaultValue = "0") Integer wpId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            WarehousePositions warehousePositions = warehousePositionService.getByID(wpId);
            response.setData(warehousePositions);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }

        return   response;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="wpId", value="仓位id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="wpNumber", value="仓位编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="warehouseId", value="仓库id",paramType="query",dataType="String"),
            @ApiImplicitParam(name="wpState", value="仓位状态",paramType="query",dataType="String"),
            @ApiImplicitParam(name="wpHead", value="仓位负责人",paramType="query",dataType="double"),
            @ApiImplicitParam(name="wpCapacity", value="仓位容量",paramType="query",dataType="String"),
            @ApiImplicitParam(name="wpCurrentCapacity", value="仓位当前容量",paramType="query",dataType="double"),
            @ApiImplicitParam(name="wpUse", value="仓位用途",paramType="query",dataType="int")
    })





    @RequestMapping(value = "addwarehouseposition",method = RequestMethod.POST)
    @ApiOperation(value = "添加仓位",nickname = "addwarehouseposition",notes = "仓位添加",tags = {"@占伟"})
    @ResponseBody
    @RequiresPermissions(value ={"editwarehouseposition"} )
    public LayuiCommonResponse addWarehousePositions(@RequestBody WarehousePositions warehousePositions){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            warehousePositionService.insert(warehousePositions);
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
            response.setCode(ZwCode.FAIL_INSERT.getCode());
        }
        return response;
    }



    @ApiOperation(value = "删除仓位",nickname = "deleteWarehousePositionsById",notes = "根据id删除仓位",tags = {"@占伟"})
    @RequestMapping(value = "deleteWarehousePositionsById",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value ={"deleteWarehousePositionsById"} )
    public LayuiCommonResponse deleteWarehousePositionsById(int wpId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {

            warehousePositionService.deleteById(wpId);
            response.setCode(ZwCode.SUCCESS_DELETE.getCode());
            response.setMsg(ZwCode.SUCCESS_DELETE.getInfo());
        }catch (Exception  e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_DELETE.getCode());
            response.setMsg(ZwCode.FAIL_DELETE.getInfo());
        }
        return response;
    }
}
