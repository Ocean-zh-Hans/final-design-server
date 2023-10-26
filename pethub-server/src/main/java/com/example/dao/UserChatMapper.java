package com.example.dao;

import com.example.pojo.UserChat;
import java.util.List;

public interface UserChatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_chat
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer chatId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_chat
     *
     * @mbg.generated
     */
    int insert(UserChat row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_chat
     *
     * @mbg.generated
     */
    UserChat selectByPrimaryKey(Integer chatId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_chat
     *
     * @mbg.generated
     */
    List<UserChat> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_chat
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserChat row);
}