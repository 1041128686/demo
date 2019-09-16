package com.my.aspest;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

//    @Before("execution(public * com.my.controller.UserController.*(..))")
//    public void before(){
//        logger.info("登录中");
//        System.out.println("登录中");
//    }
//
//    @After("execution(public * com.my.controller.UserController.*(..))")
//    public void after(){
//        logger.info("登录成功");
//        System.out.println("登录成功");
//    }


}
