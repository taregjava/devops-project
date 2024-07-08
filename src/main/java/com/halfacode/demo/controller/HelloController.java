package com.halfacode.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/hello")
public class HelloController {

    @GetMapping
    public String hello(){

        return "test ci-cd-succees";
    }
}
