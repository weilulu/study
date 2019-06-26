package com.wl.study.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import org.apache.curator.framework.recipes.leader.LeaderLatch;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:weilu
 * @Date: 2019/4/29 22:03
 * @Description: Master选举思路：
 * 选择一个根节点，例如/master_select,多台机器同时向该节点创建一个子节点/master_select/lock,
 * 利用zookeeper的特性，最终只会一台机器能够创建成功，成功的那台机器就作为Master
 * Curator也是基于这个思路，但是它将节点创建，事件监听和自动选举过程进行了封装，开发人员只需要用
 * 简单的API就可以实现Master选举
 */
public class ZkLeaderLatch {
    private static final int SECOND = 1000;

    public static void main(String[] args) throws Exception{
        /*ExecutorService service = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            final int index = 1;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        new ZkLeaderLatch().schedule(index);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        //Thread.sleep(10 * SECOND);
        service.shutdown();*/
        new ZkLeaderLatch().schedule(1);
    }

    private void schedule(final int thread)throws Exception{
        CuratorFramework client = this.getStartedClient(thread);
        String path = "/leader_latch";
        if(client.checkExists().forPath(path) == null){
            client.create().creatingParentsIfNeeded().forPath(path);
        }
        LeaderLatch latch = new LeaderLatch(client,path);
        latch.addListener(new LeaderLatchListener(){
            @Override
           public void notLeader(){
               System.out.println(MessageFormat.format("Thread [" + thread
                       + "] I am not the leader... timestamp [{0}]", System.currentTimeMillis()));
           }
            @Override
           public void isLeader(){
               System.out.println(MessageFormat.format("Thread [" + thread + "] I am the leader... timestamp [{0}]",
                       System.currentTimeMillis()));
           }
        });
        latch.start();
        //Thread.sleep(2 * (thread + 1) * SECOND);
        if(latch != null){
            latch.close();
        }
        if(client != null){
            client.close();
        }
        System.out.println("Thread [" + thread + "] Server closed...");
    }

    private CuratorFramework getStartedClient(final int thread){
        CuratorFramework curatorFramework = null;
        try {
            RetryPolicy rp = new ExponentialBackoffRetry(1 * SECOND,3);
            curatorFramework = CuratorFrameworkFactory.builder()
                    .connectString("localhost:2181")
                    .sessionTimeoutMs(5 * SECOND)
                    .connectionTimeoutMs(3 * SECOND)
                    .retryPolicy(rp).build();
            curatorFramework.start();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Thread [" + thread + "] Server connected...");;
        return curatorFramework;
    }
}
