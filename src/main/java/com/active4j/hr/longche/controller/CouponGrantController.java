package com.active4j.hr.longche.controller;


import java.util.ArrayList;
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
import com.active4j.hr.core.util.ListUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.hr.entity.OaHrJobEntity;
import com.active4j.hr.longche.entity.CouponEntity;
import com.active4j.hr.longche.entity.CouponGrantEntity;
import com.active4j.hr.longche.entity.MaintenanceEntity;
import com.active4j.hr.longche.entity.MellSettingEntity;
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.CouponGrantService;
import com.active4j.hr.longche.service.CouponService;
import com.active4j.hr.longche.service.MaintenanceService;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/couponGrant")
public class CouponGrantController {
	
	@Autowired
	private CouponGrantService couponGrantService;
	@Autowired
	private CouponService couponService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		//
		List<CouponEntity> list = couponService.list();
		model.addAttribute("couponReplace", ListUtils.listToReplaceStr(list, "name", "id"));
		return "longche/coupon/grant_list";
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
	public void datagrid(CouponGrantEntity couponGrantEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<CouponGrantEntity> queryWrapper = QueryUtils.installQueryWrapper(couponGrantEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<CouponGrantEntity> lstResult = couponGrantService.page(new Page<CouponGrantEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(CouponGrantEntity couponGrantEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/coupon/grant_add");
		List<CouponEntity> couponList = couponService.list();
		view.addObject("couponList", couponList);
		if(StringUtils.isEmpty(couponGrantEntity.getId())) {
			//新增
			couponGrantEntity = new CouponGrantEntity();
			view.addObject("grant", couponGrantEntity);
		}else {
			couponGrantEntity = couponGrantService.getById(couponGrantEntity.getId());
			view.addObject("grant", couponGrantEntity);
		}
		return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存优惠卷发放信息", memo = "新增或编辑优惠卷发放信息")
	public AjaxJson saveSales(HttpServletRequest req, CouponGrantEntity couponGrantEntity) {
		AjaxJson j = new AjaxJson();
		try {
			/*if(StringUtils.isEmpty(couponEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("优惠卷发放名称为空");
				return j;
			}*/
			
			
			if(StringUtils.isEmpty(couponGrantEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				couponGrantEntity.setCreateDate(new Date());
				couponGrantEntity.setUpdateDate(new Date());
				couponGrantEntity.setState(1);
				
				couponGrantService.save(couponGrantEntity);
			}else {
				//编辑保存
				CouponGrantEntity tmp = couponGrantService.getById(couponGrantEntity.getId());
				
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(couponGrantEntity, tmp);
				couponGrantService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存优惠卷发放信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存用户错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除优惠卷发放信息", memo = "删除了优惠卷发放信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的优惠卷发放");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//删除用户
			couponGrantService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的优惠卷发放信息");
		}catch(Exception e) {
			log.error("删除优惠卷发放信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除优惠卷发放信息错误");
			e.printStackTrace();
		}
		return j;
	}
	
	private void couponContact(List<CouponEntity> list, StringBuffer sb) {
		if(null != list && list.size() > 0) {
			List<CouponEntity> lstTmp = new ArrayList<CouponEntity>();
			for(CouponEntity d : list) {
				lstTmp.add(d);
			}
			if(lstTmp.size() > 0) {
				sb = sb.append(", nodes: [");
				couponContact(lstTmp, sb);
				sb.append("]");
			}
		}
	}
}
