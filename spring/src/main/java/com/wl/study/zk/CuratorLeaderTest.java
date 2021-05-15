package com.wl.study.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * @Author:weilu
 * @Date:2020/3/18 16:10
 * @Description: 使用Curator实现Leader选举
 */
public class CuratorLeaderTest {

    public static final String PATH= "/example/basic";
    public static void main(String[] args)throws Exception {
        TestingServer server = new TestingServer();
        CuratorFramework client = createSimple("localhost:2182");
        client.start();
        client.create().creatingParentContainersIfNeeded().forPath(PATH ,"test".getBytes());
        System.out.println(new String(client.getData().forPath(PATH)));
        LeaderLatch leaderLatch = new LeaderLatch(client,PATH);
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println("IS LEADER");
            }

            @Override
            public void notLeader() {
                System.out.println("IS NOT LEADER");
            }
        });
        leaderLatch.start();
    }


    public static CuratorFramework createSimple(String connetString){
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(2000,3);
        return CuratorFrameworkFactory.newClient(connetString,retryPolicy);
    }
}
