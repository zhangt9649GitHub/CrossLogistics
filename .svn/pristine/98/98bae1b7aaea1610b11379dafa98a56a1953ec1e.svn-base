package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.GoodsOrderDto;
import com.siruiman.crosslogistics.model.dto.OrdinaryGoodsDto;
import com.siruiman.crosslogistics.model.dto.ThreeGoodsDto;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {


    /**
     * 查出货袋id根据货物id
     *
     * @param goodsId
     * @return
     */
    Integer selectBagIdByGdId(int goodsId);

    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    /**
     * 获取货物列表
     *
     * @param map
     * @return
     */
    List<Goods> selectGoodsList(HashMap<String, Object> map);

    /**
     * 获取货物列表行数
     *
     * @param map
     * @return
     */
    int selectCount(HashMap<String, Object> map);

    /**
     * 根据货物id查看货物信息及三方物流信息
     *
     * @param goodsId
     * @return
     */
    Goods selectGoodsById(@Param("goodsId") Integer goodsId);

    /**
     * 根据货车id查询货物列表信息
     *
     * @param truckId
     * @return
     */
    List<Goods> selectGoodsListByTruckId(@Param("truckId") Integer truckId, @Param("appUserId") Integer appUserId);

    /**
     * 根据货车id查询货物列表总行数
     *
     * @param truckId
     * @return
     */
    int selectCountGoodsListByTruckId(Integer truckId);

    /**
     * 查询货物快递单号
     *
     * @return
     */
    String selectDeliveryNumber();

    /**
     * 根据三方物流单号查看货物信息
     *
     * @param tripartiteNumber
     * @return
     */
    Goods selectGoodsByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 根据货物单号查看货物信息
     *
     * @param deliveryNumber
     * @return
     */
    Goods selectGoodsByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 根据货物快递单号与货袋绑定
     *
     * @param goods
     */
    void updateGoodsInBag(Goods goods);

    /**
     * 根据parentId获取子货物集合
     *
     * @param parentId
     * @return
     */
    List<Goods> selectGoodsByParentId(@Param("parentId") Integer parentId);

    /**
     * 张占伟
     * 修改货物在新加坡的仓库id仓位id
     *
     * @param warehouseId
     * @param wpId
     * @param updateTime
     * @param deliveryNumber
     */
    void updateGoodsInOutWarehouse(@Param("warehouseId") int warehouseId, @Param("wpId") int wpId, @Param("updateTime") String updateTime, @Param("deliveryNumber") String deliveryNumber);

    /**
     * 张占伟
     * 修改货物在国内的仓库id仓位id
     *
     * @param warehouseId
     * @param wpId
     * @param updateTime
     * @param deliveryNumber
     */
    void updateGoodsInToWarehouse(@Param("warehouseId") int warehouseId, @Param("wpId") int wpId, @Param("updateTime") String updateTime, @Param("deliveryNumber") String deliveryNumber);

    /**
     * 查出货物id根据快递单号
     * 张占伟
     *
     * @param deliveryNumber
     * @return
     */
    int selectGoodsIdByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 获取插入后的那条货物id
     *
     * @return
     */
    int selectGoodsId();

    /**
     * 查询货物运费价格根据快递单号
     *
     * @param deliveryNumber
     * @return
     */
    Goods selectGoodsAmountByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);


    /**
     * 修改货物支付时间(订单创建时间)
     *
     * @param goods
     */
    void updateGoodsCreate(@Param("goods") Goods goods);

    /**
     * 修改货物为可以打印
     *
     * @param goodsId
     */
    void updateGoodsPrintByGoodsId(@Param("goodsId") int goodsId, @Param("isPrint") int isPrint);


    /**
     * 根据货袋id查出所有的货物id
     * 张占伟
     *
     * @param bagId
     * @return
     */
    List<Goods> selectByBagId(int bagId);

    /**
     * 根据货袋id获取货物列表
     *
     * @param bagId
     * @return
     */
    List<Goods> selectCarGoodsList(@Param("bagId") Integer bagId);

    /**
     * 根据货袋id获取货物列表总条数
     *
     * @param bagId
     * @return
     */
    int selectCountCarGoodsList(@Param("bagId") Integer bagId);

    /**
     * 根据货袋id获取异常货物列表
     *
     * @param bagId
     * @return
     */
    List<Goods> selectCarGoodsWarningList(@Param("bagId") Integer bagId);

    /**
     * 根据货袋id获取异常货物总条数
     *
     * @param bagId
     * @return
     */
    int selectCountCarGoodsWarningList(@Param("bagId") Integer bagId);

    /**
     * 获取组合转运子货物信息
     *
     * @param parentId
     * @return
     */
    List<Goods> selectGoodsDetailsByParentId(Integer parentId);

    /**
     * 获取组合转运子货物的总条数
     *
     * @param parentId
     * @return
     */
    int selectCountGoodsByParentId(@Param("parentId") Integer parentId);

    /**
     * 修改货物信息
     *
     * @param goods
     */
    void updateGoods(Goods goods);

    /**
     * 添加货物成为扣除件原因
     *
     * @param goodsId
     * @param comment
     */
    void updateGoodsCommentById(@Param("goodsId") Integer goodsId, @Param("comment") String comment);


    /**
     * 根据三方单号查询货物信息
     *
     * @param tripartiteNumber
     * @return
     */
    List<Goods> selectGoodsListByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 根据快递单号查询货物信息
     *
     * @param deliveryNumber
     * @return
     */
    Goods selectGoodsInfoByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 更新货物信息
     *
     * @param goods
     */
    void updateGoodsInfo(Goods goods);

    /**
     * 后台三方货物入库
     *
     * @param goods
     */
    void updateThreeGoodsInfo(Goods goods);

    /**
     * 更新货物信息
     */
    void updateGoodsInformation(Goods goods);

    /**
     * 删除分箱货物，减少总货物的数量
     */
    void updateGoodsPoints(@Param("goodsId") int goodsId, @Param("totalGoods") int totalGoods);

    /**
     * 删除到最后一箱去掉总箱的记录，将快递单号进行修改
     *
     * @param deliveryNumber
     * @param goodsId
     */
    void updateGoodsDeliveryNumber(@Param("deliveryNumber") String deliveryNumber, @Param("goodsId") int goodsId);

    /**
     * 根据后台登录人员类型id查询货物列表
     *
     * @return
     */
    List<Goods> selectThreeGoodsList(HashMap<String, Object> map);

    /**
     * 查询后台登录人员类型id录入的货物列表的总条数
     *
     * @return
     */
    int selectCountThreeGoodsList(HashMap<String, Object> map);

    /**
     * 查询三方普通货物列表
     *
     * @return
     */
    List<Goods> selectOrdinaryGoodsList(HashMap<String, Object> map);

    /**
     * 查询三方普通货物列表总条数
     *
     * @return
     */
    int selectCountOrdinaryGoodsList(HashMap<String, Object> map);

    /**
     * 获取货物订单统计
     *
     * @param map
     * @return
     */
    List<ExportGoodsOrder> selectGoodsListByUserName(HashMap<String, Object> map);

    /**
     * 获取货物订单总条数统计
     *
     * @param map
     * @return
     */
    int selectCountGoodsListByUserName(HashMap<String, Object> map);

    /**
     * @return
     */
    List<ExportGoodsOrder> selectGoodsBySendTime(@Param("nowDate") String nowDate, @Param("nowDate1") String nowDate1);

    /**
     * 根据货物id查询货物详情
     *
     * @param goodsId
     * @return
     */
    List<CodGoods> selectCodGoodsById(@Param("goodsId") int goodsId);

    /**
     * 根据货物id查询普通货物详情
     *
     * @param goodsId
     * @return
     */
    List<OrdinaryGoods> selectOrdinaryGoodsById(@Param("goodsId") int goodsId);

    /**
     * 根据后台人员登录类型查询货物信息
     *
     * @param adminUserTypeId
     * @return
     */
    List<ExportGoodsOrder> selectThreeGoodsByAdminUserTypeId(@Param("adminUserTypeId") int adminUserTypeId);

    /**
     * 变更货物状态为异常
     *
     * @param goods
     */
    void updateGoodsStatus(Goods goods);


    /**
     * 查询三方货物列表
     *
     * @param tripartiteNumber
     * @return
     */
    List<Goods> selectByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 根据货袋id与货物状态，查询所有的货物
     * @param bagId
     * @param status
     * @return
     */
    List<Goods> selectGoodsWarningByBagId(@Param("bagId") int bagId,@Param("status") int status);

    /**
     * 将货物从货袋中移除
     * @param goodsId
     * @param date
     */
    void updateGoodsBagId(@Param("goodsId") int goodsId,@Param("date") Date date);

    /**
     * 更新转运货物的价格
     * @param goods
     */
    void updateGoodsAmount(Goods goods);

    /**
     * 根据货物所属仓库id查询所有的货物
     * @param warehouseId
     * @return
     */
    List<ExportGoodsOrder> selectGoodsListByWarehouseId(@Param("warehouseId") int warehouseId);
}