package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserWalletStream;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserWalletStreamService {
    /**
     * 根据用户类型和App用户id查询交易记录
     *
     * @param userType
     * @param appUserId
     * @return
     */
    List<AppUserWalletStream> selectWalletStream(String userType, int appUserId);

    /**
     * 根据用户类型和App用户id查询交易记录总行数
     *
     * @param userType
     * @param appUserId
     * @return
     */
    int selectCountWalletStream(String userType, int appUserId);

    /**
     * 根据用户类型和App用户id查询积分记录
     *
     * @param userType
     * @param appUserId
     * @return
     */
    List<AppUserWalletStream> selectWalletIntegralStream(String userType, int appUserId);

    /**
     * 根据用户类型和App用户id查询积分记录总行数
     *
     * @param userType
     * @param appUserId
     * @return
     */
    int selectCountWalletIntegralStream(String userType, int appUserId);

    /**
     * 添加一条我的钱包流水记录（余额提现）
     *
     * @param appUserWalletStream
     */
    void insertAppUserWalletStream(AppUserWalletStream appUserWalletStream);

    /**
     * 根据交易记录id获取交易记录详情
     *
     * @param streamId
     * @return
     */
    AppUserWalletStream selectWalletStreamById(int streamId);

    /**
     * 根据小车订单id获取此笔交易订单的所有流水
     *
     * @param carOrderId
     * @return
     */
    List<AppUserWalletStream> selectWalletStreamByCarOrderId(int carOrderId);

    /**
     * 根据用户id获取转运订单支付成功的列表
     *
     * @param appUserId
     * @return
     */
    List<AppUserWalletStream> selectTransferOrderRecordsList(int appUserId);


    /**
     * 用户支付成功添加流水记录
     *
     * @param appUserWalletStream
     */
    void insertUserPay(AppUserWalletStream appUserWalletStream);
}
