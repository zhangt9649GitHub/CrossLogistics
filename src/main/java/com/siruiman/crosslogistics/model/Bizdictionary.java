package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "郝腾")
public class Bizdictionary implements Serializable {


    @ApiModelProperty(value = "数据字典id", example = "0")
    private Integer id;


    @ApiModelProperty(value = "父节点id", example = "0")
    private Integer parentId;

    @ApiModelProperty(value = "节点名称", example = "bizName")
    private String bizName;

    @ApiModelProperty(value = "图片", example = "img")
    private String img;

    @ApiModelProperty(value = "顺序", example = "1")
    private Integer disorder;


    @ApiModelProperty(value = "结点值", example = "value")
    private String value;


    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getParentId() {
        return parentId;
    }


    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public String getBizName() {
        return bizName;
    }


    public void setBizName(String bizName) {
        this.bizName = bizName == null ? null : bizName.trim();
    }


    public String getImg() {
        return img;
    }


    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }


    public Integer getDisorder() {
        return disorder;
    }


    public void setDisorder(Integer disorder) {
        this.disorder = disorder;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", bizName=").append(bizName);
        sb.append(", img=").append(img);
        sb.append(", disorder=").append(disorder);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}