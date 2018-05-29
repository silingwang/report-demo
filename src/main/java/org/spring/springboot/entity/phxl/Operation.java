package org.spring.springboot.entity.phxl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 手术
 * Created by nice on 2018/5/11.
 */
@Getter
@Setter
@ToString
public class Operation implements Serializable {
    private String patientName;
    private String patientSex;
    private Integer patientAge;
    private String IdNumber;
    private String operationName;
    private String doctor;
    private String operationDate;
    private String narcosisType;
    private String room;
    private String nurse;
    private String bedNo;
    private List<ProductParam> productList;
}
