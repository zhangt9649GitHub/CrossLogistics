package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.AppUserWalletStreamMapper;
import com.siruiman.crosslogistics.model.AppUserWalletStream;
import com.siruiman.crosslogistics.service.AppUserWalletStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class AppUserWalletStreamServiceImpl implements AppUserWalletStreamService {

    @Autowired
    private AppUserWalletStreamMapper appUserWalletStreamMapper;

    @Override
    public List<AppUserWalletStream> selectWalletStream(String userType, int appUserId) {
        try {
            return appUserWalletStreamMapper.selectWalletStream(userType, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountWalletStream(String userType, int appUserId) {
        try {
            return appUserWalletStreamMapper.selectCountWalletStream(userType, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<AppUserWalletStream> selectWalletIntegralStream(String userType, int appUserId) {
        try {
            return appUserWalletStreamMapper.selectWalletIntegralStream(userType, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountWalletIntegralStream(String userType, int appUserId) {
        try {
            return appUserWalletStreamMapper.selectCountWalletIntegralStream(userType, appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertAppUserWalletStream(AppUserWalletStream appUserWalletStream) {
        appUserWalletStreamMapper.insert(appUserWalletStream);
    }

    @Override
    public AppUserWalletStream selectWalletStreamById(int streamId) {
        try {
            return appUserWalletStreamMapper.selectWalletStreamById(streamId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<AppUserWalletStream> selectWalletStreamByCarOrderId(int carOrderId) {
            return appUserWalletStreamMapper.selectWalletStreamByCarOrderId(carOrderId);
    }

    @Override
    public List<AppUserWalletStream> selectTransferOrderRecordsList(int appUserId) {
        try {
            return appUserWalletStreamMapper.selectTransferOrderRecordsList(appUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertUserPay(AppUserWalletStream appUserWalletStream) {
        appUserWalletStreamMapper.insertUserPay(appUserWalletStream);
    }
}
