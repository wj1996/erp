package com.wj.erp.biz.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.transaction.annotation.Transactional;

import com.redsum.bos.ws.Waybilldetail;
import com.redsum.bos.ws.impl.IWaybillWs;
import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.common.Constant;
import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.dao.interfaces.IOrdersDao;
import com.wj.erp.dao.interfaces.ISupplierDao;
import com.wj.erp.entity.Orderdetail;
import com.wj.erp.entity.Orders;
import com.wj.erp.entity.PageBean;

/**
 * 订单业务逻辑层
 * 
 * @author [author]
 *
 */
@Transactional
public class OrdersBizImpl extends BaseBizImpl<Orders> implements IOrdersBiz {

	private IOrdersDao ordersDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;
	private IWaybillWs waybillWs;
	
	public void setWaybillWs(IWaybillWs waybillWs) {
		this.waybillWs = waybillWs;
	}

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao(ordersDao);
	}

	@Override
	public void add(Orders orders) {
		orders.setState(Constant.STATE_CREATE);
//		orders.setType(Constant.TYPE_IN); //由前台传入
		orders.setCreatetime(new Date());

		double total = 0;
		for (Orderdetail detail : orders.getOrderDetails()) {
			total += detail.getMoney();
			// 明细的状态
			detail.setState(Constant.STATE_NOT_IN);
			// 设置跟订单的关系
			detail.setOrders(orders);
		}
		// 设置订单总金额
		orders.setTotalmoney(total);
		ordersDao.add(orders);
	}

	@Override
	public PageBean<Orders> getListByPage(Orders t1, Orders t2, Object param, Integer page, Integer rows) {
		PageBean<Orders> pageBean = super.getListByPage(t1, t2, param, page, rows);

		// 缓存员工编号和员工的名称
		Map<Long, String> empNameMap = new HashMap<>();
		// 缓存供应商编号和员工的名称
		Map<Long, String> supplierNameMap = new HashMap<>();
		// 循环获取员工的名称
		for (Orders order : pageBean.getList()) {
			order.setCreaterName(getEmpName(order.getCreater(), empNameMap));
			order.setCheckerName(getEmpName(order.getChecker(), empNameMap));
			order.setStarterName(getEmpName(order.getStarter(), empNameMap));
			order.setEnderName(getEmpName(order.getEnder(), empNameMap));
			// 供应商
			order.setSupplierName(getSupplierName(order.getSupplieruuid(), supplierNameMap));
		}

		return pageBean;
	}

	/**
	 * 获取员工名称
	 * 
	 * @param uuid员工编号
	 * @param empNameMap
	 * @return 返回员工名称
	 */
	private String getEmpName(Long uuid, Map<Long, String> empNameMap) {
		if (uuid == null) {
			return null;
		}
		String empName = empNameMap.get(uuid);
		if (null == empName) {
			// 如果缓存中没有
			empName = empDao.getById(uuid).getUsername();
			// 放入缓存
			empNameMap.put(uuid, empName);
		}
		return empName;
	}

	/**
	 * 获取供应商名称
	 * 
	 * @param uuid供应商编号
	 * @param supplierNameMap
	 * @return 返回供应商名称
	 */
	private String getSupplierName(Long uuid, Map<Long, String> supplierNameMap) {
		if (uuid == null) {
			return null;
		}
		String supplierName = supplierNameMap.get(uuid);
		if (null == supplierName) {
			// 如果缓存中没有
			supplierName = supplierDao.getById(uuid).getName();
			// 放入缓存
			supplierNameMap.put(uuid, supplierName);
		}
		return supplierName;
	}

	/**
	 * 审核
	 */
	@Override
	public void doCheck(Long uuid, Long empUuid) {
		// 获取订单，进入持久化状态
		Orders order = ordersDao.getById(uuid);
		if (!Constant.STATE_CREATE.equals(order.getState())) {
			throw new ErpException("亲！订单已经审核过了");
		}
		// 设置订单的状态
		order.setState(Constant.STATE_CHECK);
		// 审核时间
		order.setChecktime(new Date());
		// 审核人
		order.setChecker(empUuid);

	}

	@Override
	public void doStart(Long uuid, Long empUuid) {
		// 获取订单，进入持久化状态
		Orders order = ordersDao.getById(uuid);
		if (!Constant.STATE_CHECK.equals(order.getState())) {
			throw new ErpException("亲！订单已经确认过了");
		}
		// 设置订单的状态
		order.setState(Constant.STATE_START);
		// 审核时间
		order.setStarttime(new Date());
		// 确认人
		order.setStarter(empUuid);
	}

	@Override
	public void export(OutputStream os, Long uuid) {
		// 创建一个工作簿
		HSSFWorkbook wk = new HSSFWorkbook();
		// 创建以工作表
//						HSSFSheet hssfSheet = wk.createSheet();

		Orders orders = ordersDao.getById(uuid);
		List<Orderdetail> orderDetails = orders.getOrderDetails();

		String sheetName = "";
		if (Constant.TYPE_IN.equals(orders.getType())) {
			sheetName = "采购";
		}
		if (Constant.TYPE_OUT.equals(orders.getType())) {
			sheetName = "销售";
		}

		HSSFSheet hssfSheet = wk.createSheet("测试");
		HSSFRow row = hssfSheet.createRow(0);
		// 创建单元格样式
		HSSFCellStyle style = wk.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);

		// 对齐方式
		style.setAlignment(HorizontalAlignment.CENTER); // 水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中

		// 创建内容样式的字体
		HSSFFont font = wk.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11); // 设置字体大小
		style.setFont(font);
		// 日期格式
		HSSFCellStyle dateStyle = wk.createCellStyle();
		// 把style的样式复制到date中去
		dateStyle.cloneStyleFrom(style);
		HSSFDataFormat df = wk.createDataFormat();
		dateStyle.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));

		// 标题样式
		HSSFCellStyle titleStyle = wk.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFFont titleFont = wk.createFont();
		titleFont.setFontName("黑体");
		titleFont.setFontHeightInPoints((short) 18);
		// 加粗
		titleFont.setBold(true);

		titleStyle.setFont(titleFont);
		// 合并单元格
		// 标题：
		hssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		//
		hssfSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
		// 订单明细
		hssfSheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 3));

		int rowCount = orderDetails.size() + 9;
		for (int i = 2; i <= rowCount; i++) {
			row = hssfSheet.createRow(i);
			for (int j = 0; j < 4; j++) {
				row.createCell(j).setCellStyle(style);
				;
			}
			row.setHeight((short) 500);
		}
		// 必须先有创建的行和单元格
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

		hssfSheet.getRow(8).getCell(0).setCellValue("商品名称");
		hssfSheet.getRow(8).getCell(1).setCellValue("数量");
		hssfSheet.getRow(8).getCell(2).setCellValue("价格");
		hssfSheet.getRow(8).getCell(3).setCellValue("金额");

		// 设置行高和列宽
		hssfSheet.getRow(0).setHeight((short) 1000);
		for (int i = 2; i <= rowCount; i++) {
			hssfSheet.getRow(i).setHeight((short) 500);
		}

		// 设置列宽
		for (int i = 0; i < 4; i++) {
			hssfSheet.setColumnWidth(i, 6000);
		}
		// 订单详情,设置日期与经办人
		// 缓存员工编号和员工的名称
		Map<Long, String> empNameMap = new HashMap<>();
		// 缓存供应商编号和员工的名称
		Map<Long, String> supplierNameMap = new HashMap<>();
		//设置供应商
		hssfSheet.getRow(2).getCell(1).setCellValue(getSupplierName(orders.getSupplieruuid(), supplierNameMap));
		hssfSheet.getRow(3).getCell(1).setCellStyle(dateStyle);
		hssfSheet.getRow(4).getCell(1).setCellStyle(dateStyle);
		hssfSheet.getRow(5).getCell(1).setCellStyle(dateStyle);
		hssfSheet.getRow(6).getCell(1).setCellStyle(dateStyle);
		if(null != orders.getCreatetime())
		hssfSheet.getRow(3).getCell(1).setCellValue(orders.getCreatetime());
		if(null != orders.getChecktime())
		hssfSheet.getRow(4).getCell(1).setCellValue(orders.getChecktime());
		if(null != orders.getStarttime())
		hssfSheet.getRow(5).getCell(1).setCellValue(orders.getStarttime());
		if(null != orders.getEndtime())
		hssfSheet.getRow(6).getCell(1).setCellValue(orders.getEndtime());
		hssfSheet.getRow(3).getCell(3).setCellValue(getEmpName(orders.getCreater(), empNameMap));
		hssfSheet.getRow(4).getCell(3).setCellValue(getEmpName(orders.getCreater(), empNameMap));
		hssfSheet.getRow(5).getCell(3).setCellValue(getEmpName(orders.getCreater(), empNameMap));
		hssfSheet.getRow(6).getCell(3).setCellValue(getEmpName(orders.getCreater(), empNameMap));

		// 设置内容
		for (int i = 9; i < rowCount; i++) {
			Orderdetail orderdetail = orderDetails.get(i - 9);
			row = hssfSheet.getRow(i);
			row.getCell(0).setCellValue(orderdetail.getGoodsname());
			row.getCell(1).setCellValue(orderdetail.getNum());
			row.getCell(2).setCellValue(orderdetail.getPrice());
			row.getCell(3).setCellValue(orderdetail.getMoney());
		}
		// 设置总额
		row = hssfSheet.getRow(rowCount);
		row.getCell(0).setCellValue("合计");
		row.getCell(3).setCellValue(orders.getTotalmoney());

		try {
			wk.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wk.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Waybilldetail> waybilldetailList(Long sn) {
		return waybillWs.waybilldetailList(sn);
	}

}
