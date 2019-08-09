package com.wl.study.concurrent.guava_rateLimiter;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author:weilu
 * @Date: 2019/3/15 11:15
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private static ExecutorService pool = new ThreadPoolExecutor(THREAD_COUNT,
            100,
            0L,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<Runnable>(1024),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    private static Semaphore semaphore = new Semaphore(3);

    /**
     * 总共会有30个线程，但一次只允许3个线程访问
     * @param args
     */
    public static void main(String[] args) {
        for (int i=0;i<THREAD_COUNT;i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(new Date());
                        Thread.sleep(1000);
                        semaphore.release();
                    }catch (InterruptedException e){

                    }
                }
            });
        }
        pool.shutdown();
    }
}
