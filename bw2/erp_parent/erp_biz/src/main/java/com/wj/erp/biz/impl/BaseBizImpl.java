package com.wj.erp.biz.impl;

import java.util.List;
import java.util.Map;

import com.wj.erp.biz.interfaces.IBaseBiz;
import com.wj.erp.dao.interfaces.IBaseDao;
import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.dao.interfaces.IGoodsDao;
import com.wj.erp.dao.interfaces.IStoreDao;
import com.wj.erp.entity.PageBean;

public class BaseBizImpl<T> implements IBaseBiz<T>{

	//注入baseDao
	private IBaseDao<T> baseDao;

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<T> getList() {
		return baseDao.getList();
	}

	@Override
	public List<T> getList(T t,T t2,Object param) {
		return baseDao.getList(t,t2,param);
	}

	@Override
	public PageBean<T> getListByPage(T t1,T t2,Object param,Integer page, Integer rows) {
		PageBean<T> pageBean = new PageBean<T>();
		//总条数
		Integer totalSize = baseDao.getCount(t1,t2,param);
		//总页数
		Integer totalPage = 0;
		if(null != totalSize) totalPage = (int) Math.ceil((double)totalSize / rows);
		List<T> list = baseDao.getListByPage(t1,t2,param,page,rows);
		pageBean.setPage(page);
		pageBean.setRows(rows);
		pageBean.setTotalSize(totalSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void add(T t) {
		baseDao.add(t);
	}

	@Override
	public void delete(T t) throws Exception{
		//service层处理，先查询出来
		List<T> list = baseDao.getList(t, null, null);
		if(null != list && list.size() == 1) {
			baseDao.delete(list.get(0));
		}else {
//			throw new Exception("删除失败");
		}
	}

	@Override
	public T get(Long id) {
		return baseDao.getById(id);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public T get(String id) {
		return baseDao.getById(id);
	}
	
	public String getGoodsName(Long uuid,Map<Long,String> goodsNameMap,IGoodsDao goodsDao) {
		if(null == uuid) {
			return null;
		}
		
		String goodsName = goodsNameMap.get(uuid);
		if(null == goodsName) {
			goodsName = goodsDao.getById(uuid).getName();
			goodsNameMap.put(uuid, goodsName);
		}
		
		return goodsName;
	}
	public String getEmpName(Long uuid,Map<Long,String> empNameMap,IEmpDao empDao) {
		if(null == uuid) {
			return null;
		}
		
		String empName = empNameMap.get(uuid);
		if(null == empName) {
			empName = empDao.getById(uuid).getName();
			empNameMap.put(uuid, empName);
		}
		
		return empName;
	}
	public String getStoreName(Long uuid,Map<Long,String> storeNameMap,IStoreDao storeDao) {
		if(null == uuid) {
			return null;
		}
		
		String storeName = storeNameMap.get(uuid);
		if(null == storeName) {
			storeName = storeDao.getById(uuid).getName();
			storeNameMap.put(uuid, storeName);
		}
		
		return storeName;
	}
	
	
}
