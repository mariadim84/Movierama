package com.workable.mapper;

import com.workable.model.Movie;
import com.workable.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Select("SELECT movieId FROM LIKES l , USERS u WHERE u.userid=l.userid and u.username=#{username}")
    List<Integer> likedMovieIds(String username);

    @Select("SELECT movieId FROM HATES h , USERS u WHERE u.userid=h.userid and u.username=#{username}")
    List<Integer> hatedMovieIds(String username);

    @Select("SELECT userid FROM USERS WHERE username = #{username}")
    User getUserId(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
