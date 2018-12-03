package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IStoredetailBiz;
import com.wj.erp.dao.interfaces.IStoredetailDao;
import com.wj.erp.entity.Storedetail;
/**
 * 仓库库存业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class StoredetailBizImpl extends BaseBizImpl<Storedetail> implements IStoredetailBiz{
	
	private IStoredetailDao storedetailDao;
	
	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
		super.setBaseDao(storedetailDao);
	}

	

}
