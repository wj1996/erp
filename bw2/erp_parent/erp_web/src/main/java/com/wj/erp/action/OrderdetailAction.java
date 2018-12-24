package com.wj.erp.action;

import com.wj.erp.biz.exception.ErpException;
import com.wj.erp.biz.interfaces.IOrderdetailBiz;
import com.wj.erp.entity.Emp;
import com.wj.erp.entity.Orderdetail;
/**
 * 订单明细action
 * @author [author]
 *
 */
public class OrderdetailAction extends BaseAction<Orderdetail> {

	private IOrderdetailBiz orderdetailBiz;

	public void setOrderdetailBiz(IOrderdetailBiz orderdetailBiz) {
		this.orderdetailBiz = orderdetailBiz;
		super.setBaseBiz(this.orderdetailBiz);
	}
	
	private Long storeuuid;
	
	public Long getStoreuuid() {
		return storeuuid;
	}

	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}

	/**
	 * 入库
	 */
	public void doInStore() {
		Emp loginUser = getLoginUser();
		if(null == loginUser) {
			//用户没有登录，session已失败
			ajaxReturn(false,"亲！您还没有登录");
			return;
		}
		try {
			orderdetailBiz.doInStore(getId(), storeuuid, loginUser.getUuid());
			ajaxReturn(true,"入库成功");
		} catch (ErpException e) {
			ajaxReturn(false,e.getMessage());
		}catch (Exception e) {
			ajaxReturn(false,"入库失败");
			e.printStackTrace();
		}
	}
	/**
	 * 出库
	 */
	public void doOutStore() {
		Emp loginUser = getLoginUser();
		if(null == loginUser) {
			//用户没有登录，session已失败
			ajaxReturn(false,"亲！您还没有登录");
			return;
		}
		try {
			//调用出库业务
			orderdetailBiz.doOutStore(getId(), storeuuid, loginUser.getUuid());
			ajaxReturn(true,"出库成功");
		} catch (ErpException e) {
			ajaxReturn(false,e.getMessage());
		}catch (Exception e) {
			ajaxReturn(false,"出库失败");
			e.printStackTrace();
		}
	}
}
