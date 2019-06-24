package com.siruiman.crosslogistics;

import com.siruiman.crosslogistics.mapper.ChinaAreaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrosslogisticsApplicationTests {
    @Autowired
    private ChinaAreaMapper chinaAreaMapper;
    @Test
    public void contextLoads() {
    }
//    省市区导入数据库
    @Test
    public  void   insertChinaArea(){

    }
}

