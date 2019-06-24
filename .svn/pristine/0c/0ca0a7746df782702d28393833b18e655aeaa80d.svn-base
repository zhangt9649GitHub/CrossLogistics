package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.mapper.GoodsMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 异常货物管理
 */
@Api(value = "AbnormalGoodsManagement", description = "异常货物管理API", tags = {"异常货物管理"})
@RestController
@RequestMapping("/abnormalGoods")
public class AbnormalGoodsManagementController {

    @Autowired
    private GoodsWarningService goodsWarningService;
    @Autowired
    private GoodsDetailsService goodsDetailsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private BagSerivce bagSerivce;

    @ApiOperation(value = "获取异常货物列表", notes = "获取GoodsWarning对象列表信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "goodsWarning", value = "异常货物实体", paramType = "query", dataType = "GoodsWarning"),
    })
    @RequestMapping(value = "/getGoodsWarningList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getGoodsWarningList"})
    public LayuiCommonResponse getGoodsWarningList(@Validated @RequestParam(defaultValue = "1") int page,
                                                   @Validated @RequestParam(defaultValue = "10") int limit,
                                                   @Validated GoodsWarning goodsWarning) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            if (goodsWarning != null && goodsWarning.getWarningStateNumber() != null) {
                switch (goodsWarning.getWarningStateNumber()) {
                    case 1:
                        goodsWarning.setWarningState("无异常");
                        break;
                    case 2:
                        goodsWarning.setWarningState("异常");
                        break;
                    case 3:
                        goodsWarning.setWarningState("正在处理中");
                        break;
                }
            }
            List<GoodsWarning> goodsWarningList = goodsWarningService.selectGoodsWarningList(goodsWarning);
            int count = goodsWarningService.selectCountGoodsWarningList(goodsWarning);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(goodsWarningList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取异常货物详情信息", notes = "获取GoodsDetails对象信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getGoodsWarningDetails", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateGoodsAndGoodsDetailsInfo"})
    public LayuiCommonResponse getGoodsWarningDetailById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsDetails goodsDetails = goodsDetailsService.selectGoodsDetailById(goodsId);
            if (goodsDetails != null && goodsDetails.getWarningState() != null) {
                if (goodsDetails.getWarningState().equals("无异常")) {
                    goodsDetails.setWarningStateNumber(1);
                } else if (goodsDetails.getWarningState().equals("异常")) {
                    goodsDetails.setWarningStateNumber(2);
                } else if (goodsDetails.getWarningState().equals("正在处理中")) {
                    goodsDetails.setWarningStateNumber(3);
                }
            }
            //如果是转运订单，增加用户编号
            if (goodsDetails != null && goodsDetails.getFrom().equals("转运订单") && goodsDetails.getAppUserId() != 0) {
                AppUser appUser = appUserService.selectAppUserDetail(goodsDetails.getAppUserId());
                goodsDetails.setNumber(appUser.getNumber());
            }
            //如果三方货物的总箱数与收到的三方单不符，则将总箱数置为空
            if (goodsDetails != null && goodsDetails.getFrom().equals("三方货单") && goodsDetails.getWarningType().equals("总箱数不正确") ||
                    goodsDetails != null && goodsDetails.getFrom().equals("三方货单") && goodsDetails.getWarningType().equals("The total number of boxes is incorrect")) {
                goodsDetails.setTotalGoods(null);
            }
            if(goodsDetails.getBagId()!=null){
                Bag bag = bagSerivce.selectBagDetailsById(goodsDetails.getBagId());
                goodsDetails.setBagNumber(bag.getBagNumber());
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

    @ApiOperation(value = "查看节点操作人员信息", notes = "查看节点操作人员信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "goodsId", value = "货物id", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/getGoodsWarningOperateNameById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getWarningGoodsLogisticById"})
    public LayuiCommonResponse getGoodsWarningOperateNameById(@Validated @RequestParam(defaultValue = "1") int page,
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

    @ApiOperation(value = "获取异常货物物流进程", notes = "获取异常货物物流进程", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsId", value = "货物id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getWarningGoodsLogisticById", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getWarningGoodsLogisticById"})
    public LayuiCommonResponse getWarningGoodsLogisticById(Integer goodsId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            Goods goods1 = goodsService.selectGoodsById(goodsId);
            //转运订单的组合转运需要将物流进程排序
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


    @ApiOperation(value = "更新异常货物详情信息", notes = "更新GoodsWarning相关信息", tags = {"@郝腾"})
    @ApiImplicitParam(name = "goodsDetails", value = "数据字典实体类", paramType = "query", dataType = "GoodsDetails")
    @RequestMapping(value = "/updateGoodsWarningDetails", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = {"updateGoodsAndGoodsDetailsInfo"})
    public LayuiCommonResponse updateGoodsWarningDetails(@RequestBody GoodsDetails goodsDetails) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            if (goodsDetails != null && goodsDetails.getWarningStateNumber() != null) {
                switch (goodsDetails.getWarningStateNumber()) {
                    case 1:
                        goodsDetails.setWarningState("无异常");
                        break;
                    case 2:
                        goodsDetails.setWarningState("异常");
                        break;
                    case 3:
                        goodsDetails.setWarningState("正在处理中");
                        break;
                }
            }
            if(goodsDetails.getBagNumber()!= null && !(goodsDetails.getBagNumber().equals(""))){
                Bag bag = bagSerivce.selectBagByNumber(goodsDetails.getBagNumber());
                if( bag ==null){
                    response.setCode(HtCode.FAIL_BAG_UPDATE.getCode());
                    response.setMsg(HtCode.FAIL_BAG_UPDATE.getInfo());
                    return response;
                }else{
                    goodsDetails.setBagId(bag.getBagId());
                }
            }
            if (goodsDetails != null && goodsDetails.getFrom().equals("转运订单")) {
                //转运订单的编号不能为空
                if (goodsDetails.getNumber() == null || goodsDetails.getNumber().equals("")) {
                    response.setCode(HtCode.FAIL_UPDATE_NUMBER.getCode());
                    response.setMsg(HtCode.FAIL_UPDATE_NUMBER.getInfo());
                    return response;
                } else {
                    //编号不为空，如果查询不到用户则返回编号不正确
                    AppUser appUser = appUserService.selectAppUserByNumber(goodsDetails.getNumber());
                    if (appUser == null && appUser.getAppUserId() == null) {
                        response.setCode(HtCode.FAIL_NUMBER.getCode());
                        response.setMsg(HtCode.FAIL_NUMBER.getInfo());
                        return response;
                    } else {
                        //给GoodsDetails对象里的appUserId赋值
                        goodsDetails.setAppUserId(appUser.getAppUserId());
                    }
                }
            }
            //获取后台登录的adminUid
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            goodsDetails.setAdminUid(Integer.parseInt(user.getAdminUId()));
            String regEx = "^[0-9]+$";
            //三方物流单号必须符合纯数字组合
            if (goodsDetails.getTripartiteNumber() != null && !(goodsDetails.getTripartiteNumber().matches(regEx))) {
                response.setCode(HtCode.FAIL_TRIPARTITENUMBER.getCode());
                response.setMsg(HtCode.FAIL_TRIPARTITENUMBER.getInfo());
                return response;
            }
            //检查邮编的格式，是否符合纯数字
            if (goodsDetails.getZipCode() != null && !(goodsDetails.getZipCode().equals(""))) {
                if (!goodsDetails.getZipCode().matches(regEx)) {
                    response.setCode(HtCode.FAIL_ZIPCODE_UPDATE.getCode());
                    response.setMsg(HtCode.FAIL_ZIPCODE_UPDATE.getInfo());
                    return response;
                }
            }
            //如果异常类型为总箱数不正确
            if ((goodsDetails != null && goodsDetails.getFrom().equals("三方货单")
                    && goodsDetails.getWarningType().equals("总箱数不正确")
                    && goodsDetails.getWarningState().equals("无异常")) ||
                    (goodsDetails != null && goodsDetails.getFrom().equals("三方货单")
                            && goodsDetails.getWarningType().equals("The total number of boxes is incorrect")
                            && goodsDetails.getWarningState().equals("无异常"))) {
                //根据三方单号查询货物详情列表
                List<GoodsDetails> goodsDetailsList = goodsDetailsService.selectGoodsDetailByTripartiteNumber(goodsDetails.getTripartiteNumber());
                //已经有物流进程的总箱数
                int totalGoods = 0;
                //原始总箱数
                int oldtotalGoods = 1;
                //可以做编辑用的子货物id集合
                List<Integer> goodsIdList = new ArrayList<>();
                //如果list数据大于1，说明为分箱货物
                if (goodsDetailsList.size() >= 1) {
                    for (GoodsDetails goodsDetails1 : goodsDetailsList
                    ) {
                        //如果总箱数大于1，说明该条数据为总记录有几箱
                        if (goodsDetails1.getTotalGoods() > 1) {
                            //赋值给旧的总箱数
                            oldtotalGoods = goodsDetails1.getTotalGoods();
                        } else {
                            //分箱的子包裹货物，查询物流进程
                            List<LogisticInfo> logisticInfoList = logisticInfoService.selectGoodsLogisticInfoByGoodsId(goodsDetails1.getGoodsId());
                            //如果无物流进程
                            if (logisticInfoList.size() == 0) {
                                //将子货物id加入集合
                                goodsIdList.add(goodsDetails1.getGoodsId());
                            }
                            //如果有物流进程
                            for (LogisticInfo logisticInfo : logisticInfoList
                            ) {
                                //物流进程的数据有效时
                                if (logisticInfo != null && logisticInfo.getOperateResult() != null) {
                                    //已经有物流进程的总箱数+1
                                    totalGoods++;
                                    break;
                                } else {
                                    //将货物id加入可编辑的子货物id集合中
                                    goodsIdList.add(logisticInfo.getGoodsId());
                                }
                            }
                        }

                    }
                }
                //在无异常的情况下，编辑时输入的总箱数不能为空
                if (goodsDetails.getTotalGoods() == null && goodsDetails.getTotalGoods().equals("")) {
                    response.setCode(HtCode.FAIL_TOTALGOODS_UPDATE_NULL.getCode());
                    response.setMsg(HtCode.FAIL_TOTALGOODS_UPDATE_NULL.getInfo());
                    return response;
                    //在无异常的情况下，编辑时输入的总箱数不能小于已经有物流信息的总箱数
                } else if (goodsDetails.getTotalGoods() < totalGoods) {
                    response.setCode(HtCode.FAIL_TOTALGOODS_UPDATE.getCode());
                    response.setMsg(HtCode.FAIL_TOTALGOODS_UPDATE.getInfo());
                    return response;
                } else {
                /* if(goodsIdList.size()>0){
                     for (Integer goodsId:goodsIdList
                     ) {
                         goodsDetailsService.deleteGoodsDetailsById(goodsId);
                     }
                 }*/
                    //原始的总箱数大于编辑时输入的总箱数
                    if (oldtotalGoods > goodsDetails.getTotalGoods()) {
                        //需要变动的箱数为几(原始总箱数-有物流进程的箱数)-（编辑时输入的总箱数-有物流进程的箱数）
                        int number = (oldtotalGoods - totalGoods) - (goodsDetails.getTotalGoods() - totalGoods);
                        //遍历要变动的箱数
                        for (int i = 1; i <= number; i++) {
                            if (goodsIdList.size() > 0) {
                                //要删除的goodsId
                                int goodsId = 0;
                                //要输删除最大的子箱数
                                int num = 0;
                                for (Integer goodsId1 : goodsIdList
                                ) {
                                    //根据子包裹集合中的goodsId查询货物信息
                                    Goods goods = goodsService.selectGoodsById(goodsId1);
                                    //将快递单号由"-"做拆分
                                    String[] strs = goods.getDeliveryNumber().split("-");
                                    //如果子箱数大于num，就赋值给num，直到最大值为止
                                    if (Integer.parseInt(strs[1]) > num) {
                                        num = Integer.parseInt(strs[1]);
                                        goodsId = goodsId1;
                                    }
                                }
                                //删除该条分箱记录
                                goodsDetailsService.deleteGoodsDetailsById(goodsId);
                            }
                        }
                        //根据输入的货物详情编辑货物信息
                        goodsWarningService.updateGoodsWarningInfo(goodsDetails);
                        //原始的总箱数小于编辑时输入的总箱数
                    } else if (oldtotalGoods < goodsDetails.getTotalGoods()) {
                        int number = 0;
                        if (oldtotalGoods > 1) {
                            number = (goodsDetails.getTotalGoods() - totalGoods) - (oldtotalGoods - totalGoods);
                        } else if (oldtotalGoods == 1) {
                            number = goodsDetails.getTotalGoods();
                        }
                        //需要添加的箱数为几（编辑时输入的总箱数-有物流进程的箱数）-(原始总箱数-有物流进程的箱数)

                        //根据货物id查询货物详情
                        GoodsDetails goodsDetails2 = goodsDetailsService.selectGoodsDetailById(goodsDetails.getGoodsId());
                        //遍历要添加的箱数
                        for (int i = 1; i <= number; i++) {
                            //添加分箱数
                            goodsDetailsService.insertGoodsDetails(goodsDetails2, oldtotalGoods, i);
                        }
                        //根据输入的货物详情编辑货物信息
                        goodsWarningService.updateGoodsWarningInfo(goodsDetails);
                    }
                }
            } else {
                //如果不是总箱数不正确的问题，直接编辑货物信息
                goodsWarningService.updateGoodsWarningDetails(goodsDetails);
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


}
