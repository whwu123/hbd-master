package com.active4j.hr.yc.controller;


import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.ListUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcUpdateStulog;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.service.YcStudentService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ycStudent")
public class YcstudentController extends BaseController {
	
	@Autowired
	private YcStudentService ycStudentService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private YcUpdateStulogService ycUpdateStulogService;
	@Autowired
	private YcPaymentRecordService ycPaymentRecordService;
	@Autowired
	private SysDeptService sysDeptService;

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
			}else if(isquxianAdmin.equals("0")){
				// 给区县查询条件中的下拉框准备数据
				List<SysDeptEntity> lstDeparts = sysDeptService.getChildDeptsByDeptId("137b1112dcef19b7adab2b85c0624c4d");
				model.addAttribute("departsReplace", ListUtils.listToReplaceStr(lstDeparts, "name", "name"));
			}
		}
		model.addAttribute("isquxianAdmin",isquxianAdmin);
		return "yc/student/list";
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
	public void datagrid(YcStudentEntity ycStudentEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		//获取当前用户id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//获取角色集合
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				ycStudentEntity.setQuxianDepartment(user.getDeptName());
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				ycStudentEntity.setStudentSchool(user.getDeptName());
			}
		}

		//拼接查询条件
		QueryWrapper<YcStudentEntity> queryWrapper = QueryUtils.installQueryWrapper(ycStudentEntity, request.getParameterMap(), dataGrid);

		//执行查询
		IPage<YcStudentEntity> lstResult = ycStudentService.page(new Page<YcStudentEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);

	}

	@RequestMapping("/updateStu")
	public ModelAndView updateStu(YcStudentEntity ycStudentEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("yc/student/ycStudent");
		if(!StringUtils.isEmpty(ycStudentEntity.getId())) {
			ycStudentEntity = ycStudentService.getById(ycStudentEntity.getId());
			view.addObject("stu",ycStudentEntity);
		}
		return view;
	}

	@RequestMapping("/saveStu")
	@ResponseBody
	@Log(type = LogType.save, name = "编辑了用户信息", memo = "编辑保存了学生信息")
	public AjaxJson saveStu(HttpServletRequest req, YcStudentEntity ycStudentEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(!StringUtils.isEmpty(ycStudentEntity.getId())) {
				ycStudentService.saveOrUpdate(ycStudentEntity);
			}

			YcUpdateStulog ycUpdateStulog = new YcUpdateStulog();
			ycUpdateStulog.setQuxianDepartment(ycStudentEntity.getQuxianDepartment());
			ycUpdateStulog.setStudentSchool(ycStudentEntity.getStudentSchool());
			ycUpdateStulog.setStudentBanji(ycStudentEntity.getStudentBanji());
			ycUpdateStulog.setStudentName(ycStudentEntity.getStudentName());
			ycUpdateStulog.setStudentNianji(ycStudentEntity.getStudentNianji());
			ycUpdateStulog.setStudentCard(ycStudentEntity.getStudentCard());
			ycUpdateStulog.setState("2");

			//根据身份证拿到投保人信息
			List<YcPaymentRecord> recordList = new ArrayList<>();
			QueryWrapper<YcPaymentRecord> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("studentCard",ycStudentEntity.getStudentCard());
			recordList =ycPaymentRecordService.list();
			if(recordList.size()>0){
				ycUpdateStulog.setToubaorenName(recordList.get(0).getToubaorenName());
				ycUpdateStulog.setToubaorenPhone(recordList.get(0).getToubaorenPhone());
			}
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
}
