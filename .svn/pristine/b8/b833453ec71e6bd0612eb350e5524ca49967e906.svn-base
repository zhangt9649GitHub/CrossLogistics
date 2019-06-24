package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.ResultsMapper;
import com.siruiman.crosslogistics.model.Results;
import com.siruiman.crosslogistics.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张占伟
 * @date 2018/12/21
 */
@Transactional
@Service
public class ResultsServiceImpl implements ResultsService {

    @Autowired
    private ResultsMapper resultsMapper;
    @Override
    public void addResults(Results results) {
        resultsMapper.insert(results);
    }

    @Override
    public List<Results> select(int a, int b) {
        return resultsMapper.select(a,b);
    }
}
