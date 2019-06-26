package com.wl.study.concurrent.guava_rateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * @Author:weilu
 * @Date: 2019/3/15 14:27
 */
public class RateLimiterTest   {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.5);//２秒生成１个令牌

        /*boolean result = rateLimiter.tryAcquire(2);//true 能否无延迟的获取２个许可数
        boolean result2 = rateLimiter.tryAcquire(1);//false　能否无延迟的获取１个许可数，因为前面已经获取了２个，则需要等４s
        System.out.println(result);
        System.out.println(result2);*/

        List<Integer> list = Arrays.asList(1,6,2);
        for (Integer i:list) {
            System.out.println(System.currentTimeMillis()+" acq "+i+" wait "+rateLimiter.acquire(i) + " get:"+rateLimiter.getRate());
        }
        /**
         * 1552631936241 acq 1 wait 0.0
         * 1552631936243 acq 6 wait 1.998075
         * 1552631938247 acq 2 wait 11.994508
         *
         * 输出说明：
         * acq 1时，申请消费１个令牌，因为是2s生成一个令牌，所以这里只能消费0.5个令牌
         * acq 6时，申请消费6个令牌，由于之前消费了1个令牌，故而等待了２秒，之后又预消费了６个令牌
         * acq 2时，由于之前预消费了6个令牌，故而等待了12秒
         */
    }


    @Test
    public void test(){
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        System.out.println(rateLimiter.tryAcquire(3)+",count:"+rateLimiter.getRate());
        try {
            Thread.sleep(6 * 1000);
        }catch (InterruptedException e){

        }
        System.out.println(rateLimiter.tryAcquire(1)+",count:"+rateLimiter.getRate());
    }
}
