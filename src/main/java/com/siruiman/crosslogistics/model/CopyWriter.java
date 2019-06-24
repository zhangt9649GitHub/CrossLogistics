package com.siruiman.crosslogistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "郝腾")
public class CopyWriter implements Serializable {

    @ApiModelProperty(value = "文案id", example = "1")
    private Integer cwId;

    @ApiModelProperty(value = "文案名称", example = "cwName")
    private String cwName;

    @ApiModelProperty(value = "语言", example = "language")
    private String language;

    @ApiModelProperty(value = "类型", example = "type")
    private String type;

    @ApiModelProperty(value = "内容", example = "content")
    private String content;

    @ApiModelProperty(value = "图标", example = "icon")
    private String icon;

    @ApiModelProperty(value = "路径", example = "path")
    private String path;

    private static final long serialVersionUID = 1L;


    public Integer getCwId() {
        return cwId;
    }

    public void setCwId(Integer cwId) {
        this.cwId = cwId;
    }

    public String getCwName() {
        return cwName;
    }

    public void setCwName(String cwName) {
        this.cwName = cwName;
    }

    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", language=").append(language);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}