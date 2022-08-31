package com.workable.mapper;

import com.workable.model.Hate;
import com.workable.model.Movie;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface HateMapper {

    @Select("SELECT count(1) FROM HATES WHERE movieid= #{movieid}")
    Integer countHates(Integer movieid);

    @Insert("INSERT INTO HATES (movieid, userid) VALUES(#{movieid} ,#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "hate.hateId")
    Integer addHate(@Param("hate") Hate hate,Integer movieid, Integer userid);

    @Delete("DELETE FROM HATES WHERE userid = #{userid} and movieid= #{movieid}")
    Integer deleteHate(Integer movieid,Integer userid);
}

