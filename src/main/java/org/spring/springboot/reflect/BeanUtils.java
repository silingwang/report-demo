package org.spring.springboot.reflect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by nice on 2018/3/20.
 */
@Slf4j
public class BeanUtils {
    /**
     * 简单的benToMap
     *
     * @param t
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> beanToMap(Object t) {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> aClass = t.getClass();
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            //获取字段名称
            String name = field.getName();
            //获取此字段的值
            try {
                Object object = field.get(t);
                map.put(name, object);
            } catch (IllegalAccessException e) {
                log.info("获取此字段的值出现异常  " + e.getMessage());
            }
        }
        return map;
    }

    /**
     * 简单的mapToBean
     *
     * @param
     * @return
     * @throws IllegalAccessException
     */
    public static <T> T mapToBean(Map map, Class<T> clzss) {
        T t = null;
            try {
                t = clzss.newInstance();
                Field[] fields = clzss.getFields();
                for (Field field : fields) {
                    //获取字段名称
                    String name = field.getName();
                    //获取此字段的值
                    Set set = map.keySet();
                    if (set.contains(name)) {
                        Object o = map.get(name);
                        field.set(t, o);
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                log.info(" 实例化异常  " + e.getMessage());
            }
        return (T) t;
    }
}
