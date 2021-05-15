package com.jvm.study.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.println;

/**
 * @Author:weilu
 * @Date:2019/8/2 16:22
 * @Description:${Description}
 */
@BTrace
public class InvokeTest {

    @OnMethod(clazz = "com.jd.jr.epp.openapi.gateway.invoke.convert.InnerToJSFConvert",
            method = "recursiveHandleParamter",
            location = @Location(Kind.RETURN))
    public void test(AnyType args){
        println("method args: "+ str(args));
        println("==========================");
    }
}
