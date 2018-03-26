package org.spring.springboot.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.User;
import org.spring.springboot.reflect.BeanUtils;
import org.spring.springboot.util.JsonUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * Created by nice on 2018/3/20.
 */
@Slf4j
@Service
public class BeanUtilTest {
    //测试反射的两个方法
    @Scheduled(cron = "0 58 11 * * *")
    public void  beanToMapTest(){
        log.info("定时任务开始");
        User user = new User();
        user.setId(1L);
        user.setAge(25);
        user.setUserName("nice");
        Map<String, Object> map = BeanUtils.beanToMap(user);
        log.info(" getMap  "+ JsonUtil.objectToJson(map));
        User o = BeanUtils.mapToBean(map, User.class);
        log.info("mapToBean   "+JsonUtil.objectToJson(o));
    }

}
