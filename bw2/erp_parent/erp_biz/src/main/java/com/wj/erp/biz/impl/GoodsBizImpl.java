package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IGoodsBiz;
import com.wj.erp.dao.interfaces.IGoodsDao;
import com.wj.erp.entity.Goods;
/**
 * 商品业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class GoodsBizImpl extends BaseBizImpl<Goods> implements IGoodsBiz{
	
	private IGoodsDao goodsDao;
	
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		super.setBaseDao(goodsDao);
	}

	

}
