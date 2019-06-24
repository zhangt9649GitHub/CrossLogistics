package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.ProblemPieceDto;
import com.siruiman.crosslogistics.model.dto.SigningDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.MapUtils;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value="CarDeliveryAssistant",description = "小车",tags={"APP送货助手(小车)-小车"})
@RestController
@RequestMapping("/carDeliveryAssistant")
public class CarDeliveryAssistantController {
    @Autowired
    private CarDeliveryAssistantService deliveryAssistantService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private GoodsWarningService goodsWarningService;
    @Autowired
    private AppUserWalletService appUserWalletService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private TruckDeliveryAssistantService truckDeliveryAssistantService;
    @Autowired
    private AppUserService appUserService;

    @ApiOperation(value = "获取小车订单列表",notes = "selectTaskOrder",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectTaskOrder",method = RequestMethod.GET)
    public CommonResponse selectTaskOrder(int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<TaskOrder> selectTaskOrder = deliveryAssistantService.selectTaskOrder(appUserId);
            if(selectTaskOrder.size() == 0){
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setData(new ArrayList<>());
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            }
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTaskOrder);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改小车订单状态为进行中",notes = "editCarOrderStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editCarOrderStatus",method = RequestMethod.POST)
    public CommonResponse editCarOrderStatus(int taskOrderId, int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            //查询是否填写了联系地址
            AppUser appUser =appUserService.selectAppUserDetail(appUserId);
            if(appUser==null||appUser.getZipCode()==null||appUser.getZipCode().equals("")){
                commonResponse.setCode(HtCode.FAIL_NULL_DATA.getCode());
                commonResponse.setMessage(HtCode.FAIL_NULL_DATA.getInfo());
                return commonResponse;
            }
            Integer bagId = deliveryAssistantService.selectBagId(taskOrderId);
            if(bagId == null){
                commonResponse.setCode(GyCode.FAIL_CAR_ORDER_NOT_BAG.getCode());
                commonResponse.setMessage(GyCode.FAIL_CAR_ORDER_NOT_BAG.getInfo());
                return commonResponse;
            }else {
                logisticInfoService.insertNormalLogisticInfo(8, 0, appUserId, 0, bagId);
            }

            Integer editCarOrderStatus=deliveryAssistantService.editCarOrderStatus(taskOrderId, appUserId);
            if(editCarOrderStatus==3){
                commonResponse.setCode(GyCode.NOW_TRUCK_ODER_START.getCode());
                commonResponse.setMessage(GyCode.NOW_TRUCK_ODER_START.getInfo());
                return commonResponse;
            }
            if(editCarOrderStatus < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
            }else {
                commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询小车集散地",notes = "selectRallyPoint",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectRallyPoint",method = RequestMethod.GET)
    public CommonResponse selectRallyPoint(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            RallyPoint selectRallyPoint = deliveryAssistantService.selectRallyPoint(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectRallyPoint);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "打开小车车锁(打开小车车厢锁)",notes = "carUnlock",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="lockNumber", value="车锁编号",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/carUnlock",method = RequestMethod.POST)
    public CommonResponse carUnlock(int taskOrderId, String lockNumber) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            /*查询小车集结点id*/
            RallyPoint selectRallyPoint = deliveryAssistantService.selectRallyPoint(taskOrderId);
            String carUnlock = deliveryAssistantService.carUnlock(selectRallyPoint.getRallyPointId(), taskOrderId, lockNumber);
            if(carUnlock.equals("2")){
                commonResponse.setCode(GyCode.FAIL_UNLOCK.getCode());
                commonResponse.setMessage(GyCode.FAIL_UNLOCK.getInfo());
            }else if(carUnlock.equals("1")){
                Integer carId = deliveryAssistantService.selectCarIdByLockNumber(lockNumber);
                Integer insertMessage = messageService.insertMessage(2, 0, carId, 0, 0, 0, 0);
                commonResponse.setCode(GyCode.SUCCESS_UNLOCK.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_UNLOCK.getInfo());
            }else {
                commonResponse.setCode(GyCode.FAIL_UNLOCK.getCode());
                commonResponse.setMessage(GyCode.FAIL_UNLOCK.getInfo());
                commonResponse.setData(carUnlock);
            }

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_UNLOCK.getCode());
            commonResponse.setMessage(GyCode.FAIL_UNLOCK.getInfo());
            return commonResponse;
        }
        return commonResponse;
    }

