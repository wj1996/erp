package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IDepBiz;
import com.wj.erp.entity.Dep;
/**
 * 部门action
 * @author [author]
 *
 */
public class DepAction extends BaseAction<Dep> {

	private IDepBiz depBiz;

	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		super.setBaseBiz(this.depBiz);
	}
}
