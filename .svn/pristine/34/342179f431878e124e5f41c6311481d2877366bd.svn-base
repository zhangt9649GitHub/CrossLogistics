package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ApiModel(description = "郝腾")
public class UploadFiles implements Serializable {
    @ApiModelProperty(value = "图片存储表id", example = "1")
    private Integer ufId;

    @ApiModelProperty(value = "图片存储表类型 1.身份证正面照 2.身份证背面照 3.护照正面照 4.工作证正面照 5.工作证反面照 6.驾驶证正面照 7.货车照", example = "1")
    private Integer type;

    @ApiModelProperty(value = "图片存储路径", example = "ufSavePath")
    private String ufSavePath;


    private static final long serialVersionUID = 1L;

    public Integer getUfId() {
        return ufId;
    }

    public void setUfId(Integer ufId) {
        this.ufId = ufId;
    }

    public String getUfSavePath() {
        return ufSavePath;
    }


    public void setUfSavePath(String ufSavePath) {
        this.ufSavePath = ufSavePath == null ? null : ufSavePath.trim();
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
        sb.append(", ufId=").append(ufId);
        sb.append(", ufSavePath=").append(ufSavePath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}