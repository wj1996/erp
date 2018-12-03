package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IGoodsDao;
import com.wj.erp.entity.Goods;
/**
 * 商品数据访问层类
 * @author [author]
 *
 */
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao{

	/**
	 * 查询公共代码抽取
	 * @param goods
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Goods goods,Goods goods2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Goods.class);
		if(null != goods) {
			if(null != goods.getGoodsType() && 0!= goods.getGoodsType().getUuid()) {
				dc.add(Restrictions.eq("goodsType.uuid", goods.getGoodsType().getUuid()));
			}
		}
		return dc;
	}


}
