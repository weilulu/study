package main.policy;
import main.RetryPolicy;
import main.RetrySleeper;

import java.util.concurrent.TimeUnit;

/**
 * 只要线程休眠不被中断就会一直执行，可以看出一直执行也是休眠一段时间再执行
 */
public class RetryForever implements RetryPolicy{
    private final int retryIntervalMs;
    public RetryForever(int retryIntervalMs){
        this.retryIntervalMs = retryIntervalMs;
    }

    @Override
    public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
        try {
            sleeper.sleepFor(retryIntervalMs, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }
}
