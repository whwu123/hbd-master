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
	 *  	??????????????????
	 * @return void
	 * @author ?????????
	 * @time 2020???1???25??? ??????9:46:12
	 */
	@RequestMapping("/datagrid")
	public void datagrid(CouponGrantEntity couponGrantEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//??????????????????
		QueryWrapper<CouponGrantEntity> queryWrapper = QueryUtils.installQueryWrapper(couponGrantEntity, request.getParameterMap(), dataGrid);
		
		//????????????
		IPage<CouponGrantEntity> lstResult = couponGrantService.page(new Page<CouponGrantEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//????????????
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(CouponGrantEntity couponGrantEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/coupon/grant_add");
		List<CouponEntity> couponList = couponService.list();
		view.addObject("couponList", couponList);
		if(StringUtils.isEmpty(couponGrantEntity.getId())) {
			//??????
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
	@Log(type = LogType.save, name = "???????????????????????????", memo = "????????????????????????????????????")
	public AjaxJson saveSales(HttpServletRequest req, CouponGrantEntity couponGrantEntity) {
		AjaxJson j = new AjaxJson();
		try {
			/*if(StringUtils.isEmpty(couponEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("???????????????????????????");
				return j;
			}*/
			
			
			if(StringUtils.isEmpty(couponGrantEntity.getId())) {
				//????????????
				//??????
				//salesReturnEntity.setVersions(1);
				couponGrantEntity.setCreateDate(new Date());
				couponGrantEntity.setUpdateDate(new Date());
				couponGrantEntity.setState(1);
				
				couponGrantService.save(couponGrantEntity);
			}else {
				//????????????
				CouponGrantEntity tmp = couponGrantService.getById(couponGrantEntity.getId());
				
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(couponGrantEntity, tmp);
				couponGrantService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("????????????????????????????????????????????????:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("??????????????????");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "???????????????????????????", memo = "??????????????????????????????")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("???????????????????????????????????????");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//????????????
			couponGrantService.removeById(id);
			log.info("?????????" + user.getUserName() + "?????????id??????" + id + "????????????????????????");
		}catch(Exception e) {
			log.error("???????????????????????????????????????????????????{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("?????????????????????????????????");
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
