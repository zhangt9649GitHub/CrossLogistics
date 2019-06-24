package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.GoodsWarning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface GoodsWarningMapper {

    int deleteByPrimaryKey(Integer goodsWarningId);


    int insert(GoodsWarning record);


    GoodsWarning selectByPrimaryKey(Integer goodsWarningId);


    List<GoodsWarning> selectAll();


    int updateByPrimaryKey(GoodsWarning record);

    /**
     * 查询异常货物列表
     *
     * @param map
     * @return
     */
    List<GoodsWarning> selectGoodsWarningList(HashMap<String, Object> map);

    /**
     * 查询异常货物列表总行数
     *
     * @param map
     * @return
     */
    int selectCountGoodsWarningList(HashMap<String, Object> map);

    /**
     * 根据货物id查询异常货物信息
     *
     * @param goodsId
     * @return
     */
    GoodsWarning selectGoodsWarningByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 根据货物id删除异常货物
     */
    void deleteGoodsWarningByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 获取异常货物快递单号
     *
     * @return
     */
    List<String> getGoodsWarningNumber();
}