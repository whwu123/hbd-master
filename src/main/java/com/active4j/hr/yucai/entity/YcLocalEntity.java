package com.active4j.hr.yucai.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @title SysDeptEntity.java
 * @description 
		  育才系统管理   地区理
 * @version 1.0
*/
@TableName("yc_local")
@Getter
@Setter
public class YcLocalEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3540862564325838898L;

	/**
	 * 编号
	 */
	@TableField("DEPT_NO")
	private String deptNo;
	
	/**
	 * 名称  全称
	 */
	@TableField("NAME")
	private String name;
	
	/**
	 * 类型  0：其他      1： 地区：     2： 区县
	 */
	@TableField("TYPE")
	private String type;
	
	/**
	 * 上级部门ID
	 */
	@TableField("PARENT_ID")
	private String parentId;
	
	/**
	 * 部门排序
	 */
	@TableField("LEVEL")
	private int level;
	
	/**
	 * 部门描述
	 */
	@TableField("DESCRIPTION")
	private String description;
	
	/**
	 * 备注
	 */
	@TableField("MEMO")
	private String memo;
}

