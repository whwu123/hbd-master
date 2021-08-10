package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("lc_users_bank")
@Getter
@Setter
public class UsersBankEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;

	
	@TableField("state")
	private int state;
	
	@TableField("name")
	private String name;
	
	@TableField("card")
	private String card;
	
	@TableField("opening")
	private String opening;
	
	@TableField("user_id")
	private String userId;
	
}
