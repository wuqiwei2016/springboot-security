package com.htht.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2019/11/29.
 */
@RestController
public class TestController {


    @GetMapping("/hello1111")
    public String Hello(){
        return  "111";
    }
}
