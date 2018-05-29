package org.spring.springboot.scheduled;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.User;
import org.spring.springboot.reflect.BeanUtils;
import org.spring.springboot.util.JsonUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nice on 2018/3/20.
 */
@Slf4j
@Service
public class BeanUtilTest {
    private Map<String, Object> map = new HashMap<>();
    {
        map.put("time", System.currentTimeMillis());
        map.put("token", "token");
    }

    /**
     * 用程序模拟缓存 demo
     */
    public String simulateCache() {
        long time = map.get("time")==null? 0: (long) map.get("time");
        long current = System.currentTimeMillis() - 7*1000; //7200 * 1000;  //当前时间 减去有效时间
        if (current >=time) {
            map = new HashMap<>();
            map.put("time",System.currentTimeMillis());
            String uuid = UUID.randomUUID().toString().replace("-","");
            map.put("token",uuid);
            String test="PRODUCENAME";
            return uuid;
        }else {
            return (String) map.get("token");
        }
    }

    //测试反射的两个方法
    @Scheduled(cron = "0 58 11 * * *")
    public void beanToMapTest() {
        log.info("定时任务开始");
        User user = new User();
        user.setId(1L);
        user.setAge(25);
        user.setUserName("nice");
        Map<String, Object> map = BeanUtils.beanToMap(user);
        log.info(" getMap  " + JsonUtil.objectToJson(map));
        User o = BeanUtils.mapToBean(map, User.class);
        log.info("mapToBean   " + JsonUtil.objectToJson(o));
    }

    //将两个相同对象中属性合到一个对象中
    @Scheduled(cron = "0 15 9 * * *")
    public void mergeValues()  {
        User chief=new User(12L,"Tom",null);
        User copied=new User(null,"sun",25);
        Class<?> chiefClass = chief.getClass();
        Class<?> copiedClass = copied.getClass();
        Field[] copiedClassFields = copiedClass.getFields();
        Field[] fields = chiefClass.getFields();
        for (Field field : fields) {
            try {
                Object   o = field.get(chief);//获取此字段的值
                String name = field.getName();//获取字段名称
                //如果此字段值为 null ,就将 copied对象中 对应字段的值赋值到此字段中
                if (null==o){
                    for (Field copefield:copiedClassFields) {
                        //获取改字段的值
                        Object value = copefield.get(copied);
                        String copefieldName = copefield.getName();
                        if (name.equals(copefieldName)){
                            field.set(chief,value);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        log.info("mergeValues  later  chief  "+JsonUtil.objectToJson(chief));

    }

}
