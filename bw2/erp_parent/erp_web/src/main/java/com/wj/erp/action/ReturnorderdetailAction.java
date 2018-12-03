package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IReturnorderdetailBiz;
import com.wj.erp.entity.Returnorderdetail;
/**
 * 退货订单明细action
 * @author [author]
 *
 */
public class ReturnorderdetailAction extends BaseAction<Returnorderdetail> {

	private IReturnorderdetailBiz returnorderdetailBiz;

	public void setReturnorderdetailBiz(IReturnorderdetailBiz returnorderdetailBiz) {
		this.returnorderdetailBiz = returnorderdetailBiz;
		super.setBaseBiz(this.returnorderdetailBiz);
	}
}
