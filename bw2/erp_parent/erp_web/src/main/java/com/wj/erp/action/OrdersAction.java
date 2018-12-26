package com.wj.erp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IOrdersBiz;
import com.wj.erp.common.Constant;
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
	
	/**
	 * 采购订单确认
	 */
	public void doStart() {
		Emp loginUser = getLoginUser();
		if(null == loginUser) {
			ajaxReturn(false,"请登陆！");
			return;
		}
		try {
			ordersBiz.doStart(getId(), loginUser.getUuid());
			ajaxReturn(false,"确认成功！");
		} catch(ErpException e) {
			ajaxReturn(false,e.getMessage());
		}catch (Exception e) {
			ajaxReturn(false,"确认失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 我的订单
	 */
	public void myListByPage() {
		if(null == getT1()) {
			setT1(new Orders());
		}
		Emp loginUser = getLoginUser();
		getT1().setCreater(loginUser.getUuid());
		super.getListByPage();
	}
	
	public void export() {
		
		String filename = "";
		if(Constant.TYPE_IN.equals(getT1().getType())) {
			filename = "采购订单";
		}
		if(Constant.TYPE_OUT.equals(getT1().getType())) {
			filename = "销售订单";
		}
		filename += "_" + getId() + ".xls";
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//设置输出流
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"iso-8859-1"));
			ordersBiz.export(response.getOutputStream(), getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
