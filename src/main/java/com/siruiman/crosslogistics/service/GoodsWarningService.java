package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.GoodsDetails;
import com.siruiman.crosslogistics.model.GoodsWarning;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsWarningService {
    /**
     * 查询异常货物列表
     *
     * @param goodsWarning
     * @return
     */
    List<GoodsWarning> selectGoodsWarningList(GoodsWarning goodsWarning);

    /**
     * 查询异常货物列表总行数
     *
     * @param goodsWarning
     * @return
     */
    int selectCountGoodsWarningList(GoodsWarning goodsWarning);
    /**
     * 根据异常货物id查询异常货物
     * @param goodsWarningId
     * @return
     */
    /*GoodsWarning selectGoodsWarningById(Integer goodsWarningId);*/

    /**
     * 根据货物id查询异常货物信息
     *
     * @param goodsId
     * @return
     */
    GoodsWarning selectGoodsWarningByGoodsId(Integer goodsId);

    /**
     * 更新异常货物
     *
     * @param goodsWarning
     */
    void updateGoodsWarning(GoodsWarning goodsWarning);

    /**
     * 根据货物id删除异常货物
     */
    void deleteGoodsWarningByGoodsId(Integer goodsId);

    /**
     * 添加异常货物
     *
     * @param goodsWarning
     */
    void insertGoodsWarning(GoodsWarning goodsWarning);

    /**
     * 编辑异常货物信息
     *
     * @param goodsDetails
     */
    void updateGoodsWarningDetails(GoodsDetails goodsDetails);

    /**
     * 小车送货出现异常件，添加异常货物
     *
     * @param goodsId        货物id
     * @param warningComment 异常描述
     * @return 1.为添加成功
     */
    Integer insertGoodsWarningByAppUser(Integer goodsId, String warningComment);

    /**
     * 获取异常货物快递单号
     *
     * @return
     */
    List<String> getGoodsWarningNumber();

    /**
     * 更新货物异常信息
     *
     * @param goodsDetails
     */
    void updateGoodsWarningInfo(GoodsDetails goodsDetails);

}
