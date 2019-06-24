package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 货物业务层接口
 */
@Service
public interface GoodsService {

    /**
     * 查出货物的货袋Id
     *
     * @return
     */
    Integer selectBagIdByGdId(int goodsId);


    /**
     * 获取货物列表
     */
    List<Goods> selectGoodsList(Goods goods);

    /**
     * 获取货物列表总行数
     */
    int selectCount(Goods goods);

    /**
     * Excel导入
     */
    String insertBatchImport(String fileName, MultipartFile file) throws IllegalAccessException, IOException;

    /**
     * 删除货物
     *
     * @param goodsId
     */
    void deleteGoodsById(Integer goodsId);

    /**
     * 根据货物id查看查看货物信息及三方物流信息
     *
     * @param goodsId
     * @return
     */
    Goods selectGoodsById(Integer goodsId);

    /**
     * 更新货物信息
     *
     * @param goods
     */
    void updateGoods(Goods goods);

    /**
     * 根据货车id查询货物列表信息
     *
     * @param truckId
     * @return
     */
    List<Goods> selectGoodsListByTruckId(Integer truckId, Integer appUserId);

    /**
     * 根据货车id查询货物列表总行数
     *
     * @param truckId
     * @return
     */
    int selectCountGoodsListByTruckId(Integer truckId);

    /**
     * 获取货物快递单号
     * V1.0
     */
    String getDeliveryNumber() throws IllegalAccessException;

    /**
     * 根据三方物流单号查看货物信息
     *
     * @param tripartiteNumber
     * @return
     */
    Goods selectGoodsByTripartiteNumber(String tripartiteNumber);

    /**
     * 根据货物单号查看货物信息
     *
     * @param deliveryNumber
     * @return
     */
    Goods selectGoodsByDeliveryNumber(String deliveryNumber);

    /**
     * 添加转运货物信息
     *
     * @param goods
     */
    void insertTransshipmentGoods(Goods goods);

    /**
     * 货物打包绑定货袋
     * 张占伟
     *
     * @param goods
     */
    void putGoodsInBag(Goods goods);


    /**
     * 根据货袋id查询货袋里货物id
     * 张占伟
     *
     * @param bagId
     * @return
     */
    List<Goods> selectByBagId(int bagId);
    /**
     * 根据app用户id查询货物信息
     * @param appUserId
     * @return
     *//*
    List<Goods> selectTransshipmentGoodsByUserId(Integer appUserId);*/

    /**
     * 根据parentId获取子货物集合
     *
     * @param parentId
     * @return
     */
    List<Goods> selectGoodsByParentId(Integer parentId);

    /**
     * 根据货物id查询货物信息
     *
     * @return
     */
    Goods selectGoodsByGoodsId(Integer goodsId);

    /**
     * 张占伟
     * 修改货物在新加坡的仓库id仓位id
     *
     * @param warehouseId
     * @param wpId
     * @param deliveryNumber
     */
    void updateGoodsInOutWarehouse(int warehouseId, int wpId, String deliveryNumber);


    /**
     * 张占伟
     * 修改货物在国内的仓库id仓位id
     *
     * @param warehouseId
     * @param wpId
     * @param deliveryNumber
     */
    void updateGoodsInToWarehouse(int warehouseId, int wpId, String deliveryNumber);

    /**
     * 张占伟
     * 查询货物id
     *
     * @param deliveryNumber
     * @return
     */
    int selectGoodsIdByDeliveryNumber(String deliveryNumber);

    /**
     * 获取插入后的那条货物id
     *
     * @return
     */
    int selectGoodsId();

    /**
     * 张占伟
     * 查询货物价格根据货物单号
     *
     * @param deliveryNumber
     * @return
     */
    Goods selectGoodsAmountByDeliveryNumber(String deliveryNumber);

    /**
     * 张占伟
     * 支付成功修改订单创建时间支付时间
     *
     * @param goods
     */
    void updateGoodsCreate(Goods goods);

    /**
     * 修改货物打印状态
     *
     * @param goodsId
     */
    void updateGoodsPrintByGoodsId(int goodsId, int isPrint);

    /**
     * 根据货袋id获取货物列表
     *
     * @param bagId
     * @return
     */
    List<Goods> selectCarGoodsList(Integer bagId);

    /**
     * 根据货袋id获取货物列表总条数
     *
     * @param bagId
     * @return
     */
    int selectCountCarGoodsList(Integer bagId);

    /**
     * 扫描三方单号入库
     *
     * @param tripartiteNumber
     * @return
     */
    GoodsDetails insertGoods(String tripartiteNumber);

    /**
     * 转运货物未扫描过的入库问题件
     *
     * @param tripartiteNumber
     * @param warningType
     * @param warningComment
     * @param number
     */
    Integer insertGoodsWarning(String tripartiteNumber, String warningType, String warningComment, String number, int staffId);

    /**
     * 三方货物的入库问题件
     *
     * @param goods
     * @param warningType
     * @param warningComment
     */
    void updateGoodsAndDetails(Goods goods, String warningType, String warningComment, int staffId, String number);

    /**
     * 货物更新
     *
     * @return
     */
    String updateGoodsAndGoodsDetails(PDAGoodsDetailsDto pdaGoodsDetailsDto);

    /**
     * 根据货袋id获取异常货物列表
     *
     * @param bagId
     * @return
     */
    List<Goods> selectCarGoodsWarningList(Integer bagId);

