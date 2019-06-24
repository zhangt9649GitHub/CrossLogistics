package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TruckContactAddressMapper;
import com.siruiman.crosslogistics.model.TruckContactAddress;
import com.siruiman.crosslogistics.service.TruckContactAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckContactAddressServiceImpl implements TruckContactAddressService {
    @Autowired
    private TruckContactAddressMapper truckContactAddressMapper;

    @Override
    public TruckContactAddress selectTruckContactAddress(int appUserId) {
        return truckContactAddressMapper.selectTruckContactAddress(appUserId);
    }

    @Override
    public String selectAddress(String zipCode) {
        return truckContactAddressMapper.selectAddress(zipCode);
    }

    @Override
    public Integer editTruckAddress(TruckContactAddress truckContactAddress) {
        try{
            return truckContactAddressMapper.editTruckAddress(truckContactAddress);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
