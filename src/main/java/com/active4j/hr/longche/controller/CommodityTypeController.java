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
import com.active4j.hr.longche.entity.CommodityTypeEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.longche.service.CommodityTypeService;
import com.active4j.hr.system.model.ActiveUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/commodityType")
public class CommodityTypeController {
	
	@Autowired
	private CommodityTypeService commodityTypeService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		
		return "longche/commodity/index_type";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		return "longche/commodity/type_list";
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
	public void datagrid(CommodityTypeEntity commodityTypeEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<CommodityTypeEntity> queryWrapper = QueryUtils.installQueryWrapper(commodityTypeEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<CommodityTypeEntity> lstResult = commodityTypeService.page(new Page<CommodityTypeEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(CommodityTypeEntity commodityTypeEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("longche/commodity/type_add");
		//List<ArticleEntity> list = articleService.list();
		//view.addObject("list", list);
		if(StringUtils.isEmpty(commodityTypeEntity.getId())) {
			//新增
			commodityTypeEntity = new CommodityTypeEntity();
			commodityTypeEntity.setState(1);
			view.addObject("commodityType", commodityTypeEntity);
		}else {
			commodityTypeEntity = commodityTypeService.getById(commodityTypeEntity.getId());
			view.addObject("commodityType", commodityTypeEntity);
		}
		return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存商品类型信息", memo = "新增或编辑商品类型信息")
	public AjaxJson saveSales(HttpServletRequest req, CommodityTypeEntity commodityTypeEntity) {
		AjaxJson j = new AjaxJson();
		try {
			/*if(StringUtils.isEmpty(couponEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("优惠卷发放名称为空");
				return j;
			}*/
			
			
			if(StringUtils.isEmpty(commodityTypeEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				commodityTypeEntity.setCreateDate(new Date());
				commodityTypeEntity.setUpdateDate(new Date());
				commodityTypeEntity.setState(1);
				commodityTypeService.save(commodityTypeEntity);
			}else {
				//编辑保存
				CommodityTypeEntity tmp = commodityTypeService.getById(commodityTypeEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(commodityTypeEntity, tmp);
				commodityTypeService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存商品类型信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存商品类型错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除商品类型信息", memo = "删除了商品类型信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的商品类型");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			commodityTypeService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的商品类型信息");
		}catch(Exception e) {
			log.error("删除商品类型信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除商品类型信息错误");
			e.printStackTrace();
		}
		return j;
	}
}
