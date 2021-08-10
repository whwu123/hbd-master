package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("yc_update_stulog")
@Getter
@Setter
public class YcUpdateStulog extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325838838L;


    @TableField("quxian_department")
    @QueryField(queryColumn="quxian_department", condition= QueryCondition.like)
    private String quxianDepartment;

    @TableField("student_name")
    @QueryField(queryColumn="student_name", condition= QueryCondition.eq)
    private String studentName;

    @TableField("student_card")
    @QueryField(queryColumn="student_card", condition= QueryCondition.like)
    private String studentCard;

    @TableField("student_school")
    @QueryField(queryColumn="student_school", condition= QueryCondition.like)
    private String studentSchool;

    @TableField("student_nianji")
    @QueryField(queryColumn="student_nianji", condition= QueryCondition.like)
    private String studentNianji;

    @TableField("student_banji")
    @QueryField(queryColumn="student_banji", condition= QueryCondition.like)
    private String studentBanji;

    @TableField("toubaoren_name")
    @QueryField(queryColumn="toubaoren_name", condition= QueryCondition.like)
    private String toubaorenName;

    @TableField("toubaoren_phone")
    @QueryField(queryColumn="toubaoren_phone", condition= QueryCondition.like)
    private String toubaorenPhone;

    @TableField("memo")
    @QueryField(queryColumn="memo", condition= QueryCondition.like)
    private String memo;

    @TableField("yc_state")
    @QueryField(queryColumn="yc_state", condition= QueryCondition.eq)
    private String state;
}
