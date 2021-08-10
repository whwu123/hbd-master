package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_paymentrecord")
@Getter
@Setter
public class YcPaymentRecord extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = -3440862564325848838L;


    @TableField("shi_department")
    private String shiDepartment;

    @TableField("quxian_department")
    @QueryField(queryColumn="quxian_department", condition= QueryCondition.like)
    private String quxianDepartment;

    @TableField("student_name")
    @QueryField(queryColumn="student_name", condition= QueryCondition.like)
    private String studentName;

    @TableField("student_card")
    @QueryField(queryColumn="student_card", condition= QueryCondition.like)
    private String studentCard;

    @TableField("student_school")
    @QueryField(queryColumn="student_school", condition= QueryCondition.like)
    private String studentSchool;

    @TableField("student_nianji")
    @QueryField(queryColumn="student_nianji", condition= QueryCondition.eq)
    private String studentNianji;


    @TableField("student_banji")
    @QueryField(queryColumn="student_banji", condition= QueryCondition.eq)
    private String studentBanji;

    @TableField("cas_name")
    @QueryField(queryColumn="cas_name", condition= QueryCondition.like)
    private String casName;

    @TableField("pay_money")
    @QueryField(queryColumn="pay_money", condition= QueryCondition.like)
    private String payMoney;

    @TableField("type")
    @QueryField(queryColumn="type", condition= QueryCondition.like)
    private String type;

    @TableField("baofei_money")
    @QueryField(queryColumn="baofei_money", condition= QueryCondition.like)
    private String baofeiMoney;

    @TableField("zhifu_number")
    @QueryField(queryColumn="zhifu_number", condition= QueryCondition.like)
    private String zhifuNumber;

    @TableField("supplier")
    @QueryField(queryColumn="supplier", condition= QueryCondition.like)
    private String supplier;

    @TableField("toubaoren_name")
    @QueryField(queryColumn="toubaoren_name", condition= QueryCondition.like)
    private String toubaorenName;

    @TableField("toubaoren_phone")
    @QueryField(queryColumn="toubaoren_phone", condition= QueryCondition.like)
    private String toubaorenPhone;
}
