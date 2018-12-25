package com.wj.erp.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wj.erp.biz.interfaces.IReportBiz;

/**
 * 报表action
 * @author 24253
 *
 */
public class ReportAction {

	private IReportBiz reportBiz;

	public void setReportBiz(IReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}
	
	private Date startDate;
	private Date endDate;
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void orderReport() {
		List list = reportBiz.ordersReport(startDate,endDate);
		write(JSON.toJSONString(list));
	}
	
	private Long year;
	
	public void setYear(Long year) {
		this.year = year;
	}

	public void trendReport() {
		List list = reportBiz.trendReport(year);
		write(JSONObject.toJSONString(list));
	}
	
	
	
	
	public void write(String jsonString) {
		try {
			String jsonp = ServletActionContext.getRequest().getParameter("callback");
			HttpServletResponse response = ServletActionContext.getResponse();
			//跨域设置（IE10以下版本无法使用）
//			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/json;charset=utf-8");
			if (StringUtils.isNotBlank(jsonp)) {
				response.getWriter().write(jsonp + "(" + jsonString + ")");
			} else {
				response.getWriter().write(jsonString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
