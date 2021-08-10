package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.SalesReturnDao;
import com.active4j.hr.longche.entity.SalesReturnEntity;
import com.active4j.hr.longche.service.SalesReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service("salesReturnService")
@Transactional
public class SalesReturnServiceImpl extends ServiceImpl<SalesReturnDao, SalesReturnEntity> implements SalesReturnService{

}
