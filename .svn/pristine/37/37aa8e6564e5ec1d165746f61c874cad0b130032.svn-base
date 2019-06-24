package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.GoodsPrintFormMapper;
import com.siruiman.crosslogistics.model.GoodsPrintForm;
import com.siruiman.crosslogistics.service.GoodsPrintFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张占伟
 * @date 2019/3/27 16:22
 */
@Service
public class GoodsPrintFormServiceImpl implements GoodsPrintFormService {
    @Autowired
    private GoodsPrintFormMapper goodsPrintFormMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(GoodsPrintForm form) {
        goodsPrintFormMapper.add(form);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add2(GoodsPrintForm form) {
        goodsPrintFormMapper.add2(form);
    }

    @Override
    public int selectByNum(String formNumber) {
        return goodsPrintFormMapper.selectByNum(formNumber);
    }
}
