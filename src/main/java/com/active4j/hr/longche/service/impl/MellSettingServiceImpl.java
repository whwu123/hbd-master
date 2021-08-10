package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.MellSettingDao;
import com.active4j.hr.longche.entity.MellSettingEntity;
import com.active4j.hr.longche.service.MellSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("mellSettingService")
@Transactional
public class MellSettingServiceImpl extends ServiceImpl<MellSettingDao, MellSettingEntity> implements MellSettingService{

}
