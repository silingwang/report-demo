package org.spring.springboot;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RemoteIpFilter;
import org.spring.springboot.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 16/4/26.
 */
// Spring Boot 应用的标识
@SpringBootApplication
// mapper 接口类扫描包配置
@EnableCaching
@Configuration
@EnableScheduling  //开启定时任务支持
@Slf4j
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class, args);
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        log.info("==============================================");
        try {
            Thread.sleep(3000);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        String[] aaa = new String[20000000];
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        try {
            Thread.sleep(3000);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
//        String d="aaaa";
        long time = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
//            aaa[i] = d;
            aaa[i]=new String("aaaa");
        }
        long endTime = System.currentTimeMillis();
        log.info("耗时   :   "+(endTime-time));
        log.info("==============================================");
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        try {
            Thread.sleep(3000);
        } catch (Exception ee) {
            ee.printStackTrace();
        }


    }
//    @Bean
//    public RemoteIpFilter remoteIpFilter() {
//        return new RemoteIpFilter();
//    }

    /**
     * 配置,过滤器,过滤路径
     * @return
     */
//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new MyFilter());//添加过滤器
////        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.addInitParameter("token", "value");//添加默认参数
//        registration.setName("MyFilter");//设置优先级
//        registration.setOrder(1);//设置优先级
//        return registration;
//    }
//    /**
//     * 注入HazelcastInstance bean 到ioc 容器中
//     *
//     * @return
//     */
//    @Bean
//    public HazelcastInstance installHazelcastInstance() {
//        return Hazelcast.newHazelcastInstance();
//    }
//
//    /**
//     * RedisTemplate配置
//     *
//     * @param factory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        //使用此序列化,可以自增
//        GenericToStringSerializer<String> stringGenericToStringSerializer = new GenericToStringSerializer<String>(String.class);
//        template.setValueSerializer(stringGenericToStringSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }

}
