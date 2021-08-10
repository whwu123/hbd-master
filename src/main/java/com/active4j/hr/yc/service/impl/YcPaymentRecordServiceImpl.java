package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcPaymentRecordDao;
import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.active4j.hr.yc.service.YcPaymentRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public List<String> getnianjiNameBySchoolName(String schoolName) {
        return this.baseMapper.getnianjiNameBySchoolName(schoolName);
    }

    @Override
    public String getCountSchool(String schoolName, String type) {
        return this.baseMapper.getCountSchool(schoolName,type);
    }

    @Override
    public String getSumSchool(String schoolName, String type) {
        return this.baseMapper.getSumSchool(schoolName,type);
    }

    @Override
    public String getCountNianjji(String nianjiName, String type,String schoolName) {
        return this.baseMapper.getCountNianjji(nianjiName,type,schoolName);
    }

    @Override
    public String getSumNianjji(String nianjiName, String type,String schoolName) {
        return this.baseMapper.getSumNianjji(nianjiName,type,schoolName);
    }
}
