package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserInfo;
import com.siruiman.crosslogistics.model.SingaporeAreaBuilding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppPersonalCenterMapper {
    /**
     * 查询个人资料
     * @param appUserId
     * @return
     */
    AppUserInfo selectAppUserInfo(@Param("appUserId") int appUserId, @Param("userType") String userType);

    /**
     * 查询个人资料(普通用户)
     * @param appUserId
     * @return
     */
    AppUserInfo selectAppUserInfoByPt(@Param("appUserId") int appUserId);

    /**
     * 编辑个人资料
     * @param appUserInfo
     * @return
     */
    Integer editAppUserInfo(@Param("appUserInfo") AppUserInfo appUserInfo);

    /**
     * 根据邮编查询大楼经纬度
     * @param zipCode
     * @return
     */
    SingaporeAreaBuilding selectSingaporeAreaBuilding(@Param("zipCode") String zipCode);
}
