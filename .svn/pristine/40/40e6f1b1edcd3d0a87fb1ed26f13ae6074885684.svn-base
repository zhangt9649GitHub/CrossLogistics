package com.siruiman.crosslogistics.service.impl;

import com.alipay.api.domain.GoodsDetail;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserDto;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.model.dto.PDAGoodsDto;
import com.siruiman.crosslogistics.service.GoodsDetailsService;
import com.siruiman.crosslogistics.util.DateUtil;
import com.siruiman.crosslogistics.util.ExchangeRateUtil;
import com.siruiman.crosslogistics.util.ImputedPriceUtil;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.siruiman.crosslogistics.util.ImputedPriceUtil.*;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;

@Service
public class GoodsDetailsServiceImpl implements GoodsDetailsService {

    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private LogisticInfoMapper logisticInfoMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private WarehousePositionsMapper warehousePositionsMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private SingaporeAreaBuildingMapper singaporeAreaBuildingMapper;
    @Autowired
    private UploadFilesMapper uploadFilesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGoodsDetailById(Integer goodsId) {

            return goodsDetailsMapper.selectGoodsDetailById(goodsId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsDetails(GoodsDetails goodsDetails) {
        goodsDetailsMapper.updateByPrimaryKey(goodsDetails);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGoodsDetailsByTripartiteNumber(String tripartiteNumber) {
            return goodsDetailsMapper.selectGoodsDetailsByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGoodsDetailsByDeliveryNumber(String deliveryNumber) {

            return goodsDetailsMapper.selectGoodsDetailsByDeliveryNumber(deliveryNumber);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertTransshipmentGoods(GoodsDetails goodsDetails) {
        goodsDetailsMapper.insert(goodsDetails);
    }

    /**
     * 根据货物订单编号查询货物详情
     * 张占伟
     *
     * @param deliveryNumber
     * @return
     */
    @Override
    public GoodsDetails getGoodsDetailsByDeliveryNumber(String deliveryNumber) {
        return goodsDetailsMapper.selectGoodsDetailsByDeliveryNumber(deliveryNumber);
    }

    /**
     * 根据货物快递单号查出货物详情表对应的id
     * 张占伟
     *
     * @param deliveryNumber
     * @return
     */
    @Override
    public int selectgdIdByDeliveryNumber(String deliveryNumber) {
        return goodsDetailsMapper.selectgdIdByDeliveryNumber(deliveryNumber);
    }

    /**
     * 修改货物打包状态 及打包操作人
     * 张占伟
     *
     * @param goodsDetails
     */
    @Override

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void packgingGoods(GoodsDetails goodsDetails) {
        goodsDetailsMapper.updateGoodsPacking(goodsDetails);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectTransshipmentGoodsByUserId(Integer appUserId, String goodState, String transportType, String deliveryNumber) {

            String transportType1 = "组合子转运";
            String goodState1 = "异常货物";
            return goodsDetailsMapper.selectTransshipmentGoodsByUserId(appUserId, goodState, transportType, deliveryNumber, transportType1, goodState1);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountTransshipmentGoodsByUserId(Integer appUserId, String goodState, String transportType, String deliveryNumber) {

            String transportType1 = "组合子转运";
            String goodState1 = "异常货物";
            return goodsDetailsMapper.selectCountTransshipmentGoodsByUserId(appUserId, goodState, transportType, deliveryNumber, transportType1, goodState1);


    }

    /**
     * 修改货物运输状态根据货物id
     * 张占伟
     *
     * @param details
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsState(GoodsDetails details) {
        goodsDetailsMapper.updateGoodsState(details);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectTransshipmentGoodsListByParentId(Integer parentId) {

            return goodsDetailsMapper.selectTransshipmentGoodsListByParentId(parentId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGoodsInfoByDeliveryNumber(String deliveryNumber) {

            return goodsDetailsMapper.selectGoodsInfoByDeliveryNumber(deliveryNumber);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectTransshipmentGoodsList(GoodsDetails goodsDetails) {

            HashMap<String, Object> map = new HashMap<>();
            map.put("deliveryNumber", goodsDetails.getDeliveryNumber());
            map.put("receiptContact", goodsDetails.getReceiptContact());
            map.put("addTime", goodsDetails.getAddTime());
            map.put("endTime", goodsDetails.getEndTime());
            map.put("paymentStatus", goodsDetails.getPaymentStatus());
            map.put("transportType", goodsDetails.getTransportType());
            map.put("from", "转运订单");
            return goodsDetailsMapper.selectTransshipmentGoodsList(map);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectTransshipmentGoodsListCount(GoodsDetails goodsDetails) {

            HashMap<String, Object> map = new HashMap<>();
            map.put("deliveryNumber", goodsDetails.getDeliveryNumber());
            map.put("receiptContact", goodsDetails.getReceiptContact());
            map.put("addTime", goodsDetails.getAddTime());
            map.put("endTime", goodsDetails.getEndTime());
            map.put("paymentStatus", goodsDetails.getPaymentStatus());
            map.put("transportType", goodsDetails.getTransportType());
            map.put("from", "转运订单");
            return goodsDetailsMapper.selectTransshipmentGoodsListCount(map);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectTransshipmentGoodsDetails(int goodsId) {

            return goodsDetailsMapper.selectTransshipmentGoodsDetails(goodsId);

    }

    /**
     * 根据货袋id查出货袋里货物详情
     *
     * @param bagId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> getGoodsInBagDetailsByBagId(int bagId) {
        return goodsDetailsMapper.getGoodsInBagDetailsByBagId(bagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectGoodsDetailByIds(Integer[] goodsIds) {

            List<GoodsDetails> goodsDetailsList = new ArrayList<>();
            for (int i = 0; i < goodsIds.length; i++) {
                goodsDetailsList.add(goodsDetailsMapper.selectTransshipmentGoodsDetails(goodsIds[i]));
            }
            return goodsDetailsList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGoodsDetailsById(int goodsId) {
        goodsMapper.deleteByPrimaryKey(goodsId);
        goodsDetailsMapper.deleteGoodsDetailsByGoodsId(goodsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGoodsByTripartiteNumber(String tripartiteNumber) {

            return goodsDetailsMapper.selectGoodsByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectTransshipmentGoodsStateList(String createTime, Integer singaporeAreaId, Integer rallyPointId, Integer exitWayNumber) {

            String exitWay = null;
            if (exitWayNumber != null) {
                if (exitWayNumber == 1) {
                    exitWay = "空运";
                } else if (exitWayNumber == 2) {
                    exitWay = "海运";
                }
            }
            String goodState = "待集运";
            String warningState = "无异常";
            HashMap<String, Object> map = new HashMap<>();
            map.put("createTime", createTime);
            map.put("singaporeAreaId", singaporeAreaId);
            map.put("rallyPointId", rallyPointId);
            if (exitWay != null) {
                map.put("exitWay", exitWay);
            }
            map.put("goodState", goodState);
            map.put("warningState", warningState);
            return goodsDetailsMapper.selectTransshipmentGoodsStateList(map);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGoodsPrint() {

            GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsPrint();
            if(goodsDetails!=null&&goodsDetails.getTotalGoods()!=null){
                if(goodsDetails.getTotalGoods()>1){
                    String[] strs = goodsDetails.getDeliveryNumber().split("-");
                    goodsDetails.setOrder(Integer.parseInt(strs[1]));
                }else{
                    goodsDetails.setOrder(0);
                }
            }
            return goodsDetails;

    }

    /**
     * 查询货物运费支付状态
     * 张占伟
     *
     * @param deliveryNumber
     * @param appUserId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDetails selectGooodsPayStateByNOAndUID(String deliveryNumber, int appUserId) {
        return goodsDetailsMapper.selectGooodsPayStateByNOAndUID(deliveryNumber, appUserId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsPayState(GoodsDetails details) {
        goodsDetailsMapper.updateGoodsPayState(details);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectAPPUIdByGdId(Integer goodsId) {
        return goodsDetailsMapper.selectAPPUIdByGdId(goodsId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertCombinedTransport(int appUserId, Integer[] goodsIds,int WarehouseId) {
        //获取所有货物详情
        List<GoodsDetails> goodsDetailsList = new ArrayList<>();
        for (int i = 0; i < goodsIds.length; i++) {
            goodsDetailsList.add(goodsDetailsMapper.selectTransshipmentGoodsDetails(goodsIds[i]));
        }
        boolean flag = true;
        for (GoodsDetails goodsDetails : goodsDetailsList
        ) {
            if (!(goodsDetails.getTransportType().equals("普通转运"))) {
                flag = false;
            }
        }
        Goods goods = new Goods();
        goods.setFrom("转运订单");
        String delliveryNumber = "04" + getRandomCode();
        goods.setDeliveryNumber(delliveryNumber);
        goods.setStatus((byte) 1);
        goods.setAddTime(new Date());
        goods.setTransportType("组合转运");
        goods.setWarehouseId(WarehouseId);
        // goods.setIsPrint(1);
        goodsMapper.insert(goods);
        Goods goods1 = goodsMapper.selectGoodsByDeliveryNumber(delliveryNumber);
        GoodsDetails goodsDetails1 = new GoodsDetails();
        goodsDetails1.setGoodsId(goods1.getGoodsId());
        goodsDetails1.setWarningState("无异常");
        goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
        goodsDetails1.setAppUserId(appUserId);
        goodsDetails1.setGoodState("已到仓");
        String category = "普通";
        for (int i = 0; i < goodsIds.length; i++) {
            GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goodsIds[i]);
            if (goodsDetails.getCategory() != null && goodsDetails.getCategory().equals("敏感")) {
                category = "敏感";
                break;
            } else if (goodsDetails.getCategory() != null && goodsDetails.getCategory().equals("Sensitive")) {
                category = "敏感";
                break;
            }
        }
        goodsDetails1.setCategory(category);
        goodsDetailsMapper.insert(goodsDetails1);
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(goods1.getGoodsId());
        logisticInfo.setCreateTime(new Date());
        logisticInfo.setOperateResult("合并的转运货物");
        logisticInfo.setOperateComment("合并的转运货物");
        logisticInfo.setOperateType("合并的转运货物");
        logisticInfo.setAppUserId(appUserId);
        AppUser appUser = appUserMapper.selectAppUserName(appUserId);
        if (appUser != null && appUser.getUserName() != null) {
            logisticInfo.setOperateName(appUser.getUserName());
        }
        logisticInfoMapper.insert(logisticInfo);
        if (flag) {
            for (int i = 0; i < goodsIds.length; i++) {
                Goods goods2 = goodsMapper.selectGoodsById(goodsIds[i]);
                goods2.setUpdateTime(goods.getAddTime());
                goods2.setParentId(goods1.getGoodsId());
                goods2.setMergeNumber(goods.getDeliveryNumber());
                goods2.setTransportType("组合子转运");
                goodsMapper.updateByPrimaryKey(goods2);
                LogisticInfo logisticInfo1 = new LogisticInfo();
                logisticInfo1.setGoodsId(goodsIds[i]);
                logisticInfo1.setCreateTime(new Date());
                logisticInfo1.setOperateResult("快递单号为:" + goods2.getDeliveryNumber() + "的货物提交合并转运，合并后快递单号为:" + goods2.getMergeNumber());
                logisticInfo1.setOperateComment("货物提交合并转运");
                logisticInfo1.setOperateType("货物提交合并转运");
                logisticInfo1.setAppUserId(appUserId);
                AppUser appUser1 = appUserMapper.selectAppUserName(appUserId);
                if (appUser1 != null && appUser1.getUserName() != null) {
                    logisticInfo1.setOperateName(appUser1.getUserName());
                }
                logisticInfoMapper.insert(logisticInfo1);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateAppUserAddress(AppUserAddress appUserAddress, Integer goodsId) {
        Goods goods = goodsMapper.selectGoodsById(goodsId);
        if (goods.getTransportType().equals("普通转运")) {
            goods.setZipCode(appUserAddress.getZipCode());//必填
            goods.setUpdateTime(new Date());
            goodsMapper.updateByPrimaryKey(goods);
            GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goodsId);
            goodsDetails.setReceiptAddress(appUserAddress.getDetailedAddress());
            goodsDetails.setReceiptContact(appUserAddress.getName());
            goodsDetails.setReceiptContactMobile(appUserAddress.getMobile());
            goodsDetails.setUpdateTime(goods.getUpdateTime());
            SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(appUserAddress.getZipCode());
            if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
            }
            if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
            }
            goodsDetailsMapper.updateByPrimaryKey(goodsDetails);
        } else {
            List<Goods> goodsList = goodsMapper.selectGoodsByParentId(goodsId);
            for (Goods goods1 : goodsList
            ) {
                goods1.setZipCode(appUserAddress.getZipCode());//必填
                goods1.setUpdateTime(new Date());
                goodsMapper.updateByPrimaryKey(goods1);
                GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goods1.getGoodsId());
                goodsDetails1.setReceiptAddress(appUserAddress.getDetailedAddress());
                goodsDetails1.setReceiptContact(appUserAddress.getName());
                goodsDetails1.setReceiptContactMobile(appUserAddress.getMobile());
                goodsDetails1.setUpdateTime(goods1.getUpdateTime());
                SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(appUserAddress.getZipCode());
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                    goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                }
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                    goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                }
                goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
            }
            goods.setZipCode(appUserAddress.getZipCode());//必填
            goods.setUpdateTime(new Date());
            goodsMapper.updateByPrimaryKey(goods);
            GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goodsId);
            goodsDetails1.setReceiptAddress(appUserAddress.getDetailedAddress());
            goodsDetails1.setReceiptContact(appUserAddress.getName());
            goodsDetails1.setReceiptContactMobile(appUserAddress.getMobile());
            goodsDetails1.setUpdateTime(goods.getUpdateTime());
            SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(appUserAddress.getZipCode());
            if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
            }
            if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
            }
            goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updategoodsDetailsList(List<GoodsDetails> goodsDetailsList, Integer goodsId) {
        BigDecimal amount = new BigDecimal(0);
        BigDecimal volume = new BigDecimal(0);
        BigDecimal totalactualWeight = new BigDecimal(0);
        BigDecimal gstPrice = new BigDecimal(0);
        double itemValuetotal = 0;

        GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goodsId);
        Goods goods = goodsMapper.selectGoodsById(goodsId);

        goods.setUpdateTime(new Date());
        Float totalactualLong = 0f;
        Float totalctualWidth = 0f;
        Float totalactualHeight = 0f;
        String category = "普通";
        for (GoodsDetails goodsDetails : goodsDetailsList
        ) {
            if (goodsDetails.getCategory().equals("敏感")) {
                //获取物品分类
                category = goodsDetails.getCategory();
            } else if (goodsDetails.getCategory().equals("Sensitive")) {
                category = "敏感";
            }
        }
        for (GoodsDetails goodsDetails : goodsDetailsList
        ) {
            String isUrgent = "否";
            //获取是否紧急
            switch (goodsDetails.getIsUrgentNumber()) {
                case 11:
                    isUrgent = "是";
                    break;
                case 12:
                    isUrgent = "否";
                    break;
            }
            if (goodsDetails1.getTransportType().equals("普通转运")) {
                if (goodsDetails1.getActualLong() != null && goodsDetails1.getActualLong() > 0 && goodsDetails1.getActualWidth() != null && goodsDetails1.getActualWidth() > 0
                        && goodsDetails1.getActualHeight() != null && goodsDetails1.getActualHeight() > 0 && goodsDetails1.getActualWeight() != null && goodsDetails1.getActualWeight() > 0) {
                    //获取长 单位为㎝换算成m
                    Float actualLong = goodsDetails1.getActualLong() / 100;
                    //获取宽
                    Float ActualWidth = goodsDetails1.getActualWidth() / 100;
                    //获取高
                    Float actualHeight = goodsDetails1.getActualHeight() / 100;
                    //获取重 kg
                    Float actualWeight = goodsDetails1.getActualWeight();
                    if (goodsDetails.getExitWayNumber() == 1) {
                        amount = getAirFreightPrice(actualLong, ActualWidth, actualHeight, actualWeight, category, isUrgent);
                        goods.setExitWay("空运");
                        goods.setAmount(amount);
                        goodsMapper.updateByPrimaryKey(goods);
                        if(goodsDetails.getValueUnit().equals("美元USD")){
                            //美元转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),21));
                        }
                        if (goodsDetails.getValueUnit().equals("新加坡元SGD")){
                           //新币转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),31));
                        }
                        //新币换算成人民币
                        BigDecimal gstPrice2 = ExchangeRateUtil.rateExchange(new BigDecimal(StaticConfigUtil.GSTPrice),31);
                        double gstPrice1 = Double.parseDouble(gstPrice2.toString());
                        double rate = StaticConfigUtil.rate;
                        double permit = StaticConfigUtil.permit;
                        if (goodsDetails.getItemValue().doubleValue() > gstPrice1) {
                            double taxPrice1 = (goodsDetails.getItemValue().doubleValue() * rate) + permit;
                            BigDecimal taxPrice2 = new BigDecimal(Double.toString(taxPrice1));
                            BigDecimal totalprice1 = taxPrice2.setScale(2, RoundingMode.HALF_UP);
                            //人民币转换成美元
                            gstPrice = ExchangeRateUtil.rateExchange(totalprice1,12);
                        }
                    } else if (goodsDetails.getExitWayNumber() == 2) {
                        if(goodsDetails.getValueUnit().equals("美元USD")){
                            //美元转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),21));
                        }
                        if (goodsDetails.getValueUnit().equals("新加坡元SGD")){
                            //新币转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),31));
                        }
                        amount = getShippingPrice(actualLong, ActualWidth, actualHeight, category, isUrgent);
                        goods.setExitWay("海运");
                        goods.setAmount(amount);
                        goodsMapper.updateByPrimaryKey(goods);
                    }
                    goodsDetails1.setCategory(category);
                    goodsDetails1.setIsUrgent(isUrgent);
                    goodsDetails1.setGstPrice(gstPrice);
                    goodsDetails1.setItemName(goodsDetails.getItemName());
                    goodsDetails1.setItemExplain(goodsDetails.getItemExplain());
                    //人民币转美元
                    goodsDetails1.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),12));
                    goodsDetails1.setUpdateTime(goods.getUpdateTime());
                    goodsDetails1.setPaymentStatus("待支付");
                    goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
                    goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
                    goodsDetails1.setIsArrivalPay(1);
                    goodsDetailsMapper.updateGoodsDetailsAllInfo(goodsDetails1);
                } else {
                    return "长宽高重量不能小于等于零或为空";
                }
            } else {
                GoodsDetails goodsDetails2 = goodsDetailsMapper.selectGoodsDetailById(goodsDetails.getGoodsId());
                if (goodsDetails2.getActualLong() != null && goodsDetails2.getActualLong() > 0 && goodsDetails2.getActualWidth() != null && goodsDetails2.getActualWidth() > 0 && goodsDetails2.getActualHeight() != null && goodsDetails2.getActualHeight() > 0 && goodsDetails2.getActualWeight() != null && goodsDetails2.getActualWeight() > 0) {
                    boolean flag = false;
                    if ((goodsDetails2.getActualLong() / 100) > 1.5 || (goodsDetails2.getActualWidth() / 100) > 1.5 || (goodsDetails2.getActualHeight() / 100) > 1.5) {
                        flag = true;
                    }
                    totalactualLong = goodsDetails2.getActualLong() / 100;
                    totalctualWidth = goodsDetails2.getActualWidth() / 100;
                    totalactualHeight = goodsDetails2.getActualHeight() / 100;
                    BigDecimal actualLong1 = new BigDecimal(Float.toString(totalactualLong));
                    BigDecimal actualWidth1 = new BigDecimal(Float.toString(totalctualWidth));
                    BigDecimal actualHeight1 = new BigDecimal(Float.toString(totalactualHeight));
                    volume = volume.add((actualLong1.multiply(actualWidth1)).multiply(actualHeight1));
                    //获取重 kg
                    totalactualWeight = totalactualWeight.add(new BigDecimal(goodsDetails2.getActualWeight()));
                    if (goodsDetails.getExitWayNumber() == 1) {
                        amount = getAirFreightMergePrice(volume, totalactualWeight, flag, category, isUrgent);
                        goods.setExitWay("空运");
                        if(goodsDetails.getValueUnit().equals("美元USD")){
                            //美元转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),21));
                        }
                        if (goodsDetails.getValueUnit().equals("新加坡元SGD")){
                            //新币转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),31));
                        }
                        itemValuetotal = itemValuetotal + goodsDetails.getItemValue().doubleValue();
                        //新币换算成人民币
                        BigDecimal gstPrice2 = ExchangeRateUtil.rateExchange(new BigDecimal(StaticConfigUtil.GSTPrice),31);
                        double gstPrice1 = Double.parseDouble(gstPrice2.toString());
                        double rate = StaticConfigUtil.rate;
                        double permit = StaticConfigUtil.permit;
                        if (itemValuetotal > gstPrice1) {
                            double taxPrice1 = (itemValuetotal * rate) + permit;
                            BigDecimal taxPrice2 = new BigDecimal(Double.toString(taxPrice1));
                            BigDecimal totalprice1 = taxPrice2.setScale(2, RoundingMode.HALF_UP);
                            //人民币转换成美元
                            gstPrice = ExchangeRateUtil.rateExchange(totalprice1,12);
                        }
                    } else if (goodsDetails.getExitWayNumber() == 2) {
                        if(goodsDetails.getValueUnit().equals("美元USD")){
                            //美元转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),21));
                        }
                        if (goodsDetails.getValueUnit().equals("新加坡元SGD")){
                            //新币转为人民币
                            goodsDetails.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),31));
                        }
                        itemValuetotal = itemValuetotal + goodsDetails.getItemValue().doubleValue();
                        amount = getShippingMergePrice(volume, category, isUrgent);
                        goods.setExitWay("海运");
                    }
                    goodsDetails2.setCategory(goodsDetails.getCategory());
                    goodsDetails2.setIsUrgent(isUrgent);
                    goodsDetails2.setItemName(goodsDetails.getItemName());
                    goodsDetails2.setItemExplain(goodsDetails.getItemExplain());
                    goodsDetails2.setItemValue(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),12));
                    goodsDetails2.setUpdateTime(goods.getUpdateTime());
                    goodsDetails2.setSingaporeAreaId(goodsDetails2.getSingaporeArea().getSingaporeAreaId());
                    goodsDetails2.setRallyPointId(goodsDetails2.getRallyPoint().getRallyPointId());
                    goodsDetails2.setIsArrivalPay(1);
                    goodsDetailsMapper.updateGoodsDetailsInfoALL(goodsDetails2);
                } else {
                    return "长宽高重量不能小于等于零或为空";
                }
                goods.setAmount(amount);
                goods.setTotalGoods(1);
                goodsMapper.updateGoodsAmount(goods);
                goodsDetails1.setItemValue(ExchangeRateUtil.rateExchange(new BigDecimal(itemValuetotal),12));
                goodsDetails1.setGstPrice(gstPrice);
                goodsDetails1.setCategory(category);
                goodsDetails1.setIsUrgent(isUrgent);
                goodsDetails1.setUpdateTime(goods.getUpdateTime());
                goodsDetails1.setPaymentStatus("待支付");
                goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
                goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
                goodsDetails1.setIsArrivalPay(1);
                goodsDetailsMapper.updateGoodsDetailsAllInfo(goodsDetails1);
            }
        }
        return "1";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateGoodsAndGoodsDetails(PDAGoodsDto pdaGoodsDto) {
        Goods goods = goodsMapper.selectGoodsById(pdaGoodsDto.getGoodsId());
        goods.setStatus((byte) 1);
        if (pdaGoodsDto.getGoodType() != null) {
            goods.setGoodType(pdaGoodsDto.getGoodType());
        }
        goods.setIsSpecialGoods(pdaGoodsDto.getIsSpecialGoods());
        goods.setUpdateTime(new Date());
        WarehousePositions warehousePositions = warehousePositionsMapper.selectWarehousePositionsbyWpNumber(pdaGoodsDto.getIntoWpNumber());
        goods.setIntoWarehouseId(warehousePositions.getWarehouseId());
        goods.setIntoWarehousePositionsId(warehousePositions.getWpId());
        goods.setTransportType("普通转运");
        if (pdaGoodsDto.getUfSavePath() != null) {
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUfSavePath(pdaGoodsDto.getUfSavePath());
            uploadFiles.setType(8);
            uploadFilesMapper.insert(uploadFiles);
            Integer id = uploadFilesMapper.getLastId();
            goods.setGoodsUfId(id);
        }
        goodsMapper.updateGoods(goods);
        GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goods.getGoodsId());
        AppUser appUser = appUserMapper.selectAppUserByNumber(pdaGoodsDto.getNumber());
        goodsDetails1.setAppUserId(appUser.getAppUserId());
        if (pdaGoodsDto.getActualLong() != null && pdaGoodsDto.getActualLong() > 0 && pdaGoodsDto.getActualWidth() != null && pdaGoodsDto.getActualWidth() > 0
                && pdaGoodsDto.getActualHeight() != null && pdaGoodsDto.getActualHeight() > 0 && pdaGoodsDto.getActualWeight() != null && pdaGoodsDto.getActualWeight() > 0) {
            goodsDetails1.setActualLong(pdaGoodsDto.getActualLong());
            goodsDetails1.setActualWidth(pdaGoodsDto.getActualWidth());
            goodsDetails1.setActualHeight(pdaGoodsDto.getActualHeight());
            goodsDetails1.setActualWeight(pdaGoodsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        goodsDetails1.setWarningState("无异常");
        goodsDetails1.setGoodState("已到仓");
        goodsDetails1.setUpdateTime(goods.getUpdateTime());
        if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Sensitive")) {
            goodsDetails1.setCategory("敏感");
        } else if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Ordinary")) {
            goodsDetails1.setCategory("普通");
        } else if (pdaGoodsDto.getCategory() != null &&!(pdaGoodsDto.getCategory().equals(""))) {
            goodsDetails1.setCategory(pdaGoodsDto.getCategory());
        }
        goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
        return "1";

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateGoodsDetailsAndGoods(PDAGoodsDto pdaGoodsDto) {
        Goods goods = goodsMapper.selectGoodsById(pdaGoodsDto.getGoodsId());
        goods.setIsSpecialGoods(pdaGoodsDto.getIsSpecialGoods());
        goods.setUpdateTime(new Date());
        goods.setIsPrint(1);
        if (pdaGoodsDto.getUfSavePath() != null) {
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUfSavePath(pdaGoodsDto.getUfSavePath());
            uploadFiles.setType(8);
            uploadFilesMapper.insert(uploadFiles);
            Integer id = uploadFilesMapper.getLastId();
            goods.setGoodsUfId(id);
        }
        goodsMapper.updateGoods(goods);
        GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(pdaGoodsDto.getGoodsId());
        if (pdaGoodsDto.getActualLong() != null && pdaGoodsDto.getActualLong() > 0 && pdaGoodsDto.getActualWidth() != null && pdaGoodsDto.getActualWidth() > 0
                && pdaGoodsDto.getActualHeight() != null && pdaGoodsDto.getActualHeight() > 0 && pdaGoodsDto.getActualWeight() != null && pdaGoodsDto.getActualWeight() > 0) {
            goodsDetails1.setActualLong(pdaGoodsDto.getActualLong());
            goodsDetails1.setActualWidth(pdaGoodsDto.getActualWidth());
            goodsDetails1.setActualHeight(pdaGoodsDto.getActualHeight());
            goodsDetails1.setActualWeight(pdaGoodsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        goodsDetails1.setUpdateTime(goods.getUpdateTime());
        if (goodsDetails1.getSingaporeArea() != null && goodsDetails1.getSingaporeArea().getSingaporeAreaId() != null) {
            goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
        }
        if (goodsDetails1.getRallyPoint() != null && goodsDetails1.getRallyPoint().getRallyPointId() != null) {
            goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
        }
        if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Sensitive")) {
            goodsDetails1.setCategory("敏感");
        } else if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Ordinary")) {
            goodsDetails1.setCategory("普通");
        } else if (pdaGoodsDto.getCategory() != null &&!(pdaGoodsDto.getCategory().equals(""))) {
            goodsDetails1.setCategory(pdaGoodsDto.getCategory());
        }
        goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(pdaGoodsDto.getGoodsId());
        logisticInfo.setCreateTime(new Date());
        Staff staff = staffMapper.selectStaffDetail(pdaGoodsDto.getStaffId());
        if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
            logisticInfo.setOperateResult(staff.getAttribution()+"发货仓：揽收入库成功，待打包");
        }else if(staff!=null&&staff.getAttribution()!=null){
            logisticInfo.setOperateResult(staff.getAttribution()+"：揽收入库成功，待打包");
        }
        if (staff != null) {
            logisticInfo.setOperateName(staff.getStaffName());
        }
        logisticInfo.setOperateComment("货物入库成功");
        logisticInfo.setOperateType("货物入库成功");
        logisticInfo.setStaffId(pdaGoodsDto.getStaffId());
        logisticInfoMapper.insert(logisticInfo);
        return "1";

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectGoodsDetailByTripartiteNumber(String tripartiteNumber) {
            return goodsDetailsMapper.selectGoodsDetailByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateGoodsDetailsByOrder(PDAGoodsDto pdaGoodsDto) {
        Goods goods = goodsMapper.selectGoodsById(pdaGoodsDto.getGoodsId());
        Goods goods1 = goodsMapper.selectGoodsInfoByDeliveryNumber(goods.getDeliveryNumber() + "-" + pdaGoodsDto.getOrder());
        goods1.setIsSpecialGoods(pdaGoodsDto.getIsSpecialGoods());
        goods1.setUpdateTime(new Date());
        goods1.setIsPrint(1);
        if (pdaGoodsDto.getUfSavePath() != null) {
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUfSavePath(pdaGoodsDto.getUfSavePath());
            uploadFiles.setType(8);
            uploadFilesMapper.insert(uploadFiles);
            Integer id = uploadFilesMapper.getLastId();
            goods1.setGoodsUfId(id);
        }
        goodsMapper.updateGoods(goods1);
        GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goods1.getGoodsId());
        if (pdaGoodsDto.getActualLong() != null && pdaGoodsDto.getActualLong() > 0 && pdaGoodsDto.getActualWidth() != null && pdaGoodsDto.getActualWidth() > 0
                && pdaGoodsDto.getActualHeight() != null && pdaGoodsDto.getActualHeight() > 0 && pdaGoodsDto.getActualWeight() != null && pdaGoodsDto.getActualWeight() > 0) {
            goodsDetails1.setActualLong(pdaGoodsDto.getActualLong());
            goodsDetails1.setActualWidth(pdaGoodsDto.getActualWidth());
            goodsDetails1.setActualHeight(pdaGoodsDto.getActualHeight());
            goodsDetails1.setActualWeight(pdaGoodsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        goodsDetails1.setUpdateTime(goods.getUpdateTime());
        goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
        goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
        if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Sensitive")) {
            goodsDetails1.setCategory("敏感");
        } else if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Ordinary")) {
            goodsDetails1.setCategory("普通");
        } else if (pdaGoodsDto.getCategory() != null) {
            goodsDetails1.setCategory(pdaGoodsDto.getCategory());
        }
        goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(goods1.getGoodsId());
        logisticInfo.setCreateTime(new Date());
        Staff staff = staffMapper.selectStaffDetail(pdaGoodsDto.getStaffId());
        if(staff!=null&&staff.getAttribution()!=null&&staff.getAttribution().equals("新加坡")){
            logisticInfo.setOperateResult(staff.getAttribution()+"发货仓：揽收入库成功，待打包");
        }else if(staff!=null&&staff.getAttribution()!=null){
            logisticInfo.setOperateResult(staff.getAttribution()+"：揽收入库成功，待打包");
        }
        if (staff != null) {
            logisticInfo.setOperateName(staff.getStaffName());
        }
        logisticInfo.setOperateComment("货物入库成功");
        logisticInfo.setOperateType("货物入库成功");
        logisticInfo.setStaffId(pdaGoodsDto.getStaffId());
        logisticInfoMapper.insert(logisticInfo);
        return "1";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(GoodsDetails goodsDetails) {
        goodsDetailsMapper.insert(goodsDetails);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertGoodsDetails(GoodsDetails goodsDetails2, int oldtotalGoods, int i) {
        Goods goods = new Goods();
        goods.setFrom("三方货单");
        goods.setStatus((byte) 1);
        boolean flag = true;
        while (flag) {
            Goods goods1 = goodsMapper.selectGoodsByDeliveryNumber(goodsDetails2.getDeliveryNumber() + "-" + i);
            if (goods1 == null) {
                goods.setDeliveryNumber(goodsDetails2.getDeliveryNumber() + "-" + i);
                flag = false;
            } else {
                i++;
            }
        }
        goods.setTripartiteNumber(goodsDetails2.getTripartiteNumber());
        goods.setZipCode(goodsDetails2.getZipCode());
        if (goodsDetails2.getGoodType() != null) {
            goods.setGoodType(goodsDetails2.getGoodType());
        }
        goods.setAddTime(new Date());
        goods.setExitWay(goodsDetails2.getExitWay());
        goods.setTotalGoods(1);
        goodsMapper.insert(goods);
        GoodsDetails goodsDetails4 = new GoodsDetails();
        int goodsId = goodsMapper.selectGoodsId();
        goodsDetails4.setGoodsId(goodsId);
        if (goodsDetails2.getTripartiteLong() != null) {
            goodsDetails4.setTripartiteLong(goodsDetails2.getTripartiteLong());
        }
        if (goodsDetails2.getTripartiteWidth() != null) {
            goodsDetails4.setTripartiteWidth(goodsDetails2.getTripartiteWidth());
        }
        if (goodsDetails2.getTripartiteHeight() != null) {
            goodsDetails4.setTripartiteHeight(goodsDetails2.getTripartiteHeight());
        }
        if (goodsDetails2.getTripartiteWeight() != null) {
            goodsDetails4.setTripartiteWeight(goodsDetails2.getTripartiteWeight());
        }
        if (goodsDetails2.getShipAddress() != null && !(goodsDetails2.getShipAddress().equals(""))) {
            goodsDetails4.setShipAddress(goodsDetails2.getShipAddress());
        } else {
            goodsDetails4.setShipAddress(null);
        }
        if (goodsDetails2.getShipContact() != null && !(goodsDetails2.getShipContact().equals(""))) {
            goodsDetails4.setShipContact(goodsDetails2.getShipContact());
        } else {
            goodsDetails4.setShipContact(null);
        }
        if (goodsDetails2.getShipContactMobile() != null && !(goodsDetails2.getShipContact().equals(""))) {
            goodsDetails4.setShipContactMobile(goodsDetails2.getShipContactMobile());
        } else {
            goodsDetails4.setShipContactMobile(null);
        }
        if (goodsDetails2.getReceiptAddress() != null && !(goodsDetails2.getReceiptAddress().equals(""))) {
            goodsDetails4.setReceiptAddress(goodsDetails2.getReceiptAddress());
        } else {
            goodsDetails4.setReceiptAddress(null);
        }
        if (goodsDetails2.getReceiptContact() != null && !(goodsDetails2.getReceiptContact().equals(""))) {
            goodsDetails4.setReceiptContact(goodsDetails2.getReceiptContact());
        } else {
            goodsDetails4.setReceiptContact(null);
        }
        if (goodsDetails2.getReceiptContactMobile() != null && !(goodsDetails2.getReceiptContactMobile().equals(""))) {
            goodsDetails4.setReceiptContactMobile(goodsDetails2.getReceiptContactMobile());
        } else {
            goodsDetails4.setReceiptContactMobile(null);
        }
        if (goodsDetails2.getWarningState() != null && !(goodsDetails2.getWarningState().equals(""))) {
            goodsDetails4.setWarningState(goodsDetails2.getWarningState());
        }
        if (goodsDetails2.getAbnormalText() != null && !(goodsDetails2.getAbnormalText().equals(""))) {
            goodsDetails4.setAbnormalText(goodsDetails2.getAbnormalText());
        }
        goodsDetails4.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
        goodsDetails4.setWarningState("无异常");

        if (goodsDetails2.getSingaporeArea().getSingaporeAreaId() != null) {
            goodsDetails4.setSingaporeAreaId(goodsDetails2.getSingaporeArea().getSingaporeAreaId());
        }
        if (goodsDetails2.getRallyPoint().getRallyPointId() != null) {
            goodsDetails4.setRallyPointId(goodsDetails2.getRallyPoint().getRallyPointId());
        }
        goodsDetailsMapper.insert(goodsDetails4);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArrayList<GoodsDetails> selectGoodsInfoByBagNumber(String wildMatch) {
        return (ArrayList<GoodsDetails>) goodsDetailsMapper.selectGoodsInfoByBagNumber(wildMatch);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateIsReceiveGoods(int goodsId) {
        Date date = new Date();
        goodsDetailsMapper.updateIsReceiveGoods(goodsId, date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GoodsDetails> selectGoodsDetailListByTripartiteNumber(String tripartiteNumber) {

            return goodsDetailsMapper.selectGoodsDetailListByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PeopleGoods calculateGoodsPrice(String goodsIds) {
            BigDecimal COD = new BigDecimal(0.00);
            BigDecimal GST = new BigDecimal(0.00);
            BigDecimal totalCodGst = new BigDecimal(0.00);
            String[] goodsIds1 = goodsIds.split(",");
            List<String> deliveryNumbers = new ArrayList<>();
            for (int i = 0; i < goodsIds1.length; i++) {
                int goodsId = Integer.parseInt(goodsIds1[i]);
                GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goodsId);
                if (goodsDetails.getDeliveryNumber().indexOf("-") != -1) {
                    String deliveryNumber = StringUtils.substringBefore(goodsDetails.getDeliveryNumber(), "-");
                    if (deliveryNumbers.size() >= 0) {
                        deliveryNumbers.add(deliveryNumber);
                    }
                    GST = GST.add(ExchangeRateUtil.rateExchange(goodsDetails.getGstPrice(),23));
                } else {
                    if (goodsDetails.getIsArrivalPay() == 2) {
                        COD = COD.add(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),23));
                    }
                    GST = GST.add(ExchangeRateUtil.rateExchange(goodsDetails.getGstPrice(),23));
                }
            }
            HashSet hashSet = new HashSet(deliveryNumbers);
            deliveryNumbers.clear();
            deliveryNumbers.addAll(hashSet);
            for (String deliveryNumber : deliveryNumbers
            ) {
                GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailsByDeliveryNumber(deliveryNumber);
                if (goodsDetails.getIsArrivalPay() == 2 && goodsDetails.getIsReceiveGoods() == 2) {
                    COD = COD.add(ExchangeRateUtil.rateExchange(goodsDetails.getItemValue(),23));
                }

            }
            totalCodGst = COD.add(GST);
            PeopleGoods peopleGoods = new PeopleGoods();
            peopleGoods.setCOD(COD);
            peopleGoods.setGST(GST);
            peopleGoods.setTotalCodGst(totalCodGst);
            return peopleGoods;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertGoodsAndGoodsDetails(PDAGoodsDto pdaGoodsDto) {
        Goods goods = new Goods();
        goods.setFrom("转运订单");
        goods.setDeliveryNumber(pdaGoodsDto.getDeliveryNumber());
        goods.setTripartiteNumber(pdaGoodsDto.getTripartiteNumber());
        goods.setGoodType(pdaGoodsDto.getGoodType());
        goods.setStatus((byte) 1);
        goods.setIsSpecialGoods(pdaGoodsDto.getIsSpecialGoods());
        goods.setUpdateTime(new Date());
        WarehousePositions warehousePositions = warehousePositionsMapper.selectWarehousePositionsbyWpNumber(pdaGoodsDto.getIntoWpNumber());
        goods.setIntoWarehouseId(warehousePositions.getWarehouseId());
        goods.setIntoWarehousePositionsId(warehousePositions.getWpId());
        goods.setTransportType("普通转运");
        if (pdaGoodsDto.getUfSavePath() != null) {
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUfSavePath(pdaGoodsDto.getUfSavePath());
            uploadFiles.setType(8);
            uploadFilesMapper.insert(uploadFiles);
            Integer id = uploadFilesMapper.getLastId();
            goods.setGoodsUfId(id);
        }
        goods.setIsPrint(1);
        goods.setAddTime(new Date());
        goods.setTotalGoods(1);
        goods.setWarehouseId(warehousePositions.getWarehouseId());
        goodsMapper.insert(goods);
        GoodsDetails goodsDetails = new GoodsDetails();
        int goodsId = goodsMapper.selectGoodsId();
        goodsDetails.setGoodsId(goodsId);
        AppUser appUser = appUserMapper.selectAppUserByNumber(pdaGoodsDto.getNumber());
        goodsDetails.setAppUserId(appUser.getAppUserId());
        if (pdaGoodsDto.getActualLong() != null && pdaGoodsDto.getActualLong() > 0
                && pdaGoodsDto.getActualWidth() != null && pdaGoodsDto.getActualWidth() > 0
                && pdaGoodsDto.getActualHeight() != null && pdaGoodsDto.getActualHeight() > 0
                && pdaGoodsDto.getActualWeight() != null && pdaGoodsDto.getActualWeight() > 0) {
            goodsDetails.setActualLong(pdaGoodsDto.getActualLong());
            goodsDetails.setActualWidth(pdaGoodsDto.getActualWidth());
            goodsDetails.setActualHeight(pdaGoodsDto.getActualHeight());
            goodsDetails.setActualWeight(pdaGoodsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        goodsDetails.setWarningState("无异常");
        goodsDetails.setGoodState("已到仓");
        goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
        if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Sensitive")) {
            goodsDetails.setCategory("敏感");
        } else if (pdaGoodsDto.getCategory() != null && pdaGoodsDto.getCategory().equals("Ordinary")) {
            goodsDetails.setCategory("普通");
        } else {
            goodsDetails.setCategory(pdaGoodsDto.getCategory());
        }
        goodsDetailsMapper.insert(goodsDetails);
        return ""+goodsId ;
    }

    @Override
    public List<GoodsDetails> selectGoodsDetailListByGoodsIds(Set<Integer> goodsIds) {
        try{
           return goodsDetailsMapper.selectGoodsDetailListByGoodsIds(goodsIds);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}

