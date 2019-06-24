package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserWallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppUserWalletMapper {

    int deleteByPrimaryKey(Integer walletId);


    int insert(AppUserWallet record);


    AppUserWallet selectByPrimaryKey(Integer walletId);


    List<AppUserWallet> selectAll();


    int updateByPrimaryKey(AppUserWallet record);


    /**
     * 根据用户类型和app用户id查询我的钱包
     *
     * @param userType
     * @param appUserId
     * @return
     */
    AppUserWallet selectUserWallet(@Param("userType") String userType, @Param("appUserId") int appUserId);

    /**
     * 获取用户钱包余额和计算可提现金额
     *
     * @param userType
     * @param appUserId
     * @return
     */
    AppUserWallet selectWalletBalance(@Param("userType") String userType, @Param("appUserId") int appUserId, @Param("isDefault") int isDefault);

}