package com.mao.exceptionTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeptController {
    @RequestMapping("/indexException")
    public String index(){
        return "exceptionIndex";
    }
    @RequestMapping("/add")
    public String add(String name)throws Exception{
        if(name==null)
            throw new NullPointerException("not nott null haha");
        return "success";

    }
}
