package com.wl.study.spring_listener.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author:weilu
 * @Date: 2019/4/17 14:13
 * @Description: 通过实现ApplicationContextListener来获取spring上下文
 */
public class GetApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //获取applicationContext
        ApplicationContext applicationContext = event.getApplicationContext();
        //TODO else
    }
}
