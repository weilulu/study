package com.wl.study.watcher.test.impl;

import com.wl.study.watcher.ClientInfo;
import com.wl.study.watcher.annotation.MonitorArgs;
import com.wl.study.watcher.annotation.MonitorMethod;
import com.wl.study.watcher.test.MonitorService;
import org.springframework.stereotype.Service;

/**
 * @Author:weilu
 * @Date: 2019/3/20 10:03
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Override
    @MonitorMethod(monitorArgs = {@MonitorArgs(index = 1, alias = "appId", path = "appId")})
    //@MonitorMethod
    public void methodCall(ClientInfo clientInfo){
        System.out.println("limit test");
    }

}
