package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("commodity_type")
@Getter
@Setter
public class CommodityTypeEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;

	@TableField("state")
	private int state;
	
	@TableField("name")
	private String name;
	
	
	
}
