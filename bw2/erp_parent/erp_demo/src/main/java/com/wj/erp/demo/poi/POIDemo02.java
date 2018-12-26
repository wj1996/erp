package com.wj.erp.demo.poi;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIDemo02 {

	public static void main(String[] args) {
		// 创建一个工作簿
		HSSFWorkbook wk = new HSSFWorkbook();
		// 创建以工作表
//				HSSFSheet hssfSheet = wk.createSheet();
		HSSFSheet hssfSheet = wk.createSheet("测试");
		HSSFRow row = hssfSheet.createRow(0);
		//创建单元格样式
		HSSFCellStyle style = wk.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		
		//对齐方式
		style.setAlignment(HorizontalAlignment.CENTER); //水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
		
		//创建内容样式的字体
		HSSFFont font = wk.createFont();
		font.setFontName("宋体"); 
		font.setFontHeightInPoints((short)11);  //设置字体大小
		style.setFont(font);
		
		
		//标题样式
		HSSFCellStyle titleStyle = wk.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFFont titleFont = wk.createFont();
		titleFont.setFontName("黑体");
		titleFont.setFontHeightInPoints((short)18);
		//加粗
		titleFont.setBold(true);
		
		titleStyle.setFont(titleFont);
		//合并单元格
		//标题：
		hssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		//
		hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
		//订单明细
		hssfSheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 3));
		
		for(int i = 2;i <= 12;i++) {
			row = hssfSheet.createRow(i);
			for(int j = 0;j < 4;j++) {
				row.createCell(j).setCellStyle(style);;
			}
			row.setHeight((short)500);
		}
		//必须先有创建的行和单元格
		HSSFCell titleCell = hssfSheet.createRow(0).createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue("采购单");
		
		hssfSheet.getRow(2).getCell(0).setCellValue("供应商");
		hssfSheet.getRow(3).getCell(0).setCellValue("下单日期");
		hssfSheet.getRow(4).getCell(0).setCellValue("审核日期");
		hssfSheet.getRow(5).getCell(0).setCellValue("采购日期");
		hssfSheet.getRow(6).getCell(0).setCellValue("入库日期");
		hssfSheet.getRow(3).getCell(2).setCellValue("经办人");
		hssfSheet.getRow(4).getCell(2).setCellValue("经办人");
		hssfSheet.getRow(5).getCell(2).setCellValue("经办人");
		hssfSheet.getRow(6).getCell(2).setCellValue("经办人");
		
		hssfSheet.getRow(7).getCell(0).setCellValue("订单明细");
		//设置行高和列宽
		hssfSheet.getRow(0).setHeight((short)1000);
		for(int i = 2;i <= 12;i++) {
			hssfSheet.getRow(i).setHeight((short)500);
		}
	
		//设置列宽
		for(int i = 0;i < 4;i++) {
			hssfSheet.setColumnWidth(i, 5000);
		}
		
		
		
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
