package com.active4j.hr.yc.controller;


import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcUpdateStulog;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.service.YcUpdateStulogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ycUpdateStulogController")
public class YcUpdateStulogController extends BaseController {
	
	@Autowired
	private YcUpdateStulogService ycUpdateStulogService;
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		String isquxianAdmin = "0";
		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				isquxianAdmin = "1";
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				isquxianAdmin = "2";
			}
		}
		model.addAttribute("isquxianAdmin",isquxianAdmin);

		return "yc/student/update/list";
	}

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String index2(Model model) {
		String isquxianAdmin = "0";
		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				isquxianAdmin = "1";
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				isquxianAdmin = "2";
			}
		}
		model.addAttribute("isquxianAdmin",isquxianAdmin);
		return "yc/student/update/list2";
	}

	@RequestMapping(value = "/index3", method = RequestMethod.GET)
	public String index3(Model model) {
		String isquxianAdmin = "0";
		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				isquxianAdmin = "1";
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				isquxianAdmin = "2";
			}
		}
		model.addAttribute("isquxianAdmin",isquxianAdmin);
		return "yc/student/update/list3";
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
	public void datagrid(YcUpdateStulog ycUpdateStulog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				ycUpdateStulog.setQuxianDepartment(user.getDeptName());
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				ycUpdateStulog.setStudentSchool(user.getDeptName());
			}
		}
		if(ycUpdateStulog.getState()==null || ycUpdateStulog.getState().isEmpty()){
			ycUpdateStulog.setState("1");
		}
		//拼接查询条件
		QueryWrapper<YcUpdateStulog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateStulog, request.getParameterMap(), dataGrid);
		//执行查询
		IPage<YcUpdateStulog> lstResult = ycUpdateStulogService.page(new Page<YcUpdateStulog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
	}


	@RequestMapping("/datagrid2")
	public void datagrid2(YcUpdateStulog ycUpdateStulog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		/*if(dataGrid.getSidx()==null && dataGrid.getSidx().isEmpty()){
			dataGrid.setSidx("createDate");
		}*/
		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				ycUpdateStulog.setQuxianDepartment(user.getDeptName());
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				ycUpdateStulog.setStudentSchool(user.getDeptName());
			}
		}
		if(ycUpdateStulog.getState()==null || ycUpdateStulog.getState().isEmpty()){
			ycUpdateStulog.setState("2");
		}
		//拼接查询条件
		QueryWrapper<YcUpdateStulog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateStulog, request.getParameterMap(), dataGrid);
		//执行查询
		IPage<YcUpdateStulog> lstResult = ycUpdateStulogService.page(new Page<YcUpdateStulog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
	}

	@RequestMapping("/datagrid3")
	public void datagrid3(YcUpdateStulog ycUpdateStulog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				ycUpdateStulog.setQuxianDepartment(user.getDeptName());
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				ycUpdateStulog.setStudentSchool(user.getDeptName());
			}
		}
		if(ycUpdateStulog.getState()==null || ycUpdateStulog.getState().isEmpty()){
			ycUpdateStulog.setState("0");
		}
		//拼接查询条件
		QueryWrapper<YcUpdateStulog> queryWrapper = QueryUtils.installQueryWrapper(ycUpdateStulog, request.getParameterMap(), dataGrid);
		//执行查询
		IPage<YcUpdateStulog> lstResult = ycUpdateStulogService.page(new Page<YcUpdateStulog>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
	}


	@RequestMapping("/updateStu")
	public ModelAndView updateStu(YcUpdateStulog ycUpdateStulog, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("yc/student/update/student");
		if(!StringUtils.isEmpty(ycUpdateStulog.getId())) {
			YcUpdateStulog newStu = new YcUpdateStulog();
			newStu = ycUpdateStulogService.getById(ycUpdateStulog.getId());
			view.addObject("stu",newStu);
		}
		return view;
	}

	@RequestMapping("/saveStu")
	@ResponseBody
	@Log(type = LogType.save, name = "编辑了用户信息", memo = "编辑保存了学生信息")
	public AjaxJson saveStu(HttpServletRequest req, YcUpdateStulog ycUpdateStulog) {
		AjaxJson j = new AjaxJson();
		try {
			ycUpdateStulog.setState("2");
			ycUpdateStulogService.saveOrUpdate(ycUpdateStulog);
			j.setSuccess(true);
			j.setMsg("审核编辑学生《"+ycUpdateStulog.getStudentName()+"》信息成功");


		}catch(Exception e) {
			log.error("审核时编辑学生信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("审核时编辑学生信息错误");
			e.printStackTrace();
		}

		return j;
	}

	@RequestMapping(value = "/auditingStu", method = RequestMethod.POST)
	@ResponseBody
	@Log(type = LogType.update, name = "审核了学生信息", memo = "审核了学生信息")
	public AjaxJson auditingStu(String id, HttpServletRequest req) {
		YcUpdateStulog stu = new YcUpdateStulog();
		AjaxJson j = new AjaxJson();
		try {
				stu = ycUpdateStulogService.getById(id);
				stu.setState("2");
				ycUpdateStulogService.saveOrUpdate(stu);
				j.setSuccess(true);
				j.setMsg("审核学生《"+stu.getStudentName()+"》信息成功");
				return j;

		}catch(Exception e) {
			log.error("审核学生信息错误:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("审核学生信息错误");
			e.printStackTrace();
		}
		return j;
	}

	@RequestMapping(value = "/updateStuToSys", method = RequestMethod.POST)
	@ResponseBody
	@Log(type = LogType.update, name = "修改学生信息到缴费系统", memo = "修改学生信息到缴费系统")
	public AjaxJson updateStuToSys(String id, HttpServletRequest req) {
		YcUpdateStulog stu = new YcUpdateStulog();
		AjaxJson j = new AjaxJson();
		try {
			stu = ycUpdateStulogService.getById(id);
			stu.setState("0");
			ycUpdateStulogService.saveOrUpdate(stu);
			j.setSuccess(true);
			j.setMsg("修改学生《"+stu.getStudentName()+"》信息到缴费系统成功");
			return j;

		}catch(Exception e) {
			log.error("修改学生信息到缴费系统错误:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("修改学生信息到缴费系统错误");
			e.printStackTrace();
		}
		return j;
	}

	/**
	 *
	 * @description
	 *  	删除账户
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2020年2月8日 下午4:25:02
	 */
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除学生审核信息", memo = "删除学生审核信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {

			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的学生审核信息");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//删除用户
			ycUpdateStulogService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的学生审核信息");
		}catch(Exception e) {
			log.error("删除学生审核信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除学生审核信息错误");
			e.printStackTrace();
		}

		return j;
	}
}

