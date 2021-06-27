package com.jvm.study.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;
@BTrace
public class MyBatisBtrace {

    /*@OnMethod(
            clazz = "com.jd.jr.epp.bankacc.web.service.inner.BankaccCompanyCustomerService",
            method = "getByCustomerId",
            location = @Location(Kind.RETURN)
    )
    public static void query(@Return EppCustomerIdGenerator result){
        println(result);
    }*/
}
