package com.siruiman.crosslogistics.util;

import java.util.Random;

public class RandomCodeUtil {
    /**
     * 九位获取随机码
     * V2.0
     * 01.管理员管理,02.员工管理,03.用户管理,04.货物管理,05.异常货物管理,06.货袋管理,07.小车管理,08.货车管理,09.任务管理,
     * 10.任务订单,11.财务管理,12.仓库管理,13.仓位管理 14.提现申请
     */
    public static String getRandomCode() {
        Random random = new Random();
        Long rannum = (long) (random.nextDouble() * (999999999 - 100000000 + 1)) + 100000000;
        String code = String.valueOf(rannum);
        return code;
    }

    /**
     * 六位随机码
     * 03+用户编号使用
     */
    public static String getSixRandomCode() {
        Random random = new Random();
        Integer rannum = (int) (random.nextDouble() * (999999 - 100000 + 1)) + 100000;
        String code = String.valueOf(rannum);
        return code;
    }


    public static String getEightRandomCode() {
        Random random = new Random();
        Long rannum = (long) (random.nextDouble() * (99999999 - 10000000 + 1)) + 10000000;
        String code = String.valueOf(rannum);
        return code;
    }
}
