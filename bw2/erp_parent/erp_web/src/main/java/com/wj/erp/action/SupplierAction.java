package com.wj.erp.action;

import com.wj.erp.biz.interfaces.ISupplierBiz;
import com.wj.erp.entity.Supplier;
/**
 * 供应商action
 * @author [author]
 *
 */
public class SupplierAction extends BaseAction<Supplier> {

	private ISupplierBiz supplierBiz;

	public void setSupplierBiz(ISupplierBiz supplierBiz) {
		this.supplierBiz = supplierBiz;
		super.setBaseBiz(this.supplierBiz);
	}
}
