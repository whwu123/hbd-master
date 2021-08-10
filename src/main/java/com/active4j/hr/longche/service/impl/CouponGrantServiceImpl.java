package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.CouponGrantDao;
import com.active4j.hr.longche.entity.CouponGrantEntity;
import com.active4j.hr.longche.service.CouponGrantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("couponGrantService")
@Transactional
public class CouponGrantServiceImpl extends ServiceImpl<CouponGrantDao , CouponGrantEntity > implements CouponGrantService{

}
