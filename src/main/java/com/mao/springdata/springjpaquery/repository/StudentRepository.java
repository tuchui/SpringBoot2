package com.mao.springdata.springjpaquery.repository;


import com.mao.springdata.springjpaquery.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByClazz_name(String clazzName);
    /*
    @Query写法
    ?! 表示第一个参数
     */
    @Query("select s from  Student s where  s.clazz.name=?1")
    List<Student> findClazzNameByClazzAndNam(String clazzName);

    @Query("select new Map(s.name as name  , s.sex as sex)  from Student s where s.clazz.name=?1")
    List<Map<String,Object>> findNameAndSexClazzName(String clazzName);

    @Query("select s.name from Student s where s.clazz.name =:clazzName and s.sex= :sex")
    List<String> findNameByClazzNameAndSex(@Param("clazzName")String clazzName, @Param("sex") char sex);

    /**
     *  使用@Query注解的形式，查询某个学生属于哪个班级
     * @param stuName
     * @return
     */
    @Query("select c.name from Clazz c inner join c.students s "
            + "where s.name = ?1 ")
    String findClazzNameByStuName(String stuName);
    @Modifying
    @Query("delete  from Student s where s.name=?1")
    int deleteStuByStuName(String stuName);

}
