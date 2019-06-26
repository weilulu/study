package com.wl.study.spring_listener.listener;

import com.wl.study.spring_listener.EmailEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author:weilu
 * @Date: 2019/4/16 21:28
 * @Description:实现spring监听器
 */
@Component
public class EmailListener implements ApplicationListener<EmailEvent>{

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(EmailEvent event) {
        System.out.println(event.getSource()+","+event.getAddress()+","+event.getText());
    }
}
