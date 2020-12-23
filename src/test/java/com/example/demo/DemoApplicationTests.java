package com.example.demo;

import com.example.demo.bean.Test1Bean;
import com.example.demo.bean.UserEntity1;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserRepository1;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private SeletTest1 seletTest1;
	@Autowired
	private UserRepository userRepository;
	@PersistenceContext
	private EntityManager entityManager;
	@Test
	void contextLoads() throws Exception {
		Test1Bean test1Bean = new Test1Bean();
		 test1Bean.setContent("111");

		String[] strs = new String[]{};
	     List<Test1Bean> restr = seletTest1.selectResultByNative (test1Bean,"dddd","",strs ,"",Test1Bean.class);
	     for (Test1Bean rr :   restr ){

			 System.out.println("结果"+rr);
		}
	}

	@Test
	void Tesdt2() throws Exception {
//  List<Object> pPlanCheck(@Param("carModelName") Integer aa, @Param("timesPerDay") Integer bb, @Param("toolManageNo") Integer cc, @Param("name") Integer dd);
		StoredProcedureQuery checkInputCarDetail = entityManager.createNamedStoredProcedureQuery("testaaa");
		checkInputCarDetail.setParameter("t11","111");
		checkInputCarDetail.setParameter("t22","111");
		//checkInputCarDetail.setParameter("toolManageNo",111);

		checkInputCarDetail.execute();
		List resultList = checkInputCarDetail.getResultList();
		System.out.println(resultList.get(0));

		//String res1 = (String) checkInputCarDetail.getOutputParameterValue("nameresult");
		//System.out.println(res1);
	}

}
