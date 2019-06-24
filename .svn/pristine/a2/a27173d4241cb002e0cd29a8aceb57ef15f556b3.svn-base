package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppTaskCarOrderDetails;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetails;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import com.siruiman.crosslogistics.pojo.AppCommonResponse;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AppTaskOrderService;
import com.siruiman.crosslogistics.service.TruckTaskService;
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

@Api(value="AppTaskOrder",description = "骑手抢单大厅",tags={"app小车用户端-骑手抢单大厅"})
@RestController
@RequestMapping("/appTaskOrder")
public class AppTaskOrderController {
    @Autowired
    private AppTaskOrderService appTaskOrderService;
    @Autowired
    private TruckTaskService truckTaskService;


    @ApiOperation(value = "App任务订单列表",notes = "taskOrderAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/taskOrderAll",method = RequestMethod.GET)
    public AppCommonResponse taskOrderAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "15") int limit,
                                          AppTaskOrderDto appTaskOrderDto) {

        AppCommonResponse commonResponse = new AppCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<TaskOrder> selectTaskOrderAll = appTaskOrderService.selectTaskOrderAll(appTaskOrderDto);
            if(selectTaskOrderAll == null){
                commonResponse.setCode(GyCode.FAIL_USER_ADDRESS.getCode());
                commonResponse.setMessage(GyCode.FAIL_USER_ADDRESS.getInfo());
                return commonResponse;
            }
            Integer count = appTaskOrderService.count(appTaskOrderDto);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTaskOrderAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "App任务订单详情",notes = "appTaskOrderGoods",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="任务订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/appTaskOrderGoods",method = RequestMethod.GET)
    public CommonResponse appTaskOrderGoods(int taskOrderId) {

        CommonResponse commonResponse = new CommonResponse();

        try{
            TaskOrderDetails selectTaskOrderDetails = appTaskOrderService.selectTaskOrderDetails(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTaskOrderDetails);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询区域列表",notes = "singaporeAreaAll",tags={"@郭阳"})
    @RequestMapping(value = "/singaporeAreaAll",method = RequestMethod.GET)
    public CommonResponse selectSingaporeAreaAll() {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<SingaporeArea> selectSingaporeAreaAll = truckTaskService.selectSingaporeAreaAll();

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectSingaporeAreaAll);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }
}
