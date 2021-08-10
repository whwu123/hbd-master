package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcPaymentRecordDao;
import com.active4j.hr.yc.dao.YcStudentDao;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.active4j.hr.yc.service.YcStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("ycPaymentRecordService")
@Transactional
public class YcPaymentRecordServiceImpl extends ServiceImpl<YcPaymentRecordDao, YcPaymentRecord> implements YcPaymentRecordService {

    @Override
    public List<String> getschoolNameByQuxianName(String quxianName) {
        return this.baseMapper.getschoolNameByQuxianName(quxianName);
    }

    @Override
    public String getCount(String schoolName, String type) {
        return this.baseMapper.getCount(schoolName,type);
    }

    @Override
    public String getSum(String schoolName, String type) {
        return this.baseMapper.getSum(schoolName,type);
    }

    @Override
    public String getCountQuxian(String quxianDepartment, String type) {
        return this.baseMapper.getCountQuxian(quxianDepartment,type);
    }

    @Override
    public String getSumQuxian(String quxianDepartment, String type) {
        return this.baseMapper.getSumQuxian(quxianDepartment,type);
    }
}
