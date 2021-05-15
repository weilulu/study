package com.wl.study.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/4/30 11:19
 * @Description: 为了学习CountDownLatch,一个任务在其它任务执行完之后再执行
 * 计数器不能被重置，如果需要重置考虑使用CyclicBarrier
 *
 * 输出：
 *  pool-1-thread-5 start check...
    pool-1-thread-1 start check...
    pool-1-thread-2 start check...
    pool-1-thread-3 start check...
    pool-1-thread-4 start check...
    ALL CHECKED! main线程的输出
 */
public class CountDownLatchDemo implements Runnable {

    private static final CountDownLatch latch = new CountDownLatch(5);

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.println(Thread.currentThread().getName()+" start check...");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            latch.countDown();//计数减1
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            service.submit(new CountDownLatchDemo());
        }
        try {
            //调用await方法的线程将被阻塞，直到这个计数器的计数值被其它线程减为0为止
            //也可以指定等待时间跳过
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ALL CHECKED!");
        service.shutdown();
    }
}
