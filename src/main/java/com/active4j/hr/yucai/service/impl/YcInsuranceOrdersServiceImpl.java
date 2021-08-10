package com.active4j.hr.yucai.service.impl;

import com.active4j.hr.yucai.dao.YcInsuranceOrdersDao;
import com.active4j.hr.yucai.dao.YcOrdersDao;
import com.active4j.hr.yucai.entity.YcInsuranceOrdersEntity;
import com.active4j.hr.yucai.entity.YcOrdersEntity;
import com.active4j.hr.yucai.service.YcInsuranceOrdersService;
import com.active4j.hr.yucai.service.YcOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author teli_
 *
 */
@Service("ycInsuranceOrdersService")
@Transactional
public class YcInsuranceOrdersServiceImpl extends ServiceImpl<YcInsuranceOrdersDao, YcInsuranceOrdersEntity> implements YcInsuranceOrdersService {



}
