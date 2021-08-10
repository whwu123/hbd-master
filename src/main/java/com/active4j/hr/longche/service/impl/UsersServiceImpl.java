package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.ArticleDao;
import com.active4j.hr.longche.dao.UsersDao;
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.entity.UsersEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.longche.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("usersService")
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersDao , UsersEntity> implements UsersService{

}
