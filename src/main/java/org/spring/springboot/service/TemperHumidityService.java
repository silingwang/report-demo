package org.spring.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.TemperHumidityModel;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nice on 2018/4/17.
 */
@Service
@Slf4j
public class TemperHumidityService {
    public List<TemperHumidityModel> getList(Map<String, Object> params) {
        List<TemperHumidityModel> list=new ArrayList<>();
        list.add(new TemperHumidityModel(new Date(1523927714000l),"1",9,5,52,21,52,"","poi"));
        list.add(new TemperHumidityModel(new Date(1523927714000l),"1",1,3,48,20,48,"","poi"));
        list.add(new TemperHumidityModel(new Date(1523927714000l),"1",1,3,46,20,49,"","poi"));
        list.add(new TemperHumidityModel(new Date(1523927714000l),"1",1,6,43,21,48,"","poi"));
        list.add(new TemperHumidityModel(new Date(1523927714000l),"1",1,6,41,20,49,"","poi"));
        list.add(new TemperHumidityModel(new Date(1523927714000l),"1",2,5,45,19,50,"","poi"));
        return list;
    }
}
