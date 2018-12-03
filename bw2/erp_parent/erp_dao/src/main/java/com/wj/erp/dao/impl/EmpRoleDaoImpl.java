package com.wj.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IEmpRoleDao;
import com.wj.erp.entity.EmpRole;
/**
 * 员工角色数据访问层类
 * @author [author]
 *
 */
public class EmpRoleDaoImpl extends BaseDaoImpl<EmpRole> implements IEmpRoleDao{

	/**
	 * 查询公共代码抽取
	 * @param empRole
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(EmpRole empRole,EmpRole empRole2) {
		DetachedCriteria dc = DetachedCriteria.forClass(EmpRole.class);
		if(null != empRole) {
			
		}
		return dc;
	}


}
