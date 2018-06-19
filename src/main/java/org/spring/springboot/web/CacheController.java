package org.spring.springboot.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.reflect.BeanUtils;
import org.spring.springboot.scheduled.BeanUtilTest;
import org.spring.springboot.util.BaseResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public String aop(String request) {
        return beanUtilTest.simulateCache();
    }

    /**
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/put")
    @ResponseBody
    public BaseResponse put(@RequestParam("put") String put) {
        return beanUtilTest.putString(put);
    }
    /**
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/getMap")
    @ResponseBody
    public Map getMap(String put) {
        return beanUtilTest.getMap();
    }
}
