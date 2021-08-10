package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.CommodityTypeDao;
import com.active4j.hr.longche.entity.CommodityTypeEntity;
import com.active4j.hr.longche.service.CommodityTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("commodityTypeService")
@Transactional
public class CommodityTypeServiceImpl extends ServiceImpl<CommodityTypeDao , CommodityTypeEntity > implements CommodityTypeService{

}
