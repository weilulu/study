package com.wl.study.callback.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/5/11 20:55
 * @Description: completableFuture的初级使用，异步任务执行完成之后进行回调(发生异常时，进入异常回调入
 * runAsync方法没返回值
 * supplyAsync方法有返回值
 *
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args)throws Exception {
        //创建异步任务,supplyAsync接受一个Supply
        //这里supplyAsync方法中没指定线程池，则会使用默认公共的线程池，如果都使用的是公共线程池，要是有的任务i/o很慢，则会导致整个线程池性能极差而影响整体性能
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureDemo1::fetchPrice);
        //执行成功回调
        cf.thenAccept(result -> {
            System.out.println("price: " + result);
        });
        //执行异常回调
        cf.exceptionally(e ->{
            System.out.println("run exception!!!");
            e.printStackTrace();
            return null;
        });
        //cf.complete(1D);
        //System.out.println(cf.get());在主线程里调用get方法时还是会阻塞，如果主线程里还有别的逻辑可以去执行别的逻辑，如果需要拿CompletableFuture的值那就等待get的返回
        //但是CompletableFuture可以串行或并行多个任务(组合异步编程)，另外，如果主线程里所有任务可以在回调里结束，那就在回调完成就行了。这两点应该是这个框架强大的地方
        //Thread.sleep(200);//!!!!!main线程不能先退出，不然拿不到结果,所以这里的时候不能短于子线程的时间，这是一个问题！！！见上面的分析
    }

    static Double fetchPrice(){
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        /*if(Math.random() < 0.3){
            throw new RuntimeException("fetch price failed!");
        }*/
        return 5 + Math.random() * 20;
    }
}
