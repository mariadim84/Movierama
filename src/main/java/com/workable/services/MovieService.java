package com.workable.services;

import com.workable.mapper.MovieMapper;
import com.workable.model.Movie;
import com.workable.model.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovieService {

    private MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }


    public boolean addMovie(Movie movie, Integer userId, Timestamp insertDate) {
        Integer result;
        Movie newMovie = new Movie();
        newMovie.setMovieDescription(movie.getMovieDescription());
        newMovie.setMovieTitle(movie.getMovieTitle());

        result=movieMapper.addMovie(newMovie, userId,insertDate);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public boolean deleteMovie(Integer movieId, Integer userId) {
        Integer result;
        result=movieMapper.deleteMovie(movieId, userId);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public boolean updateMovie(Movie movie, Integer userId) {
        Integer result;
        result=movieMapper.updateMovie(movie, userId);
        if (result > 0) {
            return true;
        }
        else {return false;}
    }

    public List<Movie> getMovies() {
               return movieMapper.getAllMovies();
    }

    public List<Movie> filterMovies(Integer userId) {
        return movieMapper.filterMovies(userId);
    }

}

