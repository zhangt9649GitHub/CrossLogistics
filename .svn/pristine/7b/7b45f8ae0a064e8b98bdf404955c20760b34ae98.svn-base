package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.PDAGoodsDetailsDto;
import com.siruiman.crosslogistics.model.dto.PDAGoodsDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.CarJPushClientUtil.pushCarMsg;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getSixRandomCode;

@Api(value = "PdaGoods", description = "PDA货物管理API", tags = {"PDA货物管理"})
@RestController
@RequestMapping("/pdaGoods")
public class PDAGoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private WarehousePositionService warehousePositionService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private BizdictionaryService bizdictionaryService;
    @Autowired
    private SingaporeAreaService singaporeAreaService;
    @Autowired
    private RallyPointService rallyPointService;
    @Autowired
    private TruckProblemPieceService truckProblemPieceService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private GoodsFromService goodsFromService;


    @ApiOperation(value = "入库时扫码或输入三方物流单号查询", notes = "获取货物详情信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "tripartiteNumber", value = "三方物流单号", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/getGoodsDetailsByTripartiteNumber", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsDetailsByTripartiteNumber(String tripartiteNumber) {
        CommonResponse response = new CommonResponse();
        try {
            String regEx = "^[A-Za-z0-9]+$";
            if (tripartiteNumber.matches(regEx)) {
                // GoodsDetails goodsDetails = goodsDetailsService.selectGoodsByTripartiteNumber(tripartiteNumber);
                //根据三方物流单号查询货物详情信息，有可能分箱，故返回为list
                List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectGoodsDetailByTripartiteNumber(tripartiteNumber);
                //Goods goods =goodsService.selectGoodsByTripartiteNumber(tripartiteNumber);
                List<Goods> goodsList = goodsService.selectGoodsListByTripartiteNumber(tripartiteNumber);
                //如果数量为0，表示是转运订单入库
                if (goodsList.size() == 0) {
                  //  GoodsDetails goodsDetails2 = goodsService.insertGoods(tripartiteNumber);
                    GoodsDetails goodsDetails2 = new GoodsDetails();
                    goodsDetails2.setFrom("转运订单");
                    String deliveryNumber = "04" + getRandomCode();
                    goodsDetails2.setDeliveryNumber(deliveryNumber);
                    goodsDetails2.setTripartiteNumber(tripartiteNumber);
                    response.setCode(HtCode.SUCCESS_SELECT.getCode());
                    response.setMessage(HtCode.SUCCESS_SELECT.getInfo());
                    response.setData(goodsDetails2);
                    return response;
                }

                for (GoodsDetails goodsDetails : goodsDetailsList
                ) {
                    //如果size等于1，所以总箱数为1，有用户编号加入用户编号，没有则不加入
                    if (goodsDetailsList.size() == 1) {
                        if (goodsDetails != null && goodsDetails.getFrom().equals("转运订单") && goodsDetails.getAppUserId() != 0) {
                            AppUser appUser = appUserService.selectAppUserDetail(goodsDetails.getAppUserId());
                            goodsDetails.setNumber(appUser.getNumber());
                        }
                        response.setCode(HtCode.SUCCESS_SELECT.getCode());
                        response.setMessage(HtCode.SUCCESS_SELECT.getInfo());
                        response.setData(goodsDetails);
                    } else {
                        //如何size>1，显示总箱数大于1的那条数据记录
                        if (goodsDetails.getTotalGoods() > 1) {
                            response.setCode(HtCode.SUCCESS_SELECT.getCode());
                            response.setMessage(HtCode.SUCCESS_SELECT.getInfo());
                            response.setData(goodsDetails);
                        }
                    }

                }
            } else {
                response.setCode(HtCode.FAIL_SELECT.getCode());
                response.setMessage(HtCode.FAIL_SELECT.getInfo());
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_SELECT.getCode());
            response.setMessage(HtCode.FAIL_SELECT.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "货物入库", notes = "货物入库", tags = {"@郝腾"})
    @ApiImplicitParam(name = "pdaGoodsDto", value = "货物详情实体", dataType = "PDAGoodsDto")
    @RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse insertGoods(PDAGoodsDto pdaGoodsDto) {
        CommonResponse response = new CommonResponse();
        try {
            if (pdaGoodsDto.getTotalGoods() < pdaGoodsDto.getOrder()) {
                response.setCode(HtCode.FAIL_ERROR_ORDER.getCode());
                response.setMessage(HtCode.FAIL_ERROR_ORDER.getInfo());
                return response;
            }
            if(pdaGoodsDto.getFrom().equals("三方货单")){
                List<LogisticInfo> logisticInfoList = new ArrayList<>();
                //总箱数为1，查询物流记录
                if (pdaGoodsDto.getTotalGoods() == 1) {
                    Goods goods = goodsService.selectGoodsById(pdaGoodsDto.getGoodsId());
                    //输入的总箱数与导入的总箱数不一致的三方货单
                    if (!(goods.getTotalGoods().equals(pdaGoodsDto.getTotalGoods())) && goods.getFrom().equals("三方货单")) {
                        response.setCode(HtCode.FAIL_INSERT_TOTAL.getCode());
                        response.setMessage(HtCode.FAIL_INSERT_TOTAL.getInfo());
                        return response;
                    }
                    //异常货物无法入库
                    if (goods.getStatus() != null && goods.getStatus() == 2) {
                        response.setCode(HtCode.FAIL_INSERT_ABNORMAL.getCode());
                        response.setMessage(HtCode.FAIL_INSERT_ABNORMAL.getInfo());
                        return response;
                    }
                    logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(pdaGoodsDto.getGoodsId());
                } else {
                    Goods goods2 = goodsService.selectGoodsById(pdaGoodsDto.getGoodsId());
                    //输入的总箱数与导入的总箱数不一致的三方货单
                    if (!(goods2.getTotalGoods().equals(pdaGoodsDto.getTotalGoods())) && goods2.getFrom().equals("三方货单")) {
                        response.setCode(HtCode.FAIL_INSERT_TOTAL.getCode());
                        response.setMessage(HtCode.FAIL_INSERT_TOTAL.getInfo());
                        return response;
                    }
                    Goods goods4 = goodsService.selectGoodsByDeliveryNumber(goods2.getDeliveryNumber());
                    //三方货物货物总数的那条记录为异常，无法入库
                    if (goods4.getStatus() == 2) {
                        response.setCode(HtCode.FAIL_INSERT_ABNORMAL.getCode());
                        response.setMessage(HtCode.FAIL_INSERT_ABNORMAL.getInfo());
                        return response;
                    }
                    Goods goods3 = goodsService.selectGoodsByDeliveryNumber(goods2.getDeliveryNumber() + "-" + pdaGoodsDto.getOrder());
                    //异常货物无法入库
                    if (goods3 != null && goods3.getStatus() == 2) {
                        response.setCode(HtCode.FAIL_INSERT_ABNORMAL.getCode());
                        response.setMessage(HtCode.FAIL_INSERT_ABNORMAL.getInfo());
                        return response;
                    }
                    logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods3.getGoodsId());
                }
                //遍历物流记录，查询是否成功入过库
                for (LogisticInfo logisticInfo : logisticInfoList
                ) {
                    if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                        response.setCode(HtCode.FAIL_PUT_REPEAT.getCode());
                        response.setMessage(HtCode.FAIL_PUT_REPEAT.getInfo());
                        return response;
                    }
                }
                //如果总箱数为1
                if (pdaGoodsDto.getTotalGoods() == 1) {
                    String str = goodsDetailsService.updateGoodsDetailsAndGoods(pdaGoodsDto);
                    if (str.equals("长宽高重量不能小于等于零或为空")) {
                        response.setCode(HtCode.FAIL_NULL.getCode());
                        response.setMessage(HtCode.FAIL_NULL.getInfo());
                        return response;
                    }
                } else {
                    String str = goodsDetailsService.updateGoodsDetailsByOrder(pdaGoodsDto);
                    if (str.equals("长宽高重量不能小于等于零或为空")) {
                        response.setCode(HtCode.FAIL_NULL.getCode());
                        response.setMessage(HtCode.FAIL_NULL.getInfo());
                        return response;
                    }
                }
            }else if(pdaGoodsDto.getFrom().equals("转运订单")) {
                if(pdaGoodsDto.getTripartiteNumber()!=null){
                    List<Goods> goodsList = goodsService.selectGoodsListByTripartiteNumber(pdaGoodsDto.getTripartiteNumber());
                    if(goodsList.size()==1){
                        for (Goods goods:goodsList
                             ) {
                           List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods.getGoodsId());
                            for (LogisticInfo logisticInfo : logisticInfoList
                            ) {
                                if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                                    response.setCode(HtCode.FAIL_PUT_REPEAT.getCode());
                                    response.setMessage(HtCode.FAIL_PUT_REPEAT.getInfo());
                                    return response;
                                }
                            }
                        }
                    }
                }

                //转运订单输入的总箱数大于1箱
                if (pdaGoodsDto.getTotalGoods() > 1) {
                    response.setCode(HtCode.FAIL_INSERT_TRANSSHIPMENT.getCode());
                    response.setMessage(HtCode.FAIL_INSERT_TRANSSHIPMENT.getInfo());
                    return response;
                }
                if (pdaGoodsDto.getNumber() == null || pdaGoodsDto.getNumber().equals("")) {
                    response.setCode(HtCode.FAIL_JOIN.getCode());
                    response.setMessage(HtCode.FAIL_JOIN.getInfo());
                    return response;
                }
                //如果是转运订单
                if (pdaGoodsDto.getNumber() != null && !(pdaGoodsDto.getNumber().equals(""))) {
                    WarehousePositions warehousePositions = warehousePositionService.selectWarehousePositionsbyWpNumber(pdaGoodsDto.getIntoWpNumber());
                    //找不到仓位编号
                    if (warehousePositions == null) {
                        response.setCode(HtCode.FAIL_POSITIONS.getCode());
                        response.setMessage(HtCode.FAIL_POSITIONS.getInfo());
                        return response;
                    }
                    //找不到用户编号
                    AppUser appUser = appUserService.selectAppUserByNumber(pdaGoodsDto.getNumber());
                    if (appUser == null) {
                        response.setCode(HtCode.FAIL_NUMBER.getCode());
                        response.setMessage(HtCode.FAIL_NUMBER.getInfo());
                        return response;
                    }
                    //转运订单的物品分类必须不能为空
                    if (pdaGoodsDto.getCategory() != null && !(pdaGoodsDto.getCategory().equals(""))) {
                        String str = goodsDetailsService.insertGoodsAndGoodsDetails(pdaGoodsDto);
                        if (str.equals("长宽高重量不能小于等于零或为空")) {
                            response.setCode(HtCode.FAIL_NULL.getCode());
                            response.setMessage(HtCode.FAIL_NULL.getInfo());
                            return response;
                        }
                        int result = messageService.insertMessage(1, appUser.getAppUserId(), 0, Integer.parseInt(str), 0, 0, 0);
                        if (result == 1) {
                            logisticInfoService.insertNormalLogisticInfo(1, pdaGoodsDto.getStaffId(), 0, Integer.parseInt(str), 0);
                        }
                    } else {
                        response.setCode(HtCode.FAIL_CATEGORY.getCode());
                        response.setMessage(HtCode.FAIL_CATEGORY.getInfo());
                        return response;
                    }
                }

            }
            response.setCode(HtCode.SUCCESS_PUT.getCode());
            response.setMessage(HtCode.SUCCESS_PUT.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_PUT.getCode());
            response.setMessage(HtCode.FAIL_PUT.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "货物入库时问题件", notes = "货物入库时问题件提交", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffId", value = "员工id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "tripartiteNumber", value = "三方物流单号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "warningType", value = "错误类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "warningComment", value = "错误描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "number", value = "用户编号", dataType = "String"),
            @ApiImplicitParam(name = "totalGoods", value = "共计几箱", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "order", value = "第几箱", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/insertGoodsWarning", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse insertGoodsWarning(@Validated @RequestParam int staffId,
                                             @Validated @RequestParam String tripartiteNumber,
                                             @Validated @RequestParam String warningType,
                                             @Validated @RequestParam String warningComment,
                                             @Validated @RequestParam String number,
                                             @Validated @RequestParam int totalGoods,
                                             @Validated @RequestParam int order) {
        CommonResponse response = new CommonResponse();
        try {
            // Goods goods =goodsService.selectGoodsByTripartiteNumber(tripartiteNumber);
            //根据三方物流单号查询货物信息
            List<Goods> goodsList = goodsService.selectGoodsListByTripartiteNumber(tripartiteNumber);
            //size为0时
            if (goodsList.size() == 0) {

                if (number != null && !(number.equals(""))) {
                    AppUser appUser = appUserService.selectAppUserByNumber(number);
                    if (appUser != null && appUser.getAppUserId() != null) {
                        if (totalGoods > 1) {
                            response.setCode(HtCode.FAIL_INSERT_TRANSSHIPMENT.getCode());
                            response.setMessage(HtCode.FAIL_INSERT_TRANSSHIPMENT.getInfo());
                            return response;
                        }
                        Integer result = goodsService.insertGoodsWarning(tripartiteNumber, warningType, warningComment, number, staffId);
                        if (result > 0) {
                            messageService.insertAbnormalMessage(2, appUser.getAppUserId(), warningComment, result);
                        }
                    } else {
                        response.setCode(HtCode.FAIL_PUT_GOODS_WARNING.getCode());
                        response.setMessage(HtCode.FAIL_PUT_GOODS_WARNING.getInfo());
                        return response;
                    }
                } else {
                    //没有用户编号，库里还没有货物信息
                    response.setCode(HtCode.FAIL_INSERT_GOODS_WARNING.getCode());
                    response.setMessage(HtCode.FAIL_INSERT_GOODS_WARNING.getInfo());
                    return response;
                }

            } else {
                //查询物流信息
                List<LogisticInfo> logisticInfoList = new ArrayList<>();
                //一箱货物的情况
                if (totalGoods == 1 && order == 1) {
                    for (Goods goods : goodsList
                    ) {
                        logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods.getGoodsId());
                    }
                } else {

                    //多箱货物的情况
                    String str = "";
                    for (Goods goods : goodsList
                    ) {
                        if (goods.getTotalGoods() > 1) {
                            //总箱数不一致
                            if ((!(goods.getTotalGoods().equals(totalGoods)) && goods.getFrom().equals("三方货单") && warningType.equals("总箱数不正确")) ||
                                    (!(goods.getTotalGoods().equals(totalGoods)) && goods.getFrom().equals("三方货单")
                                            && warningType.equals("The total number of boxes is incorrect"))) {

                                for (Goods goods1 : goodsList
                                ) {
                                    String str1 = "";
                                    if (goods.getTotalGoods() > 1) {
                                        str1 = goods.getDeliveryNumber();
                                    }
                                    if (goods.getDeliveryNumber().equals(str1)) {
                                        Goods goods3 = goodsService.selectGoodsByDeliveryNumber(goods.getDeliveryNumber());
                                        goodsService.updateGoodsAndDetails(goods3, warningType, warningComment, staffId, number);
                                        response.setCode(HtCode.SUCCESS_INSERT.getCode());
                                        response.setMessage(HtCode.SUCCESS_INSERT.getInfo());
                                        return response;
                                    }
                                }
                            }

                            //数据库表中总箱数大于1的那条数据，快递单号拼接第几箱为子货物快递单号
                            str = goods.getDeliveryNumber() + "-" + order;
                        } else if (goods.getTotalGoods() == 1) {
                            //总箱数不一致
                            if ((!(goods.getTotalGoods().equals(totalGoods)) && goods.getFrom().equals("三方货单") && warningType.equals("总箱数不正确")) ||
                                    (!(goods.getTotalGoods().equals(totalGoods)) && goods.getFrom().equals("三方货单")
                                            && warningType.equals("The total number of boxes is incorrect"))) {
                                Goods goods3 = goodsService.selectGoodsByDeliveryNumber(goods.getDeliveryNumber());
                                goodsService.updateGoodsAndDetails(goods3, warningType, warningComment, staffId, number);
                                response.setCode(HtCode.SUCCESS_INSERT.getCode());
                                response.setMessage(HtCode.SUCCESS_INSERT.getInfo());
                                return response;
                            }
                        }
                        //查询子货物的物流信息
                        if (goods.getDeliveryNumber().equals(str)) {
                            Goods goods3 = goodsService.selectGoodsByDeliveryNumber(goods.getDeliveryNumber());
                            logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods3.getGoodsId());
                        }
                    }
                }
                //查询物流信息是否有入库
                for (LogisticInfo logisticInfo : logisticInfoList
                ) {
                    if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物入库成功")) {
                        response.setCode(HtCode.FAIL_GOODS_WARNING.getCode());
                        response.setMessage(HtCode.FAIL_GOODS_WARNING.getInfo());
                        return response;
                    }
                }
                //根据三方单号查询到货物信息，用户编号不为空的时候找不到用户信息
                if (number != null && !(number.equals(""))) {
                    AppUser appUser = appUserService.selectAppUserByNumber(number);
                    if (appUser == null) {
                        response.setCode(HtCode.FAIL_PUT_GOODS_WARNING.getCode());
                        response.setMessage(HtCode.FAIL_PUT_GOODS_WARNING.getInfo());
                        return response;
                    }
                }
                //箱数为1时
                if (totalGoods == 1 && order == 1) {
                    for (Goods goods : goodsList
                    ) {
                        goodsService.updateGoodsAndDetails(goods, warningType, warningComment, staffId, number);
                        if (goods.getFrom() != null && goods.getFrom().equals("转运订单")) {
                            if (number != null && !(number.equals(""))) {
                                AppUser appUser = appUserService.selectAppUserByNumber(number);
                                if (appUser != null) {
                                    messageService.insertAbnormalMessage(2, appUser.getAppUserId(), warningComment, goods.getGoodsId());
                                }
                            }

                        }
                    }
                } else {
                    String str = "";
                    for (Goods goods : goodsList
                    ) {
                        if (goods.getTotalGoods() > 1) {
                            str = goods.getDeliveryNumber() + "-" + order;
                        }
                        if (goods.getDeliveryNumber().equals(str)) {
                            Goods goods3 = goodsService.selectGoodsByDeliveryNumber(goods.getDeliveryNumber());
                            goodsService.updateGoodsAndDetails(goods3, warningType, warningComment, staffId, number);
                        }
                    }
                }

            }
            response.setCode(HtCode.SUCCESS_INSERT.getCode());
            response.setMessage(HtCode.SUCCESS_INSERT.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_INSERT.getCode());
            response.setMessage(HtCode.FAIL_INSERT.getInfo());
            return response;
        }
        return response;
    }

    @ApiOperation(value = "获取转运待打包列表", notes = "获取转运待打包列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "createTime", value = "订单创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "singaporeAreaId", value = "新加坡区域id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "rallyPointId", value = "集结地id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "exitWayNumber", value = "出入境方式 1.空运 2.海运", paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "/getGoodsTransshipmentDetailsList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsTransshipmentDetailsList(@Validated @RequestParam(defaultValue = "1") int page,
                                                           @Validated @RequestParam(defaultValue = "15") int limit,
                                                           @Validated String createTime,
                                                           @Validated Integer singaporeAreaId,
                                                           @Validated Integer rallyPointId,
                                                           @Validated Integer exitWayNumber) {
        CommonResponse response = new CommonResponse();
        try {
            PageHelper.startPage(page, limit);
            //支付状态为已支付,货物状态为待集运
            List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectTransshipmentGoodsStateList(createTime, singaporeAreaId, rallyPointId, exitWayNumber);
            for (GoodsDetails goodsDetails : goodsDetailsList
            ) {
                if (!(goodsDetails.getCreateTime() == null) && !(goodsDetails.getCreateTime().equals(""))) {
                    goodsDetails.setCreateTime(goodsDetails.getCreateTime().substring(0, goodsDetails.getCreateTime().indexOf(".")));
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

    @ApiOperation(value = "获取区域名称列表", notes = "获取区域名称列表", tags = {"@郝腾"})
    @RequestMapping(value = "/getSingaporeAreaNameList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getSingaporeAreaNameList() {
        CommonResponse response = new CommonResponse();
        try {
            List<SingaporeArea> singaporeAreaList = singaporeAreaService.selectSingaporeAreaNameList();
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(singaporeAreaList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取集结地名称列表", notes = "获取集结地名称列表", tags = {"@郝腾"})
    @ApiImplicitParam(name = "singaporeAreaId", value = "新加坡区域id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getRallyPointNumberList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse getRallyPointNumberList(int singaporeAreaId) {
        CommonResponse response = new CommonResponse();
        try {
            List<RallyPoint> rallyPointList = rallyPointService.selectRallyPointListById(singaporeAreaId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(rallyPointList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "货物查看时扫快递单号查询基本信息及状态信息", notes = "货物查看时扫快递单号查询基本信息及状态信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "deliveryNumber", value = "快递单号", required = true, dataType = "String")
    @RequestMapping(value = "/scanningGoods", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse scanningGoods(@Validated @RequestParam String deliveryNumber) {
        CommonResponse response = new CommonResponse();
        try {
            Goods goods = goodsService.selectGoodsByDeliveryNumber(deliveryNumber);
            if (goods != null && goods.getParentId() != null) {
                response.setCode(HtCode.FAIL_ERROR.getCode());
                response.setMessage(HtCode.FAIL_ERROR.getInfo() + goods.getMergeNumber());
                return response;
            }
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsInfoByDeliveryNumber(deliveryNumber);
            //获取不等于null
            if (goodsDetails != null) {
                if (goodsDetails.getTripartiteNumber() != null) {
                    List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectGoodsDetailListByTripartiteNumber(goodsDetails.getTripartiteNumber());
                    if (goodsDetailsList.size() == 1) {
                        if (goodsDetails.getIsArrivalPay() == 2) {
                            goodsDetails.setTotal(goodsDetails.getItemValue());
                        }
                        if (goodsDetails.getTotal() != null) {
                            goodsDetails.setTotal(goodsDetails.getItemValue().add(goodsDetails.getGstPrice()));
                        } else if (goodsDetails.getGstPrice().compareTo(new BigDecimal(0)) > 0) {
                            goodsDetails.setTotal(goodsDetails.getGstPrice());
                        } else {
                            goodsDetails.setTotal(new BigDecimal(0));
                        }
                    } else {
                        for (GoodsDetails goodsDetails1 : goodsDetailsList) {
                            if (goodsDetails1.getTotalGoods() > 1 && goodsDetails1.getIsReceiveGoods() == 2 && goodsDetails.getIsArrivalPay() == 2) {
                                if (goodsDetails1.getItemValue() != null) {
                                    goodsDetails.setTotal(goodsDetails1.getItemValue());
                                    goodsDetails.setItemValue(goodsDetails1.getItemValue());
                                }
                                break;
                            } else {
                                goodsDetails.setTotal(new BigDecimal(0));
                            }
                        }
                    }
                }
            }
            if (goodsDetails == null) {
                response.setCode(HtCode.FAIL_SELECT.getCode());
                response.setMessage(HtCode.FAIL_SELECT.getInfo());
                return response;
            }
            if (goods.getFrom().equals("转运订单") && goodsDetails.getAppUserId() != 0) {
                AppUser appUser = appUserService.selectAppUserDetail(goodsDetails.getAppUserId());
                goodsDetails.setNumber(appUser.getNumber());
            }
      /*      if (goods.getTransportType() != null && goods.getTransportType().equals("组合转运")) {
                List<Goods> goodsList = goodsService.selectGoodsByParentId(goods.getGoodsId());
                List<String> list = new ArrayList<>();
                String merge = "";
                for (Goods goods1 : goodsList
                ) {
                    list.add(goods1.getDeliveryNumber());
                    if (merge.equals("")) {
                        merge = merge + goods1.getDeliveryNumber();
                    } else {
                        merge = merge + "," + goods1.getDeliveryNumber();
                    }
                }
                goodsDetails.setChildren(list);
                response.setCode(HtCode.SUCCESS_MERGE.getCode());
                response.setMessage(HtCode.SUCCESS_MERGE.getInfo() + merge);
                response.setData(goodsDetails);
                return response;
            }*/
            if (goods.getTransportType() != null && goods.getFrom().equals("转运订单") && goods.getTransportType().equals("组合转运")) {
                List<Goods> goodsList = goodsService.selectGoodsByParentId(goods.getGoodsId());
                List<LogisticInfo> logisticInfoList1 = new ArrayList<>();
                List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goods.getGoodsId());
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
                goodsDetails.setLogisticInfoList(logisticInfoList1);
                response.setCode(HtCode.SUCCESS_SELECT.getCode());
                response.setMessage(HtCode.SUCCESS_SELECT.getInfo());
                response.setData(goodsDetails);
                return response;
            }
            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsDetails.getGoodsId());
            if (logisticInfoList.size() > 0) {
                goodsDetails.setLogisticInfoList(logisticInfoList);
            }
            response.setCode(HtCode.SUCCESS_SELECT.getCode());
            response.setMessage(HtCode.SUCCESS_SELECT.getInfo());
            response.setData(goodsDetails);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_SELECT.getCode());
            response.setMessage(HtCode.FAIL_SELECT.getInfo());
        }
        return response;
    }

    /*@ApiOperation(value = "货物更新",notes = "货物更新信息",tags={"@郝腾"})
    @ApiImplicitParam(name="pdaGoodsDetailsDto", value="货物详情实体",dataType="PDAGoodsDetailsDto")
    @RequestMapping(value = "/updateGoodsInfo",method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateGoodsInfo(PDAGoodsDetailsDto pdaGoodsDetailsDto){
        CommonResponse response = new CommonResponse();
        try {
             Goods goods = goodsService.selectGoodsById(pdaGoodsDetailsDto.getGoodsId());
            if (goods==null){
                response.setCode(HtCode.FAIL_UPDATE.getCode());
                response.setMessage(HtCode.FAIL_UPDATE.getInfo());
                return response;
            }
            if (pdaGoodsDetailsDto.getIntoWpNumber()!=null){
                WarehousePositions warehousePositions =warehousePositionService.selectWarehousePositionsbyWpNumber(pdaGoodsDetailsDto.getIntoWpNumber());
                if(warehousePositions==null){
                    response.setCode(HtCode.FAIL_POSITIONS.getCode());
                    response.setMessage(HtCode.FAIL_POSITIONS.getInfo());
                    return response;
                }
            }
          *//*  if(pdaGoodsDetailsDto.getWarningStateNumber()!=null){
                switch(pdaGoodsDetailsDto.getWarningStateNumber()){
                    case 1 : pdaGoodsDetailsDto.setWarningState("无异常"); break;
                    case 2 : pdaGoodsDetailsDto.setWarningState("异常"); break;
                    case 3 : pdaGoodsDetailsDto.setWarningState("正在处理中");break;
                }
            }*//*
          String str = goodsService.updateGoodsAndGoodsDetails(pdaGoodsDetailsDto);
            if(str!=null&&str.equals("长宽高重量不能小于等于零或为空")) {
                response.setCode(HtCode.FAIL_NULL.getCode());
                response.setMessage(HtCode.FAIL_NULL.getInfo());
                return response;
            }
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMessage(HtCode.SUCCESS_UPDATE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMessage(HtCode.FAIL_UPDATE.getInfo());
        }
        return response;
    }*/

    @ApiOperation(value = "货物修改打印状态", notes = "货物修改打印状态", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isPrint", value = "是否打印", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "order", value = "第几箱", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/updateGoodsPrint", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateGoodsPrint(@Validated int goodsId, @Validated int isPrint, @Validated int order) {
        CommonResponse response = new CommonResponse();
        try {
            if(order>0){
                Goods goods =goodsService.selectGoodsById(goodsId);
                Goods goods1 = goodsService.selectGoodsByDeliveryNumber(goods.getDeliveryNumber()+"-"+order);
                if(goods1!=null&&goods1.getGoodsId()!=null){
                    goodsService.updateGoodsPrintByGoodsId(goods1.getGoodsId(), isPrint);
                }
            }else if(order == 0){
                goodsService.updateGoodsPrintByGoodsId(goodsId, isPrint);
            }
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMessage(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMessage(HtCode.FAIL_UPDATE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取打印货物信息", notes = "获取打印货物信息", tags = {"@郝腾"})
    @RequestMapping(value = "/getGoodsPrint", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getGoodsPrint() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsPrint();
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

    @ApiOperation(value = "货物问题件接收", notes = "货物问题件接收", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staffId", value = "员工id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "deliveryNumber", value = "快递单号", paramType = "query", required = true, dataType = "String")
    })
    @RequestMapping(value = "/scanningWarningGoods", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse scanningWarningGoods(@Validated int staffId, @Validated String deliveryNumber) {
        CommonResponse response = new CommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsInfoByDeliveryNumber(deliveryNumber);
            GoodsDetails goodsDetails1 = new GoodsDetails();
            if (goodsDetails != null && goodsDetails.getGoodsId() != null && goodsDetails.getWarningState().equals("异常")) {
                TruckProblemPiece truckProblemPiece = truckProblemPieceService.selectByGoodsId(goodsDetails.getGoodsId());
                if (truckProblemPiece != null) {
                    truckProblemPieceService.updateStatusByGoodsId(goodsDetails.getGoodsId());
                    int steps = 3;
                    logisticInfoService.insertAbnormalLogisticInfo(3, goodsDetails.getAbnormalText(), goodsDetails.getGoodsId(), staffId, 0);
                } else {
                    response.setCode(HtCode.FAIL_DISTRIBUTION_WARNINGGOODS.getCode());
                    response.setMessage(HtCode.FAIL_DISTRIBUTION_WARNINGGOODS.getInfo());
                    return response;
                }
                goodsDetails1 = goodsDetailsService.selectGoodsDetailById(goodsDetails.getGoodsId());
            } else {
                response.setCode(HtCode.FAIL_NOTFOUND.getCode());
                response.setMessage(HtCode.FAIL_NOTFOUND.getInfo());
                return response;
            }

            response.setCode(HtCode.SUCCESS_RECEIVE.getCode());
            response.setMessage(HtCode.SUCCESS_RECEIVE.getInfo());
            response.setData(goodsDetails1);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_RECEIVE.getCode());
            response.setMessage(HtCode.FAIL_RECEIVE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取转运货物物品分类", notes = "获取转运货物物品分类", tags = {"@郝腾"})
    @ApiImplicitParam(name = "language", value = "语言(zh 中文，en 英文)", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/getGoodsCategory", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsCategory(String language) {
        CommonResponse response = new CommonResponse();
        try {
            List<Bizdictionary> bizdictionaryList = new ArrayList<>();
            if (language.equals("zh")) {
                int id1 = 171;
                Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(id1);
                bizdictionaryList.add(bizdictionary);
                int id2 = 172;
                Bizdictionary bizdictionary2 = bizdictionaryService.selectBizdictionary(id2);
                bizdictionaryList.add(bizdictionary2);
            } else if (language.equals("en")) {
                int id1 = 241;
                Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(id1);
                bizdictionaryList.add(bizdictionary);
                int id2 = 242;
                Bizdictionary bizdictionary2 = bizdictionaryService.selectBizdictionary(id2);
                bizdictionaryList.add(bizdictionary2);
            }
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

    @ApiOperation(value = "获取问题件货物异常类型列表", notes = "获取问题件货物异常类型列表", tags = {"@郝腾"})
    @ApiImplicitParam(name = "language", value = "语言(zh 中文，en 英文)", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/getGoodsWaringType", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getGoodsWaringType(String language) {
        CommonResponse response = new CommonResponse();
        try {
            List<Bizdictionary> bizdictionaryList = new ArrayList<>();
            if (language.equals("zh")) {
                bizdictionaryList = bizdictionaryService.selectBizdictionaryByParentId(244);
            } else if (language.equals("en")) {
                bizdictionaryList = bizdictionaryService.selectBizdictionaryByParentId(248);
            }
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

    @ApiOperation(value = "修改货物表单打印状态", notes = "修改货物表单打印状态", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formId", value = "表单id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "isPrint", value = "是否打印", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/updateGoodsFromPrint", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateGoodsFromPrint(@Validated int formId, @Validated int isPrint) {
        CommonResponse response = new CommonResponse();
        try {
            goodsFromService.updateGoodsFromPrint(formId, isPrint);
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMessage(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMessage(HtCode.FAIL_UPDATE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取打印货物表单信息", notes = "获取打印货物信息", tags = {"@郝腾"})
    @RequestMapping(value = "/getGoodsFromPrint", method = RequestMethod.GET)
    @ResponseBody
    public LayuiCommonResponse getGoodsFromPrint() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsFrom goodsFrom = goodsFromService.selectGoodsFromPrint();
            response.setMsg("success");
            response.setCode(0);
            response.setData(goodsFrom);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }


}
