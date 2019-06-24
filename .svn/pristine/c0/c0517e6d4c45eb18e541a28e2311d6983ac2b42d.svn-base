package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.AppUserWalletAndOrderNum;
import com.siruiman.crosslogistics.model.OrderRecord;
import com.siruiman.crosslogistics.model.dto.AddAppUserDto;
import com.siruiman.crosslogistics.model.dto.AppUserDto;

import java.util.List;

public interface AppUserService {
    /**
     * 用户列表
     * @param appUserDto
     * @return
     */
    List<AppUser> selectAppUserAll(AppUserDto appUserDto);

    /**
     * 用户列表条数
     * @param appUserDto
     * @return
     */
    Integer count(AppUserDto appUserDto);

    /**
     * 用户查看详情
     * @param appUserId
     * @return
     */
    AppUser selectAppUserDetail(int appUserId);

    /**
     * 添加APP用户
     * @param addAppUserDto
     * @return
     */
    Integer addAppUser(AddAppUserDto addAppUserDto);

    /**
     * 删除APP用户
     * @param appUserId
     * @return
     */
    Integer deleteAppUser(int appUserId);

    /**
     * 启用禁用APP用户
     * @param appUserId
     * @param status
     * @return
     */
    Integer editAppUserStatus(int appUserId, String status);

    /**
     * 查询编辑app用户的信息
     * @param appUserId
     * @return
     */
    AddAppUserDto selectEditAppUserDetail(int appUserId);

    /**
     * 编辑app用户信息
     * @param addAppUserDto
     * @return
     */
    Integer editAppUser(AddAppUserDto addAppUserDto);

    /**
     * 根据用户编号查询APP用户信息
     * @param number
     * @return
     */
    AppUser selectAppUserByNumber(String number);

    /**
     * 根据用户和用户状态查询钱包余额和订单完成数
     * @param appUserId
     * @param userType
     * @return
     */
    AppUserWalletAndOrderNum selectAppUserWalletAndOrderNum(int appUserId, String userType);

    /**
     * 根据用户查询订单记录
     * @param appUserId
     * @return
     */
    List<OrderRecord> selectOrderRecordByUser(int appUserId, String userType);

    /**
     * 根据用户查询小车订单记录条数
     * @param appUserId
     * @return
     */
    Integer countOrderRecordByCarUser(int appUserId);

    /**
     * 根据用户查询货车订单记录条数
     * @param appUserId
     * @return
     */
    Integer countOrderRecordByTruckUser(int appUserId);

    /**
     * 查询用户id根据编号
     * @param number
     * @return
     */
    int selectUIdByNumber(String number);

    /**
     * 验证用户真实姓名是否重复
     * @param actualName
     * @return
     */
    Integer checkActualName(String actualName);

    /**
     * 检验昵称是否重复
     * @param userName
     * @return
     */
    Integer selectCountAppUserName(String userName);
}
