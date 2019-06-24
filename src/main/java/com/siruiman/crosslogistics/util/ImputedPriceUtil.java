package com.siruiman.crosslogistics.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * 计算空运和海运价格
 */
public class ImputedPriceUtil {
    private static double regularAirPriceOne = StaticConfigUtil.regularAirPriceOne;//普货：0.5kg以内29
    private static double regularAirPriceTwo = StaticConfigUtil.regularAirPriceTwo;//普货：0.5kg以上每超过0.5kg加收9
    private static double regularAirPriceThree = StaticConfigUtil.regularAirPriceThree;//普货：超过10kg-50kg 每千克19
    private static double regularAirPriceFour = StaticConfigUtil.regularAirPriceFour;//普货：超过50kg-100kg 每千克18
    private static double regularAirPriceFive = StaticConfigUtil.regularAirPriceFive;//普货：超过100kg 每千克17
    private static double sensitiveAirPriceOne = StaticConfigUtil.sensitiveAirPriceOne;//敏感：0.5kg以内35
    private static double sensitiveAirPriceTwo = StaticConfigUtil.sensitiveAirPriceTwo;//敏感：0.5kg以上每超过0.5kg 13
    private static double sensitiveAirPriceThree = StaticConfigUtil.sensitiveAirPriceThree;//敏感： 超过10kg-50kg 23
    private static double sensitiveAirPriceFour = StaticConfigUtil.sensitiveAirPriceFour;//敏感： 超过50kg-100kg 22
    private static double marinePriceOne = StaticConfigUtil.marinePriceOne;//普货 1-5立方   450/立方
    private static double marinePriceTwo = StaticConfigUtil.marinePriceTwo;//5-10立方  420/立方
    private static double marinePriceThree = StaticConfigUtil.marinePriceThree;//10-20立方 390/立方
    private static double marinePriceFour = StaticConfigUtil.marinePriceFour;// 20立方+   370/立方
    private static double unilateralOverlengthPrice = StaticConfigUtil.unilateralOverlengthPrice;//空运：货物长、宽、高是否有>=150cm  加收150
    private static double overweightPrice = StaticConfigUtil.overweightPrice; //空运：货物重量是否有>=40kg  加收150
    private static double emergencyDeliveryPrice = StaticConfigUtil.emergencyDeliveryPrice; //空运和海运： 是否紧急加收150
    private static double GSTPrice = StaticConfigUtil.GSTPrice;//空运:货品超过2021RMB（SGD400）交GST税
    private static double rate = StaticConfigUtil.rate;//税率7%
    private static double permit = StaticConfigUtil.permit;//permit费用250
    private static double sensitiveMarinePrice = StaticConfigUtil.sensitiveMarinePrice;//海运敏感货每单加收100

