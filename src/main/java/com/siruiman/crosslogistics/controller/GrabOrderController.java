package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.GrabOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value="GrabOrder",description = "抢单接口",tags={"APP(小车货车)抢单大厅-抢单接口"})
@RestController
@RequestMapping("/grabOrder")
public class GrabOrderController {
    @Autowired
    private GrabOrderService grabOrderService;

    @ApiOperation(value = "小车抢单接口",notes = "carGrab",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/carGrab",method = RequestMethod.POST)
    public CommonResponse carGrab(int appUserId, int taskOrderId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer carGrab = grabOrderService.carGrab(taskOrderId, appUserId);
            if(carGrab == 3){
                commonResponse.setCode(GyCode.FAIL_APP_USER_VETIFIED.getCode());
                commonResponse.setMessage(GyCode.FAIL_APP_USER_VETIFIED.getInfo());
                return commonResponse;
            }
            if(carGrab == 4){
                commonResponse.setCode(GyCode.FAIL_USER_TIME_IS_CAR_ORDER.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_TIME_IS_CAR_ORDER.getInfo());
                return commonResponse;
            }
            if(carGrab == 2){
                commonResponse.setCode(GyCode.ORDER_HAS_BEEN_ROBBED.getCode());
                commonResponse.setMessage(GyCode.ORDER_HAS_BEEN_ROBBED.getInfo());
                return commonResponse;
            }
            if(carGrab < 1){
                commonResponse.setCode(GyCode.FAIL_GRAP_ORDER.getCode());
                commonResponse.setMessage(GyCode.FAIL_GRAP_ORDER.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_GRAP_ORDER.getCode());
            commonResponse.setData(carGrab);
            commonResponse.setMessage(GyCode.SUCCESS_GRAP_ORDER.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GRAP_ORDER.getCode());
            commonResponse.setMessage(GyCode.FAIL_GRAP_ORDER.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "货车抢单接口",notes = "truckGrab",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/truckGrab",method = RequestMethod.POST)
    public CommonResponse truckGrab(int appUserId, int taskOrderId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer truckGrab = grabOrderService.truckGrab(appUserId, taskOrderId);
            if(truckGrab == 3){
                commonResponse.setCode(GyCode.FAIL_APP_USER_VETIFIED.getCode());
                commonResponse.setMessage(GyCode.FAIL_APP_USER_VETIFIED.getInfo());
                return commonResponse;
            }
            if(truckGrab == 4){
                commonResponse.setCode(GyCode.FAIL_USER_TIME_IS_CAR_ORDER.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_TIME_IS_CAR_ORDER.getInfo());
                return commonResponse;
            }
            if(truckGrab == 2){
                commonResponse.setCode(GyCode.ORDER_HAS_BEEN_ROBBED.getCode());
                commonResponse.setMessage(GyCode.ORDER_HAS_BEEN_ROBBED.getInfo());
                return commonResponse;
            }
            if(truckGrab < 1){
                commonResponse.setCode(GyCode.FAIL_GRAP_ORDER.getCode());
                commonResponse.setMessage(GyCode.FAIL_GRAP_ORDER.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_GRAP_ORDER.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GRAP_ORDER.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GRAP_ORDER.getCode());
            commonResponse.setMessage(GyCode.FAIL_GRAP_ORDER.getInfo());
        }

        return commonResponse;
    }
}
