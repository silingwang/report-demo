package org.spring.springboot.entity.phxl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 操作人
 * Created by nice on 2018/5/15.
 */
@Getter
@Setter
@ToString
public class Operator implements Serializable {
    private String operatorNo;//操作人工号
    private String operatorIdentity;//操作人身份
    private String operatorName;//操作人姓名
    private String deptName;//科室名称
    private String deptGuid;//科室类型
}
