package com.siruiman.crosslogistics.zhangzhanwei;

import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.model.SingaporeArea;
import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import com.siruiman.crosslogistics.model.SingaporePoint;
import com.siruiman.crosslogistics.service.RallyPointService;
import com.siruiman.crosslogistics.service.SingaporeAreaBuildingService;
import com.siruiman.crosslogistics.service.SingaporeAreaService;
import com.siruiman.crosslogistics.service.SingaporePointService;
import com.siruiman.crosslogistics.util.MapUtils;
import com.siruiman.crosslogistics.util.MyPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 张占伟
 * @date 2018/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AreaTest {
    @Autowired
    private RallyPointService rallyPointService;
    @Autowired
    private SingaporePointService singaporePointService;
    @Autowired
    private SingaporeAreaService singaporeAreaService;
    @Autowired
    private SingaporeAreaBuildingService singaporeAreaBuildingService;

    @Test
    public void addRallypointTest(){

        RallyPoint rallyPoint = new RallyPoint();
        rallyPoint.setDelState(1);
        rallyPoint.setRallyPointAccount("helloworld");
        rallyPoint.setSingaporeAreaId(1);
        rallyPoint.setRallyPointAddress("anywhere");
        rallyPointService.insertRallyPoint(rallyPoint);

    }
    @Test
    public void updateArea(){
        RallyPoint rallyPoint = new RallyPoint();
        rallyPoint.setRallyPointId(3);
        rallyPoint.setDelState(1);
        rallyPoint.setRallyPointAccount("helloworld");
        rallyPoint.setSingaporeAreaId(1);
        rallyPoint.setRallyPointAddress("anywhere");
        rallyPointService.updateRallyPoint(rallyPoint);

    }


    /**
     * 划区
     */
    @Test
    public void  adas(){
        String name =null;
        List<SingaporeArea> list = singaporeAreaService.selectAll(name);
        SingaporeArea area = list.get(0);
//        List<SingaporePoint> points = singaporePointService.selectBySGAreaId(area.getSingaporeAreaId());
//            List<MyPoint> myPoints = new ArrayList<>();
//            for (SingaporePoint sp:points) {
//                MyPoint myPoint = new MyPoint();
//                myPoint.setX(Double.parseDouble(sp.getLat()));
//                myPoint.setY(Double.parseDouble(sp.getLng()));
//                myPoints.add(myPoint);
//            }
//            MyPoint myPoint1 = new MyPoint();
            List<SingaporeAreaBuilding> buildings = singaporeAreaBuildingService.selectNoAreaBuilding("");
            int i=0;
            for (SingaporeAreaBuilding a:buildings){
//                myPoint1.setX(Double.parseDouble(a.getSaBuildingLat()));
//                myPoint1.setY(Double.parseDouble(a.getSaBuildingLng()));
//                boolean results = MapUtils.isPointInPolygon(myPoint1, myPoints);
//                System.out.println(results);
//                if(results==true){
//                    查出区域集结点id
                    List<RallyPoint> list1 = rallyPointService.selectBySGId(area.getSingaporeAreaId());
                    Integer pointId=1;
                    if (i%2==0){
                     pointId = list1.get(1).getRallyPointId();
                   }else{
                        pointId = list1.get(0).getRallyPointId();
                    }
                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    System.out.println(area.getSingaporeAreaName());
                    a.setSaId(area.getSingaporeAreaId());
                    a.setRallyPointId(pointId);
                    singaporeAreaBuildingService.updateBuidingArea(a);
                    System.out.println("修改大楼以完成");
                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    i++;
                }

        }
/**
 *Step 2
 * Merchant shall create (a) the Transaction Request (txnReq) message and (b) generate the
 * MAC value of txnReq. Together with the downloaded keyId, the merchant shall send them to
 * the Merchant App. The keyId can be downloaded from the NETS Admin Portal.
 * (a) txnReq message
 * The txnReq message is a JSON string. Merchants shall refer to section 5.2.2 to form the
 * actual txnReq message. Below is an example of txnReq message. Please ensure that the
 * final JSON string do not contain CR or LF.
 * {"ss":"1","msg":{"netsMid":"UMID_887770001","tid":"","submissionMode":"B","txnAmount":"1000","me
 * rchantTxnRef":"2017060510265198","merchantTxnDtm":"20170605
 * 10:36:51.989","paymentType":"SALE","currencyCode":"SGD","paymentMode":"","merchantTimeZ
 * one":"+8:00","b2sTxnEndURL":"https://sit2.enets.sg/MerchantApp/sim/b2sTxnEndURL.jsp","b2sTxn
 * EndURLParam":"","s2sTxnEndURL":"https://sit2.enets.sg/MerchantApp/rest/s2sTxnEnd","s2sTxnEn
 * dURLParam":"","clientType":"S","supMsg":"","netsMidIndicator":"U","ipAddress":"127.0.0.1","langua
 * ge":"en","mobileOs":"ANDROID"}}
 * The txnReq format is described below.
 *  “ss”:”1” – is default to 1 and the value is used by eNETS GW internally.
 *  “msg: { <is a nested json string - refer to Appendix D – Message Format TxnReq> }
 * Please refer to Appendix D – Message Format description and replace the field values in the
 * sample codes.
 * For easy readability the above json message is as follows.
 * <replace> There are 6 fields that require you to replace their values
 * < default value1,2> If your requirement defer from the default value, please read the Appendix D
 * Message Format to understand the value to put in.
 * Default1 may be required to change. Default 2 is unlikely to be changed.
 *
 *
 *
 * */

//    }
}
