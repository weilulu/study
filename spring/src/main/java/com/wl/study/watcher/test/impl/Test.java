package com.wl.study.watcher.test.impl;

import com.wl.study.watcher.ClientInfo;
import com.wl.study.watcher.test.MonitorService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:weilu
 * @Date: 2019/3/20 10:20
 */
public class Test {

    public static ClassPathXmlApplicationContext getContext(){
        return new ClassPathXmlApplicationContext("aop.xml");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = getContext();
        MonitorService ms = context.getBean(MonitorService.class);
        /*    for(int i=0;i<5;i++){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ClientInfo clientInfo = new ClientInfo();
                        clientInfo.setAppId("1");
                        ms.methodCall(clientInfo);

                    }
                });
                thread.start();
            }*/
        test1(ms);
        try {
            Thread.sleep(5 * 1000);
        }catch (InterruptedException e){

        }
        test2(ms);

    }

    public static void test1(MonitorService ms){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setAppId("1");
        ms.methodCall(clientInfo);
    }
    public static void test2(MonitorService ms){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setAppId("1");
        ms.methodCall(clientInfo);
    }

}
