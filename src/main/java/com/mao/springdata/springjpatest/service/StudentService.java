package com.mao.springdata.springjpatest.service;

import com.mao.springdata.springjpatest.bean.Student;
import com.mao.springdata.springjpatest.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentRepository studentRepository;

    @Transactional
    public void saveAll(List<Student> students){
        studentRepository.saveAll(students);
    }
    public Student getStuByName(String name){
        return studentRepository.findByName(name);
    }
    public List<Student> getStudentByNameAndAddr(String name,String address){
        return studentRepository.findByNameAndAddress(name,address);
    }
    public List<Student> getStusByNameLike(String name){
        return studentRepository.findByNameLike("%"+name+"%");
    }
}

