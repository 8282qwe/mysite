package com.poscodx.mysite06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hello {

    @GetMapping("/index")
    public String index() {
        return "hello";
    }
}
