package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.PayGive;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface PayGiveMapper {

    int deleteByPrimaryKey(Integer payGiveId);


    int insert(PayGive record);


    PayGive selectByPrimaryKey(Integer payGiveId);


    List<PayGive> selectAll();


    int updateByPrimaryKey(PayGive record);

    /**
     * 获取工资发放列表
     *
     * @param map
     * @return
     */
    List<PayGive> selectPayGiveList(HashMap<Object, String> map);

    /**
     * 获取工资发放列表总行数
     *
     * @param map
     * @return
     */
    int selectCountPayGiveList(HashMap<Object, String> map);
}