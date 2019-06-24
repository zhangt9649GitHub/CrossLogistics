package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarTaskMapper;
import com.siruiman.crosslogistics.mapper.GenerateCarordersMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AddCarOrderDto;
import com.siruiman.crosslogistics.service.GenerateCarordersService;
import com.siruiman.crosslogistics.util.Base64;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenerateCarordersServiceImpl implements GenerateCarordersService {

    @Autowired
    private GenerateCarordersMapper generateCarordersMapper;
    @Autowired
    private CarTaskMapper carTaskMapper;

    /*@Override
    public Integer generateCarorders() {

        try{
            for(int i = 0; i < StaticConfigUtil.generateOrderTimeLimitCar; i++){
                int timeNum = i;
                *//*获取几天后的时间*//*
                String fetureDate = Base64.getFetureDate(timeNum);

                *//*查询所有小车*//*
                List<Car> selectCarAll = generateCarordersMapper.selectCarAll();
                for(Car car : selectCarAll){
                    int carId = car.getCarId();
                    *//*根据小车id查询小车对应任务模板*//*
                    CarTask carTask = generateCarordersMapper.selectCarTask(carId);
                    *//*订单编号*//*
                    String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                    AddCarOrderDto addCarOrderDto = new AddCarOrderDto();

                    *//*查询小车所对应的集结点和区域*//*
                    RallyPointAndSingaporeArea rallyPointAndSingaporeArea = generateCarordersMapper.selectRallyPointAndSingaporeArea(carId);
                    int rallyPointId = rallyPointAndSingaporeArea.getRallyPointId();
                    String rallyPointName = rallyPointAndSingaporeArea.getRallyPointName();
                    int singaporeAreaId = rallyPointAndSingaporeArea.getSingaporeAreaId();
                    String singaporeAreaName = rallyPointAndSingaporeArea.getSingaporeAreaName();

                    *//*钱*//*
                    Double averageMoney = selectAverageMoney(carId);

                    *//*订单名称*//*
                    String orderName = singaporeAreaName + rallyPointName + orderNumber;

                    *//*查询当前时间和小车id查询今天是否有订单*//*
                    CarOrderUserCar carOrderUserCar = generateCarordersMapper.selectCarOrderUserCar(fetureDate, carId);
                    *//*如果小车有任务模板*//*
                    if(carTask != null){
                        *//*存放生成订单参数*//*
                        addCarOrderDto.setCarTaskId(carTask.getCarTaskId());
                        addCarOrderDto.setAppUserId(carTask.getUserId());
                        addCarOrderDto.setOrderNumber(orderNumber);
                        addCarOrderDto.setName(orderName);
                        addCarOrderDto.setMoney(averageMoney);
                        addCarOrderDto.setSingaporeAreaId(singaporeAreaId);
                        addCarOrderDto.setRallyPointId(rallyPointId);
                        addCarOrderDto.setCarId(carId);
                        addCarOrderDto.setCreateTime(fetureDate);


                        *//*根据当前时间和模板id查询今天是否有订单*//*
                        Integer countTimeOrder = generateCarordersMapper.countTimeOrder(fetureDate, carTask.getCarTaskId());
                        *//*判断当前时间如果有订单并且新建模板没有订单(同一个小车)*//*
                        if(carOrderUserCar!=null && countTimeOrder==0){
                            if(carOrderUserCar.getAppUserId() == null){
                                *//*删除之前生成多余订单*//*
                                Integer delCarOrder = generateCarordersMapper.delCarOrder(carOrderUserCar.getTaskOrderId());
                                *//*生成新订单*//*
                                Integer addCarOrderByTask = generateCarordersMapper.addCarOrderByTask(addCarOrderDto);
                            }
                        }else {
                            *//*如果当前时间没订单*//*
                            if(carOrderUserCar==null) {
                                *//*生成订单*//*
                                Integer addCarOrderByTask = generateCarordersMapper.addCarOrderByTask(addCarOrderDto);
                            }
                        }


                    }else{
                        *//*如果当前时间没订单*//*
                        if(carOrderUserCar==null){
                            addCarOrderDto.setOrderNumber(orderNumber);
                            addCarOrderDto.setName(orderName);
                            addCarOrderDto.setMoney(averageMoney);
                            addCarOrderDto.setSingaporeAreaId(singaporeAreaId);
                            addCarOrderDto.setRallyPointId(rallyPointId);
                            addCarOrderDto.setCarId(carId);
                            addCarOrderDto.setCreateTime(fetureDate);

                            Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                        }

                    }

                }

            }

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/

    @Override
    @Transactional
    public Integer generateCarorders() {
            System.out.println(StaticConfigUtil.generateOrderTimeLimitCar);
            for(int i = 0; i < StaticConfigUtil.generateOrderTimeLimitCar; i++){
                int timeNum = i;
                /*获取时间*/
                String fetureDate = Base64.getFetureDate(timeNum);
                /*查询当前小车所有集结点*/
                List<RallyPoint> rallyPointAll = carTaskMapper.selectRallyPointAll();
                for(RallyPoint rallyPoint : rallyPointAll){
                    /*小车集结点id*/
                    int rallyPointId = rallyPoint.getRallyPointId();

                    /*添加订单参数*/
                    AddCarOrderDto addCarOrderDto = new AddCarOrderDto();
                    /*区域id*/
                    int singaporeAreaId = rallyPoint.getSingaporeAreaId();
                    addCarOrderDto.setSingaporeAreaId(singaporeAreaId);
                    /*小车集结点id*/
                    addCarOrderDto.setRallyPointId(rallyPointId);
                    /*小车集结点名称*/
                    String rallyPointName = rallyPoint.getRallyPointName();
                    /*订单时间*/
                    addCarOrderDto.setCreateTime(fetureDate);
                    /*订单钱数selectAverageMoney(carId);*/
                    Double averageMoney = 100.00;
                    addCarOrderDto.setMoney(averageMoney);

                    /*查询当前集结点小车数*/
                    Integer countRallyPointCarNum = generateCarordersMapper.countRallyPointCarNum(rallyPointId);
                    /*查询当前集结点今天是否有订单*/
                    Integer selectRallyPointIsCarOrder = generateCarordersMapper.selectRallyPointIsCarOrder(rallyPointId,fetureDate);
                    /*查询当前集结点所有小车模板*/
                    List<CarTask> selectCarTaskAll = generateCarordersMapper.selectCarTaskAll(rallyPoint.getRallyPointId());
                    /*如果集结点里有小车*/
                    if(countRallyPointCarNum != 0){
                        /*如果今天当前集结点有订单 但是 应该生成的订单数大于已有的订单数（为了集结点新加入小车）（减小车后台做处理）*/
                        if(selectRallyPointIsCarOrder != 0 && countRallyPointCarNum > selectRallyPointIsCarOrder){

                            /*如果当前集结点有模板*/
                            if(selectCarTaskAll.size() != 0){
                                /*生成订单数大于模板数量（不会小于 添加模板有限制模板个数）*/
                                if(countRallyPointCarNum > selectCarTaskAll.size()){
                                    /*应该生成订单数-用模板生成的订单数=剩下要生成订单的数量*/
                                    int otherCarOrderNum = countRallyPointCarNum - selectCarTaskAll.size();
                                    /*先生成模板订单*/
                                    for(CarTask carTask : selectCarTaskAll){
                                        /*小车模板id*/
                                        int carTaskId = carTask.getCarTaskId();
                                        addCarOrderDto.setCarTaskId(carTaskId);
                                        /*用户id*/
                                        int userId = carTask.getUserId();
                                        addCarOrderDto.setAppUserId(userId);
                                        /*订单编号*/
                                        String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                        addCarOrderDto.setOrderNumber(orderNumber);
                                        /*区域名称*/
                                        String singaporeAreaName = carTask.getSingaporeAreaName();
                                        /*订单名称*/
                                        String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                        addCarOrderDto.setName(orderName);

                                        /*查询当前时间当前模板是否有订单(订单数)*/
                                        Integer nowCarOrderNum = generateCarordersMapper.countNowCarOrderNum(fetureDate, rallyPointId);

                                        if(nowCarOrderNum==0){ //今天当前模板没订单
                                            /*生成订单*/
                                            Integer addCarOrder = generateCarordersMapper.addCarOrderByTask(addCarOrderDto);
                                        }
                                    }
                                    /*查询当前时间当前集结点已有的未用模板生成的订单数量*/
                                    Integer selectRallyPointCarOrder = generateCarordersMapper.selectRallyPointCarOrder(rallyPointId);
                                    /*如果 已有的未用模板生成的订单数量 != 0*/
                                    if(selectRallyPointCarOrder != 0){
                                        /*应该生成的订单数 = 剩下要生成订单的数量 - 已有的未用模板生成的订单数量*/
                                        Integer carOrderNum = otherCarOrderNum - selectRallyPointCarOrder;

                                        /*生成剩下的订单*/
                                        for(int j = 0; j<carOrderNum; j++){
                                            /*订单编号*/
                                            String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                            addCarOrderDto.setOrderNumber(orderNumber);
                                            /*查询当前集结点所在区域的名称*/
                                            String singaporeAreaName = generateCarordersMapper.singaporeAreaName(singaporeAreaId);
                                            /*订单名称*/
                                            String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                            addCarOrderDto.setName(orderName);
                                            Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                                        }
                                    }else {
                                        /*生成剩下的订单*/
                                        for(int j = 0; j < otherCarOrderNum; j++){
                                            /*订单编号*/
                                            String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                            addCarOrderDto.setOrderNumber(orderNumber);
                                            /*查询当前集结点所在区域的名称*/
                                            String singaporeAreaName = generateCarordersMapper.singaporeAreaName(singaporeAreaId);
                                            /*订单名称*/
                                            String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                            addCarOrderDto.setName(orderName);
                                            Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                                        }
                                    }

                                }

                            }else {/*如果没有模板*/
                                for(int j = 0; j < countRallyPointCarNum; j++){
                                    /*订单编号*/
                                    String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                    addCarOrderDto.setOrderNumber(orderNumber);
                                    /*查询当前集结点所在区域的名称*/
                                    String singaporeAreaName = generateCarordersMapper.singaporeAreaName(singaporeAreaId);
                                    /*订单名称*/
                                    String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                    addCarOrderDto.setName(orderName);
                                    Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                                }
                            }
                        }else if (selectRallyPointIsCarOrder == 0){ /*今天没有订单*/
                            /*如果当前集结点有模板*/
                            if(selectCarTaskAll.size() != 0){

                                /*应该生成订单数-用模板生成的订单数=剩下要生成订单的数量*/
                                int otherCarOrderNum = countRallyPointCarNum - selectCarTaskAll.size();
                                /*先生成模板订单*/
                                for(CarTask carTask : selectCarTaskAll){
                                    /*小车模板id*/
                                    int carTaskId = carTask.getCarTaskId();
                                    addCarOrderDto.setCarTaskId(carTaskId);
                                    /*用户id*/
                                    int userId = carTask.getUserId();
                                    addCarOrderDto.setAppUserId(userId);
                                    /*订单编号*/
                                    String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                    addCarOrderDto.setOrderNumber(orderNumber);
                                    /*区域名称*/
                                    String singaporeAreaName = carTask.getSingaporeAreaName();
                                    /*订单名称*/
                                    String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                    addCarOrderDto.setName(orderName);


                                    /*生成订单*/
                                    Integer addCarOrder = generateCarordersMapper.addCarOrderByTask(addCarOrderDto);

                                }
                                /*生成剩下的订单*/
                                for(int j = 0; j < otherCarOrderNum; j++){
                                    /*订单编号*/
                                    String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                    addCarOrderDto.setOrderNumber(orderNumber);
                                    /*查询当前集结点所在区域的名称*/
                                    String singaporeAreaName = generateCarordersMapper.singaporeAreaName(singaporeAreaId);
                                    /*订单名称*/
                                    String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                    addCarOrderDto.setName(orderName);
                                    Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                                }

                            }else {/*如果没有模板*/
                                for(int j = 0; j < countRallyPointCarNum; j++){
                                    /*订单编号*/
                                    String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                    addCarOrderDto.setOrderNumber(orderNumber);
                                    /*查询当前集结点所在区域的名称*/
                                    String singaporeAreaName = generateCarordersMapper.singaporeAreaName(singaporeAreaId);
                                    /*订单名称*/
                                    String orderName = singaporeAreaName + rallyPointName + orderNumber;
                                    addCarOrderDto.setName(orderName);
                                    Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                                }
                            }
                        }
                    }

                }
            }

            return 1;
    }

    @Override
    public Double selectAverageMoney(int rallyPointId) {
        try{
            Double averageMoney = 0.00;
            for(int i = 0; i < 7; i++){
                String outTime = Base64.getPastDate(i+1);
                Double selectAverageMoney = generateCarordersMapper.selectAverageMoney(rallyPointId, outTime);
                if(selectAverageMoney != null){
                    averageMoney = averageMoney + selectAverageMoney;
                }
            }
            if(averageMoney == 0.00){
                return 100.00;
            }
            return averageMoney;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
