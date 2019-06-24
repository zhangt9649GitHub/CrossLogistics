package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.BigDecimalUtil.round;
import static com.siruiman.crosslogistics.util.StaticConfigUtil.anOrderPrice;

@Api(value="CarOrder",description = "小车订单管理",tags={"订单管理-小车订单管理"})
@RestController
@RequestMapping("/carOrder")
public class CarOrderController {
    @Autowired
    private AppTaskOrderService appTaskOrderService;
    @Autowired
    private TruckTaskService truckTaskService;
    @Autowired
    private CarOrderService carOrderService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private TaskCarOrderService taskCarOrderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AppUserWalletService appUserWalletService;




    @ApiOperation(value = "App任务订单列表",notes = "taskOrderAll",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="第几页",paramType="query",dataType="int"),
            @ApiImplicitParam(name="limit", value="每页容量",paramType="query",dataType="int"),
})
    @RequestMapping(value = "/taskOrderAll",method = RequestMethod.GET)
    public LayuiCommonResponse taskOrderAll(@Validated @RequestParam(defaultValue = "1") int page, @Validated @RequestParam(defaultValue = "15") int limit,
                                            TruckOrderDto truckOrderDto) {

        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            PageHelper.startPage(page,limit);
            List<TaskOrder> selectTaskOrderAll = carOrderService.selectTaskOrderAll(truckOrderDto);

            Integer countTaskOrderAll = carOrderService.countTaskOrderAll(truckOrderDto);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectTaskOrderAll);
            layuiCommonResponse.setCount(countTaskOrderAll);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
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

    @ApiOperation(value = "查询小车集结点列表",notes = "rallyPointIdAll",tags={"@郭阳"})
    @ApiImplicitParam(name="singaporeAreaId", value="区域id",paramType="query",dataType="int")
    @RequestMapping(value = "/rallyPointIdAll",method = RequestMethod.GET)
    public CommonResponse selectRallyPointIdAll(int singaporeAreaId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<RallyPoint> selectRallyPointBySingaporeArea = carOrderService.selectRallyPointBySingaporeArea(singaporeAreaId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectRallyPointBySingaporeArea);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "根据订单id查询当前订单的派送人",notes = "selectAppUserByCarOrderId",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectAppUserByCarOrderId",method = RequestMethod.GET)
    public CommonResponse selectAppUserByCarOrderId(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            AppUser selectAppUser = carOrderService.selectAppUser(taskOrderId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectAppUser);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改订单派送人",notes = "editCarOrderUser",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/editCarOrderUser",method = RequestMethod.POST)
    public CommonResponse editCarOrderUser(int taskOrderId, int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editCarOrderUser = carOrderService.editCarOrderUser(taskOrderId, appUserId);
            if(editCarOrderUser < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            String selectCarOrderTime = carOrderService.selectCarOrderTime(taskOrderId);
            if(createTime.equals(selectCarOrderTime.substring(0, 10))){
                Integer insertMessage = messageService.insertMessage(6, appUserId, 0, 0, taskOrderId, 0, 0);
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

    @ApiOperation(value = "查询根据订单时间当天没有订单的小车用户",notes = "selectCarUser",tags={"@郭阳"})
    @ApiImplicitParam(name="createTime", value="订单时间",paramType="query",dataType="String")
    @RequestMapping(value = "/selectCarUser",method = RequestMethod.GET)
    public CommonResponse selectCarUser(String createTime) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<AppUser> selectAppUser = carOrderService.selectAppUser(createTime);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectAppUser);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "根据订单id查询当前订单送货小车",notes = "selectCarByCarOrder",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectCarByCarOrder",method = RequestMethod.GET)
    public CommonResponse selectCarByCarOrder(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Car selectCarByCarOrder = carOrderService.selectCarByCarOrder(taskOrderId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectCarByCarOrder);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前时间没有订单的小车",notes = "selectCarNoCarOrder",tags={"@郭阳"})
    @ApiImplicitParam(name="createTime", value="订单时间",paramType="query",dataType="String")
    @RequestMapping(value = "/selectCarNoCarOrder",method = RequestMethod.GET)
    public CommonResponse selectCarNoCarOrder(String createTime) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<Car> selectCarNoCarOrder = carOrderService.selectCarNoCarOrder(createTime);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectCarNoCarOrder);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改订单送货小车",notes = "editCarOrderCar",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="carId", value="小车id",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/editCarOrderCar",method = RequestMethod.POST)
    public CommonResponse editCarOrderCar(int taskOrderId, int carId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editCarOrderCar = carOrderService.editCarOrderCar(taskOrderId, carId);
            if(editCarOrderCar == 2){
                commonResponse.setCode(GyCode.FAIL_CAR_ORDER_NOT_BAG.getCode());
                commonResponse.setMessage(GyCode.FAIL_CAR_ORDER_NOT_BAG.getInfo());
                return commonResponse;
            }
            if(editCarOrderCar == 3){
                commonResponse.setCode(GyCode.SUCCESS_CAR_ORDER.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_CAR_ORDER.getInfo());
                return commonResponse;
            }
            if(editCarOrderCar < 1){
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

    @ApiOperation(value = "App任务订单货物清单",notes = "App任务订单货物清单",tags={"@郝腾"})
    @ApiImplicitParam(name="taskOrderId", value="任务订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/getGoodsListByTaskOrderId",method = RequestMethod.GET)
    public LayuiCommonResponse getGoodsListByTaskOrderId(int taskOrderId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try{
            List<Goods> goodsList = new ArrayList<>();
            int count = 0;
            TaskCarOrder taskCarOrder= taskCarOrderService.selectCarIdById(taskOrderId);
            if(taskCarOrder!=null&&taskCarOrder.getBagId()!=null){
               goodsList = goodsService.selectCarGoodsList(taskCarOrder.getBagId());
                for (Goods goods:goodsList
                     ) {
                    if(taskCarOrder!=null&&taskCarOrder.getState()!=null&&taskCarOrder.getState()==7){
                        goods.setDeductState(1);
                    }else{
                        if(goods.getComment()!=null&&!(goods.getComment().equals(""))){
                            goods.setDeductState(1);
                        }else if(goods.getStatus()==2){
                            goods.setDeductState(1);
                        }else {
                            goods.setDeductState(2);
                        }
                    }
                }
               count = goodsService.selectCountCarGoodsList(taskCarOrder.getBagId());
            }
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(goodsList);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }

        return response;
    }


    @ApiOperation(value = "确认扣除",notes = "确认扣除",tags={"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId", value="货物id",paramType="query",required = true,dataType="int"),
            @ApiImplicitParam(name="comment", value="扣除原因",paramType="query",dataType="String"),
            @ApiImplicitParam(name="taskOrderId", value="任务订单id",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/updateGoodsComment",method= RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateGoodsComment"})
    public LayuiCommonResponse updateGoodsComment(int goodsId,String comment,int taskOrderId){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
           String commment1 = comment.trim();
           if(commment1==null||commment1.equals("")){
               response.setCode(HtCode.FAIL_TEXT_DEDUCT.getCode());
               response.setMsg(HtCode.FAIL_TEXT_DEDUCT.getInfo());
               return response;
           }
         int result = goodsService.updateGoodsCommentById(goodsId,comment);
         TaskCarOrder taskCarOrder= taskCarOrderService.selectCarIdById(taskOrderId);
         if (result==1){
             if(taskCarOrder!=null&&taskCarOrder.getAppUserId()!=null){
                 BigDecimal commissionAmount = new BigDecimal(anOrderPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
                Integer result2 = appUserWalletService.updateCommissionAndIntegral(taskCarOrder.getAppUserId(),commissionAmount,0,"小车",2,taskOrderId,goodsId);
                if(result2==2){
                    response.setCode(HtCode.FAIL_DEDUCT.getCode());
                    response.setMsg(HtCode.FAIL_DEDUCT.getInfo());
                    return response;
                }
                 Integer result3 =  messageService.insertAbnormalMessage(4,taskCarOrder.getAppUserId(),comment,goodsId);
             }
             response.setMsg(HtCode.SUCCESS_DEDUCT.getInfo());
             response.setCode(HtCode.SUCCESS_DEDUCT.getCode());
         }
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(HtCode.FAIL_DEDUCT.getCode());
            response.setMsg(HtCode.FAIL_DEDUCT.getInfo());
        }
        return response;
    }

}
