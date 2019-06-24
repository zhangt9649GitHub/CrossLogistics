package com.siruiman.crosslogistics.controller;


import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AddAppUserDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.ExchangeRateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.PrivateKey;
import java.util.*;

import static com.siruiman.crosslogistics.util.ImputedPriceUtil.*;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;

@Api(value = "AppTransshipment", description = "APP转运管理API", tags = {"APP转运管理"})
@RestController
@RequestMapping("/appTransshipment")
public class AppTransshipmentController {

    @Autowired
    private BizdictionaryService bizdictionaryService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private AppUserAddressService appUserAddressService;
    @Autowired
    private CopyWriterService copyWriterService;

    @ApiOperation(value = "转运须知", notes = "APP用户三方快递物流信息模板", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appUserId", value = "app用户id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "language", value = "语言", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/getLogisticsInformationTemplate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getLogisticsInformationTemplate(@Validated @RequestParam int appUserId,
                                                          @Validated @RequestParam String language) {
        CommonResponse response = new CommonResponse();
        try {
            AddAppUserDto appUser = appUserService.selectEditAppUserDetail(appUserId);
            String number = appUser.getNumber();
            Bizdictionary bizdictionary = bizdictionaryService.getBizdictionaryById(174);
            bizdictionary.setValue(bizdictionary.getValue() + number);
            Bizdictionary bizdictionary1 = bizdictionaryService.getBizdictionaryById(175);
            Bizdictionary bizdictionary2 = bizdictionaryService.getBizdictionaryById(177);
            String type = "转运须知";
            CopyWriter copyWriter = copyWriterService.selectContentByType(type, language);
            TransferInstructions transferInstructions = new TransferInstructions();
            transferInstructions.setRecipient(bizdictionary.getValue());
            transferInstructions.setAddress(bizdictionary1.getValue());
            transferInstructions.setMobile(bizdictionary2.getValue());
            transferInstructions.setInstructions(copyWriter.getContent());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(transferInstructions);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取转运公司介绍", notes = "获取转运公司介绍", tags = {"@郝腾"})
    @ApiImplicitParam(name = "language", value = "语言", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/getTransshipmentCompanyIntroduce", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getTransshipmentCompanyIntroduce(@Validated @RequestParam String language) {
        CommonResponse response = new CommonResponse();
        try {
            String type = "转运公司介绍";
            CopyWriter copyWriter = copyWriterService.selectCopyWriter(type, language);
            copyWriter.setPath("/admin/copybookSettings/open?copyWriterId=" + copyWriter.getCwId());
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(copyWriter);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取转运列表", notes = "获取转运列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "stateNumber", value = "货物状态的数值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "transportTypeNumber", value = "转运类型", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "deliveryNumber", value = "快递单号", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getGoodsTransshipmentList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsTransshipmentList(@Validated @RequestParam(defaultValue = "1") int page,
                                                    @Validated @RequestParam(defaultValue = "15") int limit,
                                                    @Validated Integer appUserId, @Validated Integer stateNumber,
                                                    @Validated Integer transportTypeNumber, @Validated String deliveryNumber) {
        CommonResponse response = new CommonResponse();
        try {
            PageHelper.startPage(page, limit);
            String goodState = null;
            if (stateNumber != null) {
                switch (stateNumber) {
                    case 1:
                        goodState = null;
                        break;
                    case 2:
                        goodState = "已到仓";
                        break;
                    case 3:
                        goodState = "待集运";
                        break;
                    case 4:
                        goodState = "待支付";
                        break;
                    case 5:
                        goodState = "已支付";
                        break;
                    case 6:
                        goodState = "转运中";
                        break;
                    case 7:
                        goodState = "已完成";
                        break;
                }
            }
            String transportType = null;
            if (transportTypeNumber != null) {
                switch (transportTypeNumber) {
                    case 10:
                        transportType = null;
                        break;
                    case 11:
                        transportType = "普通转运";
                        break;
                    case 12:
                        transportType = "组合转运";
                        break;
                }
            }
            List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectTransshipmentGoodsByUserId(appUserId, goodState, transportType, deliveryNumber);
            for (GoodsDetails goodsDetails1 : goodsDetailsList
            ) {
                if (!(goodsDetails1.getAddTime() == null) && !(goodsDetails1.getAddTime().equals(""))) {
                    goodsDetails1.setAddTime(goodsDetails1.getAddTime().substring(0, goodsDetails1.getAddTime().indexOf(".")));
                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(goodsDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "提交合并转运", notes = "提交合并转运", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "goodsIds", value = "货物id", paramType = "query", required = true, dataType = "Integer[]"),
    })
    @RequestMapping(value = "/insertCombinedTransport", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse insertCombinedTransport(@Validated @RequestParam int appUserId,
                                                  @Validated @RequestParam Integer[] goodsIds) {
        CommonResponse response = new CommonResponse();
        try {
            int WarehouseId = 0;
            for (int i = 0; i < goodsIds.length; i++) {
               GoodsDetails goodsDetails =goodsDetailsService.selectTransshipmentGoodsDetails(goodsIds[i]);
               if (goodsDetails!=null&&goodsDetails.getWarehouseId()>0){
                   if(WarehouseId ==0){
                       WarehouseId=goodsDetails.getWarehouseId();
                   }else if(WarehouseId!=goodsDetails.getWarehouseId()){
                       response.setCode(HtCode.FAIL_GOODS_DIFFERENT_COMBINED.getCode());
                       response.setMessage(HtCode.FAIL_GOODS_DIFFERENT_COMBINED.getInfo());
                       return response;
                   }
               }
               if(goodsDetails!=null&&goodsDetails.getGoodState()!=null&&!(goodsDetails.getGoodState().equals("已到仓"))){
                   response.setCode(HtCode.FAIL_GOODS_COMBINED.getCode());
                   response.setMessage(HtCode.FAIL_GOODS_COMBINED.getInfo());
                   return response;
               }
               if(goodsDetails!=null&&goodsDetails.getTransportType()!=null&&goodsDetails.getTransportType().equals("组合转运")){
                   response.setCode(HtCode.FAIL_GOODS_SECOND_COMBINED.getCode());
                   response.setMessage(HtCode.FAIL_GOODS_SECOND_COMBINED.getInfo());
                   return response;
               }
            }
            goodsDetailsService.insertCombinedTransport(appUserId, goodsIds,WarehouseId);
            response.setCode(HtCode.SUCCESS_ADD.getCode());
            response.setMessage(HtCode.SUCCESS_ADD.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_ADD.getCode());
            response.setMessage(HtCode.FAIL_ADD.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取转运详情", notes = "获取转运详情", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getTransportGoodsDetail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getTransportGoodsDetail(@Validated @RequestParam Integer goodsId) {
        CommonResponse response = new CommonResponse();
        try {
            Goods goods = goodsService.selectGoodsById(goodsId);
            if (goods.getTransportType().equals("普通转运") || goods.getTransportType().equals("组合子转运")) {
                GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsId);
                if(goodsDetails!=null&& goodsDetails.getAppUserId() > 0){
                    List<AppUserAddress> appUserAddressList = appUserAddressService.selectAppUserAddressByAppUserId(goodsDetails.getAppUserId());
                    if(appUserAddressList.size()>0){
                        for (AppUserAddress appUserAddress1:appUserAddressList
                        ) {
                            //处理低屋无门牌的情况
                            if (appUserAddress1.getAddressType() == 1) {
                                appUserAddress1.setDetailedAddress(appUserAddress1.getSaBuildingAddress() + " " + appUserAddress1.getHouseNumber());
                            } else if (appUserAddress1.getAddressType() == 2) {
                                appUserAddress1.setDetailedAddress(appUserAddress1.getSaBuildingAddress());
                            }
                         if(appUserAddress1.getAddressDefault()==2){
                             goodsDetails.setAppUserAddress(appUserAddress1);
                             if(goodsDetails.getGoodState().equals("已到仓")&&goods.getTransportType().equals("普通转运")){
                                 goodsDetailsService.updateAppUserAddress(appUserAddress1, goodsId);
                             }

                         }
                        }
                    }

                }
                if(goods.getTransportType().equals("组合子转运")){
                    if(goods.getMergeNumber()!=null){
                       GoodsDetails goodsDetails1 = goodsDetailsService.selectGoodsInfoByDeliveryNumber(goods.getMergeNumber());
                        goodsDetails.setGoodState(goodsDetails1.getGoodState());
                    }

                }
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(goodsDetails);
                return response;
            } else if (goods.getTransportType().equals("组合转运")) {
                List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectTransshipmentGoodsListByParentId(goodsId);
                GoodsDetails goodsDetails1 = goodsDetailsService.selectGoodsDetailById(goodsId);
                int appUserId = 0;
                for (GoodsDetails goodsDetails:goodsDetailsList
                     ) {
                    appUserId = goodsDetails.getAppUserId();
                    goodsDetails.setGoodState(goodsDetails1.getGoodState());
                }
                List<AppUserAddress> appUserAddressList = appUserAddressService.selectAppUserAddressByAppUserId(appUserId);
                if(appUserAddressList.size()>0){
                    for (AppUserAddress appUserAddress1:appUserAddressList
                    ) {
                        //处理低屋无门牌的情况
                        if (appUserAddress1.getAddressType() == 1) {
                            appUserAddress1.setDetailedAddress(appUserAddress1.getSaBuildingAddress() + " " + appUserAddress1.getHouseNumber());
                        } else if (appUserAddress1.getAddressType() == 2) {
                            appUserAddress1.setDetailedAddress(appUserAddress1.getSaBuildingAddress());
                        }
                        if(appUserAddress1.getAddressDefault()==2){
                            for (GoodsDetails goodsDetails:goodsDetailsList
                            ) {
                                goodsDetails.setAppUserAddress(appUserAddress1);
                        }
                        GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsId);
                            if(goodsDetails.getGoodState().equals("已到仓")) {
                                goodsDetailsService.updateAppUserAddress(appUserAddress1, goodsId);
                            }
                    }
                }

            }
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(goodsDetailsList);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取转运货物物流进程", notes = "获取转运货物物流进程信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/getGoodsLogisticById", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsLogisticById(Integer goodsId) {
        CommonResponse response = new CommonResponse();
        try {
            Goods goods1 = goodsService.selectGoodsById(goodsId);
            if (goods1.getParentId() != null) {
                List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods1.getParentId());
                List<LogisticInfo> logisticInfoList1 = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
                for (LogisticInfo logisticInfo : logisticInfoList
                ) {
                    logisticInfoList1.add(logisticInfo);
                }
                Collections.sort(logisticInfoList1);
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(logisticInfoList1);
                return response;
            }
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(logisticInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取转运货物收货人信息列表", notes = "获取转运货物收货人信息列表", tags = {"@郝腾"})
    @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getConsigneeInformation", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getConsigneeInformation(@Validated @RequestParam Integer appUserId) {
        CommonResponse response = new CommonResponse();
        try {
            List<AppUserAddress> appUserAddressList = appUserAddressService.selectAppUserAddressByAppUserId(appUserId);
            if (appUserAddressList != null) {
                for (AppUserAddress appUserAddress : appUserAddressList
                ) {
                    //处理低屋无门牌的情况
                    if (appUserAddress.getAddressType() == 1) {
                        appUserAddress.setDetailedAddress(appUserAddress.getSaBuildingAddress() + " " + appUserAddress.getHouseNumber());
                    } else if (appUserAddress.getAddressType() == 2) {
                        appUserAddress.setDetailedAddress(appUserAddress.getSaBuildingAddress());
                    }

                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appUserAddressList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "提交转运货物收货人信息", notes = "提交转运货物收货人信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appUserAddress", value = "app用户地址实体类", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsId", value = "货物id", paramType = "query", required = true, dataType = "Integer"),
    })
    @RequestMapping(value = "/getAppUserAddress", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse getAppUserAddress(@Validated String appUserAddress, @Validated Integer goodsId) {
        CommonResponse response = new CommonResponse();
        try {
            JSONObject json = JSONObject.fromObject(appUserAddress);
            AppUserAddress appUserAddress1 = (AppUserAddress) JSONObject.toBean(json, AppUserAddress.class);
            goodsDetailsService.updateAppUserAddress(appUserAddress1, goodsId);
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMessage(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMessage(HtCode.FAIL_UPDATE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "计算运费", notes = "计算运费", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsDetailsList", value = "货物详情集合", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsId", value = "货物id", paramType = "query", required = true, dataType = "Integer"),
    })
    @RequestMapping(value = "/getgoodsDetailsList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse getgoodsDetailsList(@Validated String goodsDetailsList, @Validated Integer goodsId) {
        CommonResponse response = new CommonResponse();
        try {
            JSONArray jsonArray = JSONArray.fromObject(goodsDetailsList);
            //Java集合
            List<GoodsDetails> goodsDetailsList1 = (List<GoodsDetails>) jsonArray.toCollection(jsonArray, GoodsDetails.class);
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsId);
            if (goodsDetails.getPaymentStatus() != null && goodsDetails.getPaymentStatus().equals("已支付")) {
                response.setCode(HtCode.FAIL_PAY_REPEAT.getCode());
                response.setMessage(HtCode.FAIL_PAY_REPEAT.getInfo());
                return response;
            }

            String regEx = "^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)";
            for (GoodsDetails goodsDetails1 : goodsDetailsList1
            ) {
                String itemValue = (goodsDetails1.getItemValue().setScale(2, RoundingMode.HALF_UP)).toString();
                if (!(itemValue.matches(regEx))) {
                    response.setCode(HtCode.FAIL_ITEM_VALUE.getCode());
                    response.setMessage(HtCode.FAIL_ITEM_VALUE.getInfo());
                    return response;
                }
            }
            String str = goodsDetailsService.updategoodsDetailsList(goodsDetailsList1, goodsId);
            if (!(str.equals("1")) && str.equals("长宽高重量不能小于等于零或为空")) {
                response.setCode(HtCode.FAIL_NULL.getCode());
                response.setMessage(HtCode.FAIL_NULL.getInfo());
                return response;
            }
            GoodsDetails goodsDetails3 = goodsDetailsService.selectGoodsDetailById(goodsId);
            if (goodsDetails3.getGstPrice().compareTo(new BigDecimal(0)) > 0) {
                Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(254);
                String prompt = bizdictionary.getValue() + "" + goodsDetails3.getGstPrice()+"美元USD";
                goodsDetails3.setPrompt(prompt);
            }
            response.setCode(HtCode.SUCCESS_CALULATE.getCode());
            response.setMessage(HtCode.SUCCESS_CALULATE.getInfo());
            response.setData(goodsDetails3);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_CALULATE.getCode());
            response.setMessage(HtCode.FAIL_CALULATE.getInfo());
        }
        return response;

    }


    @ApiOperation(value = "获取单位列表", notes = "获取单位列表", tags = {"@郝腾"})
    @RequestMapping(value = "/getListUnit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getListUnit() {
        CommonResponse response = new CommonResponse();
        try {
            List<Bizdictionary> bizdictionaryList = new ArrayList<>();
            Bizdictionary bizdictionary= bizdictionaryService.selectBizdictionary(258);
            Bizdictionary bizdictionary1= bizdictionaryService.selectBizdictionary(259);
            Bizdictionary bizdictionary2= bizdictionaryService.selectBizdictionary(260);
            bizdictionaryList.add(bizdictionary);
            bizdictionaryList.add(bizdictionary1);
            bizdictionaryList.add(bizdictionary2);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(bizdictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

}
