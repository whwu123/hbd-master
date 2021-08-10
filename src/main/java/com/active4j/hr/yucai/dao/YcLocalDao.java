package com.active4j.hr.yucai.dao;

import com.active4j.hr.yucai.entity.YcLocalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YcLocalDao extends BaseMapper<YcLocalEntity>{

    public List<YcLocalEntity> selectFormJiaoYuId(@Param("quXianId")String quXianId, @Param("jiaoYuId") String jiaoYuId,@Param("type") String type);
}
