package com.mao.springdata.springdatatest.bean;

import javax.persistence.*;
import java.io.Serializable;
/*
创建持久化类
 */
@Entity
@Table(name="tb_user")
public class User implements Serializable {

    /*
        使用@Id指定主键
        GeneratedValue指定主键的生产策略
         */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String loginname;
    private char sex;
    private int age;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
