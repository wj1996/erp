package com.wj.erp.biz.interfaces;

import java.util.Date;
import java.util.List;
/**
 * 报表业务接口
 * @author 24253
 *
 */

public interface IReportBiz {
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
