package com.siruiman.crosslogistics.zhangzhanwei;

import com.github.wxpay.sdk.WXPayUtil;
import com.siruiman.crosslogistics.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * @author 张占伟
 * @date 2018/12/25 19:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CarTest {
    @Autowired
    private CarService carService;

    @Test
    public void add(){
        System.out.println(new Date());

    }

    @Test
    public void test(){
        long l = System.currentTimeMillis();
        System.out.println(l);

    }

    @Test
    public void test1(){
        char a = 'A';
        for (int i = 0; i < 26; i++) {
            System.out.print( a+",");
            a++;
        }

    }
    @Test
    public void test2(){
        String[] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q"
                ,"r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M"
                ,"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int a=r.nextInt(arr.length);
            int b= r.nextInt(arr.length);
            String merchantTxnRef =a+ arr[a]+b+arr[b];
            System.out.println(merchantTxnRef
            +",");
        }

    }
}
