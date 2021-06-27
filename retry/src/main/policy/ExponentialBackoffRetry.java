package main.policy;

import java.util.Random;

/**
 * Retry policy that retries a set number of times with increasing sleep time between reties
 * 在两次重试间增加休眠时长的重试策略(从名字上看休眠时长会指数增长，但其实是一定范围内的随机，为了避免出现惊群)
 */
public class ExponentialBackoffRetry extends SleepingRetry{
    private static final int MAX_RETRY_LIMIT = 29;
    private static final int DEFAULT_MAX_SLEEP_MS = Integer.MAX_VALUE;
    private final Random random = new Random();
    private final  int baseSleepTimeMs;
    private final  int maxSleepMs;
    public ExponentialBackoffRetry(int baseSleepTimeMs,int maxRetries){
        this(baseSleepTimeMs,maxRetries,DEFAULT_MAX_SLEEP_MS);
    }
    public ExponentialBackoffRetry(int baseSleepTimeMs,int maxRetries,int maxSleepMs){
        super(validateMaxRetries(maxRetries));
        this.baseSleepTimeMs = baseSleepTimeMs;
        this.maxSleepMs = maxSleepMs;
    }
    public long getBaseSleepTimeMs(){return baseSleepTimeMs;}


    private static int validateMaxRetries(int maxRetries){
        if(maxRetries > MAX_RETRY_LIMIT){
            maxRetries = MAX_RETRY_LIMIT;
        }
        return maxRetries;
    }
    @Override
    protected long getSleepTimeMs(int retryCount, long elapsedTimeMs) {
        //这里使用了指数补偿或指数退避的思想
        //使用随机，是为了避免出现惊群效应，防止相同时间执行大量操作
        //1 << n -> 2的n次方； n << 1 -> n*2
        long sleepMs = baseSleepTimeMs * Math.max(1,random.nextInt(1 << (retryCount + 1)));
        if(sleepMs > maxSleepMs){
            sleepMs = maxSleepMs;
        }
        return sleepMs;
    }
}
