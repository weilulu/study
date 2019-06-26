package com.wl.study.watcher.test;

import com.wl.study.watcher.ClientInfo;
import com.wl.study.watcher.annotation.MonitorArgs;
import com.wl.study.watcher.annotation.MonitorMethod;

/**
 * @Author:weilu
 * @Date: 2019/3/18 17:27
 */
public interface MonitorService {

    void methodCall(ClientInfo clientInfo);
}
