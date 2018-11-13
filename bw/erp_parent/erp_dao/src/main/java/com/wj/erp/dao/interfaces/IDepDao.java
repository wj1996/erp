package com.wj.erp.dao.interfaces;

import java.util.List;

import com.wj.erp.entity.Dep;

public interface IDepDao {

	List<Dep> getList();
	
	List<Dep> getList(Dep dep,Dep dep2,Object param);

	Integer getCount(Dep dep,Dep dep2,Object param);

	List<Dep> getListByPage(Dep dep,Dep dep2,Object param,Integer page, Integer rows);
	
	void add(Dep dep);
	
	void delete(Dep dep);
	
	Dep getById(Dep dep);
}
