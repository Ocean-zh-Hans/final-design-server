package com.example.dao;

import com.example.pojo.PetVariety;
import java.util.List;

public interface PetVarietyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_variety
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer varietyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_variety
     *
     * @mbg.generated
     */
    int insert(PetVariety row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_variety
     *
     * @mbg.generated
     */
    PetVariety selectByPrimaryKey(Integer varietyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_variety
     *
     * @mbg.generated
     */
    List<PetVariety> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_variety
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PetVariety row);
}