package com.active4j.hr.yucai.service;

import com.active4j.hr.core.web.tag.model.tree.TSDepartTreeData;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.yucai.entity.YcLocalEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YcLocalService extends IService<YcLocalEntity> {
	

	/**
	 * 
	 * @description
	 *  	获取所有顶级部门
	 * @return List<SysDeptEntity>
	 * @author 麻木神
	 * @time 2020年1月28日 下午3:13:14
	 */
	public List<YcLocalEntity> getParentDepts();
	
	
	/**
	 * @description
	 *  	根据部门ID  获取子部门
	 * @return List<SysDeptEntity>
	 * @author 麻木神
	 * @time 2020年1月28日 下午3:35:12
	 */
	public List<YcLocalEntity> getChildDeptsByDeptId(String deptId);
	
	/**
	 * 
	 * @description
	 *  	表格的树形显示
	 * @return List<TSDepartTreeData>
	 * @author 麻木神
	 * @time 2020年1月29日 下午4:15:50
	 */
	public List<TSDepartTreeData> getTreeDepartList();
	
	/**
	 * 
	 * @description
	 *  	根据部门id获取用户
	 * @params
	 * @return List<SysUserEntity>
	 * @author guyp
	 * @time 2020年4月9日 下午4:27:12
	 */
	public List<SysUserEntity> getUsersByDept(String deptId);

	public List<YcLocalEntity> selectFormJiaoYuId(String quXianId,String jiaoYuId,String type);

}
