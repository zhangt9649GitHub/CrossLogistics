package com.siruiman.crosslogistics.service.impl;


import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.GoodsWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsWarningServiceImpl implements GoodsWarningService {
    @Autowired
    private GoodsWarningMapper goodsWarningMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private TruckProblemPieceMapper truckProblemPieceMapper;
    @Autowired
    private TruckDeliveryAssistantMapper truckDeliveryAssistantMapper;
    @Autowired
    private TaskOrderBagMapper taskOrderBagMapper;
    @Autowired
    private  TaskTruckOrderMapper taskTruckOrderMapper;

    @Override
    public List<GoodsWarning> selectGoodsWarningList(GoodsWarning goodsWarning) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("deliveryNumber", goodsWarning.getDeliveryNumber());
            map.put("tripartiteNumber", goodsWarning.getTripartiteNumber());
            map.put("goodType", goodsWarning.getGoodType());
            map.put("warningState", goodsWarning.getWarningState());
            return goodsWarningMapper.selectGoodsWarningList(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectCountGoodsWarningList(GoodsWarning goodsWarning) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("deliveryNumber", goodsWarning.getDeliveryNumber());
            map.put("tripartiteNumber", goodsWarning.getTripartiteNumber());
            map.put("goodType", goodsWarning.getGoodType());
            map.put("warningState", goodsWarning.getWarningState());
            return goodsWarningMapper.selectCountGoodsWarningList(map);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public GoodsWarning selectGoodsWarningByGoodsId(Integer goodsId) {
        try {
            return goodsWarningMapper.selectGoodsWarningByGoodsId(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsWarning(GoodsWarning goodsWarning) {
        goodsWarningMapper.updateByPrimaryKey(goodsWarning);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGoodsWarningByGoodsId(Integer goodsId) {
        goodsWarningMapper.deleteGoodsWarningByGoodsId(goodsId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertGoodsWarning(GoodsWarning goodsWarning) {
        goodsWarningMapper.insert(goodsWarning);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsWarningDetails(GoodsDetails goodsDetails) {
        if (!(goodsDetails.getWarningState().equals("无异常"))) {
            Goods goods = goodsMapper.selectGoodsById(goodsDetails.getGoodsId());
            if (goodsDetails.getZipCode() != null && !(goodsDetails.getZipCode().equals(""))) {
                goods.setZipCode(goodsDetails.getZipCode());
            } else {
                goods.setZipCode(null);
            }
            if (goodsDetails.getGoodType() != null && !(goodsDetails.getGoodType().equals(""))) {
                goods.setGoodType(goodsDetails.getGoodType());
            } else {
                goods.setGoodType(null);
            }
            goods.setTripartiteNumber(goodsDetails.getTripartiteNumber());
            goods.setUpdateTime(new Date());
            if(goodsDetails.getBagId()!=null){
                goods.setBagId(goodsDetails.getBagId());
            }
            goodsMapper.updateByPrimaryKey(goods);
            GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goodsDetails.getGoodsId());
              /*  if(goodsDetails.getActualLong() instanceof Float&&goodsDetails.getActualLong() > 0f){
                    goodsDetails1.setActualLong(goodsDetails.getActualLong());
                }else if(goodsDetails.getActualLong() <= 0f){
                    return "货物长度不能为负数或者等于O";
                }
                if(goodsDetails.getActualWeight() instanceof Float && goodsDetails.getActualWeight() >0f){
                    goodsDetails1.setActualWeight(goodsDetails.getActualWeight());
                }else if(goodsDetails.getActualWeight() <= 0f){
                    return "货物重量不能为负数或者等于O";
                }
                if(goodsDetails.getActualWidth() instanceof Float && goodsDetails.getActualWidth() >0f ){
                    goodsDetails1.setActualWidth(goodsDetails.getActualWidth());
                }else if(goodsDetails.getActualWidth() <= 0f){
                    return "货物宽度不能为负数或者等于O";
                }
                if(goodsDetails.getActualHeight() instanceof Float && goodsDetails.getActualHeight() >0f ){
                    goodsDetails1.setActualHeight(goodsDetails.getActualHeight());
                }else if(goodsDetails.getActualHeight() <= 0f){
                    return "货物高度不能为负数或者等于O";
                }*/
            if (goodsDetails.getShipAddress() != null && !(goodsDetails.getShipAddress().equals(""))) {
                goodsDetails1.setShipAddress(goodsDetails.getShipAddress());
            } else {
                goodsDetails1.setShipAddress(null);
            }
            if (goodsDetails.getShipContact() != null && !(goodsDetails.getShipContact().equals(""))) {
                goodsDetails1.setShipContact(goodsDetails.getShipContact());
            } else {
                goodsDetails1.setShipContact(null);
            }
            if (goodsDetails.getShipContactMobile() != null && !(goodsDetails.getShipContact().equals(""))) {
                goodsDetails1.setShipContactMobile(goodsDetails.getShipContactMobile());
            } else {
                goodsDetails1.setShipContactMobile(null);
            }
            if (goodsDetails.getReceiptAddress() != null && !(goodsDetails.getReceiptAddress().equals(""))) {
                goodsDetails1.setReceiptAddress(goodsDetails.getReceiptAddress());
            } else {
                goodsDetails1.setReceiptAddress(null);
            }
            if (goodsDetails.getReceiptContact() != null && !(goodsDetails.getReceiptContact().equals(""))) {
                goodsDetails1.setReceiptContact(goodsDetails.getReceiptContact());
            } else {
                goodsDetails1.setReceiptContact(null);
            }
            if (goodsDetails.getReceiptContactMobile() != null && !(goodsDetails.getReceiptContactMobile().equals(""))) {
                goodsDetails1.setReceiptContactMobile(goodsDetails.getReceiptContactMobile());
            } else {
                goodsDetails1.setReceiptContactMobile(null);
            }
            if (goodsDetails.getWarningState() != null && !(goodsDetails.getWarningState().equals(""))) {
                goodsDetails1.setWarningState(goodsDetails.getWarningState());
            }
            if (goodsDetails.getAbnormalText() != null && !(goodsDetails.getAbnormalText().equals(""))) {
                goodsDetails1.setAbnormalText(goodsDetails.getAbnormalText());
            }
            goodsDetails1.setUpdateTime(goods.getUpdateTime());
            goodsDetails1.setAdminUid(goodsDetails.getAdminUid());
            if (goodsDetails.getAppUserId() != 0) {
                goodsDetails1.setAppUserId(goodsDetails.getAppUserId());
            }
            if (goodsDetails1.getSingaporeArea() != null && goodsDetails1.getSingaporeArea().getSingaporeAreaId() != null) {
                goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
            }
            if (goodsDetails1.getRallyPoint() != null && goodsDetails1.getRallyPoint().getRallyPointId() != null) {
                goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
            }
            goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
            GoodsWarning goodsWarning = goodsWarningMapper.selectGoodsWarningByGoodsId(goodsDetails.getGoodsId());
            if (goodsDetails.getAbnormalText() != null && !(goodsDetails.getAbnormalText().equals(""))) {
                goodsWarning.setWarningComment(goodsDetails.getAbnormalText());
            }
            if (goodsDetails.getDealComment() != null && !(goodsDetails.getDealComment().equals(""))) {
                goodsWarning.setDealComment(goodsDetails.getDealComment());
            }
            if (goodsDetails.getWarningState() != null && !(goodsDetails.getWarningState().equals(""))) {
                goodsWarning.setWarningState(goodsDetails.getWarningState());
            }
            goodsWarning.setAdminUid(goodsDetails.getAdminUid());
            goodsWarningMapper.updateByPrimaryKey(goodsWarning);
        } else {
            GoodsWarning goodsWarning = goodsWarningMapper.selectGoodsWarningByGoodsId(goodsDetails.getGoodsId());
            if (goodsDetails.getAbnormalText() != null && !(goodsDetails.getAbnormalText().equals(""))) {
                goodsWarning.setWarningComment(goodsDetails.getAbnormalText());
            }
            if (goodsDetails.getDealComment() != null && !(goodsDetails.getDealComment().equals(""))) {
                goodsWarning.setDealComment(goodsDetails.getDealComment());
            }
            if (goodsDetails.getWarningState() != null && !(goodsDetails.getWarningState().equals(""))) {
                goodsWarning.setWarningState(goodsDetails.getWarningState());
            }
            goodsWarning.setAdminUid(goodsDetails.getAdminUid());
            goodsWarning.setDealTime(new Date());
            goodsWarning.setDelStatus(2);
            goodsWarningMapper.updateByPrimaryKey(goodsWarning);
            Goods goods = goodsMapper.selectGoodsById(goodsDetails.getGoodsId());
            if (goodsDetails.getZipCode() != null && !(goodsDetails.getZipCode().equals(""))) {
                goods.setZipCode(goodsDetails.getZipCode());
            } else {
                goods.setZipCode(null);
            }
            if (goodsDetails.getGoodType() != null && !(goodsDetails.getGoodType().equals(""))) {
                goods.setGoodType(goodsDetails.getGoodType());
            } else {
                goods.setGoodType(null);
            }
            goods.setTripartiteNumber(goodsDetails.getTripartiteNumber());
            goods.setUpdateTime(new Date());
            goods.setStatus((byte) 1);
            if(goodsDetails.getBagId()!=null){
                goods.setBagId(goodsDetails.getBagId());
            }
            goodsMapper.updateByPrimaryKey(goods);
            GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goodsDetails.getGoodsId());
                /*if(goodsDetails.getActualLong() instanceof Float&&goodsDetails.getActualLong() > 0f){
                    goodsDetails1.setActualLong(goodsDetails.getActualLong());
                }else if(goodsDetails.getActualLong() <= 0f){
                    return "货物长度不能为负数或者等于O";
                }
                if(goodsDetails.getActualWeight() instanceof Float && goodsDetails.getActualWeight() >0f){
                    goodsDetails1.setActualWeight(goodsDetails.getActualWeight());
                }else if(goodsDetails.getActualWeight() <= 0f){
                    return "货物重量不能为负数或者等于O";
                }
                if(goodsDetails.getActualWidth() instanceof Float && goodsDetails.getActualWidth() >0f ){
                    goodsDetails1.setActualWidth(goodsDetails.getActualWidth());
                }else if(goodsDetails.getActualWidth() <= 0f){
                    return "货物宽度不能为负数或者等于O";
                }
                if(goodsDetails.getActualHeight() instanceof Float && goodsDetails.getActualHeight() >0f ){
                    goodsDetails1.setActualHeight(goodsDetails.getActualHeight());
                }else if(goodsDetails.getActualHeight() <= 0f){
                    return "货物高度不能为负数或者等于O";
                }*/
            if (goodsDetails.getShipAddress() != null && !(goodsDetails.getShipAddress().equals(""))) {
                goodsDetails1.setShipAddress(goodsDetails.getShipAddress());
            } else {
                goodsDetails1.setShipAddress(null);
            }
            if (goodsDetails.getShipContact() != null && !(goodsDetails.getShipContact().equals(""))) {
                goodsDetails1.setShipContact(goodsDetails.getShipContact());
            } else {
                goodsDetails1.setShipContact(null);
            }
            if (goodsDetails.getShipContactMobile() != null && !(goodsDetails.getShipContact().equals(""))) {
                goodsDetails1.setShipContactMobile(goodsDetails.getShipContactMobile());
            } else {
                goodsDetails1.setShipContactMobile(null);
            }
            if (goodsDetails.getReceiptAddress() != null && !(goodsDetails.getReceiptAddress().equals(""))) {
                goodsDetails1.setReceiptAddress(goodsDetails.getReceiptAddress());
            } else {
                goodsDetails1.setReceiptAddress(null);
            }
            if (goodsDetails.getReceiptContact() != null && !(goodsDetails.getReceiptContact().equals(""))) {
                goodsDetails1.setReceiptContact(goodsDetails.getReceiptContact());
            } else {
                goodsDetails1.setReceiptContact(null);
            }
            if (goodsDetails.getReceiptContactMobile() != null && !(goodsDetails.getReceiptContactMobile().equals(""))) {
                goodsDetails1.setReceiptContactMobile(goodsDetails.getReceiptContactMobile());
            } else {
                goodsDetails1.setReceiptContactMobile(null);
            }
            if (goodsDetails.getWarningState() != null && !(goodsDetails.getWarningState().equals(""))) {
                goodsDetails1.setWarningState(goodsDetails.getWarningState());
            }
            if (goodsDetails.getAbnormalText() != null && !(goodsDetails.getAbnormalText().equals(""))) {
                goodsDetails1.setAbnormalText(goodsDetails.getAbnormalText());
            }
            goodsDetails1.setUpdateTime(goods.getUpdateTime());
            goodsDetails1.setAdminUid(goodsDetails.getAdminUid());
            if (goodsDetails1.getSingaporeArea() != null && goodsDetails1.getSingaporeArea().getSingaporeAreaId() != null) {
                goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
            }
            if (goodsDetails1.getRallyPoint() != null && goodsDetails1.getRallyPoint().getRallyPointId() != null) {
                goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
            }
            goodsDetailsMapper.updateGoodsDetailsInfo(goodsDetails1);
            //查询是否是货车上的问题件
            TruckProblemPiece truckProblemPiece = truckProblemPieceMapper.selectByGoodsId(goodsDetails.getGoodsId());
            if(truckProblemPiece!=null && truckProblemPiece.getStatus() ==1){
                //查询货车订单id
                TaskBagOrder taskOrderBag = taskOrderBagMapper.selectOrderIdByGoodsId(goodsDetails.getGoodsId());
                if(taskOrderBag!=null&&taskOrderBag.getTaskOrderId()>0){
                    //查询货车订单
                    TaskTruckOrder taskTruckOrder =taskTruckOrderMapper.selectByPrimaryKey(taskOrderBag.getTaskOrderId());
                    //货车订单为货车模式
                    if(taskTruckOrder!=null&&taskTruckOrder.getOrderType()!=null){
                        if(taskTruckOrder.getOrderType() == 2){
                            //查询步骤
                            TruckDeliveryAssistant truckDeliveryAssistant = truckDeliveryAssistantMapper.selectTruckOrderById(taskOrderBag.getTaskOrderId());
                            //如果步骤小于3，删除掉货车问题件
                            if(truckDeliveryAssistant.getStep()<3){
                                truckProblemPieceMapper.deleteByPrimaryKey(truckProblemPiece.getAppUserId(),goodsDetails.getGoodsId());
                            }
                        }
                    }


                }

            }
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insertGoodsWarningByAppUser(Integer goodsId, String warningComment) {
        GoodsWarning goodsWarning1 = goodsWarningMapper.selectGoodsWarningByGoodsId(goodsId);
        if (goodsWarning1 == null) {
            GoodsWarning goodsWarning = new GoodsWarning();
            goodsWarning.setGoodsId(goodsId);
            goodsWarning.setWarningComment(warningComment);
            goodsWarning.setWarningState("异常");
            goodsWarning.setWarningType(warningComment);
            goodsWarning.setCreateTime(new Date());
            goodsWarning.setDelStatus(1);
            goodsWarningMapper.insert(goodsWarning);
        } else {
            goodsWarning1.setWarningComment(warningComment);
            goodsWarning1.setWarningState("异常");
            goodsWarning1.setWarningType(warningComment);
            goodsWarning1.setDelStatus(1);
            goodsWarningMapper.updateByPrimaryKey(goodsWarning1);
        }
      /*  GoodsWarning goodsWarning = new GoodsWarning();
        goodsWarning.setGoodsId(goodsId);
        goodsWarning.setWarningComment(warningComment);
        goodsWarning.setWarningState("异常");
        goodsWarning.setWarningType(warningComment);
        goodsWarning.setCreateTime(new Date());
        goodsWarning.setDelStatus(1);
        goodsWarningMapper.insert(goodsWarning);*/
        return 1;
    }

    @Override
    public List<String> getGoodsWarningNumber() {
        List<String> stringList = new ArrayList<>();
        try {
            return goodsWarningMapper.getGoodsWarningNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsWarningInfo(GoodsDetails goodsDetails) {
        GoodsWarning goodsWarning = goodsWarningMapper.selectGoodsWarningByGoodsId(goodsDetails.getGoodsId());
        if (goodsDetails.getAbnormalText() != null && !(goodsDetails.getAbnormalText().equals(""))) {
            goodsWarning.setWarningComment(goodsDetails.getAbnormalText());
        }
        if (goodsDetails.getDealComment() != null && !(goodsDetails.getDealComment().equals(""))) {
            goodsWarning.setDealComment(goodsDetails.getDealComment());
        }
        if (goodsDetails.getWarningState() != null && !(goodsDetails.getWarningState().equals(""))) {
            goodsWarning.setWarningState(goodsDetails.getWarningState());
        }
        goodsWarning.setAdminUid(goodsDetails.getAdminUid());
        goodsWarning.setDealTime(new Date());
        goodsWarning.setDelStatus(2);
        goodsWarningMapper.updateByPrimaryKey(goodsWarning);
        Goods goods = goodsMapper.selectGoodsById(goodsDetails.getGoodsId());
        if (goodsDetails.getZipCode() != null && !(goodsDetails.getZipCode().equals(""))) {
            goods.setZipCode(goodsDetails.getZipCode());
        } else {
            goods.setZipCode(null);
        }
        if (goodsDetails.getGoodType() != null && !(goodsDetails.getGoodType().equals(""))) {
            goods.setGoodType(goodsDetails.getGoodType());
        } else {
            goods.setGoodType(null);
        }
        goods.setTripartiteNumber(goodsDetails.getTripartiteNumber());
        goods.setUpdateTime(new Date());
        goods.setStatus((byte) 1);
        goods.setTotalGoods(goodsDetails.getTotalGoods());
        if(goodsDetails.getBagId()!=null){
            goods.setBagId(goodsDetails.getBagId());
        }
        goodsMapper.updateGoodsInfo(goods);
        GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goodsDetails.getGoodsId());
        if (goodsDetails.getShipAddress() != null && !(goodsDetails.getShipAddress().equals(""))) {
            goodsDetails1.setShipAddress(goodsDetails.getShipAddress());
        } else {
            goodsDetails1.setShipAddress(null);
        }
        if (goodsDetails.getShipContact() != null && !(goodsDetails.getShipContact().equals(""))) {
            goodsDetails1.setShipContact(goodsDetails.getShipContact());
        } else {
            goodsDetails1.setShipContact(null);
        }
        if (goodsDetails.getShipContactMobile() != null && !(goodsDetails.getShipContact().equals(""))) {
            goodsDetails1.setShipContactMobile(goodsDetails.getShipContactMobile());
        } else {
            goodsDetails1.setShipContactMobile(null);
        }
        if (goodsDetails.getReceiptAddress() != null && !(goodsDetails.getReceiptAddress().equals(""))) {
            goodsDetails1.setReceiptAddress(goodsDetails.getReceiptAddress());
        } else {
            goodsDetails1.setReceiptAddress(null);
        }
        if (goodsDetails.getReceiptContact() != null && !(goodsDetails.getReceiptContact().equals(""))) {
            goodsDetails1.setReceiptContact(goodsDetails.getReceiptContact());
        } else {
            goodsDetails1.setReceiptContact(null);
        }
        if (goodsDetails.getReceiptContactMobile() != null && !(goodsDetails.getReceiptContactMobile().equals(""))) {
            goodsDetails1.setReceiptContactMobile(goodsDetails.getReceiptContactMobile());
        } else {
            goodsDetails1.setReceiptContactMobile(null);
        }
        if (goodsDetails.getWarningState() != null && !(goodsDetails.getWarningState().equals(""))) {
            goodsDetails1.setWarningState(goodsDetails.getWarningState());
        }
        if (goodsDetails.getAbnormalText() != null && !(goodsDetails.getAbnormalText().equals(""))) {
            goodsDetails1.setAbnormalText(goodsDetails.getAbnormalText());
        }
        goodsDetails1.setUpdateTime(goods.getUpdateTime());
        goodsDetails1.setAdminUid(goodsDetails.getAdminUid());
        if (goodsDetails1.getSingaporeArea() != null && goodsDetails1.getSingaporeArea().getSingaporeAreaId() != null) {
            goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
        }
        if (goodsDetails1.getRallyPoint() != null && goodsDetails1.getRallyPoint().getRallyPointId() != null) {
            goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
        }
        goodsDetailsMapper.updateGoodsDetailsInfo(goodsDetails1);
        //查询是否是货车上的问题件
        TruckProblemPiece truckProblemPiece = truckProblemPieceMapper.selectByGoodsId(goodsDetails.getGoodsId());
        if(truckProblemPiece!=null && truckProblemPiece.getStatus() ==1){
            //查询货车订单id
            TaskBagOrder taskOrderBag = taskOrderBagMapper.selectOrderIdByGoodsId(goodsDetails.getGoodsId());
            if(taskOrderBag!=null&&taskOrderBag.getTaskOrderId()>0){
                //查询货车订单
                TaskTruckOrder taskTruckOrder =taskTruckOrderMapper.selectByPrimaryKey(taskOrderBag.getTaskOrderId());
                //货车订单为货车模式
                if(taskTruckOrder!=null&&taskTruckOrder.getOrderType()!=null){
                    if(taskTruckOrder.getOrderType() == 2){
                        //查询步骤
                        TruckDeliveryAssistant truckDeliveryAssistant = truckDeliveryAssistantMapper.selectTruckOrderById(taskOrderBag.getTaskOrderId());
                        //如果步骤小于3，删除掉货车问题件
                        if(truckDeliveryAssistant.getStep()<3){
                            truckProblemPieceMapper.deleteByPrimaryKey(truckProblemPiece.getAppUserId(),goodsDetails.getGoodsId());
                        }
                    }
                }


            }

        }
    }


}
