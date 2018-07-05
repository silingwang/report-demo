package org.spring.springboot.reflect;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.MenuExt;
import org.spring.springboot.entity.User;
import org.spring.springboot.util.JsonUtil;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by nice on 2018/3/20.
 */
@Slf4j
public class BeanUtils {

    public static List<MenuExt> createTreeMenus(List<MenuExt> menus) {
        List<MenuExt> treeMenus = null;
        if (null != menus && !menus.isEmpty()) {
            // 创建根节点
            MenuExt root = new MenuExt();
            root.setName("菜单根目录");

            // 组装Map数据
            Map<Long, MenuExt> dataMap = new HashMap<Long, MenuExt>();
            for (MenuExt menu : menus) {
                dataMap.put(menu.getId(), menu);
            }
            // 组装树形结构
            Set<Map.Entry<Long, MenuExt>> entrySet = dataMap.entrySet();
            for (Map.Entry<Long, MenuExt> entry : entrySet) {
                MenuExt menu = entry.getValue();
                if (null == menu.getParentId() || 0 == menu.getParentId()) {
                    root.getChildren().add(menu);
                } else {
                    dataMap.get(menu.getParentId()).getChildren().add(menu);
                }
            }
            // 对树形结构进行二叉树排序
            root.sortChildren();
            treeMenus = root.getChildren();
        }
        return treeMenus;
    }


    /**
     * 简单的beanToMap
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
    public static Map<String,Object> getMethod(){
        Map<String,Object> map=new HashMap<>();
        User user = new User();
        Class<? extends User> aClass = user.getClass();
        Method[] methods = aClass.getMethods();
        List<String> list=new ArrayList<>();
        list.add("id");
        list.add("userName");
        list.add("age");
        for (Method method:methods) {
            for (String li: list) {
                if (method.getName().equalsIgnoreCase("set"+li)){
                    map.put(li,method);
                }
            }
        }
        return map;
    }
}
