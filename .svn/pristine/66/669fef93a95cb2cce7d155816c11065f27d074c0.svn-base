package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TruckOrderMapper;
import com.siruiman.crosslogistics.model.AppTruckOrder;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;
import com.siruiman.crosslogistics.service.TruckOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TruckOrderServiceImpl implements TruckOrderService {
    @Autowired
    private TruckOrderMapper truckOrderMapper;

    @Override
    public List<AppTruckOrder> selectAppTruckOrderAll(TruckOrderDto truckOrderDto) {
        try{
            return truckOrderMapper.selectAppTruckOrderAll(truckOrderDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countAppTruckOrderAll(TruckOrderDto truckOrderDto) {
        try{
            return truckOrderMapper.countAppTruckOrderAll(truckOrderDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUser selectAppUser(int taskOrderId) {
        try{
            return truckOrderMapper.selectAppUser(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AppUser> selectTruckAppUser(String createTime) {
        try{
            List<AppUser> selectTruckAppUser = truckOrderMapper.selectTruckAppUser();
            List<AppUser> returnTruckAppUser = new ArrayList<>();
            for(AppUser appUser : selectTruckAppUser){
                Integer selectCarOrderByTruckUser = truckOrderMapper.selectCarOrderByTruckUser(appUser.getAppUserId(), createTime);
                if(selectCarOrderByTruckUser == 0){
                    returnTruckAppUser.add(appUser);
                }
            }
            return returnTruckAppUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editTruckOrderDriver(int taskOrderId, int newAppUserId) {
        try{
            /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            *//*查询当前订单是否是今天的订单*//*
            String selectCarOrderTime = truckOrderMapper.selectCarOrderTime(taskOrderId);
            if((selectCarOrderTime.substring(0,10)).equals(createTime)){
                Integer editCarOrderUser = truckOrderMapper.editTruckOrderDriver(taskOrderId, newAppUserId, 3);
                return editCarOrderUser;
            }*/
            Integer editCarOrderUser = truckOrderMapper.editTruckOrderDriver(taskOrderId, newAppUserId, 2);
            return editCarOrderUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AppTruckOrder> getAppUserTruckStatistics(String startDate, String endDate, int appUserId) {
        return truckOrderMapper.getAppUserTruckStatistics(startDate,endDate,appUserId);
    }
}
