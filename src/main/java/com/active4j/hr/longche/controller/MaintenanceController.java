package com.active4j.hr.longche.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.longche.entity.MaintenanceEntity;
import com.active4j.hr.longche.entity.MellSettingEntity;
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.MaintenanceService;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/maintenance")
public class MaintenanceController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		return "longche/maintenance/index";
	}

	/**
	 * 
	 * @description
	 *  	表格数据显示
	 * @return void
	 * @author 麻木神
	 * @time 2020年1月25日 下午9:46:12
	 */
	@RequestMapping("/datagrid")
	public void datagrid(MaintenanceEntity maintenanceEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<MaintenanceEntity> queryWrapper = QueryUtils.installQueryWrapper(maintenanceEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<MaintenanceEntity> lstResult = maintenanceService.page(new Page<MaintenanceEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(MaintenanceEntity maintenanceEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/maintenance/sales");
		if(StringUtils.isEmpty(maintenanceEntity.getId())) {
			//新增
			maintenanceEntity = new MaintenanceEntity();
			view.addObject("maintenance", maintenanceEntity);
		}else {
			//编辑
			maintenanceEntity = maintenanceService.getById(maintenanceEntity.getId());
			
			//salesReturnService.saveOrUpdate(salesReturnEntity);
			view.addObject("maintenance", maintenanceEntity);
		}
		return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存商城信息", memo = "新增或编辑保存了商城信息")
	public AjaxJson saveSales(HttpServletRequest req, MaintenanceEntity maintenanceEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(maintenanceEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("名称为空");
				return j;
			}
			
			if(StringUtils.isEmpty(maintenanceEntity.getPhone())) {
				j.setSuccess(false);
				j.setMsg("电话为空");
				return j;
			}
			
			if(StringUtils.isEmpty(maintenanceEntity.getAddress())) {
				j.setSuccess(false);
				j.setMsg("地址为空");
				return j;
			}
			
			if(StringUtils.isEmpty(maintenanceEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				maintenanceEntity.setCreateDate(new Date());
				maintenanceEntity.setUpdateDate(new Date());
				maintenanceEntity.setState("1");
				maintenanceService.save(maintenanceEntity);
			}else {
				//编辑保存
				MaintenanceEntity tmp = maintenanceService.getById(maintenanceEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(maintenanceEntity, tmp);
				maintenanceService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存用户信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存用户错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除维修厂信息", memo = "删除了维修厂信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的维修厂");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//删除用户
			maintenanceService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的维修厂信息");
		}catch(Exception e) {
			log.error("删除维修厂信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除维修厂信息错误");
			e.printStackTrace();
		}
		return j;
	}
}
