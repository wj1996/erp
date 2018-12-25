package com.wj.erp.demo.poi;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIDemo {

	public static void main(String[] args){
		//创建一个工作簿
		HSSFWorkbook wk = new HSSFWorkbook();
		//创建以工作表
//		HSSFSheet hssfSheet = wk.createSheet();
		HSSFSheet hssfSheet = wk.createSheet("测试");
		//创建一行,行的索引从0开始
		HSSFRow row = hssfSheet.createRow(0);
		//创建单元格,列的索引从0开始
		HSSFCell cell = row.createCell(0);
		//给单元格赋值
		cell.setCellValue("测试");
		
		//设置列宽
		hssfSheet.setColumnWidth(0, 5000);
		//width:跟字体有关，每个字符大小 * 256
		//保存
		File file = new File("G:/test2/test.xls");
		try {
			wk.write(file);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				wk.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
