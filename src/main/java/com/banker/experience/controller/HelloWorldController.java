package com.banker.experience.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/api/")
public class HelloWorldController {
    
    @GetMapping("helloworld")
    public String helloWorld() {
        return "Hello World";
    }
}
