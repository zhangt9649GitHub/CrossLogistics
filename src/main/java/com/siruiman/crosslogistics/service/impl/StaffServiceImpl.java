package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.StaffMapper;
import com.siruiman.crosslogistics.mapper.WarehouseMapper;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.StaffDto;
import com.siruiman.crosslogistics.service.StaffService;
import com.siruiman.crosslogistics.service.WarehouseService;
import com.siruiman.crosslogistics.util.Base64;
import com.siruiman.crosslogistics.util.MD5Util;
import com.siruiman.crosslogistics.util.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public List<Staff> selectStaffAll(StaffDto staffDto) {
        List<Staff> selectStaffAll = staffMapper.selectStaffAll(staffDto);
        for(Staff staff : selectStaffAll){
            staff.setAddTime(staff.getAddTime().substring(0,19));
            if(staff.getStatus() == 0){
                staff.setStatusName("禁用");
            }
            if(staff.getStatus() == 1){
                staff.setStatusName("启用");
            }
        }
        return selectStaffAll;
    }

    @Override
    public Integer count(StaffDto staffDto) {
        try{
            return staffMapper.count(staffDto);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addStaff(Staff staff) {
        try{
            Integer selectStaffUserName = staffMapper.selectStaffUserName(staff.getUserName());
            if(selectStaffUserName > 0){
                return 2;
            }

            String password = MD5Util.encrypt(staff.getPassword());
            staff.setPassword(password);
            String number = "02" + RandomCodeUtil.getSixRandomCode();
            staff.setNumber(number);

            return staffMapper.addStaff(staff);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Staff selectStaffDetail(int staffId) {
        try{
            Staff staff =staffMapper.selectStaffDetail(staffId);
            if(staff.getWarehouseId()>0){
                Warehouse warehouse =warehouseMapper.selectByPrimaryKey(staff.getWarehouseId());
                staff.setWarehouseName(warehouse.getWarehouseName());
            }
            return staff;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StaffAccess> selectStaffAccessAll() {
        try{
            return staffMapper.selectStaffAccessAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Staff selectEeditStaff(int staffId) {
        try{
            Staff selectEeditStaff = staffMapper.selectEeditStaff(staffId);
            /*selectEeditStaff.setHeadPic(Base64.GetImageStrFromPath(selectEeditStaff.getHeadPic()));*/
            return selectEeditStaff;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editStaff(Staff staff) {
        try{
            String selectStaffPsw = staffMapper.selectStaffPsw(staff.getStaffId());
            String password = MD5Util.encrypt(staff.getPassword());
            if(password.equals(selectStaffPsw)){
                String newPassword = MD5Util.encrypt(staff.getNewPassword());
                staff.setNewPassword(newPassword);

                return staffMapper.editStaff(staff);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer deleteStaff(int staffId) {
        try{
            return staffMapper.deleteStaff(staffId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer editStaffStatus(int staffId, int status) {
        try{
            return staffMapper.editStaffStatus(staffId, status);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer checkUserName(String userName) {
        try{
            return staffMapper.checkUserName(userName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Staff selectStaffbyNumber(String number, String staffName) {
        try{
            return staffMapper.selectStaffbyNumber(number,staffName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LogInfo> selectLogisticInfoByStaff(int staffId) {
        try{
            return staffMapper.selectLogisticInfoByStaff(staffId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countLogisticInfoByStaff(int staffId) {
        try{
            return staffMapper.countLogisticInfoByStaff(staffId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StaffGroupAccess> selectStaffGroupAll() {
        try{
            return staffMapper.selectStaffGroupAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Staff> selectStaffList() {
        try{
            return staffMapper.selectStaffList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
