package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IMenuDao;
import com.wj.erp.entity.Menu;
/**
 * 菜单数据访问层类
 * @author [author]
 *
 */
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements IMenuDao{

	/**
	 * 查询公共代码抽取
	 * @param menu
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Menu menu,Menu menu2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Menu.class);
		if(null != menu) {
			
		}
		return dc;
	}


}
