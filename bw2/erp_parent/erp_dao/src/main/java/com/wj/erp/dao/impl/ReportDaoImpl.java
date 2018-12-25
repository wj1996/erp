package com.wj.erp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wj.erp.dao.interfaces.IReportDao;

public class ReportDaoImpl extends HibernateDaoSupport implements IReportDao{

	@Override
	public List ordersReport(Date startDate,Date endDate) {
		StringBuilder hql = new StringBuilder();
		hql.append("select new Map(gt.name as name,sum(ol.money) as y) from Orders o,Orderdetail ol,Goods gs,Goodstype gt ");
		hql.append("where gt.uuid = gs.goodsType and ol.orders = o.uuid and ");
		hql.append("ol.goodsuuid = gs.uuid and o.type = '2' ");
		
		
		List<Date> dateList = new ArrayList<>();
		
		if(null != startDate) {
			hql.append(" and o.createtime >= ?0");
			dateList.add(startDate);
		}
		if(null != endDate) {
			hql.append(" and o.createtime <= ?1");
			dateList.add(endDate);
		}
		
		hql.append(" group by gt.name");
		
		return getHibernateTemplate().find(hql.toString(),dateList.toArray(new Date[]{}));
	}

	@Override
	public List trendReport(Long year) {
		StringBuilder hql = new StringBuilder();
		hql.append("select new Map(month(o.createtime) as name,sum(ol.money) as y) from Orderdetail ol,Orders o where ");
		hql.append("ol.orders = o and o.type = '2' and year(o.createtime) = ?0 group by month(o.createtime)");
		return getHibernateTemplate().find(hql.toString(),year.intValue());
	}

}
