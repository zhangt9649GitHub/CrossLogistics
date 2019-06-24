package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class ProblemPieceDto {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer appUserId;
    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;
    @ApiModelProperty(value = "异常描述", example = "异常描述")
    private String abnormalText;

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getAbnormalText() {
        return abnormalText;
    }

    public void setAbnormalText(String abnormalText) {
        this.abnormalText = abnormalText;
    }

    @Override
    public String toString() {
        return "ProblemPieceDto{" +
                "appUserId=" + appUserId +
                ", goodsId=" + goodsId +
                ", abnormalText='" + abnormalText + '\'' +
                '}';
    }
}
