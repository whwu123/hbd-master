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
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.service.YcPaymentRecordService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ycPayMentRecord")
public class YcPaymentRecordsController extends BaseController {
	
	@Autowired
	private YcPaymentRecordService ycPaymentRecordService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDeptService sysDeptService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		String isquxianAdmin = "0";
		//??????????????????id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//??????????????????
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				isquxianAdmin = "1";
				model.addAttribute("deptName",user.getDeptName());
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				isquxianAdmin = "2";
				model.addAttribute("xuexiaoName",user.getDeptName());
			}else if(isquxianAdmin.equals("0")){
				// ????????????????????????????????????????????????
				List<SysDeptEntity> lstDeparts = sysDeptService.getChildDeptsByDeptId("137b1112dcef19b7adab2b85c0624c4d");
				model.addAttribute("departsReplace", ListUtils.listToReplaceStr(lstDeparts, "name", "name"));
			}
		}
		model.addAttribute("isquxianAdmin",isquxianAdmin);
		return "yc/paymentrecord/list";
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
	public void datagrid(YcPaymentRecord ycPaymentRecord, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		//??????????????????id
		String userId = ShiroUtils.getSessionUserId();
		SysUserModel user = sysUserService.getInfoByUserId(userId);
		//??????????????????
		List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
		for(int i= 0 ; i<sysRoleEntities.size();i++){
			SysRoleEntity roleEntity = sysRoleEntities.get(i);
			if(roleEntity.getRoleCode().equals("quxianAdmin")){
				ycPaymentRecord.setQuxianDepartment(user.getDeptName());
			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				ycPaymentRecord.setStudentSchool(user.getDeptName());
			}
		}

		//??????????????????
		QueryWrapper<YcPaymentRecord> queryWrapper = QueryUtils.installQueryWrapper(ycPaymentRecord, request.getParameterMap(), dataGrid);

		//????????????
		IPage<YcPaymentRecord> lstResult = ycPaymentRecordService.page(new Page<YcPaymentRecord>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

		//????????????
		ResponseUtil.writeJson(response, dataGrid, lstResult);

	}

	/**
	 *
	 * @description
	 *  	????????????
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2020???2???8??? ??????4:25:02
	 */
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "????????????????????????", memo = "????????????????????????")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {

			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("??????????????????????????????????????????");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//????????????
			ycPaymentRecordService.removeById(id);
			log.info("?????????" + user.getUserName() + "?????????id??????" + id + "?????????????????????");
		}catch(Exception e) {
			log.error("????????????????????????????????????????????????{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("??????????????????????????????");
			e.printStackTrace();
		}

		return j;
	}
}
