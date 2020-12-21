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

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private SeletTest1 seletTest1;
	@Autowired
	private UserRepository userRepository;

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
		List<String> userEntity1s = userRepository.testProc(111, 111, 111);
		for (String rr :   userEntity1s ){

			System.out.println("结果"+rr);
		}
	}

}
