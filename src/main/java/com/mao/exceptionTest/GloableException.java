package com.mao.exceptionTest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GloableException {

    @ExceptionHandler
    @ResponseBody
    public Object errorHadnler(HttpServletRequest req, HttpServletResponse resp,Exception e)throws Exception{
        System.out.println("全局异常");
        Map<String,Object> map=new HashMap<>();
        map.put("code",100);
        map.put("msg",e.getMessage());
        map.put("url",req.getRequestURI().toString());
        return map;
    }
}
