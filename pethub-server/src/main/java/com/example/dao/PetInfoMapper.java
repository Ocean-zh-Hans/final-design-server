package com.example.dao;

import com.example.pojo.PetInfo;
import java.util.List;

public interface PetInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer petId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_info
     *
     * @mbg.generated
     */
    int insert(PetInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_info
     *
     * @mbg.generated
     */
    PetInfo selectByPrimaryKey(Integer petId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_info
     *
     * @mbg.generated
     */
    List<PetInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PetInfo row);
}