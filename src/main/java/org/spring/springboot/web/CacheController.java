package org.spring.springboot.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.reflect.BeanUtils;
import org.spring.springboot.scheduled.BeanUtilTest;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nice on 2017/10/27.
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/cache")
public class CacheController {

    private BeanUtilTest beanUtilTest;


    /**
     * aop 测试
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public String aop(String request) {
        return beanUtilTest.simulateCache();
    }
}
