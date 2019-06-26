package com.wl.study.watcher.annotation;

import com.wl.study.watcher.IArgsConvertor;
import com.wl.study.watcher.NoneConvertor;

import java.lang.annotation.*;

/**
 * 埋点参数监控
 * @Author:weilu
 * @Date: 2019/3/15 19:51
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MonitorArgs {

    int level() default 1;
    int topSize() default 2000;
    int index();//指定第几个参数，从１开始，必传
    String alias();//用来指定当前方法里的某个参数，必传
    String path() default "";//也是用来标明方法里的参数的，非必传，和alias重了？//TODO
    /**
     *
     * [可选] 配合JsfContextFiller 使用   用来获取 AppName 等参数
     */
    String key() default "";
    Class<? extends IArgsConvertor> convertor() default NoneConvertor.class;
}
