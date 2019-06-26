package com.wl.study.spring_listener.listener;

import com.wl.study.spring_listener.EmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author:weilu
 * @Date: 2019/4/16 22:03
 * @Description: Listener的另一种实现，不用实现ApplicationListener
 * 1,类上加上Component
 * 2,方法上加上EventListener
 *
 * TODO,目前方法不执行，原因未知。。。
 */
@Component
public class EmailWithAnnotationListener {

    @EventListener
    public void handlerEvent(EmailEvent event){
        System.out.println("EmailListenerWithAnnotation start!");
    }
}
