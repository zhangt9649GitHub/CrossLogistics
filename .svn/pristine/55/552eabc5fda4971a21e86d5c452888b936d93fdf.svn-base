package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUserCertification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AppUserCertificationMapper {
    /**
     * app用户审核列表
     *
     * @return
     */
    List<AppUserCertification> selectAppUserCertificationAll(@Param("search") String search, @Param("userType") String userType);

    /**
     * app用户审核列表条数
     *
     * @return
     */
    Integer count(@Param("search") String search, @Param("userType") String userType);

    /**
     * 审核
     *
     * @param certificationId
     * @return
     */
    Integer checkAppUser(@Param("certificationId") int certificationId, @Param("userCertificationStatus") String userCertificationStatus);

    /**
     * 查询当前订审核类型（小车，货车）
     *
     * @param certificationId
     * @return
     */
    String selecUserType(@Param("certificationId") int certificationId);

    /**
     * 修改小车认证状态
     *
     * @param appUserId
     * @param approveStatus
     * @return
     */
    Integer editCarApproveStatus(@Param("appUserId") int appUserId, @Param("approveStatus") String approveStatus);

    /**
     * 修改货车认证状态
     *
     * @param appUserId
     * @param approveStatus
     * @return
     */
    Integer editTruckApproveStatus(@Param("appUserId") int appUserId, @Param("approveStatus") String approveStatus);

    /**
     * 查询用户审核信息
     *
     * @param certificationId
     * @return
     */
    AppUserCertification selectAppUserCertification(@Param("certificationId") int certificationId);

    /**
     * 添加用户审核信息
     *
     * @param appUserCertification
     */
    void insertAppUserCertification(AppUserCertification appUserCertification);

    /**
     * 获取审核列表
     *
     * @param map
     * @return
     */
    List<AppUserCertification> selectAppUserCertificationList(HashMap<Object, String> map);

    /**
     * 获取审核列表条数
     *
     * @param map
     * @return
     */
    int selectCountAppUserCertificationList(HashMap<Object, String> map);

    /**
     * 根据id获取审核列表详情
     *
     * @return
     */
    AppUserCertification selectAppUserCertificationById(@Param("certificationId") int certificationId);

    /**
     * 更新用户审核状态
     *
     * @param map
     */
    void updateUserCertificationStatusById(HashMap<String, Object> map);

    /**
     * 修改用户审核资料
     *
     * @param appUserCertification
     */
    void updateAppUserCertification(AppUserCertification appUserCertification);

    /**
     * 根据用户id获取审核信息
     *
     * @param appUserId
     * @return
     */
    AppUserCertification selectUserCertificationByUserId(@Param("appUserId") int appUserId, @Param("userType") String userType);

    /**
     * 根据驾照牌照查询appUser信息
     *
     * @param licensePlate
     * @return
     */
    AppUserCertification selectAppUserCertificationByLicensePlate(@Param("licensePlate") String licensePlate);
}
