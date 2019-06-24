package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.TaskOrder;
import com.siruiman.crosslogistics.model.TaskOrderDetail;
import com.siruiman.crosslogistics.model.dto.AppUserDto;
import com.siruiman.crosslogistics.model.dto.TaskOrderDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.TaskOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="TaskOrder",description = "任务订单管理",tags={"任务订单管理"})
@RestController
@RequestMapping("/taskOrder")
public class TaskOrderController {
    @Autowired
    private TaskOrderService taskOrderService;

    @ApiOperation(value = "获取任务订单列表",notes = "taskOrderAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/taskOrderAll",method = RequestMethod.GET)
    public LayuiCommonResponse taskOrderAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "10") int limit,
                                            TaskOrderDto taskOrderDto) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<TaskOrder> selectTaskOrderAll = taskOrderService.selectTaskOrderAll(taskOrderDto);
            Integer count = taskOrderService.count(taskOrderDto);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectTaskOrderAll);
            layuiCommonResponse.setCount(count);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "获取任务订单详情",notes = "taskOrderDetail",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="任务订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/taskOrderDetail",method = RequestMethod.GET)
    public CommonResponse taskOrderDetail(int taskOrderId) {

        CommonResponse commonResponse = new CommonResponse();
        try{
            TaskOrderDetail selectTaskOrderDetail = taskOrderService.selectTaskOrderDetail(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTaskOrderDetail);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

}
