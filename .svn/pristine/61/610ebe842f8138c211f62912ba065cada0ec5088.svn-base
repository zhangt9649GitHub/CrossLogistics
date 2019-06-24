package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.TruckProblemPiece;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TruckProblemPieceMapper {

    int deleteByPrimaryKey(@Param("appUserId") Integer appUserId, @Param("goodsId") Integer goodsId);


    int insert(TruckProblemPiece record);


    TruckProblemPiece selectByPrimaryKey(@Param("appUserId") Integer appUserId, @Param("goodsId") Integer goodsId);


    List<TruckProblemPiece> selectAll();


    int updateByPrimaryKey(TruckProblemPiece record);

    /**
     * 修改货物问题件到仓库的状态
     * @param goodsId
     */
    void updateStatusByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 根据货物id查询问题件记录
     * @param goodsId
     * @return
     */
    TruckProblemPiece selectByGoodsId(@Param("goodsId") Integer goodsId);
}