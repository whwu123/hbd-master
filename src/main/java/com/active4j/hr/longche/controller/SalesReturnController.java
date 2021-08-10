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
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.SalesReturnService;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequestMapping("/sales")
/**
 * 
 * @Package: com.active4j.hr.longche.controller 
 * @author wuchunhui
 * @date: 2020年11月23日 下午3:24:14
 */
public class SalesReturnController {

	@Autowired
	private SalesReturnService salesReturnService;

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		/*
		 * SalesReturnEntity salesReturnEntity = new SalesReturnEntity();
		 * salesReturnEntity.setName("111"); salesReturnEntity.setPhone("18655222222");
		 * salesReturnEntity.setReturnAddress("地址");
		 * salesReturnService.save(salesReturnEntity);
		 */
		
		/*
		 * SalesReturnEntity salesReturnEntity = salesReturnService.getById("1");
		 * System.out.println(salesReturnEntity.toString());
		 */
		return "longche/salesreturn/index";
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
	public void datagrid(SalesReturnEntity salesReturnEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<SalesReturnEntity> queryWrapper = QueryUtils.installQueryWrapper(salesReturnEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<SalesReturnEntity> lstResult = salesReturnService.page(new Page<SalesReturnEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(SalesReturnEntity salesReturnEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/salesreturn/sales");
		if(StringUtils.isEmpty(salesReturnEntity.getId())) {
			//新增
			salesReturnEntity = new SalesReturnEntity();
			view.addObject("sales", salesReturnEntity);
		}else {
			//编辑
			salesReturnEntity = salesReturnService.getById(salesReturnEntity.getId());
			
			//salesReturnService.saveOrUpdate(salesReturnEntity);
			view.addObject("sales", salesReturnEntity);
		}
		return view;
	}
	
	@RequestMapping("/saveSales")
	@ResponseBody
	@Log(type = LogType.save, name = "保存退货地址信息", memo = "新增或编辑保存了退货地址信息")
	public AjaxJson saveSales(HttpServletRequest req, SalesReturnEntity salesReturnEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(salesReturnEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("姓名不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(salesReturnEntity.getPhone())) {
				j.setSuccess(false);
				j.setMsg("联系电话为空");
				return j;
			}
			
			if(StringUtils.isEmpty(salesReturnEntity.getReturnAddress())) {
				j.setSuccess(false);
				j.setMsg("退货地址为空");
				return j;
			}
			
			if(StringUtils.isEmpty(salesReturnEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				salesReturnEntity.setCreateDate(new Date());
				salesReturnEntity.setUpdateDate(new Date());
				salesReturnEntity.setState("1");
				salesReturnService.save(salesReturnEntity);
			}else {
				//编辑保存
				SalesReturnEntity tmp = salesReturnService.getById(salesReturnEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(salesReturnEntity, tmp);
				salesReturnService.saveOrUpdate(tmp);
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
	@Log(type = LogType.del, name = "删除退货地址", memo = "删除了退货地址信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的退货地址");
				return j;
			}
			
			ActiveUser user = ShiroUtils.getSessionUser();
			
			
			//删除用户
			salesReturnService.removeById(id);
			
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的退货地址信息");
		}catch(Exception e) {
			log.error("删除退货地址报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除退货地址错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public String getById(Model model) {
		
		return "longche/index";
	}
}
