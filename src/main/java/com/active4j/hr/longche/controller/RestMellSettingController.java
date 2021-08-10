package com.active4j.hr.longche.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.longche.entity.MaintenanceEntity;
import com.active4j.hr.longche.entity.MellSettingEntity;
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.MellSettingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/rest/mell")
public class RestMellSettingController {
	
	@Autowired
	private MellSettingService mellSettingService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson list() {
		AjaxJson j = new AjaxJson();
		QueryWrapper<MellSettingEntity> queryWrapper = new QueryWrapper<MellSettingEntity>();
		queryWrapper.eq("state", 1);
		List<MellSettingEntity> list = mellSettingService.list(queryWrapper);
		j.setObj(list);
		return j;
	}

	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getById(String id) {
		AjaxJson j = new AjaxJson();
		if(id!=null && !id.isEmpty()) {
			MellSettingEntity m = mellSettingService.getById(id);
			j.setObj(m);
		}
		return j;
	}
	
}
