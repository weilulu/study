package com.wl.study.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @Author:weilu
 * @Date: 2019/1/20 19:45
 *
 * spring会为被调用方法所在对象生成一个代理，此代理拥有与服务相同的方法，
 * 如果方法没有被执行切面，则在代理中直接将执行的方法转发给实际的服务，
 * 如果有切面，则会在代理中完成切面，这就是切面的原理。
 * 这也解释了通过testCall方法调用test不会生效
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

        //不生效的原因是被代理的目标对应调用了test方法，而不是代理对应调用的，所以在上面的
        //call方法中先获取到代理对象，然后通过代理对象调用test就可以了，也就是说一定要通过代理对象调用才行
        test();
    }
    @Override
    public void test(){
        System.out.println("aop test");
    }
}
