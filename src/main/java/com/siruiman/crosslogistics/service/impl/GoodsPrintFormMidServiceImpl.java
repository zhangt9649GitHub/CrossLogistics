package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.GoodsPrintFormMidMapper;
import com.siruiman.crosslogistics.model.GoodsPrintFormMid;
import com.siruiman.crosslogistics.service.GoodsPrintFormMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张占伟
 * @date 2019/3/27 16:44
 */
@Service
public class GoodsPrintFormMidServiceImpl implements GoodsPrintFormMidService {

    @Autowired
    private GoodsPrintFormMidMapper goodsPrintFormMidMapper;

    @Override@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(GoodsPrintFormMid goodsPrintFormMid) {
        goodsPrintFormMidMapper.add(goodsPrintFormMid);
    }
}
