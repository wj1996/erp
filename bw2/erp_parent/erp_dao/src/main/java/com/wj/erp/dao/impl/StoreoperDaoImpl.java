package com.wj.erp.dao.impl;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mchange.rmi.Cardable;
import com.wj.erp.dao.interfaces.IStoreoperDao;
import com.wj.erp.entity.Storeoper;
/**
 * 仓库操作记录数据访问层类
 * @author [author]
 *
 */
public class StoreoperDaoImpl extends BaseDaoImpl<Storeoper> implements IStoreoperDao{

	/**
	 * 查询公共代码抽取
	 * @param storeoper
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Storeoper storeoper,Storeoper storeoper2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Storeoper.class);
		if(null != storeoper) {
			if(StringUtils.isNotBlank(storeoper.getType())) {
				dc.add(Restrictions.eq("type", storeoper.getType()));
			}
			
			//商品查询
			if(null != storeoper.getGoodsuuid()) {
				dc.add(Restrictions.eq("goodsuuid", storeoper.getGoodsuuid()));
			}
				
			//仓库查询
			if(null != storeoper.getStoreuuid()) {
				dc.add(Restrictions.eq("storesuuid", storeoper.getStoreuuid()));
			}	
			//员工
			if(null != storeoper.getEmpuuid()) {
				dc.add(Restrictions.eq("empuuid", storeoper.getEmpuuid()));
			}	
			//操作时间
			if(null != storeoper.getOpertime()) {
				//大于
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(storeoper.getOpertime());
				calendar.set(Calendar.HOUR, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				dc.add(Restrictions.ge("opertime", calendar.getTime()));
			}
		}
		
		if(null != storeoper2) {
			if(null != storeoper2.getOpertime()) {
				//小于
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(storeoper.getOpertime());
				calendar.set(Calendar.HOUR, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);
				dc.add(Restrictions.le("opertime", calendar.getTime()));
			}
		}
		
		return dc;
	}


}
