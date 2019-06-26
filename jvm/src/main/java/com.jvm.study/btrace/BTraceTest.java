package com.jvm.study.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.println;

/**
 * @Author:weilu
 * @Date: 2019/6/19 22:21
 * @Description: btrace
 *
 *  1,拦截时机：
 *    location=Kind.ENTRY 刚进入函数的时候，不写location默认是刚进入函数
 *    location=Kind.Return 函数返回结果时
 *    location=Kind.Throw  异常抛出
 *    location=Kind.Catch  异常被捕获
 *    location=Kind.Error  异常没被捕获，被抛出函数之外
 *    location=Kind.Call   监控函数里调用的所有其他函数
 *    location=Kind.Line,line=23   监控代码是否到达了23行
 *
 *  2,日志记录：
 *    ./btrace $pid BtraceTest.java > mylog
 */
@BTrace
public class BTraceTest {

    @OnMethod(clazz = "com.jd.jr.epp.openapi_gateway.controller.ApiProxyController",
              method = "proxyRequest",
              location = @Location(Kind.RETURN))
    public static void printParam(@ProbeClassName String name,@ProbeMethodName String method,String interfaceName){
        println(strcat("the class name=>", name));
        println(strcat("the class method=>", method));
        println(strcat("the class method interfaceName=>", str(interfaceName)));
    }
}
