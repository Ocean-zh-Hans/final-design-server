package com.example.dao;

import com.example.pojo.PetKnowledge;
import java.util.List;

public interface PetKnowledgeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_knowledge
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer knoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_knowledge
     *
     * @mbg.generated
     */
    int insert(PetKnowledge row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_knowledge
     *
     * @mbg.generated
     */
    PetKnowledge selectByPrimaryKey(Integer knoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_knowledge
     *
     * @mbg.generated
     */
    List<PetKnowledge> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pet_knowledge
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PetKnowledge row);
}