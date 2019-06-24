package com.siruiman.crosslogistics.ht;

import com.siruiman.crosslogistics.model.LogisticInfo;
import com.siruiman.crosslogistics.model.Message;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import com.siruiman.crosslogistics.service.MessageService;
import com.siruiman.crosslogistics.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static com.siruiman.crosslogistics.util.CarJPushClientUtil.pushCarMsg;
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpushTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LogisticInfoService logisticInfoService;

    /**
     * 别名测试
     */
    @Test
    public void jushClientTest1() {
        Message message = new Message();
        message.setAppUserId(30);
        message.setTitle("测试alias");
        message.setContent("测试内容alias");
        message.setType("通知消息");
        message.setCategory("小车");
        message.setState("未读");
        message.setAddTime(new Date());
        message.setEnTitle("测试alias");
        message.setEnContent("测试内容alias");
        message.setEnType("Notice");
        messageService.insert(message);
        pushCarMsg("alias", "测试alias", "Car13643962", "测试内容alias");
    }

    /**
     * 小车标签测试
     */
    @Test
    public void jushClientTest2() {
        pushCarMsg("tag", "测试tag", "小车13373748", "测试内容tag");
    }

    /**
     * 小车标签组测试
     */
    @Test
    public void jushClientTest3() {
        pushCarMsg("tag", "测试tagsList", "小车", "测试内容tagsList");
    }

    @Test
    public void jushClientTest4() {
        pushCarMsg("alias", "测试alias", "Car13373748", "您的转运包裹已到仓库，可以发起包裹转运了" + "\r\nYour transshipment parcel has arrived at the warehouse and can be used to initiate parcel transfer.");
    }

    @Test
    public void jushClientTest5() {
        int result = messageService.insertMessage(2, 0, 2, 0, 0, 0, 0);
    }

    @Test
    public void jushClientTest6() {
        int result = messageService.insertMessage(3, 0, 0, 490, 0, 0, 0);
    }

    @Test
    public void jushClientTest7() {
        int result = messageService.insertMessage(6, 35, 0, 0, 1737, 0, 0);
    }

    @Test
    public void Test8(){
       goodsService.updateGoodsInOutWarehouse(4,6,"04379876078");
    //                记录货物操作进程
    LogisticInfo goodsInfo = new LogisticInfo();
                goodsInfo.setCreateTime(new Date());
                goodsInfo.setGoodsId(263);
                goodsInfo.setStaffId(2);
                goodsInfo.setOperateName("马旭东");
                goodsInfo.setOperateComment("货物入新加坡仓库");
                goodsInfo.setOperateType("货物入库");
                goodsInfo.setOperateResult("貨物已到達新加坡倉庫");
                logisticInfoService.insertLogisticInfo(goodsInfo);
}


    @Test
    public void jushClientTest50(){

        System.out.println(DateUtil.getWXPayExpireTime(300));

    }
}
