package com.workable.mapper;

import com.workable.model.Movie;
import com.workable.model.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface MovieMapper {

    @Select("SELECT movieId,movieTitle,movieDescription,m.userId,insertDate, (select count(1) from LIKES l WHERE l.movieId=m.movieId) as likesCount,(select count(1) from HATES l WHERE l.movieId=m.movieId) as hatesCount ,u.username  FROM MOVIES m ,USERS u WHERE m.userId=u.userId")
    List<Movie> getAllMovies();

    @Select("SELECT movieId,movieTitle,movieDescription,m.userId,insertDate, (select count(1) from LIKES l WHERE l.movieId=m.movieId) as likesCount,(select count(1) from HATES l WHERE l.movieId=m.movieId) as hatesCount ,u.username  FROM MOVIES m ,USERS u WHERE m.userId=u.userId and m.userId =#{selecteduserid}")
    List<Movie> filterMovies(Integer selecteduserid);

    @Insert("INSERT INTO MOVIES (movietitle, moviedescription, userid, insertDate) VALUES(#{movie.movieTitle}, #{movie.movieDescription},#{userid},#{insertDate,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "movie.movieId")
    Integer addMovie(@Param("movie") Movie movie, Integer userid, Timestamp insertDate);

    @Delete("DELETE FROM MOVIES WHERE userid = #{userid} and movieid= #{movieid}")
    Integer deleteMovie(Integer movieid,Integer userid);

    @Update("UPDATE MOVIES SET movietitle =#{movie.movieTitle}, moviedescription = #{movie.movieDescription} WHERE userid = #{userid} and movieid= #{movie.movieId}")
    Integer updateMovie(Movie movie,Integer userid);

}

