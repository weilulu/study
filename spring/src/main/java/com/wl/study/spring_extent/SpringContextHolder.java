package com.wl.study.spring_extent;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author:weilu
 * @Date: 2019/4/17 10:09
 * @Description: ${Description}
 * 并不是每个地方都能将属性注入，比如在Utils工具类中使用dao就不方便注入了。
 * 个这个时候 我们可以封装spring context，拿到context之后就可以拿到想要的bean了
 */
@Component
public class SpringContextHolder implements ApplicationContextAware,DisposableBean {

    private static ApplicationContext applicationContext;

    /**
     * 获取spring上下文
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        //使用类变量持有applicationContext
        SpringContextHolder.applicationContext = applicationContext;
        System.out.println("got applicationContext!!!");
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return SpringContextHolder.applicationContext;
    }
    /**
     * 清空springContext
     */
    @Override
    public void destroy(){
        SpringContextHolder.clearHolder();
    }

    public static void clearHolder(){
        SpringContextHolder.applicationContext = null;
    }

    /**
     * 根据类型取bean
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type){
        return (T)getApplicationContext().getBean(type);
    }

    /**
     * 根据name取bean
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        assertContextInjected();//先判断一下是否获取到了applicationContext
        return (T)getApplicationContext().getBean(name);
    }

    public static void assertContextInjected(){
        Validate.isTrue(applicationContext == null,"applicationContext injected fail!!!");
    }


    public static void main(String[] args){
        /*int digit = (int)Math.pow(10,7-1);
        int rs = new Random().nextInt(digit * 10);
        if(rs < digit){rs += digit;
        }
        System.out.println(rs);*/

        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < 6; ++i) {
            sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
        }

        String s = sb.toString();
        System.out.println(s);
    }
}
