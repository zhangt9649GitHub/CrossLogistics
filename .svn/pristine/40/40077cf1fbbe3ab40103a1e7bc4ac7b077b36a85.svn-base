package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description="郭阳")
public class Access {
    @ApiModelProperty(value = "权限主键id", example = "1")
    private Integer accessId;
    @ApiModelProperty(value = "权限名称", example = "accessName")
    private String accessName;
    @ApiModelProperty(value = "所属权限模块id", example="1")
    private Integer accessModuleId;
    @ApiModelProperty(value = "url", example="url")
    private String url;
    @ApiModelProperty(value = "权限类型1菜单,2按钮,3其他", example="1")
    private Integer type;
    private List<Access> accesses = new ArrayList<>();

    @ApiModelProperty(value = "标识", example="0")
    private Integer mark;

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public Integer getAccessModuleId() {
        return accessModuleId;
    }

    public void setAccessModuleId(Integer accessModuleId) {
        this.accessModuleId = accessModuleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<Access> accesses) {
        this.accesses = accesses;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Access{" +
                "accessId=" + accessId +
                ", accessName='" + accessName + '\'' +
                ", accessModuleId=" + accessModuleId +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", accesses=" + accesses +
                ", mark=" + mark +
                '}';
    }
}
