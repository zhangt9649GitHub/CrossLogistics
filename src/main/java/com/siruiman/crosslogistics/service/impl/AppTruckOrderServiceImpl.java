package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppPersonalCenterMapper;
import com.siruiman.crosslogistics.mapper.AppTruckOrderMapper;
import com.siruiman.crosslogistics.mapper.SingaporePointMapper;
import com.siruiman.crosslogistics.mapper.TruckTaskMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import com.siruiman.crosslogistics.model.dto.AppTruckOrderDto;
import com.siruiman.crosslogistics.model.dto.TruckTaskRouteDto;
import com.siruiman.crosslogistics.service.AppTruckOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppTruckOrderServiceImpl implements AppTruckOrderService {
    @Autowired
    private AppTruckOrderMapper appTruckOrderMapper;
    @Autowired
    private TruckTaskMapper truckTaskMapper;
    @Autowired
    private SingaporePointMapper singaporePointMapper;
    @Autowired
    private AppPersonalCenterMapper appPersonalCenterMapper;

    @Override
    public List<AppTruckOrder> selectAppTruckOrderAll(AppTaskOrderDto appTruckOrderDto) {
        try{
            List<AppTruckOrder> selectAppTruckOrderAll = new ArrayList<>();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String nowTime = df.format(new Date());
            appTruckOrderDto.setNowTime(nowTime);
            if(appTruckOrderDto.getSort() == 3 || appTruckOrderDto.getSort() == 4){
                if(appTruckOrderDto.getDistance() == 0){
                    AppUserInfo selectAppUserInfoByPt = appPersonalCenterMapper.selectAppUserInfoByPt(appTruckOrderDto.getAppUserId());
                    if(selectAppUserInfoByPt.getZipCode().equals("") || selectAppUserInfoByPt.getZipCode() == null){
                        return null;
                    }
                    /*根据邮编查询大楼经纬度*/
                    SingaporeAreaBuilding selectSingaporeAreaBuilding = appPersonalCenterMapper.selectSingaporeAreaBuilding(selectAppUserInfoByPt.getZipCode());
                    appTruckOrderDto.setPositionLat(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLat()));
                    appTruckOrderDto.setPositionLng(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLng()));
                    selectAppTruckOrderAll = appTruckOrderMapper.selectAppTruckOrderAll(appTruckOrderDto);
                }
            }else {
                selectAppTruckOrderAll = appTruckOrderMapper.selectAppTruckOrderAll(appTruckOrderDto);
            }

            return selectAppTruckOrderAll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(AppTaskOrderDto appTruckOrderDto) {
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String nowTime = df.format(new Date());
            appTruckOrderDto.setNowTime(nowTime);
            Integer count = 0;
            if(appTruckOrderDto.getSort() == 3 || appTruckOrderDto.getSort() == 4){
                if(appTruckOrderDto.getDistance() == 0){
                    AppUserInfo selectAppUserInfoByPt = appPersonalCenterMapper.selectAppUserInfoByPt(appTruckOrderDto.getAppUserId());
                    /*根据邮编查询大楼经纬度*/
                    SingaporeAreaBuilding selectSingaporeAreaBuilding = appPersonalCenterMapper.selectSingaporeAreaBuilding(selectAppUserInfoByPt.getZipCode());
                    appTruckOrderDto.setPositionLat(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLat()));
                    appTruckOrderDto.setPositionLng(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLng()));
                    AppTaskOrder appTaskOrder = appTruckOrderMapper.count(appTruckOrderDto);
                    if(appTaskOrder!=null&&appTaskOrder.getTotal()!=null){
                    count = appTaskOrder.getTotal();
                    }
                }
            }else {
                AppTaskOrder appTaskOrder = appTruckOrderMapper.count(appTruckOrderDto);
                if(appTaskOrder!=null&&appTaskOrder.getTotal()!=null){
                    count = appTaskOrder.getTotal();
                }
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppTruckOrderDetails selectAppTruckOrderDetails(int taskOrderId) {
        try{
            AppTruckOrderDetails selectAppTruckOrderDetails = appTruckOrderMapper.selectAppTruckOrderDetails(taskOrderId);
            BigDecimal money = selectAppTruckOrderDetails.getTotalMoney();
            BigDecimal addMoney = selectAppTruckOrderDetails.getAddMoney();
            if(addMoney != null){
                selectAppTruckOrderDetails.setTotalMoney(money.add(addMoney));
            }
            /*查询区域经纬度*/
            List<SingaporePoint> selectBySGAreaId = singaporePointMapper.selectBySGAreaId(selectAppTruckOrderDetails.getSingaporeAreaId());
            selectAppTruckOrderDetails.setSingaporePoints(selectBySGAreaId);

            /*查询货车路线*/
            List<TruckTaskRouteDto> selectTruckTaskRoute = appTruckOrderMapper.selectTruckTaskRoute(taskOrderId);
            List<TruckTaskRoute> truckTaskRoutes = new ArrayList<>();
            for(TruckTaskRouteDto truckTaskRouteDto : selectTruckTaskRoute){
                /*1小车集结点 2仓库*/
                int status = truckTaskRouteDto.getStatus();
                int routeId = truckTaskRouteDto.getRouteId();
                int sort = truckTaskRouteDto.getSort();
                if(status == 1){
                    /*查询小车集结点名称和经纬度*/
                    TruckTaskRoute truckTaskRouteByCar = truckTaskMapper.selectTruckTaskRouteByCar(routeId);
                    if(truckTaskRouteByCar != null){
                        truckTaskRouteByCar.setRouteId(routeId);
                        truckTaskRouteByCar.setStatus(status);
                        truckTaskRouteByCar.setSort(sort);
                        truckTaskRoutes.add(truckTaskRouteByCar);
                    }
                }
                if(status == 2){
                    /*查询仓库名称和经纬度*/
                    TruckTaskRoute truckTaskRouteByWarehouse = truckTaskMapper.selectTruckTaskRouteByWarehouse(routeId);
                    if(truckTaskRouteByWarehouse != null){
                        truckTaskRouteByWarehouse.setRouteId(routeId);
                        truckTaskRouteByWarehouse.setStatus(status);
                        truckTaskRouteByWarehouse.setSort(sort);
                        truckTaskRoutes.add(truckTaskRouteByWarehouse);
                    }
                }
            }
            selectAppTruckOrderDetails.setTruckTaskRoutes(truckTaskRoutes);
            /*货袋信息*/
            List<AppTruckOrderBag> appTruckOrderBagByTruckOrder = appTruckOrderMapper.selectAppTruckOrderBagByTruckOrder(taskOrderId);
            if(appTruckOrderBagByTruckOrder.size() != 0){
                selectAppTruckOrderDetails.setAppTruckOrderBags(appTruckOrderBagByTruckOrder);
            }
            return selectAppTruckOrderDetails;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppTruckOrderBagDetails selectAppTruckOrderBagDetails(int bagId) {
        try{
            return appTruckOrderMapper.selectAppTruckOrderBagDetails(bagId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
