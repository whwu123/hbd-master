package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("mell_setting")
@Getter
@Setter
public class MellSettingEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;
	
	/**
	 * 状态 （-1 删除  0正常）
	 */
	@TableField("state")
	private String state;
	
	/**
	 * 退货地址
	 */
	@TableField("return_address")
	private String returnAddress;
	
	/**
	 * 姓名
	 */
	@TableField("name")
	private String name;
	
	/**
	 * 联系方式
	 */
	@TableField("phone")
	private String phone;
	
	/**
	 * 未支付超时时间
	 */
	@TableField("over_time")
	private int overTime;
	
	/**
	 * 收货时间
	 */
	@TableField("receiving_time")
	private int receivingTime;
	
	/**
	 * 售后时间
	 */
	@TableField("support_time")
	private int supportTime;
}
