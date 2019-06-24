package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AddCarOrderDto;
import com.siruiman.crosslogistics.model.dto.ProblemPieceDto;
import com.siruiman.crosslogistics.service.TruckDeliveryAssistantService;
import com.siruiman.crosslogistics.util.Base64;
import com.siruiman.crosslogistics.util.LockUtils;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TruckDeliveryAssistantServiceImpl implements TruckDeliveryAssistantService {
    @Autowired
    private TruckDeliveryAssistantMapper deliveryAssistantMapper;
    @Autowired
    private TaskCarOrderMapper taskCarOrderMapper;
    @Autowired
    private CarDeliveryAssistantMapper carDeliveryAssistantMapper;
    @Autowired
    private TaskTruckOrderMapper taskTruckOrderMapper;
    @Autowired
    private TaskOrderBagMapper taskOrderBagMapper;
    @Autowired
    private BagMapper bagMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private  CarMapper carMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private LogisticInfoMapper logisticInfoMapper;
    @Autowired
    private RallyPointMapper rallyPointMapper;
    @Autowired
    private SingaporeAreaMapper singaporeAreaMapper;
    @Autowired
    private GenerateCarordersMapper generateCarordersMapper;

    @Override
    public List<AppTruckOrder> selectAppTruckOrder(int appUserId) {
        try{
            List<AppTruckOrder> appTruckOrderList = new ArrayList<>();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            List<AppTruckOrder>  appTruckOrderList1 = deliveryAssistantMapper.selectAppTruckOrder(appUserId, createTime);
            for (AppTruckOrder appTruckOrder:appTruckOrderList1
                 ) {

                if(appTruckOrder.getState()==3 || (appTruckOrder.getCreateTime().substring(0,10)).equals(createTime)){
                    appTruckOrderList.add(appTruckOrder);
                }
            }
            return appTruckOrderList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editTruckOrderStatus(int taskOrderId, int appUserId, int orderType) {
        try{
            /*查询订单类型 如果已经修改了 在判断是否这次修改 和上次一样*/
            Integer selectOrderType = deliveryAssistantMapper.selectOrderType(taskOrderId);
            /*if(selectOrderType != null && orderType != selectOrderType){
                return 6;
            }*/
            Integer selectTruckOrderStatus = deliveryAssistantMapper.selectTruckOrderStatus(taskOrderId);
            if(selectTruckOrderStatus == 3){
                if(orderType != selectOrderType){
                    return 6;
                }
            }
            if(selectTruckOrderStatus == 5){
                return selectTruckOrderStatus;
            }
            /*判断除当前订单是否还有开始未完成订单*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            List<AppTruckOrder> selectAppTruckOrder=deliveryAssistantMapper.selectAppTruckOrder(appUserId, createTime);
            int state = 0;
            for(AppTruckOrder appTruckOrder : selectAppTruckOrder){
                if(appTruckOrder.getTaskOrderId() != taskOrderId){
                    if(appTruckOrder.getState() == 3){
                        state = appTruckOrder.getState();
                    }
                }
            }
            if(state == 3){
                return state;
            }
            Integer editTruckOrderStatus = deliveryAssistantMapper.editTruckOrderStatus(taskOrderId, orderType);
            if(editTruckOrderStatus > 0){
                /*修改货车使用状态*/
                Integer editTruckStatus = deliveryAssistantMapper.editTruckStatus(appUserId, 1);
            }
            return editTruckOrderStatus;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Warehouse selectWarehouseLatLng(int taskOrderId) {
        try{
            return deliveryAssistantMapper.selectWarehouseLatLng(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String selectTruckNumber(int appUserId) {
        try{
            return deliveryAssistantMapper.selectTruckNumber(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addDeliverySsistantRecord(int appUserId, int taskOrderId, int orderType) {
        try{
            Integer countDeliverySsistantRecord = deliveryAssistantMapper.countDeliverySsistantRecord(appUserId, taskOrderId, orderType);
            if(countDeliverySsistantRecord == 1){
                return 2;
            }
            return deliveryAssistantMapper.addDeliverySsistantRecord(appUserId, taskOrderId, orderType);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer truckOrderBindingBag(String bagNumber, int taskOrderId) {
        try{

            /*查询货袋id*/
            Integer bagId = deliveryAssistantMapper.selectBagId(bagNumber);
            /*验证是否已经绑定这个货袋 避免重复*/
            Integer selectBindingBag = deliveryAssistantMapper.selectBindingBag(bagId, taskOrderId);
            if(selectBindingBag > 0){
                return 2;
            }
            Integer truckOrderBindingBag = deliveryAssistantMapper.truckOrderBindingBag(bagId, taskOrderId);
            if(truckOrderBindingBag > 0){
                Integer editBagStatus = deliveryAssistantMapper.editBagStatus(bagId, 6);
                return editBagStatus;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editStep(int appUserId, int taskOrderId, int orderType, int step) {
        try{
            Integer editStep = deliveryAssistantMapper.editStep(appUserId, taskOrderId, orderType, step);
            return editStep;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TruckTaskRoute> selectTruckOrderRoute(int taskOrderId) {
        try{
            List<TruckTaskRoute> selectTruckOrderRoute = deliveryAssistantMapper.selectTruckOrderRoute(taskOrderId);
            /*处理如果集结点没货 点位状态改为完成*/
            for(TruckTaskRoute truckTaskRoute : selectTruckOrderRoute){
                List<RallyPointBags> selectRallyPointBags = deliveryAssistantMapper.selectRallyPointBags(taskOrderId, truckTaskRoute.getRouteId());
                if(selectRallyPointBags.size() == 0){
                    truckTaskRoute.setReachStatus(1);
                }
            }
            return selectTruckOrderRoute;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countTruckOrderRoute(int taskOrderId) {
        try{
            return deliveryAssistantMapper.countTruckOrderRoute(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String carUnlock(int appUserId, int taskOrderId, String lockNumber) {
        try{
            /*查询小车使用状态  使用状态  1.空闲 2.派送中 3.维护中 4.空闲（内有异常件）5.报废*/
            Integer carState = deliveryAssistantMapper.selectCarState(lockNumber);
            if(carState != 1 && carState != 4){
                return carState.toString();
            }
            /*根据车锁编号查询小车集结点id*/
            Integer rallyPointId = deliveryAssistantMapper.selectRallyPointId(lockNumber);
            /*查询订单路线*/
            List<TruckTaskRoute> selectTruckOrderRoute = deliveryAssistantMapper.selectTruckOrderRoute(taskOrderId);
            int canIt = 0;
            for(TruckTaskRoute truckTaskRoute : selectTruckOrderRoute){
                if(truckTaskRoute.getRouteId() == rallyPointId){
                    canIt = 1;
                }
            }
            if(canIt == 1){
                LockUtils.unLock(lockNumber);
                Thread.sleep(6000);
                Hashtable lockState = StaticConfigUtil.lockState;
                String unlockStatus = (String) lockState.get(lockNumber);
                if(unlockStatus.equals("2")){
                    return "6";
                }
                /*存储车锁编号 逻辑用*/
                Integer storageLockNumber = deliveryAssistantMapper.storageLockNumber(lockNumber, appUserId, taskOrderId, "", 0, 0);
            }
            return carState.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer bindingAppUserAndVeryGoods(String deliveryNumber, int appUserId, String lockNumber) {
        try{
            /*根据货物快递单号查询货物id*/
            Integer goodsId = deliveryAssistantMapper.selectGoodsId(deliveryNumber);
            /*查询小车id*/
            Integer carId = deliveryAssistantMapper.selectCarId(lockNumber);
            /*绑定问题件和货车司机*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            Integer bindingAppUserAndVeryGoods = deliveryAssistantMapper.bindingAppUserAndVeryGoods(appUserId, goodsId, carId, createTime);
            return bindingAppUserAndVeryGoods;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer bindingCarBag(String bagNumber, String lockNumber, int appUserId, int taskOrderId, int todrId, int rallyPointId) {
        try{
            /*根据货袋编号查询货袋id*/
            Integer bagId = deliveryAssistantMapper.selectBagId(bagNumber);
            /*查询当前货袋所属小车集结点id*/
            Integer selectRallyPointId = deliveryAssistantMapper.selectRallyPointIdByBagId(bagId);

            /*验证当前绑定货袋是不是当前下车集结点的*/
            /*根据车锁查询小车id和小车集结点id*/
            RallyPointIdAndCarId rallyPointIdAndCarId = deliveryAssistantMapper.selectRallyPointIdAndCarId(lockNumber);
            if(selectRallyPointId != rallyPointIdAndCarId.getRallyPointId()){
                return 2;
            }

            /*根据小车车锁编号查询小车id */
            Integer carId = deliveryAssistantMapper.selectCarId(lockNumber);
            /*当前时间*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            /*查询小车订单id*/
            /*Integer taskOrderId = deliveryAssistantMapper.selectCarOrderId(rallyPointIdAndCarId.getCarId(), createTime);*/
            /*查询车锁是否关闭 如果关闭不允许绑定货袋*/
            Integer selectCarLockState = deliveryAssistantMapper.selectCarLockState(lockNumber);
            if(selectCarLockState == 2){
                return 3;
            }
            /*如果已经绑定货袋 不能再绑*/
            Integer selectCarBudingBag = deliveryAssistantMapper.selectCarBudingBag(carId);
            if(selectCarBudingBag > 0){
                return 4;
            }

            /*绑定小车和货袋*/
            Integer bindingCarBag = deliveryAssistantMapper.bindingCarBag(bagId, carId);
            if(bindingCarBag > 0){
                /*如果绑定成功 将该小车的状态改为派送中*/
                Integer editCarStatus = deliveryAssistantMapper.editCarStatus(rallyPointIdAndCarId.getCarId());
                /*修改货袋状态为小车运输中*/
                Integer editBagStatus = deliveryAssistantMapper.editBagStatus(bagId, 7);
                /*修改步骤到第5步 */
                Integer editStep = deliveryAssistantMapper.editStep(appUserId, taskOrderId, 1, 5);
                /*保存货袋编号 货车路线主键id 集结点id*/
                Integer storageLockNumber = deliveryAssistantMapper.storageLockNumber(lockNumber, appUserId, taskOrderId, bagNumber, todrId, rallyPointId);
            }
            return bindingCarBag;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editTruckOrderRouteStatus(int todrId) {
        try{
            Integer editTruckOrderRouteStatus = deliveryAssistantMapper.editTruckOrderRouteStatus(todrId);
            return editTruckOrderRouteStatus;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer carCloseLock(String lockNumber, int taskOrderId) {
        try{
            Thread.sleep(3000);
            Hashtable lockState = StaticConfigUtil.lockState;
            String unlockStatus = (String) lockState.get(lockNumber);
            if(!unlockStatus.equals("2")){
                return 1;
            }
            /*修改关闭车锁状态*/
            Integer editCarLockStatus = deliveryAssistantMapper.editCarLockStatus(lockNumber, 2);
            /*根据车锁编号查询小车集结点id*/
            Integer rallyPointId = deliveryAssistantMapper.selectRallyPointId(lockNumber);
            /*查询订单路线*/
            TruckTaskRoute selectTruckOrderRouteByrallyPointId = deliveryAssistantMapper.selectTruckOrderRouteByrallyPointId(taskOrderId, rallyPointId);
            int canIt = 0;
            if(selectTruckOrderRouteByrallyPointId.getReachStatus() == 0){
                canIt = 1;
            }
            /*如果当前小车点全部送完*/
            if(canIt ==  0){
                return 2;
            }
            return 3;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map selectStep(int appUserId, int taskOrderId) {
        Map map = new HashMap();
        try{
            /*返回234步骤用*/
            /*查询货车送货路线*/
            List<TruckTaskRoute> selectTruckOrderRoute = deliveryAssistantMapper.selectTruckOrderRoute(taskOrderId);
            /*处理如果集结点没货 点位状态改为完成*/
            for(TruckTaskRoute truckTaskRoute : selectTruckOrderRoute){
                List<RallyPointBags> selectRallyPointBags = deliveryAssistantMapper.selectRallyPointBags(taskOrderId, truckTaskRoute.getRouteId());
                if(selectRallyPointBags.size() == 0){
                    truckTaskRoute.setReachStatus(1);
                }
            }
            /*查询步骤*/
            Integer step = deliveryAssistantMapper.selectStep(appUserId, taskOrderId);
            if(step == null){
                map.put("step", 0);
                return map;
            }
            /*扫描货袋*/
            if(step == 1){
                map.put("step", step);
                /*查询已绑定货袋信息*/
                List<String> selectTruckOrderBags = deliveryAssistantMapper.selectTruckOrderBags(taskOrderId);
                if(selectTruckOrderBags.size() == 0){
                    map.put("stepInfo", new ArrayList<>());
                }else {
                    map.put("stepInfo", selectTruckOrderBags);
                }
                return map;
            }

            /*查询步骤存的车锁编号（逻辑用）*/
            TruckDeliveryAssStep selectLockNumber = deliveryAssistantMapper.selectLockNumber(appUserId, taskOrderId);
            /*导航至小车集散点*/
            if(step == 2){
                map.put("step", step);
                map.put("stepInfo", selectTruckOrderRoute);

                if(selectLockNumber != null && selectLockNumber.getLockNumber() != null){
                    map.put("stepLockNumber", selectLockNumber.getLockNumber());
                }else {
                    map.put("stepLockNumber", "");
                }
                return map;
            }

            /*问题件*/
            if(step == 3){
                map.put("step", step);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String createTime = df.format(new Date());
                Integer carId = deliveryAssistantMapper.selectCarId(selectLockNumber.getLockNumber());
                /*查询今天这个小车和货车司机所绑定的问题件*/
                List<String> selectVeryGoods = deliveryAssistantMapper.selectVeryGoods(appUserId, carId, createTime);
                map.put("stepInfo", selectTruckOrderRoute);
                if(selectVeryGoods.size() == 0){
                    List<String> selectVeryGoodss = new ArrayList<>();
                    map.put("stepGoodsInfo", selectVeryGoodss);
                }else {
                    map.put("stepGoodsInfo", selectVeryGoods);
                }

                if(selectLockNumber != null && selectLockNumber.getLockNumber() != null){
                    map.put("stepLockNumber", selectLockNumber.getLockNumber());
                }else {
                    map.put("stepLockNumber", "");
                }
                return map;
            }

            /*绑定货袋和小车*/
            if(step == 4){
                map.put("step", step);
                map.put("stepInfo", selectTruckOrderRoute);
                if(selectLockNumber != null && selectLockNumber.getLockNumber() != null){
                    map.put("stepLockNumber", selectLockNumber.getLockNumber());
                }else {
                    map.put("stepLockNumber", "");
                }
                return map;
            }

            /*是否有问题件*/
            if(step == 5){
                map.put("step", step);
                /*map.put("stepInfo", selectTruckOrderRoute);*/
                if(selectLockNumber != null && selectLockNumber.getLockNumber() != null){
                    map.put("stepLockNumber", selectLockNumber.getLockNumber());
                }else {
                    map.put("stepLockNumber", "");
                }

                if(selectLockNumber != null && selectLockNumber.getBagNumber() != null){
                    map.put("stepBagNumber", selectLockNumber.getBagNumber());
                }else {
                    map.put("stepBagNumber", "");
                }

                if(selectLockNumber != null && selectLockNumber.getTodrId() != 0){
                    map.put("stepTodrId", selectLockNumber.getTodrId());
                }else {
                    map.put("stepTodrId", 0);
                }

                if(selectLockNumber != null && selectLockNumber.getRallyPointId() != 0){
                    map.put("stepRallyPointId", selectLockNumber.getRallyPointId());
                }else {
                    map.put("stepRallyPointId", 0);
                }
                return map;
            }

            /*是否有问题件*/
            if(step == 6){
                map.put("step", step);
               /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String createTime = df.format(new Date());
                *//*查询是否有异常件需要送回仓库*//*
                Integer selectIsAbnormal = deliveryAssistantMapper.selectIsAbnormal(appUserId, createTime);*/
                List<Integer> bagIds = new ArrayList<>();
                //判断这个人是否有异常件
                List<TruckProblemPiece> truckProblemPieceList =  deliveryAssistantMapper.selectIsAbnormal(appUserId);
                if(truckProblemPieceList.size()>0){
                    for (TruckProblemPiece truckProblemPiece:truckProblemPieceList
                    ) {
                        int bagId = goodsMapper.selectBagIdByGdId(truckProblemPiece.getGoodsId());
                        bagIds.add(bagId);
                    }
                }
                int selectIsAbnormal =0;
                if(bagIds.size()>0){
                    //根据订单id查询所有货袋id
                    List<TaskOrderBag> taskOrderBagList = taskOrderBagMapper.selectBagsBytruckOrderId(taskOrderId);
                    for (TaskOrderBag taskOrderBag:taskOrderBagList
                    ) {
                        for (Integer bagId : bagIds
                        ) {
                            if (bagId == taskOrderBag.getBagId()) {
                                selectIsAbnormal = 1;
                                break;
                            }
                        }
                        if(selectIsAbnormal==1){
                            break;
                        }
                    }
                }
                map.put("stepInfo", selectIsAbnormal);
                if(selectLockNumber != null && selectLockNumber.getLockNumber() != null){
                    map.put("stepLockNumber", selectLockNumber.getLockNumber());
                }else {
                    map.put("stepLockNumber", "");
                }
                return map;
            }
            map.put("step", 0);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer truckOrderComplete(int taskOrderId, int appUserId) {
        try{
            /*查询订单状态*/
            Integer truckOrderStatus = deliveryAssistantMapper.selectTruckOrderStatus(taskOrderId);
            if(truckOrderStatus != 5){
               /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String createTime = df.format(new Date());
                *//*查询货车异常件是否处理完毕*//*
                Integer selectVeryGoodsIsSolve = deliveryAssistantMapper.selectVeryGoodsIsSolve(appUserId, createTime);*/
                List<Integer> bagIds = new ArrayList<>();
                //判断这个人是否有异常件
                List<TruckProblemPiece> truckProblemPieceList =  deliveryAssistantMapper.selectIsAbnormal(appUserId);
                if(truckProblemPieceList.size()>0){
                    for (TruckProblemPiece truckProblemPiece:truckProblemPieceList
                    ) {
                        int bagId = goodsMapper.selectBagIdByGdId(truckProblemPiece.getGoodsId());
                        bagIds.add(bagId);
                    }
                }
                if(bagIds.size()>0){
                    //根据订单id查询所有货袋id
                    List<TaskOrderBag> taskOrderBagList1 = taskOrderBagMapper.selectBagsBytruckOrderId(taskOrderId);
                    int selectVeryGoodsIsSolve =0;
                    for (TaskOrderBag taskOrderBag:taskOrderBagList1
                    ) {
                        for (Integer bagId : bagIds
                        ) {
                            if (bagId == taskOrderBag.getBagId()) {
                                selectVeryGoodsIsSolve = 1;
                                break;
                            }
                        }
                        if(selectVeryGoodsIsSolve==1){
                            break;
                        }
                    }
                    if(selectVeryGoodsIsSolve != 0){
                        return 3;
                    }
                }
                /*修改货车使用状态*/
                Integer editTruckStatus = deliveryAssistantMapper.editTruckStatus(appUserId, 2);
                //查询订单的送货模式
                TaskTruckOrder taskTruckOrder = taskTruckOrderMapper.selectByPrimaryKey(taskOrderId);
                if(taskTruckOrder!=null && taskTruckOrder.getOrderType()==2){
                    List<TaskOrderBag> taskOrderBagList =taskOrderBagMapper.selectBagsBytruckOrderId(taskOrderId);
                    if(taskOrderBagList.size()>0){
                        for (TaskOrderBag taskOrderBag:taskOrderBagList
                        ) {
                            Bag bag = new Bag();
                            bag.setState((short)8);
                            bag.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            bag.setBagId(taskOrderBag.getBagId());
                            bagMapper.updateBagState(bag);
                            LogisticInfo logisticInfo = new LogisticInfo();
                            logisticInfo.setBagId(taskOrderBag.getBagId());
                            logisticInfo.setCreateTime(new Date());
                            logisticInfo.setOperateResult("货袋配送完成");
                            AppUser appUser = appUserMapper.selectAppUserByUserId(appUserId);
                            if (appUser != null && appUser.getActualName() != null) {
                                logisticInfo.setOperateName(appUser.getActualName());
                            }
                            logisticInfo.setOperateComment("货袋配送完成");
                            logisticInfo.setOperateType("货袋配送完成");
                            logisticInfo.setAppUserId(appUserId);
                            logisticInfo.setGoodsId(null);
                            logisticInfoMapper.insert(logisticInfo);
                        }
                    }


                }
                return deliveryAssistantMapper.truckOrderComplete(taskOrderId);
            }

            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectBagId(String bagNumber) {
        try{
            return deliveryAssistantMapper.selectBagId(bagNumber);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RallyPointBags> selectRallyPointBags(int taskOrderId, int rallyPointId) {
        try{
            return deliveryAssistantMapper.selectRallyPointBags(taskOrderId, rallyPointId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectCarState(String lockNumber) {
        try{
            return deliveryAssistantMapper.selectCarState(lockNumber);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer bindingCarOrderBag(String lockNumber, String bagNumber) {
        try{

            /*根据货袋编号查询货袋id*/
            Integer bagId = deliveryAssistantMapper.selectBagId(bagNumber);
            /*查询当前货袋有多少货物*/
            Integer goodsNum = deliveryAssistantMapper.selectGoodsNum(bagId);
            /*订单钱数*/
            Double orderMoney = goodsNum * StaticConfigUtil.anOrderPrice;
            /*根据小车车锁编号查询小车id */
            Integer carId = deliveryAssistantMapper.selectCarId(lockNumber);
            Integer count = deliveryAssistantMapper.selectCountCarOrder(bagId,carId);
            if(count != 1){

                /*当前时间*/
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String createTime = df.format(new Date());
                Integer rallyPointId = deliveryAssistantMapper.selectRallyPointIdByCarId(carId);

                //新增代码
                //查询此集结点有无小车订单，如果无小车订单了，就生成一条新订单
                List<TaskCarOrder> taskCarOrderList = deliveryAssistantMapper.selectCarOrderByRallyId(rallyPointId,createTime);
                if(taskCarOrderList.size()==0){
                    //根据集结点查询所属区域
                    RallyPoint  rallyPoint = rallyPointMapper.selectByPrimaryKey(rallyPointId);
                    SingaporeArea singaporeArea =singaporeAreaMapper.selectByPrimaryKey(rallyPoint.getSingaporeAreaId());
                    //*添加订单参数*//*
                    AddCarOrderDto addCarOrderDto = new AddCarOrderDto();
                    //*区域id*//*
                    addCarOrderDto.setSingaporeAreaId(rallyPoint.getSingaporeAreaId());
                    //*小车集结点名称*//*
                    String rallyPointName = rallyPoint.getRallyPointName();
                    //*小车集结点id*//*
                    addCarOrderDto.setRallyPointId(rallyPointId);
                    //*订单时间*//*
                    addCarOrderDto.setCreateTime(createTime);
                    //*订单钱数selectAverageMoney(carId);*//*
                    Double averageMoney = 100.00;
                    addCarOrderDto.setMoney(averageMoney);
                    //*订单编号*//*
                    String orderNumber = "10" + RandomCodeUtil.getRandomCode();
                    addCarOrderDto.setOrderNumber(orderNumber);
                    //*订单名称*//*
                    String singaporeAreaName = singaporeArea.getSingaporeAreaName();
                    String orderName = singaporeAreaName + rallyPointName + orderNumber;
                    addCarOrderDto.setName(orderName);
                    Integer addCarOrder = generateCarordersMapper.addCarOrder(addCarOrderDto);
                    //查询最后一条订单id
                    Integer taskOrderId = generateCarordersMapper.selectLastOrderId();
                    //*绑定小车和订单 修改订单钱数*//*
                    Integer bindingCarOrderAndCar = deliveryAssistantMapper.bindingCarOrderAndCar(taskOrderId, carId, orderMoney, bagId);
                    return bindingCarOrderAndCar;
                }
                //新增代码结束

                /*查询谁先抢的单*/
                List<CarOrderTime> selectCarOrderTime = deliveryAssistantMapper.selectCarOrderTime(createTime, rallyPointId);

                /*如果只有一个人抢单*/
                if(selectCarOrderTime.size() == 1){
                    CarOrderTime carOrderTime = selectCarOrderTime.get(0);
                    /*绑定小车和订单 修改订单钱数*/
                    Integer bindingCarOrderAndCar = deliveryAssistantMapper.bindingCarOrderAndCar(carOrderTime.getTaskOrderId(), carId, orderMoney, bagId);
                    return bindingCarOrderAndCar;
                }
                /*如果有多人抢单*/
                if(selectCarOrderTime.size() != 0){
                    /*绑定小车和订单 修改订单钱数*/
                    Integer bindingCarOrderAndCar = bindingCarOrderAndCar(selectCarOrderTime, carId, 1, createTime, orderMoney, bagId);
                    return bindingCarOrderAndCar;
                }
                /*如果没人抢单*/
                /*查询模板id和模板添加时间 并且是今日的订单未绑定小车的*/
                List<CarOrderTime> selectCarOrderTimeByCarTask = deliveryAssistantMapper.selectCarOrderTimeByCarTask(rallyPointId,createTime);
                /*如果只有一个模板*/
                if(selectCarOrderTimeByCarTask.size() == 1){
                    int carTaskId = selectCarOrderTimeByCarTask.get(0).getTaskOrderId();
                    /*根据模板查询小车订单id*/
                    Integer carOrderIdByCarTask = deliveryAssistantMapper.selectCarOrderIdByCarTask(carTaskId, createTime);
                    /*绑定小车和订单 修改订单钱数*/
                    Integer bindingCarOrderAndCar = deliveryAssistantMapper.bindingCarOrderAndCar(carOrderIdByCarTask, carId, orderMoney, bagId);
                    return bindingCarOrderAndCar;
                }else if(selectCarOrderTimeByCarTask.size() != 0){ /*如果有多个模板*/
                    /*绑定小车和订单 修改订单钱数*/
                    Integer bindingCarOrderAndCar = bindingCarOrderAndCar(selectCarOrderTime, carId, 2, createTime, orderMoney, bagId);
                    return bindingCarOrderAndCar;
                }
                /*如果没有模板也没有抢单*/
                List<CarOrderTime> selectCarOrderNOTaskGrab = deliveryAssistantMapper.selectCarOrderNOTaskGrab(rallyPointId, createTime);
                CarOrderTime carOrderTime = selectCarOrderNOTaskGrab.get(0);
                /*绑定小车和订单 修改订单钱数*/
                Integer bindingCarOrderAndCar = deliveryAssistantMapper.bindingCarOrderAndCar(carOrderTime.getTaskOrderId(), carId, orderMoney, bagId);
                return bindingCarOrderAndCar;
            }
           return count;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param selectCarOrderTime
     * @param carId
     * @param carOrderStatus（1是多个抢单 2是多个模板）
     * @return
     */
    public Integer bindingCarOrderAndCar(List<CarOrderTime> selectCarOrderTime, int carId, int carOrderStatus, String createTime, double orderMoney, int bagId){
        Integer bindingCarOrderAndCar;
        int taskOrderId = 0;
        Long leastTime = Long.parseLong("0");
        for(int i = 0; i < selectCarOrderTime.size(); i++){
            CarOrderTime carOrderTime = selectCarOrderTime.get(i);
            Long grabOrderTime = Long.parseLong(Base64.dateToStamp(carOrderTime.getGrabOrderTime()));
            if(i == 0){
                leastTime = grabOrderTime;
                taskOrderId = carOrderTime.getTaskOrderId();
            }else if(grabOrderTime < leastTime){
                leastTime = grabOrderTime;
                taskOrderId = carOrderTime.getTaskOrderId();
            }

            /*for(int y = 0; y < selectCarOrderTime.size(); y++){
                if(i!=y){
                    CarOrderTime carOrderTimes = selectCarOrderTime.get(y);
                    Long grabOrderTimes = Long.parseLong(Base64.dateToStamp(carOrderTimes.getGrabOrderTime()));
                    if(grabOrderTime < grabOrderTimes){
                        if(y == selectCarOrderTime.size()-1){
                            taskOrderId = carOrderTime.getTaskOrderId();
                        }
                    }else {
                        break;
                    }
                }
            }*/
        }
        if(carOrderStatus == 1){
            /*绑定小车和订单*/
            bindingCarOrderAndCar = deliveryAssistantMapper.bindingCarOrderAndCar(taskOrderId, carId, orderMoney, bagId);
            return bindingCarOrderAndCar;
        }
        if(carOrderStatus == 2){
            /*根据模板查询小车订单id*/
            Integer carOrderIdByCarTask = deliveryAssistantMapper.selectCarOrderIdByCarTask(taskOrderId, createTime);
            /*绑定小车和订单*/
            bindingCarOrderAndCar = deliveryAssistantMapper.bindingCarOrderAndCar(carOrderIdByCarTask, carId, orderMoney, bagId);
            return bindingCarOrderAndCar;
        }
        return null;
    }

    @Override
    public TruckOrderMoney selectTruckOrderMoney(int taskOrderId) {
        try{
            return deliveryAssistantMapper.selectTruckOrderMoney(taskOrderId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectIsAbnormal(int appUserId, int taskOrderId) {
        try{
           /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            Integer selectIsAbnormal = deliveryAssistantMapper.selectIsAbnormal(appUserId, createTime);*/
           List<Integer> bagIds = new ArrayList<>();
            //判断这个人是否有异常件
            List<TruckProblemPiece> truckProblemPieceList =  deliveryAssistantMapper.selectIsAbnormal(appUserId);
            if(truckProblemPieceList.size()>0){
                for (TruckProblemPiece truckProblemPiece:truckProblemPieceList
                ) {
                    int bagId = goodsMapper.selectBagIdByGdId(truckProblemPiece.getGoodsId());
                    bagIds.add(bagId);
                }
            }
            int selectIsAbnormal =0;
            if(bagIds.size()>0){
                //根据订单id查询所有货袋id
                List<TaskOrderBag> taskOrderBagList = taskOrderBagMapper.selectBagsBytruckOrderId(taskOrderId);
                for (TaskOrderBag taskOrderBag:taskOrderBagList
                ) {
                    for (Integer bagId : bagIds
                    ) {
                        if (bagId == taskOrderBag.getBagId()) {
                            selectIsAbnormal = 1;
                            break;
                        }
                    }
                    if(selectIsAbnormal==1){
                        break;
                    }
                }
            }
            /*如果有异常件*/
            if(selectIsAbnormal > 0){
                /*修改步骤到第6步 送回异常件*/
                Integer editStep = deliveryAssistantMapper.editStep(appUserId, taskOrderId, 1, 6);
            }
            return selectIsAbnormal;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> selectVeryGoodsByTruck(int appUserId) {
        try{
           /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            List<String> selectVeryGoodsByTruck = deliveryAssistantMapper.selectVeryGoodsByTruck(appUserId, createTime);*/
            List<String> selectVeryGoodsByTruck = deliveryAssistantMapper.selectVeryGoodsByTruck(appUserId);
            return selectVeryGoodsByTruck;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editTruckOrderMoney(int taskOrderId) {
        try{
            /*查询当前订单有多少个货袋*/
            Integer selectTruckOrderBagsNum = deliveryAssistantMapper.selectTruckOrderBagsNum(taskOrderId);
            double money = selectTruckOrderBagsNum * StaticConfigUtil.anBagPrice;
            /*修改货车订单钱数*/
            Integer editTruckOrderMoney = deliveryAssistantMapper.editTruckOrderMoney(taskOrderId, money);
            return editTruckOrderMoney;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReceivingPoint> selectTruckOrderBagZipCode(int taskOrderId) {
        try{
            List<ReceivingPoint> receivingPoints = new ArrayList<>();
            List<String> selectZipCode = deliveryAssistantMapper.selectZipCode(taskOrderId);
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
                /*List<CargoInfo> selectCargoInfoByCarOrderAndZipCode = carDeliveryAssistantMapper.selectCargoInfoByCarOrderAndZipCode(taskOrderId, zipCode);*/
                List<CargoInfo> selectTruckOrderBagByZipCode = deliveryAssistantMapper.selectTruckOrderBagByZipCode(taskOrderId, zipCode);
                for (CargoInfo cargoInfo : selectTruckOrderBagByZipCode){
                    if(cargoInfo.getWarningState().equals("无异常") && cargoInfo.getIsReceiveGoods() == 2){
                        isComplete = 0;
                    }
                }
                receivingPoint.setIsComplete(isComplete);
                receivingPoints.add(receivingPoint);
            }
            TaskTruckOrder taskTruckOrder = taskTruckOrderMapper.selectByPrimaryKey(taskOrderId);
            if(taskTruckOrder!=null&&taskTruckOrder.getOrderType()!=null&&taskTruckOrder.getOrderType()==2){
                Collections.sort(receivingPoints);
            }
            return receivingPoints;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PointPackage> selectPointPackageByZipCode(int taskOrderId, int appUserId, String zipCode) {
        try{
            List<PointPackage> selectPointPackageByZipCode = deliveryAssistantMapper.selectPointPackageByZipCode(taskOrderId, appUserId, zipCode);
            for(PointPackage pointPackage : selectPointPackageByZipCode){
                String deliveryNumber = pointPackage.getDeliveryNumber();
                PointPackage totalGoods = deliveryAssistantMapper.selectTotalGoods(deliveryNumber);
                if(pointPackage.getDeliveryNumber().indexOf("-") != -1){
                    /*货物一共多少箱*/
                    deliveryNumber = StringUtils.substringBefore(pointPackage.getDeliveryNumber(), "-");
                    //用总快递单号去查总箱数和货值
                     totalGoods = deliveryAssistantMapper.selectTotalGoods(deliveryNumber);
                    pointPackage.setTotalGoods(totalGoods.getTotalGoods());
                }else {
                    pointPackage.setTotalGoods(1);
                }
                /*处理COD和GST的钱*/
                if(pointPackage.getIsArrivalPay() == 1){
                    pointPackage.setCOD(new BigDecimal(0.00));
                    pointPackage.setTotalCodGst(pointPackage.getGST());
                }else {
                    /*查询当前分箱货物 是否已经有签收的*/
                    Integer selectIsReceiveGoods = deliveryAssistantMapper.selectIsReceiveGoods(deliveryNumber);
                    if(selectIsReceiveGoods == 1){
                        pointPackage.setCOD(new BigDecimal(0.00));
                        pointPackage.setTotalCodGst(new BigDecimal(0.00));
                    }else {
                        /*如果箱数 大于1*/
                        if(totalGoods.getTotalGoods() > 1){
                            pointPackage.setCOD(totalGoods.getCOD());
                            pointPackage.setTotalCodGst(totalGoods.getCOD());
                        }else {
                            pointPackage.setTotalCodGst(pointPackage.getGST().add(pointPackage.getCOD()));
                        }
                    }
                }
            }
            return selectPointPackageByZipCode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer bindingAppUserAndVeryGoodsTwo(ProblemPieceDto problemPieceDto) {
        try{
            /*绑定问题件和货车司机*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String createTime = df.format(new Date());
            Integer bindingAppUserAndVeryGoods = deliveryAssistantMapper.bindingAppUserAndVeryGoods(problemPieceDto.getAppUserId(), problemPieceDto.getGoodsId(), 0, createTime);
            return bindingAppUserAndVeryGoods;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Map selectTruckDeliveryStep(int appUserId, int taskOrderId) {
        Map map = new HashMap();
        try{
            /*查询步骤*/
            Integer step = deliveryAssistantMapper.selectStep(appUserId, taskOrderId);
            if(step == null){
                map.put("step", 0);
                return map;
            }
            /*所有已绑定的货袋*/
            if(step == 1){
                map.put("step", step);
                /*查询已绑定货袋信息*/
                List<String> selectTruckOrderBags = deliveryAssistantMapper.selectTruckOrderBags(taskOrderId);
                if(selectTruckOrderBags.size() == 0){
                    List<String> truckOrderBags = new ArrayList<>();
                    map.put("stepInfo", truckOrderBags);
                }else {
                    map.put("stepInfo", selectTruckOrderBags);
                }
                return map;
            }

            /*所有的收货点*/
            List<ReceivingPoint> selectTruckOrderBagZipCode = selectTruckOrderBagZipCode(taskOrderId);
            if(step == 2){
                map.put("step", step);
                if(selectTruckOrderBagZipCode.size() == 0){
                    map.put("stepInfo", new ArrayList<>());
                }else {
                    map.put("stepInfo", selectTruckOrderBagZipCode);
                }
                return map;
            }

            /*问题件*/
            if(step == 3){
                map.put("step", step);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String createTime = df.format(new Date());
                /*查询货车司机所绑定的问题件*/
                List<String> selectTruckUserBudingErrorGoods = deliveryAssistantMapper.selectTruckUserBudingErrorGoods(appUserId, createTime);
                if(selectTruckUserBudingErrorGoods.size() == 0){
                    map.put("stepInfo", new ArrayList<>());
                }else {
                    map.put("stepInfo", selectTruckUserBudingErrorGoods);
                }
                return map;
            }

            map.put("step", 0);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectBagIdByGoodsId(int goodsId) {
        try{
            return deliveryAssistantMapper.selectBagIdByGoodsId(goodsId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PeopleGoods> selectPeopleGoodsAll(int taskOrderId, String deliveryNumber, int simpleOrNormal, String zipCode) {
        try{
            List<PeopleGoods> selectPeopleGoodsAll = new ArrayList<>();
            /*判断1货车送货 2简易APP用 3小车送货*/
            if(simpleOrNormal == 1){
                selectPeopleGoodsAll = deliveryAssistantMapper.selectPeopleGoodsByTruckNormal(taskOrderId, deliveryNumber, zipCode);
            }

            /*2简易APP用*/
            /*if(simpleOrNormal == 2){
                selectPeopleGoodsAll = deliveryAssistantMapper.selectPeopleGoodsBySimple(deliveryNumber);
            }*/

            /*3小车送货*/
            if(simpleOrNormal == 2){
                selectPeopleGoodsAll = deliveryAssistantMapper.selectPeopleGoodsByCarNormal(taskOrderId, deliveryNumber, zipCode);
            }

            for(PeopleGoods peopleGoods : selectPeopleGoodsAll){
                //如何有分包的包裹，就要给每一包的COD赋值
                if(peopleGoods.getDeliveryNumber().indexOf("-") != -1){
                    /*货物一共多少箱*/
                    deliveryNumber = StringUtils.substringBefore(peopleGoods.getDeliveryNumber(), "-");
                    PointPackage totalGoods = deliveryAssistantMapper.selectTotalGoods(deliveryNumber);
                    peopleGoods.setCOD(totalGoods.getCOD());
                }

                if(peopleGoods.getIsArrivalPay() == 1){
                    peopleGoods.setCOD(new BigDecimal(0.00));
                    peopleGoods.setTotalCodGst(peopleGoods.getGST());
                }else {
                    peopleGoods.setTotalCodGst(peopleGoods.getGST().add(peopleGoods.getCOD()));
                }
            }
            return selectPeopleGoodsAll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer loadingGoodsWarning(String lockNumber) {
        /*查询小车id*/
        Integer carId = deliveryAssistantMapper.selectCarId(lockNumber);
        //查询一下小车状态
        Integer carState = deliveryAssistantMapper.selectCarState(lockNumber);
        //如果小车状态等于4
        if(carState == 4){
            //查询货袋中与此小车绑定，并且状态为内有异常件的最新的一条记录
            Bag bag =  bagMapper.selectBagByCarIdAndState(carId,9);
            //标记为true，如果所有异常件货物都已经与货车绑定，则为true
            boolean flag = true;
            if(bag!=null&&bag.getBagId()!=null){
                //用货袋id和货物状态为异常2的，查询出这个货袋里的所有异常货物
                List<Goods> goodsList = goodsMapper.selectGoodsWarningByBagId(bag.getBagId(),2);
                if (goodsList.size()>0){
                    for (Goods goods:goodsList
                    ) {
                        //遍历异常货物集合，查询货车上绑定的异常件
                        TruckProblemPiece truckProblemPieces =deliveryAssistantMapper.selectGoodsIdByCarId(goods.getGoodsId());
                        //如果货车与异常件绑定表里没有该货物，则将标记改为false
                        if(truckProblemPieces==null){
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(flag){
                //修改小车状态为空闲
                carMapper.updateCarState(carId);
                return 1;
            }
            return 2;
        }
            return 1;
    }
}
