package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("lc_users")
@Getter
@Setter
public class UsersEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;

	
	@TableField("state")
	private int state;
	
	@TableField("name")
	private String name;
	
	@TableField("phone")
	private String phone;
	
	@TableField("department")
	private String department;
	
	@TableField("remark")
	private String remark;
	
	@TableField("rank")
	private String rank;
	
	@TableField("order_num")
	private int orderNum;
	
	@TableField("coupon_num")
	private int couponNum;
	
	@TableField("integral")
	private String integral;
	
	@TableField("balance")
	private String balance;
	
	@TableField("open_id")
	private String openId;
	
}
