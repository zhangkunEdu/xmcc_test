package com.xmcc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class testController {


   //Logger logger = LoggerFactory.getLogger(testController.class);

   @RequestMapping("/hello")
   @GetMapping
   @PostMapping
   public String hello(){
      //logger.info("hello logback info");
      log.info("info->{}","hello log slf4j!");
     return "何永欢";
   }
}