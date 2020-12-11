package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() throws Exception {
		Test1Bean test1Bean = new Test1Bean();
		test1Bean.setContent("1111");
		test1Bean.setCreateDate(new Date());
		String[] strs = new String[]{};
		List<Test1Bean> restr = SeletTest1.selectResultByNative (test1Bean,"dddd","",strs ,"",Test1Bean.class);
	}

}
