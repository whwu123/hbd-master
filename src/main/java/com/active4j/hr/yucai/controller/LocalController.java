package com.active4j.hr.yucai.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.web.tag.TagUtil;
import com.active4j.hr.core.web.tag.model.TreeDataGrid;
import com.active4j.hr.core.web.tag.model.tree.TSDepartTreeData;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysDicValueEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.util.SystemUtils;
import com.active4j.hr.yucai.entity.YcLocalEntity;
import com.active4j.hr.yucai.service.YcLocalService;
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
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/local")
public class LocalController extends BaseController {

    @Autowired
    private YcLocalService ycLocalService;

    @RequestMapping(value = "/localList", method = RequestMethod.GET)
    public String localList(Model model) {
        return "yucai/local/localList";
    }
    /**
     * 跳转到机构的新增页面
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView add(YcLocalEntity depart, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yucai/local/local");
        //获取教育阶段的数据字典
        List<SysDicValueEntity> lstJiaoyu = SystemUtils.getDictionaryLst("jiaoyujieduan");
        view.addObject("lstJiaoyu", lstJiaoyu);
        //获取部门类型的数据字典
        List<SysDicValueEntity> lstTypes = SystemUtils.getDictionaryLst("yclocal");
        view.addObject("types", lstTypes);

        return view;
    }

    /**
     * 部门列表页面跳转
     *
     * @return
     */
    @RequestMapping("/update")
    public ModelAndView update(YcLocalEntity depart, HttpServletRequest req) {
        ModelAndView view = new ModelAndView("yucai/local/local");

        //获取部门类型的数据字典
        List<SysDicValueEntity> lstTypes = SystemUtils.getDictionaryLst("yclocal");
        view.addObject("types", lstTypes);

        //获取教育阶段的数据字典
        List<SysDicValueEntity> lstJiaoyu = SystemUtils.getDictionaryLst("jiaoyujieduan");
        view.addObject("lstJiaoyu", lstJiaoyu);

        depart = ycLocalService.getById(depart.getId());
        view.addObject("depart", depart);

        YcLocalEntity parentDept = ycLocalService.getById(depart.getParentId());
        if(null != parentDept) {
            view.addObject("parentDepartName", parentDept.getName());
        }

        return view;
    }

    /**
     * 菜单列表--树形结构
     */
    @RequestMapping("/localTreeGrid")
    public void localTreeGrid(HttpServletRequest request, HttpServletResponse response, TreeDataGrid dataGrid) {
        // 生成树形表格菜单的集合
        List<TSDepartTreeData> lstDatas = ycLocalService.getTreeDepartList();

        dataGrid.setPage(1);
        dataGrid.setResults(lstDatas);
        dataGrid.setRows(1000);
        dataGrid.setTotal(lstDatas.size());
        dataGrid.setTotalPage(1);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     *
     * @description
     *  	机构保存
     */
    @RequestMapping("/save")
    @ResponseBody
    @Log(type = LogType.save, name = "保存育才机构信息", memo = "新增或编辑保存了机构信息")
    public AjaxJson save(YcLocalEntity depart, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        try {
            if(StringUtils.isEmpty(depart.getParentId())) {
                depart.setLevel(0);
                depart.setParentId(null);
            }else {
                YcLocalEntity parent = ycLocalService.getById(depart.getParentId());
                depart.setLevel(parent.getLevel() + 1);
            }

            if(StringUtils.isNotEmpty(depart.getParentId()) && StringUtils.equals(depart.getParentId(), depart.getId())) {
                j.setSuccess(false);
                j.setMsg("上级部门不能选择自己，请重新选择");
                return j;
            }

            if (StringUtils.isNotEmpty(depart.getId())) {
                //编辑保存
                YcLocalEntity tmp = ycLocalService.getById(depart.getId());
                MyBeanUtils.copyBeanNotNull2Bean(depart, tmp);
                if(StringUtils.isEmpty(depart.getParentId())) {
                    tmp.setParentId(null);
                }
                ycLocalService.saveOrUpdate(tmp);
            }else {
                //新增保存
                ycLocalService.save(depart);
            }
        }catch(Exception e) {
            log.error("保存部门信息报错，错误信息:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("保存部门错误");
            e.printStackTrace();
        }

        return j;
    }

    /**
     * 删除机构
     *
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    @Log(type = LogType.del, name = "删除机构信息", memo = "删除了机构信息")
    public AjaxJson del(YcLocalEntity depart, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        try {
            if(StringUtils.isNotEmpty(depart.getId())) {
                //查询机构下面的用户，存在用户则不能删除该机构
                List<SysUserEntity> lstUsers = ycLocalService.getUsersByDept(depart.getId());
                if(null != lstUsers && lstUsers.size() > 0) {
                    j.setSuccess(false);
                    j.setMsg("该机构下存在用户，不能直接删除");
                    return j;
                }

                List<YcLocalEntity> lstDepts = ycLocalService.getChildDeptsByDeptId(depart.getId());
                if(null != lstDepts && lstDepts.size() > 0) {
                    j.setSuccess(false);
                    j.setMsg("该机构下存在子机构，不能直接删除");
                    return j;
                }

                //删除部门
                ycLocalService.removeById(depart.getId());
            }

        }catch(Exception e) {
            log.error("删除机构信息报错，错误信息:" + e.getMessage());
            j.setSuccess(false);
            j.setMsg("删除机构错误");
            e.printStackTrace();
        }

        return j;
    }



}
