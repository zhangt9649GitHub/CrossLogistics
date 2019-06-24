package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(description = "郝腾")
public class GoodsDetails implements Serializable {
    @ApiModelProperty(value = "货物详情id", example = "1")
    private Integer gdId;

    @ApiModelProperty(value = "来源", example = "from")
    private String from;

    @ApiModelProperty(value = "货物类型", example = "goodType")
    private String goodType;

    @ApiModelProperty(value = "是否为特殊货物 1是 2不是", example = "1")
    private Integer isSpecialGoods;

    @ApiModelProperty(value = "快递单号", example = "deliveryNumber")
    private String deliveryNumber;

    @ApiModelProperty(value = "三方物流单号", example = "tripartiteNumber")
    private String tripartiteNumber;

    @ApiModelProperty(value = "处理描述", example = "dealComment")
    private String dealComment;

    @ApiModelProperty(value = "异常类型", example = "warningType")
    private String warningType;

    @ApiModelProperty(value = "货袋编号", example = "bagNumber")
    private String bagNumber;

    @ApiModelProperty(value = "金额", example = "1")
    private BigDecimal amount;

    @ApiModelProperty(value = "新加坡地区名字", example = "dsafa")
    private String singaporeAreaName;

    @ApiModelProperty(value = "出境物流方式", example = "exitWay")
    private String exitWay;

    @ApiModelProperty(value = "1.空运 2.海运", example = "1")
    private Integer exitWayNumber;

    @ApiModelProperty(value = "货物id", example = "0")
    private Integer goodsId;

    @ApiModelProperty(value = "三方快递单上货物长度", example = "1")
    private Float tripartiteLong;

    @ApiModelProperty(value = "三方快递单上货物宽度", example = "1")
    private Float tripartiteWidth;

    @ApiModelProperty(value = "三方快递单上货物高度", example = "1")
    private Float tripartiteHeight;

    @ApiModelProperty(value = "三方快递单上货物重量", example = "1")
    private Float tripartiteWeight;

    @ApiModelProperty(value = "三方提供的（长*宽*高）", example = "2.1*1.8*0.6")
    private String tripartitevolume;

    @ApiModelProperty(value = "实际的（长*宽*高）", example = "2.1*1.8*0.6")
    private String actualvolume;

    @ApiModelProperty(value = "实际货物长度", example = "1")
    private Float actualLong;

    @ApiModelProperty(value = "实际货物宽度", example = "1")
    private Float actualWidth;

    @ApiModelProperty(value = "实际货物高度", example = "1")
    private Float actualHeight;

    @ApiModelProperty(value = "实际货物重量", example = "1")
    private Float actualWeight;


    @ApiModelProperty(value = "发货地址", example = "shipAddress")
    private String shipAddress;

    @ApiModelProperty(value = "发货联系人", example = "shipContact")
    private String shipContact;

    @ApiModelProperty(value = "发货人手机号", example = "shipContactMobile")
    private String shipContactMobile;

    @ApiModelProperty(value = "收货地址", example = "receiptAddress")
    private String receiptAddress;

    @ApiModelProperty(value = "收货联系人", example = "receiptContact")
    private String receiptContact;

    @ApiModelProperty(value = "收货联系人手机号", example = "receiptContactMobile")
    private String receiptContactMobile;

    @ApiModelProperty(value = "承运方", example = "shipper")
    private String shipper;

    @ApiModelProperty(value = "异常状态", example = "无异常，处理中，异常")
    private String warningState;

    @ApiModelProperty(value = "异常状态编号 1.无异常 2.异常 3.正在处理中", example = "1")
    private Integer warningStateNumber;

    @ApiModelProperty(value = "异常描述", example = "abnormalText")
    private String abnormalText;

    @ApiModelProperty(value = "添加时间", example = "yyyy-MM-dd-HH-mm-ss")
    private String addTime;

    @ApiModelProperty(value = "修改时间", example = "yyyy-MM-dd-HH-mm-ss")
    private Date updateTime;

    @ApiModelProperty(value = "后台用户主键id", example = "0")
    private Integer adminUid;

    @ApiModelProperty(value = "邮编", example = "zipCode")
    private String zipCode;

    @ApiModelProperty(value = "入库仓库名称", example = "cnWarehouseName")
    private String intoWarehouseName;

    @ApiModelProperty(value = "出库仓库名称", example = "sgpWarehouseName")
    private String outWarehouseName;

    @ApiModelProperty(value = "入库仓位编号", example = "cnWpNumber")
    private String intoWpNumber;

