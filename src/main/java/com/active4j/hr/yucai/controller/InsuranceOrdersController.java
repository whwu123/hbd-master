package com.active4j.hr.yucai.controller;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.util.ListUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.yucai.entity.YcInsuranceOrdersEntity;
import com.active4j.hr.yucai.entity.YcLocalEntity;
import com.active4j.hr.yucai.entity.YcOrdersEntity;
import com.active4j.hr.yucai.service.YcInsuranceOrdersService;
import com.active4j.hr.yucai.service.YcLocalService;
import com.active4j.hr.yucai.service.YcOrdersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ycInsuranceOrders")
public class InsuranceOrdersController extends BaseController {

    @Autowired
    private YcLocalService ycLocalService;

    @Autowired
    private YcOrdersService ycOrdersService;

    @Autowired
    private YcInsuranceOrdersService ycInsuranceOrdersService;

    /**
     * 菜单列表页面跳转
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView("yucai/insurenceOrder/list");

        // 给部门查询条件中的下拉框准备数据
        List<YcLocalEntity> lstDeparts = ycLocalService.list();
         view.addObject("departsReplace", ListUtils.listToReplaceStr(lstDeparts, "name", "id"));

        return view;
    }

    /**
     *
     * @description
     *  	表格数据显示
     */
    @RequestMapping("/datagrid")
    public void datagrid(YcInsuranceOrdersEntity entity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //拼接查询条件
        QueryWrapper<YcInsuranceOrdersEntity> queryWrapper = QueryUtils.installQueryWrapper(entity, request.getParameterMap(), dataGrid);

        //执行查询
        IPage<YcInsuranceOrdersEntity> lstResult = ycInsuranceOrdersService.page(new Page<YcInsuranceOrdersEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);

        //输出结果
        ResponseUtil.writeJson(response, dataGrid, lstResult);

    }

}
