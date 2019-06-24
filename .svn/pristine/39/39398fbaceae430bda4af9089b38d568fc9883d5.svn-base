package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.LogisticInfo;
import com.siruiman.crosslogistics.model.LogisticInfo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface LogisticInfoMapper {

    int deleteByPrimaryKey(Integer logisticInfoId);


    int insert(LogisticInfo record);


    LogisticInfo selectByPrimaryKey(Integer logisticInfoId);


    List<LogisticInfo> selectAll();


    int updateByPrimaryKey(LogisticInfo record);

    /**
     * 根据货物id查询货物物流信息
     *
     * @param goodsId
     * @return
     */
    List<LogisticInfo> selectGoodsLogisticInfoByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 根据货物id查询节点操作人员信息
     *
     * @param goodsId
     * @return
     */
    List<LogisticInfo> selectOperateNameById(@Param("goodsId") Integer goodsId);

    /**
     * 根据货物id查询节点操作人员信息的总行数
     *
     * @param goodsId
     * @return
     */
    int selectOperateNameCount(@Param("goodsId") Integer goodsId);

    /**
     * 根据员工id获取总计件数
     *
     * @param staffId
     * @return
     */
    int selectPieceByStaffId(@Param("staffId") int staffId);

    /**
     * 根据员工姓名获取本月操作记录
     *
     * @param staffId
     * @param firstDayOfMonth
     * @param lastDayOfMonth
     * @return
     */
    List<LogisticInfo> selectLogisticInfoBystaffId(@Param("staffId") int staffId, @Param("firstDayOfMonth") String firstDayOfMonth, @Param("lastDayOfMonth") String lastDayOfMonth);

    /**
     * 根据员工姓名获取本月操作记录总行数
     *
     * @param staffId
     * @param firstDayOfMonth
     * @param lastDayOfMonth
     * @return
     */
    int selectCountLogisticInfoBystaffId(@Param("staffId") int staffId, @Param("firstDayOfMonth") String firstDayOfMonth, @Param("lastDayOfMonth") String lastDayOfMonth);

    /**
     * 根据货袋id获取
     *
     * @param bagId
     * @return
     */
    List<LogisticInfo> selectBagLogisticInfoByBagId(@Param("bagId") int bagId);

    /**
     * 根据货袋id获取操作人记录总条数
     *
     * @param bagId
     * @return
     */
    int selectBagOperateNameCount(@Param("bagId") int bagId);

    /**
     * 根据货袋id和操作类型获取物流进程
     *
     * @param bagId
     * @param OperateType
     * @return
     */
    LogisticInfo selectBagId(@Param("bagId") int bagId, @Param("OperateType") String OperateType);

    /**
     * 根据货物id和操作类型获取物流进程
     *
     * @param goodsId
     * @param OperateType
     * @return
     */
    LogisticInfo selectGoodsLogisticInfoByIdType(@Param("goodsId") int goodsId, @Param("OperateType") String OperateType);


    /**
     * 根据货物id获取货物最新的一条数据
     *
     * @param goodsId
     * @return
     */
    LogisticInfo selectLogisticInfoByGoodsId(@Param("goodsId") int goodsId);


    /**
     * 根据货物id获取货物最新的一条数据
     *
     * @param bagId
     * @return
     */
    LogisticInfo selectLogisticInfoByBagId(@Param("bagId") int bagId);

    /**
     * 查询货物物流进程根据快递单号
     *
     * @param deliveryNum
     * @return
     */
    List<LogisticInfo> selectLogisticInfoByDeliveryNum(@Param("deliveryNum") String deliveryNum);

    /**
     * 查询货物物流进程根据三方快递单号
     *
     * @param tripartiteNumber
     * @return
     */
    List<LogisticInfo2> selectLogisticInfoByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 根据三方物流单号查询物流进程
     *
     * @param tripartiteNumber
     * @return
     */
    List<LogisticInfo2> selectLogisticInfo(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     *
     * @param map
     * @return
     */
    List<LogisticInfo> selectLogisticInfoBystaffIdList(HashMap<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    List<LogisticInfo> selectLogisticInfoByadminUidList(HashMap<String, Object> map);
}