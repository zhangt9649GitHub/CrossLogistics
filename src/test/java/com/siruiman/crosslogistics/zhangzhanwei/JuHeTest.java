package com.siruiman.crosslogistics.zhangzhanwei;

import com.siruiman.crosslogistics.util.JuHeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 张占伟
 * @date 2019/3/7 9:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JuHeTest {

    @Test
    public void test1(){
        JuHeUtil.getRequest1();
    }
    @Test
    public void test2(){
        JuHeUtil.getRequest2();
    }

}
