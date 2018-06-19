package org.spring.springboot.java7;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.entity.User;
import org.spring.springboot.util.DateUtil;
import org.spring.springboot.util.JsonUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8  的新特性
 * Created by nice on 2018/3/20.
 */
@Slf4j
@Service
public class java7 {
    /**
     * switch  支持 字符串类型
     *
     * @Scheduled(cron = "0 58 11 * * *")
     */
    @Scheduled(cron = "0 38 13 * * *")
    public void testSwitch() {
        String s = "test";
        switch (s) {
            case "test":
                System.out.println("test");
                break;
            case "test1":
                System.out.println("test1");
                break;
            default:
                System.out.println("break");
                break;
        }
    }


    /**
     *实现Cloneable接口并重写Object类中的clone()方法
     */
    @Scheduled(cron = "0 56 16 * * *")
    public void testList() {
        User user = new User(14879L, "userName", 20);
        User clone = user.clone();
        clone.setUserName("clone");
        log.info("对象1 " + user);
        log.info("对象2 " + clone);
        User user3 = user;
        user3.setUserName("user3");
        log.info("对象1 " + user);
        log.info("对象3 " + user3);

    }
    //当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递
    //答值传递 ,java中没有引用传递 ,传递的是原对象引用的副本 ,指向同一对象

    //对象什么时候会被回收 ,对象没有被引用的时候,清除阶段就会清除此对象

    /**
     *比较器  TreeMap 默认是按升序排序，也可以指定排序的比较器
     */
    @Scheduled(cron = "0 18 15 * * *")
    public void testTreeSet() {
        User user = new User(14879L, "A", 20);
        User user1=new User(148791L,"B",19);
        User user2=new User(148791L,"A",19);
        User user3=new User(148791L,"C",18);
        TreeSet<User> treeSet=new TreeSet<User>(new Comparator<User>() {
            @Override
            public int compare(User user, User user2) {
                int compare =user.getAge().compareTo(user2.getAge());
                if (0==compare){
                    compare=user.getUserName().compareTo(user2.getUserName());
                }
                return compare;
            }
        });
        treeSet.add(user);
        treeSet.add(user1);
        treeSet.add(user2);
        treeSet.add(user3);
        log.info("treeSet " + JsonUtil.objectToJson(treeSet));

    }

    //java 中三个代 年轻代（Young Generation）、年老代（Old Generation）和持久代（Permanent Generation）
    /**
     * 年轻代  分为三个区 Eden区，两个 Survivor区  所有新生成的对象首先都是放在年轻代的 年老区 (Tenured)
     */
    /**
     * 经历多少次垃圾回收是可以设置的
     * 年老代 在年轻代中经历了N次垃圾回收后仍然存活的对象，就会被放到年老代中。因此，可以认
     为年老代中存放的都是一些生命周期较长的对象
     */
    /**
     * 持久代: 用于存放静态文件，如今Java类、方法等
     *
     * 持久代补充：持久带也称为方法区
     * 方法区：方法区存储每一个java类的结构信息：比如运行时常量池，字段和方法数据，
     * 构造函数和普通方法的字节码内容以及类、实例、接口初始化时需要使用到的特殊方法等数据
     * JDK8中已经把持久代（PermGen Space） 干掉了，取而代之的元空间（Metaspace）。Metaspace占用的是本地内存，不再占用虚拟机内存
     */
    /**
     *  方法一 对字符进行编码 对字符串进行编码解码 保存特殊NickName  可以解决绝大部分 nickName 名称保存问题
     *  方法二修改数据库字符集为 utf-8Mb4
     */
    @Scheduled(cron = "40 58 21 * * *")
    public void testSaveNickName() {
        //测试过的字符  ๑-_-|| , 壞壞♂™ , 小林子⁶⁶⁶₆₆₆⁶⁶⁶ ,＼(☆o☆)／ ,〆。﹏命中注定的分开
       String nickName="〆。﹏命中注定的分开";
        log.info("nickName  is  "+nickName);
        try {
            //编码
            String encode = URLEncoder.encode(nickName, "utf-8");
            log.info("编码后的  nickName  "+encode);
            //解码
            String decode = URLDecoder.decode(encode, "utf-8");
            log.info("解码后的  nickName  "+decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * java8 之 Stream
     * 流一旦用了就没有了
     */
    @Scheduled(cron = "50 15 15 * * *")
    public void testJava8Stream() {
        List<Integer> nums = Lists.newArrayList(1,null,3,4,null,6);
        Stream<Integer> streams = nums.stream();
        log.info("原始stream   " +streams.toString());
        //得到
        Stream<Integer> integerStream = streams.filter(stream -> stream != null);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        log.info("过滤后的stream 转化得到的list  "+JsonUtil.objectToJson(collect));
        nums.stream().filter(num -> num != null).count();

    }
    /**
     * java8 之 Stream
     * 流一旦用了就没有了
     */
    @Scheduled(cron = "50 15 15 * * *")
    public void testDate() {
        String time="201801";
        Date date = DateUtil.formatDateTimeStr(time, "yyyyMM");
        log.info("testDate  date  ");
    }
}
