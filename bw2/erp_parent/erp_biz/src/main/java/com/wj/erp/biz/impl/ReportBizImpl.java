package com.wj.erp.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wj.erp.biz.interfaces.IReportBiz;
import com.wj.erp.dao.interfaces.IReportDao;

public class ReportBizImpl implements IReportBiz {

	private IReportDao reportDao;

	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public List ordersReport(Date startDate, Date endDate) {
		return reportDao.ordersReport(startDate, endDate);
	}

	@Override
	public List trendReport(Long year) {
		// 对月份进行查缺补漏
		List<Map<String, Object>> list = reportDao.trendReport(year);
		// 最终返回的数据
		List<Map<String, Object>> rtnData = new ArrayList<>();
		// key=月份，值={"name":4,"y":990}
		Map<String, Map<String, Object>> yearDataMap = new HashMap<>();
		// 把从数据库存在的月份的数据存到yearData中
		for (Map<String, Object> month : list) {
			yearDataMap.put(month.get("name") + "", month);
		}
		Map<String, Object> monthDataMap = null;
		for (int i = 1; i <= 12; i++) {
			monthDataMap = yearDataMap.get(i + "");
			if (monthDataMap == null) {
				monthDataMap = new HashMap<>();
				monthDataMap.put("name", i + "月");
				monthDataMap.put("y", 0);
			} else {
				monthDataMap.put("name", i + "月");
			}

			rtnData.add(monthDataMap);
		}

		return rtnData;
	}

}
