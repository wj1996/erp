package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IOrderdetailBiz;
import com.wj.erp.dao.interfaces.IOrderdetailDao;
import com.wj.erp.entity.Orderdetail;
/**
 * 订单明细业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class OrderdetailBizImpl extends BaseBizImpl<Orderdetail> implements IOrderdetailBiz{
	
	private IOrderdetailDao orderdetailDao;
	
	public void setOrderdetailDao(IOrderdetailDao orderdetailDao) {
		this.orderdetailDao = orderdetailDao;
		super.setBaseDao(orderdetailDao);
	}

	

}
