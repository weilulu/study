package com.wl.study.spring_extent.processor;

import com.wl.study.spring_extent.bean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author:weilu
 * @Date: 2019/1/14 23:07
 */
public class UserPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if(o instanceof User){
            User user = (User)o;
            user.setName("wl");
            return user;
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
