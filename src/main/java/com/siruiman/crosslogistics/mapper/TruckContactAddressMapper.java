package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.TruckContactAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TruckContactAddressMapper {
    /**
     * 用户id
     * @param appUserId
     * @return
     */
    TruckContactAddress selectTruckContactAddress(@Param("appUserId") int appUserId);

    /**
     * 根据邮编查询地址
     * @param zipCode
     * @return
     */
    String selectAddress(@Param("zipCode") String zipCode);

    /**
     * 修改联系地址
     * @param truckContactAddress
     * @return
     */
    Integer editTruckAddress(@Param("truckContactAddress") TruckContactAddress truckContactAddress);
}
