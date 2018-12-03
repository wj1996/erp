package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IReturnordersBiz;
import com.wj.erp.entity.Returnorders;
/**
 * 退货订单action
 * @author [author]
 *
 */
public class ReturnordersAction extends BaseAction<Returnorders> {

	private IReturnordersBiz returnordersBiz;

	public void setReturnordersBiz(IReturnordersBiz returnordersBiz) {
		this.returnordersBiz = returnordersBiz;
		super.setBaseBiz(this.returnordersBiz);
	}
}
