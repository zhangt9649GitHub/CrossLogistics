package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.GoodsDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CarMapper {

    int deleteByPrimaryKey(Integer carId);


    int insert(Car record);


    Car selectByPrimaryKey(Integer carId);


    List<Car> selectAll(Car car);


    int updateByPrimaryKey(Car record);


    int selectCountCar(Car car);


    Car selectCarById(Integer goodsId);


    Car selectCarDetail(int carId);


    Car getBagDetailedOfCar(int bagId);

    /**
     * 获取小车管理列表
     *
     * @param carNumber
     * @param state
     * @return
     */
    List<Car> selectCarInfoList(@Param("carNumber") String carNumber, @Param("state") Integer state, @Param("singaporeAreaId") Integer singaporeAreaId, @Param("rallyPointId") Integer rallyPointId);

    /**
     * 获取小车管理列表总条数
     *
     * @param carNumber
     * @param state
     * @return
     */
    int selectCountCarInfoList(@Param("carNumber") String carNumber, @Param("state") Integer state, @Param("singaporeAreaId") Integer singaporeAreaId, @Param("rallyPointId") Integer rallyPointId);

    /**
     * 根据小车id货车小车详情
     *
     * @param carId
     * @return
     */
    Car selectCarDetailsById(@Param("carId") Integer carId);

    /**
     * 获取刚刚插入数据的id
     *
     * @return
     */
    int selectCarLastId();

    /**
     * 根据条件获取小车信息
     *
     * @param carNumber
     * @param state
     * @param bagNumber
     * @return
     */
    List<Car> selectCarList(@Param("carNumber") String carNumber, @Param("state") int state, @Param("bagNumber") String bagNumber);

    /**
     * 根据条件获取总条数
     *
     * @param carNumber
     * @param state
     * @param bagNumber
     * @return
     */
    int selectCountCarList(@Param("carNumber") String carNumber, @Param("state") int state, @Param("bagNumber") String bagNumber);


    /**
     * 模糊查询小车编号
     *
     * @param carNumber
     * @return
     */
    List<Car> selectByCarNumber(String carNumber);

    /**
     * 修改维修状态
     */
    void updateCar(@Param(value = "car") Car car);

    /**
     * 查询小车状态
     *
     * @param carNumber
     * @return
     */
    int selectCarStateByNum(@Param("carNumber") String carNumber);

    /**
     * 修改小车的状态为空闲
     */
    void updateCarState(@Param("carId") int carId);
}