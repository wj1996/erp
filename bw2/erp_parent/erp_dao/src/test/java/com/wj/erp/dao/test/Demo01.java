package com.wj.erp.dao.test;

import org.junit.Test;

public class Demo01 {

	@Test
	public void tt() {
		/**
		 * Math.round 
		 */
		//小数点后一位小于5
		System.out.println(Math.round(11.46)); //11
		System.out.println(Math.round(-11.46)); //-11
		//小数点后一位大于5
		System.out.println(Math.round(11.56)); //12
		System.out.println(Math.round(-11.56)); //-12
		/**
		 * Math.ceil(找最接近这个数的double整数(并且要大于)，返回的是个double xx.0)
		 */
		System.out.println(Math.ceil(11.46)); //12.0
		System.out.println(Math.ceil(-11.46)); //-11.0
		System.out.println(Math.ceil(11.56)); //12.0
		System.out.println(Math.ceil(-11.56)); //-11.0
		/**
		 * Math.floor(最接近double整数（小于）)
		 */
		System.out.println(Math.floor(11.46)); //11.0
		System.out.println(Math.floor(-11.46)); //-12.0
		System.out.println(Math.floor(11.56)); //11.0
		System.out.println(Math.floor(-11.56)); //-12.0
	}
}
