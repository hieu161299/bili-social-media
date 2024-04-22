package com.bilibili.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String homeController() {
        return "This is home controller!!!";
    }
    @GetMapping("/home")
    public String homeController1() {
        return "This is home controller1!!!";
    }
}
