package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("commodity")
@Getter
@Setter
public class CommodityEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;

	@TableField("state")
	private int state;
	
	@TableField("name")
	private String name;
	
	@TableField("type")
	private String type;
	
	@TableField("price")
	private String price;
	
	@TableField("repertory")
	private int repertory;
	
	@TableField("sales")
	private int sales;
	
	@TableField("introduction")
	private String introduction;
	
	@TableField("thumbnail")
	private String thumbnail;
	
	@TableField("picture")
	private String picture;
	
	@TableField("freight")
	private String freight;
	
	@TableField("specification")
	private String specification;
	
	@TableField("coupon_id")
	private String couponId;
	
	@TableField("particulars")
	private String particulars;
	
	@TableField("cartype_id")
	private String cartypeId;
	
	@TableField("gongli_number")
	private int gongliNumber;
}
