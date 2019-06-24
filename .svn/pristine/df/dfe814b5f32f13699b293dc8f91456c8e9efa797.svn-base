package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.service.BizdictionaryService;
import com.siruiman.crosslogistics.service.WithdrawApplicationService;
import com.siruiman.crosslogistics.util.ExchangeRateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;

@Service
public class WithdrawApplicationServiceImpl implements WithdrawApplicationService {

    @Autowired
    private WithdrawApplicationMapper withdrawApplicationMapper;
    @Autowired
    private AppUserWalletMapper appUserWalletMapper;
    @Autowired
    private AppUserWalletStreamMapper appUserWalletStreamMapper;
    @Autowired
    private FinanceMoneyFlowMapper financeMoneyFlowMapper;
    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public List<WithdrawApplication> selectWithdrawApplicationList(String addTime, String endTime, String withdrawRole) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("addTime", addTime);
            map.put("endTime", endTime);
            map.put("withdrawRole", withdrawRole);
            return withdrawApplicationMapper.selectWithdrawApplicationList(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectWithdrawApplicationListCount(String addTime, String endTime, String withdrawRole) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("addTime", addTime);
            map.put("endTime", endTime);
            map.put("withdrawRole", withdrawRole);
            return withdrawApplicationMapper.selectWithdrawApplicationListCount(map);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public WithdrawApplication selectWithdrawApplicationById(int withdrawId) {
        try {
            return withdrawApplicationMapper.selectByPrimaryKey(withdrawId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateWithdrawApplication(int withdrawId) {
        WithdrawApplication withdrawApplication = withdrawApplicationMapper.selectByWithdrawId(withdrawId);
        //修改我的钱包的流水记录
        AppUserWalletStream appUserWalletStream = appUserWalletStreamMapper.selectAppUserWalletStreamByWithdrawId(withdrawId);
        appUserWalletStream.setArriveTime(new Date());
        appUserWalletStream.setStreamText("余额提现成功,提现金额为:" + withdrawApplication.getWithdrawMoney());
        appUserWalletStream.setState((byte) 4);
        appUserWalletStreamMapper.updateByPrimaryKey(appUserWalletStream);
        //财务流水的记录
        FinanceMoneyFlow financeMoneyFlow = new FinanceMoneyFlow();
        financeMoneyFlow.setAmount(withdrawApplication.getWithdrawMoney());
        financeMoneyFlow.setFinanceMoneyType("余额提现");
        financeMoneyFlow.setFinanceObject(withdrawApplication.getUserTrueName());
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(appUserWalletStream.getArriveTime());
        financeMoneyFlow.setCreateTime(dateStr);
        financeMoneyFlow.setSerialNumber(withdrawApplication.getWithdrawOrderNumber());
        financeMoneyFlow.setUnit(withdrawApplication.getUnit());
        financeMoneyFlowMapper.insert(financeMoneyFlow);
        //提现申请表状态修改
        withdrawApplication.setWithdrawType((byte) 2);
        withdrawApplication.setArriveTime(appUserWalletStream.getArriveTime());
        AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        withdrawApplication.setAdminUid(Integer.parseInt(user.getAdminUId()));
        withdrawApplicationMapper.updateByPrimaryKey(withdrawApplication);
        return 1;
    }

    @Override
    public List<WithdrawApplication> selectWithdrawApplicationByUserId(String withdrawRole, int userId) {
        try {
            return withdrawApplicationMapper.selectWithdrawApplicationByUserId(withdrawRole, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountWithdrawApplicationByUserId(String withdrawRole, int userId) {
        try {
            return withdrawApplicationMapper.selectCountWithdrawApplicationByUserId(withdrawRole, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertWithdrawApplication(WithdrawApplication withdrawApplication) {
        withdrawApplicationMapper.insert(withdrawApplication);
    }

    @Override
    public WithdrawApplication selectWithdrawApplicationByOrderNumber(String withdrawOrderNumber) {
        try {
            return withdrawApplicationMapper.selectWithdrawApplicationByOrderNumber(withdrawOrderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public WithdrawApplication insertWithdrawApplicationApply(WithdrawApplication withdrawApplication) {
        if (withdrawApplication.getCardNumber() != null && !(withdrawApplication.getCardNumber().equals(""))) {
            BankCard bankCard = bankCardMapper.selectBankCardByCardNumber(withdrawApplication.getCardNumber());
            withdrawApplication.setBankCardId(bankCard.getBankCardId());
        }
        withdrawApplication.setWithdrawType((byte) 1);
        String withdrawOrderNumber = 14 + getRandomCode();
        withdrawApplication.setWithdrawOrderNumber(withdrawOrderNumber);
        withdrawApplication.setAddTime(new Date());
        if (withdrawApplication.getWithdrawWay().equals("银行卡")) {
            withdrawApplication.setUnit(3);
        } else {
            withdrawApplication.setUnit(1);
        }
        withdrawApplicationMapper.insert(withdrawApplication);
        WithdrawApplication withdrawApplication1 = withdrawApplicationMapper.selectWithdrawApplicationByOrderNumber(withdrawOrderNumber);
        //我的钱包的流水记录
        AppUserWalletStream appUserWalletStream = new AppUserWalletStream();
        appUserWalletStream.setWithdrawId(withdrawApplication1.getWithdrawId());
        appUserWalletStream.setAppUserId(withdrawApplication1.getUserId());
        appUserWalletStream.setStreamType((byte) 1);
        appUserWalletStream.setStreamText("余额提现已提交,提现金额为:" + withdrawApplication1.getWithdrawMoney());
        appUserWalletStream.setAmount(withdrawApplication.getWithdrawMoney());
        appUserWalletStream.setAddTime(new Date());
        appUserWalletStream.setUserType(withdrawApplication.getWithdrawRole());
        appUserWalletStream.setState((byte) 1);
        if (withdrawApplication.getWithdrawWay().equals("银行卡")) {
            appUserWalletStream.setUnit(3);
        } else {
            appUserWalletStream.setUnit(1);
        }
        appUserWalletStreamMapper.insert(appUserWalletStream);
        AppUserWallet appUserWallet1 = appUserWalletMapper.selectUserWallet(withdrawApplication.getWithdrawRole(), withdrawApplication.getUserId());
        if(withdrawApplication.getWithdrawWay().equals("银行卡")) {
            appUserWallet1.setCommissionAmount(appUserWallet1.getCommissionAmount().subtract(withdrawApplication1.getWithdrawMoney()));
        }else{
            appUserWallet1.setCommissionAmount(appUserWallet1.getCommissionAmount().subtract(ExchangeRateUtil.rateExchange(withdrawApplication1.getWithdrawMoney(),13)));
        }
        appUserWalletMapper.updateByPrimaryKey(appUserWallet1);
        return withdrawApplication1;
    }

    @Override
    public List<WithdrawApplication> selectWithdrawApplicationByBankCardId(int bankCardId) {
        try {
            return withdrawApplicationMapper.selectWithdrawApplicationByBankCardId(bankCardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WithdrawApplication selectByWithdrawId(int withdrawId) {
        try {
            return withdrawApplicationMapper.selectByWithdrawId(withdrawId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
