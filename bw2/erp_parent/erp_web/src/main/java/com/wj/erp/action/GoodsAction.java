package com.wj.erp.action;

import com.wj.erp.biz.interfaces.IGoodsBiz;
import com.wj.erp.entity.Goods;
/**
 * 商品action
 * @author [author]
 *
 */
public class GoodsAction extends BaseAction<Goods> {

	private IGoodsBiz goodsBiz;

	public void setGoodsBiz(IGoodsBiz goodsBiz) {
		this.goodsBiz = goodsBiz;
		super.setBaseBiz(this.goodsBiz);
	}
}
