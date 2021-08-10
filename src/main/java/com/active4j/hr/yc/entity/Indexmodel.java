package com.active4j.hr.yc.entity;

import com.active4j.hr.system.model.MenuModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Indexmodel implements Serializable {

    private static final long serialVersionUID = 7516450772634980274L;

    private String quxianName;

    private String schoolName;

    private String nianjiName;

    private String xuepingxian;

    private String yiwaixian;

    private String jianhurenxian;

    private String total;

}
