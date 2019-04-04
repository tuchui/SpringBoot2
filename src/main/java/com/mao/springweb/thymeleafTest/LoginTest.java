package com.mao.springweb.thymeleafTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginTest {
    @RequestMapping("/")
    public String index(Model moel){
        System.out.println("Login Test index execute..");
        return "index";
    }
    @PostMapping("login")
    public ModelAndView login(@RequestParam("loginName") String loginName, @RequestParam("password") String password
    ,ModelAndView mv){

        System.out.println("loginname: "+loginName+"  password:"+password);
        mv.setViewName("redirect:/main");
        return mv;
    }


}
