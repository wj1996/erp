package com.wj.erp.biz.interfaces;

import java.util.List;

import com.wj.erp.entity.Dep;
import com.wj.erp.entity.PageBean;

/**
 * 部门业务接口
 * @author 24253
 *
 */
public interface IDepBiz {

	List<Dep> getList();
	
	List<Dep> getList(Dep dep,Dep dep2,Object param);

	PageBean<Dep> getListByPage(Dep dep1,Dep dep2,Object param,Integer page, Integer rows);
	
	void add(Dep dep);
	
	void delete(Dep dep) throws Exception;
	
	Dep get(Dep dep);

	void update(Dep dep);
}
