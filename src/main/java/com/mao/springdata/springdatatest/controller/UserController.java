package com.mao.springdata.springdatatest.controller;

import com.mao.springdata.springdatatest.bean.User;
import com.mao.springdata.springdatatest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/save")
    public String save(User user){
        User model=new User();
        model.setLoginname("lucy");
        model.setAge(10);
        model.setSex('女');
        model.setUsername("lucy ann");
        userService.save(model);
        return  "save success";
    }
    @RequestMapping("/delete")
    public String delete(){
        userService.delete(1);
        return "delte sucees";
    }
    @RequestMapping("/getAll")
    public Iterable<User> getAll(){
        return  userService.findAll();
    }
    @RequestMapping("/update")
    public String update(){
        User user=userService.getById(1);
        userService.update(user);
        return "update suceess";
    }
}
