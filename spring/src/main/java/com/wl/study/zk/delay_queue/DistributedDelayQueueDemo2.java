package com.wl.study.zk.delay_queue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.queue.DistributedDelayQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/5/6 20:31
 * @Description: curator实现的延迟队列,
 * 不过不推荐使用，说明https://cwiki.apache.org/confluence/display/CURATOR/TN4
 *
 * 运行过程中发现的：
 * １，如果第一次没有消费，第二次运行的时候，还会将上次的数据进行消费，这也证明了数据是持久化的
 * ２，消费之前，进程不能退出
 * ３，按时间进行了排序，如果有新的元素加入，则需要找合适的位置插入
 */
public class DistributedDelayQueueDemo2 {
    //这里的newClient最终会实例化ZooKeeper类里的ClientCnxn类，
    // ClientCnxn是ZK客户端的核心类，负责客户端与服务端的通信，主要包括SendThread(IO读写、心跳检测、断连重试)与EventThread(事件处理：WatchEvent等)两大成员(内部类)，
    private static CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(3000, 2));
    private static String path = "/queue/0001";//队列对应的zk路径
    private static DistributedDelayQueue<String> delayQueue;
    private static LeaderSelectorClient selectorClient = new LeaderSelectorClient(client,path);

    /**
     * 使用静态块启动
     */
    static {
        client.start();
        selectorClient.start();
    }

    // TODO: 2020/7/7 在leaderSelector里 create consumer instance 
    public static void main(String[] args) throws Exception{
        QueueConsumer<String> consumer = new QueueConsumer<String>() {
            //在DistributeQueue类的688行进行了回调
            @Override
            public void consumeMessage(String s) throws Exception {
                //System.out.println("consume data:"+s+",currentTime:"+System.currentTimeMillis());
                LeaderSelectorClient.messages.add(s);
                selectorClient.takeLeadership(client);
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                System.out.println("state changed");
            }
        };
        delayQueue = QueueBuilder.builder(client, consumer, new QueueSerializer<String>() {
            @Override
            public byte[] serialize(String t) {
                try {
                    return t.getBytes("utf-8");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public String deserialize(byte[] bytes) {
                return new String(bytes);
            }
        },path).buildDelayQueue();/**实例化DistributedQueue对象,{@link DistributedDelayQueue#DistributedDelayQueue(CuratorFramework, QueueConsumer, QueueSerializer, String, ThreadFactory, Executor, int, String, int, boolean, int)}*/
        /**{@link DistributedDelayQueue#start()}*/
        delayQueue.start();//start方法里新开了子线程去执行完之后会进行回调consumeMessage方法
        System.out.println("delay queue built");
        //使用ls /queue/0001可以看到存储格式：[queue-|171EA3DEECC|0000000019, queue-|171EA3F4E5C|0000000020]
        //第一部分为固定常量，第二部分为延迟时间的十六进制表示法，第三部分为节点序号
        delayQueue.put("a",System.currentTimeMillis() + 4000);//延迟4s
        delayQueue.put("b",System.currentTimeMillis() + 7000);
        delayQueue.put("c",System.currentTimeMillis() + 10000);
        System.out.println("put ended");
        TimeUnit.MINUTES.sleep(15);
        delayQueue.close();
        TimeUnit.MINUTES.sleep(5);
        client.close();
    }
}
