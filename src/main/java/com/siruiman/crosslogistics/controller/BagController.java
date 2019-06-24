package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
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
import java.util.List;

import static java.lang.Math.abs;


/**
 * @author 张占伟
 * @date 2018/12/20
 */
@RestController
@RequestMapping("/bag")
@Api(value = "bag-API", description = "货袋管理-API", tags = {"货袋管理"})
public class BagController {
    @Autowired
    private BagSerivce bagSerivce;
    @Autowired
    private TruckService truckService;
    @Autowired
    private CarService carService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private SingaporeAreaService singaporeAreaService;

    @ApiOperation(value = "获取货袋列表", notes = "获取货袋列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bag", value = "货袋", dataType = "Bag")
    })
    @RequestMapping(value = "getBagList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagList"})
    public LayuiCommonResponse getBagList(@Validated @RequestParam(defaultValue = "1") int page,
                                          @Validated @RequestParam(defaultValue = "10") int limit,
                                          @Validated Bag bag) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Bag> bagList = bagSerivce.selectBagList(bag);
            int count = bagSerivce.selectCountBagList(bag);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(bagList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取货袋物流进程", notes = "获取货袋物流进程", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bagId", value = "货袋id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "getBagLogisticInfo", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagDetails"})
    public LayuiCommonResponse getBagLogisticInfo(@Validated int bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectBagLogisticInfoByBagId(bagId);
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

    @ApiOperation(value = "查看节点操作人员信息", notes = "查看节点操作人员信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/getOperateNameByBagId", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagDetails"})
    public LayuiCommonResponse getOperateNameByBagId(@Validated int bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectBagLogisticInfoByBagId(bagId);
            int count = logisticInfoService.selectBagOperateNameCount(bagId);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(logisticInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取货袋详情", notes = "获取货袋详情", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bagId", value = "货袋id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "getBagDetails", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagDetails"})
    public LayuiCommonResponse getBagDetails(@Validated int bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Bag bag = bagSerivce.selectBagDetailsById(bagId);
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

    @ApiOperation(value = "获取货袋内货物列表", notes = "获取货袋内货物列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bagId", value = "货袋id", paramType = "query", required = true, dataType = "int")
    })
    @RequestMapping(value = "getGoodsByBag", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagDetails"})
    public LayuiCommonResponse getGoodsByBag(@Validated @RequestParam(defaultValue = "1") int page,
                                             @Validated @RequestParam(defaultValue = "10") int limit,
                                             @Validated int bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Goods> goodsList = goodsService.selectCarGoodsList(bagId);
            for (Goods goods1 : goodsList
            ) {
                LogisticInfo logisticInfo = logisticInfoService.selectLogisticInfoByGoodsId(goods1.getGoodsId());
                if (logisticInfo != null && logisticInfo.getOperateType() != null) {
                    goods1.setOperateResult(logisticInfo.getOperateType());
                }
            }
            int count = goodsService.selectCountCarGoodsList(bagId);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取货袋关联的货车信息", notes = "获取货袋关联的货车信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bagId", value = "货袋id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "getTruckDetailsByBag", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagDetails"})
    public LayuiCommonResponse getTruckDetailsByBag(@Validated int bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Bag bag = bagSerivce.selectBagDetailsById(bagId);
            Truck truck = new Truck();
            if (bag != null && bag.getLicensePlate() != null) {
                truck = truckService.selectTruckDetailsByBag(bag.getLicensePlate());
                String singaporeAreaName = "";
                if (truck.getSingaporeAreaId() != null && !(truck.getSingaporeAreaId().equals(""))) {
                    String[] ids = truck.getSingaporeAreaId().split(",");
                    Integer[] sgids = new Integer[ids.length];
                    for (int j = 0; j < ids.length; j++) {
                        sgids[j] = Integer.valueOf(ids[j]);
                    }
                    for (int i = 0; i < sgids.length; i++) {
                        SingaporeArea singaporeArea = singaporeAreaService.selectById(sgids[i]);
                        if (singaporeAreaName.equals("")) {
                            singaporeAreaName = singaporeArea.getSingaporeAreaName();
                        } else {
                            singaporeAreaName = singaporeAreaName + "," + singaporeArea.getSingaporeAreaName();
                        }
                    }
                }
                truck.setSingaporeAreaName(singaporeAreaName);
            }
            response.setMsg("success");
            response.setCode(0);
            response.setData(truck);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取货袋关联的小车信息", notes = "获取货袋关联的小车信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bagId", value = "货袋id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "getCarDetailsByBag", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getBagDetails"})
    public LayuiCommonResponse getCarDetailsByBag(@Validated int bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Bag bag = bagSerivce.selectBagDetailsById(bagId);
            Car car = new Car();
            if (bag != null && bag.getCarId() != null) {
                if (bag.getState() == 7 || bag.getState() == 9 || bag.getState() == 8) {
                    car = carService.selectCarDetailsById(bag.getCarId());
                }
            }
            response.setMsg("success");
            response.setCode(0);
            response.setData(car);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }


    @ApiOperation(value = "删除货袋信息", notes = "删除货袋信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bagId", value = "货袋id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteBagById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deleteBagById"})
    public LayuiCommonResponse deleteBagById(Integer bagId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            bagSerivce.deleteBagById(bagId);
            response.setCode(HtCode.SUCCESS_DELETE.getCode());
            response.setMsg(HtCode.SUCCESS_DELETE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_DELETE.getCode());
            response.setMsg(HtCode.FAIL_DELETE.getInfo());
            return response;
        }
        return response;
    }


    @ApiOperation(value = "将包裹从货袋中移除", notes = "将包裹从货袋中移除", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", dataType = "Integer", paramType = "query", required = true)
    @RequestMapping(value = "/updateGoodsPackingCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateGoodsPackingCondition"})
    public LayuiCommonResponse updateGoodsPackingCondition(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
           List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
            for (LogisticInfo logisticInfo:logisticInfoList
                 ) {
                if(logisticInfo.getOperateType()!=null&&logisticInfo.getOperateType().equals("货物出库成功")){
                    response.setCode(HtCode.FAIL_REMOVE_OUTBOUND.getCode());
                    response.setMsg(HtCode.FAIL_REMOVE_OUTBOUND.getInfo());
                    return response;
                }
            }
            goodsService.updateGoodsPackingCondition(goodsId);
            response.setCode(HtCode.SUCCESS_REMOVE.getCode());
            response.setMsg(HtCode.SUCCESS_REMOVE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_REMOVE.getCode());
            response.setMsg(HtCode.FAIL_REMOVE.getInfo());
            return response;
        }
        return response;
    }

}
