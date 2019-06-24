package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppTaskCarOrder;
import com.siruiman.crosslogistics.model.AppTaskCarOrderDetails;
import com.siruiman.crosslogistics.pojo.AppCommonResponse;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.AppTaskCarOrderService;
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


@Api(value="AppTaskCarOrder",description = "骑手订单",tags={"APP用户端-骑手订单"})
@RestController
@RequestMapping("/appTaskCarOrder")
public class AppTaskCarOrderController {
    @Autowired
    private AppTaskCarOrderService appTaskCarOrderService;


    @ApiOperation(value = "骑手个人订单列表",notes = "appTaskCarOrderByStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
            @ApiImplicitParam(name="type", value="1未开始 2已开始 3已完成",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
    })

    @RequestMapping(value = "/appTaskCarOrderByStatus",method = RequestMethod.GET)
    public AppCommonResponse selectAppTaskCarOrderByStatus(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "15") int limit,
                                                           int type, int appUserId) {
        AppCommonResponse commonResponse = new AppCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<AppTaskCarOrder> selectAppTaskCarOrderByStatus = appTaskCarOrderService.selectAppTaskCarOrderByStatus(type, appUserId);
            Integer count = appTaskCarOrderService.count(type, appUserId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppTaskCarOrderByStatus);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setCount(count);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }
        return commonResponse;
    }

    @ApiOperation(value = "小车任务订单详情",notes = "appTaskCarOrderDetails",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="小车任务订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/appTaskCarOrderDetails",method = RequestMethod.GET)
    public CommonResponse selectAppTaskCarOrderDetails(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();

        try{
            AppTaskCarOrderDetails selectAppTaskCarOrderDetails = appTaskCarOrderService.selectAppTaskCarOrderDetails(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppTaskCarOrderDetails);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }
}
