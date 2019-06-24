package com.siruiman.crosslogistics.zhangzhanwei;

import com.siruiman.crosslogistics.model.RallyPoint;
import com.siruiman.crosslogistics.model.Results;
import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import com.siruiman.crosslogistics.service.RallyPointService;
import com.siruiman.crosslogistics.service.ResultsService;
import com.siruiman.crosslogistics.service.SingaporeAreaBuildingService;
import com.siruiman.crosslogistics.util.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/26 19:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SingaporeTest {
    @Autowired
    private SingaporeAreaBuildingService singaporeAreaBuildingService;
    @Autowired
    private ResultsService resultsService;
    @Autowired
    private RallyPointService rallyPointService;

    @Test
    public void partition(){
        SingaporeAreaBuilding s = new SingaporeAreaBuilding();
//        for (int i = 1; i <130000 ; i+=100) {
//            int a=i;
//            int b=i+100;
//            System.out.println(i+"asdgfasiaiuysd***********************************************");

        List<Results> list = resultsService.select(100001,20000);
        for (Results r:list) {
                String postal = r.getPOSTAL();
                s.setSaZipCode(postal);
                s.setSaBuildingName( r.getBUILDING());
                s.setSaBuildingLat(r.getLATITUDE());
                s.setSaBuildingLng(r.getLONGITUDE());
                s.setSaBuildingAddress(r.getADDRESS());

                try {
                    singaporeAreaBuildingService.insert(s);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
//            System.out.println(i+"##############################################################################################");

        }
//测试地图距离计算116.314191,39.959096
//116.329898,39.964112
    @Test
    public void mapTest(){
        System.out.println("-------------------------------------------------------------------------------------------------------");
        double v = MapUtils.getDistance(116.313733, 39.959259, 116.329898,39.964112);
        System.out.println(v);
    }


    @Test
    public void addRp(){
//        集结点添加
        RallyPoint rallyPoint = new RallyPoint();
        rallyPoint.setRallyPointAddress("新加坡街道001");
        rallyPoint.setSingaporeAreaId(1);
        rallyPoint.setRallyPointAccount("测试添加001");
        rallyPoint.setRallyPointName("紫禁之巅");
        rallyPoint.setRallyPointLat("1.37899224735908");
        rallyPoint.setRallyPointLng("103.947454401619");
        rallyPoint.setRallyPointNumber("013413211");
        rallyPointService.insertRallyPoint(rallyPoint);



    }
}

