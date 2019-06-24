package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.Result.HttpResult;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.LogisticInfo2;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/http")
public class HttpClientController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;

    @CrossOrigin()
    @RequestMapping(value = "/getLogisticsInformation",method = RequestMethod.POST)
    public HttpResult getLogisticsInformation (@Validated @RequestParam String tripartiteNumber)  {
        try{
            if(tripartiteNumber==null||tripartiteNumber.equals("")){
                return new HttpResult(1, "出错了");
            }
            List<Goods> goods =  goodsService.selectByTripartiteNumber(tripartiteNumber);
//                没有返回没有当前订单消息
            if(goods.size()==0){
                return new HttpResult(1, "未找到此货物信息");
            }
            List<LogisticInfo2> list = new ArrayList<>();
            if(goods.size()>1){
                list = logisticInfoService.selectLogisticInfoByTripartiteNumber(tripartiteNumber);

            }else if (goods.size()==1){
                list  =logisticInfoService.selectLogisticInfo(tripartiteNumber);
            }
          return new HttpResult(0,"成功",list);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new HttpResult(1, "出错了");
    }
}
