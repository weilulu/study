package com.wl.study.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author:weilu
 * @Date: 2019/3/5 21:43
 */
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<String, String>(){
            @Override
            public boolean removeEldestEntry(Map.Entry<String,String> eldest){
                return size() > 3;
            }
        };
        orderMap.put("1","1");
        orderMap.put("2","2");
        orderMap.put("3","3");

        System.out.println(orderMap);
        System.out.println(orderMap.get("1"));//就算有再次访问也会移除
        orderMap.put("4","4");
        System.out.println(orderMap);

        //最早加入的("1","1")被移除了
    }

    /**
     * 这里有个结论，map容量被初始化的时候都是2的指数,
     * 如果给定的值刚好是2的指数，则容量就是这个值；
     * 如果给定的值不是2的指数，则向上取2的指数
     * 0,1->1
     * 2->2
     * 4->4
     * 5->8
     * 7->8
     * 12->16
     */
    @Test
    public void testMapSize(){
        Map<String,Integer> map = new HashMap<>(2);
        int n = 1 - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }
}
