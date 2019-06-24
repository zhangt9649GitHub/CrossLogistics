package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.mapper.AdminUserMapper;
import com.siruiman.crosslogistics.mapper.GoodsDetailsMapper;
import com.siruiman.crosslogistics.mapper.GoodsMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.PDAGoodsDto;
import com.siruiman.crosslogistics.model.dto.ThreeGoodsDto;
import com.siruiman.crosslogistics.model.dto.TransshipmentGoodsDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.abs;

/**
 * 正常货物管理
 */
@Api(value = "GoodsManagement", description = "货物管理API", tags = {"货物管理"})
@RestController
@RequestMapping("/goods")
public class GoodsManagementController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private CarService carService;
    @Autowired
    private SingaporeAreaService singaporeAreaService;
    @Autowired
    private UploadFilesService uploadFilesService;
    @Autowired
    private WarehousePositionService warehousePositionService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BagSerivce bagSerivce;
    @Autowired
    private StaffService staffService;
    @Autowired
    private AdminUserService adminUserService;


    @ApiOperation(value = "获取货物列表", notes = "获取Goods对象列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "goods", value = "货物实体", paramType = "query", dataType = "Goods"),
    })
    @RequestMapping(value = " ", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsList"})
    public LayuiCommonResponse getGoodsList(@Validated @RequestParam(defaultValue = "1") int page,
                                            @Validated @RequestParam(defaultValue = "10") int limit,
                                            @Validated Goods goods) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            switch (goods.getOperateResultNumber()){
                case 0 : goods.setOperateResult(null);break;
                case 1 : goods.setOperateResult("货物入库成功");break;
                case 2 : goods.setOperateResult("货物打包成功");break;
                case 3 : goods.setOperateResult("货物出库成功");break;
                case 4 : goods.setOperateResult("货物送出");break;
                case 5 : goods.setOperateResult("货物入新加坡仓库");break;
                case 6 : goods.setOperateResult("货物出库");break;
                case 7 : goods.setOperateResult("货物直接配送");break;
                case 8 : goods.setOperateResult("货物配送中");break;
                case 9 : goods.setOperateResult("货物配送完成");break;
                case 10 : goods.setOperateResult("货物入库异常");break;
                case 11 : goods.setOperateResult("(骑手)货物派送异常");break;
                case 12 : goods.setOperateResult("货物问题件接收成功");break;
                case 13 : goods.setOperateResult("货物派送异常");break;
                case 14 : goods.setOperateResult("货物装配小车");break;
                case 15 : goods.setOperateResult("合并转运货物");break;
                case 16 : goods.setOperateResult("货物发起转运");break;
            }
            List<Goods> goodsList = goodsService.selectGoodsList(goods);
            int count = goodsService.selectCount(goods);
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

    @ApiOperation(value = "导入货物信息", notes = "导入Excel表格", tags = {"@郝腾"})
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"importExcel"})
    public LayuiCommonResponse insertGoodsList(@ApiParam(value = "MultipartFile", required = true) MultipartFile file) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            String fileName = file.getOriginalFilename();
            String str = goodsService.insertBatchImport(fileName, file);
            if (!(str.equals("1"))) {
                response.setCode(HtCode.FAIL_IMPORT.getCode());
                response.setMsg(str);
                return response;
            }
            response.setCode(HtCode.SUCCESS_IMPORT.getCode());
            response.setMsg(HtCode.SUCCESS_IMPORT.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_IMPORT.getCode());
            response.setMsg(HtCode.FAIL_IMPORT.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "删除货物信息", notes = "删除Goods对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteGoodsById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deleteGoodsById"})
    public LayuiCommonResponse deleteGoodsById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            //查询货物的物流进程
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
            //遍历物流进程，如果已入库，将无法删除
            for (LogisticInfo logisticInfo : logisticInfoList
            ) {
                if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                    response.setCode(HtCode.FAIL_GOODS_DELETE.getCode());
                    response.setMsg(HtCode.FAIL_GOODS_DELETE.getInfo());
                    return response;
                }
            }
            //根据货物id查询货物详情
            Goods goods = goodsService.selectGoodsById(goodsId);
            List<Goods> goodsList = new ArrayList<>();
            //三方货物单号查询货物集合
            if (goods != null && goods.getTripartiteNumber() != null) {
                goodsList = goodsService.selectGoodsListByTripartiteNumber(goods.getTripartiteNumber());
            }
            String deliveryNumber = "";
            int totalGoods = 0;
            if (goodsList.size() > 1) {
                for (Goods goods1 : goodsList
                ) {
                    if (goods1.getTotalGoods() == 1) {
                        List<LogisticInfo> logisticInfoList1 = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods1.getGoodsId());
                        for (LogisticInfo logisticInfo : logisticInfoList1
                        ) {
                            if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                                response.setCode(HtCode.FAIL_DELETE_POINTS.getCode());
                                response.setMsg(HtCode.FAIL_DELETE_POINTS.getInfo());
                                return response;
                            }
                        }
                    } else if (goods1.getTotalGoods() > 1) {
                        deliveryNumber = goods1.getDeliveryNumber();
                        totalGoods = goods1.getTotalGoods();
                    }

                }

            }
            if (!(deliveryNumber.equals("")) && totalGoods != 0) {
                if (goods.getDeliveryNumber().equals(deliveryNumber + "-" + totalGoods)) {
                    goodsService.deleteGoodsById(goodsId);
                } else {
                    response.setCode(HtCode.FAIL_DELETE_MAX_POINTS.getCode());
                    response.setMsg(HtCode.FAIL_DELETE_MAX_POINTS.getInfo() + deliveryNumber + "-" + totalGoods);
                    return response;
                }
            } else {
                goodsService.deleteGoodsById(goodsId);
            }
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

    @ApiOperation(value = "查看货物物流进程", notes = "查看货物物流进程信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getGoodsLogisticById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsDetailById"})
    public LayuiCommonResponse getGoodsLogisticById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Goods goods1 = goodsService.selectGoodsById(goodsId);
            if (goods1.getFrom().equals("转运订单") && goods1.getTransportType().equals("组合转运")) {
                List<Goods> goodsList = goodsService.selectGoodsByParentId(goodsId);
                List<LogisticInfo> logisticInfoList1 = new ArrayList<>();
                List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
                for (LogisticInfo logisticInfo : logisticInfoList
                ) {
                    logisticInfoList1.add(logisticInfo);
                }
                for (Goods goods2 : goodsList
                ) {
                    List<LogisticInfo> logisticInfoList3 = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods2.getGoodsId());
                    for (LogisticInfo logisticInfo : logisticInfoList3
                    ) {
                        logisticInfoList1.add(logisticInfo);
                    }
                }
                Collections.sort(logisticInfoList1);
                response.setMsg("success");
                response.setCode(0);
                response.setData(logisticInfoList1);
                return response;
            }
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

    @ApiOperation(value = "查看货物信息及三方物流信息", notes = "查看货物信息及三方物流信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getGoodsById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsDetailById"})
    public LayuiCommonResponse getGoodsById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Goods goods = goodsService.selectGoodsById(goodsId);
            if (goods != null && goods.getGoodsUfId() != null) {
                List<String> images = new ArrayList<>();
                UploadFiles uploadFiles = uploadFilesService.selectUfPathById(goods.getGoodsUfId());
                images.add(uploadFiles.getUfSavePath());
                goods.setImages(images);
            }
            if (goods != null && goods.getUfId() != null) {
                UploadFiles uploadFiles1 = uploadFilesService.selectUfPathById(goods.getUfId());
                goods.setSignPictures(uploadFiles1.getUfSavePath());
            }
            response.setMsg("success");
            response.setCode(0);
            response.setData(goods);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "查看货物发货人信息及收货人信息", notes = "查看货物发货人信息及收货人信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getGoodsDetailById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsDetailById"})
    public LayuiCommonResponse getGoodsDetailById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsId);
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

    @ApiOperation(value = "查看关联货车信息", notes = "查看关联货车信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getTruckById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsDetailById"})
    public LayuiCommonResponse getTruckById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Truck truck = truckService.selectTruckById(goodsId);
            String singaporeAreaName = "";
            if (truck != null && truck.getSingaporeAreaId() != null && !(truck.getSingaporeAreaId().equals(""))) {
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

    @ApiOperation(value = "查看关联小车信息", notes = "查看关联小车信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getCarById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsDetailById"})
    public LayuiCommonResponse getCarById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Car car = carService.selectCarById(goodsId);
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

    @ApiOperation(value = "查看节点操作人员信息", notes = "查看节点操作人员信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/getOperateNameById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsDetailById"})
    public LayuiCommonResponse getOperateNameById(@Validated @RequestParam(defaultValue = "1") int page,
                                                  @Validated @RequestParam(defaultValue = "10") int limit,
                                                  @Validated Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectOperateNameById(goodsId);
            int count = logisticInfoService.selectOperateNameCount(goodsId);
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

    //后台增加三方货物入库功能

    @ApiOperation(value = "三方货物入库", notes = "三方货物入库", tags = {"@郝腾"})
    @ApiImplicitParam(name = "ThreeGoodsDto", value = "三方货物详情实体", dataType = "threeGoodsDto")
    @RequestMapping(value = "/updateThreeGoodsInfo", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateThreeGoodsInfo"})
    public LayuiCommonResponse updateThreeGoodsInfo(@RequestBody ThreeGoodsDto threeGoodsDto) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(threeGoodsDto.getGoodsId());
            for (LogisticInfo logisticInfo : logisticInfoList
            ) {
                if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                    response.setCode(HtCode.FAIL_PUT_REPEAT.getCode());
                    response.setMsg(HtCode.FAIL_PUT_REPEAT.getInfo());
                    return response;
                }
            }
            String str = goodsService.updateThreeGoodsInfo(threeGoodsDto);
            if (str.equals("长宽高重量不能小于等于零或为空")) {
                response.setCode(HtCode.FAIL_NULL.getCode());
                response.setMsg(HtCode.FAIL_NULL.getInfo());
                return response;
            }
            response.setCode(HtCode.SUCCESS_PUT.getCode());
            response.setMsg(HtCode.SUCCESS_PUT.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_PUT.getCode());
            response.setMsg(HtCode.FAIL_PUT.getInfo());
            return response;
        }
        return response;
    }

    //后台增加转运货物入库功能

    @ApiOperation(value = "转运货物入库", notes = "转运货物入库", tags = {"@郝腾"})
    @ApiImplicitParam(name = "TransshipmentGoodsDto", value = "三方货物详情实体", dataType = "transshipmentGoodsDto")
    @RequestMapping(value = "/insertTransshipmentGoodsInfo", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"insertTransshipmentGoodsInfo"})
    public LayuiCommonResponse insertTransshipmentGoodsInfo(@RequestBody TransshipmentGoodsDto transshipmentGoodsDto) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Goods goods = goodsService.selectGoodsByTripartiteNumber(transshipmentGoodsDto.getTripartiteNumber());
            if (goods != null && goods.getGoodsId() != null) {
                List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods.getGoodsId());
                for (LogisticInfo logisticInfo : logisticInfoList
                ) {
                    if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                        response.setCode(HtCode.FAIL_PUT_REPEAT.getCode());
                        response.setMsg(HtCode.FAIL_PUT_REPEAT.getInfo());
                        return response;
                    }
                }
            }
            WarehousePositions warehousePositions = warehousePositionService.selectWarehousePositionsbyWpNumber(transshipmentGoodsDto.getIntoWpNumber());
            //找不到仓位编号
            if (warehousePositions == null) {
                response.setCode(HtCode.FAIL_POSITIONS.getCode());
                response.setMsg(HtCode.FAIL_POSITIONS.getInfo());
                return response;
            }
            //找不到用户编号
            AppUser appUser = appUserService.selectAppUserByNumber(transshipmentGoodsDto.getNumber());
            if (appUser == null) {
                response.setCode(HtCode.FAIL_NUMBER.getCode());
                response.setMsg(HtCode.FAIL_NUMBER.getInfo());
                return response;
            }
            String str = goodsService.insertTransshipmentGoodsInfo(transshipmentGoodsDto);
            if (str.equals("长宽高重量不能小于等于零或为空")) {
                response.setCode(HtCode.FAIL_NULL.getCode());
                response.setMsg(HtCode.FAIL_NULL.getInfo());
                return response;
            }
            if (Integer.parseInt(str) > 0) {
                int result = messageService.insertMessage(1, appUser.getAppUserId(), 0, Integer.parseInt(str), 0, 0, 0);
            }
            response.setCode(HtCode.SUCCESS_PUT.getCode());
            response.setMsg(HtCode.SUCCESS_PUT.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_PUT.getCode());
            response.setMsg(HtCode.FAIL_PUT.getInfo());
            return response;
        }
        return response;
    }

    //后台增加编辑功能（编辑前查看）

    @ApiOperation(value = "查看货物需要编辑的信息", notes = "查看货物需要编辑的信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getGoodsAndGoodsDetailsById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateGoodsAndGoodsDetailsInfo"})
    public LayuiCommonResponse getGoodsAndGoodsDetailsById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsAndGoodsDetails goodsAndGoodsDetails = goodsService.getGoodsAndGoodsDetailsById(goodsId);
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setData(goodsAndGoodsDetails);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }


    //正常货物增加编辑功能，打包后编辑变为异常件
    @ApiOperation(value = "更新货物信息", notes = "更新货物信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "GoodsAndGoodsDetails", value = "货物及详情", dataType = "goodsAndGoodsDetails")
    @RequestMapping(value = "/updateGoodsAndGoodsDetailsInfo", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateGoodsAndGoodsDetailsInfo"})
    public LayuiCommonResponse updateGoodsAndGoodsDetailsInfo(@RequestBody GoodsAndGoodsDetails goodsAndGoodsDetails) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsAndGoodsDetails.getGoodsId());
            if (goodsDetails.getIsReceiveGoods() == 1) {
                response.setCode(HtCode.FAIL_UPDATE_RECEIVE_GOODS.getCode());
                response.setMsg(HtCode.FAIL_UPDATE_RECEIVE_GOODS.getInfo());
                return response;
            }
            //找不到仓位编号
            if (goodsAndGoodsDetails.getIntoWpNumber() != null && !(goodsAndGoodsDetails.getIntoWpNumber().equals(""))) {
                WarehousePositions warehousePositions = warehousePositionService.selectWarehousePositionsbyWpNumber(goodsAndGoodsDetails.getIntoWpNumber());
                if (warehousePositions == null) {
                    response.setCode(HtCode.FAIL_POSITIONS.getCode());
                    response.setMsg(HtCode.FAIL_POSITIONS.getInfo());
                    return response;
                }
            }
            if (goodsAndGoodsDetails.getOutWpNumber() != null && !(goodsAndGoodsDetails.getOutWpNumber().equals(""))) {
                WarehousePositions warehousePositions1 = warehousePositionService.selectWarehousePositionsbyWpNumber(goodsAndGoodsDetails.getOutWpNumber());
                if (warehousePositions1 == null) {
                    response.setCode(HtCode.FAIL_POSITIONS.getCode());
                    response.setMsg(HtCode.FAIL_POSITIONS.getInfo());
                    return response;
                }
            }
            if (goodsAndGoodsDetails.getNumber() != null && !(goodsAndGoodsDetails.getNumber().equals(""))) {
                //找不到用户编号
                AppUser appUser = appUserService.selectAppUserByNumber(goodsAndGoodsDetails.getNumber());
                if (appUser == null) {
                    response.setCode(HtCode.FAIL_NUMBER.getCode());
                    response.setMsg(HtCode.FAIL_NUMBER.getInfo());
                    return response;
                }
            }
            GoodsAndGoodsDetails goodsAndGoodsDetails1 = goodsService.getGoodsAndGoodsDetailsById(goodsAndGoodsDetails.getGoodsId());
            //默认为false，无变更
            boolean flag = false;
            if (!(goodsAndGoodsDetails.getTripartiteNumber().equals(goodsAndGoodsDetails1.getTripartiteNumber()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getGoodType() == null) {
                goodsAndGoodsDetails1.setGoodType("");
            }
            if (!(goodsAndGoodsDetails.getGoodType().equals(goodsAndGoodsDetails1.getGoodType()))) {
                flag = true;
            }
            if (abs(goodsAndGoodsDetails.getActualLong() - goodsAndGoodsDetails1.getActualLong()) > 1e-6) {
                flag = true;
            }
            if (abs(goodsAndGoodsDetails.getActualWidth() - goodsAndGoodsDetails1.getActualWidth()) > 1e-6) {
                flag = true;
            }
            if (abs(goodsAndGoodsDetails.getActualHeight() - goodsAndGoodsDetails1.getActualHeight()) > 1e-6) {
                flag = true;
            }
            if (abs(goodsAndGoodsDetails.getActualWeight() - goodsAndGoodsDetails1.getActualWeight()) > 1e-6) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getCategory() == null) {
                goodsAndGoodsDetails1.setCategory("");
            }
            if (!(goodsAndGoodsDetails.getCategory().equals(goodsAndGoodsDetails1.getCategory()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getIsUrgent() == null) {
                goodsAndGoodsDetails1.setIsUrgent("");
            }
            if (!(goodsAndGoodsDetails.getIsUrgent().equals(goodsAndGoodsDetails1.getIsUrgent()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getZipCode() == null) {
                goodsAndGoodsDetails1.setZipCode("");
            }
            if (!(goodsAndGoodsDetails.getZipCode().equals(goodsAndGoodsDetails1.getZipCode()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getExitWay() == null) {
                goodsAndGoodsDetails1.setExitWay("");
            }
            if (!(goodsAndGoodsDetails.getExitWay().equals(goodsAndGoodsDetails1.getExitWay()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getIntoWpNumber() == null) {
                goodsAndGoodsDetails1.setIntoWpNumber("");
            }
            if (!(goodsAndGoodsDetails.getIntoWpNumber().equals(goodsAndGoodsDetails1.getIntoWpNumber()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getOutWpNumber() == null) {
                goodsAndGoodsDetails1.setOutWpNumber("");
            }
            if (!(goodsAndGoodsDetails.getOutWpNumber().equals(goodsAndGoodsDetails1.getOutWpNumber()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails.getIsSpecialGoods() != goodsAndGoodsDetails1.getIsSpecialGoods()) {
                flag = true;
            }
            if (goodsAndGoodsDetails.getItemValue() == null) {
                goodsAndGoodsDetails.setItemValue(new BigDecimal(0));
            }
            if (goodsAndGoodsDetails1.getItemValue() == null) {
                goodsAndGoodsDetails1.setItemValue(new BigDecimal(0));
            }
            if (!(goodsAndGoodsDetails.getItemValue().compareTo(goodsAndGoodsDetails1.getItemValue()) == 0)) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getShipContact() == null) {
                goodsAndGoodsDetails1.setShipContact("");
            }
            if (!(goodsAndGoodsDetails.getShipContact().equals(goodsAndGoodsDetails1.getShipContact()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getShipAddress() == null) {
                goodsAndGoodsDetails1.setShipAddress("");
            }
            if (!(goodsAndGoodsDetails.getShipAddress().equals(goodsAndGoodsDetails1.getShipAddress()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getShipContactMobile() == null) {
                goodsAndGoodsDetails1.setShipContactMobile("");
            }
            if (!(goodsAndGoodsDetails.getShipContactMobile().equals(goodsAndGoodsDetails1.getShipContactMobile()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getReceiptContact() == null) {
                goodsAndGoodsDetails1.setReceiptContact("");
            }
            if (!(goodsAndGoodsDetails.getReceiptContact().equals(goodsAndGoodsDetails1.getReceiptContact()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getReceiptAddress() == null) {
                goodsAndGoodsDetails1.setReceiptAddress("");
            }
            if (!(goodsAndGoodsDetails.getReceiptAddress().equals(goodsAndGoodsDetails1.getReceiptAddress()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getReceiptContactMobile() == null) {
                goodsAndGoodsDetails1.setReceiptContactMobile("");
            }
            if (!(goodsAndGoodsDetails.getReceiptContactMobile().equals(goodsAndGoodsDetails1.getReceiptContactMobile()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getNumber() == null) {
                goodsAndGoodsDetails1.setNumber("");
            }
            if (!(goodsAndGoodsDetails.getNumber().equals(goodsAndGoodsDetails1.getNumber()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getRemark() == null) {
                goodsAndGoodsDetails1.setRemark("");
            }
            if (!(goodsAndGoodsDetails.getRemark().equals(goodsAndGoodsDetails1.getRemark()))) {
                flag = true;
            }
            if (goodsAndGoodsDetails1.getBagNumber() == null) {
                goodsAndGoodsDetails1.setBagNumber("");
            }
            if (!(goodsAndGoodsDetails.getBagNumber().equals(goodsAndGoodsDetails1.getBagNumber()))) {
                flag = true;
                if(goodsAndGoodsDetails.getBagNumber()!= null && !(goodsAndGoodsDetails.getBagNumber().equals(""))){
                    Bag bag = bagSerivce.selectBagByNumber(goodsAndGoodsDetails.getBagNumber());
                    if( bag ==null){
                        response.setCode(HtCode.FAIL_BAG_UPDATE.getCode());
                        response.setMsg(HtCode.FAIL_BAG_UPDATE.getInfo());
                        return response;
                    }
                }

            }
            //如果信息有变更就执行
            if (flag) {
                String str = goodsService.updateGoodsAndGoodsDetailsInfo(goodsAndGoodsDetails);
                if (str.equals("长宽高重量不能小于等于零或为空")) {
                    response.setCode(HtCode.FAIL_NULL.getCode());
                    response.setMsg(HtCode.FAIL_NULL.getInfo());
                    return response;
                } else if (str.equals("1")) {
                    response.setCode(HtCode.FAIL_UPDATE_PACK_AFTER.getCode());
                    response.setMsg(HtCode.FAIL_UPDATE_PACK_AFTER.getInfo());
                    return response;
                }
            }
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMsg(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMsg(HtCode.FAIL_UPDATE.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "导入三方物流公司货物派送结果的信息", notes = "导入Excel表格", tags = {"@郝腾"})
    @RequestMapping(value = "/importGoodsSendConditionExcel", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"importGoodsSendConditionExce"})
    public LayuiCommonResponse insertGoodsSendCondition(@ApiParam(value = "MultipartFile", required = true) MultipartFile file) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            String fileName = file.getOriginalFilename();
            String str = goodsService.insertGoodsSendCondition(fileName, file);
            if (!(str.equals("1"))) {
                response.setCode(HtCode.FAIL_IMPORT.getCode());
                response.setMsg(str);
                return response;
            }
            response.setCode(HtCode.SUCCESS_IMPORT.getCode());
            response.setMsg(HtCode.SUCCESS_IMPORT.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_IMPORT.getCode());
            response.setMsg(HtCode.FAIL_IMPORT.getInfo());
        }
        return response;
    }


    @ApiOperation(value = "导出广州送出的货物与货袋", notes = "导出广州送出的货物与货袋",tags = {"@郝腾"})
    @RequestMapping(value = "/getGoodsAndBags", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始时间",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束时间",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "staffId", value = "员工id",required = true,dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "adminUid", value = "后台用户id",required = true,dataType = "int", paramType = "query")
    })
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsAndBags"})
    public void getGoodsAndBags(HttpServletResponse response,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,
                                @RequestParam("staffId") Integer staffId,@RequestParam("adminUid") Integer adminUid)
            throws IOException {
        /**1、用HSSFWorkbook打开或者创建“Excel文件对象”

         2、用HSSFWorkbook对象返回或者创建Sheet对象

         3、用Sheet对象返回行对象，用行对象得到Cell对象

         4、对Cell对象读写。

         5、将生成的HSSFWorkbook放入HttpServletResponse中响应到前端页面
         * */
        response.setContentType("application/application/vnd.ms-excel");
        response.setHeader("Content-disposition",
                "attachment;filename=" + URLEncoder.encode("PackingList" + ".xls", "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("转单号");
        row.createCell(1).setCellValue("运单号");
        row.createCell(2).setCellValue("货袋编号");
        ByteArrayOutputStream arrayInputStream = new ByteArrayOutputStream();
        int index = 1;
        try {
            Set<Integer> goodsIds = new HashSet<>();
          if(staffId>0){
             List<LogisticInfo> logisticInfoList = logisticInfoService.selectLogisticInfoBystaffIdList(staffId,startDate,endDate);
            if(!(logisticInfoList.isEmpty())){
                for (LogisticInfo logisticInfo:logisticInfoList
                ) {
                    goodsIds.add(logisticInfo.getGoodsId());
                }
            }
          }
          if(adminUid>0){
              List<LogisticInfo> logisticInfoList = logisticInfoService.selectLogisticInfoByadminUidList(adminUid,startDate,endDate);
              if(!(logisticInfoList.isEmpty())){
                  for (LogisticInfo logisticInfo:logisticInfoList
                  ) {
                      goodsIds.add(logisticInfo.getGoodsId());
                  }
              }
          }
          if(goodsIds.size()>0){
            List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectGoodsDetailListByGoodsIds(goodsIds);
            if(!(goodsDetailsList.isEmpty())){
                for (GoodsDetails goodsDetails : goodsDetailsList
                ) {
                    row = sheet.createRow(index);
                    row.createCell(0).setCellValue(goodsDetails.getDeliveryNumber());
                    row.createCell(1).setCellValue(goodsDetails.getTripartiteNumber());
                    row.createCell(2).setCellValue(goodsDetails.getBagNumber());
                    index++;
                }
            }
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        workbook.write(arrayInputStream);
        byte[] bytes = arrayInputStream.toByteArray();
        out.write(bytes);
        out.close();
    }

    @ApiOperation(value = "获取PDA员工姓名列表", notes = "获取PDA员工姓名列表", tags = {"@郝腾"})
    @RequestMapping(value = "/getStaffList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsAndBags"})
    public LayuiCommonResponse getStaffList() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<Staff> staffList = staffService.selectStaffList();
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setData(staffList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取后台管理员姓名列表", notes = "获取后台管理员姓名列表", tags = {"@郝腾"})
    @RequestMapping(value = "/getAdminList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsAndBags"})
    public LayuiCommonResponse getAdminList() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<AdminUser> adminList = adminUserService.getAdminList();
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setData(adminList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

}


