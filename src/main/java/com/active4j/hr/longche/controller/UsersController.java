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
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.entity.UsersEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.longche.service.UsersService;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/userslc")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		return "longche/users/list";
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
	public void datagrid(UsersEntity usersEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<UsersEntity> queryWrapper = QueryUtils.installQueryWrapper(usersEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<UsersEntity> lstResult = usersService.page(new Page<UsersEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	                                                                                                                                                                                                                                                                                                                                                          
}
