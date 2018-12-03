package com.wj.erp.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wj.erp.dao.interfaces.IDepDao;

public class DepTest {

	@Test
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext_*.xml");
		IDepDao dao = (IDepDao) ac.getBean("depDao");
		
		System.out.println(dao.getList().size());
		
	}
}
