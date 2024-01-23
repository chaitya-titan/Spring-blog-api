package com.springpractice.blogapi.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    private String name = "Iron Man";

    @GetMapping("/")
    public String hello(){
        return "Hello, " + name;
    }
}
