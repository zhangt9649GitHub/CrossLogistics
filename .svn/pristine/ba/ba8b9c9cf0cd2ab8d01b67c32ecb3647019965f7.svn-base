package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@ApiModel(description = "郝腾")
public class CommissionDetails {
    @ApiModelProperty(value = "冻结中交易记录", example = "FreezeDeal")
    private AppUserWalletStream freezeDeal;

    @ApiModelProperty(value = "已结算交易记录", example = "ClearingTrades")
    private AppUserWalletStream clearingTrades;

    @ApiModelProperty(value = "扣除件的交易记录", example = "deductUserWalletStreamList")
    private List<AppUserWalletStream> deductUserWalletStreamList;

    public AppUserWalletStream getFreezeDeal() {
        return freezeDeal;
    }

    public void setFreezeDeal(AppUserWalletStream freezeDeal) {
        this.freezeDeal = freezeDeal;
    }

    public AppUserWalletStream getClearingTrades() {
        return clearingTrades;
    }

    public void setClearingTrades(AppUserWalletStream clearingTrades) {
        this.clearingTrades = clearingTrades;
    }

    public List<AppUserWalletStream> getDeductUserWalletStreamList() {
        return deductUserWalletStreamList;
    }

    public void setDeductUserWalletStreamList(List<AppUserWalletStream> deductUserWalletStreamList) {
        this.deductUserWalletStreamList = deductUserWalletStreamList;
    }
}
