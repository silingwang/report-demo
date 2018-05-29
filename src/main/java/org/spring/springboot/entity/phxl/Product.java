package org.spring.springboot.entity.phxl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品
 * Created by nice on 2018/5/11.
 */
@Getter
@Setter
@ToString
public class Product implements Serializable {
    private String productCode;
    private String productName;
    private String model;
    private BigDecimal retailPrice;
    private String briefCode;
}
