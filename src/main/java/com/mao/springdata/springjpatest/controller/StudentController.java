package com.mao.springdata.springjpatest.controller;

import com.mao.springdata.springjpatest.bean.Student;
import com.mao.springdata.springjpatest.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/save")
    public String save(){
        Student student=new Student();
        student.setAddress("xian");
        student.setName("jack");
        student.setAge('男');
        student.setAge(20);

        Student student2=new Student();
        student2.setAddress("xian");
        student2.setName("lik");
        student2.setSex('男');
        student2.setAge(25);

        Student student3=new Student();
        student3.setAddress("amercia");
        student3.setName("redhat");
        student3.setSex('女');
        student3.setAge(25);
        List<Student> list=new ArrayList<>();
        list.add(student);
        list.add(student2);
        list.add(student3);
        studentService.saveAll(list);
        return "保存student成功";
    }
    @ RequestMapping("/name")
    public Student getStudentByName(String name){
    return studentService.getStuByName(name);
    }
     @RequestMapping("/nameAndAddress")
    public Student getStudentByNameAndArress(String name,String address){
        return studentService.getStudentByNameAndAddr(name,address).get(0);

     }
     @RequestMapping("/likeName")
    public List<Student> getStudentsByNameLike(String name){
        return studentService.getStusByNameLike(name);
     }
}
