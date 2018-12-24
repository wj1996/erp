package com.wj.erp.biz.impl;

import java.util.Date;
import java.util.List;

import com.wj.erp.biz.interfaces.IReportBiz;
import com.wj.erp.dao.interfaces.IReportDao;

public class ReportBizImpl implements IReportBiz{
	
	
	private IReportDao reportDao;

	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public List ordersReport(Date startDate,Date endDate) {
		return reportDao.ordersReport(startDate,endDate);
	}

	
	
}
