package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IReturnorderdetailDao;
import com.wj.erp.entity.Returnorderdetail;
/**
 * 退货订单明细数据访问层类
 * @author [author]
 *
 */
public class ReturnorderdetailDaoImpl extends BaseDaoImpl<Returnorderdetail> implements IReturnorderdetailDao{

	/**
	 * 查询公共代码抽取
	 * @param returnorderdetail
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Returnorderdetail returnorderdetail,Returnorderdetail returnorderdetail2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Returnorderdetail.class);
		if(null != returnorderdetail) {
			
		}
		return dc;
	}


}
