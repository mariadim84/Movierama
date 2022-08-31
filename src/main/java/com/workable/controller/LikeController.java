package com.workable.controller;

import com.workable.model.Like;
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
public class LikeController {

    private UserService userService;
    private LikeService likeService;

    public LikeController( LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService=userService;
    }

    @GetMapping("/add_like/{movieid}")
    public String saveLike(Authentication authentication, @PathVariable("movieid") Integer movieid ,Like like, Model model) {
        boolean result;
        User user = this.userService.getUser(authentication.getPrincipal().toString());
        result=this.likeService.addLike(like,movieid, user.getUserId());
        if (result ) {
            return "redirect:/result?success";
        }
        else {
            return "redirect:/result?error";
        }
    }

    @GetMapping("/delete_like/{movieid}")
    public String removeLike(Authentication authentication, @PathVariable("movieid") Integer movieid ,  Like like, Model model) {
        boolean result;
        User user = this.userService.getUser(authentication.getPrincipal().toString());
        result=this.likeService.deleteLike(movieid, user.getUserId());
        if (result ) {
            return "redirect:/result?success";
        }
        else {
            return "redirect:/result?error";
        }
    }
}

