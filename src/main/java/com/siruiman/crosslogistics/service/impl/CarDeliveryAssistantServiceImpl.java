package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarDeliveryAssistantMapper;
import com.siruiman.crosslogistics.mapper.TruckDeliveryAssistantMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.ProblemPieceDto;
import com.siruiman.crosslogistics.model.dto.SigningDto;
import com.siruiman.crosslogistics.service.CarDeliveryAssistantService;
import com.siruiman.crosslogistics.util.ExchangeRateUtil;
import com.siruiman.crosslogistics.util.LockUtils;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CarDeliveryAssistantServiceImpl implements CarDeliveryAssistantService {
    @Autowired
    private CarDeliveryAssistantMapper carDeliveryAssistantMapper;
    @Autowired
    private TruckDeliveryAssistantMapper deliveryAssistantMapper;

    @Override
    public List<TaskOrder> selectTaskOrder(int appUserId) {
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            return carDeliveryAssistantMapper.selectTaskOrder(appUserId, createTime);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RallyPoint selectRallyPoint(int taskOrderId) {
        try{
            return carDeliveryAssistantMapper.selectRallyPoint(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String carUnlock(int rallyPointId, int taskOrderId, String lockNumber) {
        try{
            /*查询订单小车集结点*/
            Integer selectRallyPointId = carDeliveryAssistantMapper.selectRallyPointId(taskOrderId);
            if(rallyPointId == selectRallyPointId){
                LockUtils.unLock(lockNumber);
                Thread.sleep(3000);
                Hashtable lockState = StaticConfigUtil.lockState;
                String unlockStatus = (String) lockState.get(lockNumber);
                return "1";
            }
            return "订单集结点和小车集结点不匹配";

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReceivingPoint> selectZipCode(int taskOrderId) {
        try{
            List<ReceivingPoint> receivingPoints = new ArrayList<>();
            List<String> selectZipCode = carDeliveryAssistantMapper.selectZipCode(taskOrderId);
            for(int i = 0; i < selectZipCode.size(); i++){
                String zipCode = selectZipCode.get(i);
                ReceivingPoint receivingPoint = new ReceivingPoint();
                receivingPoint.setZipCode(zipCode);

                /*收货点经纬度*/
                ReceivingPoint selectLatLng = carDeliveryAssistantMapper.selectLatLng(zipCode);
                receivingPoint.setLat(selectLatLng.getLat());
                receivingPoint.setLng(selectLatLng.getLng());
                receivingPoint.setSaBuildingName(selectLatLng.getSaBuildingName());

                /*处理当前收货点是否送完货物*/
                int isComplete = 1;
                List<CargoInfo> selectCargoInfoByCarOrderAndZipCode = carDeliveryAssistantMapper.selectCargoInfoByCarOrderAndZipCode(taskOrderId, zipCode);
                for (CargoInfo cargoInfo : selectCargoInfoByCarOrderAndZipCode){
                    if(cargoInfo.getWarningState().equals("无异常") && cargoInfo.getIsReceiveGoods() == 2){
                        isComplete = 0;
                    }
                }
                receivingPoint.setIsComplete(isComplete);
                receivingPoints.add(receivingPoint);
            }
            return receivingPoints;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PointPackage> selectPointPackageByZipCode(int taskOrderId, int appUserId) {
        try{
            /*查询小车送货助手存储的点位邮编*/
            String zipCode = carDeliveryAssistantMapper.selectDeliverySsistantRecordZipCode(taskOrderId, appUserId);
            List<PointPackage> selectPointPackageByZipCode = carDeliveryAssistantMapper.selectPointPackageByZipCode(taskOrderId, zipCode);
            for(PointPackage pointPackage : selectPointPackageByZipCode){
                if(pointPackage.getIsArrivalPay() == 1){
                    pointPackage.setCOD(new BigDecimal(0.00));
                    pointPackage.setTotalCodGst(ExchangeRateUtil.rateExchange(pointPackage.getGST(),23));
                }else {
                    pointPackage.setTotalCodGst(ExchangeRateUtil.rateExchange(pointPackage.getGST(),23).add( ExchangeRateUtil.rateExchange(pointPackage.getCOD(),23)));
                }
                if(pointPackage.getDeliveryNumber().indexOf("-") != -1){
                    /*货物一共多少箱*/
                   String deliveryNumber = StringUtils.substringBefore(pointPackage.getDeliveryNumber(), "-");
                    PointPackage totalGoods = deliveryAssistantMapper.selectTotalGoods(deliveryNumber);
                    pointPackage.setTotalGoods(totalGoods.getTotalGoods());
                }
            }

            return selectPointPackageByZipCode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*@Override
    public Integer signing(SigningDto signingDto) {
        try{
            String[] goodsIds = signingDto.getGoodsIds().split(",");
            for(int i = 0; i < goodsIds.length; i++){
                int goodsId = Integer.parseInt(goodsIds[i]);
                if(signingDto.getIsPeopleReceive() == 1){
                    editGoodStatus(goodsId, signingDto.getAppUserId());
                }
                if(signingDto.getIsPeopleReceive() == 2){
                    signingDto.setGoodsId(goodsId);
                    editGoodStatusUnmanned(signingDto);
                }
            }

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/

    /*有人签收*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void editGoodStatus(int goodsId, SigningDto signingDto) {
        try{
            /*查询货物快递单号*/
            String selectDeliveryNumber = carDeliveryAssistantMapper.selectDeliveryNumber(goodsId);
            if (selectDeliveryNumber.indexOf("-")!=-1){
                //"只要test.indexOf('This')返回的值不是-1说明test字符串中包含字符串'This',相反如果包含返回的值必定是-1"
                System.out.println("存在包含关系，因为返回的值不等于-1");
                /*截取快递单号*/
                String deliveryNumber = StringUtils.substringBefore(selectDeliveryNumber, "-");
                /*修改签收状态*/
                Integer editTotalGoodsIsReceiveGoods = carDeliveryAssistantMapper.editTotalGoodsIsReceiveGoods(deliveryNumber);
            }

            String actualName = carDeliveryAssistantMapper.selectAppUserActualName(signingDto.getAppUserId());
            carDeliveryAssistantMapper.addGoodsPic(signingDto);
            Integer editGoodStatus = carDeliveryAssistantMapper.editGoodStatus(goodsId);
            Integer editGoodDetailStatus = carDeliveryAssistantMapper.editGoodDetailStatus(goodsId, actualName,signingDto.getUfId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*无人签收*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void editGoodStatusUnmanned(SigningDto signingDto) {
        try{
            String actualName = carDeliveryAssistantMapper.selectAppUserActualName(signingDto.getAppUserId());
            signingDto.setActualName(actualName);
            carDeliveryAssistantMapper.addGoodsPic(signingDto);
            carDeliveryAssistantMapper.editGoodStatus(signingDto.getGoodsId());
            carDeliveryAssistantMapper.editGoodsDetailsReceiptStatus(signingDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void problemSubmission(ProblemPieceDto problemPieceDto) {
        try{
            carDeliveryAssistantMapper.editGoodsStatusByOdd(problemPieceDto.getGoodsId());
            carDeliveryAssistantMapper.editGoodsDetailsStatusByOdd(problemPieceDto.getGoodsId(), problemPieceDto.getAbnormalText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer editCarOrderStatus(int taskOrderId, int appUserId) {
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            int state = 0;
            List<TaskOrder> selectTaskOrder = carDeliveryAssistantMapper.selectTaskOrder(appUserId, createTime);
            for(TaskOrder taskOrder : selectTaskOrder){
                if(taskOrder.getTaskOrderId() != taskOrderId){
                    if(taskOrder.getState() == 3){
                        state = taskOrder.getState();
                    }
                }
            }
            if(state == 3){
                return state;
            }
            return carDeliveryAssistantMapper.editCarOrderStatus(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectBagId(int taskOrderId) {
        try{
            return carDeliveryAssistantMapper.selectBagId(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editBagStatus(int taskOrderId) {
        try{
            /*查询当前订单下的所有包裹*/
            int state = 8;
            List<PointPackage> selectPointPackage = carDeliveryAssistantMapper.selectPointPackage(taskOrderId);
            for(PointPackage pointPackage : selectPointPackage){
                if(pointPackage.getStatus() == 2){
                    state = 9;
                }
            }
            Integer bagId = carDeliveryAssistantMapper.selectBagId(taskOrderId);
            Integer editBagStatus = carDeliveryAssistantMapper.editBagStatus(bagId, state);
            return editBagStatus;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editCarStatus(int taskOrderId) {
        try{
            /*查询当前订单下的所有包裹*/
            int state = 1;
            List<PointPackage> selectPointPackage = carDeliveryAssistantMapper.selectPointPackage(taskOrderId);
            for(int i = 0; i < selectPointPackage.size(); i++){

                PointPackage pointPackage = selectPointPackage.get(i);
                if(state != 2){
                    if(pointPackage.getIsReceiveGoods() == 2){
                        if(pointPackage.getStatus() == 2){
                            /*存在异常件*/
                            state = 4;
                        }else {
                            /*还有没收货也不是异常的货物*/
                            state = 2;
                        }
                    }
                }
            }
            Integer carId = carDeliveryAssistantMapper.selectCarIdByCarOrder(taskOrderId);
            if(state == 2){
                return state;
            }

            /*修改小车状态*/
            Integer editCarStatus = carDeliveryAssistantMapper.editCarStatus(taskOrderId, state, carId);
            /*修改小车订单状态*/
            Integer editCarOrderStatusByWC = carDeliveryAssistantMapper.editCarOrderStatusByWC(taskOrderId);
            return editCarOrderStatusByWC;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map selectCarOrderStep(int appUserId, int taskOrderId) {
        try{
            Map map = new HashMap();
            Integer carOrderStep = carDeliveryAssistantMapper.selectCarOrderStep(appUserId, taskOrderId);
            if(carOrderStep == null){
                map.put("carOrderStep", 0);
                return map;
            }
            /*导航至包裹收货点*/
            if(carOrderStep == 1){
                map.put("carOrderStep", carOrderStep);
                List<ReceivingPoint> selectZipCode = selectZipCode(taskOrderId);
                if(selectZipCode.size() == 0){
                    map.put("carOrderStepInfo", new ArrayList<>());
                }else {
                    map.put("carOrderStepInfo", selectZipCode);
                }
            }
            /*派送包裹*/
            if(carOrderStep == 2){
                map.put("carOrderStep", carOrderStep);
                /*查询小车送货助手存储的点位邮编*/
                /*String zipCode = carDeliveryAssistantMapper.selectZipCode(appUserId, taskOrderId);*/
                List<PointPackage> selectPointPackageByZipCode = selectPointPackageByZipCode(taskOrderId, appUserId);
                if(selectPointPackageByZipCode.size() == 0){
                    map.put("carOrderStepInfo", new ArrayList<>());
                }else {
                    map.put("carOrderStepInfo", selectPointPackageByZipCode);
                }
            }
            /*归还小车*/
            if(carOrderStep == 3){
                map.put("carOrderStep", carOrderStep);
                RallyPoint selectRallyPoint = selectRallyPoint(taskOrderId);
                map.put("carOrderStepInfo", selectRallyPoint);
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TruckOrderMoney selectTruckOrderMoney(int taskOrderId) {
        try{
            return carDeliveryAssistantMapper.selectTruckOrderMoney(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer storageZipCode(int taskOrderId, int appUserId, String zipCode) {
        try{
            return carDeliveryAssistantMapper.storageZipCode(taskOrderId, appUserId, zipCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectCarIdByLockNumber(String lockNumber) {
        try{
            return carDeliveryAssistantMapper.selectCarIdByLockNumber(lockNumber);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countErrorGoods(int taskOrderId) {
        try{
            return carDeliveryAssistantMapper.countErrorGoods(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