    /**
     * 根据货袋id获取异常货物总条数
     *
     * @param bagId
     * @return
     */
    int selectCountCarGoodsWarningList(Integer bagId);

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
    int selectCountGoodsDetailsByParentId(Integer parentId);

    /**
     * 添加货物成为扣除件原因
     *
     * @param goodsId
     * @param comment
     */
    int updateGoodsCommentById(Integer goodsId, String comment);

    /**
     * 根据三方单号查询货物信息
     *
     * @param tripartiteNumber
     * @return
     */
    List<Goods> selectGoodsListByTripartiteNumber(String tripartiteNumber);

    /**
     * 添加货物
     *
     * @param goods
     */
    void insert(Goods goods);

    /**
     * 后台三方货物入库
     *
     * @param threeGoodsDto
     */
    String updateThreeGoodsInfo(ThreeGoodsDto threeGoodsDto);

    /**
     * 后台转运货物入库
     *
     * @return
     */
    String insertTransshipmentGoodsInfo(TransshipmentGoodsDto transshipmentGoodsDto);

    /**
     * 根据货物id查询货物编辑信息
     *
     * @param goodsId
     * @return
     */
    GoodsAndGoodsDetails getGoodsAndGoodsDetailsById(Integer goodsId);

    /**
     * 后台更新货物信息
     *
     * @param goodsAndGoodsDetails
     * @return
     */
    String updateGoodsAndGoodsDetailsInfo(GoodsAndGoodsDetails goodsAndGoodsDetails);

    /**
     * 根据后台登录人员类型id查询货物列表
     *
     * @param adminUserTypeId
     * @return
     */
    List<Goods> selectThreeGoodsList(int adminUserTypeId, CodGoodsDto codGoodsDto);

    /**
     * 查询后台登录人员类型id录入的货物列表的总条数
     *
     * @param adminUserTypeId
     * @return
     */
    int selectCountThreeGoodsList(int adminUserTypeId, CodGoodsDto codGoodsDto);

    /**
     * 三方导入COD货物
     *
     * @param fileName
     * @param file
     * @return
     */
    String insertCodGoodsList(String fileName, MultipartFile file) throws IOException;

    /**
     * 三方添加货物信息
     *
     * @param codGoods
     */
    String insertCodGoods(CodGoods codGoods);

    /**
     * 查询三方普通货物列表
     *
     * @param adminUserTypeId
     * @param ordinaryGoodsDto
     * @return
     */
    List<Goods> selectOrdinaryGoodsList(int adminUserTypeId, OrdinaryGoodsDto ordinaryGoodsDto);

    /**
     * 查询三方普通货物列表总条数
     *
     * @param adminUserTypeId
     * @param ordinaryGoodsDto
     * @return
     */
    int selectCountOrdinaryGoodsList(int adminUserTypeId, OrdinaryGoodsDto ordinaryGoodsDto);

    /**
     * 三方导入普通货物
     *
     * @param fileName
     * @param file
     * @return
     */
    String insertOrdinaryGoodsList(String fileName, MultipartFile file) throws IOException;

    /**
     * 三方添加普通货物
     *
     * @param ordinaryGoods
     * @return
     */
    String insertOrdinaryGoods(OrdinaryGoods ordinaryGoods);

    /**
     * 获取货物订单统计
     *
     * @param goodsOrderDto
     * @return
     */
    List<ExportGoodsOrder> selectGoodsListByUserName(GoodsOrderDto goodsOrderDto);

    /**
     * 获取货物订单总条数统计
     *
     * @param goodsOrderDto
     * @return
     */
    int selectCountGoodsListByUserName(GoodsOrderDto goodsOrderDto);

    /**
     * 根据时间获取当日订单数量
     *
     * @param nowDate
     * @return
     */
    List<ExportGoodsOrder> selectGoodsBySendTime(String nowDate, String nowDate1);

    /**
     *
     * @param adminUserTypeId
     * @return
     */
    // List<ExportGoodsOrder> selectThreeGoodsByAdminUserTypeId(int adminUserTypeId);

    /**
     * 根据货物id查询COD货物详情
     *
     * @param goodsId
     * @return
     */
    List<CodGoods> selectCodGoodsById(int goodsId);

    /**
     * 根据货物id查询普通货物详情
     *
     * @param goodsId
     * @return
     */
    List<OrdinaryGoods> selectOrdinaryGoodsById(int goodsId);

    /**
     * 根据后台人员登录类型查询货物信息
     *
     * @param adminUserTypeId
     * @return
     */
    List<ExportGoodsOrder> selectThreeGoodsByAdminUserTypeId(int adminUserTypeId);

    /**
     * 导入三方物流公司完成货物派送的信息
     *
     * @param fileName
     * @param file
     */
    String insertGoodsSendCondition(String fileName, MultipartFile file) throws IOException, ParseException;

    /**
     * 查询三方货物列表
     *
     * @param tripartiteNumber
     * @return
     */
    List<Goods> selectByTripartiteNumber(String tripartiteNumber);

    /**
     * 将包裹从货袋中移除
     * @param goodsId
     */
     void  updateGoodsPackingCondition(int goodsId);

    /**
     * 根据货物所属仓库id查询所有的货物
     * @param warehouseId
     * @return
     */
    List<ExportGoodsOrder> selectGoodsListByWarehouseId(int warehouseId);
}
