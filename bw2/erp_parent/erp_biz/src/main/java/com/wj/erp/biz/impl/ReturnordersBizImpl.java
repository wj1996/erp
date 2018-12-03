package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IReturnordersBiz;
import com.wj.erp.dao.interfaces.IReturnordersDao;
import com.wj.erp.entity.Returnorders;
/**
 * 退货订单业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class ReturnordersBizImpl extends BaseBizImpl<Returnorders> implements IReturnordersBiz{
	
	private IReturnordersDao returnordersDao;
	
	public void setReturnordersDao(IReturnordersDao returnordersDao) {
		this.returnordersDao = returnordersDao;
		super.setBaseDao(returnordersDao);
	}

	

}
