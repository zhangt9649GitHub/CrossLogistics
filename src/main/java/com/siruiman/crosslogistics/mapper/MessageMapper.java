package com.siruiman.crosslogistics.mapper;

import com.siruiman.crosslogistics.model.AppMessage;
import com.siruiman.crosslogistics.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    int deleteByPrimaryKey(Integer messageId);


    int insert(Message record);


    Message selectByPrimaryKey(Integer messageId);


    List<Message> selectAll();


    int updateByPrimaryKey(Message record);

    /**
     * 根据app用户id获取消息中心列表
     *
     * @param appUserId
     * @return
     */
    List<AppMessage> selectMessageList(@Param("category") String category, @Param("appUserId") int appUserId);

    /**
     * 根据app用户id获取英文消息中心列表
     *
     * @param category
     * @param appUserId
     * @return
     */
    List<AppMessage> selectEnMessageList(@Param("category") String category, @Param("appUserId") int appUserId);

    /**
     * 根据app用户id获取消息中心列表总行数
     *
     * @param category
     * @param appUserId
     * @return
     */
    int selectCountMessageList(@Param("category") String category, @Param("appUserId") int appUserId);

    /**
     * 根据消息id获取消息详细内容
     *
     * @param messageId
     * @return
     */
    AppMessage selectMessageById(@Param("messageId") int messageId);

    /**
     * 修改消息状态
     *
     * @param appMessage
     */
    void updateZhMessage(AppMessage appMessage);

    /**
     * 根据消息id获取英文消息详细内容
     *
     * @param messageId
     * @return
     */
    AppMessage selectEnMessageById(@Param("messageId") int messageId);

    /**
     * 获取刚刚插入的id
     *
     * @return
     */
    int selectLastId();

    /**
     * 验重字段
     *
     * @param comment
     * @return
     */
    Message selectMessageComment(@Param("comment") String comment);

}