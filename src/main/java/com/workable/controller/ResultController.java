package com.workable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping()
    public String loginView() {
        return "result";
    }

}