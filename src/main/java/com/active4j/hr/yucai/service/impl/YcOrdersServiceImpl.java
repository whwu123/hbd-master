package com.active4j.hr.yucai.service.impl;

import com.active4j.hr.yucai.dao.YcOrdersDao;
import com.active4j.hr.yucai.entity.YcOrdersEntity;
import com.active4j.hr.yucai.service.YcOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门管理service类
 * 
 * @author teli_
 *
 */
@Service("ycOrdersService")
@Transactional
public class YcOrdersServiceImpl extends ServiceImpl<YcOrdersDao, YcOrdersEntity> implements YcOrdersService {



}
