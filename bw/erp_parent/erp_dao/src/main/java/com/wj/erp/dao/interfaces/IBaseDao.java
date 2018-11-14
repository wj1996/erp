package com.wj.erp.dao.interfaces;

import java.util.List;


public interface IBaseDao<T> {

	List<T> getList();
	
	List<T> getList(T t,T t2,Object param);

	Integer getCount(T t,T t2,Object param);

	List<T> getListByPage(T t,T T,Object param,Integer page, Integer rows);
	
	void add(T t);
	
	void delete(T t);
	
	T getById(Long id);

	void update(T t);
}
