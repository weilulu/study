package main;

import java.util.concurrent.TimeUnit;

public interface RetrySleeper {

    /**
     *
     * @param time
     * @param unit
     * @throws InterruptedException sleep被中断
     */
    void sleepFor(long time, TimeUnit unit)throws InterruptedException;
}
