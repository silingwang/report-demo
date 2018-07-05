package org.spring.springboot.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.enums.ReturnCodeEnum;
import org.spring.springboot.reflect.BeanUtils;
import org.spring.springboot.util.BackResponseUtil;
import org.spring.springboot.util.BaseResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by nice on 2018/6/26.
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    /**
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/")
    @ResponseBody
    public BaseResponse aop(String request) {
        BaseResponse response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        return response;
    }

}
