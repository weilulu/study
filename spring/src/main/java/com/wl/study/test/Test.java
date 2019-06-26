package com.wl.study.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author:weilu
 * @Date: 2019/4/5 21:33
 */
public class Test {

    Map<String,String> map = new HashMap<>();//这个map是单独属于每个Test实例
    //static Map<String,String> map = new HashMap<>();//这个map是属于Test这个类

    final String value = "1";

    public static void main(String[] args) {
        Test test1 = new Test();
        test1.map.put("1","1");
        System.out.println(test1.map.toString());//有值

        Test test2 = new Test();
        System.out.println(test2.map.toString());//空map

        //test1.value = "2"; //编译会报错

        System.out.println(System.identityHashCode(test1));
        System.out.println(Objects.hashCode(test1));

    }
}
