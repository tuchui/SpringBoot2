package com.mao.springdata.springdatatest.repositry;

import com.mao.springdata.springdatatest.bean.User;
import org.springframework.data.repository.CrudRepository;
//定义数据访问层接口，数据访问层无需提高实现
public interface UserCRUD extends CrudRepository<User,Integer> {

}
