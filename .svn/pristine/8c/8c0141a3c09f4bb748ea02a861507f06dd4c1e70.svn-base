package com.siruiman.crosslogistics.mapper;


import com.siruiman.crosslogistics.model.GoodsPrintForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 张占伟
 * @date 2019/3/27 16:22
 */
@Mapper
public interface GoodsPrintFormMapper {
    /**
     *
     * @param form
     */
    void add(@Param("form") GoodsPrintForm form);

    /**
     * 添加有司机名字
     * @param form
     */
    void add2(@Param("form") GoodsPrintForm form);

    /**
     * 根据编号查询id
     * @param formNumber
     * @return id
     */
    int selectByNum(@Param("formNumber")String formNumber);
}
