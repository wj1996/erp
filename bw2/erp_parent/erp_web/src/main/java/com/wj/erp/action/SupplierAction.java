package com.wj.erp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.wj.erp.biz.exception.ErpException;
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
	
	private File file; //上传的文件
	private String fileFileName;//上传文件名
	private String fileContentType;//上传文件类型
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * 导入数据
	 */
	public void doImport() {
		//文件类型判断
		if(!"application/vnd.ms-excel".equals(fileContentType)) {
			ajaxReturn(false, "上传文件必须为excel文件");
			return;
		}
		try {
			supplierBiz.doImport(new FileInputStream(file));
			ajaxReturn(true, "上传文件成功");
		} catch (ErpException e) {
			ajaxReturn(false, e.getMessage());
		} catch (FileNotFoundException e) {
			ajaxReturn(false, "上传文件失败");
			e.printStackTrace();
		}
	}
}
