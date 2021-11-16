package com.wl.study.spring_extent.custom_annotation.configure;

import com.wl.study.spring_extent.custom_annotation.self.MyAutoConfigureRegister;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 * 作用是指定spring需要扫描的包路径
 */
@Configuration
@Import(MyAutoConfigureRegister.class)
@ComponentScan("com.wl.study.spring_extent.custom_annotation.self")
public class MyConfigure {
}
