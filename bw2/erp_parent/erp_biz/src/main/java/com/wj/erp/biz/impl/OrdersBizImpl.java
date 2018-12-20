package com.wj.erp.biz.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.common.Constant;
import com.wj.erp.dao.interfaces.IEmpDao;
import com.wj.erp.dao.interfaces.IOrdersDao;
import com.wj.erp.dao.interfaces.ISupplierDao;
import com.wj.erp.entity.Orderdetail;
import com.wj.erp.entity.Orders;
import com.wj.erp.entity.PageBean;

/**
 * 订单业务逻辑层
 * 
 * @author [author]
 *
 */
@Transactional
public class OrdersBizImpl extends BaseBizImpl<Orders> implements IOrdersBiz {

	private IOrdersDao ordersDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao(ordersDao);
	}

	@Override
	public void add(Orders orders) {
		orders.setState(Constant.STATE_CREATE);
		orders.setType(Constant.TYPE_IN);
		orders.setCreatetime(new Date());

		double total = 0;
		for (Orderdetail detail : orders.getOrderDetails()) {
			total += detail.getMoney();
			// 明细的状态
			detail.setState(Constant.STATE_NOT_IN);
			// 设置跟订单的关系
			detail.setOrders(orders);
		}
		// 设置订单总金额
		orders.setTotalmoney(total);
		ordersDao.add(orders);
	}

	@Override
	public PageBean<Orders> getListByPage(Orders t1, Orders t2, Object param, Integer page, Integer rows) {
		PageBean<Orders> pageBean = super.getListByPage(t1, t2, param, page, rows);

		// 缓存员工编号和员工的名称
		Map<Long, String> empNameMap = new HashMap<>();
		// 缓存供应商编号和员工的名称
		Map<Long, String> supplierNameMap = new HashMap<>();
		// 循环获取员工的名称
		for (Orders order : pageBean.getList()) {
			order.setCreaterName(getEmpName(order.getCreater(), empNameMap));
			order.setCheckerName(getEmpName(order.getChecker(), empNameMap));
			order.setStarterName(getEmpName(order.getStarter(), empNameMap));
			order.setEnderName(getEmpName(order.getEnder(), empNameMap));
			// 供应商
			order.setSupplierName(getSupplierName(order.getSupplieruuid(), supplierNameMap));
		}

		return pageBean;
	}

	/**
	 * 获取员工名称
	 * 
	 * @param uuid员工编号
	 * @param empNameMap
	 * @return 返回员工名称
	 */
	private String getEmpName(Long uuid, Map<Long, String> empNameMap) {
		if (uuid == null) {
			return null;
		}
		String empName = empNameMap.get(uuid);
		if (null == empName) {
			// 如果缓存中没有
			empName = empDao.getById(uuid).getUsername();
			// 放入缓存
			empNameMap.put(uuid, empName);
		}
		return empName;
	}

	/**
	 * 获取供应商名称
	 * 
	 * @param uuid供应商编号
	 * @param supplierNameMap
	 * @return 返回供应商名称
	 */
	private String getSupplierName(Long uuid, Map<Long, String> supplierNameMap) {
		if (uuid == null) {
			return null;
		}
		String supplierName = supplierNameMap.get(uuid);
		if (null == supplierName) {
			// 如果缓存中没有
			supplierName = supplierDao.getById(uuid).getName();
			// 放入缓存
			supplierNameMap.put(uuid, supplierName);
		}
		return supplierName;
	}

	/**
	 * 审核
	 */
	@Override
	public void doCheck(Long uuid, Long empUuid) {
		// 获取订单，进入持久化状态
		Orders order = ordersDao.getById(uuid);
		if (!Constant.STATE_CREATE.equals(order.getState())) {
			throw new ErpException("亲！订单已经审核过了");
		}
		// 设置订单的状态
		order.setState(Constant.STATE_CHECK);
		// 审核时间
		order.setChecktime(new Date());
		// 审核人
		order.setChecker(empUuid);

	}

	@Override
	public void doStart(Long uuid, Long empUuid) {
		// 获取订单，进入持久化状态
		Orders order = ordersDao.getById(uuid);
		if (!Constant.STATE_CHECK.equals(order.getState())) {
			throw new ErpException("亲！订单已经确认过了");
		}
		// 设置订单的状态
		order.setState(Constant.STATE_START);
		// 审核时间
		order.setStarttime(new Date());
		// 确认人
		order.setStarter(empUuid);
	}

}
