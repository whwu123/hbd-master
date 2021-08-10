package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("coupon")
@Getter
@Setter
public class CouponEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;
	
	/**
	 * 状态 （-1 删除  0正常）
	 */
	@TableField("state")
	private int state;
	
	@TableField("name")
	private String name;
	
	@TableField("low_consumption")
	private String lowConsumption;
	
	@TableField("valid_time")
	private Integer validTime;
	
	@TableField("number")
	private Integer number;
	
	@TableField("preferential_way")
	private Integer preferentialWay;
	
	@TableField("is_join")
	private String isJoin;
	
	
	@TableField("content")
	private String content;
	
	
	@TableField("coupon_money")
	private String couponMoney;

}
