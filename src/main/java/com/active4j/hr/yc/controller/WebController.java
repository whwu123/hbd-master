package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.common.constant.GlobalConstant;
import com.active4j.hr.yc.entity.Indexmodel;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcUpdateStulog;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.service.YcUpdateStulogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/webController")
public class WebController extends BaseController {

    @Autowired
    private YcUpdateStulogService ycUpdateStulogService;

    @Autowired
    private YcPaymentRecordService ycPaymentRecordService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "yc/web/selectStu";
    }
    @RequestMapping(value = "/selectStuContent", method = RequestMethod.POST)
    public String index(Model model,String xueshengCard,String payNum,String toubaorenPhone) {
        //通过身份证号和支付流水号去查询学生信息
        QueryWrapper<YcPaymentRecord> queryWrapper = new QueryWrapper<>();
        List<YcPaymentRecord> list = new ArrayList<>();
        if(xueshengCard!=null && !xueshengCard.isEmpty()){
            queryWrapper.eq("student_card",xueshengCard);
            list =  ycPaymentRecordService.list(queryWrapper);

        }else if(payNum!=null && !payNum.isEmpty()){
            //通过支付流水号查询得到缴费记录，然后通过身份证号去查询学生信息

            queryWrapper.eq("pay_money",payNum);
            list =  ycPaymentRecordService.list(queryWrapper);
        }else if(toubaorenPhone!=null && !toubaorenPhone.isEmpty()){

            queryWrapper.eq("toubaoren_phone",toubaorenPhone);
            list =  ycPaymentRecordService.list(queryWrapper);

        }
        if(list.size()>0){
            model.addAttribute("prList",list);
            model.addAttribute("ycPaymentRecord",list.get(0));
        }

        return "yc/web/selectStu2";
    }



    @RequestMapping(value = "/updateStu", method = RequestMethod.GET)
    public String updateStu(Model model,String studentCard,String zhifuNumber) {
        //通过身份证号和支付流水号去查询学生信息
        QueryWrapper<YcPaymentRecord> queryWrapper = new QueryWrapper<>();
        List<YcPaymentRecord> list = new ArrayList<>();
        if(studentCard!=null && !studentCard.isEmpty()){
            queryWrapper.eq("student_card",studentCard);
            list =  ycPaymentRecordService.list(queryWrapper);

        }else if(zhifuNumber!=null && !zhifuNumber.isEmpty()){
            //通过支付流水号查询得到缴费记录，然后通过身份证号去查询学生信息
            queryWrapper.eq("zhifu_number",zhifuNumber);
            list =  ycPaymentRecordService.list(queryWrapper);
        }
        if(list.size()>0){
            model.addAttribute("prList",list);
            model.addAttribute("ycPaymentRecord",list.get(0));
        }
        return "yc/web/updateStu";
    }

    @RequestMapping(value = "/doUpdateStu", method = RequestMethod.POST)
    public String doUpdateStu(Model model, YcUpdateStulog ycUpdateStulog) {
        if(ycUpdateStulog!=null){
            ycUpdateStulog.setState("1");
            ycUpdateStulogService.save(ycUpdateStulog);
            model.addAttribute("ycUpdateStulog",ycUpdateStulog);
        }
        return "yc/web/updateSuc";
    }

    @RequestMapping(value = "/toSchoolIndex", method = RequestMethod.GET)
    public String toSchoolIndex(Model model, String name) {
        if(name!=null && !name.isEmpty()){
            List<Indexmodel> indexmodelSchoolList = new ArrayList<>();
            Indexmodel xuexiaoModel = new Indexmodel();
            xuexiaoModel.setSchoolName(name);
            //查询学校学平险的总人数和总金额
            String totalXpxSchoolCount = ycPaymentRecordService.getCountSchool(name, GlobalConstant.hbd_baoxian_xuesheng);
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
            model.addAttribute("xuexiaoName",name);
            model.addAttribute("xuexiaoModel",xuexiaoModel);
            //根据学校名称拿到年级
            List<String> nianjiNameList = ycPaymentRecordService.getnianjiNameBySchoolName(name);
            model.addAttribute("nianjiNameList",nianjiNameList);

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
            model.addAttribute("indexmodelSchoolList",indexmodelSchoolList);

        }
        return "main/console2";
    }
}
