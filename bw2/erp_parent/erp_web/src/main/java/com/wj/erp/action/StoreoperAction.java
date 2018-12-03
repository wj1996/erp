package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IStoreoperBiz;
import com.wj.erp.entity.Storeoper;
/**
 * 仓库操作记录action
 * @author [author]
 *
 */
public class StoreoperAction extends BaseAction<Storeoper> {

	private IStoreoperBiz storeoperBiz;

	public void setStoreoperBiz(IStoreoperBiz storeoperBiz) {
		this.storeoperBiz = storeoperBiz;
		super.setBaseBiz(this.storeoperBiz);
	}
}
