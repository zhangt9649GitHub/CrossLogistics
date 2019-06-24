package com.siruiman.crosslogistics.controller;


import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.CarMaintenance;
import com.siruiman.crosslogistics.model.WaringMessage;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.CarMaintenanceService;
import com.siruiman.crosslogistics.service.CarService;
import com.siruiman.crosslogistics.service.GoodsWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/25 16:49
 */
@RestController
@Api(value="CarMaintenance-API",description = "小车维护-API",tags={"小车维护"})
@RequestMapping("CarMaintenance")
public class CarMaintenanceController {

    @Autowired
    private CarMaintenanceService carMaintenanceService;
    @Autowired
    private GoodsWarningService goodsWarningService;

    @Autowired
    private CarService carService;

    @ApiImplicitParam(name="carNumber", value="小车编号",paramType="query",dataType="String")
    @RequestMapping(value = "getCarMaintenancelist",method = RequestMethod.GET)
    @ApiOperation(value = "获取小车维护列表",nickname = "getCarMaintenanceById",notes = "获取小车维护列表",tags = {"@占伟"})
    @RequiresPermissions(value = {"getCarMaintenanceList"})
    public LayuiCommonResponse getCarMaintenanceList(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10")int limit,String carNumber,@RequestParam(defaultValue = "0")int state ){
        LayuiCommonResponse response = new LayuiCommonResponse();
        PageHelper.startPage(page, limit);
        try {
            CarMaintenance maintenance = new CarMaintenance();
            maintenance.setState(state);
            maintenance.setCarNumber(carNumber);
            List<CarMaintenance> list= carMaintenanceService.getCarMaintenanceList(maintenance);
            int count =  carMaintenanceService.getCountMaintenance(maintenance);
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

    @ApiImplicitParam(name="carMaintenanceId", value="小车维修id",paramType="query",dataType="int")
    @ApiOperation(value = "获取小车详情",nickname = "getCarMaintenanceById",notes = "获取小车维修详情",tags = {"@占伟"})
    @RequestMapping(value = "getCarMaintenanceById",method = RequestMethod.GET)
    @RequiresPermissions(value = {"getCarMaintenanceById"})
    public LayuiCommonResponse getCarMaintenanceById(@RequestParam(defaultValue = "1") int carMaintenanceId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            CarMaintenance carMaintenance = carMaintenanceService.getCarMaintenanceById(carMaintenanceId);
            response.setData(carMaintenance);
            response.setCode(0);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "修改小车维修进度",nickname = "editCarMaintenance",notes = "更新小车维修进度",tags = {"@占伟"})
    @RequestMapping(value = "editCarMaintenance",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"editCarMaintenance"})
    public LayuiCommonResponse editCarMaintenance(@RequestBody CarMaintenance carMaintenance){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            carMaintenanceService.updateCarMaintenance(carMaintenance);
            response.setCode(ZwCode.SUCCESS_EDIT.getCode());
            response.setMsg(ZwCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_EDIT.getCode());
            response.setMsg(ZwCode.FAIL_EDIT.getInfo());
        }
        return response;
    }


    @ApiOperation(value = "获取当前是否有需要处理的信息",nickname = "getMessagesOfCarAndGoods",notes = "获取当前是否有需要处理的信息",tags = {"@占伟"})
    @RequestMapping(value = "getMessagesOfCarAndGoods",method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getMessagesOfCarAndGoods(){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            WaringMessage waringMessage = new WaringMessage();
//            List<String> carMaintenanceNumbers= carMaintenanceService.getCarMaintenanceNumber();
            List<String> goodsWarningNumber = goodsWarningService.getGoodsWarningNumber();
//            if (carMaintenanceNumbers.size()<=0&&goodsWarningNumber.size()<=0){
            if (goodsWarningNumber.size()<=0){
                response.setCode(ZwCode.NO_ABNORMAL_INFORMATION.getCode());
                response.setMsg(ZwCode.NO_ABNORMAL_INFORMATION.getInfo());
                return response;
            }
//            if(carMaintenanceNumbers.size()>0){
//               waringMessage.setCarMaintenances(carMaintenanceNumbers);
//            }
            if (goodsWarningNumber.size()>0){
                waringMessage.setGoodsWaringMessage(goodsWarningNumber);
            }
            response.setCode(ZwCode.ABNORMAL_INFORMATION.getCode());
            response.setMsg(ZwCode.ABNORMAL_INFORMATION.getInfo());
            response.setData(waringMessage);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;

    }




    @ApiOperation(value = "新增小车维修",nickname = "addCarMaintenance",notes = "新增小车维修",tags = {"@占伟"})
    @RequestMapping(value = "addCarMaintenance",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"addCarMaintenance"})
    public LayuiCommonResponse addCarMaintenance(@RequestBody CarMaintenance carMaintenance){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {

            carMaintenanceService.add(carMaintenance);
            response.setCode(ZwCode.SUCCESS_INSERT.getCode());
            response.setMsg(ZwCode.SUCCESS_INSERT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_INSERT.getCode());
            response.setMsg(ZwCode.FAIL_INSERT.getInfo());
        }
        return response;
    }



    @ApiOperation(value = "获取小车编号列表",nickname = "getCarNumber",notes = "模糊查询获取小车编号列表",tags = {"@占伟"})
    @RequestMapping(value = "getCarNumber",method = RequestMethod.GET)
    public LayuiCommonResponse getCarNumber(String carNumber){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
        List<Car> cars = carService.selectByCarNumber(carNumber);
        response.setData(cars);
        response.setCode(0);
        response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
        e.printStackTrace();
        response.setCode(-1);
        response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }
}
