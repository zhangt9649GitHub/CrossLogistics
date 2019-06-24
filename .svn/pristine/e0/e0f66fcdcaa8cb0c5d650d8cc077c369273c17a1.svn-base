package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.model.dto.CarLockDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Mapper
@Repository
public interface CarLockMapper {

    int deleteByPrimaryKey(Integer lockId);


    int insert(CarLock record);


    CarLock selectByPrimaryKey(Integer lockId);


    List<CarLock> selectAll();


    int updateByPrimaryKey(CarLock record);

    /**
     * 根据小车id获取小车锁编号
     * @param carId
     * @return
     */
    List<CarLock> selectCarLockByCarId(@Param("carId") int carId);

    /**
     * 根据锁编号查询小车锁信息
     * @param lockNumber
     * @return
     */
    CarLock selectCarLockByLockNumber(@Param("lockNumber") String lockNumber);

    /**
     * 根据小车id和锁的位置获取所有的锁
     * @param carId
     * @param lockPosition
     * @return
     */
    List<CarLock> selectCarLock(@Param("carId") int carId,@Param("lockPosition") int lockPosition);

    /**
     * 服务运行初始化小车数据
     * @return
     */
    List<CarLock> selectAllCarLockState();

    /**
     * 修改小车锁开启状态
     * @param lock
     */
    void updateUnLockState(@Param("lock") CarLock lock);

    /**
     * 返回cell信息修改
     * 修改小车锁定位移动基站
     * @param lock
     */
    void updateLockStationNum(@Param("lock")CarLock lock);


    /**
     * 返回wgs84信息修改
     * 修改小车锁定位状态
     * @param lock
     */
    void updateLockLatLng(@Param("lock")CarLock lock);

    /**
     * 根据锁的位置和状态获取所有空置的锁列表
     * @param lockPostion
     * @param status
     * @return
     */
    List<CarLock> selectVacancyCarLock(@Param("lockPostion") int lockPostion,@Param("status") byte status);

    CarLock selectCarIdByLockNumber(@Param("lockNumber") String lockNumber);


    /**
     * 获取车锁列表
     * @param lockNumber
     * @param status
     * @return
     */
    List<CarLock> getList(@Param("lockNumber")String lockNumber,@Param("status") Byte status);

    /**
     *
     * @param lockNumber
     * @param status
     * @return
     */
    int getCount(@Param("lockNumber")String lockNumber,@Param("status") Byte status);;

    /**
     * 修改车锁编号和位置
     * @param carLock
     */
    void updateCarNumberAndPosition(CarLock carLock);

    /**
     * 车锁报废
     * @param lockId
     */
    void scrapLock(@Param("lockId")Integer lockId,@Param("updateTime") Date updateTime);

    /**
     * 修改报废车锁为空闲状态
     * @param lockId
     */
    void repairLock(@Param("lockId")Integer lockId,@Param("updateTime") Date updateTime);

    /**
     * 新增车锁
     * @param lockDto
     */
    void addLock(@Param("lockDto")CarLockDto lockDto);

    /**
     * 根据编号查询小车锁
     * @param lockNumber
     * @return
     */
    CarLock selectCarLockByLockNumberORId(@Param("lockNumber")String lockNumber,@Param("lockId")Integer lockId);
}