package com.my.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class MyErrorController implements ErrorController {
//
//    private final static String ERROR_PAGE = "/error";
//
//    @Override
//    public String getErrorPath() {
//        return ERROR_PAGE;
//    }
//
//    @RequestMapping(ERROR_PAGE)
//    public ModelAndView error(ModelAndView mv){
//        mv.addObject("exception","404异常");
//        mv.setViewName("error");
//        return mv;
//    }
//
//    @RequestMapping("/403")
//    public ModelAndView error4(ModelAndView mv){
//        mv.addObject("exception","403权限不足");
//        mv.setViewName("error");
//        return mv;
//    }
//
//}
