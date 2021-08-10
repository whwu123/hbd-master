package com.active4j.hr.longche.entity;

import java.util.Date;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("lc_order")
@Getter
@Setter
public class OrderEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;


	@TableField("state")
	private int state;
	
	@TableField("shop_id")
	private String shopId;
	
	@TableField("money")
	private String money;
	
	@TableField("delivery_address")
	private String deliveryAddress;
	
	@TableField("pay_time")
	private Date payTime;
	
	@TableField("send_time")
	private Date sendTime;
	
	@TableField("evaluate")
	private int evaluate;
	
	@TableField("number")
	private int number;
}
