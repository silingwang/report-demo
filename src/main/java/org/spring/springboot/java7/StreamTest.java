package org.spring.springboot.java7;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.User;
import org.spring.springboot.util.DateUtil;
import org.spring.springboot.util.JsonUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8  流的特性
 * Created by nice on 2018/3/22.
 */
@Slf4j
@Service
@EnableScheduling  //开启定时任务支持
public class StreamTest {
    /**
     *filter 根据字段过滤元素
     */
    @Scheduled(cron = "40 33 15 * * *")
    public void testList() {
        User user = new User(14879L, "userName", 20);
        User user1 = user.clone();
        User clone = user.clone();
        clone.setAge(19);
        User cloneDdeep = user.cloneDdeep();
        cloneDdeep.setAge(30);
        //用filter 过滤List这元素
        Stream<User> userStream = Arrays.asList(user, clone, cloneDdeep,user1).stream().filter(list -> list.getAge() < 25);
        log.info("过滤后的Stream 转化成list 后 "+ JsonUtil.objectToJson(userStream.collect(Collectors.toList())));
    }

    /**
     * distinct: 返回一个有唯一元素的stream(根据stream中元素的equals实现)。
     */
    @Scheduled(cron = "40 42 15 * * *")
    public void testList2() {
        User user = new User(14879L, "userName", 20);
        User user1 = user.clone();
        User clone = user.clone();
        clone.setAge(19);
        User cloneDdeep = user.cloneDdeep();
        cloneDdeep.setAge(30);
        //用filter 过滤List这元素
        Stream<User> userStream = Arrays.asList(user, clone, cloneDdeep,user1).stream().filter(list -> list.getAge() < 25);
        Stream<User> distinct = userStream.distinct();
        log.info("distinct  后的 stream "+distinct.collect(Collectors.toList()));
    }
    /**
     * limit(n): 返回一个不长于给定大小n的stream。
     */
    @Scheduled(cron = "0 52 15 * * *")
    public void testLimit() {
        User user = new User(14879L, "userName", 20);
        User user1 = user.clone();
        User clone = user.clone();
        clone.setAge(19);
        User cloneDdeep = user.cloneDdeep();
        cloneDdeep.setAge(30);
        //用filter 过滤List这元素
        Stream<User> userStream = Arrays.asList(user, clone, cloneDdeep, user1).stream();
        Stream<User> limit = userStream.limit(3);
        log.info("stream  limit "+JsonUtil.objectToJson(limit.collect(Collectors.toList())));
    }
    /**
     * skip(n): 返回一个丢弃了前面n个元素的stream。
     */
    @Scheduled(cron = "30 51 15 * * *")
    public void testSkip() {
        User user = new User(14879L, "userName", 20);
        User user1 = user.clone();
        User clone = user.clone();
        clone.setAge(19);
        User cloneDdeep = user.cloneDdeep();
        cloneDdeep.setAge(30);
        //用filter 过滤List这元素
        Stream<User> userStream = Arrays.asList(user, clone, cloneDdeep, user1).stream();
        Stream<User> limit = userStream.skip(2);
        log.info("stream  limit "+JsonUtil.objectToJson(limit.collect(Collectors.toList())));
    }
    /**
     * 自定义排序
     * Sorted(n): 定义比较器
     */
    @Scheduled(cron = "59 58 15 * * *")
    public void testSorted() {
        User user = new User(14879L, "userName", 20);
        User user1 = user.clone();
        User clone = user.clone();
        clone.setAge(19);
        User cloneDdeep = user.cloneDdeep();
        cloneDdeep.setAge(30);
        //用filter 过滤List这元素
        Stream<User> userStream = Arrays.asList(user, clone, cloneDdeep, user1).stream();
        Stream<User> sorted = userStream.sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        log.info("Stream  Sorted  "+JsonUtil.objectToJson(sorted.collect(Collectors.toList())));
    }
    /**
     * map生成的是个一对一映射,for的作用
     */
    @Scheduled(cron = "0 20 16 * * *")
    public void testMap() {
        User user = new User(14879L, "userName", 20);
        User user1 = user.clone();
        User clone = user.clone();
        clone.setAge(19);
        User cloneDdeep = user.cloneDdeep();
        cloneDdeep.setAge(30);
        //用filter 过滤List这元素
        Stream<User> userStream = Arrays.asList(user, clone, cloneDdeep, user1).stream();

        List<String> collect = userStream.map(user2 -> user2.getUserName()).collect(Collectors.toList());
        log.info("Stream  map  "+JsonUtil.objectToJson(collect));
    }

    /**
     * reduce 
     */
    @Scheduled(cron = "0 28 16 * * *")
    public  void reduceTest(){
        //累加，初始化值是 10
        Integer reduce = Stream.of(1, 2, 3, 4)
                .reduce(10, (count, item) ->{
                    log.info("count:"+count);
                    log.info("item:"+item);
                    return count + item;
                } );
        log.info(reduce.toString());

        Integer reduce1 = Stream.of(1, 2, 3, 4)
                .reduce(0, (x, y) -> x + y);
        log.info(reduce1.toString());
        //x 就是 "0"
        String reduce2 = Stream.of("1", "2", "3")
                .reduce("0", (x, y) -> (x + "," + y));
        log.info(reduce2);
    }
    /**
     * 两个时间比较
     */
    @Scheduled(cron = "30 37 14 * * *")
    public  void dateTest(){
        try {
            Date parse = new Date();
            Thread.sleep(6000L);
            Date date = new Date();
            boolean before = parse.before(date);
            log.info("parse date is  "+DateUtil.formatDate(parse,DateUtil.YEAR_MONTH_DAY_HH_MM_SS));
            log.info("date  date  is  "+DateUtil.formatDate(date,DateUtil.YEAR_MONTH_DAY_HH_MM_SS)+"  boolean  : "+before);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
