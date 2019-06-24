package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.WxpayGoodsMapper;
import com.siruiman.crosslogistics.model.WxpayGoods;
import com.siruiman.crosslogistics.service.WxpayGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxpayGoodsServiceImpl implements WxpayGoodsService {

    @Autowired
    private WxpayGoodsMapper wxpayGoodsMapper;

    @Override
    public WxpayGoods selectDeliveryNumber(String deliveryNumber) {

        return wxpayGoodsMapper.selectDeliveryNumber(deliveryNumber);

    }

    @Override
    public void update(String sysDeliveryNumber, String dealtime,int wxpayGdNumberId) {
        wxpayGoodsMapper.update(sysDeliveryNumber,dealtime,wxpayGdNumberId);

    }

    @Override
    public WxpayGoods selectdeliveryNumberBysysDeliveryNumber(String sysDeliveryNumber) {
        return wxpayGoodsMapper.selectdeliveryNumberBysysDeliveryNumber(sysDeliveryNumber);
    }

    @Override
    public void updateIsPayState(int wxpayGdNumberId) {
        wxpayGoodsMapper.updateIsPayState(wxpayGdNumberId);
    }

    @Override
    public void insert(WxpayGoods wxpayGoods) {
        wxpayGoodsMapper.insert(wxpayGoods);
    }


}
