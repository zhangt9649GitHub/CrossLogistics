package com.siruiman.crosslogistics.service;


import com.siruiman.crosslogistics.model.Bag;
import com.siruiman.crosslogistics.model.Car;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.Truck;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BagSerivce {

    /**
     * 查询所有的货袋
     *
     * @return
     */
    List<Bag> getAll(Bag bag);

    /**
     * 查询所有货袋的个数
     *
     * @return
     */
    int getCountBag(Bag bag);

    /**
     * 货袋添加
     *
     * @param bag
     */

    void insertBag(Bag bag);

    /**
     * 根据货袋id查询所属的仓库仓位
     *
     * @param bagId
     * @return
     */
    Bag selectBagDetailedOfWarehouseById(int bagId);

    /**
     * 根据货车id查询货袋列表信息
     *
     * @param truckId
     * @return
     */
    List<Bag> selectBagListByTruckId(Integer truckId);

    /**
     * 根据货车id查询货袋列表总行数
     *
     * @param truckId
     * @return
     */
    int selectCountBagListByTruckId(Integer truckId);

    /**
     * 根据货袋id查询关联货车信息
     * 张占伟
     *
     * @param bagId
     * @return
     */
    Truck selectBagDetailedOfTruckById(int bagId);

    /**
     * 根据货袋id查询小车信息及货袋订单信息单号
     *
     * @param bagId
     * @return
     */
    Car getBagDetailedOfCar(int bagId);

    /**
     * 根据货袋编号查询货袋id
     *
     * @param bagNumber
     * @return
     */
    int selectByBagNumber(String bagNumber);

    /**
     * 修改货袋状态根据货袋bagNumber
     *
     * @param bag
     */
    void updateBagState(Bag bag);


    /**
     * 查询货袋id根据货袋编号
     *
     * @param bagNumber
     * @return
     */
    int selectBagIdByBagNumber(String bagNumber);


    /**
     * 根据货袋编号查询货袋详情
     *
     * @param bagNumber
     * @return
     */
    Bag selectBagDetailedByBagNumber(String bagNumber);

    /**
     * 货袋更新
     *
     * @param bag
     */
    void updateBag(Bag bag);

    /**
     * 根据货袋id查询货袋详情
     *
     * @param bagId
     * @return
     */
    Bag selectBagById(int bagId);


    /**
     * 修改货袋在中国的仓库id仓位id
     *
     * @param warehouseId
     * @param wpId
     * @param bagId
     */
    void updateBagInitWarehouse(int warehouseId, int wpId, int bagId);

    /**
     * 修改货袋在新加坡的仓库id仓位id
     *
     * @param warehouseId
     * @param wpId
     * @param bagId
     */
    void updateBagLastWarehouse(int warehouseId, int wpId, int bagId);

    /**
     * 查询货袋所属区域id 集结点
     *
     * @param bagNumber
     * @return
     */
    Bag selectBagByNumber(String bagNumber);


    /**
     * 更新货袋状态货袋装车出库
     *
     * @param bagId
     * @param truckId
     */
    void updateBagInTruck(int bagId, int truckId);

    /**
     * 查询可以打印的货袋信息
     *
     * @return
     */
    Bag selectBagPrint();

    /**
     * 修改货袋打印状态
     *
     * @param bagId
     */
    void updateBagPrintState(int bagId, int printState);

    /**
     * 检查货袋是否已经装车出库
     *
     * @param bagId
     * @return true 未出库 ,false 已出库
     */
    boolean checkBagOutBound(int bagId);

    /**
     * 查出货袋状态
     *
     * @param bagId
     * @return
     */
    Bag selectBagStateById(int bagId);

    /**
     * 根据小车id和状态获取货袋id
     *
     * @param carId
     * @param state
     * @return
     */
    Bag selectBagId(Integer carId, Byte state);

    /**
     * 获取货袋列表
     *
     * @param bag
     * @return
     */
    List<Bag> selectBagList(Bag bag);

    /**
     * 获取货袋列表总条数
     *
     * @param bag
     * @return
     */
    int selectCountBagList(Bag bag);

    /**
     * 根据货袋id获取货袋详情
     *
     * @param bagId
     * @return
     */
    Bag selectBagDetailsById(int bagId);

    /**
     * 根据货袋id修改货袋删除状态为已删除
     *
     * @param bagId
     */
    void deleteBagById(Integer bagId);

    /**
     * 检查货物是否已经入库
     *
     * @param bagId
     * @return
     */
    boolean checkBagInSGByBagId(int bagId);


    /**
     * 查出指定集结点已入新加坡库的未出库的货袋装车出库
     *
     * @param rallyPointId
     * @return
     */

    List<Bag> selectByRallyPointId(int rallyPointId);

    /**
     * 根据区域集结点id查询当前区域入新加坡库的货袋
     *
     * @param singaporeAreaId
     * @return
     */
    List<Bag> selectBagBySingaporeAreaId(int singaporeAreaId);

    /**
     * 修改货袋状态为直接配送
     *
     * @param bagId
     * @param appUserId 大货车司机id
     */
    void updateBagDirect(int bagId, int appUserId);

    /**
     * 根据货车司机的用户id查询所有未扫描的货袋
     * @param appUserId
     * @return
     */
    List<Bag> selectBagListByUserId(int appUserId);


}
