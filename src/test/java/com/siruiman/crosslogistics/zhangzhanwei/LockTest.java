package com.siruiman.crosslogistics.zhangzhanwei;


import com.siruiman.crosslogistics.mqttclient.MqttConnection;
import com.siruiman.crosslogistics.util.LockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {
    @Test
    public void lockTest(){
        try {
            LockUtils.unLock("858740056017238");//858740056017238//858740056017238
        } catch (Exception e) {
//            开锁失败
            System.out.println("开锁失败");
            e.printStackTrace();
        }

    }


}
