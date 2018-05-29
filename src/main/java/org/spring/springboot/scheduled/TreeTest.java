package org.spring.springboot.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.MenuExt;
import org.spring.springboot.reflect.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nice on 2018/4/16.
 */
@Slf4j
@Service
public class TreeTest {
    private BeanUtils beanUtils;

    public List<MenuExt> TreeTest(){
        List<MenuExt> list=new ArrayList<>();
        MenuExt menuExtA=new MenuExt();
        menuExtA.setId(1L);

        List<MenuExt> treeMenus = beanUtils.createTreeMenus(null);



        return treeMenus;
    }



}
