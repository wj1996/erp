package com.wj.erp.biz.interfaces;

import java.util.List;

import com.wj.erp.entity.PageBean;

public interface IBaseBiz<T> {

	List<T> getList();
	
	List<T> getList(T t1,T t2,Object param);

	PageBean<T> getListByPage(T t1,T t2,Object param,Integer page, Integer rows);
	
	void add(T t);
	
	void delete(T t) throws Exception;
	
	T get(Long id);

	void update(T t);
	
	T get(String id);
}
