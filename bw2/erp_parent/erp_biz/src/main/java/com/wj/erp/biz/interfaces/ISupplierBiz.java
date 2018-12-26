package com.wj.erp.biz.interfaces;

import java.io.InputStream;
import java.io.OutputStream;

import com.wj.erp.entity.Supplier;

/**
 * 供应商业务逻辑层
 * @author [author]
 *
 */
public interface ISupplierBiz extends IBaseBiz<Supplier>{

	/**
	 * 导出数据
	 * @param os
	 */
	void export(OutputStream os,Supplier t);
	
	/**
	 * 导入数据
	 * @param is
	 */
	void doImport(InputStream is);
}
