package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserInfo;

public interface AppPersonalCenterService {
    /**
     * 查询个人资料
     * @param appUserId
     * @return
     */
    AppUserInfo selectAppUserInfo(int appUserId, String userType);

    /**
     * 查询个人资料(普通用户)
     * @param appUserId
     * @return
     */
    AppUserInfo selectAppUserInfoByPt(int appUserId);

    /**
     * 编辑个人资料
     * @param appUserInfo
     * @return
     */
    Integer editAppUserInfo(AppUserInfo appUserInfo);
}
