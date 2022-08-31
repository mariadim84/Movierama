package com.workable.services;

import com.workable.mapper.LikeMapper;
import com.workable.mapper.MovieMapper;
import com.workable.model.Like;
import com.workable.model.Movie;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeService {

    private LikeMapper likeMapper;

    public LikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    public boolean addLike(Like like, Integer movieId, Integer userId) {
        Integer result;
        Like newLike = new Like();

        result=likeMapper.addLike(newLike,movieId, userId);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public boolean deleteLike(Integer movieId, Integer userId) {
        Integer result;
        result=likeMapper.deleteLike(movieId, userId);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public Integer getLikes(Integer movieId) {
        return likeMapper.countLikes(movieId);
    }

}