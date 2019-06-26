package com.wl.study.spring_listener;

import org.springframework.context.ApplicationEvent;

/**
 * @Author:weilu
 * @Date: 2019/4/16 21:25
 */
public class EmailEvent extends ApplicationEvent {

    public String address;
    public String text;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source,String address,String text){
        super(source);
        this.address = address;
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void print(){
        System.out.println("helle spring event!");
    }
}
