package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="郭阳")
public class AppTaskCarOrderBag {
    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;
    @ApiModelProperty(value = "快递单号", example = "快递单号")
    private String deliveryNumber;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    @Override
    public String toString() {
        return "AppTaskCarOrderBag{" +
                "goodsId=" + goodsId +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                '}';
    }
}
