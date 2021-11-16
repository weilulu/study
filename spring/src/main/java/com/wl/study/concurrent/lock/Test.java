package com.wl.study.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:weilu
 * @Date:2020/2/16 11:48
 * @Description:${Description}
 */
public class Test {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();//如果超过65535未获取成功会抛出异常
        try{
            String test = null;
            //test.toLowerCase();
        }finally {
            System.out.println("test");
            lock.unlock();//如果未加锁成功就释放锁会抛出异常
        }
    }


}
