package com.wl.study.watcher;

import java.util.Map;

/**
 * 对入参进行补充，当前线程同步回调，可用于监控线程上下文中参数
 *
 * @author: jingzhuo
 * @since: 2018/3/12
 */
public interface IArgsContextFiller {

    Map<String, String> fill();
}
