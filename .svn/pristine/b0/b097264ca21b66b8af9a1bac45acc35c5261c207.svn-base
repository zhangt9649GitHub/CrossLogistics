package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.BankCard;
import com.siruiman.crosslogistics.model.WithdrawApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankCardMapper {

    int deleteByPrimaryKey(Integer bankCardId);

    int insert(BankCard record);


    BankCard selectByPrimaryKey(Integer bankCardId);


    List<BankCard> selectAll();


    int updateByPrimaryKey(BankCard record);

    /**
     * 根据app用户id查询默认银行卡信息
     *
     * @param appUserId
     * @param Isdefault
     * @return
     */
    BankCard selectBankCardByUserId(@Param("appUserId") int appUserId, @Param("Isdefault") int Isdefault);

    /**
     * 根据用户id查询银行卡信息
     *
     * @param appUserId
     * @return
     */
    List<BankCard> selectBankCardById(@Param("appUserId") Integer appUserId);

    /**
     * 根据银行卡id修改为不是默认银行卡
     * @param bankCardId
     */
    /*void updateBankCardIsNotDefaultById(@Param("bankCardId") Integer bankCardId);*/

    /**
     * 根据银行卡id修改为默认
     * @param bankCardId
     */
    /*  void updateBankCardIsDefaultById(@Param("bankCardId") Integer bankCardId);*/

    /**
     * 根据卡号查询银行卡信息
     *
     * @param cardNumber
     * @return
     */
    BankCard selectBankCardByCardNumber(@Param("cardNumber") String cardNumber);

    /**
     * 创建时间排序后的第一条记录
     *
     * @param appUserId
     * @return
     */
    BankCard selectFirstBankCardById(@Param("appUserId") int appUserId);
}