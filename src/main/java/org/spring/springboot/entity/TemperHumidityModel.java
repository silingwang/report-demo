package org.spring.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * Created by nice on 2018/4/17.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class TemperHumidityModel {
    //时间
    private Date recordDate;
    //记录日期
    private String recordDay;
    //天气
    private int  weather;
    //自然温度
    private Integer natureTem;
    //自然湿度
    private Integer natureHum;
    //调整温度
    private Integer adjustTem;
    //调整湿度
    private Integer adjustHum;
    //备注
    private String remark;
    //记录人
    private String realname;

}
