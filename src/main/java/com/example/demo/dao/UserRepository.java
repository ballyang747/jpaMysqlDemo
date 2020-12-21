package com.example.demo.dao;

import com.example.demo.bean.TestSp;
import com.example.demo.bean.UserEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<TestSp, String>{


  @Procedure(name="checkInputCarDetail")//和entity的jpa的存储过程名一致
  List<String> testProc(@Param("carModelName") Integer inParam1,@Param("timesPerDay") Integer inParam2 ,
                        @Param("toolManageNo") Integer inParam3);
}
