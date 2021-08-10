package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("maintenance")
@Getter
@Setter
public class MaintenanceEntity extends BaseEntity{
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
	 * 地址
	 */
	@TableField("address")
	private String address;
	
	/**
	 * 名称
	 */
	@TableField("name")
	private String name;
	
	/**
	 * 联系方式
	 */
	@TableField("phone")
	private String phone;
	
}
