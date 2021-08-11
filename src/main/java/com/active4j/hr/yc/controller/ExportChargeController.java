package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.service.YcStudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Controller
@Slf4j
@RequestMapping("/exportChargeController")
public class ExportChargeController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private YcStudentService ycStudentService;

    @Autowired
    private YcPaymentRecordService ycPaymentRecordService;

    @RequestMapping("/export")
    @ResponseBody
    public AjaxJson uploadFiles(String db, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        AjaxJson j = new AjaxJson();
        try{
            Map<String, MultipartFile> fileMap = request.getFileMap();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                MultipartFile mf = entity.getValue();// 获取上传文件对象
                InputStream in = mf.getInputStream();
                // 1.创建workbook对象，读取整个文档
                XSSFWorkbook wb = new XSSFWorkbook(in);
                // 2.读取页脚sheet
                XSSFSheet sheet = wb.getSheetAt(0);

                // 3.循环读取某一行
                int index = 0;
                for (Row row : sheet) {
                    // 4.读取每一行的单元格
                    if (index == 0) {
                        index++;
                        continue;
                    }
                    String stringCellValue = row.getCell(0).getStringCellValue();
                    String stringCellValue2 = row.getCell(1).getStringCellValue();
                    String stringCellValue3 = row.getCell(2).getStringCellValue();
                    //int stringCellValue4 = (int)row.getCell(3).getNumericCellValue();
                   //插入到部门数据库
                    SysDeptEntity sysDeptEntity = new SysDeptEntity();
                    sysDeptEntity.setDeptNo(stringCellValue2);
                    sysDeptEntity.setLevel(2);
                    sysDeptEntity.setType("1");
                    sysDeptEntity.setName(stringCellValue);
                    sysDeptEntity.setParentId(stringCellValue3);
                    sysDeptService.save(sysDeptEntity);
                }
            }
        }catch(Exception e){
            j.setSuccess(false);
            j.setMsg("導入數據是失敗");
            log.error("導入數據是失敗报错，错误信息：｛｝", e.getMessage());
        }
        return j;
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "yc/student/export";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index2(Model model) {
        return "yc/student/export2";
    }


    @RequestMapping(value = "/index3", method = RequestMethod.GET)
    public String index3(Model model) {
        return "yc/paymentrecord/export";
    }

    @RequestMapping("/export2")
    @ResponseBody
    public AjaxJson uploadFiles2(String db, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        AjaxJson j = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Map<String, MultipartFile> fileMap = request.getFileMap();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

                MultipartFile mf = entity.getValue();// 获取上传文件对象
                InputStream in = mf.getInputStream();
                // 1.创建workbook对象，读取整个文档
                XSSFWorkbook wb = new XSSFWorkbook(in);
                // 2.读取页脚sheet
                XSSFSheet sheet = wb.getSheetAt(0);

                // 3.循环读取某一行
                int index = 0;
                for (Row row : sheet) {
                    // 4.读取每一行的单元格
                    if (index == 0) {
                        index++;
                        continue;
                    }
                    String stringCellValue1 = row.getCell(0).getStringCellValue();
                    String stringCellValue2 = row.getCell(1).getStringCellValue();
                    String stringCellValue3 = row.getCell(2).getStringCellValue();
                    String stringCellValue4 = row.getCell(3).getStringCellValue();
                    String stringCellValue5 = row.getCell(4).getStringCellValue();
                    String stringCellValue6 = row.getCell(5).getStringCellValue();
                    String stringCellValue7 = row.getCell(6).getStringCellValue();
                    String stringCellValue8 = row.getCell(7).getStringCellValue();
                    String stringCellValue9 = row.getCell(8).getStringCellValue();
                    String stringCellValue10 = row.getCell(9).getStringCellValue();
                    String stringCellValue11 = row.getCell(10).getStringCellValue();
                    String stringCellValue12 = row.getCell(11).getStringCellValue();
                    String stringCellValue13 = row.getCell(12).getStringCellValue();


                    //插入到学生信息数据库
                    YcStudentEntity ycStudentEntity = new YcStudentEntity();
                    ycStudentEntity.setStudentName(stringCellValue1);
                    ycStudentEntity.setStudentSex(stringCellValue2);
                    ycStudentEntity.setStudentCard(stringCellValue4);
                    ycStudentEntity.setStudentBirthday(sdf.parse(stringCellValue5));
                    ycStudentEntity.setStudentPhone(stringCellValue6);
                    ycStudentEntity.setStudentSchoolBm(stringCellValue7);
                    ycStudentEntity.setStudentSchool(stringCellValue8);
                    ycStudentEntity.setStudentXueli(stringCellValue10);
                    ycStudentEntity.setStudentNianji(stringCellValue11);
                    ycStudentEntity.setStudentBanji(stringCellValue12);
                    ycStudentEntity.setQuxianDepartment(stringCellValue13);

                    ycStudentService.save(ycStudentEntity);
                }
            }

        }catch(Exception e){
            j.setSuccess(false);
            j.setMsg("導入數據是失敗");
            log.error("導入數據是失敗报错，错误信息：｛｝", e.getMessage());
        }

        return j;
    }


    /**
     * 导入缴费记录
     */
    @RequestMapping("/exportPayMentRecord")
    @ResponseBody
    public AjaxJson exportPayMentRecord(String db, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        AjaxJson j = new AjaxJson();
        try{
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Map<String, MultipartFile> fileMap = request.getFileMap();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

                MultipartFile mf = entity.getValue();// 获取上传文件对象
                InputStream in = mf.getInputStream();
                // 1.创建workbook对象，读取整个文档
                XSSFWorkbook wb = new XSSFWorkbook(in);
                // 2.读取页脚sheet
                XSSFSheet sheet = wb.getSheetAt(0);

                // 3.循环读取某一行
                int index = 0;
                for (Row row : sheet) {
                    // 4.读取每一行的单元格
                    if (index == 0) {
                        index++;
                        continue;
                    }
                    String shiDepartment = row.getCell(0).getStringCellValue();
                    String quxianDepartment = row.getCell(1).getStringCellValue();
                    String studentName = row.getCell(2).getStringCellValue();
                    String studentCard = row.getCell(3).getStringCellValue();
                    String schoolName = row.getCell(4).getStringCellValue();
                    String studentNianJi = row.getCell(6).getStringCellValue();
                    String studentBanJi = row.getCell(7).getStringCellValue();
                    String casName = row.getCell(8).getStringCellValue();
                    String payMoney = row.getCell(9).getStringCellValue();
                    String typeXianzhong = row.getCell(10).getStringCellValue();
                    String baoxianMoney = row.getCell(11).getStringCellValue();
                    String creatDate =  row.getCell(20).getStringCellValue();;
                    String zhifNumber = row.getCell(14).getStringCellValue();
                    String gongyingshang = row.getCell(15).getStringCellValue();
                    String tbrName = row.getCell(17).getStringCellValue();
                    String tbrPhone = row.getCell(18).getStringCellValue();
                    String careatName = row.getCell(19).getStringCellValue();

                    //插入到缴费记录表
                    YcPaymentRecord ycPaymentRecord = new YcPaymentRecord();
                    ycPaymentRecord.setShiDepartment(shiDepartment);
                    ycPaymentRecord.setQuxianDepartment(quxianDepartment);
                    ycPaymentRecord.setStudentName(studentName);
                    ycPaymentRecord.setStudentCard(studentCard);
                    ycPaymentRecord.setStudentSchool(schoolName);
                    ycPaymentRecord.setStudentNianji(studentNianJi);
                    ycPaymentRecord.setStudentBanji(studentBanJi);
                    ycPaymentRecord.setCasName(casName);
                    ycPaymentRecord.setPayMoney(payMoney);
                    ycPaymentRecord.setType(typeXianzhong);
                    ycPaymentRecord.setBaofeiMoney(baoxianMoney);

                    Date date=new Date(creatDate);
                    String dateTime=sdf.format(date);
                    ycPaymentRecord.setCreateDate(sdf.parse(dateTime));
                    ycPaymentRecord.setZhifuNumber(zhifNumber);
                    ycPaymentRecord.setSupplier(gongyingshang);
                    ycPaymentRecord.setToubaorenName(tbrName);
                    ycPaymentRecord.setToubaorenPhone(tbrPhone);
                    ycPaymentRecord.setCreateName(careatName);
                    ycPaymentRecordService.save(ycPaymentRecord);
                }
            }

        }catch(Exception e){
            j.setSuccess(false);
            j.setMsg("导入数据是失敗");
            log.error("导入数据是失敗报错，错误信息：｛｝", e.getMessage());
        }

        return j;
    }
}
