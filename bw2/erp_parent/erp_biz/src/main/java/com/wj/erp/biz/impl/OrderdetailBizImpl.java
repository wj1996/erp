package com.wj.erp.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.redsum.bos.ws.impl.IWaybillWs;
import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IOrderdetailBiz;
import com.wj.erp.common.Constant;
import com.wj.erp.dao.interfaces.IOrderdetailDao;
import com.wj.erp.dao.interfaces.IStoredetailDao;
import com.wj.erp.dao.interfaces.IStoreoperDao;
import com.wj.erp.dao.interfaces.ISupplierDao;
import com.wj.erp.entity.Orderdetail;
import com.wj.erp.entity.Orders;
import com.wj.erp.entity.Storedetail;
import com.wj.erp.entity.Storeoper;
import com.wj.erp.entity.Supplier;
/**
 * 订单明细业务逻辑层
 * @author [author]
 *
 */
@Transactional
public class OrderdetailBizImpl extends BaseBizImpl<Orderdetail> implements IOrderdetailBiz{
	
	private IOrderdetailDao orderdetailDao;
	private IStoredetailDao storedetailDao;
	private IStoreoperDao storeoperDao;
	private IWaybillWs waybillWs;
	private ISupplierDao supplierDao;
	
	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setWaybillWs(IWaybillWs waybillWs) {
		this.waybillWs = waybillWs;
	}
	
	public void setOrderdetailDao(IOrderdetailDao orderdetailDao) {
		this.orderdetailDao = orderdetailDao;
		super.setBaseDao(orderdetailDao);
	}
	
	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
	}

	public void setStoreoperDao(IStoreoperDao storeoperDao) {
		this.storeoperDao = storeoperDao;
	}

	@Override
	public void doInStore(Long uuid, Long storeuuid, Long empuuid) {
		Orderdetail orderdetail = orderdetailDao.getById(uuid);
		//判断明细状态，一定是未入库的
		if(!Constant.STATE_NOT_IN.equals(orderdetail.getState())) {
			throw new ErpException("不能重复入库");
		}
		orderdetail.setState(Constant.STATE_IN);
		orderdetail.setEnder(empuuid);
		orderdetail.setStoreuuid(storeuuid);
		orderdetail.setEndtime(new Date());
		
		//检查是否存在库存信息
		Storedetail storedetail = new Storedetail();
		storedetail.setGoodsuuid(orderdetail.getGoodsuuid());
		storedetail.setStoreuuid(orderdetail.getStoreuuid());
		List<Storedetail> list = storedetailDao.getList(storedetail, null, null);
		if(null != list && list.size() > 0) {
			list.get(0).setNum(list.get(0).getNum() == null ? 0 : list.get(0).getNum() + (orderdetail.getNum() == null ? 0 : orderdetail.getNum()));
		}else {
			storedetail.setNum(orderdetail.getNum());
			storedetailDao.add(storedetail);
		}
		
		//构建操作记录
		Storeoper oper = new Storeoper();
		oper.setEmpuuid(empuuid);
		oper.setGoodsuuid(orderdetail.getGoodsuuid());
		oper.setNum(orderdetail.getNum());
		oper.setOpertime(orderdetail.getEndtime());
		oper.setStoreuuid(storedetail.getUuid());
		oper.setType(Constant.STOREOPER_TYPE_IN);
		storeoperDao.add(oper);
		
		//查询订单下是否还存在状态为0的明细
		Orderdetail queryParam = new Orderdetail();
		Orders orders = orderdetail.getOrders();
		queryParam.setOrders(orders);
		queryParam.setState(Constant.STATE_NOT_IN);
		Integer count = orderdetailDao.getCount(queryParam, null, null);
		if(count == 0) {
			//所有订单明细都入库了
			orders.setState(Constant.STATE_IN);
			orders.setEndtime(orderdetail.getEndtime());
			orders.setEnder(empuuid);
		}
	}

	@Override
	public void doOutStore(Long uuid, Long storeuuid, Long empuuid) {
		Orderdetail orderdetail = orderdetailDao.getById(uuid);
		
		if(!Constant.STATE_NOT_OUT.equals(orderdetail.getState())) {
			throw new ErpException("亲！该明细已经出库了，不能重复出库");
		}
		
		orderdetail.setState(Constant.STATE_OUT);
		orderdetail.setEnder(empuuid);
		orderdetail.setStoreuuid(storeuuid);
		orderdetail.setEndtime(new Date());
		
		//检查是否存在库存信息
		Storedetail storedetail = new Storedetail();
		storedetail.setGoodsuuid(orderdetail.getGoodsuuid());
		storedetail.setStoreuuid(orderdetail.getStoreuuid());
		List<Storedetail> list = storedetailDao.getList(storedetail, null, null);
		if(null != list && list.size() > 0) {
			
			Storedetail sd = list.get(0);
			sd.setNum(sd.getNum() - orderdetail.getNum());
			if(sd.getNum() < 0) {
				throw new ErpException("库存不足");
			}
//			storedetailDao.update(sd);
		}else {
			throw new ErpException("库存不足！");
		}
		
		//构建操作记录
		Storeoper oper = new Storeoper();
		oper.setEmpuuid(empuuid);
		oper.setGoodsuuid(orderdetail.getGoodsuuid());
		oper.setNum(orderdetail.getNum());
		oper.setOpertime(orderdetail.getEndtime());
		oper.setStoreuuid(storedetail.getUuid());
		oper.setType(Constant.STOREOPER_TYPE_IN);
		storeoperDao.add(oper);
		
		//查询订单下是否还存在状态为0的明细
		Orderdetail queryParam = new Orderdetail();
		Orders orders = orderdetail.getOrders();
		queryParam.setOrders(orders);
		queryParam.setState(Constant.ORDERS_STATE_NOT_OUT);
		Integer count = orderdetailDao.getCount(queryParam, null, null);
		if(count == 0) {
			//所有订单明细都出库了
			orders.setState(Constant.STATE_OUT);
			orders.setEndtime(orderdetail.getEndtime());
			orders.setEnder(empuuid);
			//客户
			Supplier supplier = supplierDao.getById(orders.getSupplieruuid());
			//获取订单号
			Long waybillsn = waybillWs.addWaybill(1L, supplier.getAddress(), supplier.getContact(), supplier.getTele(), "--");
			//更新订单号
			orders.setWaybillsn(waybillsn);
		}
		
		
	}

	

}
