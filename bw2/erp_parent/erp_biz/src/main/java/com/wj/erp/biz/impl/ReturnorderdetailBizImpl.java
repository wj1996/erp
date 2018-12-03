package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IReturnorderdetailBiz;
import com.wj.erp.dao.interfaces.IReturnorderdetailDao;
import com.wj.erp.entity.Returnorderdetail;
/**
 * 退货订单明细业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class ReturnorderdetailBizImpl extends BaseBizImpl<Returnorderdetail> implements IReturnorderdetailBiz{
	
	private IReturnorderdetailDao returnorderdetailDao;
	
	public void setReturnorderdetailDao(IReturnorderdetailDao returnorderdetailDao) {
		this.returnorderdetailDao = returnorderdetailDao;
		super.setBaseDao(returnorderdetailDao);
	}

	

}
