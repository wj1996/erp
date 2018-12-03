package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IRoleDao;
import com.wj.erp.entity.Role;
/**
 * 角色数据访问层类
 * @author [author]
 *
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao{

	/**
	 * 查询公共代码抽取
	 * @param role
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Role role,Role rol2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Role.class);
		if(null != role) {
			
		}
		return dc;
	}


}
