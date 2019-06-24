package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppTaskCarOrderMapper;
import com.siruiman.crosslogistics.mapper.SingaporeAreaMapper;
import com.siruiman.crosslogistics.mapper.SingaporePointMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.AppTaskCarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AppTaskCarOrderServiceImpl implements AppTaskCarOrderService {
    @Autowired
    private AppTaskCarOrderMapper appTaskCarOrderMapper;
    @Autowired
    private SingaporePointMapper singaporePointMapper;

    @Override
    public List<AppTaskCarOrder> selectAppTaskCarOrderByStatus(int type, int appUserId) {
        try{
            return appTaskCarOrderMapper.selectAppTaskCarOrderByStatus(type, appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(int type, int appUserId) {
        try{
            return appTaskCarOrderMapper.count(type, appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppTaskCarOrderDetails selectAppTaskCarOrderDetails(int taskOrderId) {
        try{
            /*查询小车订单详情*/
            AppTaskCarOrderDetails appTaskCarOrderDetails = appTaskCarOrderMapper.selectAppTaskCarOrderDetails(taskOrderId);
            BigDecimal money = appTaskCarOrderDetails.getTotalMoney();
            BigDecimal addMoney = appTaskCarOrderDetails.getAddMoney();
            if(addMoney != null){
                appTaskCarOrderDetails.setTotalMoney(money.add(addMoney));
            }
            List<SingaporePoint> singaporePoints = singaporePointMapper.selectBySGAreaId(appTaskCarOrderDetails.getSingaporeAreaId());
            appTaskCarOrderDetails.setSingaporePoints(singaporePoints);
            /*送货路线*/
            List<AppTaskCarOrderRoute> appTaskCarOrderRoute = appTaskCarOrderMapper.selectAppTaskCarOrderRoute(taskOrderId);
            if(appTaskCarOrderRoute.size() == 0 && appTaskCarOrderRoute == null){
                appTaskCarOrderDetails.setAppTaskCarOrderRoutes(appTaskCarOrderRoute);
            }
            /*货物信息*/
            List<AppTaskCarOrderBag> appTaskCarOrderBag = appTaskCarOrderMapper.selectAppTaskCarOrderBag(taskOrderId);
            if(appTaskCarOrderBag.size() == 0 && appTaskCarOrderBag == null){
                appTaskCarOrderDetails.setAppTaskCarOrderBags(appTaskCarOrderBag);
            }
            return appTaskCarOrderDetails;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
