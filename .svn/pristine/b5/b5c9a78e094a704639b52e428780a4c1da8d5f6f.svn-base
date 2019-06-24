package com.siruiman.crosslogistics.controller;

import com.github.pagehelper.PageHelper;
import com.siruiman.crosslogistics.enums.HtCode;
import com.siruiman.crosslogistics.model.BankCard;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.Message;
import com.siruiman.crosslogistics.model.WithdrawApplication;
import com.siruiman.crosslogistics.pojo.LayuiCommonResponse;
import com.siruiman.crosslogistics.service.BankCardService;
import com.siruiman.crosslogistics.service.MessageService;
import com.siruiman.crosslogistics.service.WithdrawApplicationService;
import com.siruiman.crosslogistics.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "WithdrawApplication", description = "提现申请API", tags = {"提现申请"})
@RestController
@RequestMapping("/withdrawApplication")
public class WithdrawApplicationController {

    @Autowired
    private WithdrawApplicationService withdrawApplicationService;
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "获取提现申请列表", notes = "获取提现申请列表", tags = {"@郝腾"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页容量", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "addTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "withdrawRole", value = "提现角色", paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getWithdrawApplicationList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"getWithdrawApplicationList"})
    public LayuiCommonResponse getWithdrawApplicationList(@Validated @RequestParam(defaultValue = "1") int page,
                                                          @Validated @RequestParam(defaultValue = "10") int limit,
                                                          @Validated String addTime,
                                                          @Validated String endTime,
                                                          @Validated String withdrawRole) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            PageHelper.startPage(page, limit);
            List<WithdrawApplication> withdrawApplicationList = withdrawApplicationService.selectWithdrawApplicationList(addTime, endTime, withdrawRole);
            int count = withdrawApplicationService.selectWithdrawApplicationListCount(addTime, endTime, withdrawRole);
            response.setMsg("success");
            response.setCode(0);
            response.setCount(count);
            response.setData(withdrawApplicationList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }


    @ApiOperation(value = "确认提现操作", notes = "确认提现操作", tags = {"@郝腾"})
    @ApiImplicitParam(name = "withdrawId", value = "提现id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/getWithdrawInfoByCard", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateWithdrawApplication"})
    public LayuiCommonResponse getWithdrawInfoByCard(@Validated int withdrawId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            WithdrawApplication withdrawApplication = withdrawApplicationService.selectWithdrawApplicationById(withdrawId);
            if (withdrawApplication.getWithdrawWay().equals("银行卡")) {
                BankCard bankCard = new BankCard();
                if (withdrawApplication.getBankCardId() != null) {
                    bankCard = bankCardService.selectBankCardDetailsById(withdrawApplication.getBankCardId());
                    if (bankCard == null) {
                        response.setCode(-1);
                        response.setMsg(HtCode.FAIL_GET_CARD.getInfo());
                        return response;
                    } else {
                        withdrawApplication.setBankName(bankCard.getBankName());
                        withdrawApplication.setCardNumber(bankCard.getCardNumber());
                    }
                }
                bankCard.setWithdrawMoney(withdrawApplication.getWithdrawMoney());
                response.setMsg("success");
                response.setCode(0);
                response.setData(bankCard);
            } else {
                response.setMsg("success");
                response.setCode(0);
                response.setData(withdrawApplication);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }


    @ApiOperation(value = "银行卡、微信、支付宝确认完成提现", notes = "银行卡、微信、支付宝完成提现", tags = {"@郝腾"})
    @ApiImplicitParam(name = "withdrawId", value = "提现id", paramType = "query", required = true, dataType = "int")
    @RequestMapping(value = "/updateWithdrawApplication", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"updateWithdrawApplication"})
    public LayuiCommonResponse updateWithdrawApplication(@Validated int withdrawId) {
        LayuiCommonResponse response = new LayuiCommonResponse();
        try {
            int result = withdrawApplicationService.updateWithdrawApplication(withdrawId);
            if (result == 1) {
                WithdrawApplication withdrawApplication = withdrawApplicationService.selectByWithdrawId(withdrawId);
                if (withdrawApplication != null) {
                    if (withdrawApplication.getWithdrawRole().equals("货车")) {
                        messageService.insertMessage(11, withdrawApplication.getUserId(), 0, 0, 0, withdrawId, 0);
                    } else if (withdrawApplication.getWithdrawRole().equals("小车")) {
                        messageService.insertMessage(7, withdrawApplication.getUserId(), 0, 0, 0, withdrawId, 0);
                    }
                }
            }
            response.setMsg("success");
            response.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMsg("error");
        }
        return response;
    }

}
