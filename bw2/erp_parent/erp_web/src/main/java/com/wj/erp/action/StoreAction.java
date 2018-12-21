package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IStoreBiz;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Store;
/**
 * 仓库action
 * @author [author]
 *
 */
public class StoreAction extends BaseAction<Store> {

	private IStoreBiz storeBiz;

	public void setStoreBiz(IStoreBiz storeBiz) {
		this.storeBiz = storeBiz;
		super.setBaseBiz(this.storeBiz);
	}
	
	public void myList() {
		Store store = getT1();
		if(null == store) {
			store = new Store();
			setT1(store);
		}
		Emp loginUser = getLoginUser();
		getT1().setEmpuuid(loginUser.getUuid());
		super.list();
		
	}
}
