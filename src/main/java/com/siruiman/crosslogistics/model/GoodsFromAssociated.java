package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "郝腾")
public class GoodsFromAssociated implements Serializable {

    @ApiModelProperty(value = "货物表单关联表id", example = "1")
    private Integer gfId;

    @ApiModelProperty(value = "货物id", example = "1")
    private Integer goodsId;

    @ApiModelProperty(value = "表单id", example = "1")
    private Integer formId;


    private static final long serialVersionUID = 1L;


    public Integer getGfId() {
        return gfId;
    }


    public void setGfId(Integer gfId) {
        this.gfId = gfId;
    }


    public Integer getGoodsId() {
        return goodsId;
    }


    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public Integer getFormId() {
        return formId;
    }


    public void setFormId(Integer formId) {
        this.formId = formId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gfId=").append(gfId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", formId=").append(formId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}