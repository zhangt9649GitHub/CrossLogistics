package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppPersonalCenterMapper;
import com.siruiman.crosslogistics.mapper.AppTaskOrderMapper;
import com.siruiman.crosslogistics.mapper.SingaporePointMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AppTaskOrderDto;
import com.siruiman.crosslogistics.service.AppTaskOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppTaskOrderServiceImpl implements AppTaskOrderService {
    @Autowired
    private AppTaskOrderMapper appTaskOrderMapper;
    @Autowired
    private SingaporePointMapper singaporePointMapper;
    @Autowired
    private AppPersonalCenterMapper appPersonalCenterMapper;

    @Override
    public List<TaskOrder> selectTaskOrderAll(AppTaskOrderDto appTaskOrderDto) {
        try{
            /*if(appTaskOrderDto.getPositionLat() == 0.0){
                appTaskOrderDto.setPositionLat(1.3621181805339657);
                appTaskOrderDto.setPositionLat(103.85827491528323);
            }*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String nowTime = df.format(new Date());
            appTaskOrderDto.setNowTime(nowTime);
            List<TaskOrder> selectTaskOrderAll = new ArrayList<>();
            if(appTaskOrderDto.getSort() == 3 || appTaskOrderDto.getSort() == 4){
                if(appTaskOrderDto.getDistance() == 0){
                    AppUserInfo selectAppUserInfoByPt = appPersonalCenterMapper.selectAppUserInfoByPt(appTaskOrderDto.getAppUserId());
                    if(selectAppUserInfoByPt.getZipCode().equals("") || selectAppUserInfoByPt.getZipCode() == null){
                        return null;
                    }
                    /*根据邮编查询大楼经纬度*/
                    SingaporeAreaBuilding selectSingaporeAreaBuilding = appPersonalCenterMapper.selectSingaporeAreaBuilding(selectAppUserInfoByPt.getZipCode());
                    appTaskOrderDto.setPositionLat(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLat()));
                    appTaskOrderDto.setPositionLng(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLng()));
                    selectTaskOrderAll = appTaskOrderMapper.selectTaskOrderAll(appTaskOrderDto);
                }
            }else {
                selectTaskOrderAll = appTaskOrderMapper.selectTaskOrderAll(appTaskOrderDto);
            }
            return selectTaskOrderAll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Integer count(AppTaskOrderDto appTaskOrderDto) {
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String nowTime = df.format(new Date());
            appTaskOrderDto.setNowTime(nowTime);
            Integer count = 0;
            if(appTaskOrderDto.getSort() == 3 || appTaskOrderDto.getSort() == 4){
                if(appTaskOrderDto.getDistance() == 0){
                    AppUserInfo selectAppUserInfoByPt = appPersonalCenterMapper.selectAppUserInfoByPt(appTaskOrderDto.getAppUserId());
                    /*根据邮编查询大楼经纬度*/
                    SingaporeAreaBuilding selectSingaporeAreaBuilding = appPersonalCenterMapper.selectSingaporeAreaBuilding(selectAppUserInfoByPt.getZipCode());
                    appTaskOrderDto.setPositionLat(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLat()));
                    appTaskOrderDto.setPositionLng(Double.valueOf(selectSingaporeAreaBuilding.getSaBuildingLng()));
                    AppTaskOrder appTaskOrder = appTaskOrderMapper.count(appTaskOrderDto);
                    count = appTaskOrder.getTotal();
                }
            }else {
                AppTaskOrder appTaskOrder = appTaskOrderMapper.count(appTaskOrderDto);
                if(appTaskOrder != null && appTaskOrder.getTotal() != null){
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
    public TaskOrderDetails selectTaskOrderDetails(int taskOrderId) {
        try{
            TaskOrderDetails selectTaskOrderDetails = appTaskOrderMapper.selectTaskOrderDetails(taskOrderId);
            List<SingaporePoint> selectBySGAreaId = singaporePointMapper.selectBySGAreaId(selectTaskOrderDetails.getSingaporeAreaId());
            selectTaskOrderDetails.setSingaporePoints(selectBySGAreaId);
            List<AppTaskOrderGoods> selectAppTasjOrderGoods = appTaskOrderMapper.selectAppTaskOrderGoods(taskOrderId);
            selectTaskOrderDetails.setAppTaskOrderGoods(selectAppTasjOrderGoods);
            return selectTaskOrderDetails;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
