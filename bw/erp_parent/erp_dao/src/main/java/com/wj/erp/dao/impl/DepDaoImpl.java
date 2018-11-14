package com.wj.erp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wj.erp.dao.interfaces.IDepDao;
import com.wj.erp.entity.Dep;

public class DepDaoImpl extends HibernateDaoSupport implements IDepDao{

	@Override
	public List<Dep> getList() {
		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
	}

	/**
	 * 条件查询
	 * 查询条件优化----查某个日期期间的数据，所有再传入一个Dep,例如选择兴趣爱好，是一个数组内容，传入一个Object
	 */
	@Override
	public List<Dep> getList(Dep dep,Dep dep2,Object param) {
		/*DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != dep) {
			if(null != dep.getName() && dep.getName().trim().length() > 0) {
				
				 * MatchMode.ANYWHERE %abc%
				 * MatchMode.START %abc
				 * MatchMode.END  abc%
				 * MatchMode.EXACT abc
				 
				dc.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(null != dep.getTele() && dep.getTele().trim().length() > 0) {
				
				 * MatchMode.ANYWHERE %abc%
				 * MatchMode.START %abc
				 * MatchMode.END  abc%
				 * MatchMode.EXACT abc
				 
				dc.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
		}*/
		return (List<Dep>) this.getHibernateTemplate().findByCriteria(this.getDetachedCriteria(dep));
	}

	@Override
	public Integer getCount(Dep dep,Dep dep2,Object param) {
		/*DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != dep) {
			if(null != dep.getName() && dep.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(null != dep.getTele() && dep.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
		}*/
		DetachedCriteria dc = this.getDetachedCriteria(dep);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		if(null != list && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<Dep> getListByPage(Dep dep,Dep dep2,Object param,Integer page, Integer rows) {
		/*DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != dep) {
			if(null != dep.getName() && dep.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(null != dep.getTele() && dep.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
		}*/
		return (List<Dep>) this.getHibernateTemplate().findByCriteria(this.getDetachedCriteria(dep), (page - 1) * rows, rows);
	}
	
	/**
	 * 查询公共代码抽取
	 * @param dep
	 * @return
	 */
	private DetachedCriteria getDetachedCriteria(Dep dep) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != dep) {
			if(null != dep.getName() && dep.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(null != dep.getTele() && dep.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
			
			//根据id去查询最好不要这样写，利用hibernate的主键直接查询
			if(null != dep.getUuid()) {
				dc.add(Restrictions.eq("uuid", dep.getUuid()));
			}
		}
		
		dc.addOrder(Order.desc("uuid"));
		
		return dc;
	}

	@Override
	public void add(Dep dep) {
		this.getHibernateTemplate().save(dep);
	}

	@Override
	public void delete(Dep dep) {
		this.getHibernateTemplate().delete(dep);
	}

	@Override
	public Dep getById(Dep dep) {
		return this.getHibernateTemplate().get(Dep.class, dep.getUuid());
	}

	@Override
	public void update(Dep dep) {
		this.getHibernateTemplate().update(dep);
	}

}
