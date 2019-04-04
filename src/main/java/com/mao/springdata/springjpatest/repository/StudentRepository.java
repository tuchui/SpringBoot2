package com.mao.springdata.springjpatest.repository;

import com.mao.springdata.springjpatest.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    /*
    通过学生姓名来查询对学生对象
    相当于 ： select a from Student  s where s.name=?1
     */
    Student findByName(String name);
    /*
     通过学生姓名和地址来查询对学生对象
    相当于 ： select a from Student  s where s.name=?1 and s.address=?2
     */
    List<Student> findByNameAndAddress(String name,String address);
      /*
     通过学生姓名模糊查询学生信息
    相当于 ： select a from Student  s where s.name like ?1
     */
      List<Student> findByNameLike(String name);
}
