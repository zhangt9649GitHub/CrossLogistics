package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author 张占伟
 * @date 2019/1/23 17:23
 */
public class TaskBagOrder {
    @ApiModelProperty(value = "主键id", example = "1")
    private int orderBagId;
    @ApiModelProperty(value = "货袋id", example = "1")
    private int bagId;
    @ApiModelProperty(value = "货车司机订单id", example = "1")
    private int taskOrderId;
    @ApiModelProperty(value = "类型", example = "1")
    private int type;

    @Override
    public String toString() {
        return "TaskBagOrder{" +
                "orderBagId=" + orderBagId +
                ", bagId=" + bagId +
                ", taskOrderId=" + taskOrderId +
                ", type=" + type +
                '}';
    }

    public int getOrderBagId() {
        return orderBagId;
    }

    public void setOrderBagId(int orderBagId) {
        this.orderBagId = orderBagId;
    }

    public int getBagId() {
        return bagId;
    }

    public void setBagId(int bagId) {
        this.bagId = bagId;
    }

    public int getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(int taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TaskBagOrder(int orderBagId, int bagId, int taskOrderId, int type) {
        this.orderBagId = orderBagId;
        this.bagId = bagId;
        this.taskOrderId = taskOrderId;
        this.type = type;
    }
}
