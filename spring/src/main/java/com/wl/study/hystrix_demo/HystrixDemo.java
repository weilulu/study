package com.wl.study.hystrix_demo;

import com.netflix.hystrix.*;

public class HystrixDemo extends HystrixCommand<String> {
    private final String name;
    public HystrixDemo(String name){
        /*super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
                *//* 使用HystrixThreadPoolKey工厂定义线程池名称*//*
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool")));*/
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "HystrixThread:"+Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        HystrixDemo demo = new HystrixDemo("semaphore");
        String result = demo.execute();
        System.out.println("result:"+result);
        System.out.println("MainThread:"+Thread.currentThread().getName());
    }
}
