package com.wl.study.spring_listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author:weilu
 * @Date: 2019/4/16 21:45
 * @Description: 发送事件
 */
@Component
public class EventPublishTest implements ApplicationContextAware{

    /**
     * 构造一个事件，在容器启动之后发送事件
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //this.applicationContext = applicationContext;

        EmailEvent emailEvent = new EmailEvent("hello","boylmx@163.com","this is a email text!");
        applicationContext.publishEvent(emailEvent);
    }
}
