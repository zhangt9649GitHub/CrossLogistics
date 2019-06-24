package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.AppUserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.siruiman.crosslogistics.util.StaticConfigUtil.anOrderPrice;


@Service
public class AppUserWalletServiceImpl implements AppUserWalletService {
    @Autowired
    private AppUserWalletMapper appUserWalletMapper;
    @Autowired
    private AppUserWalletStreamMapper appUserWalletStreamMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public AppUserWallet selectUserWallet(String userType, int appUserId) {
        try {
            return appUserWalletMapper.selectUserWallet(userType, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AppUserWallet selectWalletBalance(String userType, int appUserId) {
        try {
            int isDefault = 1;
            return appUserWalletMapper.selectWalletBalance(userType, appUserId, isDefault);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateAppUserWallet(AppUserWallet appUserWallet) {
        appUserWalletMapper.updateByPrimaryKey(appUserWallet);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertAppUserWallet(AppUserWallet appUserWallet) {
        appUserWalletMapper.insert(appUserWallet);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateCommissionAndIntegral(Integer appUserId, BigDecimal commissionAmount, int integral, String userType, int state, int orderId, int goodsId) {
        AppUserWalletStream appUserWalletStream = new AppUserWalletStream();
        if (state == 1) {
            //根据小车订单id查询streamtype =2 抢单佣金
            int streamType1 = 2;
            //状态为2 冻结中
            int status1 = 2;
            AppUserWalletStream appUserWalletStream1 = appUserWalletStreamMapper.selectAppUserWalletStream(streamType1, status1, orderId);
            if (appUserWalletStream1 == null) {
                BigDecimal commissionAmount1 = commissionAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                appUserWalletStream.setAppUserId(appUserId);
                appUserWalletStream.setAmount(commissionAmount1);
                //抢单佣金
                appUserWalletStream.setStreamType((byte) 2);
                appUserWalletStream.setStreamText("抢单佣金冻结中，冻结金额为:" + commissionAmount1);
                appUserWalletStream.setAddTime(new Date());
                appUserWalletStream.setUserType(userType);
                //新增流水单位 3.新币
                appUserWalletStream.setUnit(3);
                //冻结中
                appUserWalletStream.setState((byte) 2);
                if (userType.equals("小车")) {
                    appUserWalletStream.setCarOrderId(orderId);
                } else if (userType.equals("货车")) {
                    appUserWalletStream.setTruckOrderId(orderId);
                }
                appUserWalletStreamMapper.insert(appUserWalletStream);
            }
            if (integral != 0) {
                //根据小车订单id查询streamtype =2 抢单佣金
                int streamType2 = 3;
                //状态为2 冻结中
                int status2 = 2;
                AppUserWalletStream appUserWalletStream2 = appUserWalletStreamMapper.selectAppUserWalletStream(streamType2, status2, orderId);
                //抢单积分
                appUserWalletStream.setStreamType((byte) 3);
                appUserWalletStream.setStreamText("抢单积分冻结中，冻结积分为:" + integral);
                appUserWalletStream.setAmount(null);
                //积分单位为null
                appUserWalletStream.setUnit(null);
                appUserWalletStream.setIntegral(integral);
                appUserWalletStreamMapper.insert(appUserWalletStream);
            }
            return 1;
        } else if (state == 2) {
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            String streamText = "问题件(快递单号:" + goods.getDeliveryNumber() + ")扣除佣金，金额为:" + commissionAmount;
            //根据小车订单id查询streamtype =4 扣除件佣金
            int streamType1 = 4;
            //状态为2 冻结中
            int status1 = 5;
            AppUserWalletStream appUserWalletStream1 = appUserWalletStreamMapper.selectUserWalletStream(streamType1, status1, orderId, streamText);
            if (appUserWalletStream1 == null) {
                appUserWalletStream.setAppUserId(appUserId);
                appUserWalletStream.setAmount(commissionAmount);
                //新增流水单位 3.新币
                appUserWalletStream.setUnit(3);
                //问题件扣除（佣金）
                appUserWalletStream.setStreamType((byte) 4);
                appUserWalletStream.setStreamText("问题件(快递单号:" + goods.getDeliveryNumber() + ")扣除佣金，金额为:" + commissionAmount);
                appUserWalletStream.setAddTime(new Date());
                appUserWalletStream.setUserType(userType);
                appUserWalletStream.setState((byte) 5);
                if (userType.equals("小车")) {
                    appUserWalletStream.setCarOrderId(orderId);
                } else if (userType.equals("货车")) {
                    appUserWalletStream.setTruckOrderId(orderId);
                }
                appUserWalletStreamMapper.insert(appUserWalletStream);
                if (integral != 0) {
                    appUserWalletStream.setIntegral(integral);
                    appUserWalletStream.setAmount(null);
                    //积分单位为null
                    appUserWalletStream.setUnit(null);
                    //问题件扣除（积分）
                    appUserWalletStream.setStreamType((byte) 5);
                    appUserWalletStream.setStreamText("问题件(快递单号:" + goods.getDeliveryNumber() + ")扣除积分，积分为:" + integral);
                    appUserWalletStream.setState((byte) 6);
                    appUserWalletStreamMapper.insert(appUserWalletStream);
                }
            }
            return 1;
        } else if (state == 3) {
            //根据小车订单id查询streamtype =2 抢单佣金
            int streamType1 = 2;
            //状态为3 已结算
            int status1 = 3;
            AppUserWalletStream appUserWalletStream1 = appUserWalletStreamMapper.selectAppUserWalletStream(streamType1, status1, orderId);
            if (appUserWalletStream1 == null) {
                AppUserWallet appUserWallet = appUserWalletMapper.selectUserWallet(userType, appUserId);
                if (appUserWallet != null) {
                    appUserWallet.setCommissionAmount(appUserWallet.getCommissionAmount().add(commissionAmount));
                    appUserWallet.setIntegral(appUserWallet.getIntegral() + integral);
                    appUserWalletMapper.updateByPrimaryKey(appUserWallet);
                } else {
                    AppUserWallet appUserWallet1 = new AppUserWallet();
                    appUserWallet1.setAppUserId(appUserId);
                    appUserWallet1.setCommissionAmount(commissionAmount);
                    appUserWallet1.setIntegral(integral);
                    appUserWallet1.setUserType(userType);
                    appUserWalletMapper.insert(appUserWallet1);
                }
                appUserWalletStream.setAppUserId(appUserId);
                appUserWalletStream.setAmount(commissionAmount);
                //抢单佣金
                appUserWalletStream.setStreamType((byte) 2);
                appUserWalletStream.setStreamText("抢单佣金已结算，结算金额为:" + commissionAmount);
                appUserWalletStream.setAddTime(new Date());
                appUserWalletStream.setUserType(userType);

                //新增流水单位 3.新币
                appUserWalletStream.setUnit(3);

                //已结算
                appUserWalletStream.setState((byte) 3);
                if (userType.equals("小车")) {
                    appUserWalletStream.setCarOrderId(orderId);
                } else if (userType.equals("货车")) {
                    appUserWalletStream.setTruckOrderId(orderId);
                }
                appUserWalletStreamMapper.insert(appUserWalletStream);
                appUserWalletStream.setIntegral(integral);
                appUserWalletStream.setAmount(null);
                //积分单位为null
                appUserWalletStream.setUnit(null);
                //抢单积分
                appUserWalletStream.setStreamType((byte) 3);
                appUserWalletStream.setStreamText("抢单积分已结算，结算积分为:" + integral);
                appUserWalletStreamMapper.insert(appUserWalletStream);
            }
            return 1;
        }
        return 2;
    }
}
