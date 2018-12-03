package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.ISupplierBiz;
import com.wj.erp.dao.interfaces.ISupplierDao;
import com.wj.erp.entity.Supplier;
/**
 * 供应商业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class SupplierBizImpl extends BaseBizImpl<Supplier> implements ISupplierBiz{
	
	private ISupplierDao supplierDao;
	
	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		super.setBaseDao(supplierDao);
	}

	

}
