package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.WithdrawApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface WithdrawApplicationMapper {

    int deleteByPrimaryKey(Integer withdrawId);


    int insert(WithdrawApplication record);


    WithdrawApplication selectByPrimaryKey(Integer withdrawId);


    List<WithdrawApplication> selectAll();


    int updateByPrimaryKey(WithdrawApplication record);

    /**
     * 获取提现申请列表
     *
     * @param map
     * @return
     */
    List<WithdrawApplication> selectWithdrawApplicationList(HashMap<String, Object> map);

    /**
     * 获取提现申请列表总行数
     *
     * @param map
     * @return
     */
    int selectWithdrawApplicationListCount(HashMap<String, Object> map);

    /**
     * 根据提现id查询提现申请记录
     *
     * @param withdrawId
     * @return
     */
    WithdrawApplication selectByWithdrawId(@Param("withdrawId") Integer withdrawId);

    /**
     * 根据提现申请id添加关联的银行卡id
     * @param bankCardId
     * @param withdrawId
     */
    /*void updateWithdrawApplication(@Param("bankCardId") int bankCardId,@Param("withdrawId") Integer withdrawId);*/

    /**
     * 根据app用户id和提现角色获取提现记录
     *
     * @param withdrawRole
     * @param userId
     * @return
     */
    List<WithdrawApplication> selectWithdrawApplicationByUserId(@Param("withdrawRole") String withdrawRole, @Param("userId") int userId);

    /**
     * 根据app用户id和提现角色获取提现记录总行数
     *
     * @param withdrawRole
     * @param userId
     * @return
     */
    int selectCountWithdrawApplicationByUserId(@Param("withdrawRole") String withdrawRole, @Param("userId") int userId);

    /**
     * 根据提现申请编号获取提现申请详情
     *
     * @param withdrawOrderNumber
     * @return
     */
    WithdrawApplication selectWithdrawApplicationByOrderNumber(@Param("withdrawOrderNumber") String withdrawOrderNumber);

    /**
     * 根据银行卡id查询此张银行卡的所有提现记录
     *
     * @param bankCardId
     * @return
     */
    List<WithdrawApplication> selectWithdrawApplicationByBankCardId(@Param("bankCardId") int bankCardId);
}