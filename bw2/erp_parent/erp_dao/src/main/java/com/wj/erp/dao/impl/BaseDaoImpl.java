package com.wj.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wj.erp.dao.interfaces.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{

	private Class<T> entityClass;
	
	public BaseDaoImpl() {
		//通过子类来获取父类
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) genericSuperclass;
		Type[] types = pType.getActualTypeArguments();
		Type targetType = types[0];
		entityClass = (Class<T>) targetType;
	}
	
	@Override
	public List<T> getList() {
		return (List<T>) this.getHibernateTemplate().find("from " + entityClass.getSimpleName());
	}

	/**
	 * 条件查询
	 * 查询条件优化----查某个日期期间的数据，所有再传入一个T,例如选择兴趣爱好，是一个数组内容，传入一个Object
	 */
	@Override
	public List<T> getList(T t,T t2,Object param) {
		/*DetachedCriteria dc = DetachedCriteria.forClass(T.class);
		if(null != T) {
			if(null != T.getName() && T.getName().trim().length() > 0) {
				
				 * MatchMode.ANYWHERE %abc%
				 * MatchMode.START %abc
				 * MatchMode.END  abc%
				 * MatchMode.EXACT abc
				 
				dc.add(Restrictions.like("name", T.getName(),MatchMode.ANYWHERE));
			}
			if(null != T.getTele() && T.getTele().trim().length() > 0) {
				
				 * MatchMode.ANYWHERE %abc%
				 * MatchMode.START %abc
				 * MatchMode.END  abc%
				 * MatchMode.EXACT abc
				 
				dc.add(Restrictions.like("tele", T.getTele(),MatchMode.ANYWHERE));
			}
		}*/
		return (List<T>) this.getHibernateTemplate().findByCriteria(this.getDetachedCriteria(t,t2));
	}

	@Override
	public Integer getCount(T t,T t2,Object param) {
		/*DetachedCriteria dc = DetachedCriteria.forClass(T.class);
		if(null != T) {
			if(null != T.getName() && T.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", T.getName(),MatchMode.ANYWHERE));
			}
			if(null != T.getTele() && T.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", T.getTele(),MatchMode.ANYWHERE));
			}
		}*/
		DetachedCriteria dc = this.getDetachedCriteria(t,t2);
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		if(null != list && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<T> getListByPage(T t,T t2,Object param,Integer page, Integer rows) {
		/*DetachedCriteria dc = DetachedCriteria.forClass(T.class);
		if(null != T) {
			if(null != T.getName() && T.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", T.getName(),MatchMode.ANYWHERE));
			}
			if(null != T.getTele() && T.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", T.getTele(),MatchMode.ANYWHERE));
			}
		}*/
		return (List<T>) this.getHibernateTemplate().findByCriteria(this.getDetachedCriteria(t,t2), (page - 1) * rows, rows);
	}
	
	/**
	 * 查询公共代码抽取(由子类实现)
	 * @param T
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(T t1,T t2) {
		return null;
	}

	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T getById(Long id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}
	
	
	public T getById(String id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}
	

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

}
