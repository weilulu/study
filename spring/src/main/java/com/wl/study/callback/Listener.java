package com.wl.study.callback;

/**
 * @Author:weilu
 * @Date:2020/4/27 20:51
 * @Description: 模拟一个耗时任务,用来做回调，将Worker的执行结果放到result的参数里
 */
public interface Listener {
    void result(Object result);

    void exception(String msg);
}
