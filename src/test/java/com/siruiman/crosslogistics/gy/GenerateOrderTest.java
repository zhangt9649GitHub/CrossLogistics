package com.siruiman.crosslogistics.gy;

import com.siruiman.crosslogistics.service.GenerateCarordersService;
import com.siruiman.crosslogistics.service.GenerateTruckOrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateOrderTest {
    @Autowired
    private GenerateCarordersService generateCarordersService;
    @Autowired
    private GenerateTruckOrdersService generateTruckOrdersService;

    @Test
    public void orderTest() {
        Integer generateCarorders = generateCarordersService.generateCarorders();
        System.out.println(generateCarorders);
    }

    @Test
    public void truckOrderTest() {
        Integer generateTruckOrders = generateTruckOrdersService.generateTruckOrders();
        System.out.println(generateTruckOrders);
    }
}
