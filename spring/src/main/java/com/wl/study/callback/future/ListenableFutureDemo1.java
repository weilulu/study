package com.wl.study.callback.future;

import com.google.common.util.concurrent.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/5/13 15:59
 * @Description: guava listenableFuture练习
 */
public class ListenableFutureDemo1 {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(1);

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("execute call...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){

                }
                return 4;
            }
        });

        //调加回调方法
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                latch.countDown();
                System.out.println("listenable callback result: "+ result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        },executorService);

        executorService.shutdown();
        //如果想要主线程等待，可以使用countdownLatch
        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
