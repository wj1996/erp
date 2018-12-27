package com.wj.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wj.cxfserver.IWeatherService;

public class CXFTest {

	@Test
	public void ff() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext_cxf.xml");
		IWeatherService bean = (IWeatherService) ac.getBean("weatherClient");
		System.out.println(bean.info("±±¾©"));
		System.out.println(bean.info("ºþÄÏ"));
	}
}
