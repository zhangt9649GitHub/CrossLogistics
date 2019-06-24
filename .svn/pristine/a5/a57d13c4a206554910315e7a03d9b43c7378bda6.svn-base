package com.siruiman.crosslogistics.zhangzhanwei;

import com.github.wxpay.sdk.WXPayUtil;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.service.BagSerivce;
import com.siruiman.crosslogistics.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/21
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BagTest {
    @Autowired
    private BagSerivce bagSerivce;
    @Autowired
    private GoodsService goodsService;
    @Test
    public void insert(){
        HashMap<String, String> map = new HashMap<>();
        map.put("return_code","SUCCESS");
        map.put("return_msg","OK");
        try {
            String xml = WXPayUtil.mapToXml(map);

            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void select(){
        List<Goods> list = goodsService.selectByBagId(72);
        System.out.println(list);
    }

}
