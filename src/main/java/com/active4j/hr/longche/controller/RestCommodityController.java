package com.active4j.hr.longche.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.longche.entity.CommodityEntity;
import com.active4j.hr.longche.entity.CommodityTypeEntity;
import com.active4j.hr.longche.service.CommodityService;
import com.active4j.hr.longche.service.CommodityTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/rest/commodity")
public class RestCommodityController {
	
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private CommodityTypeService commodityTypeService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson list() {
		AjaxJson j = new AjaxJson();
		QueryWrapper<CommodityEntity> queryWrapper = new QueryWrapper<CommodityEntity>();
		queryWrapper.eq("state", 1);
		List<CommodityEntity> list = commodityService.list(queryWrapper);
		j.setObj(list);
		return j;
	}

	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getById(String id) {
		AjaxJson j = new AjaxJson();
		if(id!=null && !id.isEmpty()) {
			CommodityEntity m = commodityService.getById(id);
			j.setObj(m);
		}
		return j;
	}
	
	@RequestMapping(value = "/type/list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson typeList() {
		AjaxJson j = new AjaxJson();
		QueryWrapper<CommodityTypeEntity> queryWrapper = new QueryWrapper<CommodityTypeEntity>();
		queryWrapper.eq("state", 1);
		List<CommodityTypeEntity> list = commodityTypeService.list(queryWrapper);
		j.setObj(list);
		return j;
	}

	
	@RequestMapping(value = "/type/getById", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson typeGetById(String id) {
		AjaxJson j = new AjaxJson();
		if(id!=null && !id.isEmpty()) {
			CommodityTypeEntity m = commodityTypeService.getById(id);
			j.setObj(m);
		}
		return j;
	
	}
	
	
}
