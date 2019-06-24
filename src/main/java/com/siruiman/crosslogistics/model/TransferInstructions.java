package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "郝腾")
public class TransferInstructions {
    @ApiModelProperty(value = "转运须知介绍", example = "instructions")
    private String instructions;
    @ApiModelProperty(value = "收件人", example = "recipient")
    private String recipient;
    @ApiModelProperty(value = "收件地址", example = "address")
    private String address;
    @ApiModelProperty(value = "联系电话", example = "mobile")
    private String mobile;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
