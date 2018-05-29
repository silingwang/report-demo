package org.spring.springboot.entity.phxl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 科室
 * Created by 尹熙成 on 2018/5/15.
 */
@Getter
@Setter
@ToString
public class Dept implements Serializable {
    private String deptCode;//科室编码
    private String deptName;//科室名称
    private String briefCode;//简码
    private String deptTypeCode;//科室类型Code
    private String deptTypeName;//科室类型
    private String parentDeptGuid;//父级科室名称

}
