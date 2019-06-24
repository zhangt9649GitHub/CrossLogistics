package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.LogisticInfo;
import com.siruiman.crosslogistics.model.LogisticInfo2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogisticInfoService {
    /**
     * 根据货物id查询货物物流信息
     *
     * @param goodsId
     * @return
     */
    List<LogisticInfo> selectGoodsLogisticInfoByGoodsId(Integer goodsId);

    /**
     * 根据货物id查询节点操作人员信息
     *
     * @param goodsId
     * @return
     */
    List<LogisticInfo> selectOperateNameById(Integer goodsId);

    /**
     * 根据货物id查询节点操作人员信息的总行数
     *
     * @param goodsId
     * @return
     */
    int selectOperateNameCount(Integer goodsId);

    /**
     * 货物入库添加物流信息
     */
    void insertLogisticInfo(LogisticInfo logisticInfo);

    /**
     * 根据员工id获取总计件数
     *
     * @param staffId
     * @return
     */
    int selectPieceByStaffId(int staffId);

    /**
     * 根据员工姓名获取本月操作记录
     *
     * @param staffId
     * @param firstDayOfMonth
     * @param lastDayOfMonth
     * @return
     */
    List<LogisticInfo> selectLogisticInfoBystaffId(int staffId, String firstDayOfMonth, String lastDayOfMonth);

    /**
     * 根据员工姓名获取本月操作记录总行数
     *
     * @param staffId
     * @param firstDayOfMonth
     * @param lastDayOfMonth
     * @return
     */
    int selectCountLogisticInfoBystaffId(int staffId, String firstDayOfMonth, String lastDayOfMonth);

    /**
     * 根据货袋id获取
     *
     * @param bagId
     * @return
     */
    List<LogisticInfo> selectBagLogisticInfoByBagId(int bagId);

    /**
     * 根据货袋id获取操作人记录总条数
     *
     * @param bagId
     * @return
     */
    int selectBagOperateNameCount(int bagId);

    /**
     * 正常流程步骤    1.货物入库(steps staffId goodsId) 2.货物打包(steps staffId goodsId bagId) 3 货袋出库(steps staffId bagId)
     * 4 货袋接收(steps staffId bagId) 5.货袋入仓(steps staffId bagId) 6.货袋装车(steps staffId bagId) 7.货袋装配小车(steps appUserId bagId)
     * 8.小车货袋配送中(steps appUserId bagId) 9.小车货物配送完成(steps appUserId goodsId)
     * 增加流程  10.转运货物提交合并转运 （steps appUserId goodsId） 11.转运货物发起转运（steps appUserId goodsId）
     * 增加流程 12.货车货袋配送中（steps appUserId bagId） 13.货车货物配送完成（steps appUserId goodsId）
     * 无值传0
     *
     * @param steps     第几步
     * @param staffId   员工id
     * @param appUserId app用户id
     * @param goodsId   货物id
     * @param bagId     货袋id
     */
    void insertNormalLogisticInfo(int steps, int staffId, int appUserId, int goodsId, int bagId);


    /**
     * 异常流程  1.货物入库异常（steps,warningComment，goodsId，staffId） 2 货物小车骑手派送异常(steps,warningComment，goodsId，appUserId)
     * 增加流程问题件接收 3.货物问题件接收成功
     * 增加流程 4.货物货车司机派送异常（steps,warningComment，goodsId，appUserId）
     * 无值传0
     *
     * @param steps          第几步
     * @param warningComment 异常描述
     * @param goodsId        货物id
     * @param staffId        员工id
     * @param appUserId      app用户id
     */
    void insertAbnormalLogisticInfo(int steps, String warningComment, int goodsId, int staffId, int appUserId);

    /**
     * 根据货物id获取货物最新的一条数据
     *
     * @param goodsId
     * @return
     */
    LogisticInfo selectLogisticInfoByGoodsId(int goodsId);

    /**
     * 查出货袋的最新物流进程
     *
     * @param bagId
     * @return
     */
    LogisticInfo selectBagInfoByBagId(Integer bagId);

    /**
     * 查询货物物流进程根据快递单号
     *
     * @param deliveryNum
     * @return
     */
    List<LogisticInfo> selectLogisticInfoByDeliveryNum(String deliveryNum);


    /**
     * 查询货物物流进程根据三方快递单号
     *
     * @param tripartiteNumber
     * @return
     */
    List<LogisticInfo2> selectLogisticInfoByTripartiteNumber(String tripartiteNumber);

    /**
     * 根据三方物流单号查询物流进程
     *
     * @param tripartiteNumber
     * @return
     */
    List<LogisticInfo2> selectLogisticInfo(String tripartiteNumber);

    /**
     * 根据员工id查询货物送出的物流进程
     * @param staffId
     * @param startDate
     * @param endDate
     * @return
     */
    List<LogisticInfo> selectLogisticInfoBystaffIdList(int staffId,String startDate, String endDate);

    /**
     * 根据后台管理员id查询货物送出的物流进程
     * @param adminUid
     * @param startDate
     * @param endDate
     * @return
     */
    List<LogisticInfo> selectLogisticInfoByadminUidList(int adminUid,String startDate, String endDate);
}
