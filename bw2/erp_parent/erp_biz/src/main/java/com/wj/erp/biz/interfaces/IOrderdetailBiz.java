package com.wj.erp.biz.interfaces;

import com.wj.erp.entity.Orderdetail;

/**
 * 订单明细业务逻辑层
 * @author [author]
 *
 */
public interface IOrderdetailBiz extends IBaseBiz<Orderdetail>{

	/**
	 * 入库
	 * @param uuid 明细编号
	 * @param storeuuid 仓库编号
	 * @param empuuid 库管员编号
	 */
	void doInStore(Long uuid,Long storeuuid,Long empuuid);
}
