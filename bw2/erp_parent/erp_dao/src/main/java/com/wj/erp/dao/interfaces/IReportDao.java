package com.wj.erp.dao.interfaces;

import java.util.Date;
import java.util.List;

/**
 * 报表数据接口
 * @author 24253
 *
 */
public interface IReportDao {

	/**
	 * 销售统计报表
	 * @return
	 */
	List ordersReport(Date startDate,Date endDate);
	
	/**
	 * 销售趋势
	 * @param year
	 * @return
	 */
	List trendReport(Long year);
}
