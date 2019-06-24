package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.FinanceMoneyFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface FinanceMoneyFlowMapper {

    int deleteByPrimaryKey(Integer financeMoneyId);


    int insert(FinanceMoneyFlow record);


    FinanceMoneyFlow selectByPrimaryKey(Integer financeMoneyId);


    List<FinanceMoneyFlow> selectAll();


    int updateByPrimaryKey(FinanceMoneyFlow record);

    /**
     * 获取财务流水记录列表
     *
     * @param map
     * @return
     */
    List<FinanceMoneyFlow> selectFinanceFlowList(HashMap<String, Object> map);

    /**
     * 获取财务流水记录列表总行数
     *
     * @param map
     * @return
     */
    int selectCountFinanceFlowList(HashMap<String, Object> map);
}