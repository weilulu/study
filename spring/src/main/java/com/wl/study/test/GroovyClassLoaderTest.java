package com.wl.study.test;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date: 2019/6/27 18:54
 * @Description: 这种情形下修改了groovy脚本内容，是不会动态更新的
 */
public class GroovyClassLoaderTest {

    public static void main(String[] args)throws Exception {
        String fileName = "script/test.groovy";
        ClassPathResource resource = new ClassPathResource(fileName);
        File file = resource.getFile();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        while (true){
            /*
             *每次执行groovyLoader.parseClass(groovyScript)，
             * Groovy 为了保证每次执行的都是新的脚本内容，会每次生成一个新名字的Class文件
             */
            Class clazz = groovyClassLoader.parseClass(file);
            GroovyObject groovyObject = (GroovyObject)clazz.newInstance();
            Object result = groovyObject.invokeMethod("getJsonString",null);
            System.out.println(result);
            TimeUnit.SECONDS.sleep(2);
        }

    }
}
