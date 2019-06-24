package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.MessagePush;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessagePushMapper {

    int deleteByPrimaryKey(Integer pushId);


    int insert(MessagePush record);


    MessagePush selectByPrimaryKey(Integer pushId);


    List<MessagePush> selectAll();


    int updateByPrimaryKey(MessagePush record);

    /**
     * 查询今日最早需要再次推送的消息
     *
     * @return
     */
    MessagePush selectMessagePushByTime();

    /**
     * 修改是否需要再次推送为否
     */
    void updateById(int messagePushId);
}