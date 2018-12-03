package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IGoodstypeBiz;
import com.wj.erp.entity.Goodstype;
/**
 * 商品分类action
 * @author [author]
 *
 */
public class GoodstypeAction extends BaseAction<Goodstype> {

	private IGoodstypeBiz goodstypeBiz;

	public void setGoodstypeBiz(IGoodstypeBiz goodstypeBiz) {
		this.goodstypeBiz = goodstypeBiz;
		super.setBaseBiz(this.goodstypeBiz);
	}
}
