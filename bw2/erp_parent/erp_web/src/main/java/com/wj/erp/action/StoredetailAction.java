package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IStoredetailBiz;
import com.wj.erp.entity.Storedetail;
/**
 * 仓库库存action
 * @author [author]
 *
 */
public class StoredetailAction extends BaseAction<Storedetail> {

	private IStoredetailBiz storedetailBiz;

	public void setStoredetailBiz(IStoredetailBiz storedetailBiz) {
		this.storedetailBiz = storedetailBiz;
		super.setBaseBiz(this.storedetailBiz);
	}
}
