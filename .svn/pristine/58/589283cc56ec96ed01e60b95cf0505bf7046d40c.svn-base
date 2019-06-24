package com.siruiman.crosslogistics.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRateUtil {
    //unit 1.人民币 2.美元 3.新币

    //导入是美元，系统呈现也是美元
    //汇率换算
    public static BigDecimal rateExchange(BigDecimal price,int unitExchange) {
        //美元与新加坡币的汇率 1美元=1.362 新币
        //新加坡元与人民币的汇率  1新币=4.9441元人民币
        //美元与人民币的汇率 1美元=6.7201 元人民币

        //新币换算成美元32
        //人民币换算成美元12
        //美元换算成新币23
        //人民币换算成新币13
        //美元换算成人民币21
        //新币换算成人民币31
        switch (unitExchange){
                      //新币除以汇率
            case 32 : return price.divide(StaticConfigUtil.SGDtoUSDtoExchangeFrate, 2, BigDecimal.ROUND_UP);
                      //人民币除以汇率
            case 12 : return price.divide(StaticConfigUtil.USDtoCNYExchangeFrate, 2, BigDecimal.ROUND_UP);
                      //美元乘以汇率
            case 23 : return price.multiply(StaticConfigUtil.SGDtoUSDtoExchangeFrate);
                     //人民币除以汇率
            case 13 : return price.divide(StaticConfigUtil.SGDtoCNYExchangeFrate, 2, BigDecimal.ROUND_UP);
                     //美元乘以汇率
            case 21 : return price.multiply(StaticConfigUtil.USDtoCNYExchangeFrate);
                     //新币乘以汇率
            case 31 : return price.multiply(StaticConfigUtil.SGDtoCNYExchangeFrate);
        }

        return null;
    }
}
