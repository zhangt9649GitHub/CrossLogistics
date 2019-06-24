package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.model.AppTruckOrderBagDetails;
import com.siruiman.crosslogistics.model.AppTruckOrderDetails;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import com.siruiman.crosslogistics.pojo.AppCommonResponse;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AppTruckOrderService;
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

@Api(value="AppTruckOrder",description = "货车抢单大厅",tags={"APP货车端-货车抢单大厅"})
@RestController
@RequestMapping("/appTruckOrder")
public class AppTruckOrderController {
    @Autowired
    private AppTruckOrderService appTruckOrderService;

    @ApiOperation(value = "获取货车订单列表",notes = "appTruckOrderAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/appTruckOrderAll",method = RequestMethod.GET)
    public AppCommonResponse selectAppTruckOrderAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "15") int limit,
                                                    AppTaskOrderDto appTruckOrderDto) {

        AppCommonResponse commonResponse = new AppCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<AppTruckOrder> selectAppTruckOrderAll = appTruckOrderService.selectAppTruckOrderAll(appTruckOrderDto);
            if(selectAppTruckOrderAll == null){
                commonResponse.setCode(GyCode.FAIL_USER_ADDRESS.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_ADDRESS.getInfo());
                return commonResponse;
            }
            Integer count = appTruckOrderService.count(appTruckOrderDto);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppTruckOrderAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
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
    @ApiImplicitParam(name="bagId", value="货袋id",paramType="query",dataType="int")
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
}
