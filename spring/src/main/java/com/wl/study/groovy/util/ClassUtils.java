package com.wl.study.groovy.util;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @Author:weilu
 * @Date: 2019/6/26 18:15
 * @Description: ${Description}
 */
public class ClassUtils {

    /**
     * 获取groovy class
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Class getGroovyClass(String fileName)throws Exception{
        ClassPathResource resource = new ClassPathResource(fileName);
        File file = resource.getFile();
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(),config);
        Class<?> groovyClass = groovyClassLoader.parseClass(file);
        return groovyClass;
    }
}
