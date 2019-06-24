package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.enums.ZwCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.GoodsOrderDto;
import com.siruiman.crosslogistics.model.dto.TruckDriverOrder;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 张占伟
 * @date 2019/3/21 11:40
 * 统计管理 添加订单统计
 */
@RestController
@Api(value = "统计查询", description = "statistics-api", tags = {"订单统计"})
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    private TruckDriverOrderService truckDriverOrderService;

    @Autowired
    private CarOrderService carOrderService;

    @Autowired
    private TruckOrderService truckOrderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FinanceMoneyFlowService financeMoneyFlowService;

 /*   @ApiOperation(value = "获取订单统计",notes = "订单统计",nickname = "getOrderStatistics",tags={"@张占伟"})
    @RequestMapping(value = "getOrderStatistics",method = RequestMethod.POST)
    public LayuiCommonResponse getOrderStatistics(String date){
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
//          大货车某天订单总数
            int countFinishOrder = truckDriverOrderService.selectCountFinishOrderByTime(date);
//            大货车某天订单完成数量
            int countorder =truckDriverOrderService.selectCountOrderByTime(date);
//            小车订单完成数量
            int countfinishOrderCar = carOrderService.selectCountFinishOrderByTime(date);
//            小车订单完成数量
            int countOrderCar = carOrderService.selectCountOrderByTime(date);
            Statistics statistics = new Statistics();
            statistics.setCountCarOrder(countOrderCar);
            statistics.setCountFinishCarOrder(countfinishOrderCar);
            statistics.setCountFinishTruckOrder(countorder);
            statistics.setCountFinishTruckOrder(countFinishOrder);
            response.setData(statistics);
            response.setCode(ZwCode.SUCCESS_GET.getCode());
            response.setMsg(ZwCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(ZwCode.FAIL_GET.getCode());
            response.setMsg(ZwCode.FAIL_GET.getInfo());
        }
        return response;
    }*/

    @ApiOperation(value = "统计", notes = "统计", nickname = "getStatistics", tags = {"@郝腾"})
    @RequestMapping(value = "getStatistics", method = RequestMethod.GET)
    public LayuiCommonResponse getStatistics() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            GoodsOrderStatistical goodsOrderStatistical = new GoodsOrderStatistical();
            Date date = new Date();
            String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00";
            String nowDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date) + " 23:59:59";
            List<ExportGoodsOrder> goodsList = goodsService.selectGoodsBySendTime(nowDate, nowDate1);
            int dailyOrderQuantity = 0;
            int toSendSuccessfully = 0;
            BigDecimal totalAmountCollectOnDelivery = new BigDecimal(0);
            for (ExportGoodsOrder goodsOrder : goodsList
            ) {
                //今日有多少订单
                if (goodsOrder.getTotalGoods() == 1) {
                    dailyOrderQuantity++;
                    //成功送出多少单
                    if (goodsOrder != null && goodsOrder.getIsReceiveGoods() == 1) {
                        toSendSuccessfully++;
                    }
                }
                //货到付款应收多少钱
                if (goodsOrder != null && goodsOrder.getIsArrivalPay() == 2) {
                    if (goodsOrder.getItemValue() != null) {
                        totalAmountCollectOnDelivery = totalAmountCollectOnDelivery.add(goodsOrder.getItemValue());
                    }
                }
            }
            goodsOrderStatistical.setDailyOrderQuantity(dailyOrderQuantity);
            goodsOrderStatistical.setToSendSuccessfully(toSendSuccessfully);
            goodsOrderStatistical.setTotalAmountCollectOnDelivery(totalAmountCollectOnDelivery);
            //余额提现总支出多少钱，单位人民币
            String financeMoneyType = "余额提现";
            int unit = 1;
            BigDecimal withdrawalSpending = new BigDecimal(0);
            List<FinanceMoneyFlow> financeMoneyFlowList = financeMoneyFlowService.selectFinanceFlowList(null, null, financeMoneyType, unit);
            for (FinanceMoneyFlow financeMoneyFlow : financeMoneyFlowList
            ) {
                withdrawalSpending = withdrawalSpending.add(financeMoneyFlow.getAmount());
            }
            goodsOrderStatistical.setWithdrawalSpending(withdrawalSpending);
            //余额提现总支出多少钱，单位新币
            int unit2 = 3;
            BigDecimal withdrawalSGDSpending = new BigDecimal(0);
            List<FinanceMoneyFlow> financeMoneyFlowList2 = financeMoneyFlowService.selectFinanceFlowList(null, null, financeMoneyType, unit2);
            for (FinanceMoneyFlow financeMoneyFlow2 : financeMoneyFlowList2
            ) {
                withdrawalSGDSpending = withdrawalSGDSpending.add(financeMoneyFlow2.getAmount());
            }
            goodsOrderStatistical.setWithdrawalSGDSpending(withdrawalSGDSpending);

            //转运订单支付人民币共计多少钱
            String financeMoneyType1 = "订单支付";
            BigDecimal transshipmentRmbCollection = new BigDecimal(0);
            List<FinanceMoneyFlow> financeMoneyFlowList1 = financeMoneyFlowService.selectFinanceFlowList(null, null, financeMoneyType1, unit);
            for (FinanceMoneyFlow financeMoneyFlow : financeMoneyFlowList1
            ) {
                transshipmentRmbCollection = transshipmentRmbCollection.add(financeMoneyFlow.getAmount());
            }
            goodsOrderStatistical.setTransshipmentRmbCollection(transshipmentRmbCollection);
          /*  //转运订单支付美分共计多少钱
            int unit1 = 2;
            BigDecimal transshipmentCentCollection = new BigDecimal(0);
            List<FinanceMoneyFlow> financeMoneyFlowList2 = financeMoneyFlowService.selectFinanceFlowList(null, null, financeMoneyType1, unit1);
            for (FinanceMoneyFlow financeMoneyFlow : financeMoneyFlowList2
            ) {
                transshipmentCentCollection = transshipmentCentCollection.add(financeMoneyFlow.getAmount());
            }
            goodsOrderStatistical.setTransshipmentCentCollection(transshipmentCentCollection);
            //转运订单支付新分共计多少钱
            int unit2 = 3;
            BigDecimal transshipmentSGDCentCollection = new BigDecimal(0);
            List<FinanceMoneyFlow> financeMoneyFlowList3 = financeMoneyFlowService.selectFinanceFlowList(null, null, financeMoneyType1, unit2);
            for (FinanceMoneyFlow financeMoneyFlow : financeMoneyFlowList3
            ) {
                transshipmentSGDCentCollection = transshipmentSGDCentCollection.add(financeMoneyFlow.getAmount());
            }
            goodsOrderStatistical.setTransshipmentSGDCentCollection(transshipmentSGDCentCollection);*/
            response.setData(goodsOrderStatistical);
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "三方账号查看的统计", notes = "三方账号查看的统计", nickname = "getThreeGoodsStatistics", tags = {"@郝腾"})
    @RequestMapping(value = "getThreeGoodsStatistics", method = RequestMethod.GET)
    public LayuiCommonResponse getThreeGoodsStatistics() {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            ThreeGoodsOrderStatistical threeGoodsOrderStatistical = new ThreeGoodsOrderStatistical();
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            int type = user.getType();
            int adminUserTypeId = user.getAdminUserTypeId();
            int warehouseId =user.getWarehouseId();
            List<ExportGoodsOrder> goodsList =new ArrayList<>();
            if (type == 3) {
                 goodsList = goodsService.selectThreeGoodsByAdminUserTypeId(adminUserTypeId);

            }else if(type == 2){
                  goodsList =  goodsService.selectGoodsListByWarehouseId(warehouseId);
            }
            //订单已派送数
            int quantityDispatched = 0;
            //订单派送完成数
            int orderFulfillment = 0;
            //订单派送异常件
            int orderException = 0;
            //订单未派送数
            int undeliveredQuantity = 0;
            //订单已派送件中应收货到付款金额
            BigDecimal accountsReceivable = new BigDecimal(0);
            //订单已派送完成件中实收货到付款金额
            BigDecimal realCollection = new BigDecimal(0);
            if (goodsList.size() > 0) {
                for (ExportGoodsOrder exportGoodsOrder : goodsList
                ) {
                    if (exportGoodsOrder.getSendTime() != null && exportGoodsOrder.getTotalGoods() != null && exportGoodsOrder.getTotalGoods() == 1) {
                        quantityDispatched++;
                    } else if (exportGoodsOrder.getSendTime() == null && exportGoodsOrder.getTotalGoods() != null && exportGoodsOrder.getTotalGoods() == 1) {
                        undeliveredQuantity++;
                    }
                    if (exportGoodsOrder.getSendTime() != null && exportGoodsOrder.getIsReceiveGoods() == 1 && exportGoodsOrder.getTotalGoods() == 1) {
                        orderFulfillment++;
                    }
                    if (exportGoodsOrder.getSendTime() != null && exportGoodsOrder.getStatus() == 2 && exportGoodsOrder.getTotalGoods() == 1) {
                        orderException++;
                    }
                    if (exportGoodsOrder.getSendTime() != null && exportGoodsOrder.getIsArrivalPay() == 2) {
                        if (exportGoodsOrder.getItemValue() != null) {
                            accountsReceivable = accountsReceivable.add(exportGoodsOrder.getItemValue());
                        }
                    }
                    if (exportGoodsOrder.getSendTime() != null && exportGoodsOrder.getIsReceiveGoods() == 1 && exportGoodsOrder.getIsArrivalPay() == 2) {
                        if (exportGoodsOrder.getItemValue() != null) {
                            realCollection = realCollection.add(exportGoodsOrder.getItemValue());
                        }
                    }
                }

            }
            threeGoodsOrderStatistical.setQuantityDispatched(quantityDispatched);
            threeGoodsOrderStatistical.setOrderFulfillment(orderFulfillment);
            threeGoodsOrderStatistical.setOrderException(orderException);
            threeGoodsOrderStatistical.setUndeliveredQuantity(undeliveredQuantity);
            threeGoodsOrderStatistical.setAccountsReceivable(accountsReceivable);
            threeGoodsOrderStatistical.setRealCollection(realCollection);
            response.setData(threeGoodsOrderStatistical);
            response.setMsg(HtCode.SUCCESS_GET.getInfo());
            response.setCode(HtCode.SUCCESS_GET.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMsg(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取某段时间货物订单统计", notes = "货物订单派送统计", nickname = "getAppUserCarStatistics", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "goodsOrderDto", value = "货物实体", paramType = "query", dataType = "GoodsOrderDto"),
    })
    @RequestMapping(value = "getAppUserCarStatistics", method = RequestMethod.POST)
    @RequiresPermissions(value = {"getAppUserCarStatistics"})
    public LayuiCommonResponse getAppUserCarStatistics(@Validated @RequestParam(defaultValue = "1") int page,
                                                       @Validated @RequestParam(defaultValue = "10") int limit,
                                                       @Validated GoodsOrderDto goodsOrderDto) {

        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<ExportGoodsOrder> goodsList = goodsService.selectGoodsListByUserName(goodsOrderDto);
            int count = goodsService.selectCountGoodsListByUserName(goodsOrderDto);
            response.setData(goodsList);
            response.setCount(count);
            response.setMsg("success");
            response.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

    @ApiOperation(value = "获取订单统计报表报表导出", notes = "订单统计导出", nickname = "getAppUserTruckStatistics", tags = {"@郝腾"})
    @RequestMapping(value = "getCarStatisticsFile", method = RequestMethod.GET)
    @RequiresPermissions(value = {"getCarStatisticsFile"})
    public void getCarStatisticsFile(HttpServletResponse response, String startDate, String endDate, String sendPeople)
            throws IOException {
        /**1、用HSSFWorkbook打开或者创建“Excel文件对象”

         2、用HSSFWorkbook对象返回或者创建Sheet对象

         3、用Sheet对象返回行对象，用行对象得到Cell对象

         4、对Cell对象读写。

         5、将生成的HSSFWorkbook放入HttpServletResponse中响应到前端页面
         * */
        response.setContentType("application/application/vnd.ms-excel");
        response.setHeader("Content-disposition",
                "attachment;filename=" + URLEncoder.encode("GoodsOrder" + ".xls", "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("派送方");
        row.createCell(1).setCellValue("货物来源");
        row.createCell(2).setCellValue("快递单号");
        row.createCell(3).setCellValue("货物类型");
        row.createCell(4).setCellValue("是否已签收");
        row.createCell(5).setCellValue("派送时间");
        row.createCell(6).setCellValue("货值");
        row.createCell(7).setCellValue("是否货到付款");
        ByteArrayOutputStream arrayInputStream = new ByteArrayOutputStream();
        int index = 1;
        try {
            GoodsOrderDto goodsOrderDto = new GoodsOrderDto();
            goodsOrderDto.setStartDate(startDate);
            goodsOrderDto.setEndDate(endDate);
            goodsOrderDto.setSendPeople(sendPeople);
            List<ExportGoodsOrder> goodsList = goodsService.selectGoodsListByUserName(goodsOrderDto);
            for (ExportGoodsOrder order : goodsList
            ) {
                row = sheet.createRow(index);
                if (order.getSendPeople() != null) {
                    row.createCell(0).setCellValue(order.getSendPeople());
                }
                row.createCell(1).setCellValue(order.getFrom());
                row.createCell(2).setCellValue(order.getDeliveryNumber());
                if (order.getGoodType() != null) {
                    row.createCell(3).setCellValue(order.getGoodType());
                }
                if (order.getIsReceiveGoods() != null) {
                    if (order.getIsReceiveGoods() == 1) {
                        row.createCell(4).setCellValue("是");
                    } else if (order.getIsReceiveGoods() == 2) {
                        row.createCell(4).setCellValue("否");
                    }
                }
                if (order.getSendTime() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(order.getSendTime());
                    row.createCell(5).setCellValue(str);
                }
                if (order.getItemValue() != null) {
                    row.createCell(6).setCellValue(order.getItemValue().doubleValue());
                }
                if (order.getIsArrivalPay() > 0) {
                    if (order.getIsArrivalPay() == 1) {
                        row.createCell(7).setCellValue("否");
                    }
                    if (order.getIsArrivalPay() == 2) {
                        row.createCell(7).setCellValue("是");
                    }
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        workbook.write(arrayInputStream);
        byte[] bytes = arrayInputStream.toByteArray();
        out.write(bytes);
        out.close();
    }
}
