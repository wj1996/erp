package com.wj.erp.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.interfaces.IStoredetailBiz;
import com.wj.erp.dao.interfaces.IBaseDao;
import com.wj.erp.dao.interfaces.IGoodsDao;
import com.wj.erp.dao.interfaces.IStoreDao;
import com.wj.erp.dao.interfaces.IStoredetailDao;
import com.wj.erp.entity.PageBean;
import com.wj.erp.entity.Storedetail;
/**
 * 仓库库存业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class StoredetailBizImpl extends BaseBizImpl<Storedetail> implements IStoredetailBiz{
	
	private IStoredetailDao storedetailDao;
	private IStoreDao storeDao;
	private IGoodsDao goodsDao;
	
	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
		super.setBaseDao(storedetailDao);
	}

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	/**
	 * 重写分页方法（为了查询出仓库名称和商品名称）
	 */
	@Override
	public PageBean<Storedetail> getListByPage(Storedetail t1, Storedetail t2, Object param, Integer page,
			Integer rows) {
		PageBean<Storedetail> pb = super.getListByPage(t1, t2, param, page, rows);
		Map<Long,String> goodsNameMap = new HashMap<>();
		Map<Long,String> storeNameMap = new HashMap<>();
		
		for(Storedetail sd : pb.getList()) {
			sd.setGoodsName(getGoodsName(sd.getGoodsuuid(),goodsNameMap));
			sd.setStoreName(getStoreName(sd.getStoreuuid(),storeNameMap));
		}
		
		return pb;
	}
	
	public String getGoodsName(Long uuid,Map<Long,String> goodsNameMap) {
		if(null == uuid) {
			return null;
		}
		
		String goodsName = goodsNameMap.get(uuid);
		if(null == goodsName) {
			goodsName = goodsDao.getById(uuid).getName();
			goodsNameMap.put(uuid, goodsName);
		}
		
		return goodsName;
	}
	
	public String getStoreName(Long uuid,Map<Long,String> storeNameMap) {
		if(null == uuid) {
			return null;
		}
		
		String storeName = storeNameMap.get(uuid);
		if(null == storeName) {
			storeName = storeDao.getById(uuid).getName();
			storeNameMap.put(uuid, storeName);
		}
		
		return storeName;
	}
	
}
