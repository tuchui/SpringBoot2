package com.mao.springdata.springjpaquery.service;

import com.mao.springdata.springjpaquery.bean.Clazz;
import com.mao.springdata.springjpaquery.bean.Student;
import com.mao.springdata.springjpaquery.repository.ClazzRepository;
import com.mao.springdata.springjpaquery.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClazzRepository clazzRepository;

    @Transactional
    public void saveClazzAll(List<Clazz> clazzs){
        clazzRepository.saveAll(clazzs);
    }
    @Transactional
    public void saveStudentAll(List<Student> studens){
        studentRepository.saveAll(studens);
    }
    public List<Map<String,Object>> getStusByClazzName(String clazzName){
        List<Student> students=studentRepository.findByClazz_name(clazzName);
        List<Map<String,Object>> res=new ArrayList<>();
        for (Student student:students){
            Map<String,Object> stu=new HashMap<>();
            stu.put("name",student.getName());
            stu.put("age",student.getAge());
            stu.put("sex",student.getSex());
              res.add(stu);
        }
        return res;
    }

    public List<Map<String,Object>> findNameAndSexByClazzName(String clazzName){
        return studentRepository.findNameAndSexClazzName(clazzName);
    }
    public List<String> findNameByClazzNameAndSex(String clazzName,char sex){
        return studentRepository.findNameByClazzNameAndSex(clazzName,sex);
    }
    public String findClazzNameByStuName(String stuName){
        return studentRepository.findClazzNameByStuName(stuName);
    }

    @Transactional
    public int deleteStuByName(String stuName){
        return studentRepository.deleteStuByStuName(stuName);
    }
}
