package com.my.controller;

import com.my.pojo.EsArticle;
import com.my.repository.ArticleRepository;
import com.my.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
public class MainController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value ="/")
    public ModelAndView index(){
        return new ModelAndView("home");
    }

    @RequestMapping("/test")
    public ModelAndView test(ModelAndView mv ,HttpServletRequest request ){
        return mv;
    }

    @RequestMapping("/home")
    public  ModelAndView main(){
        return new ModelAndView("home");
    }

    @RequestMapping("/editor")
    public ModelAndView editor(ModelAndView mv){
        mv.setViewName("editor");

        mv.addObject("article",new EsArticle());

        return mv;
    }



}
