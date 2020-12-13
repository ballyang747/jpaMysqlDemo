package com.example.demo.dao;

import com.example.demo.bean.UserEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository
public interface UserRepository1 extends JpaRepository<UserEntity1, Integer>{

}
