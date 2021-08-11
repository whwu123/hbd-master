package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.model.SysUserModel;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.service.SysUserService;
import com.active4j.hr.yc.entity.Indexmodel;
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
import java.util.ArrayList;
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

    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/exportQuxian")
    @ResponseBody
    public void exportQuxian(String name, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
        if(name!=null && !name.isEmpty()){
            List<Indexmodel> indexmodelList = new ArrayList<>();
            Indexmodel quxianModel = new Indexmodel();
            quxianModel.setQuxianName(name);
            quxianModel.setSchoolName(name);
            //查询区县学平险的总人数和总金额
            String totalXpxCount = ycPaymentRecordService.getCountQuxian(name, GlobalConstant.hbd_baoxian_xuesheng);
            String totalXpxSUm = ycPaymentRecordService.getSumQuxian(name,GlobalConstant.hbd_baoxian_xuesheng);
            if(totalXpxSUm == null){
                totalXpxSUm = "0";
            }
            quxianModel.setXuepingxian(totalXpxCount+"人/"+totalXpxSUm+"元");
            //查询区县意外险的总人数和总金额
            String totalYwCount = ycPaymentRecordService.getCountQuxian(name,GlobalConstant.hbd_baoxian_yiwai);
            String totalYwSUm = ycPaymentRecordService.getSumQuxian(name,GlobalConstant.hbd_baoxian_yiwai);
            if(totalYwSUm == null){
                totalYwSUm = "0";
            }
            quxianModel.setYiwaixian(totalYwCount+"人/"+totalYwSUm+"元");
            //查询区县监护人险的总人数和总金额
            String totalJhrCount = ycPaymentRecordService.getCountQuxian(name,GlobalConstant.hbd_baoxian_jianhuren);
            String totaljhrSUm = ycPaymentRecordService.getSumQuxian(name,GlobalConstant.hbd_baoxian_jianhuren);
            if(totaljhrSUm == null){
                totaljhrSUm = "0";
            }
            quxianModel.setJianhurenxian(totalJhrCount+"人/"+totaljhrSUm+"元");
            int totalQuXianCount = Integer.parseInt(totalXpxCount)+ Integer.parseInt(totalYwCount)+ Integer.parseInt(totalJhrCount);
            int totalQuXianSum = Integer.parseInt(totalXpxSUm)+ Integer.parseInt(totalYwSUm)+ Integer.parseInt(totaljhrSUm);
            quxianModel.setTotal(totalQuXianCount+"人/"+totalQuXianSum+"元");
            indexmodelList.add(quxianModel);

            List<String> schoolName = ycPaymentRecordService.getschoolNameByQuxianName(name);
            if(schoolName.size()>0){
                for (int j = 0; j<schoolName.size();j++){
                    Indexmodel indexmodel = new Indexmodel();
                    indexmodel.setQuxianName(name);
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
                    indexmodel.setTotal(totalCount+"人/"+totalSum+"元");
                    indexmodelList.add(indexmodel);
                }
            }
            //excel标题
            String[] title = {"名称","学生平安保险","交通意外、重大疾病险","监护人责任险","合计"};
            //excel文件名
            String fileName = name+"学生缴费统计表"+System.currentTimeMillis()+".xls";
            //sheet名
            String sheetName = name+"学生缴费统计表";
            String[][] content = new String[indexmodelList.size()+1][title.length];
            for (int i = 0; i < indexmodelList.size(); i++) {
                Indexmodel obj = indexmodelList.get(i);
                content[i][0] = obj.getSchoolName();
                content[i][1] = obj.getXuepingxian();
                content[i][2] = obj.getYiwaixian();
                content[i][3] = obj.getJianhurenxian();
                content[i][4] = obj.getTotal();
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
    }


    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/exportXuexiao")
    @ResponseBody
    public void exportXuexiao(String name, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
        if(name!=null && !name.isEmpty()){
            List<Indexmodel> indexmodelSchoolList = new ArrayList<>();
            Indexmodel xuexiaoModel = new Indexmodel();
            xuexiaoModel.setSchoolName(name);
            xuexiaoModel.setNianjiName(name);
            //查询学校学平险的总人数和总金额
            String totalXpxSchoolCount = ycPaymentRecordService.getCountSchool(name,GlobalConstant.hbd_baoxian_xuesheng);
            String totalXpxSchoolSUm = ycPaymentRecordService.getSumSchool(name,GlobalConstant.hbd_baoxian_xuesheng);
            if(totalXpxSchoolSUm == null){
                totalXpxSchoolSUm = "0";
            }
            xuexiaoModel.setXuepingxian(totalXpxSchoolCount+"人/"+totalXpxSchoolSUm+"元");;
            //查询区县意外险的总人数和总金额
            String totalYwSchoolCount = ycPaymentRecordService.getCountSchool(name,GlobalConstant.hbd_baoxian_yiwai);
            String totalYwSchoolSUm = ycPaymentRecordService.getSumSchool(name,GlobalConstant.hbd_baoxian_yiwai);
            if(totalYwSchoolSUm == null){
                totalYwSchoolSUm = "0";
            }
            xuexiaoModel.setYiwaixian(totalYwSchoolCount+"人/"+totalYwSchoolSUm+"元");;
            //查询区县监护人险的总人数和总金额
            String totalJhrSchoolCount = ycPaymentRecordService.getCountSchool(name,GlobalConstant.hbd_baoxian_jianhuren);
            String totaljhrSchoolSUm = ycPaymentRecordService.getSumSchool(name,GlobalConstant.hbd_baoxian_jianhuren);
            if(totaljhrSchoolSUm == null){
                totaljhrSchoolSUm = "0";
            }
            xuexiaoModel.setJianhurenxian(totalJhrSchoolCount+"人/"+totaljhrSchoolSUm+"元");;
            int totalSchoolCount = Integer.parseInt(totalXpxSchoolCount)+ Integer.parseInt(totalYwSchoolCount)+ Integer.parseInt(totalJhrSchoolCount);
            int totalSchoolSum = Integer.parseInt(totalXpxSchoolSUm)+ Integer.parseInt(totalYwSchoolSUm)+ Integer.parseInt(totaljhrSchoolSUm);
            xuexiaoModel.setTotal(totalSchoolCount+"人/"+totalSchoolSum+"元");
            indexmodelSchoolList.add(xuexiaoModel);

            List<String> nianjiNameList = ycPaymentRecordService.getnianjiNameBySchoolName(name);
            if(nianjiNameList.size()>0){
                for (int k = 0;k<nianjiNameList.size();k++){
                    Indexmodel indexmodel2 = new Indexmodel();
                    indexmodel2.setSchoolName(name);
                    indexmodel2.setNianjiName(nianjiNameList.get(k));
                    String xpxCountNianji = ycPaymentRecordService.getCountNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_xuesheng,name);
                    String xpxSumNianji = ycPaymentRecordService.getSumNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_xuesheng,name);
                    if(xpxSumNianji == null){
                        xpxSumNianji = "0";
                    }
                    indexmodel2.setXuepingxian(xpxCountNianji+"人/"+xpxSumNianji+"元");
                    String ywCountNianji = ycPaymentRecordService.getCountNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_yiwai,name);
                    String ywSumNianji = ycPaymentRecordService.getSumNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_yiwai,name);
                    if(ywSumNianji == null){
                        ywSumNianji = "0";
                    }
                    indexmodel2.setYiwaixian(ywCountNianji+"人/"+ywSumNianji+"元");
                    String jhrCountNianji = ycPaymentRecordService.getCountNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_jianhuren,name);
                    String jhrSumNianji = ycPaymentRecordService.getSumNianjji(nianjiNameList.get(k),GlobalConstant.hbd_baoxian_jianhuren,name);
                    if(jhrSumNianji == null){
                        jhrSumNianji = "0";
                    }
                    indexmodel2.setJianhurenxian(jhrCountNianji+"人/"+jhrSumNianji+"元");
                    int totalCountNianji = Integer.parseInt(xpxCountNianji)+ Integer.parseInt(ywCountNianji)+ Integer.parseInt(jhrCountNianji);
                    int totalSumNianji = Integer.parseInt(xpxSumNianji)+ Integer.parseInt(ywSumNianji)+ Integer.parseInt(jhrSumNianji);
                    indexmodel2.setTotal(totalCountNianji+"人/"+totalSumNianji+"元");
                    indexmodelSchoolList.add(indexmodel2);
                }
            }

            //excel标题
            String[] title = {"名称","学生平安保险","交通意外、重大疾病险","监护人责任险","合计"};
            //excel文件名
            String fileName = name+"学生缴费统计表"+System.currentTimeMillis()+".xls";
            //sheet名
            String sheetName = name+"学生缴费统计表";
            String[][] content = new String[indexmodelSchoolList.size()+1][title.length];
            for (int i = 0; i < indexmodelSchoolList.size(); i++) {
                Indexmodel obj = indexmodelSchoolList.get(i);
                content[i][0] = obj.getNianjiName();
                content[i][1] = obj.getXuepingxian();
                content[i][2] = obj.getYiwaixian();
                content[i][3] = obj.getJianhurenxian();
                content[i][4] = obj.getTotal();
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
    }

    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/exportDiqu")
    @ResponseBody
    public void exportDiqu(String name, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
        if(name!=null && !name.isEmpty()){
            List<Indexmodel> indexmodelSchoolList = new ArrayList<>();
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
            indexmodelSchoolList.add(shiModel);

            List<String> quxianNameList = ycPaymentRecordService.getQuxianNameByShibumenName(shiDept);
            if(quxianNameList.size()>0){
                for (int k = 0;k<quxianNameList.size();k++){
                    Indexmodel indexmodel3 = new Indexmodel();
                    indexmodel3.setQuxianName(quxianNameList.get(k));
                    String xpxCountQuxian = ycPaymentRecordService.getCountQuxian(quxianNameList.get(k),GlobalConstant.hbd_baoxian_xuesheng);
                    String xpxSumQuxian = ycPaymentRecordService.getSumQuxian(quxianNameList.get(k),GlobalConstant.hbd_baoxian_xuesheng);
                    if(xpxSumQuxian == null){
                        xpxSumQuxian = "0";
                    }
                    indexmodel3.setXuepingxian(xpxCountQuxian+"人/"+xpxSumQuxian+"元");

                    String ywCountQuxian = ycPaymentRecordService.getCountQuxian(quxianNameList.get(k),GlobalConstant.hbd_baoxian_yiwai);
                    String ywSumQuxian = ycPaymentRecordService.getSumQuxian(quxianNameList.get(k),GlobalConstant.hbd_baoxian_yiwai);
                    if(ywSumQuxian == null){
                        ywSumQuxian = "0";
                    }
                    indexmodel3.setYiwaixian(ywCountQuxian+"人/"+ywSumQuxian+"元");

                    String jhrCountQuxian = ycPaymentRecordService.getCountQuxian(quxianNameList.get(k),GlobalConstant.hbd_baoxian_jianhuren);
                    String jhrSumQuxian = ycPaymentRecordService.getSumQuxian(quxianNameList.get(k),GlobalConstant.hbd_baoxian_jianhuren);
                    if(jhrSumQuxian == null){
                        jhrSumQuxian = "0";
                    }
                    indexmodel3.setJianhurenxian(jhrCountQuxian+"人/"+jhrSumQuxian+"元");
                    int totalCountQuxian = Integer.parseInt(xpxCountQuxian)+ Integer.parseInt(ywCountQuxian)+ Integer.parseInt(jhrCountQuxian);
                    int totalSumQuxian = Integer.parseInt(xpxSumQuxian)+ Integer.parseInt(ywSumQuxian)+ Integer.parseInt(jhrSumQuxian);
                    indexmodel3.setTotal(totalCountQuxian+"人次/"+totalSumQuxian+"元");
                    indexmodelSchoolList.add(indexmodel3);
                }
            }

            //excel标题
            String[] title = {"名称","学生平安保险","交通意外、重大疾病险","监护人责任险","合计"};
            //excel文件名
            String fileName = name+"学生缴费统计表"+System.currentTimeMillis()+".xls";
            //sheet名
            String sheetName = name+"学生缴费统计表";
            String[][] content = new String[indexmodelSchoolList.size()+1][title.length];
            for (int i = 0; i < indexmodelSchoolList.size(); i++) {
                Indexmodel obj = indexmodelSchoolList.get(i);
                content[i][0] = obj.getQuxianName();
                content[i][1] = obj.getXuepingxian();
                content[i][2] = obj.getYiwaixian();
                content[i][3] = obj.getJianhurenxian();
                content[i][4] = obj.getTotal();
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
    }
}


