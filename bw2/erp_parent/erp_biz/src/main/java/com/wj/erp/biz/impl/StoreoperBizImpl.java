package com.wj.erp.biz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IStoreoperBiz;
import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.dao.interfaces.IGoodsDao;
import com.wj.erp.dao.interfaces.IStoreDao;
import com.wj.erp.dao.interfaces.IStoreoperDao;
import com.wj.erp.entity.PageBean;
import com.wj.erp.entity.Storeoper;
/**
 * 仓库操作记录业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class StoreoperBizImpl extends BaseBizImpl<Storeoper> implements IStoreoperBiz{
	
	private IStoreoperDao storeoperDao;
	private IEmpDao empDao;
	private IStoreDao storeDao;
	private IGoodsDao goodsDao;
	
	
	
	public IEmpDao getEmpDao() {
		return empDao;
	}


	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}


	public IStoreDao getStoreDao() {
		return storeDao;
	}


	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}


	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}


	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}


	public IStoreoperDao getStoreoperDao() {
		return storeoperDao;
	}


	public void setStoreoperDao(IStoreoperDao storeoperDao) {
		this.storeoperDao = storeoperDao;
		super.setBaseDao(storeoperDao);
	}

	
	@Override
	public PageBean<Storeoper> getListByPage(Storeoper t1, Storeoper t2, Object param, Integer page, Integer rows) {
		PageBean<Storeoper> pb = super.getListByPage(t1, t2, param, page, rows);
		Map<Long,String> goodsNameMap = new HashMap<>();
		Map<Long,String> storeNameMap = new HashMap<>();
		Map<Long,String> empNameMap = new HashMap<>();
		for(Storeoper so : pb.getList()) {
			so.setEmpName(getEmpName(so.getEmpuuid(), empNameMap, empDao));
			so.setGoodsName(getGoodsName(so.getGoodsuuid(),goodsNameMap,goodsDao));
			so.setStoreName(getStoreName(so.getStoreuuid(), storeNameMap, storeDao));
		}
		
		return pb;
	}
	

}
