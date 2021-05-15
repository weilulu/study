package com.wl.study.concurrent.juc;

import com.wl.study.concurrent.semaphore.SemaphoreDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author:weilu
 * @Date:2020/8/2 11:36
 * @Description: ${Description}
 *
 * 这里如果stat不使用static修饰,那么每个线程都会成功更新stat的状态，因为stat是对象级的；
 * 但使用了static修饰,则只会有一个线程更新成功
 */
public class AtomicReferenceTest extends Thread{
    private static final AtomicReference<Stat> stat = new AtomicReference<Stat>(Stat.LATENT);
    //private static final AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void run() {
        //boolean flag = state.compareAndSet(Stat.LATENT,Stat.STARTED);
        //System.out.println(Thread.currentThread().getName()+",flag:"+flag+",state:"+state.get().name());

        if(!stat.compareAndSet(Stat.LATENT,Stat.STARTED)){
            System.out.println("状态变更失败,threadName: "+Thread.currentThread().getName());
        }else {
            System.out.println("状态变更成功,threadName: "+Thread.currentThread().getName()+",stat1:"+stat);
        }

    }

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            new AtomicReferenceTest().start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" start");
    }
}
