package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "郝腾")
public class Message implements Serializable {

    @ApiModelProperty(value = "消息id", example = "1")
    private Integer messageId;

    @ApiModelProperty(value = "app用户id", example = "1")
    private Integer appUserId;

    @ApiModelProperty(value = "标题", example = "title")
    private String title;

    @ApiModelProperty(value = "内容", example = "content")
    private String content;

    @ApiModelProperty(value = "类型", example = "订单消息")
    private String type;

    @ApiModelProperty(value = "分类 普通用户 小车骑手 货车司机", example = "普通用户")
    private String category;

    @ApiModelProperty(value = "状态", example = "已读")
    private String state;

    @ApiModelProperty(value = "添加时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date addTime;

    @ApiModelProperty(value = "修改时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date updateTime;

    @ApiModelProperty(value = "英文标题", example = "enTitle")
    private String enTitle;

    @ApiModelProperty(value = "英文内容", example = "enContent")
    private String enContent;

    @ApiModelProperty(value = "英文类型", example = "enType")
    private String enType;

    @ApiModelProperty(value = "备注", example = "1")
    private String comment;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private static final long serialVersionUID = 1L;


    public Integer getMessageId() {
        return messageId;
    }


    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }


    public Integer getAppUserId() {
        return appUserId;
    }


    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }


    public Date getAddTime() {
        return addTime;
    }


    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getEnContent() {
        return enContent;
    }

    public void setEnContent(String enContent) {
        this.enContent = enContent;
    }

    public String getEnType() {
        return enType;
    }

    public void setEnType(String enType) {
        this.enType = enType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", appUserId=").append(appUserId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", type=").append(type);
        sb.append(", state=").append(state);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}