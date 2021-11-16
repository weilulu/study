package com.wl.study.spring_extent.custom_annotation.self;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * 自定义扫描器，告诉spring通过指定的标识(MyAnnotation注解)去扫描
 */
public class MyClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry,boolean useDefaultFilters) {
        super(registry,useDefaultFilters);
    }

    /**
     * 可以管理spring扫描类，添加与排除都行
     */
    protected void registerFilters(){
        //重写父类的方法
        //满足任意includeFilters会被加载
        addIncludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
        //重写父类的方法
        //满足任意excludeFilters不会被加载
        //addExcludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages){
        return super.doScan(basePackages);
    }
}
