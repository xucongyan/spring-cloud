package com.spring.cloud.client.dao;


import com.spring.cloud.client.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserByUsername(String userName);
}
