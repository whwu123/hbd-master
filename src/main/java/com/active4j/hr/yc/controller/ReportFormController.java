package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.util.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/reportController")
public class ReportFormController extends BaseController {

    @Autowired
    private YcPaymentRecordService ycPaymentRecordService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(YcPaymentRecord ycPaymentRecord, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
        //获取当前用户id
        String userId = ShiroUtils.getSessionUserId();
        SysUserModel user = sysUserService.getInfoByUserId(userId);
        //获取角色集合
        List<SysRoleEntity> sysRoleEntities = sysUserService.getUserRoleByUserId(userId);
        for(int i= 0 ; i<sysRoleEntities.size();i++){
            SysRoleEntity roleEntity = sysRoleEntities.get(i);
            if(roleEntity.getRoleCode().equals("quxianAdmin")){
                ycPaymentRecord.setQuxianDepartment(user.getDeptName());
            }else if(roleEntity.getRoleCode().equals("xuexiaoAdmin")){
                //通过部门ID拿到部门
                SysDeptEntity sysDeptEntity = sysUserService.getUserDepart(userId);
                String parentId = sysDeptEntity.getParentId();
                SysDeptEntity sysXueXiaoDeptEntity = sysDeptService.getById(parentId);
                ycPaymentRecord.setQuxianDepartment(sysXueXiaoDeptEntity.getName());
                ycPaymentRecord.setStudentSchool(user.getDeptName());
            }
        }
       //获取数据
        //拼接查询条件
        QueryWrapper<YcPaymentRecord> queryWrapper = QueryUtils.installQueryWrapper(ycPaymentRecord, request.getParameterMap(), dataGrid);
        //执行查询
        IPage<YcPaymentRecord> lstResult = ycPaymentRecordService.page(new Page<YcPaymentRecord>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        List<YcPaymentRecord> list = lstResult.getRecords();
        //excel标题
        String[] title = {"区县部门名称","学生姓名","学校名称","身份证","年级","班级","保费","险种","支付流水号","投保人姓名","投保人电话"};
        //excel文件名
        String fileName = "学生缴费记录表"+System.currentTimeMillis()+".xls";
        //sheet名
        String sheetName = "学生缴费记录表";
        String[][] content = new String[list.size()+1][title.length];
        for (int i = 0; i < list.size(); i++) {

            YcPaymentRecord obj = list.get(i);
            content[i][0] = obj.getQuxianDepartment();
            content[i][1] = obj.getStudentName();
            content[i][2] = obj.getStudentSchool();
            content[i][3] = obj.getStudentCard();
            content[i][4] = obj.getStudentNianji();
            content[i][5] = obj.getStudentBanji();
            content[i][6] = obj.getBaofeiMoney();
            content[i][7] = obj.getType();
            content[i][8] = obj.getZhifuNumber();
            content[i][9] = obj.getToubaorenName();
            content[i][10] = obj.getToubaorenPhone();

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);

            OutputStream os = response.getOutputStream();

            wb.write(os);

            os.flush();

            os.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


