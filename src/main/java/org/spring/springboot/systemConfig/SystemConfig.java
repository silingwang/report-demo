package org.spring.springboot.systemConfig;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.util.JsonUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * Created by nice on 2018/5/15.
 */
@Component
@Slf4j
public class SystemConfig {
    private static final Properties properties	= new Properties();

    /**
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
        Resource[] resources = resolver.getResources("classpath:**/spring/*.properties");
        for (Resource resource : resources) {
            if (log.isDebugEnabled()) {
                log.debug("加载Properties配置文件{}", resource.getFilename());
            }
            properties.load(resource.getInputStream());
        }
        if(log.isDebugEnabled()){
            TreeMap treeMap = new TreeMap(properties);
            Map<String, Object> configuration= Maps.newTreeMap();
            Iterator iterator = treeMap.keySet().iterator();
            while (iterator.hasNext()){
                String next = iterator.next().toString(); //得到key
                configuration.put(next,treeMap.get(next));
            }
            log.debug("****************系统配置参数表****************\n{}", JsonUtil.objectToJson(configuration));
        }
    }

    /**
     * 根据key找出value值
     * @param key 键
     * @return String
     * @author shijingbang
     */
    public static Map<String,Object> getProperty(String key) {
        TreeMap treeMap = new TreeMap(properties);
        Map<String, Object> configuration= Maps.newTreeMap();
        Iterator iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next().toString(); //得到key
            configuration.put(next,treeMap.get(next));
        }
        return configuration;
    }

}
