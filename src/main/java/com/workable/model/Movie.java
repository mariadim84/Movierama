package com.workable.model;
import java.sql.Date;
import java.util.List;

public class Movie {
    private Integer movieId;
    private String movieTitle;
    private String movieDescription;
    private Integer userId;
    private Date insertDate;

    private Integer likesCount;
    private Integer hatesCount;
    private String username;
    private List<User> likedbyUsers;

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId(){
        return userId;
    }

    public Date getInsertDate() { return insertDate; }

    public void setInsertDate(Date insertDate) { this.insertDate = insertDate;}

    public Integer getLikesCount() { return likesCount; }

    public void setLikesCount(Integer likesCount) { this.likesCount = likesCount; }

    public Integer getHatesCount() { return hatesCount; }

    public void setHatesCount(Integer hatesCount) { this.hatesCount = hatesCount; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public List<User> getLikedbyUsers() { return likedbyUsers; }

    public void setLikedbyUsers(List<User> likedbyUsers) { this.likedbyUsers = likedbyUsers; }

}
