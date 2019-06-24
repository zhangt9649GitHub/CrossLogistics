package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.Forwarding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ForwardingMapper {

    int deleteByPrimaryKey(Integer forwardingId);


    int insert(Forwarding record);


    Forwarding selectByPrimaryKey(Integer forwardingId);


    List<Forwarding> selectAll();


    int updateByPrimaryKey(Forwarding record);

    /**
     * 根据代运状态与app用户id获取转运列表
     *
     * @param state
     * @param appUserId
     * @return
     */
    List<Forwarding> selectPublishedForwardingList(@Param("state") String state, @Param("appUserId") int appUserId);

    /**
     * 根据代运状态与app用户id获取转运列表总行数
     *
     * @param state
     * @param appUserId
     * @return
     */
    int selectPublishedForwardingListCount(@Param("state") String state, @Param("appUserId") int appUserId);
}