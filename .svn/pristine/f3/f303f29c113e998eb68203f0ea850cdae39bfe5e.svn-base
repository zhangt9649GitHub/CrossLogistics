package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.AppUserWalletAndOrderNum;
import com.siruiman.crosslogistics.model.OrderRecord;
import com.siruiman.crosslogistics.model.dto.AddAppUserDto;
import com.siruiman.crosslogistics.model.dto.AppUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AppUserMapper {
    /**
     * 用户列表
     * @param appUserDto
     * @return
     */
    List<AppUser> selectAppUserAll(@Param("appUserDto") AppUserDto appUserDto);

    /**
     * 用户列表条数
     * @param appUserDto
     * @return
     */
    Integer count(@Param("appUserDto") AppUserDto appUserDto);

    /**
     * 用户查看详情
     * @param appUserId
     * @return
     */
    AppUser selectAppUserDetail(@Param("appUserId") int appUserId);

    /**
     * 添加APP用户
     * @param addAppUserDto
     * @return
     */
    Integer addAppUser(@Param("addAppUserDto") AddAppUserDto addAppUserDto);

    /**
     * 删除APP用户
     * @param appUserId
     * @return
     */
    Integer deleteAppUser(@Param("appUserId") int appUserId);

    /**
     * 启用禁用APP用户
     * @param appUserId
     * @param status
     * @return
     */
    Integer editAppUserStatus(@Param("appUserId") int appUserId, @Param("status") String status);

    /**
     * 查询编辑app用户的信息
     * @param appUserId
     * @return
     */
    AddAppUserDto selectEditAppUserDetail(@Param("appUserId") int appUserId);

    /**
     * 编辑app用户信息
     * @param addAppUserDto
     * @return
     */
    Integer editAppUser(@Param("addAppUserDto") AddAppUserDto addAppUserDto);

    /**
     * 查询app用户密码
     * @param appUserId
     * @return
     */
    String selectPsw(@Param("appUserId") int appUserId);

    /**
     * 根据用户编号查询APP用户信息
     * @param number
     * @return
     */
    AppUser selectAppUserByNumber(@Param("number") String number);

    /**
     * 根据用户和用户状态查询钱包余额
     * @param appUserId
     * @param userType
     * @return
     */
    AppUserWalletAndOrderNum selectAppUserWalletAndOrderNum(@Param("appUserId") int appUserId, @Param("userType") String userType);

    /**
     * 查询当前用户完成小车订单数
     * @param appUserId
     * @return
     */
    Integer totalOrderNumByCar(@Param("appUserId") int appUserId);

    /**
     * 查询当前用户完成货车订单数
     * @param appUserId
     * @return
     */
    Integer totalOrderNumByTruck(@Param("appUserId") int appUserId);

    /**
     * 根据用户查询小车订单记录
     * @param appUserId
     * @return
     */
    List<OrderRecord> selectOrderRecordByCarUser(@Param("appUserId") int appUserId);

    /**
     * 根据用户查询小车订单记录条数
     * @param appUserId
     * @return
     */
    Integer countOrderRecordByCarUser(@Param("appUserId") int appUserId);

    /**
     * 根据用户查询货车订单记录
     * @param appUserId
     * @return
     */
    List<OrderRecord> selectOrderRecordByTruckUser(@Param("appUserId") int appUserId);

    /**
     * 根据用户查询货车订单记录条数
     * @param appUserId
     * @return
     */
    Integer countOrderRecordByTruckUser(@Param("appUserId") int appUserId);

    /**
     * 修改用户货车认证状态

     */
    void updateTruckApproveStatus(@Param("truckApproveStatus") String truckApproveStatus,@Param("appUserId") int appUserId);

    /**
     * 修改小车认证状态
     */
    void  updateCarApproveStatus(@Param("carApproveStatus") String carApproveStatus,@Param("appUserId") int appUserId);

    /**
     * 根据app用户id获取app用户认证信息
     * @param appUserId
     * @return
     */
    AppUser selectAppUserByUserId(int appUserId);


    /**
     * 查询用户id根据用户编号
     * @param number
     * @return
     */
    int selectUIdByNumber(String number);

    /**
     * 根据用户id获取用户昵称
     * @param appUserId
     * @return
     */
    AppUser selectAppUserName(@Param("appUserId") int appUserId);

    /**
     * 验证用户真实姓名是否重复
     * @param actualName
     * @return
     */
    Integer checkActualName(@Param("actualName") String actualName);

    /**
     * 检验昵称是否重复
     * @param userName
     * @return
     */
    Integer selectCountAppUserName(@Param("userName") String userName);
}
