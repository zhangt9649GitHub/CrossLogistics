package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.TruckProblemPiece;
import org.springframework.stereotype.Service;

@Service
public interface TruckProblemPieceService {
    /**
     * 修改货物问题件到仓库的状态
     * @param goodsId
     */
    void updateStatusByGoodsId(Integer goodsId);

    /**
     * 根据货物id查询问题件记录
     * @param goodsId
     * @return
     */
    TruckProblemPiece selectByGoodsId(Integer goodsId);
}
