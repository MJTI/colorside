package com.colorsideweb.colorsidefirstdev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class demo {


    @GetMapping("/dem")
    public String demo(){
        return "demo";
    }
}
