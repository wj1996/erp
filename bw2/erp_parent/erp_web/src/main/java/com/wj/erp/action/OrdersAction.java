package com.wj.erp.action;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Orderdetail;
import com.wj.erp.entity.Orders;
/**
 * 订单action
 * @author [author]
 *
 */
public class OrdersAction extends BaseAction<Orders> {

	private IOrdersBiz ordersBiz;

	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
		super.setBaseBiz(this.ordersBiz);
	}
	//接收订单明细的json字符串，数组形式额json字符串
	private String json;

	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	/**
	 * 添加订单
	 */
	public void add() {
//		System.out.println(json);
//		System.out.println(list);
		try {
			Orders orders = getT();
			Emp loginUser = getLoginUser();
			if(null == loginUser) {
				//用户没有登陆，或者session已经失效
				ajaxReturn(false,"请登陆！");
				return;
			}
			List<Orderdetail> list = JSON.parseArray(json,Orderdetail.class);
			//订单创建者
			orders.setCreater(loginUser.getUuid());
			orders.setOrderDetails(list);
			ordersBiz.add(orders);
			ajaxReturn(true,"添加订单成功！");
		} catch (Exception e) {
			ajaxReturn(false,"添加订单失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 采购订单审核
	 */
	public void doCheck() {
		Emp loginUser = getLoginUser();
		if(null == loginUser) {
			ajaxReturn(false,"请登陆！");
			return;
		}
		try {
			ordersBiz.doCheck(getId(), loginUser.getUuid());
			ajaxReturn(false,"审核成功！");
		} catch(ErpException e) {
			ajaxReturn(false,e.getMessage());
		}catch (Exception e) {
			ajaxReturn(false,"审核失败！");
			e.printStackTrace();
		}
	}
}
