package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IStoreBiz;
import com.wj.erp.dao.interfaces.IStoreDao;
import com.wj.erp.entity.Store;
/**
 * 仓库业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class StoreBizImpl extends BaseBizImpl<Store> implements IStoreBiz{
	
	private IStoreDao storeDao;
	
	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
		super.setBaseDao(storeDao);
	}

	

}
