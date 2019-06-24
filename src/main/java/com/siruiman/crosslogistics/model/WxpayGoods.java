package com.siruiman.crosslogistics.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Mapper
@Repository
public class WxpayGoods implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxpay_goods.wxpay_gd_number_id
     *
     * @mbggenerated
     */
    private Integer wxpayGdNumberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxpay_goods.delivery_number
     *
     * @mbggenerated
     */
    private String deliveryNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxpay_goods.sys_delivery_number
     *
     * @mbggenerated
     */
    private String sysDeliveryNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wxpay_goods.is_pay_state
     *
     * @mbggenerated
     */
    private Integer isPayState;


    private String dealTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wxpay_goods
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxpay_goods.wxpay_gd_number_id
     *
     * @return the value of wxpay_goods.wxpay_gd_number_id
     *
     * @mbggenerated
     */
    public Integer getWxpayGdNumberId() {
        return wxpayGdNumberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxpay_goods.wxpay_gd_number_id
     *
     * @param wxpayGdNumberId the value for wxpay_goods.wxpay_gd_number_id
     *
     * @mbggenerated
     */
    public void setWxpayGdNumberId(Integer wxpayGdNumberId) {
        this.wxpayGdNumberId = wxpayGdNumberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxpay_goods.delivery_number
     *
     * @return the value of wxpay_goods.delivery_number
     *
     * @mbggenerated
     */
    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxpay_goods.delivery_number
     *
     * @param deliveryNumber the value for wxpay_goods.delivery_number
     *
     * @mbggenerated
     */
    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber == null ? null : deliveryNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxpay_goods.sys_delivery_number
     *
     * @return the value of wxpay_goods.sys_delivery_number
     *
     * @mbggenerated
     */
    public String getSysDeliveryNumber() {
        return sysDeliveryNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxpay_goods.sys_delivery_number
     *
     * @param sysDeliveryNumber the value for wxpay_goods.sys_delivery_number
     *
     * @mbggenerated
     */
    public void setSysDeliveryNumber(String sysDeliveryNumber) {
        this.sysDeliveryNumber = sysDeliveryNumber == null ? null : sysDeliveryNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wxpay_goods.is_pay_state
     *
     * @return the value of wxpay_goods.is_pay_state
     *
     * @mbggenerated
     */
    public Integer getIsPayState() {
        return isPayState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wxpay_goods.is_pay_state
     *
     * @param isPayState the value for wxpay_goods.is_pay_state
     *
     * @mbggenerated
     */
    public void setIsPayState(Integer isPayState) {
        this.isPayState = isPayState;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wxpay_goods
     *
     * @mbggenerated
     */


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wxpayGdNumberId=").append(wxpayGdNumberId);
        sb.append(", deliveryNumber=").append(deliveryNumber);
        sb.append(", sysDeliveryNumber=").append(sysDeliveryNumber);
        sb.append(", isPayState=").append(isPayState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}