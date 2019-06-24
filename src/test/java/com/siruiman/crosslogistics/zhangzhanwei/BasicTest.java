package com.siruiman.crosslogistics.zhangzhanwei;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.siruiman.crosslogistics.config.NETSPayConfig;
import com.siruiman.crosslogistics.model.Results;
import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import com.siruiman.crosslogistics.model.dto.TruckDriverOrder;
import com.siruiman.crosslogistics.service.ResultsService;
import com.siruiman.crosslogistics.service.SingaporeAreaBuildingService;
import com.siruiman.crosslogistics.service.TruckDriverOrderService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicTest {

    @Autowired
    private ResultsService resultsService;

    @Autowired
    private TruckDriverOrderService truckDriverOrderService;
    @Autowired
    private SingaporeAreaBuildingService singaporeAreaBuildingService;

    @Test
    public void test(){
        System.out.println(truckDriverOrderService.getUserByUID(30));
    }
   @Test
    public void  addResults()  {
        String url ;
        List<Results> list;
        for (int i = 69288  ; i <993461 ; i++) {
            System.out.println("------------------------------------------------------");
            System.out.println(i);
            url = "https://developers.onemap.sg/commonapi/search?searchVal="+i+"&returnGeom=Y&getAddrDetails=Y&pageNum=1";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 2.设置请求方式和请求信息
            HttpGet httpGet = new HttpGet(url);
            // 3.执行请求
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 4.获取返回值
            String s = null;
            try {
                s = EntityUtils.toString(response.getEntity(),Charset.forName("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 5.截取json数组
            int a = s.indexOf("results\":");
            String substring = s.substring(a+9, s.length() - 1);
//        如果截取的字符串为空或者没有内容就跳过
            if(substring==null&&substring=="[]"){
                continue;
            }
//        将json数组转化为集合
            list = JSONObject.parseArray(substring, Results.class);
            if (list.size()!=0&&list!=null){
                for (Results results1 :list){
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingService.selectSGBuildingByZipCode(results1.getPOSTAL());
                    if(singaporeAreaBuilding==null){
                        // 如果是公交车站就不插入
                        System.out.println(results1.getPOSTAL());
                        if("NIL".equals(results1.getPOSTAL())){
                            continue;
                        }
                        try{
                            SingaporeAreaBuilding singaporeAreaBuilding1 = new SingaporeAreaBuilding();
                            singaporeAreaBuilding1.setSaId(1);
                            singaporeAreaBuilding1.setSaBuildingName(results1.getBUILDING());
                            singaporeAreaBuilding1.setSaBuildingLat(results1.getLATITUDE());
                            singaporeAreaBuilding1.setSaBuildingLng(results1.getLONGITUDE());
                            singaporeAreaBuilding1.setSaZipCode(results1.getPOSTAL());
                            singaporeAreaBuilding1.setSaBuildingAddress(results1.getADDRESS());
                            singaporeAreaBuildingService.insert(singaporeAreaBuilding1);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }



    @Test
    public void test1() throws IOException {
        String json ="{\"msg\":{\"netsMid\":\"UMID_877772003\",\"merchantTxnRef\":\"2019-03-13 09:43:29\",\"netsMidIndicator\":\"U\",\"netsTxnRef\":\"20190313094330211\",\"tid\":\"06414789377\",\"paymentMode\":\"QR\",\"submissionMode\":\"B\",\"currencyCode\":\"SGD\",\"merchantTxnDtm\":\"02090409 09:43:29.727\",\"merchantTimeZone\":\"+8:00\",\"paymentType\":\"SALE\",\"clientType\":\"S\",\"stageRespCode\":\"3099-00000\",\"txnRand\":\"71579190420711888\",\"mobileOS\":\"IOS\",\"actionCode\":\"0\",\"netsTxnDtm\":\"20190313 09:43:30.265\",\"netsTimeZone\":\"+08:00\",\"netsTxnStatus\":\"0\",\"netsTxnMsg\":\"Successful\",\"netsAmountDeducted\":2232},\"ss\":\"1\"}";
        JSONObject obj = JSONObject.parseObject(json);
        JSONObject msg = (JSONObject) obj.get("msg");
        Object netsMid = msg.get("netsMid");
        System.out.println(NETSPayConfig.UMID.equals(msg.get("netsMid")));
        System.out.println("Successful".equals(msg.get("netsTxnMsg")));
        System.out.println("0".equals(msg.getString("netsTxnStatus")));
        System.out.println("Successful".equals(msg.getString("netsTxnMsg")));
        System.out.println(NETSPayConfig.UMID.equals(netsMid));
        System.out.println(msg.get("tid"));
        System.out.println(netsMid);

    }

    @Test
    public void test2(){
        String json ="{\"msg\":{\"netsMid\":\"UMID_877772003\",\"merchantTxnRef\":\"2019-03-13 09:43:29\",\"netsMidIndicator\":\"U\",\"netsTxnRef\":\"20190313094330211\",\"tid\":\"06414789377\",\"paymentMode\":\"QR\",\"submissionMode\":\"B\",\"currencyCode\":\"SGD\",\"merchantTxnDtm\":\"02090409 09:43:29.727\",\"merchantTimeZone\":\"+8:00\",\"paymentType\":\"SALE\",\"clientType\":\"S\",\"stageRespCode\":\"3099-00000\",\"txnRand\":\"71579190420711888\",\"mobileOS\":\"IOS\",\"actionCode\":\"0\",\"netsTxnDtm\":\"20190313 09:43:30.265\",\"netsTimeZone\":\"+08:00\",\"netsTxnStatus\":\"0\",\"netsTxnMsg\":\"Successful\",\"netsAmountDeducted\":2232},\"ss\":\"1\"}";

        JsonParser jsonParser = new JsonParser();
        JsonObject asJsonObject = jsonParser.parse(json)
                .getAsJsonObject();

        JsonObject msg = asJsonObject.get("msg").getAsJsonObject();
        JsonElement netsMid = msg.get("netsMid");

        System.out.println(NETSPayConfig.UMID.equals(netsMid));
        System.out.println(netsMid);
    }

    @Test
    public void test3(){
       List<Results> resultsList =resultsService.select(1,19);
        for (Results results:resultsList
             ) {
            SingaporeAreaBuilding singaporeAreaBuilding1 = new SingaporeAreaBuilding();
            singaporeAreaBuilding1.setSaId(1);
            singaporeAreaBuilding1.setSaBuildingName(results.getBUILDING());
            singaporeAreaBuilding1.setSaBuildingLat(results.getLATITUDE());
            singaporeAreaBuilding1.setSaBuildingLng(results.getLONGITUDE());
            singaporeAreaBuilding1.setSaZipCode(results.getPOSTAL());
            singaporeAreaBuilding1.setSaBuildingAddress(results.getADDRESS());
            singaporeAreaBuildingService.insert(singaporeAreaBuilding1);
        }
    }


}
