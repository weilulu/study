package ratelimit;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;

/**
 * 令牌桶算法
 * 令牌桶算法是漏桶算法的改进版，可以支持突发流量。不过与漏桶算法不同的是，令牌桶算法的漏桶中存放的是令牌而不是流量
 * 见：https://www.cnblogs.com/liran123/p/13467089.html
 */
public class TokenBucketRateLimit implements Runnable {
    /**每秒生成的token数*/
    private Integer tokenLimitSecond;
    /**令牌桶队列*/
    private BlockingDeque<String> tokenBucket;
    private static final String TOKEN = "_TOKEN_";
    private ScheduledExecutorService scheduledExecutorService;

    public TokenBucketRateLimit(Integer bucketSize,Integer tokenLimitSecond){
        this.tokenLimitSecond = tokenLimitSecond;
        this.tokenBucket = new LinkedBlockingDeque<>(bucketSize);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        long interval = (1000 * 1000 * 1000)/tokenLimitSecond;
        /**initialDelay：初始化延时;period：两次开始执行最小间隔时间*/
        scheduledExecutorService.scheduleAtFixedRate(this,0,interval, TimeUnit.SECONDS);
    }
    //取令牌,取到之后进行后续处理
    public boolean canPass() throws Exception{
        String token = tokenBucket.poll();
        if(StringUtils.isBlank(token)){
            throw new Exception("无可用令牌");
        }
        return true;
    }
    @Override
    public void run(){
        //达到容量上限
        if(tokenBucket.remainingCapacity() == 0){
            return;
        }
        //往队列放入令牌
        tokenBucket.offer(TOKEN);
    }

}
