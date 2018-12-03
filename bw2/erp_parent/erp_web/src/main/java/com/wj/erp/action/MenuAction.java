package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IMenuBiz;
import com.wj.erp.entity.Menu;
/**
 * 菜单action
 * @author [author]
 *
 */
public class MenuAction extends BaseAction<Menu> {

	private IMenuBiz menuBiz;

	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(this.menuBiz);
	}
}
