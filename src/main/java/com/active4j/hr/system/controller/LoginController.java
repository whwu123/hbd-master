package com.active4j.hr.system.controller;

import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.Indexmodel;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @title LoginController.java
 * @time  2020年1月15日 下午2:06:06
 * @author 麻木神
 * @version 1.0
*/
@Controller
@Slf4j
public class LoginController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private YcPaymentRecordService ycPaymentRecordService;
	/**
	 * 
	 * @description
	 *  	跳转到项目首页
	 * @return String
	 * @author 麻木神
	 * @time 2020年1月15日 下午10:25:13
	 */
	@RequestMapping(value = "/console", method = RequestMethod.GET)
	public String console(Model model) {
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
				model.addAttribute("deptName",user.getDeptName());
				//YcPaymentRecord ycPaymentRecord = ycPaymentRecordService.getById("8");
				List<String> schoolName = ycPaymentRecordService.getschoolNameByQuxianName(user.getDeptName());
				List<Indexmodel> indexmodelList = new ArrayList<>();
				if(schoolName.size()>0){
					for (int j = 0; j<schoolName.size();j++){
						Indexmodel indexmodel = new Indexmodel();
						indexmodel.setQuxianName(user.getDeptName());
						indexmodel.setSchoolName(schoolName.get(j));
						//根据学校名称查询出学平险总人数和总金额
						String xpxCount = ycPaymentRecordService.getCount(schoolName.get(j),GlobalConstant.hbd_baoxian_xuesheng);
						String xpxSum = ycPaymentRecordService.getSum(schoolName.get(j),GlobalConstant.hbd_baoxian_xuesheng);
						if(xpxSum == null){
							xpxSum = "0";
						}
						indexmodel.setXuepingxian(xpxCount+"人/"+xpxSum+"元");
						//根据学校名称查询出意外险总人数和总金额
						String ywCount = ycPaymentRecordService.getCount(schoolName.get(j),GlobalConstant.hbd_baoxian_yiwai);
						String ywSum = ycPaymentRecordService.getSum(schoolName.get(j),GlobalConstant.hbd_baoxian_yiwai);
						if(ywSum == null){
							ywSum = "0";
						}
						indexmodel.setYiwaixian(ywCount+"人/"+ywSum+"元");
						//根据学校名称查询出监护人险总人数和总金额
						String jhrCount = ycPaymentRecordService.getCount(schoolName.get(j),GlobalConstant.hbd_baoxian_jianhuren);
						String jhrSum = ycPaymentRecordService.getSum(schoolName.get(j),GlobalConstant.hbd_baoxian_jianhuren);
						if(jhrSum == null){
							jhrSum = "0";
						}
						indexmodel.setJianhurenxian(jhrCount+"人/"+jhrSum+"元");
						int totalCount = Integer.parseInt(xpxCount)+ Integer.parseInt(ywCount)+ Integer.parseInt(jhrCount);
						int totalSum = Integer.parseInt(xpxSum)+ Integer.parseInt(ywSum)+ Integer.parseInt(jhrSum);
						indexmodel.setTotal(totalCount+"人次/"+totalSum+"元");
						indexmodelList.add(indexmodel);
					}
				}
				Indexmodel quxianModel = new Indexmodel();
				quxianModel.setQuxianName(user.getDeptName());
				//查询区县学平险的总人数和总金额
				String totalXpxCount = ycPaymentRecordService.getCountQuxian(user.getDeptName(),GlobalConstant.hbd_baoxian_xuesheng);
				String totalXpxSUm = ycPaymentRecordService.getSumQuxian(user.getDeptName(),GlobalConstant.hbd_baoxian_xuesheng);
				if(totalXpxSUm == null){
					totalXpxSUm = "0";
				}
				quxianModel.setXuepingxian(totalXpxCount+"人/"+totalXpxSUm+"元");
				//查询区县意外险的总人数和总金额
				String totalYwCount = ycPaymentRecordService.getCountQuxian(user.getDeptName(),GlobalConstant.hbd_baoxian_yiwai);
				String totalYwSUm = ycPaymentRecordService.getSumQuxian(user.getDeptName(),GlobalConstant.hbd_baoxian_yiwai);
				if(totalYwSUm == null){
					totalYwSUm = "0";
				}
				quxianModel.setYiwaixian(totalYwCount+"人/"+totalYwSUm+"元");
				//查询区县监护人险的总人数和总金额
				String totalJhrCount = ycPaymentRecordService.getCountQuxian(user.getDeptName(),GlobalConstant.hbd_baoxian_jianhuren);
				String totaljhrSUm = ycPaymentRecordService.getSumQuxian(user.getDeptName(),GlobalConstant.hbd_baoxian_jianhuren);
				if(totaljhrSUm == null){
					totaljhrSUm = "0";
				}
				quxianModel.setJianhurenxian(totalJhrCount+"人/"+totaljhrSUm+"元");
				int totalQuXianCount = Integer.parseInt(totalXpxCount)+ Integer.parseInt(totalYwCount)+ Integer.parseInt(totalJhrCount);
				int totalQuXianSum = Integer.parseInt(totalXpxSUm)+ Integer.parseInt(totalYwSUm)+ Integer.parseInt(totaljhrSUm);
				quxianModel.setTotal(totalQuXianCount+"人次/"+totalQuXianSum+"元");
				model.addAttribute("quxianModel",quxianModel);
				model.addAttribute("indexmodelList",indexmodelList);
				model.addAttribute("isquxianAdmin",isquxianAdmin);
				return "main/console";

			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				isquxianAdmin = "2";
				//根据学校名称拿到年级
				List<String> nianjiNameList = ycPaymentRecordService.getnianjiNameBySchoolName(user.getDeptName());
				model.addAttribute("nianjiNameList",nianjiNameList);
				List<Indexmodel> indexmodelSchoolList = new ArrayList<>();
				if(nianjiNameList.size()>0){
					for (int k = 0;k<nianjiNameList.size();k++){
						Indexmodel indexmodel2 = new Indexmodel();
						indexmodel2.setSchoolName(user.getDeptName());
						indexmodel2.setNianjiName(nianjiNameList.get(k));
						String xpxCountNianji = ycPaymentRecordService.getCountNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_xuesheng,user.getDeptName());
						String xpxSumNianji = ycPaymentRecordService.getSumNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_xuesheng,user.getDeptName());
						if(xpxSumNianji == null){
							xpxSumNianji = "0";
						}
						indexmodel2.setXuepingxian(xpxCountNianji+"人/"+xpxSumNianji+"元");

						String ywCountNianji = ycPaymentRecordService.getCountNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_yiwai,user.getDeptName());
						String ywSumNianji = ycPaymentRecordService.getSumNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_yiwai,user.getDeptName());
						if(ywSumNianji == null){
							ywSumNianji = "0";
						}
						indexmodel2.setYiwaixian(ywCountNianji+"人/"+ywSumNianji+"元");

						String jhrCountNianji = ycPaymentRecordService.getCountNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_jianhuren,user.getDeptName());
						String jhrSumNianji = ycPaymentRecordService.getSumNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_jianhuren,user.getDeptName());
						if(jhrSumNianji == null){
							jhrSumNianji = "0";
						}
						indexmodel2.setJianhurenxian(jhrCountNianji+"人/"+jhrSumNianji+"元");
						int totalCountNianji = Integer.parseInt(xpxCountNianji)+ Integer.parseInt(ywCountNianji)+ Integer.parseInt(jhrCountNianji);
						int totalSumNianji = Integer.parseInt(xpxSumNianji)+ Integer.parseInt(ywSumNianji)+ Integer.parseInt(jhrSumNianji);
						indexmodel2.setTotal(totalCountNianji+"人次/"+totalSumNianji+"元");
						indexmodelSchoolList.add(indexmodel2);
					}
				}
				model.addAttribute("indexmodelSchoolList",indexmodelSchoolList);
				Indexmodel xuexiaoModel = new Indexmodel();
				xuexiaoModel.setSchoolName(user.getDeptName());
				//查询学校学平险的总人数和总金额
				String totalXpxSchoolCount = ycPaymentRecordService.getCountSchool(user.getDeptName(),GlobalConstant.hbd_baoxian_xuesheng);
				String totalXpxSchoolSUm = ycPaymentRecordService.getSumSchool(user.getDeptName(),GlobalConstant.hbd_baoxian_xuesheng);
				if(totalXpxSchoolSUm == null){
					totalXpxSchoolSUm = "0";
				}
				xuexiaoModel.setXuepingxian(totalXpxSchoolCount+"人/"+totalXpxSchoolSUm+"元");;
				//查询区县意外险的总人数和总金额
				String totalYwSchoolCount = ycPaymentRecordService.getCountSchool(user.getDeptName(),GlobalConstant.hbd_baoxian_yiwai);
				String totalYwSchoolSUm = ycPaymentRecordService.getSumSchool(user.getDeptName(),GlobalConstant.hbd_baoxian_yiwai);
				if(totalYwSchoolSUm == null){
					totalYwSchoolSUm = "0";
				}
				xuexiaoModel.setYiwaixian(totalYwSchoolCount+"人/"+totalYwSchoolSUm+"元");;
				//查询区县监护人险的总人数和总金额
				String totalJhrSchoolCount = ycPaymentRecordService.getCountSchool(user.getDeptName(),GlobalConstant.hbd_baoxian_jianhuren);
				String totaljhrSchoolSUm = ycPaymentRecordService.getSumSchool(user.getDeptName(),GlobalConstant.hbd_baoxian_jianhuren);
				if(totaljhrSchoolSUm == null){
					totaljhrSchoolSUm = "0";
				}
				xuexiaoModel.setJianhurenxian(totalJhrSchoolCount+"人/"+totaljhrSchoolSUm+"元");;
				int totalSchoolCount = Integer.parseInt(totalXpxSchoolCount)+ Integer.parseInt(totalYwSchoolCount)+ Integer.parseInt(totalJhrSchoolCount);
				int totalSchoolSum = Integer.parseInt(totalXpxSchoolSUm)+ Integer.parseInt(totalYwSchoolSUm)+ Integer.parseInt(totaljhrSchoolSUm);
				xuexiaoModel.setTotal(totalSchoolCount+"人次/"+totalSchoolSum+"元");
				model.addAttribute("xuexiaoName",user.getDeptName());
				model.addAttribute("xuexiaoModel",xuexiaoModel);
				model.addAttribute("isquxianAdmin",isquxianAdmin);
				return "main/console2";
			}else if(roleEntity.getRoleCode().equals("diquAdmin")  || roleEntity.getRoleCode().equals("admin")){
				List<Indexmodel> indexmodelShilList = new ArrayList<>();
				Indexmodel shiModel = new Indexmodel();
				String shiDept = "怀化分公司";
				shiModel.setQuxianName(shiDept);
				//查询学平险的总人数和总金额
				String totalXpxShiCount = ycPaymentRecordService.getCountShi(shiDept,GlobalConstant.hbd_baoxian_xuesheng);
				String totalXpxShilSUm = ycPaymentRecordService.getSumShi(shiDept,GlobalConstant.hbd_baoxian_xuesheng);
				if(totalXpxShilSUm == null){
					totalXpxShilSUm = "0";
				}
				shiModel.setXuepingxian(totalXpxShiCount+"人/"+totalXpxShilSUm+"元");;
				//查询意外险的总人数和总金额
				String totalYwShiCount = ycPaymentRecordService.getCountShi(shiDept,GlobalConstant.hbd_baoxian_yiwai);
				String totalYwShiSUm = ycPaymentRecordService.getSumShi(shiDept,GlobalConstant.hbd_baoxian_yiwai);
				if(totalYwShiSUm == null){
					totalYwShiSUm = "0";
				}
				shiModel.setYiwaixian(totalYwShiCount+"人/"+totalYwShiSUm+"元");;
				//查询监护人险的总人数和总金额
				String totalJhrShiCount = ycPaymentRecordService.getCountShi(shiDept,GlobalConstant.hbd_baoxian_jianhuren);
				String totaljhrShiSUm = ycPaymentRecordService.getSumShi(shiDept,GlobalConstant.hbd_baoxian_jianhuren);
				if(totaljhrShiSUm == null){
					totaljhrShiSUm = "0";
				}
				shiModel.setJianhurenxian(totaljhrShiSUm+"人/"+totaljhrShiSUm+"元");;
				int totalShiCount = Integer.parseInt(totalXpxShiCount)+ Integer.parseInt(totalYwShiCount)+ Integer.parseInt(totaljhrShiSUm);
				int totalShilSum = Integer.parseInt(totalXpxShilSUm)+ Integer.parseInt(totalYwShiSUm)+ Integer.parseInt(totaljhrShiSUm);
				shiModel.setTotal(totalShiCount+"人次/"+totalShilSum+"元");
				model.addAttribute("shiModel",shiModel);


				List<String> quxianNameList = ycPaymentRecordService.getQuxianNameByShibumenName(shiDept);
				if(quxianNameList.size()>0){
					for (int l = 0;l < quxianNameList.size();l++){
						Indexmodel indexmodel3 = new Indexmodel();
						indexmodel3.setQuxianName(quxianNameList.get(l));
						String xpxCountQuxian = ycPaymentRecordService.getCountQuxian(quxianNameList.get(l),GlobalConstant.hbd_baoxian_xuesheng);
						String xpxSumQuxian = ycPaymentRecordService.getSumQuxian(quxianNameList.get(l),GlobalConstant.hbd_baoxian_xuesheng);
						if(xpxSumQuxian == null){
							xpxSumQuxian = "0";
						}
						indexmodel3.setXuepingxian(xpxCountQuxian+"人/"+xpxSumQuxian+"元");

						String ywCountQuxian = ycPaymentRecordService.getCountQuxian(quxianNameList.get(l),GlobalConstant.hbd_baoxian_yiwai);
						String ywSumQuxian = ycPaymentRecordService.getSumQuxian(quxianNameList.get(l),GlobalConstant.hbd_baoxian_yiwai);
						if(ywSumQuxian == null){
							ywSumQuxian = "0";
						}
						indexmodel3.setYiwaixian(ywCountQuxian+"人/"+ywSumQuxian+"元");

						String jhrCountQuxian = ycPaymentRecordService.getCountQuxian(quxianNameList.get(l),GlobalConstant.hbd_baoxian_jianhuren);
						String jhrSumQuxian = ycPaymentRecordService.getSumQuxian(quxianNameList.get(l),GlobalConstant.hbd_baoxian_jianhuren);
						if(jhrSumQuxian == null){
							jhrSumQuxian = "0";
						}
						indexmodel3.setJianhurenxian(jhrCountQuxian+"人/"+jhrSumQuxian+"元");
						int totalCountQuxian = Integer.parseInt(xpxCountQuxian)+ Integer.parseInt(ywCountQuxian)+ Integer.parseInt(jhrCountQuxian);
						int totalSumQuxian = Integer.parseInt(xpxSumQuxian)+ Integer.parseInt(ywSumQuxian)+ Integer.parseInt(jhrSumQuxian);
						indexmodel3.setTotal(totalCountQuxian+"人次/"+totalSumQuxian+"元");
						indexmodelShilList.add(indexmodel3);
					}

				}
				model.addAttribute("indexmodelShilList",indexmodelShilList);
				return "main/console3";
			}
		}


		return "main/console";
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到项目首页
	 * @return String
	 * @author 麻木神
	 * @time 2020年1月15日 下午10:25:13
	 */
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
				model.addAttribute("deptName",user.getDeptName());




			}else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
				isquxianAdmin = "2";
				model.addAttribute("xuexiaoName",user.getDeptName());
			}
		}
		//System.out.println("isquxianAdmin="+isquxianAdmin);
		model.addAttribute("isquxianAdmin",isquxianAdmin);
		return "main/main";
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到项目首页
	 * @return String
	 * @author 麻木神
	 * @time 2020年1月15日 下午10:25:13
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index2(Model model) {
		return "main/main";
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到403
	 * @return String
	 * @author 麻木神
	 * @time 2020年1月15日 下午10:25:13
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String auth(Model model) {
		return "common/403";
	}
	

	/**
	 * 
	 * @description
	 *  	跳转登录页面
	 * @return String
	 * @author 麻木神
	 * @time 2020年1月15日 下午2:09:06
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		return "login/login";
	}
	
	/**
	 * 
	 * @description
	 *  	登出
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2020年2月4日 上午9:09:37
	 */
	@RequestMapping("/logout")
	@Log(type = LogType.logout, name = "用户登出", memo = "用户已登出")
	public String logout() {
		ShiroUtils.logout();
		return "login/login";
	}
	
	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	@Log(type = LogType.login, name = "用户登录", memo = "用户成功登录")
	public AjaxJson loginAction(String userName, String password, String randCode) {
		AjaxJson j = new AjaxJson();
		
		try {
			// 后端校验
			if (StringUtils.isEmpty(userName)) {
				j.setSuccess(false);
				j.setMsg("用户名不能为空");
				return j;
			}

			if (StringUtils.isEmpty(password)) {
				j.setSuccess(false);
				j.setMsg("密码不能为空");
				return j;
			}

			/*if (StringUtils.isEmpty(randCode)) {
				j.setSuccess(false);
				j.setMsg("验证码不能为空");
				return j;
			}

			// 验证码的校验
			if (!StringUtils.equalsIgnoreCase(randCode, ShiroUtils.getSessionValue(GlobalConstant.SESSION_KEY_OF_RAND_CODE))) {
				j.setSuccess(false);
				j.setMsg("验证码填写错误");
				return j;
			}*/

			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			ShiroUtils.getSubject().login(token);

		} catch (IncorrectCredentialsException e) {
			j.setSuccess(false);
			j.setMsg("用户名或密码填写错误");
		} catch (UnknownAccountException e) {
			j.setSuccess(false);
			j.setMsg("用户名或密码填写错误");
		} catch (LockedAccountException e) {
			j.setSuccess(false);
			j.setMsg("当前账户已锁定，请联系管理员");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("系统错误，请联系管理员");
			log.error("系统错误，错误信息:{}", e);
		}

		return j;
		
	}
}
