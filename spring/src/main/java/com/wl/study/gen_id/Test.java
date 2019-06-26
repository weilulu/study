package com.wl.study.gen_id;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:weilu
 * @Date: 2019/3/26 10:32
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("spring-config.xml");
        SequenceUtil sequenceUtil = cxt.getBean(SequenceUtil.class);
        String name = "TEST_SEQUENCE";
        /*for (int i=0;i<8;i++){
            System.out.println("id:"+(sequenceUtil.get(name)));
        }*/
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+",id:"+(sequenceUtil.get(name)));
                }
            }).start();
        }
    }
}
