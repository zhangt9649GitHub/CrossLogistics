package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.model.Bag;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.GoodsDetails;
import com.siruiman.crosslogistics.model.LogisticInfo;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.BagSerivce;
import com.siruiman.crosslogistics.service.GoodsDetailsService;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.ImputedPriceUtil.getAirFreightPrice;
import static com.siruiman.crosslogistics.util.ImputedPriceUtil.getShippingPrice;

/**
 * 转运管理
 */
@Api(value = "Transshipment", description = "转运管理API", tags = {"转运管理"})
@RestController
@RequestMapping("/transshipment")
public class TransshipmentController {

    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BagSerivce bagService;
    @Autowired
    private LogisticInfoService logisticInfoService;

    @ApiOperation(value = "获取转运货物列表", notes = "获取转运货物列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "goodsDetails", value = "货物详情实体", paramType = "query", dataType = "GoodsDetails"),
    })
    @RequestMapping(value = "/getTransshipmentGoodsList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTransshipmentGoodsList"})
    public LayuiCommonResponse getTransshipmentGoodsList(@Validated @RequestParam(defaultValue = "1") int page,
                                                         @Validated @RequestParam(defaultValue = "10") int limit,
                                                         @Validated GoodsDetails goodsDetails) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectTransshipmentGoodsList(goodsDetails);
            for (GoodsDetails goodsDetails1 : goodsDetailsList
            ) {
                if (!(goodsDetails1.getAddTime() == null) && !(goodsDetails1.getAddTime().equals(""))) {
                    goodsDetails1.setAddTime(goodsDetails1.getAddTime().substring(0, goodsDetails1.getAddTime().indexOf(".")));
                }
            }
            int count = goodsDetailsService.selectTransshipmentGoodsListCount(goodsDetails);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(goodsDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取转运货物物流进程详情", notes = "获取转运货物物流进程详情", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物实体", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getTransshipmentGoodsLogisticInfo", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTransshipmentGoodsDetails"})
    public LayuiCommonResponse getTransshipmentGoodsLogisticInfo(@Validated @RequestParam int goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(logisticInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取转运货物详细信息", notes = "获取转运货物详细信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物实体", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getTransshipmentGoodsDetails", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTransshipmentGoodsDetails"})
    public LayuiCommonResponse getTransshipmentGoodsDetails(@Validated @RequestParam int goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectTransshipmentGoodsDetails(goodsId);
            if (!(goodsDetails.getCreateTime() == null) && !(goodsDetails.getCreateTime().equals(""))) {
                goodsDetails.setCreateTime(goodsDetails.getCreateTime().substring(0, goodsDetails.getCreateTime().indexOf(".")));
            }
            response.setMsg("success");
            response.setCode(0);
            response.setData(goodsDetails);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取转运货物货袋信息", notes = "获取转运货物货袋信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物Id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getBagByTransshipmentGoodsId", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTransshipmentGoodsDetails"})
    public LayuiCommonResponse getBagByTransshipmentGoodsId(@Validated @RequestParam int goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Goods goods = goodsService.selectGoodsById(goodsId);
            Bag bag = new Bag();
            if (goods != null && goods.getBagId() != null) {
                bag = bagService.selectBagById(goods.getBagId());
            }
            response.setMsg("success");
            response.setCode(0);
            response.setData(bag);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

}
