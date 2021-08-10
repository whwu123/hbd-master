package com.active4j.hr.yc.service;

import com.active4j.hr.yc.entity.YcPaymentRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface YcPaymentRecordService extends IService<YcPaymentRecord>{

    /**
     * @description
     *  	根据区县名称查询出区县下面的所用缴费学校
     * @return List<String>
     */
    public List<String> getschoolNameByQuxianName(String quxianName);


    /**
     * @description
     *  	根据学校名称和险种类型查询出缴费总人数
     * @return List<String>
     */
    public String getCount(String schoolName ,String type );

    /**
     * @description
     *  	根据学校名称和险种类型查询出缴费总金额
     * @return List<String>
     */
    public String getSum(String schoolName ,String type );

    /**
     * @description
     *  	根据区县名称和险种类型查询出缴费总人数
     * @return
     */
    public String getCountQuxian(String quxianDepartment , String type );

    /**
     * @description
     *  	根据区县名称和险种类型查询出缴费总金额
     * @return List<
     */
    public String getSumQuxian(String quxianDepartment ,String type );

    /**
     * @description
     *  	根据学校名称拿到学校的缴费年级名称
     * @return List<String>
     */
    public List<String> getnianjiNameBySchoolName(String schoolName);

    /**
     * @description
     *  	根据学校名称和险种类型查询出缴费总人数
     * @return
     */
    public String getCountSchool(String schoolName , String type );

    /**
     * @description
     *  	根据学校名称和险种类型查询出缴费总金额
     * @return List<
     */
    public String getSumSchool(String schoolName ,String type );

    /**
     * @description
     *  	根据年级名称和险种类型查询出缴费总人数
     * @return
     */
    public String getCountNianjji(String nianjiName , String type,String schoolName );

    /**
     * @description
     *  	根据年级名称和险种类型查询出缴费总金额
     * @return List<
     */
    public String getSumNianjji(String nianjiName ,String type,String schoolName);

}
