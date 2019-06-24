package com.siruiman.crosslogistics.service;

import com.siruiman.crosslogistics.model.WxpayGoods;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface WxpayGoodsService {

    WxpayGoods selectDeliveryNumber(String deliveryNumber);

    void update(String sysDeliveryNumber, String dealtime,int wxpayGdNumberId);

    WxpayGoods selectdeliveryNumberBysysDeliveryNumber(String sysDeliveryNumber);

    void updateIsPayState(int wxpayGdNumberId);

    void insert(WxpayGoods wxpayGoods);

}
