package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IMenuBiz;
import com.wj.erp.dao.interfaces.IMenuDao;
import com.wj.erp.entity.Menu;
/**
 * 菜单业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class MenuBizImpl extends BaseBizImpl<Menu> implements IMenuBiz{
	
	private IMenuDao menuDao;
	
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(menuDao);
	}

	

}
