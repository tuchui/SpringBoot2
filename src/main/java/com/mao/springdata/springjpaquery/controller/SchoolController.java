package com.mao.springdata.springjpaquery.controller;

import com.mao.springdata.springjpaquery.bean.Clazz;
import com.mao.springdata.springjpaquery.bean.Student;
import com.mao.springdata.springjpaquery.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/save")
    public String save(){
            Clazz cl1=new Clazz("firstClass");
            Clazz cl2=new Clazz("secondClass");
            List<Clazz> list=new ArrayList<>();
            list.add(cl1);
            list.add(cl2);
            schoolService.saveClazzAll(list);
        Student student=new Student("jack","xian",10,'男',cl1);
        Student student2=new Student("jack2","xian",20,'男',cl1);
        Student student3=new Student("jack3","xian",30,'男',cl2);
        List<Student> list2=new ArrayList<>();
        list2.add(student);
        list2.add(student2);
        list2.add(student3);
        schoolService.saveStudentAll(list2);
        return "保存学生成功";
    }
    /*
    查询某个班级所有学生姓名、年龄、性别
     */
    @RequestMapping("/getClazzStus")
    public List<Map<String,Object>> getClazzStu(String clazzName){
        return schoolService.getStusByClazzName(clazzName);
    }
     /*
    查询某个班级所有学生姓名、年龄
     */
     @RequestMapping("/findNameAndSexByClazzName")
     public List<Map<String,Object>> findNameAndSexByClazzName(String clazzName){
         return schoolService.findNameAndSexByClazzName(clazzName);
     }
     /*
     某个班级某种性别所有学生名字
      */
     @RequestMapping("/findNameByClazzNameAndSex")
     public List<String> findNameByClazzNameAndSex(String clazzName,Character sex){
         return schoolService.findNameByClazzNameAndSex(clazzName,sex);
     }
     /*
     查询某个学生属于哪个班级
      */
     @RequestMapping("/findClazzNameByStudentName")
     public String findClazzNameByStudentName(String stuName){
         return schoolService.findClazzNameByStuName(stuName);
     }
     /*
     删除某个学生对象
      */
     @RequestMapping("/delteStuByStuName")
     public String delteStuByStuName(String stuName){
         schoolService.deleteStuByName(stuName);
         return "delete sucess";
     }
}
