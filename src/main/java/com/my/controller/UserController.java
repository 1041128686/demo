package com.my.controller;


import com.my.pojo.User;
import com.my.repository.UserRepository;
import com.my.service.impl.UserService;
import com.my.util.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/sendsms")
    @ResponseBody
    public void sendsms(@RequestParam(name = "phone")String phone){
        System.out.println(phone);
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            code.append(r);
        }
        SendSmsUtil.send(phone, String.valueOf(code));
        redisTemplate.opsForValue().set(phone,String.valueOf(code));
    }


    @RequestMapping("/register")
    public ModelAndView register(User user,ModelAndView mv,String code,String phone){
        if (redisTemplate.opsForValue().get(phone)==null||!redisTemplate.opsForValue().get(phone).equals(code)){
            System.out.println(redisTemplate.opsForValue().get(phone));
            mv.addObject("exception","验证码错误或不存在");
            mv.setViewName("register");
            return mv;
        }
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        user.setPassword(encode);
        user.setRole("ROLE_USER");
        userService.save(user);
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/register-page")
    public ModelAndView regPage(){
        return new ModelAndView("register");
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
