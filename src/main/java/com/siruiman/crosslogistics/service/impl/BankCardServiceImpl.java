package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.BankCardMapper;
import com.siruiman.crosslogistics.mapper.WithdrawApplicationMapper;
import com.siruiman.crosslogistics.model.BankCard;
import com.siruiman.crosslogistics.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public BankCard selectBankCardByUserId(Integer appUserId, Integer withdrawId) {
        try {
            int Isdefault = 1;
            BankCard bankCard = bankCardMapper.selectBankCardByUserId(appUserId, Isdefault);
            return bankCard;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BankCard> selectBankCardById(Integer appUserId) {
        try {
            return bankCardMapper.selectBankCardById(appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BankCard selectBankCardDetailsById(Integer bankCardId) {
        try {
            return bankCardMapper.selectByPrimaryKey(bankCardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertBankCard(BankCard bankCard) {
        bankCardMapper.insert(bankCard);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBankCard(BankCard bankCard) {
        bankCardMapper.updateByPrimaryKey(bankCard);
    }

    @Override
    public BankCard selectBankCardByCardNumber(String cardNumber) {
        try {
            return bankCardMapper.selectBankCardByCardNumber(cardNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBankCardById(int bankCardId) {
        BankCard bankCard = bankCardMapper.selectByPrimaryKey(bankCardId);
        bankCard.setIsDefault(2);
        bankCard.setStatus(2);
        bankCard.setUpdateTime(new Date());
        bankCardMapper.updateByPrimaryKey(bankCard);
    }

    @Override
    public BankCard selectFirstBankCardById(int appUserId) {
        try {
            return bankCardMapper.selectFirstBankCardById(appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
