package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.model.dto.CarLockDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarLockService {

    /**
     * 根据小车id获取小车锁编号
     *
     * @param carId
     * @return
     */
    List<CarLock> selectCarLockByCarId(int carId);

    /**
     * 插入锁数据
     *
     * @param carLock
     */
    void insertCarLock(CarLock carLock);

    /**
     * 根据锁编号查询小车锁信息
     *
     * @param lockNumber
     * @return
     */
    CarLock selectCarLockByLockNumber(String lockNumber);

    /**
     * 修改锁的归属小车id
     *
     * @param carLock
     */
    void updateCarLock(CarLock carLock);

    /**
     * 根据小车id和锁的位置获取所有的锁
     *
     * @param carId
     * @param lockPosition
     * @return
     */
    List<CarLock> selectCarLock(int carId, int lockPosition);

    /**
     * 根据锁id获取锁信息
     *
     * @param lockId
     * @return
     */
    CarLock selectCarLockById(int lockId);

    /**
     * 修改小车锁开锁状态
     *
     * @param lock
     */
    void updateUnLockState(CarLock lock);

    /**
     * 根据锁的位置和状态获取所有空置的锁列表
     *
     * @param lockPostion
     * @param status
     * @return
     */
    List<CarLock> selectVacancyCarLock(int lockPostion, byte status);

    /**
     * 根据锁编号查询小车id
     *
     * @param lockNumber
     * @return
     */
    CarLock selectCarIdByLockNumber(String lockNumber);


    /**
     * 修改小车锁的定位wgs84数据格式
     *
     * @param lock
     */
    void updateLockLatLng(CarLock lock);

    /**
     * 修改小车锁的基站定位信息
     * 返回cell定位信息使用
     *
     * @param lock
     */
    void updateLockStationNum(CarLock lock);

    /**
     * 获取车锁列表
     *
     * @param lockNumber
     * @param status
     * @return
     */
    List<CarLock> getList(String lockNumber, Byte status);


    /**
     * @param lockNumber
     * @param status
     * @return
     */
    int getCount(String lockNumber, Byte status);

    void updateCarNumberAndPosition(CarLock carLock);

    /**
     * 车锁报废
     *
     * @param lockId
     */
    void scrapLock(Integer lockId);

    /**
     * 车锁修复至空闲状态
     *
     * @param lockId
     */
    void repairLock(Integer lockId);

    /**
     * 车锁新增
     *
     * @param lockDto
     */
    void add(CarLockDto lockDto);

    /**
     * 车锁编号检查是否重复
     *
     * @param lockNumber
     * @return true 可以使用该编号 false 数据库存在 不能重复插入
     */
    boolean checkNumber(String lockNumber, Integer lockId);

    /**
     * 小车开锁接口
     *
     * @param lockNumber
     */
    void unLock(String lockNumber) throws Exception;
}
