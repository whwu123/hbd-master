package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("yc_student")
@Getter
@Setter
public class YcStudentEntity  extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325838838L;


    @TableField("student_name")
    @QueryField(queryColumn="student_name", condition= QueryCondition.like)
    private String studentName;

    @TableField("student_sex")
    @QueryField(queryColumn="student_sex", condition= QueryCondition.eq)
    private String studentSex;

    @TableField("student_card")
    @QueryField(queryColumn="student_card", condition= QueryCondition.like)
    private String studentCard;

    @TableField("student_birthday")
    private Date studentBirthday;

    @TableField("student_phone")
    private String studentPhone;

    @TableField("student_school")
    @QueryField(queryColumn="student_school", condition= QueryCondition.like)
    private String studentSchool;

    @TableField("student_school_bm")
    @QueryField(queryColumn="student_school_bm", condition= QueryCondition.like)
    private String studentSchoolBm;

    @TableField("student_xueli")
    @QueryField(queryColumn="student_xueli", condition= QueryCondition.like)
    private String studentXueli;

    @TableField("student_nianji")
    @QueryField(queryColumn="student_nianji", condition= QueryCondition.like)
    private String studentNianji;

    @TableField("student_banji")
    @QueryField(queryColumn="student_banji", condition= QueryCondition.like)
    private String studentBanji;

    @TableField("yc_quxian")
    @QueryField(queryColumn="yc_quxian", condition= QueryCondition.like)
    private String quxianDepartment;

}
