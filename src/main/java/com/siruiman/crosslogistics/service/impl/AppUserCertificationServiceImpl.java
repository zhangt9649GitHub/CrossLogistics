package com.siruiman.crosslogistics.service.impl;


import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.AppUserCertificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.siruiman.crosslogistics.util.CarJPushClientUtil.*;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getEightRandomCode;
import static com.siruiman.crosslogistics.util.RandomCodeUtil.getSixRandomCode;
import static com.siruiman.crosslogistics.util.TruckJPushClientUtil.pushTruckMsg;


@Service
public class AppUserCertificationServiceImpl implements AppUserCertificationService {
    @Autowired
    private AppUserCertificationMapper appUserCertificationMapper;
    @Autowired
    private UploadFilesMapper uploadFilesMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private TruckMapper truckMapper;

    @Override
    public List<AppUserCertification> selectAppUserCertificationAll(String search, String userType) {
        try {
            return appUserCertificationMapper.selectAppUserCertificationAll(search, userType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer count(String search, String userType) {
        try {
            return appUserCertificationMapper.count(search, userType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer checkAppUser(int certificationId, String userCertificationStatus, int appUserId) {
        try {
            Integer checkAppUser;
            if (userCertificationStatus.equals("已通过")) {
                /*修改APP用户审核信息状态*/
                checkAppUser = appUserCertificationMapper.checkAppUser(certificationId, userCertificationStatus);
                /*查询当前审核类型（小车，货车）*/
                String userType = appUserCertificationMapper.selecUserType(certificationId);
                String approveStatus = "";
                approveStatus = "已认证";
                if (userType.equals("小车")) {
                    Integer editApproveStatus = appUserCertificationMapper.editCarApproveStatus(certificationId, approveStatus);
                    return editApproveStatus;
                } else if (userType.equals("货车")) {
                    Integer editApproveStatus = appUserCertificationMapper.editTruckApproveStatus(certificationId, approveStatus);
                    return editApproveStatus;
                }
            }
            if (userCertificationStatus.equals("已驳回")) {
                /*修改APP用户审核信息状态*/
                checkAppUser = appUserCertificationMapper.checkAppUser(certificationId, userCertificationStatus);
                return checkAppUser;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AppUserCertification selectAppUserCertification(int certificationId) {
        try {
            return appUserCertificationMapper.selectAppUserCertification(certificationId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertAppUserCertification(AppUserCertification appUserCertification) {
        UploadFiles uploadFiles = new UploadFiles();
        // 1.身份证正面照 2.身份证背面照 3.护照正面照 4.工作证正面照 5.工作证反面照 6.驾驶证正面照 7.货车照
        List<Integer> ids = new ArrayList<>();
        if (appUserCertification.getUserType().equals("小车")) {
            if (appUserCertification.getFrontIdCard() != null && !(appUserCertification.getFrontIdCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontIdCard());
                uploadFiles.setType(1);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getReverseIdCard() != null && !(appUserCertification.getReverseIdCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getReverseIdCard());
                uploadFiles.setType(2);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getFrontPassport() != null && !(appUserCertification.getFrontPassport().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontPassport());
                uploadFiles.setType(3);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getFrontWorkCard() != null && !(appUserCertification.getFrontWorkCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontWorkCard());
                uploadFiles.setType(4);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getReverseWorkCard() != null && !(appUserCertification.getReverseWorkCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getReverseWorkCard());
                uploadFiles.setType(5);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getFrontDrivingLicence() != null && !(appUserCertification.getFrontDrivingLicence().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontDrivingLicence());
                uploadFiles.setType(6);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getReverseDrivingLicence() != null && !(appUserCertification.getReverseDrivingLicence().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getReverseDrivingLicence());
                uploadFiles.setType(9);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            String ufIds = "";
            for (Integer id : ids
            ) {
                if (id != null) {
                    if (ufIds.equals("")) {
                        ufIds = ufIds + id;
                    } else {
                        ufIds = ufIds + "," + id;
                    }
                }
            }
            appUserCertification.setUfIds(ufIds);
            if (appUserCertification.getUserTrueName() != null) {
                appUserCertification.setUserTrueName(appUserCertification.getUserTrueName());
            }
            if (appUserCertification.getUserCertificationStatus() != null && appUserCertification.getUserCertificationStatus().equals("已驳回")) {
                appUserCertification.setUserCertificationStatus("待审核");
                appUserCertificationMapper.updateAppUserCertification(appUserCertification);
            } else {
                appUserCertification.setUserCertificationStatus("待审核");
                appUserCertificationMapper.insertAppUserCertification(appUserCertification);
            }
            String carApproveStatus = "待审核";
            appUserMapper.updateCarApproveStatus(carApproveStatus, appUserCertification.getAppUserId());
        } else if (appUserCertification.getUserType().equals("货车")) {
            if (appUserCertification.getFrontIdCard() != null && !(appUserCertification.getFrontIdCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontIdCard());
                uploadFiles.setType(1);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getReverseIdCard() != null && !(appUserCertification.getReverseIdCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getReverseIdCard());
                uploadFiles.setType(2);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getFrontPassport() != null && !(appUserCertification.getFrontPassport().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontPassport());
                uploadFiles.setType(3);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getFrontWorkCard() != null && !(appUserCertification.getFrontWorkCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontWorkCard());
                uploadFiles.setType(4);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getReverseWorkCard() != null && !(appUserCertification.getReverseWorkCard().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getReverseWorkCard());
                uploadFiles.setType(5);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getFrontDrivingLicence() != null && !(appUserCertification.getFrontDrivingLicence().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getFrontDrivingLicence());
                uploadFiles.setType(6);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getTruckPhoto() != null && !(appUserCertification.getTruckPhoto().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getTruckPhoto());
                uploadFiles.setType(7);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            if (appUserCertification.getReverseDrivingLicence() != null && !(appUserCertification.getReverseDrivingLicence().equals(""))) {
                uploadFiles.setUfSavePath(appUserCertification.getReverseDrivingLicence());
                uploadFiles.setType(9);
                uploadFilesMapper.insert(uploadFiles);
                Integer id = uploadFilesMapper.getLastId();
                ids.add(id);
            }
            String ufIds = "";
            for (Integer id : ids
            ) {
                if (id != null) {
                    if (ufIds.equals("")) {
                        ufIds = ufIds + id;
                    } else {
                        ufIds = ufIds + "," + id;
                    }
                }
            }
            appUserCertification.setUfIds(ufIds);
            if (appUserCertification.getUserTrueName() != null) {
                appUserCertification.setUserTrueName(appUserCertification.getUserTrueName());
            }
            if (appUserCertification.getUserCertificationStatus() != null && appUserCertification.getUserCertificationStatus().equals("已驳回")) {
                appUserCertification.setUserCertificationStatus("待审核");
                appUserCertificationMapper.updateAppUserCertification(appUserCertification);
            } else {
                appUserCertification.setUserCertificationStatus("待审核");
                appUserCertificationMapper.insertAppUserCertification(appUserCertification);
            }
            String truckApproveStatus = "待审核";
            appUserMapper.updateTruckApproveStatus(truckApproveStatus, appUserCertification.getAppUserId());
        }
    }

    @Override
    public List<AppUserCertification> selectAppUserCertificationList(String userTrueName, String userType, String number) {
        try {
            HashMap<Object, String> map = new HashMap<>();
            map.put("userTrueName", userTrueName);
            map.put("userType", userType);
            map.put("number", number);
            return appUserCertificationMapper.selectAppUserCertificationList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountAppUserCertificationList(String userTrueName, String userType, String number) {
        try {
            HashMap<Object, String> map = new HashMap<>();
            map.put("userTrueName", userTrueName);
            map.put("userType", userType);
            map.put("number", number);
            return appUserCertificationMapper.selectCountAppUserCertificationList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public AppUserCertification selectAppUserCertificationDetails(int certificationId) {
        try {
            AppUserCertification appUserCertification = appUserCertificationMapper.selectAppUserCertificationById(certificationId);
            String[] ufIds = appUserCertification.getUfIds().split(",");
            List<String> images = new ArrayList<>();
            for (int i = 0; i < ufIds.length; i++) {
                UploadFiles uploadFiles = uploadFilesMapper.selectByPrimaryKey(Integer.parseInt(ufIds[i]));
                images.add(uploadFiles.getUfSavePath());
            }
            appUserCertification.setImages(images);
            return appUserCertification;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserCertificationStatus(int certificationId, String userCertificationStatus, String dismissExplain) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("certificationId", certificationId);
        map.put("userCertificationStatus", userCertificationStatus);
        map.put("dismissExplain", dismissExplain);
        AppUserCertification appUserCertification = appUserCertificationMapper.selectAppUserCertificationById(certificationId);
        AppUser appUser = appUserMapper.selectAppUserByUserId(appUserCertification.getAppUserId());
        if (userCertificationStatus.equals("已通过")) {
            appUserCertificationMapper.updateUserCertificationStatusById(map);

            if (appUserCertification.getUserType().equals("小车")) {
                String carApproveStatus = "已认证";

                appUserMapper.updateCarApproveStatus(carApproveStatus, appUserCertification.getAppUserId());
                String comment = "4" + appUserCertification.getAppUserId();
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    //创建一条消息
                    String content = "恭喜您！您的小车审核认证已通过";
                    String enContent = "Congratulations! Your car review certification has passed";
                    Message message = new Message();
                    message.setAppUserId(appUserCertification.getAppUserId());
                    message.setTitle("小车审核认证已通过");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("小车");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Car audit certification has passed");
                    message.setEnContent(enContent);
                    message.setEnType("Notice");
                    message.setComment("4" + appUserCertification.getAppUserId());
                    messageMapper.insert(message);
                    AppUser appUser1 = appUserMapper.selectAppUserByUserId(appUserCertification.getAppUserId());
                    pushCarMsg("alias", "小车审核认证已通过", "Car" + appUser1.getNumber(), content + "\r\n" + enContent);
                }

            } else if (appUserCertification.getUserType().equals("货车")) {
                String truckApproveStatus = "已认证";

                appUserMapper.updateTruckApproveStatus(truckApproveStatus, appUserCertification.getAppUserId());
                Truck truck = new Truck();
                truck.setState(2);
                truck.setLicensePlate(appUserCertification.getLicensePlate());
                truck.setLoad(appUserCertification.getLoad());
                truck.setModels(appUserCertification.getModels());
                truck.setName(appUserCertification.getUserTrueName());
                if (appUserCertification.getMobile() != null) {
                    truck.setMobile(appUserCertification.getMobile());
                }
                truck.setAddTime(new Date());
                truckMapper.insert(truck);
                String comment = "10" + appUserCertification.getAppUserId();
                Message message1 = messageMapper.selectMessageComment(comment);
                if (message1 == null) {
                    String content = "恭喜您！您的货车审核认证已通过";
                    String enContent = "Congratulations! Your truck review certification has passed";
                    //创建一条消息
                    Message message = new Message();
                    message.setAppUserId(appUserCertification.getAppUserId());
                    message.setTitle("货车审核认证已通过");
                    message.setContent(content);
                    message.setType("通知消息");
                    message.setCategory("货车");
                    message.setState("未读");
                    message.setAddTime(new Date());
                    message.setEnTitle("Truck audit certification has passed");
                    message.setEnContent(enContent);
                    message.setEnType("Notice");
                    message.setComment("10" + appUserCertification.getAppUserId());
                    messageMapper.insert(message);
                    pushTruckMsg("alias", "货车审核认证已通过", "Truck" + appUser.getNumber(), content + "\r\n" + enContent);
                }


            }

        } else if (userCertificationStatus.equals("已驳回")) {
            appUserCertificationMapper.updateUserCertificationStatusById(map);
            if (appUserCertification.getUserType().equals("小车")) {
                String carApproveStatus = "已驳回";

                appUserMapper.updateCarApproveStatus(carApproveStatus, appUserCertification.getAppUserId());
            } else if (appUserCertification.getUserType().equals("货车")) {
                String truckApproveStatus = "已驳回";

                appUserMapper.updateTruckApproveStatus(truckApproveStatus, appUserCertification.getAppUserId());
            }
            //推送消息
            //创建一条消息
            Message message = new Message();
            if (appUserCertification.getUserType().equals("小车")) {
                message.setAppUserId(appUserCertification.getAppUserId());
                message.setTitle("小车认证审核已驳回");
                message.setContent(dismissExplain);
                message.setType("通知消息");
                message.setCategory(appUserCertification.getUserType());
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Car audit certification has been rejected");
                message.setEnContent(dismissExplain);
                message.setEnType("Notice");
                message.setComment("15" + getEightRandomCode());
                messageMapper.insert(message);
                pushCarMsg("alias", "小车认证审核已驳回", "Car" + appUser.getNumber(), dismissExplain);
            } else if (appUserCertification.getUserType().equals("货车")) {
                message.setAppUserId(appUserCertification.getAppUserId());
                message.setTitle("货车认证审核已驳回");
                message.setContent(dismissExplain);
                message.setType("通知消息");
                message.setCategory(appUserCertification.getUserType());
                message.setState("未读");
                message.setAddTime(new Date());
                message.setEnTitle("Truck audit certification has been rejected");
                message.setEnContent(dismissExplain);
                message.setEnType("Notice");
                message.setComment("17" + getEightRandomCode());
                messageMapper.insert(message);
                pushTruckMsg("alias", "货车认证审核已驳回", "Truck" + appUser.getNumber(), dismissExplain);
            }
        }
    }

    @Override
    public AppUserCertification selectUserCertificationByUserId(int appUserId, String userType) {
        try {
            return appUserCertificationMapper.selectUserCertificationByUserId(appUserId, userType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AppUserCertification selectAppUserCertificationByLicensePlate(String licensePlate) {
        try {
            return appUserCertificationMapper.selectAppUserCertificationByLicensePlate(licensePlate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
