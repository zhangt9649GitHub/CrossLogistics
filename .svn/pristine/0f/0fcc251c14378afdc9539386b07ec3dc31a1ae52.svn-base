package com.siruiman.crosslogistics.zhangzhanwei;

import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.TaskOrderBagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/10 9:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PDABagTest {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private TaskOrderBagService taskOrderBagService;
//  测试根据货袋编号查出货物快递单号货物id
    @Test
    public void selectGoods(){
        goodsService.selectByBagId(1);
        System.out.println();
    }


    @Test
    public void selectGoodsId(){
//        int bagId = goodsService.selectBagIdByGdId(218);
//        System.out.println(bagId);
        taskOrderBagService.insertTruckBag(1,1);

    }

}