    @ApiOperation(value = "查询当前订单需要送几个收货点",notes = "selectZipCode",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectZipCode",method = RequestMethod.GET)
    public CommonResponse selectZipCode(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<ReceivingPoint> selectZipCode = deliveryAssistantService.selectZipCode(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectZipCode);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "存储收货点邮编",notes = "storageZipCode",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="zipCode", value="点位邮编",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/storageZipCode",method = RequestMethod.POST)
    public CommonResponse storageZipCode(int taskOrderId, int appUserId, String zipCode) {
        CommonResponse commonResponse = new CommonResponse();
        try{

            Integer storageZipCode = deliveryAssistantService.storageZipCode(taskOrderId, appUserId, zipCode);
            if(storageZipCode < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前点位下的所有包裹",notes = "selectPointPackageByZipCode",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectPointPackageByZipCode",method = RequestMethod.GET)
    public CommonResponse selectPointPackageByZipCode(int taskOrderId, int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<PointPackage> selectPointPackageByZipCode = deliveryAssistantService.selectPointPackageByZipCode(taskOrderId, appUserId);
            if(selectPointPackageByZipCode.size() == 0){
                List<PointPackage> pointPackageByZipCode = new ArrayList<>();
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
                commonResponse.setData(pointPackageByZipCode);
            }else {
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
                commonResponse.setData(selectPointPackageByZipCode);
            }

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "签收",notes = "signing",tags={"@郭阳"})
    @RequestMapping(value = "/signing",method = RequestMethod.POST)
    public CommonResponse signing(SigningDto signingDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{

            String[] goodsIds = signingDto.getGoodsIds().split(",");
            for(int i = 0; i < goodsIds.length; i++){
                int goodsId = Integer.parseInt(goodsIds[i]);
                if(signingDto.getCarOrTruck() == 2){
                    /*货车送货*/
                    Integer selectBagIdByGoodsId = truckDeliveryAssistantService.selectBagIdByGoodsId(goodsId);
                    logisticInfoService.insertNormalLogisticInfo(13, 0, signingDto.getAppUserId(), goodsId, selectBagIdByGoodsId);
                }else {
                    /*小车送货*/
                    Integer bagId = deliveryAssistantService.selectBagId(signingDto.getTaskOrderId());
                    logisticInfoService.insertNormalLogisticInfo(9, 0, signingDto.getAppUserId(), goodsId, bagId);
                }

                Integer insertMessage = messageService.insertMessage(3, 0, 0, goodsId, 0, 0, 0);

                if(signingDto.getIsPeopleReceive() == 1){
                    deliveryAssistantService.editGoodStatus(goodsId, signingDto);
                }
                if(signingDto.getIsPeopleReceive() == 2){
                    signingDto.setGoodsId(goodsId);
                    deliveryAssistantService.editGoodStatusUnmanned(signingDto);
                }
            }

            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "问题件提交",notes = "problemSubmission",tags={"@郭阳"})
    @RequestMapping(value = "/problemSubmission",method = RequestMethod.POST)
    public CommonResponse problemSubmission(ProblemPieceDto problemPieceDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            logisticInfoService.insertAbnormalLogisticInfo(2, problemPieceDto.getAbnormalText(), problemPieceDto.getGoodsId(), 0, problemPieceDto.getAppUserId());

            goodsWarningService.insertGoodsWarningByAppUser(problemPieceDto.getGoodsId(), problemPieceDto.getAbnormalText());
            deliveryAssistantService.problemSubmission(problemPieceDto);
            Integer insertAbnormalMessage = messageService.insertAbnormalMessage(1, 0, problemPieceDto.getAbnormalText(), problemPieceDto.getGoodsId());
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改货袋完成状态",notes = "editBagStatus",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/editBagStatus",method = RequestMethod.POST)
    public CommonResponse editBagStatus(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editBagStatus = deliveryAssistantService.editBagStatus(taskOrderId);
            if(editBagStatus < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改小车状态",notes = "editCarStatus",tags={"@郭阳"})
    @ApiImplicitParams({
        @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
        @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
        @ApiImplicitParam(name="lat", value="当前定位纬度",paramType="query",dataType="double"),
        @ApiImplicitParam(name="lng", value="当前定位经度",paramType="query",dataType="double"),
    })

    @RequestMapping(value = "/editCarStatus",method = RequestMethod.POST)
    public CommonResponse editCarStatus(int appUserId, int taskOrderId, double lat, double lng) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            /*RallyPoint selectRallyPoint = deliveryAssistantService.selectRallyPoint(taskOrderId);
            double rallyPointLat = Double.valueOf(selectRallyPoint.getRallyPointLat());
            double rallyPointLng = Double.valueOf(selectRallyPoint.getRallyPointLng());
            double distance = MapUtils.getDistance(lat, lng, rallyPointLat, rallyPointLng);
            if(distance > 100){
                commonResponse.setCode(GyCode.FAIL_DISTANCE.getCode());
                commonResponse.setMessage(GyCode.FAIL_DISTANCE.getInfo());
                return commonResponse;
            }*/


            Integer editCarStatus = deliveryAssistantService.editCarStatus(taskOrderId);
            if(editCarStatus == 2){
                commonResponse.setCode(GyCode.FAIL_CAR_ORDER.getCode());
                commonResponse.setMessage(GyCode.FAIL_CAR_ORDER.getInfo());
                return commonResponse;
            }
            if(editCarStatus < 1){
                commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
                commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
                return commonResponse;
            }
            TruckOrderMoney selectTruckOrderMoney = deliveryAssistantService.selectTruckOrderMoney(taskOrderId);
            //*查询当前小车订单异常件数量*//*
            Integer countErrorGoods = deliveryAssistantService.countErrorGoods(taskOrderId);
            //*算出异常件需要扣除的钱*//*
            double errorGoodsMoney = countErrorGoods * StaticConfigUtil.waringGoodsPrice;
            double money = selectTruckOrderMoney.getTotalMoney().doubleValue();
            double totalMoney = money - errorGoodsMoney;
            Integer updateCommissionAndIntegral = appUserWalletService.updateCommissionAndIntegral(appUserId, new BigDecimal(totalMoney), selectTruckOrderMoney.getTotalIntegral(), "小车", 1 , taskOrderId,0);
            commonResponse.setCode(GyCode.SUCCESS_EDIT.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_EDIT.getInfo());

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_EDIT.getCode());
            commonResponse.setMessage(GyCode.FAIL_EDIT.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询小车订单进行步骤",notes = "selectCarOrderStep",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="小车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectCarOrderStep",method = RequestMethod.GET)
    public CommonResponse selectCarOrderStep(int appUserId, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Map selectCarOrderStep = deliveryAssistantService.selectCarOrderStep(appUserId, taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectCarOrderStep);

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }
}
