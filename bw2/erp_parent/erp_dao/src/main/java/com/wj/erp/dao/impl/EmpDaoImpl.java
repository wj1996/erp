package com.wj.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.entity.Emp;
/**
 * 员工数据访问层类
 * @author [author]
 *
 */
public class EmpDaoImpl extends BaseDaoImpl<Emp> implements IEmpDao{

	/**
	 * 查询公共代码抽取
	 * @param emp
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(Emp emp,Emp emp2) {
		DetachedCriteria dc = DetachedCriteria.forClass(Emp.class);
		if(null != emp) {
			if(null != emp.getDep() && null != emp.getDep().getUuid()) {
				dc.add(Restrictions.eq("dep.uuid", emp.getDep().getUuid()));
			}
			//年月日
			if(null != emp.getBirthday()) {
				dc.add(Restrictions.ge("birthday", emp.getBirthday()));
			}
			if(null != emp.getGender()) {
				dc.add(Restrictions.eq("gender", emp.getGender()));
			}
		}
		if(null != emp2) {
			if(null != emp2.getBirthday()) {
				dc.add(Restrictions.le("birthday", emp2.getBirthday()));
			}
		}
		
		
		
		return dc;
	}

	@Override
	public Emp getByUsernameAndPwd(String username, String pwd) {
		String hql = "from Emp where username = ?0 and pwd = ?1";
		List<Emp> list = (List<Emp>) this.getHibernateTemplate().find(hql, username,pwd);
		if(null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


}
