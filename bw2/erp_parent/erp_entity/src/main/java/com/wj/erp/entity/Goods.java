package com.wj.erp.entity;

import java.io.Serializable;

/**
 * 商品
 * @author [author]
 *
 */
public class Goods implements Serializable{

	private Long uuid; //编号
	private String name; //名称
	private String origin; //产地
	private String producer; //厂家
	private String unit; //计量单位
	private Double inprice; //进货价格
	private Double outprice; //销售价格
	/*private Long goodstypeuuid; //商品类型
*/	
	private Goodstype goodsType;
	
	
	public Goodstype getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Goodstype goodsType) {
		this.goodsType = goodsType;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getInprice() {
		return inprice;
	}
	public void setInprice(Double inprice) {
		this.inprice = inprice;
	}
	public Double getOutprice() {
		return outprice;
	}
	public void setOutprice(Double outprice) {
		this.outprice = outprice;
	}
	
	
	/*public Long getGoodstypeuuid() {
		return goodstypeuuid;
	}
	public void setGoodstypeuuid(Long goodstypeuuid) {
		this.goodstypeuuid = goodstypeuuid;
	}*/
	
}
