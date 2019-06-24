package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.GoodsFromService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "GoodsFrom", description = "货物表单管理", tags = {"货物表单管理"})
@RestController
@RequestMapping("/goodsFrom")
public class GoodsFromController {

    @Autowired
    private GoodsFromService goodsFromService;


    @ApiOperation(value = "获取货物表单列表", notes = "获取货物表单列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "fromNumber", value = "表单单号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "driverName", value = "司机姓名", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getGoodsFromList", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getGoodsFromList(@Validated @RequestParam(defaultValue = "1") int page,
                                                @Validated @RequestParam(defaultValue = "10") int limit,
                                                @Validated String fromNumber, @Validated String driverName) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<GoodsFrom> goodsFromList = goodsFromService.selectGoodsFromList(fromNumber, driverName);
            int count = goodsFromService.selectCountGoodsFromList(fromNumber, driverName);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(goodsFromList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "查看简易APP货物表单内的货物信息列表", notes = "查看简易APP货物表单内的货物信息列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "formId", value = "表单id", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "/getGoodsFromInfo", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getGoodsFromInfo(@Validated @RequestParam(defaultValue = "1") int page,
                                                @Validated @RequestParam(defaultValue = "20") int limit, @Validated int formId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<GoodsFromInfo> goodsFromInfoList = goodsFromService.selectGoodsFromInfo(formId);
            int count = goodsFromService.selectCountGoodsFromInfo(formId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(goodsFromInfoList);
            response.setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }


    @ApiOperation(value = "查看货车直接送货的货物表单内的货物信息列表", notes = "查看货车直接送货的货物表单内的货物信息列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "formId", value = "表单id", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "/getTruckDistributionGoodsFromInfo", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getTruckDistributionGoodsFromInfo(@Validated @RequestParam(defaultValue = "1") int page,
                                                                 @Validated @RequestParam(defaultValue = "20") int limit, @Validated int formId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<GoodsFromInfo> goodsFromInfoList = goodsFromService.selectGoodsFromInfo(formId);
            int count = goodsFromService.selectCountGoodsFromInfo(formId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(goodsFromInfoList);
            response.setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }
}
