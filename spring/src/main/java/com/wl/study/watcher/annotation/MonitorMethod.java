package com.wl.study.watcher.annotation;

import com.wl.study.watcher.IArgsContextFiller;
import com.wl.study.watcher.NoneFiller;

import java.lang.annotation.*;

/**
 * 方法监控埋点
 * @Author:weilu
 * @Date: 2019/3/15 20:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MonitorMethod {
    String alias() default "";
    MonitorArgs[] monitorArgs() default {};

    Class<? extends IArgsContextFiller> filler() default NoneFiller.class;
}
