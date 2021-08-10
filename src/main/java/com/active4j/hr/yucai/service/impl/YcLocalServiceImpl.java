package com.active4j.hr.yucai.service.impl;

import com.active4j.hr.core.web.tag.model.tree.TSDepartTreeData;


import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yucai.dao.YcLocalDao;
import com.active4j.hr.yucai.entity.YcLocalEntity;
import com.active4j.hr.yucai.service.YcLocalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 部门管理service类
 * 
 * @author teli_
 *
 */
@Service("ycLocalService")
@Transactional
public class YcLocalServiceImpl extends ServiceImpl<YcLocalDao, YcLocalEntity> implements YcLocalService {


	@Autowired
	private SysUserService sysUserService;

	private  YcLocalDao ycLocalDao;

	/**
	 * 
	 * @description 获取所有顶级部门
	 * @return List<SysDeptEntity>
	 * @author 麻木神
	 * @time 2020年1月28日 下午3:13:14
	 */
	public List<YcLocalEntity> getParentDepts() {
		// 先查父级菜单 注意排序
		QueryWrapper<YcLocalEntity> queryWrapper = new QueryWrapper<YcLocalEntity>();
		queryWrapper.isNull("PARENT_ID");
		queryWrapper.orderByAsc("LEVEL");
		List<YcLocalEntity> lstDeparts = this.list(queryWrapper);

		return lstDeparts;
	}

	/**
	 * @description 根据部门ID 获取子部门
	 * @return List<SysDeptEntity>
	 * @author 麻木神
	 * @time 2020年1月28日 下午3:35:12
	 */
	public List<YcLocalEntity> getChildDeptsByDeptId(String deptId) {
		QueryWrapper<YcLocalEntity> queryWrapper = new QueryWrapper<YcLocalEntity>();
		queryWrapper.eq("PARENT_ID", deptId);
		queryWrapper.orderByAsc("LEVEL");
		List<YcLocalEntity> lstDeparts = this.list(queryWrapper);

		return lstDeparts;

	}

	/**
	 * 
	 * @description 表格的树形显示
	 * @return List<TSDepartTreeData>
	 * @author 麻木神
	 * @time 2020年1月29日 下午4:15:50
	 */
	public List<TSDepartTreeData> getTreeDepartList() {
		// 生成树形表格菜单的集合
		List<TSDepartTreeData> lstDatas = new ArrayList<TSDepartTreeData>();
		
		// 先查父级菜单 注意排序
		List<YcLocalEntity> lstDeparts = getParentDepts();
		
		//递归显示所有部门
		getTSDepartTreeList(null, lstDeparts, lstDatas);
		
		return lstDatas;
	}

	private void getTSDepartTreeList(YcLocalEntity parentDepart, List<YcLocalEntity> lstChildren, List<TSDepartTreeData> lstTreeData) {
		if (null != lstChildren && lstChildren.size() >= 0) {
			// 这里集合要进行排序
			Collections.sort(lstChildren, (s1, s2) -> s1.getLevel() - s2.getLevel());
			for (YcLocalEntity dept : lstChildren) {
				TSDepartTreeData treeData = new TSDepartTreeData();
				treeData.setId(dept.getId());
				treeData.setDepartName(dept.getName());
				treeData.setDepartNo(dept.getDeptNo());
				treeData.setType(dept.getType());
				treeData.setExpanded(false);
				treeData.setDescription(dept.getDescription());
				treeData.setLoaded(true);
				if (null == parentDepart) {
					treeData.setParentId(null);
				} else {
					treeData.setParentId(parentDepart.getId());
				}

				treeData.setLevel(String.valueOf(dept.getLevel())); // 层次
				// 获取子菜单
				List<YcLocalEntity> lstChildren2 = getChildDeptsByDeptId(dept.getId());
				
				if (null != lstChildren2 && lstChildren2.size() > 0) {
					treeData.setLeaf(false);
				} else {
					treeData.setLeaf(true);
				}
				lstTreeData.add(treeData);

				getTSDepartTreeList(dept, lstChildren2, lstTreeData);

			}
		}
	}
	
	/**
	 * 
	 * @description
	 *  	根据部门id获取用户
	 * @params
	 * @return List<SysUserEntity>
	 * @author guyp
	 * @time 2020年4月9日 下午4:27:12
	 */
	public List<SysUserEntity> getUsersByDept(String deptId) {
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
		queryWrapper.eq("DEPT_ID", deptId);
		List<SysUserEntity> lstUsers = sysUserService.list(queryWrapper);
		
		return lstUsers;
	}

	@Override
	public List<YcLocalEntity> selectFormJiaoYuId(String quXianId, String jiaoYuId,String type) {
		List<YcLocalEntity> lstLocal = this.baseMapper.selectFormJiaoYuId(quXianId,jiaoYuId,type);

		return lstLocal;
	}
}
