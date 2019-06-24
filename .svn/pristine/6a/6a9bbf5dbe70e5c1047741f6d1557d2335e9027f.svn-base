package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.TruckContactAddress;

public interface TruckContactAddressService {
    /**
     * 用户id
     * @param appUserId
     * @return
     */
    TruckContactAddress selectTruckContactAddress(int appUserId);

    /**
     * 根据邮编查询地址
     * @param zipCode
     * @return
     */
    String selectAddress(String zipCode);

    /**
     * 修改联系地址
     * @param truckContactAddress
     * @return
     */
    Integer editTruckAddress(TruckContactAddress truckContactAddress);
}
