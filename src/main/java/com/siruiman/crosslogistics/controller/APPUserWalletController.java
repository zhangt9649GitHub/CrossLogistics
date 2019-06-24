package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.pojo.CommonResponse;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.*;
import com.siruiman.crosslogistics.util.ExchangeRateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;

@Api(value = "APPUserWallet", description = "APP我的钱包API", tags = {"APP我的钱包"})
@RestController
@RequestMapping("/appUserWallet")
public class APPUserWalletController {

    @Autowired
    private AppUserWalletService appUserWalletService;
    @Autowired
    private WithdrawApplicationService withdrawApplicationService;
    @Autowired
    private AppUserWalletStreamService appUserWalletStreamService;
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private BizdictionaryService bizdictionaryService;

    @ApiOperation(value = "获取我的钱包佣金余额和积分", notes = "获取我的钱包余额和积分", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userType", value = "用户类型", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/getWalletValue", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWalletValue(@Validated String userType, @Validated int appUserId) {
        CommonResponse response = new CommonResponse();
        try {
            AppUserWallet appUserWallet = appUserWalletService.selectUserWallet(userType, appUserId);
            if (appUserWallet == null) {
                AppUserWallet appUserWallet1 = new AppUserWallet();
                appUserWallet1.setAppUserId(appUserId);
                appUserWallet1.setCommissionAmount(new BigDecimal(0));
                appUserWallet1.setUserType(userType);
                appUserWallet1.setIntegral(0);
                appUserWalletService.insertAppUserWallet(appUserWallet1);
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(appUserWallet1);
                return response;
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appUserWallet);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "小车端获取我的钱包抢单佣金已结算或冻结中的交易记录详情", notes = "小车端获取我的钱包抢单佣金已结算或冻结中的交易记录详情", tags = {"@郝腾"})
    @ApiImplicitParam(name = "streamId", value = "流水记录id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getWalletSettlementDetails", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWalletSettlementDetails(@Validated int streamId) {
        CommonResponse response = new CommonResponse();
        try {
            AppUserWalletStream appUserWalletStream = appUserWalletStreamService.selectWalletStreamById(streamId);
            CommissionDetails commissionDetails = new CommissionDetails();
            List<AppUserWalletStream> appUserWalletStreamList = new ArrayList<>();
            if (appUserWalletStream.getState() != null && appUserWalletStream.getStreamType() == 2 && appUserWalletStream.getState() == 2) {
                if(appUserWalletStream!=null&&appUserWalletStream.getUnit()!=null){
                    switch (appUserWalletStream.getUnit()){
                        case 1: appUserWalletStream.setSymbol("￥");break;
                        case 2: appUserWalletStream.setSymbol("$");break;
                        case 3: appUserWalletStream.setSymbol("S$");break;
                    }
                }
                List<AppUserWalletStream> appUserWalletStreamList1 = new ArrayList<>();
                commissionDetails.setFreezeDeal(appUserWalletStream);
                commissionDetails.setDeductUserWalletStreamList(appUserWalletStreamList1);
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(commissionDetails);
                return response;
            } else if (appUserWalletStream.getState() != null && appUserWalletStream.getState() == 3) {
                if (appUserWalletStream.getCarOrderId() != null) {
                    List<AppUserWalletStream> appUserWalletStreamList1 = appUserWalletStreamService.selectWalletStreamByCarOrderId(appUserWalletStream.getCarOrderId());
                    for (AppUserWalletStream appUserWalletStream1 : appUserWalletStreamList1
                    ) {
                        if(appUserWalletStream1!=null&&appUserWalletStream1.getUnit()!=null){
                            switch (appUserWalletStream1.getUnit()){
                                case 1: appUserWalletStream1.setSymbol("￥");break;
                                case 2: appUserWalletStream1.setSymbol("$");break;
                                case 3: appUserWalletStream1.setSymbol("S$");break;
                            }
                        }
                        if (appUserWalletStream1.getState() != null && appUserWalletStream1.getState() == 5) {
                            appUserWalletStreamList.add(appUserWalletStream1);
                        } else if (appUserWalletStream1.getState() != null && appUserWalletStream1.getStreamType() == 2 && appUserWalletStream1.getState() == 2) {
                            commissionDetails.setFreezeDeal(appUserWalletStream1);
                        } else if (appUserWalletStream1.getState() != null && appUserWalletStream1.getStreamType() == 2 && appUserWalletStream1.getState() == 3) {
                            commissionDetails.setClearingTrades(appUserWalletStream1);
                        }
                    }
                    commissionDetails.setDeductUserWalletStreamList(appUserWalletStreamList);
                }
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(commissionDetails);
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取我的钱包交易记录", notes = "获取我的钱包交易记录", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userType", value = "用户类型", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/getWalletStream", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWalletStream(@Validated @RequestParam(defaultValue = "1") int page,
                                          @Validated @RequestParam(defaultValue = "15") int limit,
                                          @Validated String userType, @Validated int appUserId) {
        CommonResponse response = new CommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<AppUserWalletStream> appUserWalletStreamList = appUserWalletStreamService.selectWalletStream(userType, appUserId);
            if(appUserWalletStreamList.size()>0){
                for (AppUserWalletStream appUserWalletStream:appUserWalletStreamList
                ) {
                    if(appUserWalletStream!=null&&appUserWalletStream.getUnit()!=null){
                        switch (appUserWalletStream.getUnit()){
                            case 1: appUserWalletStream.setSymbol("￥");break;
                            case 2: appUserWalletStream.setSymbol("$");break;
                            case 3: appUserWalletStream.setSymbol("S$");break;
                        }
                    }
                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appUserWalletStreamList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取积分余额记录", notes = "获取积分余额记录", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userType", value = "用户类型", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/getWalletIntegral", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWalletIntegral(@Validated @RequestParam(defaultValue = "1") int page,
                                            @Validated @RequestParam(defaultValue = "15") int limit,
                                            @Validated String userType, @Validated int appUserId) {
        CommonResponse response = new CommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<AppUserWalletStream> appUserWalletStreamList = appUserWalletStreamService.selectWalletIntegralStream(userType, appUserId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appUserWalletStreamList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "添加银行卡", notes = "添加银行卡", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bankCard", value = "银行卡实体", required = true, dataType = "BankCard")
    @RequestMapping(value = "/insertBankCard", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse insertBankCard(@Validated BankCard bankCard) {
        CommonResponse response = new CommonResponse();
        try {
            List<BankCard> bankCardList = bankCardService.selectBankCardById(bankCard.getAppUserId());
            if (bankCardList.size() == 0) {
                bankCard.setIsDefault(1);
            } else {
                for (BankCard bankCard1 : bankCardList
                ) {
                    if (bankCard.getCardNumber().equals(bankCard1.getCardNumber())) {
                        response.setCode(HtCode.FAIL_CARD.getCode());
                        response.setMessage(HtCode.FAIL_CARD.getInfo());
                        return response;
                    }
                }
                bankCard.setIsDefault(2);
            }
            bankCard.setAddTime(new Date());
            bankCard.setStatus(1);
            bankCardService.insertBankCard(bankCard);
            response.setCode(HtCode.SUCCESS_ADD.getCode());
            response.setMessage(HtCode.SUCCESS_ADD.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_ADD.getCode());
            response.setMessage(HtCode.FAIL_ADD.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取银行卡开户行列表", notes = "获取银行卡开户行列表", tags = {"@郝腾"})
    @RequestMapping(value = "/getOpeningBankList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getOpeningBankList() {
        CommonResponse response = new CommonResponse();
        try {
            int parentId = 220;
            List<Bizdictionary> bizdictionaryList = bizdictionaryService.selectBizdictionaryByParentId(parentId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(bizdictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "查询绑定的所有银行卡", notes = "查询绑定的所有银行卡", tags = {"@郝腾"})
    @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getBankCardList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getBankCardList(@Validated int appUserId) {
        CommonResponse response = new CommonResponse();
        try {
            List<BankCard> bankCardList = bankCardService.selectBankCardById(appUserId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(bankCardList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取银行卡详情", notes = "获取银行卡详情", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bankCardId", value = "银行卡id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getBankCardDetails", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getBankCardDetails(@Validated int bankCardId) {
        CommonResponse response = new CommonResponse();
        try {
            BankCard bankCard = bankCardService.selectBankCardDetailsById(bankCardId);
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(bankCard);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "银行卡修改", notes = "银行卡修改", tags = {"@郝腾"})
    @ApiImplicitParam(name = "bankCard", value = "银行卡实体", required = true, dataType = "BankCard")
    @RequestMapping(value = "/updateBankCard", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updateBankCard(@Validated BankCard bankCard) {
        CommonResponse response = new CommonResponse();
        try {
            List<BankCard> bankCardList = bankCardService.selectBankCardById(bankCard.getAppUserId());
            Date date = new Date();
            if (bankCard.getIsDefault() == 1) {
                for (BankCard bankCard1 : bankCardList
                ) {
                    if (bankCard1.getIsDefault() == 1) {
                        BankCard bankCard2 = bankCardService.selectBankCardDetailsById(bankCard1.getBankCardId());
                        bankCard2.setIsDefault(2);
                        bankCard2.setUpdateTime(date);
                        bankCardService.updateBankCard(bankCard2);
                    }
                }
            }
            bankCard.setUpdateTime(date);
            bankCard.setStatus(1);
            bankCardService.updateBankCard(bankCard);
            response.setCode(HtCode.SUCCESS_UPDATE.getCode());
            response.setMessage(HtCode.SUCCESS_UPDATE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_UPDATE.getCode());
            response.setMessage(HtCode.FAIL_UPDATE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "银行卡解绑", notes = "银行卡解绑", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appUserId", value = "app用户id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bankCardId", value = "银行卡id", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/deleteBankCardById", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse deleteBankCardById(@Validated int appUserId, @Validated int bankCardId) {
        CommonResponse response = new CommonResponse();
        try {
            List<BankCard> bankCardList = bankCardService.selectBankCardById(appUserId);
            if (bankCardList.size() == 1) {
                response.setCode(HtCode.FAIL_UNBIND.getCode());
                response.setMessage(HtCode.FAIL_UNBIND.getInfo());
                return response;
            }
            List<WithdrawApplication> withdrawApplicationList = withdrawApplicationService.selectWithdrawApplicationByBankCardId(bankCardId);
            if (withdrawApplicationList != null) {
                for (WithdrawApplication withdrawApplication : withdrawApplicationList
                ) {
                    if (withdrawApplication != null && withdrawApplication.getWithdrawType() != null && withdrawApplication.getWithdrawType() == 1) {
                        response.setCode(HtCode.FAIL_UNBIND_USED.getCode());
                        response.setMessage(HtCode.FAIL_UNBIND_USED.getInfo());
                        return response;
                    }
                }
            }
            BankCard bankCard = bankCardService.selectBankCardDetailsById(bankCardId);
            if (bankCard.getIsDefault() == 1) {
                bankCardService.deleteBankCardById(bankCardId);
                BankCard bankCard1 = bankCardService.selectFirstBankCardById(appUserId);
                bankCard1.setIsDefault(1);
                bankCard1.setUpdateTime(new Date());
                bankCardService.updateBankCard(bankCard1);
            } else {
                bankCardService.deleteBankCardById(bankCardId);
            }
            response.setCode(HtCode.SUCCESS_DELETE.getCode());
            response.setMessage(HtCode.SUCCESS_DELETE.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_DELETE.getCode());
            response.setMessage(HtCode.FAIL_DELETE.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取余额提现的金额和可提现金额和最低提现金额和银行卡信息", notes = "获取余额提现的金额和可提现金额和最低提现金额和银行卡信息", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userType", value = "用户类型", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "withdrawalWay", value = "提现方式 1,银行卡 2,微信支付宝", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/getUserWallet", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getUserWallet(@Validated String userType, @Validated int appUserId, @Validated int withdrawalWay) {
        CommonResponse response = new CommonResponse();
        try {
            if(withdrawalWay==1){
                BigDecimal zero = new BigDecimal(0);
                Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(179);
                BigDecimal value = new BigDecimal(bizdictionary.getValue());
                AppUserWallet appUserWallet = appUserWalletService.selectWalletBalance(userType, appUserId);
                if (appUserWallet == null) {
                    appUserWallet = appUserWalletService.selectUserWallet(userType, appUserId);
                }
                if (appUserWallet.getCommissionAmount().subtract(value).compareTo(zero) < 0) {
                    appUserWallet.setCashWithdrawal(zero);
                } else {
                    appUserWallet.setCashWithdrawal(appUserWallet.getCommissionAmount());
                }
                appUserWallet.setMinimumMoney(value.setScale(2, RoundingMode.HALF_UP));
                appUserWallet.setSymbol("S$");
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(appUserWallet);
            }else if(withdrawalWay==2){
                BigDecimal zero = new BigDecimal(0);
                Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(179);
                BigDecimal value = new BigDecimal(bizdictionary.getValue());
                //最低提现金额转为人民币
                BigDecimal value1 =ExchangeRateUtil.rateExchange(value,31).setScale(2, RoundingMode.FLOOR);
                AppUserWallet appUserWallet = appUserWalletService.selectWalletBalance(userType, appUserId);
                if (appUserWallet == null) {
                    appUserWallet = appUserWalletService.selectUserWallet(userType, appUserId);
                }
                if (appUserWallet.getCommissionAmount().subtract(value).compareTo(zero) < 0) {
                    appUserWallet.setCashWithdrawal(zero);
                } else {
                    appUserWallet.setCashWithdrawal(ExchangeRateUtil.rateExchange(appUserWallet.getCommissionAmount(),31).setScale(2, RoundingMode.FLOOR));
                }
                  appUserWallet.setMinimumMoney(value1.setScale(2, RoundingMode.HALF_UP));
                  appUserWallet.setCommissionAmount(ExchangeRateUtil.rateExchange(appUserWallet.getCommissionAmount(),31).setScale(2, RoundingMode.FLOOR));
                appUserWallet.setSymbol("￥");
                response.setCode(HtCode.SUCCESS_GET.getCode());
                response.setMessage(HtCode.SUCCESS_GET.getInfo());
                response.setData(appUserWallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "提交余额提现申请", notes = "提交余额提现申请", tags = {"@郝腾"})
    @ApiImplicitParam(name = "withdrawApplication", value = "提现申请实体", dataType = "WithdrawApplication")
    @RequestMapping(value = "/insertWithdrawApplication", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse insertWithdrawApplication(@Validated WithdrawApplication withdrawApplication) {
        CommonResponse response = new CommonResponse();
        try {
            BigDecimal zero = new BigDecimal(0);
            //微信、支付宝最低提现金额（人民币）
            Bizdictionary bizdictionary = bizdictionaryService.selectBizdictionary(179);
            //微信支付宝最低提现金额(人民币)
            BigDecimal value = new BigDecimal(bizdictionary.getValue());
            AppUserWallet appUserWallet = new AppUserWallet();
            BigDecimal withdrawMoney = new BigDecimal(0);
            if (withdrawApplication.getWithdrawWay().equals("银行卡")) {
                appUserWallet = appUserWalletService.selectWalletBalance(withdrawApplication.getWithdrawRole(), withdrawApplication.getUserId());
                withdrawMoney = withdrawApplication.getWithdrawMoney();//用户输入的金额
            } else {
                appUserWallet = appUserWalletService.selectUserWallet(withdrawApplication.getWithdrawRole(), withdrawApplication.getUserId());
                withdrawMoney = ExchangeRateUtil.rateExchange(withdrawApplication.getWithdrawMoney(),13);
            }
            BigDecimal cashWithdrawal = appUserWallet.getCommissionAmount().subtract(value);//可提现金额
            if (withdrawMoney.compareTo(value) < 0) {
                response.setCode(HtCode.FAIL_MINIMUM.getCode());
                response.setMessage(HtCode.FAIL_MINIMUM.getInfo());
                return response;
            }
            if (cashWithdrawal.compareTo(zero) >= 0 && withdrawMoney.compareTo(appUserWallet.getCommissionAmount()) <= 0) {
                WithdrawApplication withdrawApplication1 = withdrawApplicationService.insertWithdrawApplicationApply(withdrawApplication);
                response.setCode(HtCode.SUCCESS_WITHDRAW_OK.getCode());
                response.setMessage(HtCode.SUCCESS_WITHDRAW_OK.getInfo());
                response.setData(withdrawApplication1);

            } else {
                response.setCode(HtCode.FAIL_WITHDRAW.getCode());
                response.setMessage(HtCode.FAIL_WITHDRAW.getInfo());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_WITHDRAW_NO.getCode());
            response.setMessage(HtCode.FAIL_WITHDRAW_NO.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取余额提现记录", notes = "获取余额提现记录", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "withdrawRole", value = "提现角色", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/getWithdrawApplicationList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWithdrawApplicationList(@Validated @RequestParam(defaultValue = "1") int page,
                                                     @Validated @RequestParam(defaultValue = "15") int limit,
                                                     @Validated String withdrawRole, @Validated int userId) {
        CommonResponse response = new CommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<WithdrawApplication> withdrawApplicationList = withdrawApplicationService.selectWithdrawApplicationByUserId(withdrawRole, userId);
           if(!(withdrawApplicationList.isEmpty())){
               for (WithdrawApplication withdrawApplication:withdrawApplicationList
                    ) {
                   if(withdrawApplication.getUnit()!=null){
                       switch (withdrawApplication.getUnit()){
                           case 1: withdrawApplication.setSymbol("￥");break;
                           case 2: withdrawApplication.setSymbol("$");break;
                           case 3: withdrawApplication.setSymbol("S$");break;
                       }
                   }
               }
           }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(withdrawApplicationList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "查询余额提现详情", notes = "查询余额提现详情", tags = {"@郝腾"})
    @ApiImplicitParam(name = "withdrawOrderNumber", value = "提现订单号", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/getWithdrawApplicationDetails", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getWithdrawApplicationDetails(@Validated String withdrawOrderNumber) {
        CommonResponse response = new CommonResponse();
        try {
            WithdrawApplication withdrawApplication = withdrawApplicationService.selectWithdrawApplicationByOrderNumber(withdrawOrderNumber);
            if(withdrawApplication.getUnit()!=null){
                switch (withdrawApplication.getUnit()){
                    case 1: withdrawApplication.setSymbol("￥");break;
                    case 2: withdrawApplication.setSymbol("$");break;
                    case 3: withdrawApplication.setSymbol("S$");break;
                }
            }
            if (withdrawApplication.getWithdrawWay().equals("微信")) {
                Bizdictionary bizdictionary = bizdictionaryService.getBizdictionaryById(188);
                withdrawApplication.setServiceAccount(bizdictionary.getValue());
            } else if (withdrawApplication.getWithdrawWay().equals("支付宝")) {
                Bizdictionary bizdictionary = bizdictionaryService.getBizdictionaryById(189);
                withdrawApplication.setServiceAccount(bizdictionary.getValue());
            } else if (withdrawApplication.getWithdrawWay().equals("银行卡")) {
                BankCard bankCard = bankCardService.selectBankCardDetailsById(withdrawApplication.getBankCardId());
                if (bankCard != null && bankCard.getCardNumber() != null && bankCard.getBankName() != null) {
                    withdrawApplication.setBankName(bankCard.getBankName());
                    withdrawApplication.setCardNumber(bankCard.getCardNumber());
                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(withdrawApplication);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

    @ApiOperation(value = "获取普通用户的转运订单的交易记录", notes = "获取普通用户的转运订单的交易记录", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "appUserId", value = "app用户id", paramType = "query", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/getTransferOrderRecordsList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getTransferOrderRecordsList(@Validated @RequestParam(defaultValue = "1") int page,
                                                      @Validated @RequestParam(defaultValue = "15") int limit,
                                                      @Validated int appUserId) {
        CommonResponse response = new CommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<AppUserWalletStream> appUserWalletStreamList = new ArrayList<>();
            appUserWalletStreamList = appUserWalletStreamService.selectTransferOrderRecordsList(appUserId);
            if(!(appUserWalletStreamList.isEmpty())){
                for (AppUserWalletStream appUserWalletStream:appUserWalletStreamList
                     ) {
                    if(appUserWalletStream!=null&&appUserWalletStream.getUnit()!=null){
                        switch (appUserWalletStream.getUnit()){
                            case 1: appUserWalletStream.setSymbol("￥");break;
                            case 2: appUserWalletStream.setSymbol("$");break;
                            case 3: appUserWalletStream.setSymbol("S$");break;
                        }
                    }
                }
            }
            response.setCode(HtCode.SUCCESS_GET.getCode());
            response.setMessage(HtCode.SUCCESS_GET.getInfo());
            response.setData(appUserWalletStreamList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(HtCode.FAIL_GET.getCode());
            response.setMessage(HtCode.FAIL_GET.getInfo());
        }
        return response;
    }

}
