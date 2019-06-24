package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserWalletStream;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppUserWalletStreamMapper {

    int deleteByPrimaryKey(Integer streamId);


    int insert(AppUserWalletStream record);


    AppUserWalletStream selectByPrimaryKey(Integer streamId);


    List<AppUserWalletStream> selectAll();


    int updateByPrimaryKey(AppUserWalletStream record);

    /**
     * 根据用户类型和App用户id查询交易记录
     *
     * @param userType
     * @param appUserId
     * @return
     */
    List<AppUserWalletStream> selectWalletStream(@Param("userType") String userType, @Param("appUserId") int appUserId);

    /**
     * 根据提现id查询我的钱包流水记录
     *
     * @param withdrawId
     * @return
     */
    AppUserWalletStream selectAppUserWalletStreamByWithdrawId(@Param("withdrawId") int withdrawId);

    /**
     * 根据交易记录id获取交易记录详情
     *
     * @param streamId
     * @return
     */
    AppUserWalletStream selectWalletStreamById(@Param("streamId") int streamId);

    /**
     * 根据小车订单id获取此笔交易订单的所有流水
     *
     * @param carOrderId
     * @return
     */
    List<AppUserWalletStream> selectWalletStreamByCarOrderId(@Param("carOrderId") int carOrderId);

    /**
     * 根据用户类型和App用户id查询交易记录总行数
     *
     * @param userType
     * @param appUserId
     * @return
     */
    int selectCountWalletStream(@Param("userType") String userType, @Param("appUserId") int appUserId);

    /**
     * 根据用户类型和App用户id查询积分记录
     *
     * @param userType
     * @param appUserId
     * @return
     */
    List<AppUserWalletStream> selectWalletIntegralStream(@Param("userType") String userType, @Param("appUserId") int appUserId);

    /**
     * 根据用户类型和App用户id查询积分记录总行数
     *
     * @param userType
     * @param appUserId
     * @return
     */
    int selectCountWalletIntegralStream(@Param("userType") String userType, @Param("appUserId") int appUserId);

    /**
     * 根据用户id获取转运订单支付成功的列表
     *
     * @param appUserId
     * @return
     */
    List<AppUserWalletStream> selectTransferOrderRecordsList(@Param("appUserId") int appUserId);


    /**
     * 添加用户订单支付记录
     *
     * @param walletStream
     */
    void insertUserPay(@Param("walletStream") AppUserWalletStream walletStream);

    /**
     * 根据订单id 流水类型 状态查询记录
     *
     * @return
     */
    AppUserWalletStream selectAppUserWalletStream(@Param("streamType") int streamType, @Param("state") int state, @Param("orderId") int orderId);

    /**
     * 根据订单id 流水类型 状态 流水记录内容查询记录
     *
     * @param streamType
     * @param state
     * @param orderId
     * @param streamText
     * @return
     */
    AppUserWalletStream selectUserWalletStream(@Param("streamType") int streamType, @Param("state") int state, @Param("orderId") int orderId, @Param("streamText") String streamText);
}