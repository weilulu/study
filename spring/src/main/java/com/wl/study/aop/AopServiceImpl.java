package com.wl.study.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @Author:weilu
 * @Date: 2019/1/20 19:45
 */
@Service
public class AopServiceImpl implements AopService {

    @Override
    public void call(){
        System.out.println("start call");
        //通过AopContext获取AopService对象
        AopService aopService = (AopService)AopContext.currentProxy();
        //通过aopService对象进行调用
        //aopService.test();
    }

    @Override
    public void testCall(){
        //在这里直接调test方法，aop是不会生效的，正确的方法应该像call那样：先
        //获取AopContext，然后转成AopService，使用aopService进行调用,注意在获取AopContext
        //的时候，配置文件里的expose-proxy="true"记得上
        test();
    }
    @Override
    public void test(){
        System.out.println("aop test");
    }
}
