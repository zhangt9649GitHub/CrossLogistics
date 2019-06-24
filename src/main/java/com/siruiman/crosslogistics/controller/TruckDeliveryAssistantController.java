package com.siruiman.crosslogistics.controller;

import com.siruiman.crosslogistics.enums.GyCode;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.ProblemPieceDto;
import com.siruiman.crosslogistics.model.dto.SigningDto;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value="TruckDeliveryAssistant",description = "货车",tags={"APP送货助手(货车)-货车"})
@RestController
@RequestMapping("/truckDeliveryAssistant")
public class TruckDeliveryAssistantController {
    @Autowired
    private TruckDeliveryAssistantService deliveryAssistantService;
    @Autowired
    private LogisticInfoService logisticInfoService;
    @Autowired
    private AppUserWalletService appUserWalletService;
    @Autowired
    private CarDeliveryAssistantService carDeliveryAssistantService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private GoodsWarningService goodsWarningService;
    @Autowired
    private  GoodsDetailsService goodsDetailsService;
    @Autowired
    private  AppUserService appUserService;
    @Autowired
    private  BagSerivce bagSerivce;
    @Autowired
    private  AsyncService asyncService;


    @ApiOperation(value = "获取货车订单列表",notes = "appTruckOrder",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/appTruckOrder",method = RequestMethod.GET)
    public CommonResponse selectAppTruckOrder(int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<AppTruckOrder> selectAppTruckOrder = deliveryAssistantService.selectAppTruckOrder(appUserId);
            if(selectAppTruckOrder.size() == 0){
                List<AppTruckOrder> appTruckOrder = new ArrayList<>();
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setData(appTruckOrder);
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            }
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectAppTruckOrder);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "修改货车订单状态",notes = "editTruckOrderStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="orderType", value="订单类型 （1正常 2货车送货）",paramType="query",dataType="int")
    })
    @RequestMapping(value = "/editTruckOrderStatus",method = RequestMethod.POST)
    public CommonResponse editTruckOrderStatus(int taskOrderId, int appUserId, @Validated @RequestParam(defaultValue = "1") int orderType) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            //查询是否填写了联系地址
                AppUser appUser =appUserService.selectAppUserDetail(appUserId);
                if(appUser==null||appUser.getZipCode()==null||appUser.getZipCode().equals("")){
                    commonResponse.setCode(HtCode.FAIL_NULL_DATA.getCode());
                    return commonResponse;
                }
            Integer truckOrderStatus = deliveryAssistantService.editTruckOrderStatus(taskOrderId, appUserId, orderType);
            if(truckOrderStatus == 6){
                commonResponse.setCode(GyCode.ORDER_START_SELECTION_MODE.getCode());
                commonResponse.setMessage(GyCode.ORDER_START_SELECTION_MODE.getInfo());
                return commonResponse;
            }
            if(truckOrderStatus == 5){
                commonResponse.setCode(GyCode.SUCCESS_CAR_ORDER.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_CAR_ORDER.getInfo());
                return commonResponse;
            }
            if(truckOrderStatus == 3){
                commonResponse.setCode(GyCode.NOW_TRUCK_ODER_START.getCode());
                commonResponse.setMessage(GyCode.NOW_TRUCK_ODER_START.getInfo());
                return commonResponse;
            }
            if(truckOrderStatus < 1){
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

   @ApiOperation(value = "确认完成扫描",notes = "确认完成扫描",tags={"@郝腾"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/confirmCompletionOfScanning",method = RequestMethod.POST)
    public CommonResponse confirmCompletionOfScanning(int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<Bag> bagList =bagSerivce.selectBagListByUserId(appUserId);
            if(bagList.size()==0){
                //调用service层的任务
               // asyncService.executeAsync(appUserId);
                commonResponse.setCode(HtCode.SUCCESS_BAG_SCANNING.getCode());
                commonResponse.setMessage(HtCode.SUCCESS_BAG_SCANNING.getInfo());
            }else if (bagList.size()>0){
                commonResponse.setCode(HtCode.FAIL_BAG_SCANING.getCode());
                commonResponse.setMessage(HtCode.FAIL_BAG_SCANING.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(HtCode.FAIL_OK_SCANING.getCode());
            commonResponse.setMessage(HtCode.FAIL_OK_SCANING.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前订单仓库的经纬度",notes = "warehouseLatLng",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/warehouseLatLng",method = RequestMethod.GET)
    public CommonResponse selectWarehouseLatLng(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Warehouse selectWarehouseLatLng = deliveryAssistantService.selectWarehouseLatLng(taskOrderId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectWarehouseLatLng);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询送货司机编号(二维码)",notes = "truckNumber",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/truckNumber",method = RequestMethod.GET)
    public CommonResponse selectTruckNumber(int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            String selectTruckNumber = deliveryAssistantService.selectTruckNumber(appUserId);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setData(selectTruckNumber);
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "插入步骤(向仓库管理员出示完二维码调用)",notes = "addDeliverySsistantRecord",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="orderType", value="1货车 2小车",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/addDeliverySsistantRecord",method = RequestMethod.POST)
    public CommonResponse addDeliverySsistantRecord(int appUserId, int taskOrderId, int orderType) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer addDeliverySsistantRecord = deliveryAssistantService.addDeliverySsistantRecord(appUserId, taskOrderId, orderType);
            if(addDeliverySsistantRecord < 1){
                commonResponse.setCode(GyCode.FAIL_ADD.getCode());
                commonResponse.setData(addDeliverySsistantRecord);
                commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
            }
            commonResponse.setCode(GyCode.SUCCESS_ADD.getCode());
            commonResponse.setData(addDeliverySsistantRecord);
            commonResponse.setMessage(GyCode.SUCCESS_ADD.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_ADD.getCode());
            commonResponse.setMessage(GyCode.FAIL_ADD.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "绑定货袋和货车",notes = "truckOrderBindingBag",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="bagNumber", value="货袋编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="truckLogType", value="正常1/货车跑货2",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/truckOrderBindingBag",method = RequestMethod.POST)
    public CommonResponse selectTruckOrderBindingBag(String bagNumber, int taskOrderId, int appUserId, @Validated @RequestParam(defaultValue = "1") int truckLogType) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            if(truckLogType == 2){
                Integer bagId = deliveryAssistantService.selectBagId(bagNumber);
                logisticInfoService.insertNormalLogisticInfo(12,0, appUserId, 0, bagId);
            }

            Integer truckOrderBindingBag = deliveryAssistantService.truckOrderBindingBag(bagNumber, taskOrderId);
            if(truckOrderBindingBag == 2){
                commonResponse.setCode(GyCode.FAIL_BINDING_BAG.getCode());
                commonResponse.setMessage(GyCode.FAIL_BINDING_BAG.getInfo());
                return commonResponse;
            }
            if(truckOrderBindingBag == null){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            if(truckOrderBindingBag < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
            return commonResponse;
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
            return commonResponse;
        }


    }

    @ApiOperation(value = "修改步骤",notes = "editStep",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="orderType", value="1货车 2小车",paramType="query",dataType="int"),
            @ApiImplicitParam(name="step", value="步骤 1(货车：扫描货袋，小车：导航至包裹收货点) 2(货车：导航至小车集散点，小车：派送包裹) 3(货车:问题件， 小车：归还小车） 4（货车：绑定货袋和小车）5（货车：送回异常件）",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editStep",method = RequestMethod.POST)
    public CommonResponse editStep(int appUserId, int taskOrderId, int orderType, int step) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editStep = deliveryAssistantService.editStep(appUserId, taskOrderId, orderType, step);

            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setData(editStep);
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询货车订单送货路线",notes = "truckOrderRoute",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/truckOrderRoute",method = RequestMethod.GET)
    public LayuiCommonResponse selectTruckOrderRoute(int taskOrderId) {
        LayuiCommonResponse layuiCommonResponse = new LayuiCommonResponse();
        try{
            List<TruckTaskRoute> selectTruckOrderRoute = deliveryAssistantService.selectTruckOrderRoute(taskOrderId);
            Integer countTruckOrderRoute = deliveryAssistantService.countTruckOrderRoute(taskOrderId);

            layuiCommonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            layuiCommonResponse.setData(selectTruckOrderRoute);
            layuiCommonResponse.setCount(countTruckOrderRoute);
            layuiCommonResponse.setMsg(GyCode.SUCCESS_GET.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            layuiCommonResponse.setCode(GyCode.FAIL_GET.getCode());
            layuiCommonResponse.setMsg(GyCode.FAIL_GET.getInfo());
        }

        return layuiCommonResponse;
    }

    @ApiOperation(value = "扫开小车车厢",notes = "carUnlock",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="lockNumber", value="车厢锁编号",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/carUnlock",method = RequestMethod.POST)
    public CommonResponse carUnlock(int appUserId, int taskOrderId, String lockNumber) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            String carUnlock = deliveryAssistantService.carUnlock(appUserId, taskOrderId, lockNumber);
            if(carUnlock.equals("2")){
                commonResponse.setCode(GyCode.CAR_NOT_IDLE.getCode());
                commonResponse.setMessage(GyCode.CAR_NOT_IDLE.getInfo());
            }
            if(carUnlock.equals("3")){
                commonResponse.setCode(GyCode.CAR_MAINTAIN.getCode());
                commonResponse.setMessage(GyCode.CAR_MAINTAIN.getInfo());
            }
            if(carUnlock.equals("5")){
                commonResponse.setCode(GyCode.CAR_SCRAP.getCode());
                commonResponse.setMessage(GyCode.CAR_SCRAP.getInfo());
            }
            if(carUnlock.equals("6")){
                commonResponse.setCode(GyCode.FAIL_UNLOCK.getCode());
                commonResponse.setMessage(GyCode.FAIL_UNLOCK.getInfo());
            }
            if(carUnlock.equals("1")){
                commonResponse.setCode(GyCode.CAR_IDLE.getCode());
                commonResponse.setMessage(GyCode.CAR_IDLE.getInfo());
            }
            if(carUnlock.equals("4")){
                commonResponse.setCode(GyCode.CAR_IDLE_ERROR_GOODS.getCode());
                commonResponse.setMessage(GyCode.CAR_IDLE_ERROR_GOODS.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_UNLOCK.getCode());
            commonResponse.setMessage(GyCode.FAIL_UNLOCK.getInfo());
        }

        return commonResponse;
    }



    @ApiOperation(value = "绑定问题件和货车司机",notes = "bindingAppUserAndVeryGoods",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="deliveryNumber", value="问题件编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="lockNumber", value="小车车厢锁编号",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/bindingAppUserAndVeryGoods",method = RequestMethod.POST)
    public CommonResponse bindingAppUserAndVeryGoods(String deliveryNumber, int appUserId, String lockNumber) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer bindingAppUserAndVeryGoods = deliveryAssistantService.bindingAppUserAndVeryGoods(deliveryNumber, appUserId, lockNumber);
            if(bindingAppUserAndVeryGoods < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
            }else {
                commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
                commonResponse.setData(bindingAppUserAndVeryGoods);
                commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "确认问题件是否全部装上货车",notes = "确认问题件是否全部装上货车",tags={"@郝腾"})
    @ApiImplicitParam(name="lockNumber", value="小车车厢锁编号",paramType="query",dataType="String")
    @RequestMapping(value = "/loadingGoodsWarning",method = RequestMethod.POST)
    public CommonResponse loadingGoodsWarning(String lockNumber) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer truckloadingGoodsWarning = deliveryAssistantService.loadingGoodsWarning(lockNumber);
            //等于1 说明全部装车 等于2 说明还有问题件
            if(truckloadingGoodsWarning==1){
                commonResponse.setCode(HtCode.SUCCESS_LOADING.getCode());
                commonResponse.setMessage(HtCode.SUCCESS_LOADING.getInfo());
            }else if(truckloadingGoodsWarning==2){
                commonResponse.setCode(HtCode.SUCCESS_SOME_LOADING.getCode());
                commonResponse.setMessage(HtCode.SUCCESS_SOME_LOADING.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "绑定小车和货袋",notes = "bindingCarBag",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="bagNumber", value="货袋编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="lockNumber", value="小车车锁编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="appUserId", value="用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="todrId", value="货车送货路线主键id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="rallyPointId", value="集结点id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/bindingCarBag",method = RequestMethod.POST)
    public CommonResponse bindingCarBag(String bagNumber, String lockNumber, int appUserId, int taskOrderId, int todrId, int rallyPointId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer bagId = deliveryAssistantService.selectBagId(bagNumber);
            logisticInfoService.insertNormalLogisticInfo(7,0, appUserId, 0, bagId);

            Integer bindingCarBag = deliveryAssistantService.bindingCarBag(bagNumber, lockNumber, appUserId, taskOrderId, todrId, rallyPointId);
            if(bindingCarBag == 2){
                commonResponse.setCode(GyCode.FAIL_BAG_NO_RALLY_POINT.getCode());
                commonResponse.setMessage(GyCode.FAIL_BAG_NO_RALLY_POINT.getInfo());
                return commonResponse;
            }
            if(bindingCarBag == 3){
                commonResponse.setCode(GyCode.CAR_BOX_CLOSE.getCode());
                commonResponse.setMessage(GyCode.CAR_BOX_CLOSE.getInfo());
                return commonResponse;
            }
            if(bindingCarBag == 4){
                commonResponse.setCode(GyCode.CAR_BINDING_BAG.getCode());
                commonResponse.setMessage(GyCode.CAR_BINDING_BAG.getInfo());
                return commonResponse;
            }
            if(bindingCarBag < 1){
                commonResponse.setCode(GyCode.FAIL_BINDING.getCode());
                commonResponse.setMessage(GyCode.FAIL_BINDING.getInfo());
            }else {

                commonResponse.setCode(GyCode.SUCCESS_BINDING.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_BINDING.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_BINDING.getCode());
            commonResponse.setMessage(GyCode.FAIL_BINDING.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "当前集结点已完成",notes = "editTruckOrderRouteStatus",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="todrId", value="订单关联货袋主键id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/editTruckOrderRouteStatus",method = RequestMethod.POST)
    public CommonResponse editTruckOrderRouteStatus(int todrId, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editTruckOrderRouteStatus = deliveryAssistantService.editTruckOrderRouteStatus(todrId);
            if(editTruckOrderRouteStatus < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
            }else {
                List<TruckTaskRoute> selectTruckOrderRoute = deliveryAssistantService.selectTruckOrderRoute(taskOrderId);
                commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
                commonResponse.setData(selectTruckOrderRoute);
                commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "关闭小车车锁",notes = "carCloseLock",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="lockNumber", value="小车车锁编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/carCloseLock",method = RequestMethod.POST)
    public CommonResponse carCloseLock(String lockNumber, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer carCloseLock = deliveryAssistantService.carCloseLock(lockNumber, taskOrderId);
            if(carCloseLock == 2){
                commonResponse.setCode(GyCode.SUCCESS_COMPLETED.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_COMPLETED.getInfo());
            }
            if(carCloseLock == 3){
                commonResponse.setCode(GyCode.FAIL_COMPLETED.getCode());
                commonResponse.setMessage(GyCode.FAIL_COMPLETED.getInfo());
            }
            if(carCloseLock == 1){
                commonResponse.setCode(GyCode.FAIL_LOCK.getCode());
                commonResponse.setMessage(GyCode.FAIL_LOCK.getInfo());
            }else {
                commonResponse.setCode(GyCode.SUCCESS_LOCK.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_LOCK.getInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询中途退出后到达第几步(进入订单都需要查)",notes = "selectStep",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectStep",method = RequestMethod.GET)
    public CommonResponse selectStep(int appUserId, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Map selectStep = deliveryAssistantService.selectStep(appUserId, taskOrderId);
            int step = (Integer) selectStep.get("step");
            if(step == 0){
                commonResponse.setCode(GyCode.ORDER_NOT_STARTED.getCode());
                commonResponse.setMessage(GyCode.ORDER_NOT_STARTED.getInfo());
            }
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectStep);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "货车订单完成",notes = "truckOrderComplete",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/truckOrderComplete",method = RequestMethod.POST)
    public CommonResponse truckOrderComplete(int appUserId, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer truckOrderComplete = deliveryAssistantService.truckOrderComplete(taskOrderId, appUserId);
            if(truckOrderComplete == null){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            if(truckOrderComplete == 3){
                commonResponse.setCode(GyCode.VERY_GOODS_IS_SOLVE.getCode());
                commonResponse.setMessage(GyCode.VERY_GOODS_IS_SOLVE.getInfo());
                return commonResponse;
            }
            if(truckOrderComplete < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
                return commonResponse;
            }
            TruckOrderMoney selectTruckOrderMoney = deliveryAssistantService.selectTruckOrderMoney(taskOrderId);
            appUserWalletService.updateCommissionAndIntegral(appUserId, selectTruckOrderMoney.getTotalMoney(), selectTruckOrderMoney.getTotalIntegral(), "货车", 3, taskOrderId,0);
            /*Integer insertMessage = messageService.insertMessage(12, appUserId, 0, 0, 0, 0, taskOrderId);*/
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
            commonResponse.setData(truckOrderComplete);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前订单 送货路线当前集结点 需要送的货袋",notes = "selectRallyPointBags",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="rallyPointId", value="集结点id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectRallyPointBags",method = RequestMethod.GET)
    public CommonResponse selectRallyPointBags(int taskOrderId, int rallyPointId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<RallyPointBags> selectRallyPointBags = deliveryAssistantService.selectRallyPointBags(taskOrderId, rallyPointId);
            if(selectRallyPointBags.size() == 0){
                List<RallyPointBags> rallyPointBags = new ArrayList<>();
                commonResponse.setCode(GyCode.ORDER_NOT_STARTED.getCode());
                commonResponse.setMessage(GyCode.ORDER_NOT_STARTED.getInfo());
                commonResponse.setData(rallyPointBags);
            }
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectRallyPointBags);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询小车使用状态",notes = "selectCarState",tags={"@郭阳"})
    @ApiImplicitParam(name="lockNumber", value="小车车锁编号",paramType="query",dataType="String")
    @RequestMapping(value = "/selectCarState",method = RequestMethod.GET)
    public CommonResponse selectCarState(String lockNumber) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer selectCarState = deliveryAssistantService.selectCarState(lockNumber);

            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectCarState);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "匹配小车和订单",notes = "bindingCarOrderBag",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="lockNumber", value="小车车厢锁编号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="bagNumber", value="货袋编号",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/bindingCarOrderBag",method = RequestMethod.POST)
    public CommonResponse bindingCarOrderBag(String lockNumber, String bagNumber) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer bindingCarOrderBag = deliveryAssistantService.bindingCarOrderBag(lockNumber, bagNumber);
            if(bindingCarOrderBag < 1){
                commonResponse.setCode(GyCode.FAIL_MATCH.getCode());
                commonResponse.setMessage(GyCode.FAIL_MATCH.getInfo());
            }
            Integer carId = carDeliveryAssistantService.selectCarIdByLockNumber(lockNumber);
            Integer insertMessage = messageService.insertMessage(9, 0, carId, 0, 0, 0, 0);
            commonResponse.setCode(GyCode.SUCCESS_MATCH.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_MATCH.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_MATCH.getCode());
            commonResponse.setMessage(GyCode.FAIL_MATCH.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询是否有异常件",notes = "isAbnormal",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/isAbnormal",method = RequestMethod.GET)
    public CommonResponse isAbnormal(int appUserId, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer selectIsAbnormal = deliveryAssistantService.selectIsAbnormal(appUserId, taskOrderId);
            if(selectIsAbnormal > 0){
                commonResponse.setCode(GyCode.FAIL_ERROR_BAG.getCode());
                commonResponse.setMessage(GyCode.FAIL_ERROR_BAG.getInfo());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_END_TRUCK_ORDER.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_END_TRUCK_ORDER.getInfo());
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前货车所绑定的所有异常件",notes = "selectTruckBudingErrorGoods",tags={"@郭阳"})
    @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectTruckBudingErrorGoods",method = RequestMethod.GET)
    public CommonResponse selectTruckBudingErrorGoods(int appUserId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<String> selectVeryGoodsByTruck = deliveryAssistantService.selectVeryGoodsByTruck(appUserId);
            if(selectVeryGoodsByTruck.size() == 0){
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
                commonResponse.setData(new ArrayList<>());
                return commonResponse;
            }
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectVeryGoodsByTruck);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "货车订单装货完毕 重新给订单算钱",notes = "editTruckOrderMoney",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/editTruckOrderMoney",method = RequestMethod.POST)
    public CommonResponse editTruckOrderMoney(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer editTruckOrderMoney = deliveryAssistantService.editTruckOrderMoney(taskOrderId);
            if(editTruckOrderMoney < 1){
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

    /*----------------------------------------------------货车直接送货到家-----------------------------------------------------*/

    @ApiOperation(value = "查询货车所有货袋里的货物 送货地点",notes = "selectTruckOrderBagZipCode",tags={"@郭阳"})
    @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int")
    @RequestMapping(value = "/selectTruckOrderBagZipCode",method = RequestMethod.GET)
    public CommonResponse selectTruckOrderBagZipCode(int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            /*推送*/
            messageService.insertMessage(13,0,0,0,0,0,taskOrderId);

            List<ReceivingPoint> selectTruckOrderBagZipCode = deliveryAssistantService.selectTruckOrderBagZipCode(taskOrderId);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectTruckOrderBagZipCode);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前点位下的所有包裹",notes = "selectTruckPointPackageByZipCode",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="zipCode", value="点位邮编",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/selectTruckPointPackageByZipCode",method = RequestMethod.GET)
    public CommonResponse selectTruckPointPackageByZipCode(int taskOrderId, int appUserId, String zipCode) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<PointPackage> selectPointPackageByZipCode = deliveryAssistantService.selectPointPackageByZipCode(taskOrderId, appUserId, zipCode);
            if(selectPointPackageByZipCode.size() == 0){
                commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
                commonResponse.setData(new ArrayList<>());
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

    @ApiOperation(value = "货车问题件提交",notes = "problemSubmission",tags={"@郭阳"})
    @RequestMapping(value = "/problemSubmission",method = RequestMethod.POST)
    public CommonResponse problemSubmission(ProblemPieceDto problemPieceDto) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            /*修改货物 异常状态*/
            logisticInfoService.insertAbnormalLogisticInfo(4, problemPieceDto.getAbnormalText(), problemPieceDto.getGoodsId(), 0, problemPieceDto.getAppUserId());
            goodsWarningService.insertGoodsWarningByAppUser(problemPieceDto.getGoodsId(), problemPieceDto.getAbnormalText());
            carDeliveryAssistantService.problemSubmission(problemPieceDto);
            Integer insertAbnormalMessage = messageService.insertAbnormalMessage(1, 0, problemPieceDto.getAbnormalText(), problemPieceDto.getGoodsId());
            commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
            Integer bindingAppUserAndVeryGoodsTwo = 0 ;
            if(!(problemPieceDto.getAbnormalText().equals("包裹遗失"))){
            /*绑定货车司机和异常件*/
            bindingAppUserAndVeryGoodsTwo = deliveryAssistantService.bindingAppUserAndVeryGoodsTwo(problemPieceDto);
            }else{
                bindingAppUserAndVeryGoodsTwo = 1 ;
            }
            if(bindingAppUserAndVeryGoodsTwo < 1){
                commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
                commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
            }else {
                commonResponse.setCode(GyCode.SUCCESS_OPERATE.getCode());
                commonResponse.setMessage(GyCode.SUCCESS_OPERATE.getInfo());
            }

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_OPERATE.getCode());
            commonResponse.setMessage(GyCode.FAIL_OPERATE.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询“货车送货”中途退出后到达第几步(进入订单都需要查)",notes = "selectTruckDeliveryStep",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="appUserId", value="app用户id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="taskOrderId", value="货车订单id",paramType="query",dataType="int"),
    })
    @RequestMapping(value = "/selectTruckDeliveryStep",method = RequestMethod.GET)
    public CommonResponse selectTruckDeliveryStep(int appUserId, int taskOrderId) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Map selectTruckDeliveryStep = deliveryAssistantService.selectTruckDeliveryStep(appUserId, taskOrderId);
            int step = (Integer) selectTruckDeliveryStep.get("step");
            if(step == 0){
                commonResponse.setCode(GyCode.ORDER_NOT_STARTED.getCode());
                commonResponse.setMessage(GyCode.ORDER_NOT_STARTED.getInfo());
            }
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectTruckDeliveryStep);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "查询当前个人的所有包裹",notes = "selectPeopleGoods",tags={"@郭阳"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskOrderId", value="货车订单id/小车订单id",paramType="query",dataType="int"),
            @ApiImplicitParam(name="deliveryNumber", value="快递单号",paramType="query",dataType="String"),
            @ApiImplicitParam(name="simpleOrNormal", value="1货车送货 2小车送货",paramType="query",dataType="int"),
            @ApiImplicitParam(name="zipCode", value="点位邮编",paramType="query",dataType="String"),
    })
    @RequestMapping(value = "/selectPeopleGoods",method = RequestMethod.GET)
    public CommonResponse selectPeopleGoods(int taskOrderId, String deliveryNumber, int simpleOrNormal, String zipCode) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<PeopleGoods> selectPeopleGoodsAll = deliveryAssistantService.selectPeopleGoodsAll(taskOrderId, deliveryNumber, simpleOrNormal, zipCode);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(selectPeopleGoodsAll);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }

    @ApiOperation(value = "计算勾选的所有包裹的总价格",notes = "selectPeopleGoods",tags={"@郝腾"})
    @ApiImplicitParam(name="goodsIds", value="货物id(多个货物签收)",dataType="String")
    @RequestMapping(value = "/calculateGoodsPrice",method = RequestMethod.POST)
    public CommonResponse calculateGoodsPrice(String goodsIds) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            PeopleGoods peopleGoods = goodsDetailsService.calculateGoodsPrice(goodsIds);
            commonResponse.setCode(GyCode.SUCCESS_GET.getCode());
            commonResponse.setMessage(GyCode.SUCCESS_GET.getInfo());
            commonResponse.setData(peopleGoods);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(GyCode.FAIL_GET.getCode());
            commonResponse.setMessage(GyCode.FAIL_GET.getInfo());
        }

        return commonResponse;
    }
}
