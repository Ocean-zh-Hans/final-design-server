package com.example.pojo;

import java.util.Date;

public class Review {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.review_id
     *
     * @mbg.generated
     */
    private Integer reviewId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.movie_id
     *
     * @mbg.generated
     */
    private Integer movieId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.user_rating
     *
     * @mbg.generated
     */
    private Integer userRating;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.updated_at
     *
     * @mbg.generated
     */
    private Date updatedAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.reviews_content
     *
     * @mbg.generated
     */
    private String reviewsContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.review_id
     *
     * @return the value of review.review_id
     *
     * @mbg.generated
     */
    public Integer getReviewId() {
        return reviewId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.review_id
     *
     * @param reviewId the value for review.review_id
     *
     * @mbg.generated
     */
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.user_id
     *
     * @return the value of review.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.user_id
     *
     * @param userId the value for review.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.movie_id
     *
     * @return the value of review.movie_id
     *
     * @mbg.generated
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.movie_id
     *
     * @param movieId the value for review.movie_id
     *
     * @mbg.generated
     */
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.user_rating
     *
     * @return the value of review.user_rating
     *
     * @mbg.generated
     */
    public Integer getUserRating() {
        return userRating;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.user_rating
     *
     * @param userRating the value for review.user_rating
     *
     * @mbg.generated
     */
    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.created_at
     *
     * @return the value of review.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.created_at
     *
     * @param createdAt the value for review.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.updated_at
     *
     * @return the value of review.updated_at
     *
     * @mbg.generated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.updated_at
     *
     * @param updatedAt the value for review.updated_at
     *
     * @mbg.generated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.reviews_content
     *
     * @return the value of review.reviews_content
     *
     * @mbg.generated
     */
    public String getReviewsContent() {
        return reviewsContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.reviews_content
     *
     * @param reviewsContent the value for review.reviews_content
     *
     * @mbg.generated
     */
    public void setReviewsContent(String reviewsContent) {
        this.reviewsContent = reviewsContent == null ? null : reviewsContent.trim();
    }
}