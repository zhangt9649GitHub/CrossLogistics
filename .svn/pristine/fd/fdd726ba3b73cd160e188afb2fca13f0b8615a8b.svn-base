package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.WithdrawApplication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Service
public interface WithdrawApplicationService {

    /**
     * 获取提现申请列表
     *
     * @param addTime
     * @param endTime
     * @param withdrawRole
     * @return
     */
    List<WithdrawApplication> selectWithdrawApplicationList(String addTime, String endTime, String withdrawRole);

    /**
     * 获取提现申请列表总行数
     *
     * @param addTime
     * @param endTime
     * @param withdrawRole
     * @return
     */
    int selectWithdrawApplicationListCount(String addTime, String endTime, String withdrawRole);

    /**
     * 确定提现申请操作
     *
     * @param withdrawId
     */
    WithdrawApplication selectWithdrawApplicationById(int withdrawId);

    /**
     * 完成提现申请操作
     *
     * @param withdrawId
     */
    int updateWithdrawApplication(int withdrawId);

    /**
     * 提交提现申请
     * @param withdrawApplication
     */
    /*void insertWithdrawApplication(WithdrawApplication withdrawApplication);*/

    /**
     * 根据app用户id和提现角色获取提现记录
     *
     * @param withdrawRole
     * @param userId
     * @return
     */
    List<WithdrawApplication> selectWithdrawApplicationByUserId(String withdrawRole, int userId);

    /**
     * 根据app用户id和提现角色获取提现记录总行数
     *
     * @param withdrawRole
     * @param userId
     * @return
     */
    int selectCountWithdrawApplicationByUserId(String withdrawRole, int userId);

    /**
     * 提交余额提现申请
     *
     * @param withdrawApplication
     */
    void insertWithdrawApplication(WithdrawApplication withdrawApplication);

    /**
     * 根据提现申请编号获取提现申请详情
     *
     * @param withdrawOrderNumber
     * @return
     */
    WithdrawApplication selectWithdrawApplicationByOrderNumber(String withdrawOrderNumber);

    /**
     * 提交余额提现申请
     *
     * @param withdrawApplication
     * @return
     */
    WithdrawApplication insertWithdrawApplicationApply(WithdrawApplication withdrawApplication);

    /**
     * 根据银行卡id查询此张银行卡的所有提现记录
     *
     * @param bankCardId
     * @return
     */
    List<WithdrawApplication> selectWithdrawApplicationByBankCardId(int bankCardId);

    /**
     * 根据提现id查询提现记录
     *
     * @param withdrawId
     * @return
     */
    WithdrawApplication selectByWithdrawId(int withdrawId);
}
