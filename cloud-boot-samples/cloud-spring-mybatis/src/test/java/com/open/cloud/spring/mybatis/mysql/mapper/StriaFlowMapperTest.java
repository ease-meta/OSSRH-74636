package com.open.cloud.spring.mybatis.mysql.mapper;

import com.open.cloud.spring.mybatis.SpringMybatis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringMybatis.class})// 指定启动类
public class StriaFlowMapperTest {

	@Test
	public void testOne(){
		System.out.println("1");
	}
}