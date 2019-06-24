package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.GenerateTruckOrdersMapper;
import com.siruiman.crosslogistics.model.GenerateTruckOrder;
import com.siruiman.crosslogistics.model.GenerateTruckOrderRoute;
import com.siruiman.crosslogistics.model.dto.AddTruckOrderDto;
import com.siruiman.crosslogistics.model.dto.AddTruckOrderRouteDto;
import com.siruiman.crosslogistics.service.GenerateTruckOrdersService;
import com.siruiman.crosslogistics.util.Base64;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenerateTruckOrdersServiceImpl implements GenerateTruckOrdersService {
    @Autowired
    private GenerateTruckOrdersMapper generateTruckOrdersMapper;

    @Override
    @Transactional
    public Integer generateTruckOrders() {
            /*查询货车所有模板*/
            List<GenerateTruckOrder> selectGenerateTruckOrderAll = generateTruckOrdersMapper.selectGenerateTruckOrderAll();
            for(GenerateTruckOrder generateTruckOrder : selectGenerateTruckOrderAll){
                for(int i = 0; i < StaticConfigUtil.generateOrderTimeLimitTruck; i++){
                    int timeNum = i;
                    /*获取几天后的时间*/
                    String fetureDate = Base64.getFetureDate(timeNum);
                    /*查询当前时间是否有订单存在*/
                    Integer countTimeTruckOrder = generateTruckOrdersMapper.countTimeTruckOrder(fetureDate, generateTruckOrder.getTruckTaskId());
                    if(countTimeTruckOrder == 0){

                        /*处理添加货车订单数据*/
                        AddTruckOrderDto addTruckOrderDto = new AddTruckOrderDto();
                        addTruckOrderDto.setTruckTaskId(generateTruckOrder.getTruckTaskId());


                        addTruckOrderDto.setCreateTime(fetureDate);
                        String driverId = generateTruckOrder.getTruckDriverId();

                        /*算出过去当前区域七天内的平均钱数*/
                        Double averageMoney = selectAverageMoney(generateTruckOrder.getSingaporeAreaId());
                        addTruckOrderDto.setMoney(averageMoney);

                        addTruckOrderDto.setSingaporeAreaId(generateTruckOrder.getSingaporeAreaId());
                        /*模板路线*/
                        List<GenerateTruckOrderRoute> generateTruckOrderRoutes = generateTruckOrder.getGenerateTruckOrderRoutes();
                        /*判断模板是否绑定了司机*/
                        /*已绑定*/
                        if(driverId != null && driverId != ""){
                            String[] truckDriverIds = driverId.split(",");
                            addTruckOrderDto.setState(2);
                            /*处理货车司机*/
                            for(int y = 0; y < truckDriverIds.length; y++){
                                addTruckOrderDto.setAppUserId(Integer.parseInt(truckDriverIds[y]));
                                String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                                addTruckOrderDto.setOrderNumber(orderNumber);

                                String orderName = generateTruckOrder.getSingaporeAreaName() + orderNumber;
                                addTruckOrderDto.setName(orderName);

                                /*添加订单*/
                                Integer addTruckOrder = generateTruckOrdersMapper.addTruckOrderByDriver(addTruckOrderDto);

                                for(GenerateTruckOrderRoute generateTruckOrderRoute : generateTruckOrderRoutes){
                                    /*处理货车订单路线数据*/
                                    AddTruckOrderRouteDto addTruckOrderRouteDto = new AddTruckOrderRouteDto();
                                    addTruckOrderRouteDto.setTaskOrderId(addTruckOrderDto.getTaskOrderId());
                                    addTruckOrderRouteDto.setRouteId(generateTruckOrderRoute.getRouteId());
                                    addTruckOrderRouteDto.setStatus(generateTruckOrderRoute.getStatus());
                                    addTruckOrderRouteDto.setSequence(generateTruckOrderRoute.getSort());

                                    Integer addTruckOrderRoute = generateTruckOrdersMapper.addTruckOrderRoute(addTruckOrderRouteDto);
                                }
                            }
                        }else {
                            addTruckOrderDto.setState(1);
                            String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                            addTruckOrderDto.setOrderNumber(orderNumber);

                            String orderName = generateTruckOrder.getSingaporeAreaName() + orderNumber;
                            addTruckOrderDto.setName(orderName);
                            Integer addTruckOrder = generateTruckOrdersMapper.addTruckOrder(addTruckOrderDto);
                            for(GenerateTruckOrderRoute generateTruckOrderRoute : generateTruckOrderRoutes){
                                /*处理货车订单路线数据*/
                                AddTruckOrderRouteDto addTruckOrderRouteDto = new AddTruckOrderRouteDto();
                                addTruckOrderRouteDto.setTaskOrderId(addTruckOrderDto.getTaskOrderId());
                                addTruckOrderRouteDto.setRouteId(generateTruckOrderRoute.getRouteId());
                                addTruckOrderRouteDto.setStatus(generateTruckOrderRoute.getStatus());
                                addTruckOrderRouteDto.setSequence(generateTruckOrderRoute.getSort());

                                Integer addTruckOrderRoute = generateTruckOrdersMapper.addTruckOrderRoute(addTruckOrderRouteDto);
                            }
                        }



                    }
                }
            }
            return 1;
    }

    @Override
    public Double selectAverageMoney(int singaporeAreaId) {
        try{
            Double averageMoney = 0.00;
            for(int i = 0; i < 7; i++){
                String outTime = Base64.getPastDate(i+1);
                Double selectAverageMoney = generateTruckOrdersMapper.selectAverageMoney(singaporeAreaId, outTime);
                if(selectAverageMoney != null){
                    averageMoney = averageMoney + selectAverageMoney;
                }
            }
            if(averageMoney == 0.00){
                return 100.00;
            }
            return averageMoney/7;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
