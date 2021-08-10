package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.MaintenanceDao;
import com.active4j.hr.longche.entity.MaintenanceEntity;
import com.active4j.hr.longche.service.MaintenanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("maintenanceService")
@Transactional
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceDao, MaintenanceEntity> implements MaintenanceService{

}
