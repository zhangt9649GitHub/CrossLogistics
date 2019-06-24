package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
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

import java.util.List;

@Api(value = "Truck", description = "货车API", tags = {"货车管理"})
@RestController
@RequestMapping("/truck")
public class TruckController {
    @Autowired
    private TruckService truckService;
    @Autowired
    private BagSerivce bagSerivce;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PositionInfoService positionInfoService;
    @Autowired
    private TaskTruckOrderService taskTruckOrderService;
    @Autowired
    private SingaporeAreaService singaporeAreaService;
    @Autowired
    private AppUserCertificationService appUserCertificationService;
    @Autowired
    private LogisticInfoService logisticInfoService;

    @ApiOperation(value = "获取货车列表", notes = "获取Truck对象列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "truck", value = "货车实体", paramType = "query", dataType = "Truck"),
    })
    @RequestMapping(value = "/getTruckList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTruckList"})
    public LayuiCommonResponse getTruckList(@Validated @RequestParam(defaultValue = "1") int page,
                                            @Validated @RequestParam(defaultValue = "10") int limit,
                                            @Validated Truck truck) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Truck> truckList = truckService.selectTruckList(truck);
            for (Truck truck1 : truckList
            ) {
                String singaporeAreaName = "";
                if (truck1.getSingaporeAreaId() != null && !(truck1.getSingaporeAreaId().equals(""))) {
                    String[] ids = truck1.getSingaporeAreaId().split(",");
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
                truck1.setSingaporeAreaName(singaporeAreaName);
                TaskTruckOrder taskTruckOrder = taskTruckOrderService.selectTaskTruckOrderByUserId(truck1.getAppUserId());
                if (taskTruckOrder != null) {
                    truck1.setOrderNumber(taskTruckOrder.getOrderNumber());
                }
            }
            int count = truckService.selectCountTruckList(truck);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(truckList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "查看货车详细信息", notes = "查看货车详细信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "truckId", value = "货车id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getTruckById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTruckById"})
    public LayuiCommonResponse getTruckById(Integer truckId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Truck truck = truckService.selectTruckDetailsById(truckId);
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
            TaskTruckOrder taskTruckOrder = taskTruckOrderService.selectTaskTruckOrderByUserId(truck.getAppUserId());
            if (taskTruckOrder != null) {
                truck.setOrderNumber(taskTruckOrder.getOrderNumber());
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

    @ApiOperation(value = "查看货车中的货袋列表", notes = "获取货袋列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "truckId", value = "货车id", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "/getBagListByTruckId", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTruckById"})
    public LayuiCommonResponse getBagListByTruckId(@Validated @RequestParam(defaultValue = "1") int page,
                                                   @Validated @RequestParam(defaultValue = "10") int limit,
                                                   @Validated Integer truckId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Bag> bagList = bagSerivce.selectBagListByTruckId(truckId);
            int count = bagSerivce.selectCountBagListByTruckId(truckId);
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

    @ApiOperation(value = "查看货车中的货物列表", notes = "获取货物列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "truckId", value = "货车id", required = true, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/getGoodsListByTruckId", method = RequestMethod.GET)
    @ResponseBody

    public LayuiCommonResponse getGoodsListByTruckId(@Validated @RequestParam(defaultValue = "1") int page,
                                                     @Validated @RequestParam(defaultValue = "10") int limit,
                                                     @Validated Integer truckId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            Truck truck = truckService.selectTruckDetailsById(truckId);
            AppUserCertification appUserCertification = new AppUserCertification();
            if (truck.getLicensePlate() != null) {
                appUserCertification = appUserCertificationService.selectAppUserCertificationByLicensePlate(truck.getLicensePlate());
            }
            List<Goods> goodsList = goodsService.selectGoodsListByTruckId(truckId, appUserCertification.getAppUserId());
            for (Goods goods1 : goodsList
            ) {
                LogisticInfo logisticInfo = logisticInfoService.selectLogisticInfoByGoodsId(goods1.getGoodsId());
                if (logisticInfo != null && logisticInfo.getOperateType() != null) {
                    goods1.setOperateResult(logisticInfo.getOperateType());
                }
            }
            int count = goodsService.selectCountGoodsListByTruckId(truckId);
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

    @ApiOperation(value = "查看货车实时位置", notes = "查看货车实时位置", tags = {"@郝腾"})
    @ApiImplicitParam(name = "truckId", value = "货车id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getTruckPosition", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getTruckById"})
    public LayuiCommonResponse getTruckPosition(Integer truckId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PositionInfo positionInfo = positionInfoService.selectTruckPosition(truckId);
            response.setMsg("success");
            response.setCode(0);
            response.setData(positionInfo);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }
}
