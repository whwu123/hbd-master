package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Getter;
import lombok.Setter;


@TableName("sales_return")
@Getter
@Setter
public class SalesReturnEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3540862564325838838L;
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
	

}
