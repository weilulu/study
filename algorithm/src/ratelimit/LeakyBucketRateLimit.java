package ratelimit;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 漏桶算法
 * 漏桶算法限制了流出速率是一个固定常量值，所以漏桶算法不支持出现突发流出流量
 */
public class LeakyBucketRateLimit implements Runnable{
    /**限制qps*/
    private Integer limitSecond;
    /**漏桶列队*/
    private BlockingDeque<Thread> leakyBucket;
    private ScheduledExecutorService scheduledExecutorService;

    public LeakyBucketRateLimit(Integer buckSize,Integer limitSecond){
        this.limitSecond = limitSecond;
        this.leakyBucket = new LinkedBlockingDeque<>(buckSize);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        long interval = (1000 * 1000 * 1000) / limitSecond;
        scheduledExecutorService.scheduleAtFixedRate(this,0,interval, TimeUnit.SECONDS);
    }

    public boolean canPass()throws Exception{
        if(leakyBucket.remainingCapacity() == 0){
            throw new Exception("超过最大流量上限");
        }
        //客户端请求时可以不断往队列里进行添加，但是请求不会被马上处理
        leakyBucket.offer(Thread.currentThread());
        LockSupport.park(this);
        return true;
    }
    @Override
    public void run(){
        //服务端以固定速率从队列里取出请求进行执行
        Thread thread = leakyBucket.poll();
        if(Objects.nonNull(thread)){
            LockSupport.unpark(thread);
        }
        //thread 进行业务处理
    }
}
