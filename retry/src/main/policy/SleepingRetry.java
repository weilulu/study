package main.policy;

import main.RetryPolicy;
import main.RetrySleeper;

import java.util.concurrent.TimeUnit;

public abstract class SleepingRetry implements RetryPolicy {
    private final int n;//重试次数
    protected SleepingRetry(int n){
        this.n = n;
    }
    private int getN(){return n;}
    public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper retrySleeper){
        if(retryCount < n){
            try {
                retrySleeper.sleepFor(getSleepTimeMs(retryCount,elapsedTimeMs), TimeUnit.MILLISECONDS);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }
        return false;
    }

    protected abstract long getSleepTimeMs(int retryCount,long elapsedTimeMs);
}
