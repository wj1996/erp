package com.wj.erp.biz.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IDepBiz;
import com.wj.erp.dao.interfaces.IDepDao;
import com.wj.erp.entity.Dep;
import com.wj.erp.entity.PageBean;
@Transactional
public class DepBizImpl implements IDepBiz{
	
	
	private IDepDao depDao;
	
	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
	}

	@Override
	public List<Dep> getList() {
		return depDao.getList();
	}

	@Override
	public List<Dep> getList(Dep dep,Dep dep2,Object param) {
		return depDao.getList(dep,dep2,param);
	}

	@Override
	public PageBean<Dep> getListByPage(Dep dep1,Dep dep2,Object param,Integer page, Integer rows) {
		PageBean<Dep> pageBean = new PageBean<Dep>();
		//总条数
		Integer totalSize = depDao.getCount(dep1,dep2,param);
		//总页数
		Integer totalPage = 0;
		if(null != totalSize) totalPage = (int) Math.ceil((double)totalSize / rows);
		List<Dep> list = depDao.getListByPage(dep1,dep2,param,page,rows);
		pageBean.setPage(page);
		pageBean.setRows(rows);
		pageBean.setTotalSize(totalSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void add(Dep dep) {
		depDao.add(dep);
	}

	@Override
	public void delete(Dep dep) throws Exception{
		//service层处理，先查询出来
		List<Dep> list = depDao.getList(dep, null, null);
		if(null != list && list.size() == 1) {
			depDao.delete(list.get(0));
		}else {
//			throw new Exception("删除失败");
		}
	}

	@Override
	public Dep get(Dep dep) {
		return depDao.getById(dep);
	}

	@Override
	public void update(Dep dep) {
		depDao.update(dep);
	}

}
