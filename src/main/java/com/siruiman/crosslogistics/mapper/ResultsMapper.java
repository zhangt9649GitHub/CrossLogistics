package com.siruiman.crosslogistics.mapper;


import com.siruiman.crosslogistics.model.Results;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResultsMapper {

    void insert(Results results);

    List<Results> select(@Param("a") int a,@Param("b") int b);
}
