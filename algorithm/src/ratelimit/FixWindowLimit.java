package ratelimit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每秒只允许10个请求
 * 每秒钟的时候将固定窗口里的数值置空。
 * 判断的时候只需要比较计数器是否有超过设定值
 */
public class FixWindowLimit {
    private AtomicInteger counter;//计数器
    private int allowCount = 10;
    public void count(){
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        //第1S将统计数值置空
        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                counter.set(0);
            }
        },0,1, TimeUnit.SECONDS);
    }

    public boolean isAllow(){
        if(counter.incrementAndGet() > allowCount){
            return false;
        }
        return true;
    }
}
