package com.example.demo.dao;

import com.example.demo.bean.UserEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository1 extends JpaRepository<UserEntity1, Integer>{


  @Query(value = " SET NOCOUNT ON   exec  checkInputCarDetail  @carModelName=?1,@timesPerDay=?2,@toolManageNo=?3,@name=?4", nativeQuery = true)
  List<Object> pPlanCheck(@Param("carModelName") Integer aa, @Param("timesPerDay") Integer bb, @Param("toolManageNo") Integer cc, @Param("name") Integer dd);
}
