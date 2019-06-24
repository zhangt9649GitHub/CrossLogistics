package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserCertification;

import java.util.List;

public interface AppUserCertificationService {
    /**
     * app用户审核列表
     *
     * @return
     */
    List<AppUserCertification> selectAppUserCertificationAll(String search, String userType);

    /**
     * app用户审核列表条数
     *
     * @return
     */
    Integer count(String search, String userType);

    /**
     * 审核用户
     *
     * @param certificationId
     * @param userCertificationStatus
     * @return
     */
    Integer checkAppUser(int certificationId, String userCertificationStatus, int appUserId);

    /**
     * 查询用户审核信息
     *
     * @param certificationId
     * @return
     */
    AppUserCertification selectAppUserCertification(int certificationId);

    /**
     * 添加审核信息
     *
     * @param appUserCertification
     */
    void insertAppUserCertification(AppUserCertification appUserCertification);

    /**
     * 获取审核列表
     *
     * @param userTrueName
     * @param userType
     * @param number
     * @return
     */
    List<AppUserCertification> selectAppUserCertificationList(String userTrueName, String userType, String number);

    /**
     * 获取审核列表条数
     *
     * @param userTrueName
     * @param userType
     * @param number
     * @return
     */
    int selectCountAppUserCertificationList(String userTrueName, String userType, String number);

    /**
     * 根据id和用户类型查询用户审核详细信息
     *
     * @param certificationId
     * @return
     */
    AppUserCertification selectAppUserCertificationDetails(int certificationId);

    /**
     * 修改审核状态
     *
     * @param certificationId
     * @param userCertificationStatus
     */
    void updateUserCertificationStatus(int certificationId, String userCertificationStatus, String dismissExplain);

    /**
     * 根据用户id获取审核信息
     *
     * @param appUserId
     * @return
     */
    AppUserCertification selectUserCertificationByUserId(int appUserId, String userType);

    /**
     * 根据驾照牌照查询appUser信息
     *
     * @param licensePlate
     * @return
     */
    AppUserCertification selectAppUserCertificationByLicensePlate(String licensePlate);

}
