package com.wl.study.spring_extent.test;

import com.wl.study.spring_extent.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:weilu
 * @Date: 2019/1/14 22:34
 */
public class Test {

   public static void main(String[] args){
       ApplicationContext cxt = new ClassPathXmlApplicationContext("spring-config.xml");
       User user = (User)cxt.getBean("user");
       System.out.println(user.toString());
   }
}
