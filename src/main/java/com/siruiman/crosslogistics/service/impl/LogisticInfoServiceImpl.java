package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.LogisticInfoService;
import com.siruiman.crosslogistics.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LogisticInfoServiceImpl implements LogisticInfoService {

    @Autowired
    private LogisticInfoMapper logisticInfoMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private BagMapper bagMapper;
    @Autowired
    private AppUserCertificationMapper appUserCertificationMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;

    @Override
    public List<LogisticInfo> selectGoodsLogisticInfoByGoodsId(Integer goodsId) {
        try {
            return logisticInfoMapper.selectGoodsLogisticInfoByGoodsId(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LogisticInfo> selectOperateNameById(Integer goodsId) {
        try {
            return logisticInfoMapper.selectOperateNameById(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectOperateNameCount(Integer goodsId) {
        try {
            return logisticInfoMapper.selectOperateNameCount(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertLogisticInfo(LogisticInfo logisticInfo) {
        logisticInfoMapper.insert(logisticInfo);
    }

    @Override
    public int selectPieceByStaffId(int staffId) {
        try {
            return logisticInfoMapper.selectPieceByStaffId(staffId);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    public List<LogisticInfo> selectLogisticInfoBystaffId(int staffId, String firstDayOfMonth, String lastDayOfMonth) {
        try {
            return logisticInfoMapper.selectLogisticInfoBystaffId(staffId, firstDayOfMonth, lastDayOfMonth);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public int selectCountLogisticInfoBystaffId(int staffId, String firstDayOfMonth, String lastDayOfMonth) {
        try {
            return logisticInfoMapper.selectCountLogisticInfoBystaffId(staffId, firstDayOfMonth, lastDayOfMonth);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    public List<LogisticInfo> selectBagLogisticInfoByBagId(int bagId) {
        try {
            return logisticInfoMapper.selectBagLogisticInfoByBagId(bagId);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public int selectBagOperateNameCount(int bagId) {
        try {
            return logisticInfoMapper.selectBagOperateNameCount(bagId);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertNormalLogisticInfo(int steps, int staffId, int appUserId, int goodsId, int bagId) {
        //正常流程步骤  1.货物入库(steps staffId goodsId) 2.货物打包(steps staffId goodsId bagId) 3 货袋出库(steps staffId bagId)
        // 4 货袋接收(steps staffId bagId) 5.货袋入仓(steps staffId bagId) 6.货袋装车(steps staffId bagId) 7.货袋装配小车(steps appUserId bagId)
        // 8.货袋配送中(steps appUserId bagId) 9.货物配送完成(steps appUserId goodsId)
        //增加进程  10.转运货物提交合并转运 （steps appUserId goodsId） 11.转运货物发起转运（steps appUserId goodsId）
        //增加流程 12.货车货袋配送中（steps appUserId bagId） 13.货车货物配送完成（steps appUserId goodsId）
        LogisticInfo logisticInfo = new LogisticInfo();
        Staff staff = new Staff();
        Goods goods = new Goods();
        //AppUserCertification appUserCertification = new AppUserCertification();
        AppUser appUser = new AppUser();
        List<Goods> goodsList = new ArrayList<>();
        GoodsDetails goodsDetails = new GoodsDetails();
        if (staffId > 0) {
            staff = staffMapper.selectStaffDetail(staffId);
        }
        if (appUserId > 0 && steps == 7) {
              /*  String userType = "货车";
              appUserCertification = appUserCertificationMapper.selectUserCertificationByUserId(appUserId,userType);*/
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }
        if ((appUserId > 0 && steps == 8) || (appUserId > 0 && steps == 9)) {
               /* String userType = "小车";
                appUserCertification = appUserCertificationMapper.selectUserCertificationByUserId(appUserId,userType);*/
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }
        if ((appUserId > 0 && steps == 12) || (appUserId > 0 && steps == 13)) {
               /* String userType = "货车";
                appUserCertification = appUserCertificationMapper.selectUserCertificationByUserId(appUserId,userType);*/
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }
        if (goodsId > 0) {
            goods = goodsMapper.selectByPrimaryKey(goodsId);
            if (goods.getBagId() != null) {
                goodsList = goodsMapper.selectCarGoodsList(goods.getBagId());
            }
            goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goodsId);
        }
        if (bagId > 0) {
            goodsList = goodsMapper.selectCarGoodsList(bagId);
        }
        if (steps == 1) {
            logisticInfo.setGoodsId(goodsId);
            logisticInfo.setCreateTime(new Date());
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
            logisticInfo.setStaffId(staffId);
            logisticInfoMapper.insert(logisticInfo);
        } else if (steps == 2) {
           /* String OperateType = "货袋装货";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectBagId(bagId, OperateType);
            if (logisticInfo1 == null) {
                logisticInfo.setBagId(bagId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("货袋正在配货");
                if (staff != null) {
                    logisticInfo.setOperateName(staff.getStaffName());
                }
                logisticInfo.setOperateComment("货袋正在配货");
                logisticInfo.setOperateType("货袋装货");
                logisticInfo.setStaffId(staffId);
                logisticInfoMapper.insert(logisticInfo);
            }
            logisticInfo.setGoodsId(goodsId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货物装袋成功");
            if (staff != null) {
                logisticInfo.setOperateName(staff.getStaffName());
            }
            logisticInfo.setOperateComment("货物装袋成功");
            logisticInfo.setOperateType("货物打包");
            logisticInfo.setStaffId(staffId);
            logisticInfo.setBagId(null);
            logisticInfoMapper.insert(logisticInfo);*/
        } else if (steps == 3) {
           /* logisticInfo.setBagId(bagId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货袋出库成功");
            if (staff != null) {
                logisticInfo.setOperateName(staff.getStaffName());
            }
            logisticInfo.setOperateComment("货袋出库成功");
            logisticInfo.setOperateType("货袋出库");
            logisticInfo.setStaffId(staffId);
            logisticInfoMapper.insert(logisticInfo);
            if (goodsList != null) {
                for (Goods goods1 : goodsList
                ) {
                    logisticInfo.setGoodsId(goods1.getGoodsId());
                    logisticInfo.setCreateTime(new Date());
                    logisticInfo.setOperateResult("货物已从中国仓库发出");
                    if (staff != null) {
                        logisticInfo.setOperateName(staff.getStaffName());
                    }
                    logisticInfo.setOperateComment("货物已从中国仓库发出");
                    logisticInfo.setOperateType("货物出库");
                    logisticInfo.setStaffId(staffId);
                    logisticInfo.setBagId(null);
                    logisticInfoMapper.insert(logisticInfo);
                }
            }*/
        } else if (steps == 4) {
           /* logisticInfo.setBagId(bagId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货袋已到新加坡仓库");
            if (staff != null) {
                logisticInfo.setOperateName(staff.getStaffName());
            }
            logisticInfo.setOperateComment("货袋已到新加坡仓库");
            logisticInfo.setOperateType("货袋接收");
            logisticInfo.setStaffId(staffId);
            logisticInfoMapper.insert(logisticInfo);
            if (goodsList != null) {
                for (Goods goods1 : goodsList
                ) {
                    logisticInfo.setGoodsId(goods1.getGoodsId());
                    logisticInfo.setCreateTime(new Date());
                    logisticInfo.setOperateResult("货物已到新加坡仓库");
                    if (staff != null) {
                        logisticInfo.setOperateName(staff.getStaffName());
                    }
                    logisticInfo.setOperateComment("货物已到新加坡仓库");
                    logisticInfo.setOperateType("货物接收");
                    logisticInfo.setStaffId(staffId);
                    logisticInfo.setBagId(null);
                    logisticInfoMapper.insert(logisticInfo);
                }
            }*/
        } else if (steps == 5) {
           /* logisticInfo.setBagId(bagId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货袋已入新加坡仓位");
            if (staff != null) {
                logisticInfo.setOperateName(staff.getStaffName());
            }
            logisticInfo.setOperateComment("货袋已入新加坡仓位");
            logisticInfo.setOperateType("货袋入库");
            logisticInfo.setStaffId(staffId);
            logisticInfoMapper.insert(logisticInfo);
            if (goodsList != null) {
                for (Goods goods1 : goodsList
                ) {
                    logisticInfo.setGoodsId(goods1.getGoodsId());
                    logisticInfo.setCreateTime(new Date());
                    logisticInfo.setOperateResult("货物已入新加坡仓位");
                    if (staff != null) {
                        logisticInfo.setOperateName(staff.getStaffName());
                    }
                    logisticInfo.setOperateComment("货物已入新加坡仓位");
                    logisticInfo.setOperateType("货物入仓");
                    logisticInfo.setStaffId(staffId);
                    logisticInfo.setBagId(null);
                    logisticInfoMapper.insert(logisticInfo);
                }
            }*/
        } else if (steps == 6) {
           /* logisticInfo.setBagId(bagId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货袋已装车出库");
            if (staff != null) {
                logisticInfo.setOperateName(staff.getStaffName());
            }
            logisticInfo.setOperateComment("货袋已装车出库");
            logisticInfo.setOperateType("货袋装车");
            logisticInfo.setStaffId(staffId);
            logisticInfoMapper.insert(logisticInfo);
            if (goodsList != null) {
                for (Goods goods1 : goodsList
                ) {
                    logisticInfo.setGoodsId(goods1.getGoodsId());
                    logisticInfo.setCreateTime(new Date());
                    logisticInfo.setOperateResult("货物已装车出库");
                    if (staff != null) {
                        logisticInfo.setOperateName(staff.getStaffName());
                    }
                    logisticInfo.setOperateComment("货物已装车出库");
                    logisticInfo.setOperateType("货物出库");
                    logisticInfo.setStaffId(staffId);
                    logisticInfo.setBagId(null);
                    logisticInfoMapper.insert(logisticInfo);
                }
            }*/
        } else if (steps == 7) {
            String OperateType = "货袋装配小车";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectBagId(bagId, OperateType);
            if (logisticInfo1 == null) {
                logisticInfo.setBagId(bagId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("货袋装配小车成功");
                if (appUser != null && appUser.getActualName() != null) {
                    logisticInfo.setOperateName(appUser.getActualName());
                }
                logisticInfo.setOperateComment("货袋装配小车成功");
                logisticInfo.setOperateType("货袋装配小车");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
                if (goodsList != null) {
                    for (Goods goods1 : goodsList
                    ) {
                        logisticInfo.setGoodsId(goods1.getGoodsId());
                        logisticInfo.setCreateTime(new Date());
                        logisticInfo.setOperateResult("货物装配小车成功");
                        if (appUser != null && appUser.getActualName() != null) {
                            logisticInfo.setOperateName(appUser.getActualName());
                        }
                        logisticInfo.setOperateComment("货物装配小车成功");
                        logisticInfo.setOperateType("货物装配小车");
                        logisticInfo.setAppUserId(appUserId);
                        logisticInfo.setBagId(null);
                        logisticInfoMapper.insert(logisticInfo);
                    }
                }
            }
        } else if (steps == 8) {
            String OperateType = "货袋配送中";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectBagId(bagId, OperateType);
            if (logisticInfo1 == null) {
                logisticInfo.setBagId(bagId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("货袋配送中");
                if (appUser != null && appUser.getActualName() != null) {
                    logisticInfo.setOperateName(appUser.getActualName());
                }
                logisticInfo.setOperateComment("货袋配送中");
                logisticInfo.setOperateType("货袋配送中");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
                if (goodsList != null) {
                    for (Goods goods1 : goodsList
                    ) {
                        logisticInfo.setGoodsId(goods1.getGoodsId());
                        logisticInfo.setCreateTime(new Date());
                        if (appUser != null && appUser.getActualName() != null) {
                            logisticInfo.setOperateResult(appUser.getActualName() + "正在配送中");
                            logisticInfo.setOperateName(appUser.getActualName());
                        }
                        logisticInfo.setOperateComment("货物配送中");
                        logisticInfo.setOperateType("货物配送中");
                        logisticInfo.setAppUserId(appUserId);
                        logisticInfo.setBagId(null);
                        logisticInfoMapper.insert(logisticInfo);
                    }
                }
            }
        } else if (steps == 9) {
            String OperateType = "货物配送完成";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectGoodsLogisticInfoByIdType(goodsId, OperateType);
            if(logisticInfo1 == null) {
                logisticInfo.setGoodsId(goodsId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("貨物已簽收，簽收人"+goodsDetails.getReceiptContact());
                if (appUser != null && appUser.getActualName() != null) {
                    logisticInfo.setOperateName(appUser.getActualName());
                }
                logisticInfo.setOperateComment("货物配送完成");
                logisticInfo.setOperateType("货物配送完成");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
              /* boolean flag = false;
               for (Goods goods1:goodsList
                    ) {
                   if(goods1.getIsReceiveGoods()!=null&&goods1.getIsReceiveGoods()==1){
                       flag = true;
                   }
               }*/
                if (goods != null && goods.getBagId() != null) {
                    Bag bag = bagMapper.selectBagDetailsById(goods.getBagId());
                    if ((bag != null && bag.getState() != null && bag.getState() == 8) ||
                            (bag != null && bag.getState() != null && bag.getState() == 9)) {
                        logisticInfo.setBagId(goods.getBagId());
                        logisticInfo.setCreateTime(new Date());
                        logisticInfo.setOperateResult("货袋配送完成");
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
        } else if (steps == 10) {
            String OperateType = "货物提交合并转运";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectGoodsLogisticInfoByIdType(goodsId, OperateType);
            if (logisticInfo1 != null) {
                logisticInfo.setGoodsId(goodsId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("快递单号为:" + goods.getDeliveryNumber() + "的货物提交合并转运再合并");
                logisticInfo.setOperateComment("货物提交合并转运再合并");
                logisticInfo.setOperateType("货物提交合并转运再合并");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
            } else {
                logisticInfo.setGoodsId(goodsId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("快递单号为:" + goods.getDeliveryNumber() + "的货物提交合并转运");
                logisticInfo.setOperateComment("货物提交合并转运");
                logisticInfo.setOperateType("货物提交合并转运");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
            }
        } else if (steps == 11) {
            logisticInfo.setGoodsId(goodsId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货物发起转运");
            logisticInfo.setOperateComment("货物发起转运");
            logisticInfo.setOperateType("货物发起转运");
            logisticInfo.setAppUserId(appUserId);
            logisticInfoMapper.insert(logisticInfo);
        } else if (steps == 12) {
            String OperateType = "货袋配送中";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectBagId(bagId, OperateType);
            if (logisticInfo1 == null) {
                logisticInfo.setBagId(bagId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("货袋配送中");
                if (appUser != null && appUser.getActualName() != null) {
                    logisticInfo.setOperateName(appUser.getActualName());
                }
                logisticInfo.setOperateComment("货袋配送中");
                logisticInfo.setOperateType("货袋配送中");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
                if (goodsList != null) {
                    for (Goods goods1 : goodsList
                    ) {
                        logisticInfo.setGoodsId(goods1.getGoodsId());
                        logisticInfo.setCreateTime(new Date());
                        if (appUser != null && appUser.getActualName() != null) {
                            logisticInfo.setOperateResult(appUser.getActualName() + "正在配送中");
                            logisticInfo.setOperateName(appUser.getActualName());
                        }
                        logisticInfo.setOperateComment("货物配送中");
                        logisticInfo.setOperateType("货物配送中");
                        logisticInfo.setAppUserId(appUserId);
                        logisticInfo.setBagId(null);
                        logisticInfoMapper.insert(logisticInfo);
                    }
                }
            }
        } else if (steps == 13) {
            String OperateType = "货物配送完成";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectGoodsLogisticInfoByIdType(goodsId, OperateType);
            if(logisticInfo1 == null){
                logisticInfo.setGoodsId(goodsId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("貨物已簽收，簽收人"+goodsDetails.getReceiptContact());
                if (appUser != null && appUser.getActualName() != null) {
                    logisticInfo.setOperateName(appUser.getActualName());
                }
                logisticInfo.setOperateComment("货物配送完成");
                logisticInfo.setOperateType("货物配送完成");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
              /* boolean flag = false;
               for (Goods goods1:goodsList
                    ) {
                   if(goods1.getIsReceiveGoods()!=null&&goods1.getIsReceiveGoods()==1){
                       flag = true;
                   }
               }*/
                if (goods != null && goods.getBagId() != null) {
                    Bag bag = bagMapper.selectBagDetailsById(goods.getBagId());
                    if ((bag != null && bag.getState() != null && bag.getState() == 8) ||
                            (bag != null && bag.getState() != null && bag.getState() == 9)) {
                        logisticInfo.setBagId(goods.getBagId());
                        logisticInfo.setCreateTime(new Date());
                        logisticInfo.setOperateResult("货袋配送完成");
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

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertAbnormalLogisticInfo(int steps, String warningComment, int goodsId, int staffId, int appUserId) {
        //异常流程  1.货物入库异常（steps,warningComment，goodsId，staffId） 2 货物派送异常(steps,warningComment，goodsId，appUserId)
        //增加流程 4.货物货车司机派送异常（steps,warningComment，goodsId，appUserId）
        LogisticInfo logisticInfo = new LogisticInfo();
        Staff staff = new Staff();
        Goods goods = new Goods();
        // AppUserCertification appUserCertification = new AppUserCertification();
        AppUser appUser = new AppUser();
        if (staffId > 0) {
            staff = staffMapper.selectStaffDetail(staffId);
        }
        if (appUserId > 0 ) {
           /* String userType = "小车";
            appUserCertification = appUserCertificationMapper.selectUserCertificationByUserId(appUserId,userType);*/
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }
       /* if (appUserId > 0 && steps == 3) {
           *//* String userType = "货车";
            appUserCertification = appUserCertificationMapper.selectUserCertificationByUserId(appUserId,userType);*//*
            appUser = appUserMapper.selectAppUserByUserId(appUserId);
        }*/
        if (goodsId > 0) {
            goods = goodsMapper.selectByPrimaryKey(goodsId);
        }
        if (steps == 1) {
            logisticInfo.setGoodsId(goodsId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("货物入库异常,异常原因:"+warningComment);
            logisticInfo.setOperateName(staff.getStaffName());
            logisticInfo.setOperateComment(warningComment);
            logisticInfo.setOperateType("货物入库异常");
            logisticInfo.setStaffId(staffId);
            logisticInfoMapper.insert(logisticInfo);
        } else if (steps == 2) {
            logisticInfo.setGoodsId(goodsId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("骑手派送失敗，失敗原因："+warningComment);
            if (appUser != null && appUser.getActualName() != null) {
                logisticInfo.setOperateName(appUser.getActualName());
            }
            logisticInfo.setOperateComment(warningComment);
            logisticInfo.setOperateType("(骑手)货物派送异常");
            logisticInfo.setAppUserId(appUserId);
            logisticInfoMapper.insert(logisticInfo);
            if (goods != null && goods.getBagId() != null) {
                Bag bag = bagMapper.selectBagDetailsById(goods.getBagId());
                if ((bag != null && bag.getState() != null && bag.getState() == 8) ||
                        (bag != null && bag.getState() != null && bag.getState() == 9)) {
                    logisticInfo.setBagId(goods.getBagId());
                    logisticInfo.setCreateTime(new Date());
                    logisticInfo.setOperateResult("货袋配送完成");
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
        } else if (steps == 3) {
            String OperateType = "货物问题件接收成功";
            LogisticInfo logisticInfo1 = logisticInfoMapper.selectGoodsLogisticInfoByIdType(goodsId, OperateType);
            if (logisticInfo1 == null) {
                logisticInfo.setGoodsId(goodsId);
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("货物问题件接收成功");
                if (staff != null) {
                    logisticInfo.setOperateName(staff.getStaffName());
                }
                logisticInfo.setOperateComment("货物问题件接收成功");
                logisticInfo.setOperateType("货物问题件接收成功");
                logisticInfoMapper.insert(logisticInfo);
            }
        } else if (steps == 4) {
            logisticInfo.setGoodsId(goodsId);
            logisticInfo.setCreateTime(new Date());
            logisticInfo.setOperateResult("派送失敗，失敗原因："+warningComment);
            if (appUser != null && appUser.getActualName() != null) {
                logisticInfo.setOperateName(appUser.getActualName());
            }
            logisticInfo.setOperateComment(warningComment);
            logisticInfo.setOperateType("货物派送异常");
            logisticInfo.setAppUserId(appUserId);
            logisticInfoMapper.insert(logisticInfo);
           /* String OperateType ="货袋配送异常";
            LogisticInfo logisticInfo1 =logisticInfoMapper.selectBagId(goods.getBagId(),OperateType);
            if(logisticInfo1 ==null){
                logisticInfo.setBagId(goods.getBagId());
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("货袋配送异常");
                if(appUserCertification!=null&&appUserCertification.getUserTrueName()!=null) {
                    logisticInfo.setOperateName(appUserCertification.getUserTrueName());
                }
                logisticInfo.setOperateComment(warningComment);
                logisticInfo.setOperateType("货袋配送异常");
                logisticInfo.setAppUserId(appUserId);
                logisticInfoMapper.insert(logisticInfo);
            }*/
        }
    }

    @Override
    public LogisticInfo selectLogisticInfoByGoodsId(int goodsId) {
        try {
            return logisticInfoMapper.selectLogisticInfoByGoodsId(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LogisticInfo selectBagInfoByBagId(Integer bagId) {
        return logisticInfoMapper.selectLogisticInfoByBagId(bagId);
    }

    @Override
    public List<LogisticInfo> selectLogisticInfoByDeliveryNum(String deliveryNum) {
        List<LogisticInfo> list = new ArrayList<>();
        try {
            List<LogisticInfo> infos = logisticInfoMapper.selectLogisticInfoByDeliveryNum(deliveryNum);
            if (infos != null) {
                list = infos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ;

    @Override
    public List<LogisticInfo2> selectLogisticInfoByTripartiteNumber(String tripartiteNumber) {
        List<LogisticInfo2> infos = logisticInfoMapper.selectLogisticInfoByTripartiteNumber(tripartiteNumber);
        return infos;
    }

    @Override
    public List<LogisticInfo2> selectLogisticInfo(String tripartiteNumber) {
        try {
            return logisticInfoMapper.selectLogisticInfo(tripartiteNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LogisticInfo> selectLogisticInfoBystaffIdList(int staffId, String startDate, String endDate) {
        try{
            HashMap<String, Object> map = new HashMap<>();
            map.put("staffId", staffId);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("operateType", "货物送出");
            return logisticInfoMapper.selectLogisticInfoBystaffIdList(map);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LogisticInfo> selectLogisticInfoByadminUidList(int adminUid, String startDate, String endDate) {
        try{
            HashMap<String, Object> map = new HashMap<>();
            map.put("adminUid", adminUid);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            map.put("operateType", "货物送出");
            return logisticInfoMapper.selectLogisticInfoByadminUidList(map);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
