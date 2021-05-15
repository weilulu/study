package com.wl.study.callback;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/4/29 20:01
 * @Description: guava对java里的Future的改进版
 * ListenableFuture是可以监听的Future，是对java原生的Future的增强，取Future任务完的结果是一个阻塞的过程，而ListenableFuture可以通过回调获取到返回值
 * 输出：
 * call future...
   main
   get listenable future's result with callback:5
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            //新的任务
            @Override
            public Integer call() throws Exception {
                System.out.println("call future...");
                TimeUnit.SECONDS.sleep(2);
                return 5;
            }
        });

        //任务完成后进行回调,取到新任务的值
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                System.out.println("get listenable future's result with callback:"+result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        },listeningExecutorService);

        System.out.println(Thread.currentThread().getName());
    }
}
