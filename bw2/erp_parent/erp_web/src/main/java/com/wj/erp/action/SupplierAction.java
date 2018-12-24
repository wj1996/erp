package com.wj.erp.action;

import org.apache.commons.lang3.StringUtils;

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
	
	private String q; //自动补全，由easyui的combogird自动发过来的

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
	public void getList() {
		//判断查询条件是否为null
		if(null == getT1()) {
			setT1(new Supplier());
		}
		
		if(StringUtils.isNotBlank(q)) getT1().setName(q);
		super.getList();
		
	}
}
