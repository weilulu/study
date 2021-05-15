package com.wl.study.callback.future;

import java.util.concurrent.CompletableFuture;

/**
 * @Author:weilu
 * @Date:2020/5/12 19:40
 * @Description: completableFuture的升级，多个completableFuture可以并行执行
 */
public class CompletableFutureDemo3 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油","https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油","https://money.163.com/code");
        });
        //使用anyOf合并为一个新的CompletableFuture
        //thenApplyAsync()用于串行化另一个CompletableFuture;anyOf()和allOf()用于并行化多个CompletableFuture
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina,cfQueryFrom163);

        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String)code,"https://finance.sina.com.cn/price");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String)code,"https://money.163.com/price");
        });
        //用anyOf合并为一个新的CompletableFetch
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina,cfFetchFrom163);
        //最终结果
        cfFetch.thenAccept((result) -> {
            System.out.println("price: "+result);
        });
        Thread.sleep(200);//main线程不能马上结束，不然拿不到结果，可以考虑使用latch
    }

    static String queryCode(String name,String url){
        System.out.println("query code from "+url+"...");
        try {
            Thread.sleep((long)Math.random() * 100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "601857";
    }

    static Double fetchPrice(String code,String url){
        System.out.println("query price from "+url+"...");
        try {
            Thread.sleep((long)Math.random() * 100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 5 + Math.random() * 20;
    }
}
