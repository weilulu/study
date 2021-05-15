package com.wl.study.concurrent.semaphore;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/5/7 21:40
 * @Description: 学习Semaphore使用
 */
public class SemaphoreDemo extends Thread{

    private static  final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            new SemaphoreDemo().start();
        }

        System.out.println(Thread.currentThread().getName()+" start");
    }


    /**
     * 使用了semaphore之后的输出：
     * Thread-1 start1
     Thread-1 start2
     Thread-0 start1
     Thread-0 start2
     ...
     没有使用的输出为：
     Thread-0 start1
     Thread-1 start1
     Thread-2 start1
     Thread-3 start1
     ...
     说明使用了semaphore之后，同时只允许Semaphore构造器里的线程数进入;
     如果不使用，则会有多个线程进入;
     如果Semaphore构造为0则子线程会一直阻塞在semaphore.acquire()方法上，但主线程会运行
     如果构造为0，只会输出main start

     acquire(n):每调用一次，就消耗n个许可
     release(n):每调用一次，就动态添加n个许可,这两个方法并不一定要成对出现，如果只有release(n)方法，则是添加n个许可
     */

    @Override
    public void run() {
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+" start1");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" start2");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();//放在finally里执行
        }
    }

    @Test
    public void testSemaphoreRelease(){
        Semaphore semaphore = new Semaphore(0);
        System.out.println(semaphore.availablePermits());
        semaphore.release(2);
        System.out.println(semaphore.availablePermits());
    }
}