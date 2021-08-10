package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.UsersBankDao;
import com.active4j.hr.longche.entity.UsersBankEntity;
import com.active4j.hr.longche.service.UsersBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("usersBankService")
@Transactional
public class UsersBankServiceImpl extends ServiceImpl<UsersBankDao , UsersBankEntity> implements UsersBankService{

}
