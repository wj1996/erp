package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IDepBiz;
import com.wj.erp.dao.interfaces.IDepDao;
import com.wj.erp.entity.Dep;
@Transactional
public class DepBizImpl extends BaseBizImpl<Dep> implements IDepBiz{
	
	private IDepDao depDao;
	
	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
		super.setBaseDao(depDao);
	}

	

}
