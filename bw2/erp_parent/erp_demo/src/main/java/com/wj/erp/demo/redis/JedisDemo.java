package com.wj.erp.demo.redis;

import redis.clients.jedis.Jedis;

public class JedisDemo {

	public static void main(String[] args) {
		
		add();
		get();
		del();
		get();
	}
	
	public static void add() {
		Jedis jedis = new Jedis();
		jedis.set("abc", "赵子龙");
	}
	
	public static void get() {
		Jedis jedis = new Jedis();
		System.out.println(String.format("abc=%s", jedis.get("abc")));
	}
	
	public static void del() {
		Jedis jedis = new Jedis();
		jedis.del("abc");
	}
}
