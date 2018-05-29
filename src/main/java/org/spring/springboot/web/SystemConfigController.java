package org.spring.springboot.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.systemConfig.SystemConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by nice on 2018/5/15.
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/systemConfig")
public class SystemConfigController {

    /**
     * adpter
     */
    @RequestMapping(value = "/getAllSystemConfig")
    @ResponseBody
    public Map<String, Object> getAllSystemConfig(String request) {
        Map<String, Object> property = SystemConfig.getProperty(null);
        return property;
    }
}
