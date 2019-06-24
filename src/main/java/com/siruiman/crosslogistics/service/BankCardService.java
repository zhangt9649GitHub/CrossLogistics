package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.BankCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankCardService {

    /**
     * 根据app用户id查询用户银行卡信息
     *
     * @param appUserId
     * @param withdrawId
     * @return
     */
    BankCard selectBankCardByUserId(Integer appUserId, Integer withdrawId);

    /**
     * 根据用户id查询银行卡绑定列表
     *
     * @param appUserId
     * @return
     */
    List<BankCard> selectBankCardById(Integer appUserId);

    /**
     * 根据id查询银行卡详情
     *
     * @param bankCardId
     * @return
     */
    BankCard selectBankCardDetailsById(Integer bankCardId);

    /**
     * 添加新的银行卡
     *
     * @param bankCard
     */
    void insertBankCard(BankCard bankCard);

    /**
     * 根据银行卡id修改为不是默认银行卡
     * @param bankCardId
     */
    /* void updateBankCardIsNotDefaultById(Integer bankCardId);*/

    /**
     * 根据银行卡id修改为默认银行卡
     * @param bankCardId
     */
    /*void updateBankCardIsDefaultById(Integer bankCardId);*/

    /**
     * 修改银行卡信息
     *
     * @param bankCard
     */
    void updateBankCard(BankCard bankCard);

    /**
     * 根据卡号查询银行卡信息
     *
     * @param cardNumber
     * @return
     */
    BankCard selectBankCardByCardNumber(String cardNumber);

    /**
     * 根据银行卡id删除银行卡记录
     *
     * @param bankCardId
     */
    void deleteBankCardById(int bankCardId);

    /**
     * 创建时间排序后的第一条记录
     *
     * @param appUserId
     * @return
     */
    BankCard selectFirstBankCardById(int appUserId);
}
