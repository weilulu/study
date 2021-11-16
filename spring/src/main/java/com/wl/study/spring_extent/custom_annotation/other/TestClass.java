package com.wl.study.spring_extent.custom_annotation.other;

import com.wl.study.spring_extent.custom_annotation.self.MyAnnotation;
import org.springframework.stereotype.Component;

@Component
public class TestClass {
}
//自定义的注解，不会被spring默认扫描，但通过ImportBeanDefinitionRegistrar就可以识别了
@MyAnnotation
class TestClass2{}

//没任何注解
class TestClass3{}
