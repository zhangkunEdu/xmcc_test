package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController
 *
 * 里面包含了 @Controller
 *           @ResponseBody   就不用在每个方法上写ResponseBody
 */

@RestController
@RequestMapping("/test")
public class testController {


    @GetMapping
    @PostMapping
    @RequestMapping("/hello")
    public String  hello(){
       return "Hello SpringBoot";
    }
}