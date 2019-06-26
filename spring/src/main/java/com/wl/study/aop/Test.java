package com.wl.study.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:weilu
 * @Date: 2019/1/20 19:53
 */
public class Test {

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = getContext();
        AopService aopService = applicationContext.getBean(AopService.class);
        aopService.test();
        //aopService.call();//通过这种方式必须先获取AopService
    }

    public static ClassPathXmlApplicationContext getContext(){
        return new ClassPathXmlApplicationContext("aop.xml");
    }
}
