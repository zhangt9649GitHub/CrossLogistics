package com.siruiman.crosslogistics.model;

/**
 * @author 张占伟
 * @date 2019/1/21 10:10
 */
public class AdminPermission {
    private String url;//接口路径
    private String permissions; //该路径所需的权限
    private int accessId;

    public AdminPermission(String url, String permissions, int accessId) {
        this.url = url;
        this.permissions = permissions;
        this.accessId = accessId;
    }

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public AdminPermission() {
        super();
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
