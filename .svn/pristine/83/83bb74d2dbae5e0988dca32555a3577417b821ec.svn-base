package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "郝腾")
public class MessagePush implements Serializable {

    @ApiModelProperty(value = "推送id", example = "1")
    private Integer pushId;

    @ApiModelProperty(value = "app用户id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value = "小车订单id", example = "1")
    private Integer taskOrderId;

    @ApiModelProperty(value = "第一次推送时间", example = "1")
    private Date fristPushTime;

    @ApiModelProperty(value = "再次推送时间", example = "1")
    private Date secondPushTime;

    @ApiModelProperty(value = "是否需要再次推送 1,是 2，不是", example = "1")
    private Integer isPush;

    @ApiModelProperty(value = "消息id", example = "1")
    private Integer messageId;


    private static final long serialVersionUID = 1L;


    public Integer getPushId() {
        return pushId;
    }


    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }


    public Integer getAppUserId() {
        return appUserId;
    }


    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }


    public Integer getTaskOrderId() {
        return taskOrderId;
    }


    public void setTaskOrderId(Integer taskOrderId) {
        this.taskOrderId = taskOrderId;
    }


    public Date getFristPushTime() {
        return fristPushTime;
    }


    public void setFristPushTime(Date fristPushTime) {
        this.fristPushTime = fristPushTime;
    }


    public Date getSecondPushTime() {
        return secondPushTime;
    }


    public void setSecondPushTime(Date secondPushTime) {
        this.secondPushTime = secondPushTime;
    }


    public Integer getIsPush() {
        return isPush;
    }


    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }


    public Integer getMessageId() {
        return messageId;
    }


    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pushId=").append(pushId);
        sb.append(", appUserId=").append(appUserId);
        sb.append(", taskOrderId=").append(taskOrderId);
        sb.append(", fristPushTime=").append(fristPushTime);
        sb.append(", secondPushTime=").append(secondPushTime);
        sb.append(", isPush=").append(isPush);
        sb.append(", messageId=").append(messageId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}