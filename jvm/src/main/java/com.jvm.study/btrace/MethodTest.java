package com.jvm.study.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.println;

/**
 * @Author:weilu
 * @Date:2019/7/25 11:38
 * @Description:${Description}
 */
@BTrace
public class MethodTest {
    @OnMethod(clazz = "com.jd.jr.epp.openapi_gateway.auth.blacklist.impl.ApiBlacklistServiceImpl",
            method = "getCheckMap",
            location = @Location(Kind.RETURN))
    public static void printParam(@Self Object self, Object obj, @Return AnyType result){
        println(strcat("the class name=>", str(self)));
        println("method param: "+obj);
        println("method return: "+String.valueOf(result));
        println("==========================");
    }
}
