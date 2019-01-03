package com.wj.erp.action;

import com.alibaba.fastjson.JSON;
import com.wj.erp.biz.interfaces.IEmpBiz;
import com.wj.erp.biz.interfaces.IMenuBiz;
import com.wj.erp.entity.Menu;
/**
 * 菜单action
 * @author [author]
 *
 */
public class MenuAction extends BaseAction<Menu> {

	private IMenuBiz menuBiz;
	private IEmpBiz empBiz;
	
	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
	}

	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(this.menuBiz);
	}
	
	//获取菜单数据
	public void getMenuTree() {
//		Menu menu = menuBiz.get("0");
		Menu menu = empBiz.readMenusByEmpuuid(getLoginUser().getUuid());
		write(JSON.toJSONString(menu));
	}
}
