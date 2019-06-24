package com.siruiman.crosslogistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.ChinaArea;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.LogisticInfo;
import com.siruiman.crosslogistics.model.LogisticInfo2;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张占伟
 * @date 2019/3/21 9:37
 */
@RestController
@RequestMapping("Logistic")
@Api(value="LogisticInfo-api",description = "物流进程-API",tags={"物流进程查询"})
public class LogisticInfoControlller {
    @Autowired
    private LogisticInfoService logisticInfoService;

    @Autowired
    private GoodsService goodsService;

    //根据货物编号查询进程操作信息


/* 暂时注释掉接口
 @ApiOperation(value = "获取货物物流信息",nickname = "getLogisticInfo",notes = "根据物流单号查询物流进程",tags = {"@张占伟"})
    @RequestMapping(value = "getLogisticInfo",method = RequestMethod.GET)
    public LayuiCommonResponse getLogisticInfo (@Validated @RequestParam String deliveryNum){
        LayuiCommonResponse response = new LayuiCommonResponse();
        if (deliveryNum==null||deliveryNum!=""){
            response.setMsg(ZwCode.ERROR_DELIVERY_NUM.getInfo());
            response.setCode(-1);
        }
        try {

            List<LogisticInfo> list = logisticInfoService.selectLogisticInfoByDeliveryNum(deliveryNum);
//           如果查出来的物流进程为空
            if(list==null){
//                就查询数据库有无该物流单
                Goods goods = goodsService.selectGoodsByDeliveryNumber(deliveryNum);
//                没有返回没有当前订单消息
                if(goods==null){
                    response.setCode(ZwCode.WARNING_DELIVERY_NUM.getCode());
                    response.setMsg(ZwCode.WARNING_DELIVERY_NUM.getInfo());

                }else {
//                    有即为货物未入库
                    response.setCode(0);
                    response.setMsg(ZwCode.SUCCESS_GET.getInfo());
                    return response;
                }
            }
//            查出来的不为空
            response.setCode(0);
            response.setData(list);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_GET.getInfo());
            response.setCode(-1);
        }
        return response;
    }*/


    @CrossOrigin()
    @ApiOperation(value = "获取货物物流信息",nickname = "getLogisticInfoCross",notes = "根据物流单号查询物流进程",tags = {"@张占伟"})
    @RequestMapping(value = "getLogisticInfoCross",method = RequestMethod.GET)
    public LayuiCommonResponse getLogisticInfoCross ( @Validated @RequestParam String deliveryNum)  {
        LayuiCommonResponse response = new LayuiCommonResponse();
        if (deliveryNum==null||deliveryNum!=""){
            response.setMsg(ZwCode.ERROR_DELIVERY_NUM.getInfo());
            response.setCode(-1);
        }
        try {
            List<LogisticInfo> list = logisticInfoService.selectLogisticInfoByDeliveryNum(deliveryNum);
//           如果查出来的物流进程为空
            if(list.size()==0||list==null){
//                就查询数据库有无该物流单
                Goods goods = goodsService.selectGoodsByDeliveryNumber(deliveryNum);
//                没有返回没有当前订单消息
                if(goods==null){
                    response.setCode(ZwCode.WARNING_DELIVERY_NUM.getCode());
                    response.setMsg(ZwCode.WARNING_DELIVERY_NUM.getInfo());
                    return response;
                }else {
//                    有即为货物未入库
                    response.setCode(0);
                    response.setMsg(ZwCode.SUCCESS_GET.getInfo());
                    return response;
                }
            }
//            查出来的不为空
            response.setCode(0);
            response.setData(list);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_GET.getInfo());
            response.setCode(-1);
        }
        return response;
    }


    @CrossOrigin()
    @ApiOperation(value = "三方物流单号获取货物物流信息",nickname = "getLogisticInfoCross",notes = "根据三方物流单号查询物流进程",tags = {"@张占伟"})
    @RequestMapping(value = "getLogisticInfoCrossByThreeNum",method = RequestMethod.GET)
    public LayuiCommonResponse getLogisticInfoCrossByThreeNum ( @Validated @RequestParam String tripartiteNumber)  {
        LayuiCommonResponse response = new LayuiCommonResponse();
        if (tripartiteNumber==null||tripartiteNumber!=""){
            response.setMsg(ZwCode.ERROR_DELIVERY_NUM.getInfo());
            response.setCode(-1);
        }
        try {
           // 就查询数据库有无该物流单
            List<Goods> goods =  goodsService.selectByTripartiteNumber(tripartiteNumber);
//                没有返回没有当前订单消息
            if(goods.size()==0){
                response.setCode(ZwCode.WARNING_DELIVERY_NUM.getCode());
                response.setMsg(ZwCode.WARNING_DELIVERY_NUM.getInfo());
                return response;
            }
            List<LogisticInfo2> list = new ArrayList<>();
            if(goods.size()>1){
                list = logisticInfoService.selectLogisticInfoByTripartiteNumber(tripartiteNumber);

            }else if (goods.size()==1){
                list  =logisticInfoService.selectLogisticInfo(tripartiteNumber);
            }
            response.setCode(0);
            response.setData(list);
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setMsg(ZwCode.FAIL_GET.getInfo());
            response.setCode(-1);
        }
        return response;
    }
}
