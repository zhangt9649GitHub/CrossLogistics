package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.TruckProblemPieceMapper;
import com.siruiman.crosslogistics.model.TruckProblemPiece;
import com.siruiman.crosslogistics.service.TruckProblemPieceService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckProblemPieceServiceImpl implements TruckProblemPieceService {
    @Autowired
    private TruckProblemPieceMapper truckProblemPieceMapper;
    @Override
    public void updateStatusByGoodsId(Integer goodsId) {
        try {
            truckProblemPieceMapper.updateStatusByGoodsId(goodsId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public TruckProblemPiece selectByGoodsId(Integer goodsId) {
        try {
            return truckProblemPieceMapper.selectByGoodsId(goodsId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
