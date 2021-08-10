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
import com.active4j.hr.longche.entity.OrderEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.longche.service.OrderService;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "longche/order/index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		return "longche/order/list";
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
	public void datagrid(OrderEntity orderEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<OrderEntity> queryWrapper = QueryUtils.installQueryWrapper(orderEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<OrderEntity> lstResult = orderService.page(new Page<OrderEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(OrderEntity orderEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/order/add");
		if(StringUtils.isEmpty(orderEntity.getId())) {
			//新增
			orderEntity = new OrderEntity();
			view.addObject("orderEntity", orderEntity);
		}else {
			orderEntity = orderService.getById(orderEntity.getId());
			view.addObject("orderEntity", orderEntity);
		}
		return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存订单信息", memo = "新增或编辑订单信息")
	public AjaxJson saveSales(HttpServletRequest req, OrderEntity orderEntity) {
		AjaxJson j = new AjaxJson();
		try {
			/*if(StringUtils.isEmpty(couponEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("优惠卷发放名称为空");
				return j;
			}*/
			
			
			if(StringUtils.isEmpty(orderEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				orderEntity.setCreateDate(new Date());
				orderEntity.setUpdateDate(new Date());
				orderEntity.setState(1);
				orderService.save(orderEntity);
			}else {
				//编辑保存
				OrderEntity tmp = orderService.getById(orderEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(orderEntity, tmp);
				orderService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存订单信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存订单错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除订单信息", memo = "删除了订单信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的文章");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//删除用户
			orderService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的订单信息");
		}catch(Exception e) {
			log.error("删除订单信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除订单信息错误");
			e.printStackTrace();
		}
		return j;
	}
}
