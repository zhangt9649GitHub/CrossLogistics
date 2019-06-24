package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.CodGoodsDto;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import com.siruiman.crosslogistics.service.UploadFilesService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Api(value = "CODGoodsManagement", description = "COD货物管理API", tags = {"COD货物管理"})
@RestController
@RequestMapping("/codGoods")
public class CODGoodsManagementController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private UploadFilesService uploadFilesService;

    @ApiOperation(value = "三方获取COD(货到付款)货物列表", notes = "三方获取COD(货到付款)货物列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "codGoodsDto", value = "货物实体", paramType = "query", dataType = "CodGoodsDto"),
    })
    @RequestMapping(value = "/getCodGoodsList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCodGoodsList"})
    public LayuiCommonResponse getCodGoodsList(@Validated @RequestParam(defaultValue = "1") int page,
                                               @Validated @RequestParam(defaultValue = "10") int limit,
                                               @Validated CodGoodsDto codGoodsDto) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<Goods> goodsList = new ArrayList<>();
            int count = 0;
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            int adminUserTypeId = user.getAdminUserTypeId();
            if (adminUserTypeId != 1) {
                goodsList = goodsService.selectThreeGoodsList(adminUserTypeId, codGoodsDto);
                count = goodsService.selectCountThreeGoodsList(adminUserTypeId, codGoodsDto);
                for (Goods goods1 : goodsList
                ) {
                    LogisticInfo logisticInfo = logisticInfoService.selectLogisticInfoByGoodsId(goods1.getGoodsId());
                    if (logisticInfo != null && logisticInfo.getOperateType() != null) {
                        goods1.setOperateResult(logisticInfo.getOperateType());
                    }
                }
            }
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

    @ApiOperation(value = "三方导入COD货物信息", notes = "三方导入COD货物信息", tags = {"@郝腾"})
    @RequestMapping(value = "/importCODExcel", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"importCODExcel"})
    public LayuiCommonResponse insertCodGoodsList(@ApiParam(value = "MultipartFile", required = true) MultipartFile file) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            String fileName = file.getOriginalFilename();
            String str = goodsService.insertCodGoodsList(fileName, file);
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

    @ApiOperation(value = "添加货物信息", notes = "添加货物信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "codGoods", value = "数据字典实体类", required = true, dataType = "CodGoods")
    @RequestMapping(value = "/insertCodGoods", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"insertCodGoods"})
    public LayuiCommonResponse insertCodGoods(@RequestBody CodGoods codGoods) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            String regEx = "^[A-Za-z0-9]+$";
            if (codGoods.getTripartiteNumber().matches(regEx)) {
                String str = goodsService.insertCodGoods(codGoods);
                if (!(str.equals("1"))) {
                    response.setCode(HtCode.FAIL_ADD.getCode());
                    response.setMsg(str);
                    return response;
                }
            } else {
                response.setCode(HtCode.FAIL_TRIPARTITENUMBER.getCode());
                response.setMsg(HtCode.FAIL_TRIPARTITENUMBER.getInfo());
                return response;
            }
            response.setCode(HtCode.SUCCESS_ADD.getCode());
            response.setMsg(HtCode.SUCCESS_ADD.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_ADD.getCode());
            response.setMsg(HtCode.FAIL_ADD.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "删除货物信息", notes = "删除Goods对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/deleteCodGoodsById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"deleteCodGoodsById"})
    public LayuiCommonResponse deleteCodGoodsById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
            for (LogisticInfo logisticInfo : logisticInfoList
            ) {
                if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                    response.setCode(HtCode.FAIL_GOODS_DELETE.getCode());
                    response.setMsg(HtCode.FAIL_GOODS_DELETE.getInfo());
                    return response;
                }
            }
            Goods goods = goodsService.selectGoodsById(goodsId);
            List<Goods> goodsList = new ArrayList<>();
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
    @RequestMapping(value = "/getCodGoodsLogisticById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCodGoodsById"})
    public LayuiCommonResponse getCodGoodsLogisticById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setData(logisticInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "查看货物信息", notes = "查看货物信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getCodGoodsById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getCodGoodsById"})
    public LayuiCommonResponse getCodGoodsById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            List<CodGoods> codGoodsList = goodsService.selectCodGoodsById(goodsId);
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setData(codGoodsList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }
}
