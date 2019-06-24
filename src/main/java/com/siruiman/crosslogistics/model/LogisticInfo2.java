package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付宝异步通知返回结果实体类
 * @author 张占伟
 * @date 2019/4/9 14:44
 */
@ApiModel(description = "张占伟")
public class LogisticInfo2 implements Serializable,Comparable<LogisticInfo2>{
    @ApiModelProperty(value="物流信息id", example = "0")
    private Integer logisticInfoId;

    @ApiModelProperty(value="创建时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date createTime;

    @ApiModelProperty(value="货物id", example = "0")
    private Integer goodsId;

    @ApiModelProperty(value="货袋id", example = "0")
    private Integer bagId;

    @ApiModelProperty(value="操作结果", example = "operateResult")
    private String operateResult;

    @ApiModelProperty(value="操作类型", example = "operateType")
    private String operateType;

    @ApiModelProperty(value="操作说明", example = "operateComment")
    private String operateComment;

    @ApiModelProperty(value="参数", example = "parameter")
    private String parameter;

    @ApiModelProperty(value="操作人姓名", example = "operateName")
    private String operateName;

    @ApiModelProperty(value="员工id", example = "1")
    private int staffId;

    @ApiModelProperty(value="app用户id", example = "1")
    private int appUserId;

    @ApiModelProperty(value="后台用户主键id", example = "1")
    private Integer adminUid;

    @ApiModelProperty(value="第几箱", example = "1")
    private Integer numOfBoxes;

    public Integer getNumOfBoxes() {
        return numOfBoxes;
    }

    public void setNumOfBoxes(Integer numOfBoxes) {
        this.numOfBoxes = numOfBoxes;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    private static final long serialVersionUID = 1L;


    public Integer getLogisticInfoId() {
        return logisticInfoId;
    }


    public void setLogisticInfoId(Integer logisticInfoId) {
        this.logisticInfoId = logisticInfoId;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getGoodsId() {
        return goodsId;
    }


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public Integer getBagId() {
        return bagId;
    }


    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }


    public String getOperateResult() {
        return operateResult;
    }


    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult == null ? null : operateResult.trim();
    }


    public String getOperateType() {
        return operateType;
    }


    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }


    public String getOperateComment() {
        return operateComment;
    }


    public void setOperateComment(String operateComment) {
        this.operateComment = operateComment == null ? null : operateComment.trim();
    }


    public String getParameter() {
        return parameter;
    }


    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logisticInfoId=").append(logisticInfoId);
        sb.append(", createTime=").append(createTime);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", bagId=").append(bagId);
        sb.append(", operateResult=").append(operateResult);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateComment=").append(operateComment);
        sb.append(", parameter=").append(parameter);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int compareTo(LogisticInfo2 o) {
        int i = o.getLogisticInfoId() - this.getLogisticInfoId();
        return i;

    }
}