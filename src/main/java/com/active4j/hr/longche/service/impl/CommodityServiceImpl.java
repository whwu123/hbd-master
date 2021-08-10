package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.CommodityDao;
import com.active4j.hr.longche.entity.CommodityEntity;
import com.active4j.hr.longche.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("commodityService")
@Transactional
public class CommodityServiceImpl extends ServiceImpl<CommodityDao , CommodityEntity > implements CommodityService{

}
