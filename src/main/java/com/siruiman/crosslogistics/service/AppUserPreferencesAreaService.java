package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserPreferencesArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserPreferencesAreaService {
    /**
     * 根据用户id和用户类型查询默认的偏好区域
     *
     * @param appUserId
     * @param userType
     * @return
     */
    List<AppUserPreferencesArea> selectDefaultAreaByUserId(int appUserId, String userType);

    /**
     * 修改用户偏好地区
     *
     * @param appUserPreferencesArea
     */
    void updateAppUserPreferencesArea(AppUserPreferencesArea appUserPreferencesArea);

    /**
     * 添加用户偏好地区
     *
     * @param appUserPreferencesArea
     */
    void insertAppUserPreferencesArea(AppUserPreferencesArea appUserPreferencesArea);

    /**
     * 根据用户id和用户类型删除偏好区域
     *
     * @param appUserId
     * @param userType
     */
    void deleteDefaultAreaByUserId(int appUserId, String userType);


    /**
     * 根据区域id删除 用户偏好区域
     * @param areaId
     */
    void deleteByAreaId(int areaId );
}
