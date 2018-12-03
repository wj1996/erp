package com.wj.erp.biz.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IGoodstypeBiz;
import com.wj.erp.dao.interfaces.IGoodstypeDao;
import com.wj.erp.entity.Goodstype;
/**
 * 商品分类业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class GoodstypeBizImpl extends BaseBizImpl<Goodstype> implements IGoodstypeBiz{
	
	private IGoodstypeDao goodstypeDao;
	
	public void setGoodstypeDao(IGoodstypeDao goodstypeDao) {
		this.goodstypeDao = goodstypeDao;
		super.setBaseDao(goodstypeDao);
	}

	

}
