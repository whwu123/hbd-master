package com.active4j.hr.yc.service.impl;


import com.active4j.hr.yc.dao.YcUpdateStulogDao;
import com.active4j.hr.yc.entity.YcUpdateStulog;
import com.active4j.hr.yc.service.YcUpdateStulogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ycUpdateStulog")
@Transactional
public class YcUpdateStulogServiceImpl extends ServiceImpl<YcUpdateStulogDao, YcUpdateStulog> implements YcUpdateStulogService {

}
