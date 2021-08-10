package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("coupon_grant")
@Getter
@Setter
public class CouponGrantEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;
	
	/**
	 * 状态 （-1 删除  0正常）
	 */
	@TableField("state")
	private int state;
	
	/**
	 * 0注册新用户，1购买并付款
	 */
	@TableField("disbursement")
	private int disbursement;
	
	@TableField("number")
	private int number;
	

	@TableField("coupom_id")
	private String coupomId;

}
