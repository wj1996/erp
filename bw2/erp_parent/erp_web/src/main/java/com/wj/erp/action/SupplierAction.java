package com.wj.erp.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.wj.erp.biz.interfaces.ISupplierBiz;
import com.wj.erp.common.Constant;
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
	
	public void export() {
		String filename = "";
		if(Constant.TYPE_SUPPLIER.equals(getT1().getType())) {
			filename = "供应商";
		}
		if(Constant.TYPE_CUSTOMER.equals(getT1().getType())) {
			filename = "客户";
		}
		filename += ".xls";
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//设置输出流
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"iso-8859-1"));
			supplierBiz.export(response.getOutputStream(), getT1());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
