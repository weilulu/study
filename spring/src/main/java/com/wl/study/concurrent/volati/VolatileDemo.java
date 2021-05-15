package com.wl.study.concurrent.volati;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:weilu
 * @Date:2020/5/30 13:07
 * @Description: volatile的使用，想验证在多线程场景下，只使用volatile而不使用同步会是怎样的场景
 *
 * 多运算几次会出现count=1999;atomicCount=2000的情形，
 * 这说明就算变量加了volatile，在多线程场景下还是会有问题，
 * 因为volatile只是解决了可见性与有序性，并没有解决原子性。而只有通过锁(synchronize/lock/AtomicXXX)才能解决子性
 *
 * 附：对象的实例化：
 * 1，分配内在空间；2，初始化对象；3，将内存空间的地址赋值给对应引用
 * 操作系统的原因，第2步与第3步可能会有顺序变换
 */
public class VolatileDemo extends Thread{
    private static CountDownLatch latch = new CountDownLatch(2000);
    private static volatile int count = 0;
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<2000;i++){
            VolatileDemo demo = new VolatileDemo();
            demo.start();
        }
        latch.await();
        System.out.println("count:"+count);
        System.out.println("atomicCount:"+atomicCount);
    }
    @Override
    public void run(){
        count ++;
        atomicCount.getAndIncrement();
        latch.countDown();
    }
}
