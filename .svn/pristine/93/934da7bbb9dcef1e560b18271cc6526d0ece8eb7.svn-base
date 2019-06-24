package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.service.GoodsFromService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsFromServiceImpl implements GoodsFromService {

    @Autowired
    private GoodsFromMapper goodsFromMapper;
    @Autowired
    private GoodsFromAssociatedMapper goodsFromAssociatedMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private TaskTruckOrderMapper taskTruckOrderMapper;

    @Override
    public void updateGoodsFromPrint(int formId, int isPrint) {
        try {
            goodsFromMapper.updateGoodsFromPrint(formId, isPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public GoodsFrom selectGoodsFromPrint() {
        try {
            //查询打印的表单信息
            GoodsFrom goodsFrom = goodsFromMapper.selectGoodsFromPrint();
            if (goodsFrom != null && goodsFrom.getFromNumber() != null) {
                TaskTruckOrder taskTruckOrder = taskTruckOrderMapper.selectTruckOrderByOrderNumber(goodsFrom.getFromNumber());
                if (taskTruckOrder != null) {
                    goodsFrom.setType(2);
                } else {
                    goodsFrom.setType(1);
                }
            }
            if (goodsFrom != null && goodsFrom.getFormId() != null) {
                //根据表单id获取所有货物id
                List<GoodsFromAssociated> goodsFromAssociatedList = goodsFromAssociatedMapper.selectAllbyFromId(goodsFrom.getFormId());

                List<Integer> goodsIds = new ArrayList<>();
                if (goodsFromAssociatedList.size() > 0) {
                    //遍历货物id查询所有货物信息
                    for (GoodsFromAssociated goodsFromAssociated : goodsFromAssociatedList
                    ) {
                        if (goodsFromAssociated.getGoodsId() > 0) {
                            // GoodsFromInfo goodsFromInfo = goodsDetailsMapper.selectGoodsDetailByGoodsId(goodsFromAssociated.getGoodsId());
                            // goodsFromInfos.add(goodsFromInfo);
                            goodsIds.add(goodsFromAssociated.getGoodsId());
                        }

                    }

                }
                List<GoodsFromInfo> goodsFromInfos = new ArrayList<>();
                if (goodsIds.size() > 0) {
                    goodsFromInfos = goodsDetailsMapper.selectGoodsDetailByGoodsIds(goodsIds);
                    if (goodsFrom.getType() != null && goodsFrom.getType() == 2) {
                        if (goodsFromInfos.size() > 0) {
                            for (GoodsFromInfo goodsFromInfo : goodsFromInfos
                            ) {
                                if (goodsFromInfo.getIsArrivalPay() == 1) {
                                    goodsFromInfo.setItemValue(null);
                                }
                                if (goodsFromInfo.getDeliveryNumber().contains("-1")) {
                                    if (goodsFromInfo.getIsArrivalPay() == 2) {
                                        int index = goodsFromInfo.getDeliveryNumber().indexOf("-");
                                        String deliveryNumber = goodsFromInfo.getDeliveryNumber().substring(0, index);
                                        GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailsByDeliveryNumber(deliveryNumber);
                                        if (goodsDetails != null && goodsDetails.getItemValue() != null) {
                                            goodsFromInfo.setItemValue(goodsDetails.getItemValue());
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                goodsFrom.setGoodsFromInfoList(goodsFromInfos);

            }
            return goodsFrom;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoodsFrom> selectGoodsFromList(String fromNumber, String driverName) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("fromNumber", fromNumber);
            map.put("driverName", driverName);
            List<GoodsFrom> goodsFromList = goodsFromMapper.selectGoodsFromList(map);
            for (GoodsFrom goodsFrom : goodsFromList
            ) {
                TaskTruckOrder taskTruckOrder = taskTruckOrderMapper.selectTruckOrderByOrderNumber(goodsFrom.getFromNumber());
                if (taskTruckOrder != null) {
                    goodsFrom.setType(2);
                } else {
                    goodsFrom.setType(1);
                }
            }
            return goodsFromList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountGoodsFromList(String fromNumber, String driverName) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("fromNumber", fromNumber);
            map.put("driverName", driverName);
            return goodsFromMapper.selectCountGoodsFromList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<GoodsFromInfo> selectGoodsFromInfo(int formId) {
        try {
            //根据表单id获取所有货物id
            List<GoodsFromAssociated> goodsFromAssociatedList = goodsFromAssociatedMapper.selectAllbyFromId(formId);
            List<Integer> goodsIds = new ArrayList<>();
            if (goodsFromAssociatedList.size() > 0) {
                //遍历货物id查询所有货物信息
                for (GoodsFromAssociated goodsFromAssociated : goodsFromAssociatedList
                ) {
                    if (goodsFromAssociated.getGoodsId() > 0) {
                        // GoodsFromInfo goodsFromInfo = goodsDetailsMapper.selectGoodsDetailByGoodsId(goodsFromAssociated.getGoodsId());
                        // goodsFromInfos.add(goodsFromInfo);
                        goodsIds.add(goodsFromAssociated.getGoodsId());
                    }

                }

            }
            List<GoodsFromInfo> goodsFromInfos = new ArrayList<>();
            if (goodsIds.size() > 0) {
                goodsFromInfos = goodsDetailsMapper.selectGoodsDetailByGoodsIds(goodsIds);
                if (goodsFromInfos.size() > 0) {
                    for (GoodsFromInfo goodsFromInfo : goodsFromInfos
                    ) {
                        if (goodsFromInfo.getIsArrivalPay() == 1) {
                            goodsFromInfo.setItemValue(null);
                        }
                        if (goodsFromInfo.getDeliveryNumber().contains("-1")) {
                            if (goodsFromInfo.getIsArrivalPay() == 2) {
                                int index = goodsFromInfo.getDeliveryNumber().indexOf("-");
                                String deliveryNumber = goodsFromInfo.getDeliveryNumber().substring(0, index);
                                GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailsByDeliveryNumber(deliveryNumber);
                                if (goodsDetails != null && goodsDetails.getItemValue() != null) {
                                    goodsFromInfo.setItemValue(goodsDetails.getItemValue());
                                }
                            }
                        }
                    }
                }
            }
            return goodsFromInfos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCountGoodsFromInfo(int formId) {
        //根据表单id获取所有货物id
        List<GoodsFromAssociated> goodsFromAssociatedList = goodsFromAssociatedMapper.selectAllbyFromId(formId);
        int count = 0;
        if (goodsFromAssociatedList.size() > 0) {
            //遍历货物id查询所有货物信息
            for (GoodsFromAssociated goodsFromAssociated : goodsFromAssociatedList
            ) {
                if (goodsFromAssociated.getGoodsId() > 0) {
                    count++;
                }

            }
        }
        return count;
    }


}