    @ApiModelProperty(value = "出库仓位编号", example = "sgpWpNumber")
    private String outWpNumber;

    @ApiModelProperty(value = "app用户id", example = "1")
    private int appUserId;

    @ApiModelProperty(value = "货物状态", example = "已到仓，待集运，转运中，已完成")
    private String goodState;

    @ApiModelProperty(value = "支付状态", example = "待支付，已支付")
    private String paymentStatus;

    @ApiModelProperty(value = "转运类型", example = "transportType")
    private String transportType;

    @ApiModelProperty(value = "订单创建时间", example = "createTime")
    private String createTime;

    @ApiModelProperty(value = "订单结束时间", example = "endTime")
    private String endTime;

    @ApiModelProperty(value = "组合转运的子订单单号集合", example = "children")
    private List<String> children = new ArrayList<>();

    @ApiModelProperty(value = "货品分类", example = "category")
    private String category;

    @ApiModelProperty(value = "是否加急", example = "isUrgent")
    private String isUrgent;

    @ApiModelProperty(value = "11.是，12.否", example = "11")
    private Integer isUrgentNumber;

    @ApiModelProperty(value = "货品名称", example = "itemName")
    private String itemName;

    @ApiModelProperty(value = "货品说明", example = "itemExplain")
    private String itemExplain;

    @ApiModelProperty(value = "货值", example = "1")
    private BigDecimal itemValue;

    @ApiModelProperty(value = "支付方式", example = "payWay")
    private String payWay;

    @ApiModelProperty(value = "用户编号", example = "number")
    private String number;

    @ApiModelProperty(value = "集结点编号", example = "rallyPointNumber")
    private String rallyPointNumber;

    @ApiModelProperty(value = "集结点名字", example = "rallyPointName")
    private String rallyPointName;

    @ApiModelProperty(value = "父节点id", example = "1")
    private Integer parentId;

    @ApiModelProperty(value = "是否确认接收 1.是 2 否", example = "1")
    private Integer isReceiveGoods;

    @ApiModelProperty(value = "新加坡地区id", example = "0")
    private Integer singaporeAreaId;

    @ApiModelProperty(value = "集结点主键id", example = "1")
    private Integer rallyPointId;

    @ApiModelProperty(value = "区域", example = "singaporeArea")
    private SingaporeArea singaporeArea;

    @ApiModelProperty(value = "集结点", example = "rallyPoint")
    private RallyPoint rallyPoint;

    @ApiModelProperty(value = "共计几箱", example = "1")
    private Integer totalGoods;

    @ApiModelProperty(value = "需要支付GST的价格", example = "1")
    private BigDecimal gstPrice;

    @ApiModelProperty(value = "货物派送时间", example = "sendTime")
    private Date sendTime;

    @ApiModelProperty(value = "是否货到付款 1.否 2.是", example = "1")
    private int isArrivalPay;

    @ApiModelProperty(value = "缴纳GST税提示语", example = "sendTime")
    private String prompt;

    @ApiModelProperty(value = "派送人（合作的三方公司名称、小车骑手姓名+userId、货车司机姓名+userId）", example = "sendPeople")
    private String sendPeople;

    @ApiModelProperty(value = "合计", example = "1")
    private BigDecimal total;

    @ApiModelProperty(value = "货物状态 1.正常 2 异常", example = "1")
    private Byte status;

    @ApiModelProperty(value = "物流进程集合", example = "logisticInfoList")
    private List<LogisticInfo> logisticInfoList = new ArrayList<>();

    @ApiModelProperty(value = "默认的收货地址类", example = "appUserAddress")
    private  AppUserAddress appUserAddress;

    @ApiModelProperty(value = "第几箱", example = "1")
    private  Integer order;

    @ApiModelProperty(value = "货值单位", example = "valueUnit")
    private  String valueUnit;

    @ApiModelProperty(value = "货袋id", example = "1")
    private Integer bagId;

    @ApiModelProperty(value="仓库id", example = "1")
    private Integer warehouseId;

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemExplain() {
        return itemExplain;
    }

