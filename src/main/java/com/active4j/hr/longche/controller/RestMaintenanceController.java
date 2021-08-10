package com.active4j.hr.longche.controller;


import java.util.Date;
import java.util.List;

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
@RequestMapping("/rest/maintenance")
public class RestMaintenanceController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson list() {
		AjaxJson j = new AjaxJson();
		QueryWrapper<MaintenanceEntity> queryWrapper = new QueryWrapper<MaintenanceEntity>();
		queryWrapper.eq("state", 1);
		List<MaintenanceEntity> list = maintenanceService.list(queryWrapper);
		j.setObj(list);
		return j;
	}

	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getById(String id) {
		AjaxJson j = new AjaxJson();
		if(id!=null && !id.isEmpty()) {
			MaintenanceEntity m = maintenanceService.getById(id);
			j.setObj(m);
		}
		return j;
	}
	
}
