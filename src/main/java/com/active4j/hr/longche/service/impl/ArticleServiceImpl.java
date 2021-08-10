package com.active4j.hr.longche.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.ArticleDao;
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("articleService")
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleDao , ArticleEntity > implements ArticleService{

}
