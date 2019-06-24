package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;

import java.io.Serializable;


/**
 * @author 张占伟
 * @date 2019/3/21 14:05
 *            后台添加三方区别 用户类型实体类
 */
@ApiModel(description = "张占伟")
public class AdminUserType implements Serializable {

    private Integer adminUserTypeId;


    private String name;


    private String comment;


    private Integer type;


    private static final long serialVersionUID = 1L;


    public Integer getAdminUserTypeId() {
        return adminUserTypeId;
    }


    public void setAdminUserTypeId(Integer adminUserTypeId) {
        this.adminUserTypeId = adminUserTypeId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminUserTypeId=").append(adminUserTypeId);
        sb.append(", name=").append(name);
        sb.append(", comment=").append(comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}