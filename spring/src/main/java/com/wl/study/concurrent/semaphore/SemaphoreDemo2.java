package com.wl.study.concurrent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/7/4 14:23
 * @Description: 实现线程的前置依赖关系，简单说就是，让多个线程按顺序执行
 */
public class SemaphoreDemo2 {

    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(0);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run first");
            semaphore1.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore1.acquire();
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run second");
            semaphore2.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore2.acquire();
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " run third");
        }).start();
    }

    //out put:
    /**
     * Thread-0 run first
     * Thread-1 run second
     * Thread-2 run third
     */
}