    /**
     * 普通转运计算空运价格
     * 单位 m  kg
     *
     * @param actualLong
     * @param actualWidth
     * @param actualHeight
     * @param actualWeight
     * @param category
     * @param isUrgent
     * @return 人民币
     */
    public static BigDecimal getAirFreightPrice(Float actualLong, Float actualWidth, Float actualHeight, Float actualWeight, String category, String isUrgent) throws IllegalArgumentException {
        double price = 0;
        double newActualWeight = 0;
        double newActualWeight1 = 0;
        //0.计算航空标准的重量
        BigDecimal actualLong1 = new BigDecimal(Float.toString(actualLong * 100));
        BigDecimal actualWidth1 = new BigDecimal(Float.toString(actualWidth * 100));
        BigDecimal actualHeight1 = new BigDecimal(Float.toString(actualHeight * 100));
        BigDecimal volume = (actualLong1.multiply(actualWidth1)).multiply(actualHeight1);
        BigDecimal actualWeight1 = new BigDecimal(Float.toString(actualWeight));
        BigDecimal base = new BigDecimal(6000);
        BigDecimal actualWeight2 = volume.divide(base, 5, ROUND_HALF_DOWN);
        //-1表示小于，0是等于，1是大于
        if (actualWeight2.compareTo(actualWeight1) > 0) {
            newActualWeight = actualWeight2.doubleValue();

        } else {
            newActualWeight = actualWeight.doubleValue();
        }

        //1.判断货物长、宽、高是否有>=150cm  加收150
        if (actualLong >= 1.5 || actualWidth >= 1.5 || actualHeight >= 1.5) {
            price = price + unilateralOverlengthPrice;
        }
        //2.判断货物重量是否有>=40kg  加收150
        if (newActualWeight >= 40) {
            price = price + overweightPrice;
        }
        //3.判断是否紧急  加收150
        if (isUrgent.equals("是")) {
            price = price + emergencyDeliveryPrice;
        }
        //4.计算价格
        //空运0.01-10kg 普货：0.5kg以内29 0.5kg以上每超过0.5kg加收9  敏感：0.5kg以内35 0.5kg以上每超过0.5kg 13 每单加收30
        //普货：超过10kg-50kg 每千克19  敏感： 超过10kg-50kg 23
        //普货：超过50kg-100kg 每千克18  敏感： 超过10kg-50kg 22
        //普货：超过100kg 每千克17       敏感： 超过100kg 报错，无法配送
        if (category.equals("普通")) {
            if (newActualWeight <= 10) {
                if (newActualWeight <= 0.5) {
                    price = price + regularAirPriceOne;
                } else {
                    //空运向上取整
                    newActualWeight = Math.ceil(newActualWeight);
                    newActualWeight1 = (int) newActualWeight;
                    price = price + regularAirPriceOne + ((int) ((newActualWeight1 - 0.5) / 0.5) * regularAirPriceTwo);
                }
            } else if (newActualWeight <= 50) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * regularAirPriceThree);
            } else if (newActualWeight <= 100) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * regularAirPriceFour);
            } else if (newActualWeight > 100) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * regularAirPriceFive);
            }
        } else {
            if (newActualWeight <= 10) {
                if (newActualWeight <= 0.5) {
                    price = price + sensitiveAirPriceOne + 30;
                } else {
                    //空运向上取整
                    newActualWeight = Math.ceil(newActualWeight);
                    newActualWeight1 = (int) newActualWeight;
                    price = price + sensitiveAirPriceOne + 30 + ((int) ((newActualWeight1 - 0.5) / 0.5) * sensitiveAirPriceTwo);
                }
            } else if (newActualWeight <= 50) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * sensitiveAirPriceThree);
            } else if (newActualWeight <= 100) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * sensitiveAirPriceFour);
            } else if (newActualWeight > 100) {
                throw new IllegalArgumentException("敏感物品超过100kg，无法配送");
            }

        }
        BigDecimal totalprice = new BigDecimal(price);
        //5.判断总价是否高于SGD400 合人民币2021  货物价*7% +250
        //-1表示小于，0是等于，1是大于
        //改变GST算法，货值大于400SGD之后收取
      /*  if (price>GSTPrice) {
            double taxPrice1 = (price * rate) + permit;
            BigDecimal taxPrice2 = new BigDecimal(Double.toString(taxPrice1));
            totalprice =totalprice.add(totalprice.add(taxPrice2));
            BigDecimal  totalprice1 =totalprice.setScale(2, RoundingMode.HALF_UP);
            return totalprice1;
        }*/
        BigDecimal totalprice1 = totalprice.setScale(2, RoundingMode.HALF_UP);
        return totalprice1;
    }

    /**
     * 普通转运计算海运价格
     *
     * @param actualLong
     * @param actualWidth
     * @param actualHeight
     * @param category
     * @param isUrgent
     * @return
     */
    public static BigDecimal getShippingPrice(Float actualLong, Float actualWidth, Float actualHeight, String category, String isUrgent) {
        BigDecimal totalprice = new BigDecimal(0);
        BigDecimal sensitive = new BigDecimal(sensitiveMarinePrice);
        BigDecimal urgent = new BigDecimal(emergencyDeliveryPrice);
        if (category.equals("敏感")) {
            totalprice = totalprice.add(sensitive);
        }
        if (isUrgent.equals("是")) {
            totalprice = totalprice.add(urgent);
        }
        //1.计算体积
        BigDecimal actualLong1 = new BigDecimal(Float.toString(actualLong));
        BigDecimal actualWidth1 = new BigDecimal(Float.toString(actualWidth));
        BigDecimal actualHeight1 = new BigDecimal(Float.toString(actualHeight));
        BigDecimal volume = (actualLong1.multiply(actualWidth1)).multiply(actualHeight1);
        BigDecimal p1 = new BigDecimal(5);
        BigDecimal p2 = new BigDecimal(10);
        BigDecimal p3 = new BigDecimal(20);
        BigDecimal p4 = new BigDecimal(21);
        BigDecimal price1 = new BigDecimal(marinePriceOne);
        BigDecimal price2 = new BigDecimal(marinePriceTwo);
        BigDecimal price3 = new BigDecimal(marinePriceThree);
        BigDecimal price4 = new BigDecimal(marinePriceFour);
        //2.计算价格
        //普货 1-5立方   450/立方
        //     5-10立方  420/立方
        //     10-20立方 390/立方
        //     20立方+   370/立方
        //-1表示小于，0是等于，1是大于
        if (volume.compareTo(p1) <= 0) {
            totalprice = totalprice.add(volume.multiply(price1));
        } else if (volume.compareTo(p2) <= 0) {
            totalprice = totalprice.add(volume.multiply(price2));
        } else if (volume.compareTo(p3) <= 0) {
            totalprice = totalprice.add(volume.multiply(price3));
        } else if (volume.compareTo(p4) <= 0) {
            totalprice = totalprice.add(volume.multiply(price4));
        }

       /* BigDecimal price = new BigDecimal(400);
        BigDecimal rate = new BigDecimal(0.07);
        BigDecimal permit = new BigDecimal(250);
        //3.判断总价是否高于SGD400  货物价*7% +250  海运是都要交的，具体收费是到新加坡之后跟客户联系收费
        if (totalprice.compareTo(price)>0) {
            totalprice = totalprice.add(totalprice.multiply(rate).add(permit));
            return totalprice;
        }*/
        return totalprice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算合并转运货物运费
     *
     * @param volume       体积之和 m^3
     * @param actualWeight 重量之和
     * @param flag         单边最长有大于150cm为true，反之false
     * @param category     货品分类
     * @param isUrgent     是否紧急
     * @return
     */
    public static BigDecimal getAirFreightMergePrice(BigDecimal volume, BigDecimal actualWeight, Boolean flag, String category, String isUrgent) throws IllegalArgumentException {
        double price = 0;
        double newActualWeight = 0;
        double newActualWeight1 = 0;
        //将立方米转换成立方厘米
        BigDecimal num = new BigDecimal(1000000);
        volume = volume.multiply(num);

        BigDecimal base = new BigDecimal(6000);
        BigDecimal actualWeight1 = volume.divide(base, 5, ROUND_HALF_DOWN);
        //-1表示小于，0是等于，1是大于
        if (actualWeight1.compareTo(actualWeight) > 0) {
            newActualWeight = actualWeight1.doubleValue();

        } else {
            newActualWeight = actualWeight.doubleValue();
        }

        //1.判断货物长、宽、高是否有>=150cm  加收150
        if (flag) {
            price = price + unilateralOverlengthPrice;
        }
        //2.判断货物重量是否有>=40kg  加收150
        if (newActualWeight >= 40) {
            price = price + overweightPrice;
        }
        //3.判断是否紧急  加收150
        if (isUrgent.equals("是")) {
            price = price + emergencyDeliveryPrice;
        }

        //4.计算价格
        //空运0.01-10kg 普货：0.5kg以内29 0.5kg以上每超过0.5kg加收9  敏感：0.5kg以内35 0.5kg以上每超过0.5kg 13 每单加收30
        //普货：超过10kg-50kg 每千克19  敏感： 超过10kg-50kg 23
        //普货：超过50kg-100kg 每千克18  敏感： 超过10kg-50kg 22
        //普货：超过100kg 每千克17       敏感： 超过100kg 报错，无法配送
        if (category.equals("普通")) {
            if (newActualWeight <= 10) {
                if (newActualWeight <= 0.5) {
                    price = price + regularAirPriceOne;
                } else {
                    //空运向上取整
                    newActualWeight = Math.ceil(newActualWeight);
                    newActualWeight1 = (int) newActualWeight;
                    price = price + regularAirPriceOne + ((int) ((newActualWeight1 - 0.5) / 0.5) * regularAirPriceTwo);
                }
            } else if (newActualWeight <= 50) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * regularAirPriceThree);
            } else if (newActualWeight <= 100) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * regularAirPriceFour);
            } else if (newActualWeight > 100) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * regularAirPriceFive);
            }
        } else {
            if (newActualWeight <= 10) {
                if (newActualWeight <= 0.5) {
                    price = price + sensitiveAirPriceOne + 30;
                } else {
                    //空运向上取整
                    newActualWeight = Math.ceil(newActualWeight);
                    newActualWeight1 = (int) newActualWeight;
                    price = price + sensitiveAirPriceOne + 30 + ((int) ((newActualWeight1 - 0.5) / 0.5) * sensitiveAirPriceTwo);
                }
            } else if (newActualWeight <= 50) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * sensitiveAirPriceThree);
            } else if (newActualWeight <= 100) {
                newActualWeight = Math.ceil(newActualWeight);
                newActualWeight1 = (int) newActualWeight;
                price = price + (newActualWeight1 * sensitiveAirPriceFour);
            } else if (newActualWeight > 100) {
                throw new IllegalArgumentException("敏感物品超过100kg，无法配送");
            }

        }
        BigDecimal totalprice = new BigDecimal(price);
        //5.判断总价是否高于SGD400 合人民币2021  货物价*7% +250
        //-1表示小于，0是等于，1是大于
        //改变GST算法，货值大于400SGD之后收取
       /* if (price>GSTPrice) {
            double taxPrice1 = (price * rate) + permit;
            BigDecimal taxPrice2 = new BigDecimal(Double.toString(taxPrice1));
            totalprice =totalprice.add(totalprice.add(taxPrice2));
            BigDecimal  totalprice1 =totalprice.setScale(2, RoundingMode.HALF_UP);
            return totalprice1;
        }*/
        BigDecimal totalprice1 = totalprice.setScale(2, RoundingMode.HALF_UP);
        return totalprice1;
    }

    /**
     * 计算合并转运海运运费
     *
     * @param volume   体积之和
     * @param category 货品分类
     * @param isUrgent 是否紧急配送
     * @return
     */
    public static BigDecimal getShippingMergePrice(BigDecimal volume, String category, String isUrgent) {
        BigDecimal totalprice = new BigDecimal(0);
        BigDecimal sensitive = new BigDecimal(sensitiveMarinePrice);
        BigDecimal urgent = new BigDecimal(emergencyDeliveryPrice);
        if (category.equals("敏感")) {
            totalprice = totalprice.add(sensitive);
        }
        if (isUrgent.equals("是")) {
            totalprice = totalprice.add(urgent);
        }
        BigDecimal p1 = new BigDecimal(5);
        BigDecimal p2 = new BigDecimal(10);
        BigDecimal p3 = new BigDecimal(20);
        BigDecimal p4 = new BigDecimal(21);
        BigDecimal price1 = new BigDecimal(marinePriceOne);
        BigDecimal price2 = new BigDecimal(marinePriceTwo);
        BigDecimal price3 = new BigDecimal(marinePriceThree);
        BigDecimal price4 = new BigDecimal(marinePriceFour);
        //2.计算价格
        //普货 1-5立方   450/立方
        //     5-10立方  420/立方
        //     10-20立方 390/立方
        //     20立方+   370/立方
        //-1表示小于，0是等于，1是大于
        if (volume.compareTo(p1) <= 0) {
            totalprice = totalprice.add(volume.multiply(price1));
        } else if (volume.compareTo(p2) <= 0) {
            totalprice = totalprice.add(volume.multiply(price2));
        } else if (volume.compareTo(p3) <= 0) {
            totalprice = totalprice.add(volume.multiply(price3));
        } else if (volume.compareTo(p4) <= 0) {
            totalprice = totalprice.add(volume.multiply(price4));
        }

        return totalprice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}