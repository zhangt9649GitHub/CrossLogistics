package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(description = "郝腾")
public class Goods implements Serializable {

    @ApiModelProperty(value = "货袋编号", example = "bagNumber")
    private String bagNumber;

    @ApiModelProperty(value = "入库仓库名称", example = "cnWarehouseName")
    private String intoWarehouseName;

    @ApiModelProperty(value = "出库仓库名称", example = "sgpWarehouseName")
    private String outWarehouseName;

    @ApiModelProperty(value = "承运方", example = "shipper")
    private String shipper;

    @ApiModelProperty(value = "实际货物长度", example = "1")
    private Float actualLong;

    @ApiModelProperty(value = "实际货物宽度", example = "1")
    private Float actualWidth;

    @ApiModelProperty(value = "实际货物高度", example = "1")
    private Float actualHeight;

    @ApiModelProperty(value = "实际货物重量", example = "1")
    private Float actualWeight;

    @ApiModelProperty(value = "货物数据", example = "goodData")
    private String goodData;

    @ApiModelProperty(value = "入库仓位编号", example = "cnWpNumber")
    private String intoWpNumber;

    @ApiModelProperty(value = "出库仓位编号", example = "sgpWpNumber")
    private String outWpNumber;

    @ApiModelProperty(value = "父节点id", example = "1")
    private Integer parentId;

    @ApiModelProperty(value = "转运合并单号", example = "mergeNumber")
    private String mergeNumber;

    @ApiModelProperty(value = "转运类型", example = "transportType")
    private String transportType;

    @ApiModelProperty(value = "金额", example = "1")
    private BigDecimal amount;

    @ApiModelProperty(value = "订单创建时间", example = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "是否打印 1.打印", example = "1")
    private Integer isPrint;

    @ApiModelProperty(value = "是否确认接收 1.是 2 否", example = "1")
    private Integer isReceiveGoods;

    @ApiModelProperty(value = "操作结果", example = "operateResult")
    private String operateResult;

    @ApiModelProperty(value = "操作结果编号", example = "1")
    private int operateResultNumber;

    @ApiModelProperty(value = "货物入库时图片表id", example = "1")
    private Integer goodsUfId;

    @ApiModelProperty(value = "货物照片地址集合", example = "images")
    private List<String> images;

    @ApiModelProperty(value = "备注 扣除原因", example = "comment")
    private String comment;

    @ApiModelProperty(value = "扣除状态 1 为扣除 2 未扣除", example = "1")
    private Integer deductState;

    @ApiModelProperty(value = "货物id", example = "0")
    private Integer goodsId;

    @ApiModelProperty(value = "来源", example = "from")
    private String from;

    @ApiModelProperty(value = "快递单号", example = "deliveryNumber")
    private String deliveryNumber;

    @ApiModelProperty(value = "三方物流单号", example = "tripartiteNumber")
    private String tripartiteNumber;

    @ApiModelProperty(value = "货物类型", example = "goodType")
    private String goodType;

    @ApiModelProperty(value = "邮编", example = "zipCode")
    private String zipCode;

    @ApiModelProperty(value = "入库仓库id", example = "1")
    private Integer intoWarehouseId;

    @ApiModelProperty(value = "入库仓位id", example = "1")
    private Integer intoWarehousePositionsId;

    @ApiModelProperty(value = "出库仓库id", example = "1")
    private Integer outWarehouseId;

    @ApiModelProperty(value = "出库仓位id", example = "1")
    private Integer outWarehousePositionsId;

    @ApiModelProperty(value = "货袋id", example = "1")
    private Integer bagId;

    @ApiModelProperty(value = "货物状态 1.正常 2 异常", example = "1")
    private Byte status;

    @ApiModelProperty(value = "是否为特殊货物 1是 0不是", example = "1")
    private Integer isSpecialGoods;

    @ApiModelProperty(value = "添加时间", example = "addTime")
    private Date addTime;

    @ApiModelProperty(value = "修改时间", example = "updateTime")
    private Date updateTime;

