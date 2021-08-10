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
import com.active4j.hr.longche.entity.CommodityEntity;
import com.active4j.hr.longche.entity.CommodityTypeEntity;
import com.active4j.hr.longche.entity.CouponEntity;
import com.active4j.hr.longche.entity.MaintenanceEntity;
import com.active4j.hr.longche.entity.MellSettingEntity;
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.CouponService;
import com.active4j.hr.longche.service.MaintenanceService;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		return "longche/coupon/index";
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
	public void datagrid(CouponEntity couponEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<CouponEntity> queryWrapper = QueryUtils.installQueryWrapper(couponEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<CouponEntity> lstResult = couponService.page(new Page<CouponEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(CouponEntity couponEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/coupon/sales");
		if(StringUtils.isEmpty(couponEntity.getId())) {
			//新增
			couponEntity = new CouponEntity();
			view.addObject("coupon", couponEntity);
		}else {
			couponEntity = couponService.getById(couponEntity.getId());
			view.addObject("coupon", couponEntity);
		}
		return view;
	}
	@RequestMapping("/update")
	public ModelAndView update(String id, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/coupon/sales");
		CouponEntity couponEntity = couponService.getById(id);
		view.addObject("coupon", couponEntity);
		return view;
	}
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存优惠卷信息", memo = "新增或编辑优惠卷信息")
	public AjaxJson saveSales(HttpServletRequest req, CouponEntity couponEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(couponEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("优惠卷名称为空");
				return j;
			}
			
			
			if(StringUtils.isEmpty(couponEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				couponEntity.setCreateDate(new Date());
				couponEntity.setUpdateDate(new Date());
				couponEntity.setState(1);
				if(couponEntity.getPreferentialWay()==1) {
					couponEntity.setNumber(0);
				}else if(couponEntity.getPreferentialWay()==0) {
					couponEntity.setPreferentialWay(0);
				}
				couponService.save(couponEntity);
			}else {
				//编辑保存
				CouponEntity tmp = couponService.getById(couponEntity.getId());
				if(couponEntity.getPreferentialWay()==1) {
					couponEntity.setNumber(0);
				}else if(couponEntity.getPreferentialWay()==0) {
					couponEntity.setPreferentialWay(0);
				}
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(couponEntity, tmp);
				couponService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存优惠卷信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存用户错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除优惠卷信息", memo = "删除了优惠卷信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的优惠卷");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//删除用户
		 	couponService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的优惠卷信息");
		}catch(Exception e) {
			log.error("删除优惠卷信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除优惠卷信息错误");
			e.printStackTrace();
		}
		return j;
	}
}
