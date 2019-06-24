package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.GoodsFrom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface GoodsFromMapper {

    int deleteByPrimaryKey(Integer formId);


    int insert(GoodsFrom record);


    GoodsFrom selectByPrimaryKey(Integer formId);


    List<GoodsFrom> selectAll();


    int updateByPrimaryKey(GoodsFrom record);

    /**
     * 修改货物表单打印状态
     *
     * @param formId
     * @param isPrint
     */
    void updateGoodsFromPrint(@Param("formId") int formId, @Param("isPrint") int isPrint);

    /**
     * 获取货物列表打印信息
     *
     * @return
     */
    GoodsFrom selectGoodsFromPrint();

    /**
     * 获取货物表单列表
     *
     * @param map
     * @return
     */
    List<GoodsFrom> selectGoodsFromList(HashMap<String, Object> map);

    /**
     * 获取货物表单总条数
     *
     * @param map
     * @return
     */
    int selectCountGoodsFromList(HashMap<String, Object> map);
}