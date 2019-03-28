package com.mao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HellloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hekko";
    }
}
