package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.CouponDao;
import com.active4j.hr.longche.entity.CouponEntity;
import com.active4j.hr.longche.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("couponService")
@Transactional
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService{

}
