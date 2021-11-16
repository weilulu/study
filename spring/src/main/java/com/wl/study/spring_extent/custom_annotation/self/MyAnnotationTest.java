package com.wl.study.spring_extent.custom_annotation.self;

import com.wl.study.spring_extent.custom_annotation.configure.MyConfigure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试入口
 */
public class MyAnnotationTest {

    public static void main(String[] args) {
        /**加载配置类的入口**/
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfigure.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for(String name : beanDefinitionNames){
            System.out.println(name);
        }
    }

    /**
     * 下面是输出的结果，有三个测试类，分别加了@Component和@MyAnnotation，还有一个什么都没加，这里
     * 只输出了我们自定义对应的bean，说明我们加的注解对应的类是被加到IOC容器里了
     * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
     * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
     * org.springframework.context.annotation.internalRequiredAnnotationProcessor
     * org.springframework.context.annotation.internalCommonAnnotationProcessor
     * org.springframework.context.event.internalEventListenerProcessor
     * org.springframework.context.event.internalEventListenerFactory
     * myAnnotationTest
     * testClass2
     * */
}
