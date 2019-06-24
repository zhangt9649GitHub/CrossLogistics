package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.AppUserAddress;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.GoodsDetails;
import com.siruiman.crosslogistics.model.PeopleGoods;
import com.siruiman.crosslogistics.model.dto.PDAGoodsDto;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public interface GoodsDetailsService {
    /**
     * 查看货物发货人信息及收货人信息
     *
     * @param goodsId
     * @return
     */
    GoodsDetails selectGoodsDetailById(Integer goodsId);

    /**
     * 更新货物详情信息
     *
     * @param goodsDetails
     */
    void updateGoodsDetails(GoodsDetails goodsDetails);

    /**
     * 根据三方物流单号查询货物详情信息
     */
    GoodsDetails selectGoodsDetailsByTripartiteNumber(String tripartiteNumber);

    /**
     * 根据快递单号查询货物详情信息
     *
     * @param deliveryNumber
     * @return
     */
    GoodsDetails selectGoodsDetailsByDeliveryNumber(String deliveryNumber);

    /**
     * 添加转运货物信息
     *
     * @param goodsDetails
     */
    void insertTransshipmentGoods(GoodsDetails goodsDetails);

    /**
     * 根据货物订单编号查询货物详情
     * 张占伟
     *
     * @param deliveryNumber
     * @return
     */
    GoodsDetails getGoodsDetailsByDeliveryNumber(String deliveryNumber);

    /**
     * 根据货物快递单号查出货物详情表对应的id
     * 张占伟
     *
     * @param deliveryNumber
     * @return
     */
    int selectgdIdByDeliveryNumber(String deliveryNumber);

    /**
     * 修改货物打包状态 及打包操作人
     * 张占伟
     *
     * @param goodsDetails
     */
    void packgingGoods(GoodsDetails goodsDetails);

    /**
     * 根据app用户id查询货物列表
     *
     * @param appUserId
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsByUserId(Integer appUserId, String goodState, String transportType, String deliveryNumber);

    /**
     * 根据app用户id查询货物列表总条数
     *
     * @param appUserId
     * @param goodState
     * @param transportType
     * @param deliveryNumber
     * @return
     */
    int selectCountTransshipmentGoodsByUserId(Integer appUserId, String goodState, String transportType, String deliveryNumber);


    /**
     * 修改货物运输状态根据货物id
     * 张占伟
     *
     * @param details
     */
    void updateGoodsState(GoodsDetails details);

    /**
     * 根据货物的parentId查询合并转运详情
     *
     * @param parentId
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsListByParentId(Integer parentId);

    /**
     * 根据快递单号查询货物信息
     *
     * @param deliveryNumber
     * @return
     */
    GoodsDetails selectGoodsInfoByDeliveryNumber(String deliveryNumber);

    /**
     * 获取转运货物列表
     *
     * @param goodsDetails
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsList(GoodsDetails goodsDetails);

    /**
     * 获取转运列表总行数
     *
     * @param goodsDetails
     * @return
     */
    int selectTransshipmentGoodsListCount(GoodsDetails goodsDetails);

    /**
     * 根据货物id获取货物详情
     *
     * @param goodsId
     * @return
     */
    GoodsDetails selectTransshipmentGoodsDetails(int goodsId);

    /**
     * 查询货袋里的货物详情根据货袋id
     * 张占伟
     *
     * @param bagId
     * @return
     */

    List<GoodsDetails> getGoodsInBagDetailsByBagId(int bagId);

    /**
     * 查看货物运费支付状态
     * 张占伟
     *
     * @param deliveryNumber
     * @param appUserId
     * @return
     */
    GoodsDetails selectGooodsPayStateByNOAndUID(String deliveryNumber, int appUserId);

    /**
     * 根据货物id查询转运信息
     *
     * @param goodsIds
     * @return
     */
    List<GoodsDetails> selectGoodsDetailByIds(Integer[] goodsIds);

    /**
     * 合并转运货物在合并，删除旧的合并记录
     *
     * @param goodsId
     */
    void deleteGoodsDetailsById(int goodsId);


    /**
     * 修改货袋支付状态为已支付
     *
     * @param details
     */
    void updateGoodsPayState(GoodsDetails details);


    /**
     * 根据三方物流单号获取货物详情
     *
     * @param tripartiteNumber
     * @return
     */
    GoodsDetails selectGoodsByTripartiteNumber(String tripartiteNumber);

    /**
     * 获取转运待打包列表
     *
     * @param createTime
     * @param singaporeAreaId
     * @param rallyPointId
     * @param exitWayNumber
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsStateList(String createTime, Integer singaporeAreaId, Integer rallyPointId, Integer exitWayNumber);


    /**
     * 获取可以打印货物信息列表
     */
    GoodsDetails selectGoodsPrint();


    /**
     * 根据货物ID查出用户id
     *
     * @param goodsId
     * @return
     */
    int selectAPPUIdByGdId(Integer goodsId);

    /**
     * 提交合并转运
     *
     * @param appUserId
     * @param goodsIds
     */
    void insertCombinedTransport(int appUserId, Integer[] goodsIds,int WarehouseId);

    /**
     * 提交转运货物收货人信息
     *
     * @param appUserAddress
     * @param goodsId
     */
    void updateAppUserAddress(AppUserAddress appUserAddress, Integer goodsId);

    /**
     * 计算运费
     *
     * @param goodsDetailsList
     * @param goodsId
     * @return
     */
    String updategoodsDetailsList(List<GoodsDetails> goodsDetailsList, Integer goodsId);

    /**
     * 转运的货物入库
     *
     * @return
     */
    String updateGoodsAndGoodsDetails(PDAGoodsDto pdaGoodsDto);

    /**
     * 三方的货物入库
     *
     * @return
     */
    String updateGoodsDetailsAndGoods(PDAGoodsDto pdaGoodsDto);

    /**
     * 根据三方物流单号查询货物信息
     *
     * @param tripartiteNumber
     * @return
     */
    List<GoodsDetails> selectGoodsDetailByTripartiteNumber(String tripartiteNumber);

    /**
     * 入库分箱货物的子货物数据
     *
     * @param pdaGoodsDto
     * @return
     */
    String updateGoodsDetailsByOrder(PDAGoodsDto pdaGoodsDto);

    /**
     * 添加货物详情信息
     *
     * @param goodsDetails
     */
    void insert(GoodsDetails goodsDetails);

    /**
     * 添加货物信息
     */
    void insertGoodsDetails(GoodsDetails goodsDetails, int oldtotalGoods, int i);


    /**
     * 根据货袋编号查出货袋里所有货物详情
     *
     * @param wildMatch
     * @return
     */
    ArrayList<GoodsDetails> selectGoodsInfoByBagNumber(String wildMatch);

    /**
     * 更改货物接收状态
     *
     * @param goodsId
     */
    void updateIsReceiveGoods(int goodsId);


    /**
     * 根据三方快递单号查询货物详情列表
     *
     * @param tripartiteNumber
     * @return
     */
    List<GoodsDetails> selectGoodsDetailListByTripartiteNumber(String tripartiteNumber);

    /**
     * 根据提供的所有货物id计算价钱
     *
     * @param goodsIds
     * @return
     */
    PeopleGoods calculateGoodsPrice(String goodsIds);

    /**
     * 转运货物入库
     * @param pdaGoodsDto
     * @return
     */
    String insertGoodsAndGoodsDetails(PDAGoodsDto pdaGoodsDto);

    List<GoodsDetails> selectGoodsDetailListByGoodsIds(Set<Integer> goodsIds);
}
