package com.wl.study.callback.future;

import java.util.concurrent.CompletableFuture;

/**
 * @Author:weilu
 * @Date:2020/5/11 21:19
 * @Description: completableFuture的升级，多个completableFuture可以串行执行
 */
public class CompletableFutureDemo2 {

    public static void main(String[] args)throws Exception {
        //第一个任务
        CompletableFuture cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });
        //第二个任务,在第一个任务执行完的基础上执行第二个任务
        CompletableFuture<Double> cfFetch  = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String)code);
        });
        //第二个任务都执行完之后，获取结果
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        //System.out.println(cfFetch.get());
        Thread.sleep(2000);//main线程不能马上结束，不然拿不到结果，可以考虑使用latch
    }

    static String queryCode(String name){
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "601857";
    }

    static Double fetchPrice(String code){
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 5 + Math.random() * 20;
    }
}
