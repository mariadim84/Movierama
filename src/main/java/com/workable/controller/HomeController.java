package com.workable.controller;

import com.workable.model.Movie;
import com.workable.model.User;
import com.workable.services.LikeService;
import com.workable.services.MovieService;
import com.workable.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private MovieService movieService;
    private UserService userService;
    private LikeService likeService;


    public HomeController( MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService=userService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model) {
        //System.out.println(authentication.getPrincipal().toString());
        if(authentication!=null) {
            User user = this.userService.getUser(authentication.getPrincipal().toString());
            String username = user.getUsername();
            model.addAttribute("userid", user.getUserId());
            model.addAttribute("likedmovies", this.userService.likedMovies(username));
            model.addAttribute("hatedmovies", this.userService.hatedMovies(username));


        }
       // model.addAttribute("movies", this.movieService.getMovies(user.getUserId()));
        model.addAttribute("movies", this.movieService.getMovies());
        return "home";
    }

    @GetMapping("/filter_movies/{id}")
    public String filterMovies(Authentication authentication, @PathVariable("id") Integer id, Movie movie, Model model) {

        User user = this.userService.getUser(authentication.getPrincipal().toString());
        model.addAttribute("movies", this.movieService.filterMovies(user.getUserId()));
        return "home";
    }
}

