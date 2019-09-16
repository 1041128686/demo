package com.my.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyExceptionHandler {

    public static final String ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e)throws Exception{
        System.out.println(e.toString());
        e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        String error = "服务器异常";
        mv.addObject("exception",error);
        mv.setViewName(ERROR_VIEW);
        return mv;
    }

}
