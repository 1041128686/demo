package com.my.controller;


import com.my.pojo.User;
import com.my.repository.UserRepository;
import com.my.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/register")
    public ModelAndView register(User user){
        userService.save(user);
        return new ModelAndView("home");
    }



    @RequestMapping("/login-page")
    public ModelAndView login(User user, ModelAndView mv, HttpServletRequest request){
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/login-error")
    public ModelAndView loginError(ModelAndView mv){
        mv.addObject("exception","密码或者帐号错误");
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/login?logout")
    public  ModelAndView logout(ModelAndView mv){
        mv.setViewName("login");
        return mv;
    }

}
