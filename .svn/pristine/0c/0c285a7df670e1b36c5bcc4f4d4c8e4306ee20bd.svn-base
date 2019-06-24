package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.GoodsFromAssociated;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsFromAssociatedMapper {

    int deleteByPrimaryKey(Integer gfId);


    int insert(GoodsFromAssociated record);


    GoodsFromAssociated selectByPrimaryKey(Integer gfId);


    List<GoodsFromAssociated> selectAll();


    int updateByPrimaryKey(GoodsFromAssociated record);

    /**
     * 根据表单id货物货物表单关联表中所有的货物id
     *
     * @param fromId
     * @return
     */
    List<GoodsFromAssociated> selectAllbyFromId(int fromId);
}