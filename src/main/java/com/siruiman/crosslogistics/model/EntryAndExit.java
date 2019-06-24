package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "郝腾")
public class EntryAndExit implements Serializable {

    @ApiModelProperty(value = "出入境物流途径id", example = "0")
    private Integer eaeId;

    @ApiModelProperty(value = "出境方式 1.空运 ，2.海运", example = "1")
    private Integer exitWay;

    @ApiModelProperty(value = "承运公司", example = "carrierCompany")
    private String carrierCompany;

    @ApiModelProperty(value = "公司地址", example = "address")
    private String address;

    @ApiModelProperty(value = "联系人", example = "contact")
    private String contact;

    @ApiModelProperty(value = "联系方式", example = "mobile")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private static final long serialVersionUID = 1L;


    public Integer getEaeId() {
        return eaeId;
    }


    public void setEaeId(Integer eaeId) {
        this.eaeId = eaeId;
    }


    public Integer getExitWay() {
        return exitWay;
    }


    public void setExitWay(Integer exitWay) {
        this.exitWay = exitWay;
    }


    public String getCarrierCompany() {
        return carrierCompany;
    }


    public void setCarrierCompany(String carrierCompany) {
        this.carrierCompany = carrierCompany == null ? null : carrierCompany.trim();
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }


    public String getContact() {
        return contact;
    }


    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eaeId=").append(eaeId);
        sb.append(", exitWay=").append(exitWay);
        sb.append(", carrierCompany=").append(carrierCompany);
        sb.append(", address=").append(address);
        sb.append(", contact=").append(contact);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}