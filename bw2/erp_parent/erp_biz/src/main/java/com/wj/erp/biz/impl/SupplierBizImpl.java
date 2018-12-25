package com.wj.erp.biz.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.ISupplierBiz;
import com.wj.erp.common.Constant;
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

	@Override
	public void export(OutputStream os,Supplier t) {
		//获取要导出的数据列表
		List<Supplier> list = supplierDao.getList(t, null, null);
		
		String sheetName = "";
		if(Constant.TYPE_SUPPLIER.equals(t.getType())) sheetName = "供应商";
		if(Constant.TYPE_CUSTOMER.equals(t.getType())) sheetName = "客户";
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = sheet.createRow(0);
		String[] header = {"名称","地址","联系人","电话","Email"};
		int[] width = {5000,8000,4000,8000,10000};
		HSSFCell cell = null;
		for(int i = 0;i < header.length;i++) {
			cell = row.createCell(i);
			cell.setCellValue(header[i]);
			//设置列宽
			sheet.setColumnWidth(i, width[i]);
		}
		//导出的内容
		int rowCount = 1;
		for(Supplier supplier:list) {
			row = sheet.createRow(rowCount);
			row.createCell(0).setCellValue(supplier.getName());
			row.createCell(1).setCellValue(supplier.getAddress());
			row.createCell(2).setCellValue(supplier.getContact());
			row.createCell(3).setCellValue(supplier.getTele());
			row.createCell(4).setCellValue(supplier.getEmail());
			rowCount++;
		}
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	

}
