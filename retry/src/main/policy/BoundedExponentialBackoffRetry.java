package main.policy;

public class BoundedExponentialBackoffRetry extends ExponentialBackoffRetry{
    private final int maxSleepTimeMs;

    public BoundedExponentialBackoffRetry(int baseSleepTimeMs,int maxSleepTimeMs, int maxRetries) {
        super(baseSleepTimeMs, maxRetries);
        this.maxSleepTimeMs = maxSleepTimeMs;
    }
    public int getMaxSleepTimeMs(){
        return maxSleepTimeMs;
    }

    protected  long getSleepTimeMs(int retryCount,long elapsedTimeMs){
        return Math.min(maxSleepTimeMs,super.getSleepTimeMs(retryCount,elapsedTimeMs));
    }
}
