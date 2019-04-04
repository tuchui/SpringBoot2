package com.mao.springdata.springjpaquery.bean;

import javax.persistence.*;
import javax.xml.ws.Service;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String name;
//班级与学生一对多
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Student.class,mappedBy = "clazz")
    private Set<Student> students=new HashSet<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Clazz(String name) {
        this.name = name;
    }
}
