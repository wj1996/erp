package com.wj.erp.biz.interfaces;

import java.io.OutputStream;
import java.util.List;

import com.redsum.bos.ws.Waybilldetail;
import com.wj.erp.entity.Orders;

/**
 * 订单业务逻辑层
 * @author [author]
 *
 */
public interface IOrdersBiz extends IBaseBiz<Orders>{

	/**
	 * 审核
	 * @param uuid（订单编号）
	 * @param empUuid（员工编号）
	 */
	void doCheck(Long uuid,Long empUuid);
	
	/**
	 * 确认
	 * @param uuid（订单编号）
	 * @param empUuid（采购员工编号）
	 */
	void doStart(Long uuid,Long empUuid);
	
	/**
	 * 导出订单
	 * @param os
	 */
	void export(OutputStream os,Long uuid);
	
	/**
	 * 根据运单号查询运单详情
	 * @param sn
	 * @return
	 */
	List<Waybilldetail> waybilldetailList(Long sn);
	
}
