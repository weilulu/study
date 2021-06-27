package main.policy;

import main.RetrySleeper;

/**
 * A retry policy that retries until a given amount of time elapses
 * 在给定时间段内进行重试
 */
public class RetryUntilElapsed extends SleepingRetry{
    private final int maxElapsedTimeMs;
    private final int sleepMsBetweenRetries;
    public RetryUntilElapsed(int maxElapsedTimeMs,int sleepMsBetweenRetries){
        super(Integer.MAX_VALUE);
        this.maxElapsedTimeMs = maxElapsedTimeMs;
        this.sleepMsBetweenRetries = sleepMsBetweenRetries;
    }

    @Override
    public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper retrySleeper) {
        return super.allowRetry(retryCount, elapsedTimeMs, retrySleeper) && elapsedTimeMs < maxElapsedTimeMs;
    }

    @Override
    protected long getSleepTimeMs(int retryCount, long elapsedTimeMs) {
        return sleepMsBetweenRetries;
    }
}
