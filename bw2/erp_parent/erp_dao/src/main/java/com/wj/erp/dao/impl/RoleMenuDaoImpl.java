package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IRoleMenuDao;
import com.wj.erp.entity.RoleMenu;
/**
 * 角色菜单数据访问层类
 * @author [author]
 *
 */
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements IRoleMenuDao{

	/**
	 * 查询公共代码抽取
	 * @param roleMenu
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(RoleMenu roleMenu,RoleMenu roleMenu2) {
		DetachedCriteria dc = DetachedCriteria.forClass(RoleMenu.class);
		if(null != roleMenu) {
			
		}
		return dc;
	}


}
