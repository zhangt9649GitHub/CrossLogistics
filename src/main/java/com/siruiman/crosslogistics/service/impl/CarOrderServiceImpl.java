package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarOrderMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.TruckOrderDto;
import com.siruiman.crosslogistics.service.CarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarOrderServiceImpl implements CarOrderService {
    @Autowired
    private CarOrderMapper carOrderMapper;

    @Override
    public List<TaskOrder> selectTaskOrderAll(TruckOrderDto truckOrderDto) {
        try{
            return carOrderMapper.selectTaskOrderAll(truckOrderDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countTaskOrderAll(TruckOrderDto truckOrderDto) {
        try{
            return carOrderMapper.countTaskOrderAll(truckOrderDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RallyPoint> selectRallyPointBySingaporeArea(int singaporeAreaId) {
        try{
            return carOrderMapper.selectRallyPointBySingaporeArea(singaporeAreaId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editCarOrderUser(int taskOrderId, int appUserId) {
        try{
            /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            *//*查询当前订单是否是今天的订单*//*
            String selectCarOrderTime = carOrderMapper.selectCarOrderTime(taskOrderId);
            if((selectCarOrderTime.substring(0,10)).equals(createTime)){
                Integer editCarOrderUser = carOrderMapper.editCarOrderUser(taskOrderId, appUserId, 3);
                return editCarOrderUser;
            }*/
            Integer editCarOrderUser = carOrderMapper.editCarOrderUser(taskOrderId, appUserId, 2);
            return editCarOrderUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AppUser> selectAppUser(String createTime) {
        try{
            /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());*/
            List<AppUser> selectAppUser = carOrderMapper.selectAppUser();
            /*List<AppUser> retutnAppUser = new ArrayList<>();
            for(AppUser appUser : selectAppUser){
                Integer selectCarOrderByUser = carOrderMapper.selectCarOrderByUser(appUser.getAppUserId(), createTime);
                if(selectCarOrderByUser == 0){
                    retutnAppUser.add(appUser);
                }
            }*/
            return selectAppUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUser selectAppUser(int taskOrderId) {
        try{
            return carOrderMapper.selectAppUserByCarOrderId(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Car selectCarByCarOrder(int taskOrderId) {
        try{
            return carOrderMapper.selectCarByCarOrder(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> selectCarNoCarOrder(String createTime) {
        try{
            List<Car> selectCarAll = carOrderMapper.selectCarAll();
            List<Car> selectCarNoCarOrder = new ArrayList<>();
            for(Car car : selectCarAll){
                Integer selectCarOrderByCar = carOrderMapper.selectCarOrderByCar(car.getCarId(), createTime);
                if(selectCarOrderByCar == 0){
                    selectCarNoCarOrder.add(car);
                }
            }
            return selectCarNoCarOrder;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer editCarOrderCar(int taskOrderId, int newCarId) {
        try{
            /*根据小车订单id查询当前订单所绑定的货袋*/
            Integer selectBagIdByCarOrder = carOrderMapper.selectBagIdByCarOrder(taskOrderId);
            if(selectBagIdByCarOrder == null){
                return 2;
            }
            /*查询当前订单状态*/
            Integer carOrderStatus = carOrderMapper.selectCarOrderStatus(taskOrderId);
            /*如果订单已经完成*/
            if(carOrderStatus == 5){
                return 3;
            }
            /*修改订单绑定小车*/
            Integer editCarOrderCarId = carOrderMapper.editCarOrderCarId(taskOrderId, selectBagIdByCarOrder);
            /*修改货袋绑定小车*/
            Integer editBagCarId = carOrderMapper.editBagCarId(selectBagIdByCarOrder, newCarId);
            return editBagCarId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String selectCarOrderTime(int taskOrderId) {
        try{
            return carOrderMapper.selectCarOrderTime(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectCountOrderByTime(String date) {
        return carOrderMapper.selectCountOrderByTime(date);
    }

    @Override
    public int selectCountFinishOrderByTime(String date) {
        return carOrderMapper.selectCountOrderByTime(date);
    }

    @Override
    public List<AppCarOrder> getAppUserCarStatistics(String startDate, String endDate, int appUserId) {
        return carOrderMapper.getAppUserCarStatistics(startDate,endDate,appUserId);
    }
}
