package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Bag;
import com.siruiman.crosslogistics.model.Goods;
import com.siruiman.crosslogistics.model.GoodsDetails;
import com.siruiman.crosslogistics.model.GoodsFromInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Mapper
@Repository
public interface GoodsDetailsMapper {

    int deleteByPrimaryKey(Integer gdId);


    int insert(GoodsDetails record);


    GoodsDetails selectByPrimaryKey(Integer gdId);


    List<GoodsDetails> selectAll();


    int updateByPrimaryKey(GoodsDetails record);

    /**
     * 根据货物id删除货物详情信息
     *
     * @param goodsId
     */
    void deleteGoodsDetailsByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 查看货物发货人信息及收货人信息
     *
     * @param goodsId
     * @return
     */
    GoodsDetails selectGoodsDetailById(@Param("goodsId") Integer goodsId);

    /**
     * 根据货袋id 查询货袋里的所有货物详情
     * 张占伟
     *
     * @return
     */
    List<GoodsDetails> selectGoodsDetailsByBagID(@Param("bagId") int bagId);

    /**
     * 根据货袋id 查询货袋里的所有货物详情的个数
     * 分页使用
     * 张占伟
     *
     * @param bagId
     * @return
     */
    int selectCountGoodsDetailsByBagID(@Param("bagId") int bagId);

    /**
     * 根据三方物流单号查询货物详情信息
     */
    GoodsDetails selectGoodsDetailsByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 根据快递单号查询货物详情信息
     *
     * @param deliveryNumber
     * @return
     */
    GoodsDetails selectGoodsDetailsByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);


    /**
     * 根据货物快递单号查询货物对应的id修改操作状态使用
     * 张占伟
     *
     * @param deliveryNumber
     * @return
     */
    int selectgdIdByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 修改货物打包状态
     * 张占伟
     *
     * @param goodsDetails
     */
    void updateGoodsPacking(GoodsDetails goodsDetails);

    /**
     * 根据app用户id查询货物列表
     *
     * @param appUserId
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsByUserId(@Param("appUserId") Integer appUserId, @Param("goodState") String goodState, @Param("transportType") String transportType, @Param("deliveryNumber") String deliveryNumber, @Param("transportType1") String transportType1, @Param("goodState1") String goodState1);


    /**
     * 修改货物运输状态
     * 根据货物id               张占伟
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
    List<GoodsDetails> selectTransshipmentGoodsListByParentId(@Param("parentId") Integer parentId);

    /**
     * 根据快递单号查询货物信息
     *
     * @param deliveryNumber
     * @return
     */
    GoodsDetails selectGoodsInfoByDeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

    /**
     * 获取转运货物列表
     *
     * @param
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsList(HashMap<String, Object> map);

    /**
     * 根据货物id获取货物详情
     *
     * @param
     * @return
     */
    int selectTransshipmentGoodsListCount(HashMap<String, Object> map);

    /**
     * 根据货物id获取货物详情
     *
     * @param goodsId
     * @return
     */
    GoodsDetails selectTransshipmentGoodsDetails(@Param("goodsId") int goodsId);


    /**
     * 根据货袋id查询货袋里的货物详情
     *
     * @param bagId
     * @return
     */
    List<GoodsDetails> getGoodsInBagDetailsByBagId(@Param("bagId") int bagId);

    /**
     * 查询货物运费支付状态
     * 张占伟
     *
     * @param deliveryNumber
     * @param appUserId
     * @return
     */
    GoodsDetails selectGooodsPayStateByNOAndUID(@Param("deliveryNumber") String deliveryNumber, @Param("appUserId") int appUserId);

    /**
     * 根据三方物流单号获取货物详情
     *
     * @param tripartiteNumber
     * @return
     */
    GoodsDetails selectGoodsByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 修改货物订单支付状态
     *
     * @param details
     */
    void updateGoodsPayState(@Param("details") GoodsDetails details);

    /**
     * 获取转运待打包列表
     *
     * @param map
     * @return
     */
    List<GoodsDetails> selectTransshipmentGoodsStateList(HashMap<String, Object> map);


    /**
     * 根据app用户id查询货物列表总条数
     *
     * @param appUserId
     * @param goodState
     * @param transportType
     * @param deliveryNumber
     * @return
     */
    int selectCountTransshipmentGoodsByUserId(@Param("appUserId") Integer appUserId, @Param("goodState") String goodState, @Param("transportType") String transportType, @Param("deliveryNumber") String deliveryNumber, @Param("transportType1") String transportType1, @Param("goodState1") String goodState1);

    /**
     * 获取可以打印货物信息列表
     */
    GoodsDetails selectGoodsPrint();

    /**
     * 查询用户id根据用户
     *
     * @param goodsId
     * @return
     */
    int selectAPPUIdByGdId(Integer goodsId);

    /**
     * 根据三方物流单号查询货物信息
     *
     * @param tripartiteNumber
     * @return
     */
    List<GoodsDetails> selectGoodsDetailByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 三方货物后台入库
     *
     * @param goodsDetails
     */
    void updateThreeGoodsDetailsInfo(GoodsDetails goodsDetails);

    /**
     * 更新货物详情
     *
     * @param goodsDetails
     */
    void updateGoodsDetailsInformation(GoodsDetails goodsDetails);

    /**
     * 删除到最后一箱去掉总箱的记录，更新货值
     *
     * @param gdId
     * @param itemValue
     */
    void updateGoodsItemValue(@Param("gdId") int gdId, @Param("itemValue") BigDecimal itemValue);

    /**
     * APP计算运费更新货物详情
     *
     * @param goodsDetails
     */
    void updateGoodsDetailsAllInfo(GoodsDetails goodsDetails);

    /**
     * 更新货物详情
     *
     * @param goodsDetails
     */
    void updateGoodsDetails(GoodsDetails goodsDetails);

    /**
     * 变更货物详情为异常
     *
     * @param goodsDetails
     */
    void updateGoodsDetailsState(GoodsDetails goodsDetails);

    /**
     * 根据货物id查询收件人信息
     *
     * @param goodsId
     * @return
     */
    GoodsFromInfo selectGoodsDetailByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 查出货袋里所有的货物详情
     * 张占伟
     *
     * @param bagNumber
     * @return
     */
    List<GoodsDetails> selectGoodsInfoByBagNumber(@Param("bagNumber") String bagNumber);

    void updateGoodsDetailsInfo(GoodsDetails goodsDetails);

    /**
     * 更改货物接收状态
     *
     * @param goodsId
     */
    void updateIsReceiveGoods(@Param("goodsId") int goodsId, @Param("date") Date date);

    /**
     * 根据三方快递单号查询货物详情列表
     *
     * @param tripartiteNumber
     * @return
     */
    List<GoodsDetails> selectGoodsDetailListByTripartiteNumber(@Param("tripartiteNumber") String tripartiteNumber);

    /**
     * 获取货物列表，并按照邮编排序
     *
     * @param goodsIds
     * @return
     */
    List<GoodsFromInfo> selectGoodsDetailByGoodsIds(@Param("goodsIds") List<Integer> goodsIds);

    /**
     * 根据货袋id查询所有货物详情
     * @param bagId
     * @return
     */
    List<GoodsDetails> selectGoodsDetailsByBagId(@Param("bagId") int bagId);

    List<GoodsDetails> selectGoodsDetailListByGoodsIds(@Param("goodsIds") Set<Integer> goodsIds);


    void updateGoodsDetailsInfoALL(GoodsDetails goodsDetails);
}