    @ApiModelProperty(value = "后台用户主键id", example = "1")
    private Integer adminUid;

    @ApiModelProperty(value = "组合转运的子订单单号集合", example = "children")
    private List<String> children;

    @ApiModelProperty(value = "出境物流方式", example = "exitWay")
    private String exitWay;

    @ApiModelProperty(value = "收货地址", example = "receiptAddress")
    private String receiptAddress;

    @ApiModelProperty(value = "收货联系人", example = "receiptContact")
    private String receiptContact;

    @ApiModelProperty(value = "收货联系人手机号", example = "receiptContactMobile")
    private String receiptContactMobile;

    @ApiModelProperty(value = "货品分类", example = "category")
    private String category;

    @ApiModelProperty(value = "app用户id", example = "1")
    private int appUserId;

    @ApiModelProperty(value = "共计几箱", example = "1")
    private Integer totalGoods;

    @ApiModelProperty(value = "备注", example = "remark")
    private String remark;

    @ApiModelProperty(value = "系统管理员用户类型id", example = "1")
    private int adminUserTypeId;

    @ApiModelProperty(value = "货值", example = "1")
    private BigDecimal itemValue;

    @ApiModelProperty(value = "图片存储表id", example = "1")
    private Integer ufId;

    @ApiModelProperty(value = "签收图片路径", example = "SignPictures")
    private String SignPictures;

    @ApiModelProperty(value = "用于搜索的创建时间", example = "seachTime")
    private String seachTime;

    @ApiModelProperty(value = "操作人所属id", example = "1")
    private Integer WarehouseId;

    public int getOperateResultNumber() {
        return operateResultNumber;
    }

    public void setOperateResultNumber(int operateResultNumber) {
        this.operateResultNumber = operateResultNumber;
    }

    public String getExitWay() {
        return exitWay;
    }

    public void setExitWay(String exitWay) {
        this.exitWay = exitWay;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    private static final long serialVersionUID = 1L;


    public Integer getGoodsId() {
        return goodsId;
    }


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }


