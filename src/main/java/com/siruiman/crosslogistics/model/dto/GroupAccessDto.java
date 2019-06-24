package com.siruiman.crosslogistics.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
@ApiModel(description="郭阳")
public class GroupAccessDto {
    @ApiModelProperty(value = "权限组id*默认传0*", example = "1")
    private Integer groupId;
    @NotBlank(message="权限组名称不能为空")
    @ApiModelProperty(value = "权限组名称", example = "groupName")
    private String groupName;
    @NotBlank(message="权限描述不能为空")
    @ApiModelProperty(value = "权限描述", example="groupText")
    private String groupText;
    @ApiModelProperty(value = "后台用户id*默认传0*", example="1")
    private Integer adminUid;
    private List<Integer> accessIds;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupText() {
        return groupText;
    }

    public void setGroupText(String groupText) {
        this.groupText = groupText;
    }

    public int getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(int adminUid) {
        this.adminUid = adminUid;
    }

    public List<Integer> getAccessIds() {
        return accessIds;
    }

    public void setAccessIds(List<Integer> accessIds) {
        this.accessIds = accessIds;
    }

    @Override
    public String toString() {
        return "GroupAccessDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupText='" + groupText + '\'' +
                ", adminUid=" + adminUid +
                ", accessIds=" + accessIds +
                '}';
    }
}
