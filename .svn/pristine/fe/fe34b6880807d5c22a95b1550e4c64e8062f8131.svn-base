package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppUserMapper;
import com.siruiman.crosslogistics.model.AppUser;
import com.siruiman.crosslogistics.model.AppUserWalletAndOrderNum;
import com.siruiman.crosslogistics.model.OrderRecord;
import com.siruiman.crosslogistics.model.dto.AddAppUserDto;
import com.siruiman.crosslogistics.model.dto.AppUserDto;
import com.siruiman.crosslogistics.service.AppUserService;
import com.siruiman.crosslogistics.util.MD5Util;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserMapper appUserMapper;

    @Override
    public List<AppUser> selectAppUserAll(AppUserDto appUserDto) {
        try{
            List<AppUser> selectAppUserAll = appUserMapper.selectAppUserAll(appUserDto);
            return selectAppUserAll;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(AppUserDto appUserDto) {
        try{
            return appUserMapper.count(appUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUser selectAppUserDetail(int appUserId) {
        try{
            AppUser selectAppUserDetail = appUserMapper.selectAppUserDetail(appUserId);
            return selectAppUserDetail;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addAppUser(AddAppUserDto addAppUserDto) {
        try{
            addAppUserDto.setNumber("13" + RandomCodeUtil.getSixRandomCode());
            String psw = MD5Util.encrypt(addAppUserDto.getPassword());
            addAppUserDto.setPassword(psw);

            return appUserMapper.addAppUser(addAppUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer deleteAppUser(int appUserId) {
        try{
            return appUserMapper.deleteAppUser(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editAppUserStatus(int appUserId, String status) {
        try{
            return appUserMapper.editAppUserStatus(appUserId, status);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddAppUserDto selectEditAppUserDetail(int appUserId) {
            AddAppUserDto selectEditAppUserDetail = appUserMapper.selectEditAppUserDetail(appUserId);
            return selectEditAppUserDetail;

    }

    @Override
    public Integer editAppUser(AddAppUserDto addAppUserDto) {
        try{
          /*  String selectPsw = appUserMapper.selectPsw(addAppUserDto.getAppUserId());
            if(selectPsw.equals(MD5Util.encrypt(addAppUserDto.getPassword()))){
                String password = MD5Util.encrypt(addAppUserDto.getNewPassword());
                addAppUserDto.setPassword(password);


            }
            return null;*/
            return appUserMapper.editAppUser(addAppUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUser selectAppUserByNumber(String number) {
        try{
           return appUserMapper.selectAppUserByNumber(number);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUserWalletAndOrderNum selectAppUserWalletAndOrderNum(int appUserId, String userType) {
        try{
            AppUserWalletAndOrderNum selectAppUserWalletAndOrderNum = appUserMapper.selectAppUserWalletAndOrderNum(appUserId, userType);
            if(userType.equals("小车")){
                Integer totalOrderNumByCar = appUserMapper.totalOrderNumByCar(appUserId);
                selectAppUserWalletAndOrderNum.setCompleteOrderNum(totalOrderNumByCar);
                return selectAppUserWalletAndOrderNum;
            }
            if(userType.equals("货车")){
                Integer totalOrderNumByTruck = appUserMapper.totalOrderNumByTruck(appUserId);
                selectAppUserWalletAndOrderNum.setCompleteOrderNum(totalOrderNumByTruck);
                return selectAppUserWalletAndOrderNum;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderRecord> selectOrderRecordByUser(int appUserId, String userType) {
        try{
            if(userType.equals("小车")){
                List<OrderRecord> selectOrderRecordByCarUser = appUserMapper.selectOrderRecordByCarUser(appUserId);
                return selectOrderRecordByCarUser;
            }
            if(userType.equals("货车")){
                List<OrderRecord> selectOrderRecordByTruckUser = appUserMapper.selectOrderRecordByTruckUser(appUserId);
                return selectOrderRecordByTruckUser;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countOrderRecordByCarUser(int appUserId) {
        try{
            return appUserMapper.countOrderRecordByCarUser(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countOrderRecordByTruckUser(int appUserId) {
        try{
            return appUserMapper.countOrderRecordByTruckUser(appUserId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int selectUIdByNumber(String number) {
        return appUserMapper.selectUIdByNumber(number);
    }

    @Override
    public Integer checkActualName(String actualName) {
        try{
            return appUserMapper.checkActualName(actualName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectCountAppUserName(String userName) {
        try{
            return appUserMapper.selectCountAppUserName(userName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
