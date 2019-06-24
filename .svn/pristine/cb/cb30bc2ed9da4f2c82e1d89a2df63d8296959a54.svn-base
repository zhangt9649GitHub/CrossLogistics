package com.siruiman.crosslogistics.zhangzhanwei;

import com.siruiman.crosslogistics.config.IWXPayConfig;
import com.siruiman.crosslogistics.config.MessageConfig;
import com.siruiman.crosslogistics.util.DateUtil;
import com.siruiman.crosslogistics.util.SmsCodeUtil;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author 张占伟
 * @date 2019/1/24 13:58
 *
 * 短信测试
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageTest {
    private static final Logger logger = LoggerFactory
            .getLogger(MessageTest.class);
    @Test
    public void messageTest(){
        logger.info("正在发起请求");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.nxcloud.com/api/sms/mtsend");
        RequestConfig config =  RequestConfig.custom()
                .setConnectTimeout(15000)   //设置连接服务器主机时间
                .setConnectionRequestTimeout(60000) //连接请求超时时间
                .setSocketTimeout(60000)    //读取响应超时时间
                .build();
        httpPost.setConfig(config);
        String message = "验证码:678453,30分钟内有效,请勿向其他人泄露";
        HashMap<String, String> map = new HashMap<>();
        map.put("appkey","WYzst4TM");       //短信应用appkey
        map.put("secretkey","z44hfWrT");    //短信应用secretkey
        map.put("phone","006598076188");        //手机号
        map.put("content",message);      //短信内容
//        将map集合转换为form表单提交
        List<NameValuePair> list = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key:keySet) {
            list.add(new BasicNameValuePair(key,map.get(key)));
        }
        UrlEncodedFormEntity form =null;
        try {
            form = new UrlEncodedFormEntity(list, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.info("form表单错误！");
            e.printStackTrace();
        }
        httpPost.setEntity(form);
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String results = EntityUtils.toString(entity, "UTF-8");
            logger.info(results);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void yunpianTest(){
        SmsCodeUtil.singleSend("+6598067087","7896");
    }

    @Test
    public void timetest(){
        System.out.println(DateUtil.getmerchantTxnDtm());
    }

}
