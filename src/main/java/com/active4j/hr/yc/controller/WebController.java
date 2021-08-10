package com.active4j.hr.yc.controller;

import com.active4j.hr.base.controller.BaseController;
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

import java.sql.Wrapper;
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
}