    public String getDeliveryNumber() {
        return deliveryNumber;
    }


    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber == null ? null : deliveryNumber.trim();
    }


    public String getTripartiteNumber() {
        return tripartiteNumber;
    }


    public void setTripartiteNumber(String tripartiteNumber) {
        this.tripartiteNumber = tripartiteNumber == null ? null : tripartiteNumber.trim();
    }


    public String getGoodType() {
        return goodType;
    }


    public void setGoodType(String goodType) {
        this.goodType = goodType == null ? null : goodType.trim();
    }


    public String getZipCode() {
        return zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }


    public Integer getIntoWarehouseId() {
        return intoWarehouseId;
    }

    public void setIntoWarehouseId(Integer intoWarehouseId) {
        this.intoWarehouseId = intoWarehouseId;
    }

    public Integer getIntoWarehousePositionsId() {
        return intoWarehousePositionsId;
    }

    public void setIntoWarehousePositionsId(Integer intoWarehousePositionsId) {
        this.intoWarehousePositionsId = intoWarehousePositionsId;
    }

    public Integer getOutWarehouseId() {
        return outWarehouseId;
    }

    public void setOutWarehouseId(Integer outWarehouseId) {
        this.outWarehouseId = outWarehouseId;
    }

    public Integer getOutWarehousePositionsId() {
        return outWarehousePositionsId;
    }

    public void setOutWarehousePositionsId(Integer outWarehousePositionsId) {
        this.outWarehousePositionsId = outWarehousePositionsId;
    }

    public Integer getBagId() {
        return bagId;
    }


    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }


    public Byte getStatus() {
        return status;
    }


    public void setStatus(Byte status) {
        this.status = status;
    }


    public Integer getIsSpecialGoods() {
        return isSpecialGoods;
    }

    public void setIsSpecialGoods(Integer isSpecialGoods) {
        this.isSpecialGoods = isSpecialGoods;
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


    public Integer getAdminUid() {
        return adminUid;
    }


    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptContact() {
        return receiptContact;
    }

    public void setReceiptContact(String receiptContact) {
        this.receiptContact = receiptContact;
    }

    public String getReceiptContactMobile() {
        return receiptContactMobile;
    }

    public void setReceiptContactMobile(String receiptContactMobile) {
        this.receiptContactMobile = receiptContactMobile;
    }

    public Integer getIsReceiveGoods() {
        return isReceiveGoods;
    }

    public void setIsReceiveGoods(Integer isReceiveGoods) {
        this.isReceiveGoods = isReceiveGoods;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public Integer getGoodsUfId() {
        return goodsUfId;
    }

    public void setGoodsUfId(Integer goodsUfId) {
        this.goodsUfId = goodsUfId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Goods() {
        super();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMergeNumber() {
        return mergeNumber;
    }

    public void setMergeNumber(String mergeNumber) {
        this.mergeNumber = mergeNumber;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public String getIntoWarehouseName() {
        return intoWarehouseName;
    }

    public void setIntoWarehouseName(String intoWarehouseName) {
        this.intoWarehouseName = intoWarehouseName;
    }

    public String getOutWarehouseName() {
        return outWarehouseName;
    }

    public void setOutWarehouseName(String outWarehouseName) {
        this.outWarehouseName = outWarehouseName;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getGoodData() {
        return goodData = "" + this.actualLong + "*" + "" + this.actualWidth + "*" + "" + this.actualHeight + "," + "" + this.actualWeight;
    }

    public void setGoodData(String goodData) {
        this.goodData = this.goodData;
    }

    public String getIntoWpNumber() {
        return intoWpNumber;
    }

    public void setIntoWpNumber(String intoWpNumber) {
        this.intoWpNumber = intoWpNumber;
    }

    public String getOutWpNumber() {
        return outWpNumber;
    }

    public void setOutWpNumber(String outWpNumber) {
        this.outWpNumber = outWpNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Float getActualLong() {
        return actualLong;
    }

    public void setActualLong(Float actualLong) {
        this.actualLong = actualLong;
    }

    public Float getActualWidth() {
        return actualWidth;
    }

    public void setActualWidth(Float actualWidth) {
        this.actualWidth = actualWidth;
    }

    public Float getActualHeight() {
        return actualHeight;
    }

    public void setActualHeight(Float actualHeight) {
        this.actualHeight = actualHeight;
    }

    public Float getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Float actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Integer getDeductState() {
        return deductState;
    }

    public void setDeductState(Integer deductState) {
        this.deductState = deductState;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTotalGoods() {
        return totalGoods;
    }

    public void setTotalGoods(Integer totalGoods) {
        this.totalGoods = totalGoods;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAdminUserTypeId() {
        return adminUserTypeId;
    }

    public void setAdminUserTypeId(int adminUserTypeId) {
        this.adminUserTypeId = adminUserTypeId;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    public Integer getUfId() {
        return ufId;
    }

    public void setUfId(Integer ufId) {
        this.ufId = ufId;
    }

    public String getSignPictures() {
        return SignPictures;
    }

    public void setSignPictures(String signPictures) {
        SignPictures = signPictures;
    }

    public String getSeachTime() {
        return seachTime;
    }

    public void setSeachTime(String seachTime) {
        this.seachTime = seachTime;
    }

    public Integer getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        WarehouseId = warehouseId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsId=").append(goodsId);
        sb.append(", from=").append(from);
        sb.append(", deliveryNumber=").append(deliveryNumber);
        sb.append(", tripartiteNumber=").append(tripartiteNumber);
        sb.append(", goodType=").append(goodType);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", intoWarehouseId=").append(intoWarehouseId);
        sb.append(", intoWarehousePositionsId=").append(intoWarehousePositionsId);
        sb.append(", outWarehouseId=").append(outWarehouseId);
        sb.append(", outWarehousePositionsId=").append(outWarehousePositionsId);
        sb.append(", bagId=").append(bagId);
        sb.append(", status=").append(status);
        sb.append(", isSpecialGoods=").append(isSpecialGoods);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", adminUid=").append(adminUid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}