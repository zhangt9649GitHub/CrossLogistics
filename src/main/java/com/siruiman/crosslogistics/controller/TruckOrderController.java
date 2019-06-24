package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.model.AppTruckOrderBagDetails;
import com.siruiman.crosslogistics.model.AppTruckOrderDetails;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AppTruckOrderService;
import com.siruiman.crosslogistics.service.TruckOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="TruckOrder",description = "货车订单管理",tags={"订单管理-货车订单管理"})
@RestController
@RequestMapping("/truckOrder")
public class TruckOrderController {
    @Autowired
    private AppTruckOrderService appTruckOrderService;
    @Autowired
    private TruckOrderService truckOrderService;

    @ApiOperation(value = "获取货车订单列表",notes = "appTruckOrderAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/appTruckOrderAll",method = RequestMethod.GET)
    public LayuiCommonResponse selectAppTruckOrderAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "15") int limit,
                                                      TruckOrderDto truckOrderDto) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<AppTruckOrder> selectAppTruckOrderAll = truckOrderService.selectAppTruckOrderAll(truckOrderDto);
            Integer countAppTruckOrderAll = truckOrderService.countAppTruckOrderAll(truckOrderDto);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectAppTruckOrderAll);
            layuiCommonResponse.setCount(countAppTruckOrderAll);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取货车任务订单详情",notes = "appTruckOrderDetails",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="任务订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/appTruckOrderDetails",method = RequestMethod.GET)
    public CommonResponse selectAppTruckOrderDetails(int taskOrderId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            AppTruckOrderDetails selectAppTruckOrderDetails = appTruckOrderService.selectAppTruckOrderDetails(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppTruckOrderDetails);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "获取订单详情里货袋详情",notes = "appTruckOrderBagDetails",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="bagId", value="货袋id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/appTruckOrderBagDetails",method = RequestMethod.GET)
    public CommonResponse selectAppTruckOrderBagDetails(int bagId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            AppTruckOrderBagDetails selectAppTruckOrderBagDetails = appTruckOrderService.selectAppTruckOrderBagDetails(bagId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppTruckOrderBagDetails);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前订单的送货司机",notes = "selectTruckOrderDriver",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectTruckOrderDriver",method = RequestMethod.GET)
    public CommonResponse selectTruckOrderDriver(int taskOrderId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            AppUser selectAppUser = truckOrderService.selectAppUser(taskOrderId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppUser);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询根据订单时间当天没有订单的货车用户",notes = "selectNoTruckOrderDriver",tags={"@郭阳"})
    @ApiImplicitParam(name="createTime", value="订单时间",paramType="query",dataType="String")
    @RequestMapping(value = "/selectNoTruckOrderDriver",method = RequestMethod.GET)
    public CommonResponse selectNoTruckOrderDriver(String createTime) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            List<AppUser> selectTruckAppUser = truckOrderService.selectTruckAppUser(createTime);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTruckAppUser);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改货车订单送货司机",notes = "editTruckOrderDriver",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="newAppUserId", value="用户id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editTruckOrderDriver",method = RequestMethod.POST)
    public CommonResponse editTruckOrderDriver(int taskOrderId, int newAppUserId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editTruckOrderDriver = truckOrderService.editTruckOrderDriver(taskOrderId, newAppUserId);
            if(editTruckOrderDriver < 1){
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
}
