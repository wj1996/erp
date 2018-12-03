package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IOrderdetailBiz;
import com.wj.erp.entity.Orderdetail;
/**
 * 订单明细action
 * @author [author]
 *
 */
public class OrderdetailAction extends BaseAction<Orderdetail> {

	private IOrderdetailBiz orderdetailBiz;

	public void setOrderdetailBiz(IOrderdetailBiz orderdetailBiz) {
		this.orderdetailBiz = orderdetailBiz;
		super.setBaseBiz(this.orderdetailBiz);
	}
}
