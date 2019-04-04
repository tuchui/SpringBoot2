package com.mao.springdata.springdatatest.service;

import com.mao.springdata.springdatatest.bean.User;
import com.mao.springdata.springdatatest.repositry.UserCRUD;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private UserCRUD userCRUD;
    /*
    对增加 删除 保存 条件事务 @Transactional
     */
    @Transactional
    public User save(User user){
        return userCRUD.save(user);
    }
    @Transactional
    public void delete(Integer id){
        userCRUD.deleteById(id);
    }
    public Iterable<User> findAll(){
        return userCRUD.findAll();
    }
    public User getById(Integer id){
        Optional<User> op=userCRUD.findById(id);
        return op.get();
    }
    @Transactional
    /*
    更新后，持久化对象会自动更新数据库
     */
    public void update(User user){
        user.setAge(1000);
        user.setLoginname("jackcy");

    }
}