    public void setItemExplain(String itemExplain) {
        this.itemExplain = itemExplain;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    public String getTripartitevolume() {
        return tripartitevolume = "" + this.tripartiteLong + "x" + "" + this.tripartiteWidth + "x" + "" + this.tripartiteHeight;
    }

    public void setTripartitevolume(String tripartitevolume) {
        this.tripartitevolume = tripartitevolume;
    }

    public String getActualvolume() {
        return actualvolume = "" + this.actualLong + "x" + "" + this.actualWidth + "x" + "" + this.actualHeight;
    }

    public void setActualvolume(String actualvolume) {
        this.actualvolume = actualvolume;
    }

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getGoodState() {
        return goodState;
    }

    public void setGoodState(String goodState) {
        this.goodState = goodState;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    private static final long serialVersionUID = 1L;


    public Integer getGdId() {
        return gdId;
    }


    public void setGdId(Integer gdId) {
        this.gdId = gdId;
    }


    public Integer getGoodsId() {
        return goodsId;
    }

    public GoodsDetails() {
        super();
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public Float getTripartiteLong() {
        return tripartiteLong;
    }


    public void setTripartiteLong(Float tripartiteLong) {
        this.tripartiteLong = tripartiteLong;
    }


    public Float getTripartiteWidth() {
        return tripartiteWidth;
    }


    public void setTripartiteWidth(Float tripartiteWidth) {
        this.tripartiteWidth = tripartiteWidth;
    }


    public Float getTripartiteHeight() {
        return tripartiteHeight;
    }


    public void setTripartiteHeight(Float tripartiteHeight) {
        this.tripartiteHeight = tripartiteHeight;
    }


    public Float getTripartiteWeight() {
        return tripartiteWeight;
    }


    public void setTripartiteWeight(Float tripartiteWeight) {
        this.tripartiteWeight = tripartiteWeight;
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


    public String getShipAddress() {
        return shipAddress;
    }


    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress == null ? null : shipAddress.trim();
    }


    public String getShipContact() {
        return shipContact;
    }


    public void setShipContact(String shipContact) {
        this.shipContact = shipContact == null ? null : shipContact.trim();
    }


    public String getShipContactMobile() {
        return shipContactMobile;
    }


    public void setShipContactMobile(String shipContactMobile) {
        this.shipContactMobile = shipContactMobile == null ? null : shipContactMobile.trim();
    }


    public String getReceiptAddress() {
        return receiptAddress;
    }


    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress == null ? null : receiptAddress.trim();
    }


    public String getReceiptContact() {
        return receiptContact;
    }


    public void setReceiptContact(String receiptContact) {
        this.receiptContact = receiptContact == null ? null : receiptContact.trim();
    }


    public String getReceiptContactMobile() {
        return receiptContactMobile;
    }


    public void setReceiptContactMobile(String receiptContactMobile) {
        this.receiptContactMobile = receiptContactMobile == null ? null : receiptContactMobile.trim();
    }


    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getWarningState() {
        return warningState;
    }

    public void setWarningState(String warningState) {
        this.warningState = warningState;
    }

    public String getAbnormalText() {
        return abnormalText;
    }


    public void setAbnormalText(String abnormalText) {
        this.abnormalText = abnormalText == null ? null : abnormalText.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
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

    public String getExitWay() {
        return exitWay;
    }

    public void setExitWay(String exitWay) {
        this.exitWay = exitWay;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSingaporeAreaName() {
        return singaporeAreaName;
    }

    public void setSingaporeAreaName(String singaporeAreaName) {
        this.singaporeAreaName = singaporeAreaName;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public String getDealComment() {
        return dealComment;
    }

    public void setDealComment(String dealComment) {
        this.dealComment = dealComment;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public Integer getIsSpecialGoods() {
        return isSpecialGoods;
    }

    public void setIsSpecialGoods(Integer isSpecialGoods) {
        this.isSpecialGoods = isSpecialGoods;
    }

    public String getTripartiteNumber() {
        return tripartiteNumber;
    }

    public void setTripartiteNumber(String tripartiteNumber) {
        this.tripartiteNumber = tripartiteNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRallyPointNumber() {
        return rallyPointNumber;
    }

    public void setRallyPointNumber(String rallyPointNumber) {
        this.rallyPointNumber = rallyPointNumber;
    }

    public String getRallyPointName() {
        return rallyPointName;
    }

    public void setRallyPointName(String rallyPointName) {
        this.rallyPointName = rallyPointName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsReceiveGoods() {
        return isReceiveGoods;
    }

    public void setIsReceiveGoods(Integer isReceiveGoods) {
        this.isReceiveGoods = isReceiveGoods;
    }

    public Integer getSingaporeAreaId() {
        return singaporeAreaId;
    }

    public void setSingaporeAreaId(Integer singaporeAreaId) {
        this.singaporeAreaId = singaporeAreaId;
    }

    public Integer getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(Integer rallyPointId) {
        this.rallyPointId = rallyPointId;
    }

    public SingaporeArea getSingaporeArea() {
        return singaporeArea;
    }

    public void setSingaporeArea(SingaporeArea singaporeArea) {
        this.singaporeArea = singaporeArea;
    }

    public RallyPoint getRallyPoint() {
        return rallyPoint;
    }

    public void setRallyPoint(RallyPoint rallyPoint) {
        this.rallyPoint = rallyPoint;
    }

    public Integer getExitWayNumber() {
        return exitWayNumber;
    }

    public void setExitWayNumber(Integer exitWayNumber) {
        this.exitWayNumber = exitWayNumber;
    }

    public Integer getIsUrgentNumber() {
        return isUrgentNumber;
    }

    public void setIsUrgentNumber(Integer isUrgentNumber) {
        this.isUrgentNumber = isUrgentNumber;
    }

    public Integer getWarningStateNumber() {
        return warningStateNumber;
    }

    public void setWarningStateNumber(Integer warningStateNumber) {
        this.warningStateNumber = warningStateNumber;
    }

    public Integer getTotalGoods() {
        return totalGoods;
    }

    public void setTotalGoods(Integer totalGoods) {
        this.totalGoods = totalGoods;
    }

    public BigDecimal getGstPrice() {
        return gstPrice;
    }

    public void setGstPrice(BigDecimal gstPrice) {
        this.gstPrice = gstPrice;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getIsArrivalPay() {
        return isArrivalPay;
    }

    public void setIsArrivalPay(int isArrivalPay) {
        this.isArrivalPay = isArrivalPay;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSendPeople() {
        return sendPeople;
    }

    public void setSendPeople(String sendPeople) {
        this.sendPeople = sendPeople;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    public List<LogisticInfo> getLogisticInfoList() {
        return logisticInfoList;
    }

    public void setLogisticInfoList(List<LogisticInfo> logisticInfoList) {
        this.logisticInfoList = logisticInfoList;
    }

    public AppUserAddress getAppUserAddress() {
        return appUserAddress;
    }

    public void setAppUserAddress(AppUserAddress appUserAddress) {
        this.appUserAddress = appUserAddress;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public GoodsDetails(Integer gdId, String goodType, int isSpecialGoods, String deliveryNumber, Integer goodsId, Float tripartiteLong, Float tripartiteWidth, Float tripartiteHeight, Float tripartiteWeight, Float actualLong, Float actualWidth, Float actualHeight, Float actualWeight, String shipAddress, String shipContact, String shipContactMobile, String receiptAddress, String receiptContact, String receiptContactMobile, String shipper, String warningState, String abnormalText, Date addTime, Date updateTime, Integer adminUid, String zipCode) {
        this.gdId = gdId;
        this.goodType = goodType;
        this.isSpecialGoods = isSpecialGoods;
        this.deliveryNumber = deliveryNumber;
        this.goodsId = goodsId;
        this.tripartiteLong = tripartiteLong;
        this.tripartiteWidth = tripartiteWidth;
        this.tripartiteHeight = tripartiteHeight;
        this.tripartiteWeight = tripartiteWeight;
        this.actualLong = actualLong;
        this.actualWidth = actualWidth;
        this.actualHeight = actualHeight;
        this.actualWeight = actualWeight;
        this.shipAddress = shipAddress;
        this.shipContact = shipContact;
        this.shipContactMobile = shipContactMobile;
        this.receiptAddress = receiptAddress;
        this.receiptContact = receiptContact;
        this.receiptContactMobile = receiptContactMobile;
        this.shipper = shipper;
        this.warningState = warningState;
        this.abnormalText = abnormalText;
        this.updateTime = updateTime;
        this.adminUid = adminUid;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gdId=").append(gdId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", tripartiteLong=").append(tripartiteLong);
        sb.append(", tripartiteWidth=").append(tripartiteWidth);
        sb.append(", tripartiteHeight=").append(tripartiteHeight);
        sb.append(", tripartiteWeight=").append(tripartiteWeight);
        sb.append(", actualLong=").append(actualLong);
        sb.append(", actualWidth=").append(actualWidth);
        sb.append(", actualHeight=").append(actualHeight);
        sb.append(", actualWeight=").append(actualWeight);
        sb.append(", shipAddress=").append(shipAddress);
        sb.append(", shipContact=").append(shipContact);
        sb.append(", shipContactMobile=").append(shipContactMobile);
        sb.append(", receiptAddress=").append(receiptAddress);
        sb.append(", receiptContact=").append(receiptContact);
        sb.append(", receiptContactMobile=").append(receiptContactMobile);
        sb.append(", warningState=").append(warningState);
        sb.append(", abnormalText=").append(abnormalText);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", adminUid=").append(adminUid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}