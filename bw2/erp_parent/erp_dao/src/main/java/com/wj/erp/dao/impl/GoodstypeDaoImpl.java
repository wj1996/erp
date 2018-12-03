package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IGoodstypeDao;
import com.wj.erp.entity.Goodstype;
/**
 * 商品分类数据访问层类
 * @author [author]
 *
 */
public class GoodstypeDaoImpl extends BaseDaoImpl<Goodstype> implements IGoodstypeDao{

	/**
	 * 查询公共代码抽取
	 * @param goodstype
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Goodstype goodstype,Goodstype goodstype2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Goodstype.class);
		if(null != goodstype) {
			
		}
		return dc;
	}


}
