package com.workable.services;

import com.workable.mapper.HateMapper;
import com.workable.mapper.MovieMapper;
import com.workable.model.Hate;
import com.workable.model.Movie;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HateService {

    private HateMapper hateMapper;

    public HateService(HateMapper hateMapper) {
        this.hateMapper = hateMapper;
    }

    public boolean addHate(Hate hate, Integer movieId, Integer userId) {
        Integer result;
        Hate newHate = new Hate();

        result=hateMapper.addHate(newHate,movieId, userId);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public boolean deleteHate(Integer movieId, Integer userId) {
        Integer result;
        result=hateMapper.deleteHate(movieId, userId);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public Integer getHates(Integer movieId) {
        return hateMapper.countHates(movieId);
    }

}