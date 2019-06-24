package com.siruiman.crosslogistics.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeOutUtil {
    /**
     *
     * 验证验证码是否超时s
     * @return
     */
    public static int getAppUserNumber(int codeTime) {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        int nowTime = Integer.parseInt(str);

        Calendar nowBefore = Calendar.getInstance();
        Calendar nowAfter = Calendar.getInstance();
        nowBefore.add(Calendar.MINUTE, 30);
        nowAfter.add(Calendar.MINUTE, -30);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println( sdf.format(nowBefore.getTimeInMillis()) );
        System.out.println(  sdf.format(nowAfter.getTimeInMillis()) );*/

        if(nowTime >= codeTime){
            return 1;
        }

        return 0;
    }
}
