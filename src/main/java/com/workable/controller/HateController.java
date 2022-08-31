package com.workable.controller;

import com.workable.model.Hate;
import com.workable.model.Movie;
import com.workable.model.User;
import com.workable.services.HateService;
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
public class HateController {

    private UserService userService;
    private HateService hateService;

    public HateController( HateService hateService, UserService userService) {
        this.hateService = hateService;
        this.userService=userService;
    }

    @GetMapping("/add_hate/{movieid}")
    public String saveHate(Authentication authentication, @PathVariable("movieid") Integer movieid ,Hate hate, Model model) {
        boolean result;
        User user = this.userService.getUser(authentication.getPrincipal().toString());
        result=this.hateService.addHate(hate,movieid, user.getUserId());
        if (result ) {
            return "redirect:/result?success";
        }
        else {
            return "redirect:/result?error";
        }
    }

    @GetMapping("/delete_hate/{movieid}")
    public String removeHate(Authentication authentication, @PathVariable("movieid") Integer movieid ,  Hate hate, Model model) {
        boolean result;
        User user = this.userService.getUser(authentication.getPrincipal().toString());
        result=this.hateService.deleteHate(movieid, user.getUserId());
        if (result ) {
            return "redirect:/result?success";
        }
        else {
            return "redirect:/result?error";
        }
    }
}

