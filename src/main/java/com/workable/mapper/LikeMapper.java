package com.workable.mapper;

import com.workable.model.Like;
import com.workable.model.Movie;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface LikeMapper {

    @Select("SELECT count(1) FROM LIKES WHERE movieid= #{movieid}")
    Integer countLikes(Integer movieid);

    @Insert("INSERT INTO LIKES (movieid, userid) VALUES(#{movieid} ,#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "like.likeId")
    Integer addLike(@Param("like") Like like,Integer movieid, Integer userid);

    @Delete("DELETE FROM LIKES WHERE userid = #{userid} and movieid= #{movieid}")
    Integer deleteLike(Integer movieid,Integer userid);
}

