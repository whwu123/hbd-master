package com.active4j.hr.yucai.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_parents_orders")
@Getter
@Setter
public class YcOrdersEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -3540862564325138825L;


    @TableField("DIQU_NAME")
    private String diqiName;

    @TableField("DIQU_ID")
    private String diquId;

    @TableField("QUXIAN_NAME")
    private String quxianName;

    @TableField("QUXIAN_ID")
    private String quxianId;

    @TableField("XIANGZHEN_NAME")
    private String xiangzhenName;

    @TableField("XIANGZHEN_ID")
    private String xiangzhenId;

    @TableField("XUEXIAO_NAME")
    private String xuexiaoName;

    @TableField("XUEXIAO_ID")
    private String xuexiaoId;

    @TableField("NIANJI_NAME")
    private String nianjiName;

    @TableField("NIANJI_ID")
    private String nianjiId;

    @TableField("BANJI")
    private String banJi;

    @TableField("BAOXIAN_TYPE")
    private String baoxianType;

    @TableField("BAOXIAN_ORDERS")
    private String baoxianOrders;

    @TableField("BAOXIAN_MONEY")
    private String baoxianMoney;

    @TableField("STUDENT_NAME")
    private String studentName;

    @TableField("STUDENT_CARD")
    private String studentCard;

    @TableField("STATUS")
    private int status;

    @TableField("PAY_STATUS")
    private int payStatus;

    @TableField("PARENTS_GUANXI")
    private int parentsGuanxi;

    @TableField("PARENTS_NAME")
    private String parentsName;

    @TableField("PARENTS_PHONE")
    private String parentsPhone;
}
