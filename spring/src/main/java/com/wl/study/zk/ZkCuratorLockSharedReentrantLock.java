package com.wl.study.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:weilu
 * @Date: 2019/4/29 23:18
 * @Description: 分布式可重入锁实现
 */
public class ZkCuratorLockSharedReentrantLock {

    private static final int SECOND = 1000;

    public static void main(String[] args) throws Exception{
        final ZkCuratorLockSharedReentrantLock instance = new ZkCuratorLockSharedReentrantLock();
        CuratorFramework client = instance.getStartClient();
        String path = "/curator_lock/shared_reentrant_lock";
        final InterProcessMutex lock = new InterProcessMutex(client,path);
        final CountDownLatch latch = new CountDownLatch(1);
        for(int i=0;i<30;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        //重入锁，获取两次
                        lock.acquire();
                        lock.acquire();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                        String orderNo = sdf.format(new Date());
                        System.out.println("生成的订单号是："+orderNo);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            //释放两次
                            lock.acquire();
                            lock.acquire();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        //保证所有线程内部逻辑执行时间一致
        latch.countDown();
        Thread.sleep(10 * SECOND);
        if(client != null){
            client.close();
        }
        System.out.println("Server closed...");
    }

    private CuratorFramework getStartClient(){
        RetryPolicy policy = new ExponentialBackoffRetry(1 * SECOND,3);
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5 * SECOND)
                .connectionTimeoutMs(3 * SECOND)
                .retryPolicy(policy).build();
        framework.start();
        System.out.println("Server connected...");
        return framework;
    }
}
