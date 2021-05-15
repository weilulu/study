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
        lock.lock();
        try{
            String test = null;
            test.toLowerCase();
        }finally {
            System.out.println("test");
            lock.unlock();
        }
    }


}
