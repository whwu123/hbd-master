package com.active4j.hr.yc.dao;

import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YcPaymentRecordDao extends BaseMapper<YcPaymentRecord> {

    /**
     * @description
     *  	根据区县名称查询出区县下面的所用缴费学校
     * @return List<String>
     */
    public List<String> getschoolNameByQuxianName(@Param("quxianName")String quxianName);


    /**
     * @description
     *  	根据学校名称和险种类型查询出缴费总人数
     * @return
     */
    public String getCount(@Param("schoolName")String schoolName ,@Param("typebaoxian")String type );

    /**
     * @description
     *  	根据学校名称和险种类型查询出缴费总金额
     * @return List<
     */
    public String getSum(@Param("schoolName")String schoolName ,@Param("typebaoxian")String type );


    /**
     * @description
     *  	根据区县名称和险种类型查询出缴费总人数
     * @return
     */
    public String getCountQuxian(@Param("quxianDepartment")String quxianDepartment ,@Param("typebaoxian")String type );

    /**
     * @description
     *  	根据区县名称和险种类型查询出缴费总金额
     * @return List<
     */
    public String getSumQuxian(@Param("quxianDepartment")String quxianDepartment ,@Param("typebaoxian")String type );



    /**
     * @description
     *  	根据学校名称拿到学校的缴费年级名称
     * @return List<String>
     */
    public List<String> getnianjiNameBySchoolName(@Param("quxianName")String schoolName);

}
