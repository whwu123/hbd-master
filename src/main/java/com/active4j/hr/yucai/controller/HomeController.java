package com.active4j.hr.yucai.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.util.UUIDUtil;
import com.active4j.hr.yucai.entity.YcInsuranceOrdersEntity;
import com.active4j.hr.yucai.entity.YcLocalEntity;
import com.active4j.hr.yucai.entity.YcOrdersEntity;
import com.active4j.hr.yucai.service.YcInsuranceOrdersService;
import com.active4j.hr.yucai.service.YcLocalService;
import com.active4j.hr.yucai.service.YcOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/ychome")
public class HomeController extends BaseController {

    @Autowired
    private YcLocalService ycLocalService;

    @Autowired
    private YcOrdersService ycOrdersService;

    @Autowired
    private YcInsuranceOrdersService ycInsuranceOrdersService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "yc/hbd/ychome";
    }

    @RequestMapping(value = "/tebie", method = RequestMethod.GET)
    public String tebie(Model model) {
        return "yc/hbd/tebie";
    }

    @RequestMapping(value = "/tiaokuan", method = RequestMethod.GET)
    public String tiaokuan(Model model) {
        return "yc/hbd/tiaokuan";
    }

    @RequestMapping(value = "/diqu", method = RequestMethod.GET)
    public String diqu(Model model) {
        return "yc/hbd/diqu";
    }

    @RequestMapping(value = "/hh_quxian", method = RequestMethod.GET)
    public String quxian(Model model,String diquId) {
        model.addAttribute("diquId",diquId);
        return "yc/hbd/hh_quxian";
    }

    @RequestMapping(value = "/form1", method = RequestMethod.GET)
    public String form1(Model model,String quxianId,String diquId) {

        model.addAttribute("quxianId",quxianId);
        model.addAttribute("diquId",diquId);
        //根据Id查询该县的学校
        List<YcLocalEntity> ycLocalEntityList = ycLocalService.selectFormJiaoYuId(quxianId,null,"3");
        model.addAttribute("ycLocalEntityList",ycLocalEntityList);
        //拿到该县的县下面的学校
        List<YcLocalEntity> xiangzhenList = ycLocalService.selectFormJiaoYuId(quxianId,null,"4");
        model.addAttribute("xiangzhenList",xiangzhenList);
        return "yc/hbd/form1";
    }

    @RequestMapping(value = "/form2", method = RequestMethod.GET)
    public String form2(Model model,String quxianId,String xueshengCard,String xueshengName,String jieduanId,String xuexiaoName,String nianji,String banji,String xuexiaoValue,String diquId,String xiangzhengId) {

        model.addAttribute("xiangzhengId",xiangzhengId);
        model.addAttribute("diquId",diquId);
        model.addAttribute("quxianId",quxianId);
        model.addAttribute("xueshengCard",xueshengCard);
        model.addAttribute("xueshengName",xueshengName);
        model.addAttribute("jieduanId",jieduanId);
        model.addAttribute("xuexiaoName",xuexiaoName);
        model.addAttribute("nianji",nianji);
        model.addAttribute("banji",banji);
        model.addAttribute("xuexiaoValue",xuexiaoValue);

        return "yc/hbd/form2";
    }

    @RequestMapping(value = "/form3", method = RequestMethod.GET)
    public String form3(Model model,String quxianId,String xueshengCard,String xueshengName,String jieduanId,String xuexiaoName,String nianji,String banji,String xuexiaoValue,String diquId,String xiangzhengId,String baoxianStr,String baoxianTotalmoney) {
        model.addAttribute("quxianId",quxianId);
        model.addAttribute("xiangzhengId",xiangzhengId);
        model.addAttribute("diquId",diquId);
        model.addAttribute("xueshengCard",xueshengCard);
        model.addAttribute("xueshengName",xueshengName);
        model.addAttribute("jieduanId",jieduanId);
        model.addAttribute("xuexiaoName",xuexiaoName);
        model.addAttribute("nianji",nianji);
        model.addAttribute("banji",banji);
        model.addAttribute("xuexiaoValue",xuexiaoValue);
        model.addAttribute("baoxianStr",baoxianStr);
        model.addAttribute("baoxianTotalmoney",baoxianTotalmoney);

        //通过学校ID查询出学校有的年级
        List<YcLocalEntity> nianjiList = ycLocalService.selectFormJiaoYuId(xuexiaoValue,null,null);
        model.addAttribute("nianjiList",nianjiList);


        return "yc/hbd/form3";
    }


    /**
     * 根据教育机构查询该县的学校
     */
    @RequestMapping(value = "/selectFormJiaoYuId", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson selectFormJiaoYuId(String quXianId, String jiaoYuId, HttpServletRequest request,String xiangzhenId) {
        AjaxJson j = new AjaxJson();
        if(quXianId!=null && !quXianId.isEmpty()){
                List<YcLocalEntity> xuexianList = new ArrayList<>();
                String xiangzhenType = "3";
                if(xiangzhenId!=null && !xiangzhenId.isEmpty()){
                    //拿到该县的镇下面的学校

                    xuexianList = ycLocalService.selectFormJiaoYuId(xiangzhenId,jiaoYuId,xiangzhenType);
                }else{
                    //拿到该县的县下面的学校
                    xuexianList = ycLocalService.selectFormJiaoYuId(quXianId,jiaoYuId,xiangzhenType);
                }



                j.setObj(xuexianList);

        }
        j.setMsg("获取数据成功");
        return j;
    }


    /**
     * 查询该县乡镇的学校
     */
    @RequestMapping(value = "/selectFormxiangzhenId", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson selectFormxiangzhenId(String xiangzhenId, String jiaoYuId, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        if(xiangzhenId!=null && !xiangzhenId.isEmpty()){

            //拿到该县的县下面的学校
            String type = "3";
            List<YcLocalEntity> xuexianList = ycLocalService.selectFormJiaoYuId(xiangzhenId,jiaoYuId,type);
            j.setObj(xuexianList);
        }
        j.setMsg("获取数据成功");
        return j;
    }

    /**
     * 查询该学校的年级
     */
    @RequestMapping(value = "/selectFormXueXiaoId", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson selectFormXueXiaoId(String xuexiaoId,  HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        if(xuexiaoId!=null && !xuexiaoId.isEmpty()){
            List<YcLocalEntity> nianjiList = ycLocalService.selectFormJiaoYuId(xuexiaoId,null,null);
            j.setObj(nianjiList);
        }
        j.setMsg("获取数据成功");
        return j;
    }


    /**
     *存入订单信息
     */
    @RequestMapping(value = "/saveOrders", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson saveOrders(YcOrdersEntity entity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        //获取得到地区名称
        YcLocalEntity diquEntity = ycLocalService.getById(entity.getDiquId());
        if(diquEntity!= null ){
            entity.setDiqiName(diquEntity.getName());
        }

        //获取得到区县名称
        YcLocalEntity quxianEntity = ycLocalService.getById(entity.getQuxianId());
        if(quxianEntity!= null ){
            entity.setQuxianName(quxianEntity.getName());
        }

        //获取得到乡镇名称
        YcLocalEntity xiangzhenEntity = ycLocalService.getById(entity.getXiangzhenId());
        if(xiangzhenEntity!= null ){
            entity.setXiangzhenName(xiangzhenEntity.getName());
        }
        String uuidOrders = UUIDUtil.getUUID();
        entity.setId(uuidOrders);
        try{
            ycOrdersService.save(entity);
            //把保险信息存入进数据库
            String baoxianType = entity.getBaoxianType();
            //对baoxianType数据进行处理
            String[] typeList  = baoxianType.split(",");
            if(typeList.length>0){
                for (int i=0;i<typeList.length;i++){
                    String[] typeStr = typeList[i].split("_");

                    YcInsuranceOrdersEntity insuranceOrdersEntity = new YcInsuranceOrdersEntity();
                    insuranceOrdersEntity.setBanJi(entity.getBanJi());
                    insuranceOrdersEntity.setDiqiName(diquEntity.getName());
                    insuranceOrdersEntity.setDiquId(diquEntity.getId());
                    insuranceOrdersEntity.setQuxianName(quxianEntity.getName());
                    insuranceOrdersEntity.setQuxianId(quxianEntity.getId());
                    insuranceOrdersEntity.setXiangzhenName(xiangzhenEntity.getName());
                    insuranceOrdersEntity.setXiangzhenId(xiangzhenEntity.getId());
                    insuranceOrdersEntity.setXuexiaoName(entity.getXuexiaoName());
                    insuranceOrdersEntity.setXuexiaoId(entity.getXuexiaoId());
                    insuranceOrdersEntity.setStudentName(entity.getStudentName());
                    insuranceOrdersEntity.setStudentCard(entity.getStudentCard());
                    insuranceOrdersEntity.setNianjiId(entity.getNianjiId());
                    insuranceOrdersEntity.setNianjiName(entity.getNianjiName());
                    insuranceOrdersEntity.setBaoxianOrdersId(uuidOrders);
                    insuranceOrdersEntity.setBaoxianType(typeStr[0]);
                    insuranceOrdersEntity.setBaoxianName(typeStr[1]);
                    insuranceOrdersEntity.setBaoxianMoney(typeStr[2]);
                    ycInsuranceOrdersService.save(insuranceOrdersEntity);




                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        j.setMsg("获取数据成功");
        return j;
    }
}
