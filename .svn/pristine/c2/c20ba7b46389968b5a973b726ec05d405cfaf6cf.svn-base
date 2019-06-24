package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserWallet;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AppUserWalletService {
    /**
     * 根据用户类型和app用户id查询我的钱包
     *
     * @param userType
     * @param appUserId
     * @return
     */
    AppUserWallet selectUserWallet(String userType, int appUserId);

    /**
     * 获取用户钱包余额和计算可提现金额
     *
     * @param userType
     * @param appUserId
     * @return
     */
    AppUserWallet selectWalletBalance(String userType, int appUserId);

    /**
     * 修改我的用户钱包的余额
     *
     * @param appUserWallet
     */
    void updateAppUserWallet(AppUserWallet appUserWallet);

    /**
     * 创建我的钱包
     *
     * @param appUserWallet
     */
    void insertAppUserWallet(AppUserWallet appUserWallet);

    /**
     * 我的钱包的佣金和积分的结算和流水记录
     *
     * @param appUserId        app用户id
     * @param commissionAmount 佣金金额(冻结金额 扣除金额 结算金额)
     * @param integral         积分
     * @param userType         用户类型 （小车 货车）
     * @param state            状态 1.冻结中  2 扣除件 3 已结算
     * @param orderId          订单id
     * @return 1.为成功 2.为失败
     */
    Integer updateCommissionAndIntegral(Integer appUserId, BigDecimal commissionAmount, int integral, String userType, int state, int orderId, int goodsId);
}
