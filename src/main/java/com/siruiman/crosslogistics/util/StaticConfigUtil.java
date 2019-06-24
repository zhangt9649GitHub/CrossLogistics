package com.siruiman.crosslogistics.util;


import java.math.BigDecimal;
import java.util.Hashtable;

/**
 * @author 张占伟
 * @date 2019/1/15 11:40
 */
public class StaticConfigUtil  {
    public static int generateOrderTimeLimitCar =0;//生成小车订单的时间

    public static int generateOrderTimeLimitTruck=0;//生成大货车订单的时间

    public static double regularAirPriceOne = 0;//普货：0.5kg以内29

    public static double regularAirPriceTwo = 0;//普货：0.5kg以上每超过0.5kg加收9

    public static double regularAirPriceThree =0;//普货：超过10kg-50kg 每千克19

    public static double regularAirPriceFour =0;//普货：超过50kg-100kg 每千克18

    public static double regularAirPriceFive =0;//普货：超过100kg 每千克17

    public static double sensitiveAirPriceOne =0;//敏感：0.5kg以内35

    public static double sensitiveAirPriceTwo =0;//敏感：0.5kg以上每超过0.5kg 13

    public static double sensitiveAirPriceThree =0;//敏感： 超过10kg-50kg 23

    public static double sensitiveAirPriceFour =0;//敏感： 超过10kg-50kg 22

    public static double marinePriceOne =0;//普货 1-5立方   450/立方

    public static double marinePriceTwo =0;//5-10立方  420/立方

    public static double marinePriceThree = 0;//10-20立方 390/立方

    public static double marinePriceFour =0;// 20立方+   370/立方

    public static double unilateralOverlengthPrice =0;//空运：货物长、宽、高是否有>=150cm  加收150

    public static double overweightPrice = 0; //空运：货物重量是否有>=40kg  加收150

    public static double emergencyDeliveryPrice =0; //空运和海运： 是否紧急加收150

    public static double GSTPrice = 0;//空运:货品超过2021RMB（SGD400）交GST税

    public static double rate = 0;//税率7%

    public static double permit = 0;//permit费用250

    public static double sensitiveMarinePrice =0;//海运敏感货每单加收100

    public static double anOrderPrice=0;//小车运输一件货的价格

    public static double anBagPrice =0;//货车运输一个货袋的价格

    public static String STC="";//客服电话

    public static double waringGoodsPrice =0;//一个异常件扣得钱数

    public static BigDecimal SGDtoCNYExchangeFrate =new BigDecimal("0");//人民币与新加坡币的汇率

    public static BigDecimal USDtoCNYExchangeFrate =new  BigDecimal("0");//美元与人民币的汇率

    public static BigDecimal SGDtoUSDtoExchangeFrate =new  BigDecimal("0");//美元与新币的汇率

    public static Hashtable lockState = new Hashtable();


}
