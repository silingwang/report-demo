package org.spring.springboot.entity.phxl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 患者
 * Created by nice on 2018/5/11.
 */
@Getter
@Setter
@ToString
public class Patient implements Serializable {
    private String registeredNumber;
    private String visitid;
    private String medicalNumber;
    private String patientName;
    private String patientSex;
    private Integer patientAge;
    private String IdNumber;
    private String chargeType;
    private String chargeTypeName;
    private String deptId;
    private String deptName;
    private String bedNo;
    private String operatorNo;
    private String operatingTime;
    private List<Operation> operationList;


}
