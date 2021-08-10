package com.active4j.hr.longche.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("article_lc")
@Getter
@Setter
public class ArticleEntity extends BaseEntity{
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
	
	@TableField("address")
	private String address;
	
	@TableField("phone")
	private String phone;
	
	@TableField("type")
	private int type;
	
	@TableField("content")
	private String content;
}
