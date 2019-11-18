package com.my.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {


    @RequestMapping("/mytest")
    public String myTest(HttpServletResponse response){
        response.setHeader("Authorization","tokenTest");
            return "Test";
    }

}
