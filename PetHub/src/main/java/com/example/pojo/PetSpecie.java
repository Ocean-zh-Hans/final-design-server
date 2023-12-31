package com.example.pojo;

import java.util.Date;

public class PetSpecie {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_specie.specie_id
     *
     * @mbg.generated
     */
    private Integer specieId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_specie.specie_name
     *
     * @mbg.generated
     */
    private String specieName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_specie.specie_male
     *
     * @mbg.generated
     */
    private String specieMale;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_specie.specie_female
     *
     * @mbg.generated
     */
    private String specieFemale;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_specie.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_specie.updated_at
     *
     * @mbg.generated
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet_specie.specie_id
     *
     * @return the value of pet_specie.specie_id
     *
     * @mbg.generated
     */
    public Integer getSpecieId() {
        return specieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet_specie.specie_id
     *
     * @param specieId the value for pet_specie.specie_id
     *
     * @mbg.generated
     */
    public void setSpecieId(Integer specieId) {
        this.specieId = specieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet_specie.specie_name
     *
     * @return the value of pet_specie.specie_name
     *
     * @mbg.generated
     */
    public String getSpecieName() {
        return specieName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet_specie.specie_name
     *
     * @param specieName the value for pet_specie.specie_name
     *
     * @mbg.generated
     */
    public void setSpecieName(String specieName) {
        this.specieName = specieName == null ? null : specieName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet_specie.specie_male
     *
     * @return the value of pet_specie.specie_male
     *
     * @mbg.generated
     */
    public String getSpecieMale() {
        return specieMale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet_specie.specie_male
     *
     * @param specieMale the value for pet_specie.specie_male
     *
     * @mbg.generated
     */
    public void setSpecieMale(String specieMale) {
        this.specieMale = specieMale == null ? null : specieMale.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet_specie.specie_female
     *
     * @return the value of pet_specie.specie_female
     *
     * @mbg.generated
     */
    public String getSpecieFemale() {
        return specieFemale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet_specie.specie_female
     *
     * @param specieFemale the value for pet_specie.specie_female
     *
     * @mbg.generated
     */
    public void setSpecieFemale(String specieFemale) {
        this.specieFemale = specieFemale == null ? null : specieFemale.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet_specie.created_at
     *
     * @return the value of pet_specie.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet_specie.created_at
     *
     * @param createdAt the value for pet_specie.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pet_specie.updated_at
     *
     * @return the value of pet_specie.updated_at
     *
     * @mbg.generated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pet_specie.updated_at
     *
     * @param updatedAt the value for pet_specie.updated_at
     *
     * @mbg.generated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}