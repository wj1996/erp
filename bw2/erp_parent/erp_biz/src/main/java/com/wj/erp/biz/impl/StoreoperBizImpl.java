package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IStoreoperBiz;
import com.wj.erp.dao.interfaces.IStoreoperDao;
import com.wj.erp.entity.Storeoper;
/**
 * 仓库操作记录业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class StoreoperBizImpl extends BaseBizImpl<Storeoper> implements IStoreoperBiz{
	
	private IStoreoperDao storeoperDao;
	
	public void setStoreoperDao(IStoreoperDao storeoperDao) {
		this.storeoperDao = storeoperDao;
		super.setBaseDao(storeoperDao);
	}

	

}
