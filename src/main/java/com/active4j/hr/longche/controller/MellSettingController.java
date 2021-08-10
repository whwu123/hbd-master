package com.active4j.hr.longche.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.longche.entity.MellSettingEntity;
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.MellSettingService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mell")
public class MellSettingController {
	
	@Autowired
	private MellSettingService mellSettingService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		MellSettingEntity mell = null;
		List<MellSettingEntity> mells =  mellSettingService.list();
		if( mells != null && mells.size()>0) {
			 mell =  mells.get(0);
		}else {
			mell = new MellSettingEntity();
		}
		model.addAttribute("mell", mell);
		return "longche/mellsetting/index";
	}

	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存商城信息", memo = "新增或编辑保存了商城信息")
	public AjaxJson saveSales(HttpServletRequest req, MellSettingEntity mellSettingEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(mellSettingEntity.getName())) {
				j.setSuccess(false);
				j.setMsg("商城为空");
				return j;
			}
			
			if(StringUtils.isEmpty(mellSettingEntity.getPhone())) {
				j.setSuccess(false);
				j.setMsg("联系电话为空");
				return j;
			}
			
			if(StringUtils.isEmpty(mellSettingEntity.getReturnAddress())) {
				j.setSuccess(false);
				j.setMsg("退货地址为空");
				return j;
			}
			
			if(StringUtils.isEmpty(mellSettingEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				mellSettingEntity.setCreateDate(new Date());
				mellSettingEntity.setUpdateDate(new Date());
				mellSettingEntity.setState("1");
				mellSettingService.save(mellSettingEntity);
			}else {
				//编辑保存
				MellSettingEntity tmp = mellSettingService.getById(mellSettingEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(mellSettingEntity, tmp);
				mellSettingService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存用户信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存用户错误");
			e.printStackTrace();
		}
		
		return j;
	}
}
