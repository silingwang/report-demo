package org.spring.springboot.entity.phxl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 产品扩展属性
 * Created by nice on 2018/5/11.
 */
@Getter
@Setter
@ToString
public class ProductParam extends Product {
    private Integer number;
}
