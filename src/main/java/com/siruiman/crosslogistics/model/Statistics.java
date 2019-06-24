package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统计实体类
 * @author 张占伟
 * @date 2019/3/21 11:34
 */
@ApiModel(description = "张占伟")
public class Statistics implements Serializable {

    @ApiModelProperty(value = "小车订单数", example = "1")
    private int countCarOrder;

    @ApiModelProperty(value = "小车完成订单数", example = "1")
    private int countFinishCarOrder;

    @ApiModelProperty(value = "货车订单数", example = "1")
    private int countTruckOrder;

    @ApiModelProperty(value = "货车完成订单数", example = "1")
    private int countFinishTruckOrder;


    public int getCountCarOrder() {
        return countCarOrder;
    }

    public void setCountCarOrder(int countCarOrder) {
        this.countCarOrder = countCarOrder;
    }

    public int getCountFinishCarOrder() {
        return countFinishCarOrder;
    }

    public void setCountFinishCarOrder(int countFinishCarOrder) {
        this.countFinishCarOrder = countFinishCarOrder;
    }

    public int getCountTruckOrder() {
        return countTruckOrder;
    }

    public void setCountTruckOrder(int countTruckOrder) {
        this.countTruckOrder = countTruckOrder;
    }

    public int getCountFinishTruckOrder() {
        return countFinishTruckOrder;
    }

    public void setCountFinishTruckOrder(int countFinishTruckOrder) {
        this.countFinishTruckOrder = countFinishTruckOrder;
    }
}
