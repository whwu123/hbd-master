package com.active4j.hr.longche.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.active4j.hr.activiti.entity.WorkflowMngEntity;
import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.ListUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.entity.CommodityEntity;
import com.active4j.hr.longche.entity.CommodityTypeEntity;
import com.active4j.hr.longche.entity.CouponEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.longche.service.CommodityService;
import com.active4j.hr.longche.service.CommodityTypeService;
import com.active4j.hr.longche.service.CouponService;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/commodity")
public class CommodityController {
	
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private CommodityTypeService commodityTypeService;
	
	@Autowired
	private CouponService couponService;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "longche/commodity/index_type";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		QueryWrapper<CommodityTypeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("state", 1);
		List<CommodityTypeEntity> commodityTypeEntities = commodityTypeService.list(queryWrapper);
		model.addAttribute("commodityTypeReplace", ListUtils.listToReplaceStr(commodityTypeEntities, "name", "id"));
		return "longche/commodity/list";
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
	public void datagrid(CommodityEntity commodityEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<CommodityEntity> queryWrapper = QueryUtils.installQueryWrapper(commodityEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<CommodityEntity> lstResult = commodityService.page(new Page<CommodityEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(CommodityEntity commodityEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/commodity/add");
		QueryWrapper<CouponEntity> queryWrapperCoupon = new QueryWrapper<>();
		queryWrapperCoupon.eq("state", 1);
		
		List<CouponEntity> couponEntities = couponService.list(queryWrapperCoupon);
		view.addObject("couponEntities", couponEntities);
		 
		QueryWrapper<CommodityTypeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("state", 1);
		List<CommodityTypeEntity> commodityTypeEntities = commodityTypeService.list(queryWrapper);
		if(StringUtils.isEmpty(commodityEntity.getId())) {
			//新增
			commodityEntity = new CommodityEntity();
			commodityEntity.setState(1);
			view.addObject("commodity", commodityEntity);
		}else {
			commodityEntity = commodityService.getById(commodityEntity.getId());
			view.addObject("commodity", commodityEntity);
		}
		view.addObject("commodityTypeEntities", commodityTypeEntities);
		return view;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(String id, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/commodity/add");
		QueryWrapper<CommodityTypeEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("state", 1);
		List<CommodityTypeEntity> commodityTypeEntities = commodityTypeService.list(queryWrapper);
		view.addObject("commodityTypeEntities", commodityTypeEntities);
		
		CommodityEntity	commodityEntity = commodityService.getById(id);
		view.addObject("commodity", commodityEntity);
		return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存商品信息", memo = "新增或编辑商品信息")
	public AjaxJson saveSales(HttpServletRequest req, CommodityEntity commodityEntity) {
		AjaxJson j = new AjaxJson();
		try {
			/*if(StringUtils.isEmpty(couponEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("优惠卷发放名称为空");
				return j;
			}*/
			
			
			if(StringUtils.isEmpty(commodityEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				commodityEntity.setCreateDate(new Date());
				commodityEntity.setUpdateDate(new Date());
				commodityEntity.setState(1);
				commodityService.save(commodityEntity);
			}else {
				//编辑保存
				CommodityEntity tmp = commodityService.getById(commodityEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(commodityEntity, tmp);
				commodityService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存商品信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存商品错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除商品信息", memo = "删除了商品信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的商品");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			commodityService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的商品信息");
		}catch(Exception e) {
			log.error("删除商品信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除商品信息错误");
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(value = "/shangjia/{modelId}")
	@ResponseBody
	public AjaxJson shangjia(@PathVariable("modelId") String modelId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			if(modelId!= null && !modelId.isEmpty()) {
				CommodityEntity commodityEntity = commodityService.getById(modelId);
				commodityEntity.setState(1);
				commodityService.saveOrUpdate(commodityEntity);
				j.setMsg("上架商品成功");;
			}
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(GlobalConstant.ERROR_MSG);
			log.error("商品上架状态修改失败，错误信息:{}", e);
		}
		return j;
	}
	@RequestMapping(value = "/xiajia/{modelId}")
	@ResponseBody
	public AjaxJson xiajia(@PathVariable("modelId") String modelId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			if(modelId!= null && !modelId.isEmpty()) {
				CommodityEntity commodityEntity = commodityService.getById(modelId);
				commodityEntity.setState(0);
				commodityService.saveOrUpdate(commodityEntity);
				j.setMsg("下架商品成功");;
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(GlobalConstant.ERROR_MSG);
			log.error("商品下架状态修改失败，错误信息:{}", e);
		}
		return j;
	}
}
