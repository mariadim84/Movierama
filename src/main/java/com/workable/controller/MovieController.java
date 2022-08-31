package com.workable.controller;

import com.workable.model.Movie;
import com.workable.model.User;
import com.workable.services.LikeService;
import com.workable.services.MovieService;
import com.workable.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Controller
public class MovieController {

    private MovieService movieService;
    private UserService userService;
    private LikeService likeService;


    public MovieController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService=userService;
        this.likeService=likeService;
    }


    @PostMapping("/add_movie")
    public String saveMovie(Authentication authentication, Movie movie, Model model) {
        boolean result;
        User user = this.userService.getUser(authentication.getPrincipal().toString());
        Timestamp insertDate = Timestamp.valueOf(LocalDateTime.now());

        if(movie.getMovieId() ==null)
        {
                result=this.movieService.addMovie(movie, user.getUserId(),insertDate);
        }
        else
        {
            result=this.movieService.updateMovie(movie, user.getUserId());
        }

        if (result ) {
            return "redirect:/result?success";
        }
        else {
            return "redirect:/result?error";
        }

    }

    @GetMapping("/delete_movie/{id}")
    public String removeMovie(Authentication authentication, @PathVariable("id") Integer id,Movie movie, Model model) {
        boolean result;
        System.out.println(id);
        User user = this.userService.getUser(authentication.getPrincipal().toString());
        result=this.movieService.deleteMovie(id, user.getUserId());

        if (result ) {
            return "redirect:/result?success";
        }
        else {
            return "redirect:/result?error";
        }
    }

}

